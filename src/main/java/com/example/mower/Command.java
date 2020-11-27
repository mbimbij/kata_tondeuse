package com.example.mower;

import static com.example.mower.Orientation.*;

public enum Command {
  A {
    @Override
    public Position execute(Position currentPosition, Environment environment) {
      return goForward(currentPosition, environment);
    }

    private Position goForward(Position position, Environment environment) {
      if (isFacingNorth(position) && !isFacingNorthBorder(position, environment)) {
        return goForwardUp(position);
      } else if (isFacingWest(position) && !isFacingWestBorder(position)) {
        return goForwardLeft(position);
      } else if (isFacingEast(position) && !isFacingEastBorder(position, environment)) {
        return goForwardRight(position);
      } else if (isFacingSouth(position) && !isFacingSouthBorder(position, environment)) {
        return goForwardDown(position);
      } else {
        return new Position(position.getX(), position.getY(), position.getOrientation());
      }
    }

    private Position goForwardDown(Position position) {
      return new Position(position.getX(), position.getY() - 1, position.getOrientation());
    }

    private boolean isFacingSouthBorder(Position position, Environment environment) {
      return position.getY() <= 0;
    }

    private boolean isFacingSouth(Position position) {
      return position.getOrientation() == S;
    }

    private Position goForwardRight(Position position) {
      return new Position(position.getX() + 1, position.getY(), position.getOrientation());
    }

    private boolean isFacingEastBorder(Position position, Environment environment) {
      return position.getX() >= environment.getXLimit();
    }

    private boolean isFacingEast(Position position) {
      return position.getOrientation() == E;
    }

    private Position goForwardLeft(Position position) {
      return new Position(position.getX() - 1, position.getY(), position.getOrientation());
    }

    private boolean isFacingWestBorder(Position position) {
      return position.getX() <= 0;
    }

    private boolean isFacingWest(Position position) {
      return position.getOrientation() == W;
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
