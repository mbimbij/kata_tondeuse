package com.example.mower;

import static com.example.mower.Orientation.N;

public enum Command {
  A {
    @Override
    public Position execute(Position currentPosition, Environment environment) {
      return goForward(currentPosition, environment);
    }

    private Position goForward(Position position, Environment environment) {
      if (isFacingNorth(position) && !isFacingNorthBorder(position, environment)) {
        return goForwardUp(position);
      }
//      else if (isFacingWest() && !isFacingWestBorder()) {
//        goForwardLeft();
//      } else if (isFacingEast() && !isFacingEastBorder()) {
//        goForwardRight();
//      } else if (isFacingSouth() && !isFacingSouthBorder()) {
//        goForwardDown();
//      }
      return null;
    }

    private Position goForwardUp(Position position) {
      return new Position(position.getX(), position.getY() + 1, position.getOrientation());
    }

    private boolean isFacingNorthBorder(Position position, Environment environment) {
      return position.getY() >= environment.getYLimit();
    }

    private boolean isFacingNorth(Position position) {
      return position.getOrientation() == N;
    }
  }, G {
    @Override
    public Position execute(Position currentPosition, Environment environment) {
      return null;
    }
  }, D {
    @Override
    public Position execute(Position currentPosition, Environment environment) {
      return null;
    }
  };

  public abstract Position execute(Position currentPosition, Environment environment);
}
