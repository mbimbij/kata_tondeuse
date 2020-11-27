package com.example.mower;

import lombok.Builder;
import lombok.Value;

import static com.example.mower.Orientation.*;

@Value
@Builder(toBuilder = true)
public class Position {
  int x;
  int y;
  Orientation orientation;

  boolean isFacingNorth() {
    return getOrientation() == N;
  }

  boolean isFacingSouth() {
    return getOrientation() == S;
  }

  boolean isFacingEast() {
    return getOrientation() == E;
  }

  boolean isFacingWest() {
    return getOrientation() == W;
  }

  public Position turnLeft() {
    return switch (orientation) {
      case N -> toBuilder().orientation(W).build();
      case W -> toBuilder().orientation(S).build();
      case S -> toBuilder().orientation(E).build();
      case E -> toBuilder().orientation(N).build();
    };
  }

  public Position turnRight() {
    return switch (orientation) {
      case N -> toBuilder().orientation(E).build();
      case E -> toBuilder().orientation(S).build();
      case S -> toBuilder().orientation(W).build();
      case W -> toBuilder().orientation(N).build();
    };
  }
}
