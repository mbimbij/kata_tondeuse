package com.example.mower;

public class ExecuteCommandService {

  public Position goForward(Position position, Environment environment) {
    if (position.isFacingNorth() && !isFacingNorthBorder(position, environment)) {
      return goUp(position);
    } else if (position.isFacingWest() && !isFacingWestBorder(position)) {
      return goLeft(position);
    } else if (position.isFacingEast() && !isFacingEastBorder(position, environment)) {
      return goRight(position);
    } else if (position.isFacingSouth() && !isFacingSouthBorder(position)) {
      return goDown(position);
    } else {
      return new Position(position.getX(), position.getY(), position.getOrientation());
    }
  }

  private Position goDown(Position position) {
    return new Position(position.getX(), position.getY() - 1, position.getOrientation());
  }

  private Position goRight(Position position) {
    return new Position(position.getX() + 1, position.getY(), position.getOrientation());
  }

  private Position goLeft(Position position) {
    return new Position(position.getX() - 1, position.getY(), position.getOrientation());
  }

  private Position goUp(Position position) {
    return new Position(position.getX(), position.getY() + 1, position.getOrientation());
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
}
