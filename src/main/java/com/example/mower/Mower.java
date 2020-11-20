package com.example.mower;

public class Mower {

  private Position position;

  public Mower(int x, int y, Orientation orientation) {
    this.position = new Position(x, y, orientation);
  }

  public Position getPosition() {
    return position;
  }

}
