package com.example.mower;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.mower.Command.A;
import static com.example.mower.Command.G;
import static com.example.mower.Orientation.*;
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
    mower.execute(A);
    assertThat(mower.getPosition()).isEqualTo(expectedPosition);
  }

  @Test
  void givenMowerFacingNorth_andFacingLimit_whenGoForward_thenYCoordinateDoesNotChange() {
    Mower mower = new Mower(1, 5, N, environment);
    Position expectedPosition = new Position(1, 5, N);
    mower.execute(A);
    assertThat(mower.getPosition()).isEqualTo(expectedPosition);
  }

  @Test
  void givenMowerFacingWest_whenGoForward_thenXCoordinateDecreasesBy1() {
    Mower mower = new Mower(1, 5, W, environment);
    Position expectedPosition = new Position(0, 5, W);
    mower.execute(A);
    assertThat(mower.getPosition()).isEqualTo(expectedPosition);
  }

  @Test
  void givenMowerFacingWest_andFacingLimit_whenGoForward_thenXCoordinateDoesNotChange() {
    Mower mower = new Mower(0, 5, W, environment);
    Position expectedPosition = new Position(0, 5, W);
    mower.execute(A);
    assertThat(mower.getPosition()).isEqualTo(expectedPosition);
  }

  @Test
  void givenMowerFacingEast_whenGoForward_thenXCoordinateDecreasesBy1() {
    Mower mower = new Mower(1, 5, E, environment);
    Position expectedPosition = new Position(2, 5, E);
    mower.execute(A);
    assertThat(mower.getPosition()).isEqualTo(expectedPosition);
  }

  @Test
  void givenMowerFacingEast_andFacingBorder_whenGoForward_thenXCoordinateDoesNotChange() {
    Mower mower = new Mower(5, 5, E, environment);
    Position expectedPosition = new Position(5, 5, E);
    mower.execute(A);
    assertThat(mower.getPosition()).isEqualTo(expectedPosition);
  }

  @Test
  void givenMowerFacingSouth_whenGoForward_thenYCoordinateDecreasesBy1() {
    Mower mower = new Mower(1, 5, S, environment);
    Position expectedPosition = new Position(1, 4, S);
    mower.execute(A);
    assertThat(mower.getPosition()).isEqualTo(expectedPosition);
  }

  @Test
  void givenMowerFacingSouth_andFacingBorder_whenGoForward_thenYCoordinateDoesNotChange() {
    Mower mower = new Mower(1, 0, S, environment);
    Position expectedPosition = new Position(1, 0, S);
    mower.execute(A);
    assertThat(mower.getPosition()).isEqualTo(expectedPosition);
  }

  @Test
  void givenMowerFacingNorth_whenTurnLeft_thenFacingWest() {
    Mower mower = new Mower(1, 5, N, environment);
    Position expectedPosition = new Position(1, 5, W);
    mower.execute(G);
    assertThat(mower.getPosition()).isEqualTo(expectedPosition);
  }

  @Test
  void givenMowerFacingWest_whenTurnLeft_thenFacingSouth() {
    Mower mower = new Mower(1, 5, W, environment);
    Position expectedPosition = new Position(1, 5, S);
    mower.execute(G);
    assertThat(mower.getPosition()).isEqualTo(expectedPosition);
  }

  @Test
  void givenMowerFacingSouth_whenTurnLeft_thenFacingEast() {
    Mower mower = new Mower(1, 5, S, environment);
    Position expectedPosition = new Position(1, 5, E);
    mower.execute(G);
    assertThat(mower.getPosition()).isEqualTo(expectedPosition);
  }
}
