package com.project;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;


public class GameScreen extends JPanel implements Runnable, IObservable {

    private static GameScreen instance = null;

    private String currentMessage = "";
    private long messageVisibleUntil = 0;
    private static final long MESSAGE_DURATION_NS = 1_000_000_000; // 1 second in nanoseconds


    private List<IObserver> observers = new ArrayList<>();

    final int originalTile = 16;
    final int scale = 3;
    public final int scaledTile = originalTile * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = scaledTile * maxScreenCol;
    final int screenHeight = scaledTile * maxScreenRow;
    private int score;
    private int count;

    private int speed = 10;
    int FPS = 60;

    private Thread gameThread;
    KeyInput keyI = new KeyInput();

    // Entity factories
    private EntityFactory fruitsFactory = new FruitFactory();
    private EntityFactory obstaclesFactory = new ObstacleFactory();

    // Game entities
    private Player player;
    private Obstacles obstacles;
    private Fruits fruits;
    private Ground ground;
    private Background background;

    private boolean running = false;
    private boolean gameOver = false;
    int playerSpeed = 4;

    private JButton playButton;
    private JButton restartButton;
    private JButton exitButton;

    private boolean isGameStarted = false;
    private boolean highScoreAnnounced;
    private HighScoreManager highScoreManager;

    // Private constructor to prevent multiple instantiation and control the object creation
    // Instantiate all the visible game attributes
    private GameScreen(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        Color sky = new Color(135, 206, 235);
        this.setBackground(sky);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyI);
        this.setFocusable(true);
        ImageIcon playIcon = new ImageIcon(new Resource().getResourceImage("/icons/play.png"));
        ImageIcon exitIcon = new ImageIcon(new Resource().getResourceImage("/icons/exit.png"));
        ImageIcon tryAgainIcon = new ImageIcon(new Resource().getResourceImage("/icons/tryAgain.png"));
        playButton = new JButton(playIcon);
        playButton.addActionListener(e -> startGame());

        restartButton = new JButton(tryAgainIcon);
        restartButton.addActionListener(e -> restartGame());
        restartButton.setVisible(false);

        exitButton = new JButton(exitIcon);
        exitButton.addActionListener(e -> System.exit(0));
        this.add(playButton);
        this.add(restartButton);
        this.add(exitButton);

        this.highScoreManager = new HighScoreManager();
        this.highScoreAnnounced = false;
    }

    private void startGame() { 
        if (!isGameStarted) { 
            isGameStarted = true;
            playButton.setVisible(false);
            restartButton.setVisible(false);
            exitButton.setVisible(false);
            startGameThread();
        }
    }
    // Class-based getInstance function to return the instance of the class
    // If the instance is null, create a new instance and initialize the entities
    // Synchronized to ensure that only one thread can be used for the instance creation - prevents multiple
    public static synchronized GameScreen getInstance(){
        if (instance == null) {
            instance = new GameScreen();
            instance.initializeEntities();
        }
        return instance;
    }

    public void initializeEntities() {
        this.ground = new Ground(screenHeight, screenWidth);
        this.background = new Background(screenHeight, screenWidth);
        this.player = new Player.Builder(this, keyI).build();
        this.obstacles = (Obstacles) obstaclesFactory.createEntity();
        this.fruits = (Fruits) fruitsFactory.createEntity();
    }

    // Function to attach an observer to the object, with a list of interesting events that will be observed
    @Override
    public void attach(IObserver observer, List<EventType> interestingEvents) {
        observers.add(observer);
        for (EventType eventType : interestingEvents) {
            EventBus.getInstance().attach(observer, eventType);
        }
    }

    // Function to detach an observer from the object
    @Override
    public void detach(IObserver observer) {
        observers.remove(observer);
    }

    // Function to notify all observers of an event
    // Paint the observation to the screen - in the GameScreen function
    @Override
    public void notifyObservers(EventType eventType, String eventDescription) {
        EventBus.getInstance().postMessage(eventType, eventDescription);
        paintObservation(eventDescription);
    }

    public void startGameThread(){
        this.highScoreAnnounced = false;
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        running = true;

        while (running) {
            if(isGameStarted) {
                
                currentTime = System.nanoTime();

                delta += (currentTime - lastTime) / drawInterval;
                timer += (currentTime - lastTime);
                lastTime = currentTime;

                if (delta >= 1) {
                    update();
                    repaint();
                    delta--;
                }

                if (timer >= 1000000000) {
                    timer = 0;
                }

                if (gameOver) {
                    notifyObservers(EventType.GameOver, "Game Over! Score: " + score);
                    isGameStarted = false;
                    restartButton.setVisible(true);
                    exitButton.setVisible(true);
                    break;
                }
            }
        }
    }

    private void checkHighScore() {
        if (score > highScoreManager.getHighScore()) {
            highScoreManager.setHighScore(score);
            if(!highScoreAnnounced) {
                notifyObservers(EventType.HighScore, "New High Score: " + score);
                highScoreAnnounced = true;
            }
        }
    }

    private void restartGame() {
        score = 0;
        count = 0;
        gameOver = false;
        initializeEntities();
        startGame();
    }

    public void update(){
        checkHighScore();
        count += 1;
        if(count % 50 == 0){
            score += 1;
            speed += .35;
        }
        player.update();
        obstacles.update(speed);
        fruits.update(speed);
        background.update(speed/4);
        ground.update(speed);

        if(obstacles.hasCollidedObstacle(player)){
            player.die();
            repaint();
            running = false;
            gameOver = true;
            checkHighScore();
        }
        if(fruits.hasCollidedFruit(player)){
            score += 5;
            notifyObservers(EventType.Eat, "You ate a fruit! +5");
            checkHighScore();
        }
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2 = (Graphics2D) graphics;
        ground.create(graphics);
        background.create(graphics);
        player.draw(graphics2);
        obstacles.create(graphics);
        for(Fruits.Fruit f : fruits.fruit_list) {
            if(!f.isHit) { // Only draw fruits that are not hit
                graphics.drawImage(f.image, f.x, f.y, null);
            }
        }
        graphics.setFont(new Font("Times New Roman", Font.BOLD,20));
        if(!gameOver) {
            graphics.drawString(Integer.toString(score), getWidth() / 2 - 5, 100);
        }
        long currentTime = System.nanoTime();
        if (!currentMessage.isEmpty() && currentTime < messageVisibleUntil) {
            graphics2.setFont(new Font("Times New Roman", Font.BOLD, 20));
            int messageSize = graphics2.getFontMetrics().stringWidth(currentMessage);
            graphics2.drawString(currentMessage, getWidth() / 2 - messageSize / 2, 120);
        }
        graphics2.drawString("High Score: " + Integer.toString(highScoreManager.getHighScore()), 10, 570);

    }

    public void paintObservation(String message){
        currentMessage = message;
        messageVisibleUntil = System.nanoTime() + MESSAGE_DURATION_NS;
        repaint();
    }

}
