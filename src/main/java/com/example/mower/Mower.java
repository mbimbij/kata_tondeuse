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
        if (position.getOrientation() == Orientation.N && position.getY() != environment.getYLimit()) {
          position.setY(position.getY() + 1);
        }else if (position.getOrientation() == Orientation.W){
          position.setX(position.getX() - 1);
        }
        break;

    }
  }
}
