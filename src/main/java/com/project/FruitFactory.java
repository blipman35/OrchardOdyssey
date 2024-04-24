package com.project;

public class FruitFactory extends EntityFactory {
    @Override
    public Entity createEntity() {
        return new Fruits((int) (GameScreen.getInstance().screenWidth * 1.5));
    }
}
