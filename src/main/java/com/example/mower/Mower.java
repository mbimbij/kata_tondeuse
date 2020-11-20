package com.example.mower;

public class Mower {

  public static int MAX_Y;
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
        if (position.getY() != MAX_Y) {
          position.setY(position.getY() + 1);
        }
    }
  }
}
