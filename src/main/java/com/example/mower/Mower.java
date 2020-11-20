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
        if (isFacingNorth() && !isFacingNorthBorder()) {
          position.setY(position.getY() + 1);
        }else if (isFacingWest() && !isFacingWestBorder()){
          position.setX(position.getX() - 1);
        }else if (isFacingEast() && !isFacingEastBorder()){
          position.setX(position.getX() + 1);
        }else if (isFacingSouth() && !isFacingSouthBorder()){
          position.setY(position.getY() - 1);
        }
        break;

    }
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
