package com.example.mower;

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
        }else if (isFacingWest()){
          position.setX(position.getX() - 1);
        }
        break;

    }
  }

  private boolean isFacingWest() {
    return position.getOrientation() == Orientation.W;
  }

  private boolean isFacingNorthBorder() {
    return position.getY() == environment.getYLimit();
  }

  private boolean isFacingNorth() {
    return position.getOrientation() == Orientation.N;
  }
}
