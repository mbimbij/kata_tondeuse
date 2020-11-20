package com.example.mower;

import lombok.Getter;

@Getter
public class Environment {
  private final int xLimit;
  private int yLimit;

  public Environment(int xLimit, int yLimit) {
    this.xLimit = xLimit;
    this.yLimit = yLimit;
  }
}
