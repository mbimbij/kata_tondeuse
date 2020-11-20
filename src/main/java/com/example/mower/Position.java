package com.example.mower;

import lombok.Value;

@Value
public class Position {
  private int x;
  private int y;
  private Orientation orientation;
}
