package com.example.mower;

import static com.example.mower.Orientation.*;

public class Mower {

  private Position position;
  private Environment environment;

  public Mower(int x, int y, Orientation orientation, Environment environment) {
    this.environment = environment;
    this.position = new Position(x, y, orientation);
  }

  public Position getPosition() {
    return position;
  }

  public void execute(Command command) {
    switch (command) {
      case A:
        goForward();
        break;
      case G:
        turnLeft();
        break;
      case D:
        turnRight();
        break;
    }
  }

  private void goForward() {
    if (isFacingNorth() && !isFacingNorthBorder()) {
      goForwardUp();
    } else if (isFacingWest() && !isFacingWestBorder()) {
      goForwardLeft();
    } else if (isFacingEast() && !isFacingEastBorder()) {
      goForwardRight();
    } else if (isFacingSouth() && !isFacingSouthBorder()) {
      goForwardDown();
    }
  }

  private void turnLeft() {
    if (isFacingNorth()) {
      faceWest();
    } else if (isFacingWest()) {
      faceSouth();
    } else if (isFacingSouth()) {
      faceEast();
    } else if (isFacingEast()) {
      faceNorth();
    }
  }

  private void turnRight() {
    if (isFacingNorth()) {
      faceEast();
    } else if (isFacingEast()) {
      faceSouth();
    } else if (isFacingSouth()) {
      faceWest();
    } else if (isFacingWest()) {
      faceNorth();
    }
  }

  private void faceEast() {
    position.setOrientation(E);
  }

  private void faceNorth() {
    position.setOrientation(N);
  }

  private void faceSouth() {
    position.setOrientation(S);
  }

  private void faceWest() {
    position.setOrientation(W);
  }

  private void goForwardDown() {
    position.setY(position.getY() - 1);
  }

  private void goForwardRight() {
    position.setX(position.getX() + 1);
  }

  private void goForwardLeft() {
    position.setX(position.getX() - 1);
  }

  private void goForwardUp() {
    position.setY(position.getY() + 1);
  }

  private boolean isFacingSouthBorder() {
    return position.getY() <= 0;
  }

  private boolean isFacingSouth() {
    return position.getOrientation() == S;
  }

  private boolean isFacingEastBorder() {
    return position.getX() >= environment.getXLimit();
  }

  private boolean isFacingEast() {
    return position.getOrientation() == E;
  }

  private boolean isFacingWestBorder() {
    return position.getX() <= 0;
  }

  private boolean isFacingWest() {
    return position.getOrientation() == W;
  }

  private boolean isFacingNorthBorder() {
    return position.getY() >= environment.getYLimit();
  }

  private boolean isFacingNorth() {
    return position.getOrientation() == N;
  }
}
