package com.project;

public class ObstacleFactory extends EntityFactory {
    private Resource resource = new Resource();

    @Override
    public Obstacles createEntity() {
        return new Obstacles((int) (GameScreen.getInstance().screenWidth*1.5));
    }

    public Obstacles.Obstacle createObstacle(ObstacleType type, int x, int y) {
        switch (type) {
            case GROUND_SHORT:
                return new Obstacles.GroundObstacle(resource.getResourceImage("/images/Haystack-short.png"), x, y);
            case GROUND_TALL:
                return new Obstacles.GroundObstacle(resource.getResourceImage("/images/Haystack-tall.png"), x, y);
            case RAISED_SINGLE:
                return new Obstacles.RaisedObstacle(resource.getResourceImage("/images/crow.png"), x, y, 45, 1);
            case RAISED_STACK:
                return new Obstacles.RaisedObstacle(resource.getResourceImage("/images/crow.png"), x, y, 45, 5);
            default:
                throw new IllegalArgumentException("Unknown obstacle type: " + type);
        }
    }
}
