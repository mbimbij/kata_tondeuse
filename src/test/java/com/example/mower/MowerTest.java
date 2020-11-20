package com.example.mower;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.mower.Orientation.N;
import static com.example.mower.Orientation.W;
import static org.assertj.core.api.Assertions.assertThat;

public class MowerTest {

  private Environment environment;
  private Mower mower;

  @BeforeEach
  void setUp() {
    environment = new Environment(5,5);
    mower = new Mower(1, 2, N, environment);
  }

  @Test
  public void whenCreateMowerWithInitialPosition_thenPositionisAsExpected() {
    Position expectedPosition = new Position(1, 2, N);
    assertThat(mower.getPosition()).isEqualTo(expectedPosition);
  }

  @Test
  void givenMowerFacingNorth_andNotFacingLimit_whenGoForward_thenYCoordinateIncreasesBy1() {
    Position expectedPosition = new Position(1, 3, N);
    mower.execute(Command.A);
    assertThat(mower.getPosition()).isEqualTo(expectedPosition);
  }

  @Test
  void givenMowerFacingNorth_andFacingLimit_whenGoForward_thenYCoordinateDoesNotChange() {
    Mower mower = new Mower(1, 5, N, environment);
    Position expectedPosition = new Position(1, 5, N);
    mower.execute(Command.A);
    assertThat(mower.getPosition()).isEqualTo(expectedPosition);
  }

  @Test
  void givenMowerFacingWest_whenGoForward_thenXCoordinateDecreasesBy1() {
    Mower mower = new Mower(1, 5, W, environment);
    Position expectedPosition = new Position(0, 5, W);
    mower.execute(Command.A);
    assertThat(mower.getPosition()).isEqualTo(expectedPosition);
  }

}
