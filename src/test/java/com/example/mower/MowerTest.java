package com.example.mower;

import org.junit.jupiter.api.Test;

import static com.example.mower.Orientation.N;
import static org.assertj.core.api.Assertions.assertThat;

public class MowerTest {
  @Test
  public void whenCreateMowerWithInitialPosition_thenPositionisAsExpected() {
    Mower mower = new Mower(1, 2, N);
    Position expectedPosition = new Position(1, 2, N);
    assertThat(mower.getPosition()).isEqualTo(expectedPosition);
  }

  @Test
  void givenMowerNotFacingLimit_whenGoForward_thenYCoordinateIncreasesBy1() {
    Mower.MAX_Y = 5;
    Mower mower = new Mower(1, 2, N);
    Position expectedPosition = new Position(1, 3, N);
    mower.execute(Command.A);
    assertThat(mower.getPosition()).isEqualTo(expectedPosition);
  }

  @Test
  void givenMowerFacingLimit_whenGoForward_thenYCoordinateDoesNotChange() {
    Mower.MAX_Y = 2;
    Mower mower = new Mower(1, 2, N);
    Position expectedPosition = new Position(1, 2, N);
    mower.execute(Command.A);
    assertThat(mower.getPosition()).isEqualTo(expectedPosition);
  }
}
