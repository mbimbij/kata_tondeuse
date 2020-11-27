package com.example.mower;

import lombok.Builder;
import lombok.Value;

import static com.example.mower.Orientation.*;

@Value
@Builder(toBuilder = true)
public class ImmutablePosition implements Position {
  int x;
  int y;
  Orientation orientation;

  public ImmutablePosition turnLeft() {
    return switch (orientation) {
      case N -> toBuilder().orientation(W).build();
      case W -> toBuilder().orientation(S).build();
      case S -> toBuilder().orientation(E).build();
      case E -> toBuilder().orientation(N).build();
    };
  }

  public ImmutablePosition turnRight() {
    return switch (orientation) {
      case N -> toBuilder().orientation(E).build();
      case E -> toBuilder().orientation(S).build();
      case S -> toBuilder().orientation(W).build();
      case W -> toBuilder().orientation(N).build();
    };
  }
}
