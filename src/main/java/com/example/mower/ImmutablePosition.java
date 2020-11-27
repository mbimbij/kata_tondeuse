package com.example.mower;

import lombok.Value;

@Value
public class ImmutablePosition implements Position {
  int x;
  int y;
  Orientation orientation;
}
