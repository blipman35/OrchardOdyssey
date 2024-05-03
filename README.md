# OrchardOdyssey
<p align="center">
<img src="https://github.com/blipman35/OrchardOdyssey/assets/21202410/dd98f54d-ae27-4459-a5b3-584dc68e7bd4" width="500" height="500">
</p>

## Description 
Welcome to Orchard Odyssey! This is a 2D side-scrolling game, where the player dodges hay bails and crows, while collecting peaches and plums to increase their score. Aim for the highest score possible, and enjoy the scenic orchard views! 

## Design Patterns
- **Singleton** is used to maintain a single instance of the GameScreen throughout the game.
- **Builder** is used to encapsulate the construction details of the player. 
- **Factory** is used to create different types of obstacles, raised vs. ground, as well as the fruits. 
- **Observer** is used to observing when the game ends, fruit is collected, and new high score is reached. This helps us display notifications on screen. 
