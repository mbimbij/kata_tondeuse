package com.example.mower;

public enum Command {
  A{
    @Override
    public Position execute(Position position, Environment environment) {
      if (position.isFacingNorth() && position.getY() < environment.getYLimit()) {
        return position.goUp();
      } else if (position.isFacingWest() && position.getX() > 0) {
        return position.goLeft();
      } else if (position.isFacingEast() && position.getX() < environment.getXLimit()) {
        return position.goRight();
      } else if (position.isFacingSouth() && position.getY() > 0) {
        return position.goDown();
      } else {
        return position;
      }
    }

  },G {
    @Override
    public Position execute(Position position, Environment environment) {
      return position.turnLeft();
    }
  },D {
    @Override
    public Position execute(Position position, Environment environment) {
      return position.turnRight();
    }
  };

  public abstract Position execute(Position position, Environment environment);
}
