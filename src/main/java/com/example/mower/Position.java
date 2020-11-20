package com.example.mower;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {
  private int x;
  private int y;
  private Orientation orientation;
}
