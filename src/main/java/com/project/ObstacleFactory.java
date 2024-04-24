package com.project;

public class ObstacleFactory extends EntityFactory {
    @Override
    public Entity createEntity() {
        return new Obstacles((int) (GameScreen.getInstance().screenWidth * 1.5));
    }
}