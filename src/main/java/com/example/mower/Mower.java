package com.example.mower;

public class Mower {

  private Position position;

  public Mower(int x, int y, Orientation orientation) {
    this.position = new Position(x, y, orientation);
  }

  public Position getPosition() {
    return position;
  }

  public void execute(Command command) {
    switch (command) {
      case A:
        position.setY(position.getY() + 1);
    }
  }
}
