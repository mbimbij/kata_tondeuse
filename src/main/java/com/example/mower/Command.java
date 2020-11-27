package com.example.mower;

public enum Command {
  A{
    @Override
    public Position execute(Position position, Environment environment) {
      return goForward(position, environment);
    }

    private Position goForward(Position position, Environment environment) {
      if (position.isFacingNorth() && !isFacingNorthBorder(position, environment)) {
        return position.goUp();
      } else if (position.isFacingWest() && !isFacingWestBorder(position)) {
        return position.goLeft();
      } else if (position.isFacingEast() && !isFacingEastBorder(position, environment)) {
        return position.goRight();
      } else if (position.isFacingSouth() && !isFacingSouthBorder(position)) {
        return position.goDown();
      } else {
        return position;
      }
    }

    private boolean isFacingSouthBorder(Position position) {
      return position.getY() <= 0;
    }

    private boolean isFacingEastBorder(Position position, Environment environment) {
      return position.getX() >= environment.getXLimit();
    }

    private boolean isFacingWestBorder(Position position) {
      return position.getX() <= 0;
    }

    private boolean isFacingNorthBorder(Position position, Environment environment) {
      return position.getY() >= environment.getYLimit();
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
