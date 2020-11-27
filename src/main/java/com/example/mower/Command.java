package com.example.mower;

import static com.example.mower.Orientation.*;

public enum Command {
  A {
    @Override
    public ImmutablePosition execute(ImmutablePosition currentPosition, Environment environment) {
      return goForward(currentPosition, environment);
    }

    private ImmutablePosition goForward(Position position, Environment environment) {
      if (isFacingNorth(position) && !isFacingNorthBorder(position, environment)) {
        return goForwardUp(position);
      } else if (isFacingWest(position) && !isFacingWestBorder(position)) {
        return goForwardLeft(position);
      } else if (isFacingEast(position) && !isFacingEastBorder(position, environment)) {
        return goForwardRight(position);
      } else if (isFacingSouth(position) && !isFacingSouthBorder(position, environment)) {
        return goForwardDown(position);
      } else {
        return new ImmutablePosition(position.getX(), position.getY(), position.getOrientation());
      }
    }

    private ImmutablePosition goForwardDown(Position position) {
      return new ImmutablePosition(position.getX(), position.getY() - 1, position.getOrientation());
    }

    private boolean isFacingSouthBorder(Position position, Environment environment) {
      return position.getY() <= 0;
    }

    private boolean isFacingSouth(Position position) {
      return position.getOrientation() == S;
    }

    private ImmutablePosition goForwardRight(Position position) {
      return new ImmutablePosition(position.getX() + 1, position.getY(), position.getOrientation());
    }

    private boolean isFacingEastBorder(Position position, Environment environment) {
      return position.getX() >= environment.getXLimit();
    }

    private boolean isFacingEast(Position position) {
      return position.getOrientation() == E;
    }

    private ImmutablePosition goForwardLeft(Position position) {
      return new ImmutablePosition(position.getX() - 1, position.getY(), position.getOrientation());
    }

    private boolean isFacingWestBorder(Position position) {
      return position.getX() <= 0;
    }

    private boolean isFacingWest(Position position) {
      return position.getOrientation() == W;
    }

    private ImmutablePosition goForwardUp(Position position) {
      return new ImmutablePosition(position.getX(), position.getY() + 1, position.getOrientation());
    }

    private boolean isFacingNorthBorder(Position position, Environment environment) {
      return position.getY() >= environment.getYLimit();
    }

    private boolean isFacingNorth(Position position) {
      return position.getOrientation() == N;
    }
  }, G {
    @Override
    public ImmutablePosition execute(ImmutablePosition currentPosition, Environment environment) {
      return null;
    }
  }, D {
    @Override
    public ImmutablePosition execute(ImmutablePosition currentPosition, Environment environment) {
      return null;
    }
  };

  public abstract ImmutablePosition execute(ImmutablePosition currentPosition, Environment environment);
}
