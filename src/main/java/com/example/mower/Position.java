package com.example.mower;

import static com.example.mower.Orientation.*;

public interface Position {
  int getX();
  int getY();
  Orientation getOrientation();

  default boolean isFacingNorth() {
    return getOrientation() == N;
  }

  default boolean isFacingSouth() {
    return getOrientation() == S;
  }

  default boolean isFacingEast() {
    return getOrientation() == E;
  }

  default boolean isFacingWest() {
    return getOrientation() == W;
  }
}
