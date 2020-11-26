package com.example.mower;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.example.mower.Command.*;
import static com.example.mower.Orientation.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CommandExecutorTest {

  private Environment environment;

  @BeforeEach
  void setUp() {
    environment = new Environment(5,5);
  }

  @Test
  public void whenCreateMowerWithInitialPosition_thenPositionisAsExpected() {
    CommandExecutor mower = new CommandExecutor(new Position(1, 2, N), environment);
    Position expectedPosition = new Position(1, 2, N);
    assertThat(mower.getPosition()).isEqualTo(expectedPosition);
  }

  @Nested
  class GoForward{
    @Test
    void givenMowerFacingNorth_andNotFacingLimit_whenGoForward_thenYCoordinateIncreasesBy1() {
      CommandExecutor commandExecutor = new CommandExecutor(new Position(1, 2, N), environment);
      Position expectedPosition = new Position(1, 3, N);
      commandExecutor.executeCommands(Collections.singleton(A));
      assertThat(commandExecutor.getPosition()).isEqualTo(expectedPosition);
    }
//
//    @Test
//    void givenMowerFacingNorth_andFacingLimit_whenGoForward_thenYCoordinateDoesNotChange() {
//      Mower mower = new Mower(1, 5, N, environment);
//      Position expectedPosition = new Position(1, 5, N);
//      mower.execute(A);
//      assertThat(mower.getPosition()).isEqualTo(expectedPosition);
//    }
//
//    @Test
//    void givenMowerFacingWest_whenGoForward_thenXCoordinateDecreasesBy1() {
//      Mower mower = new Mower(1, 5, W, environment);
//      Position expectedPosition = new Position(0, 5, W);
//      mower.execute(A);
//      assertThat(mower.getPosition()).isEqualTo(expectedPosition);
//    }
//
//    @Test
//    void givenMowerFacingWest_andFacingLimit_whenGoForward_thenXCoordinateDoesNotChange() {
//      Mower mower = new Mower(0, 5, W, environment);
//      Position expectedPosition = new Position(0, 5, W);
//      mower.execute(A);
//      assertThat(mower.getPosition()).isEqualTo(expectedPosition);
//    }
//
//    @Test
//    void givenMowerFacingEast_whenGoForward_thenXCoordinateDecreasesBy1() {
//      Mower mower = new Mower(1, 5, E, environment);
//      Position expectedPosition = new Position(2, 5, E);
//      mower.execute(A);
//      assertThat(mower.getPosition()).isEqualTo(expectedPosition);
//    }
//
//    @Test
//    void givenMowerFacingEast_andFacingBorder_whenGoForward_thenXCoordinateDoesNotChange() {
//      Mower mower = new Mower(5, 5, E, environment);
//      Position expectedPosition = new Position(5, 5, E);
//      mower.execute(A);
//      assertThat(mower.getPosition()).isEqualTo(expectedPosition);
//    }
//
//    @Test
//    void givenMowerFacingSouth_whenGoForward_thenYCoordinateDecreasesBy1() {
//      Mower mower = new Mower(1, 5, S, environment);
//      Position expectedPosition = new Position(1, 4, S);
//      mower.execute(A);
//      assertThat(mower.getPosition()).isEqualTo(expectedPosition);
//    }
//
//    @Test
//    void givenMowerFacingSouth_andFacingBorder_whenGoForward_thenYCoordinateDoesNotChange() {
//      Mower mower = new Mower(1, 0, S, environment);
//      Position expectedPosition = new Position(1, 0, S);
//      mower.execute(A);
//      assertThat(mower.getPosition()).isEqualTo(expectedPosition);
//    }
  }
//
//  @Nested
//  class TurnLeft{
//    @Test
//    void givenMowerFacingNorth_whenTurnLeft_thenFacingWest() {
//      Mower mower = new Mower(1, 5, N, environment);
//      Position expectedPosition = new Position(1, 5, W);
//      mower.execute(G);
//      assertThat(mower.getPosition()).isEqualTo(expectedPosition);
//    }
//
//    @Test
//    void givenMowerFacingWest_whenTurnLeft_thenFacingSouth() {
//      Mower mower = new Mower(1, 5, W, environment);
//      Position expectedPosition = new Position(1, 5, S);
//      mower.execute(G);
//      assertThat(mower.getPosition()).isEqualTo(expectedPosition);
//    }
//
//    @Test
//    void givenMowerFacingSouth_whenTurnLeft_thenFacingEast() {
//      Mower mower = new Mower(1, 5, S, environment);
//      Position expectedPosition = new Position(1, 5, E);
//      mower.execute(G);
//      assertThat(mower.getPosition()).isEqualTo(expectedPosition);
//    }
//
//    @Test
//    void givenMowerFacingEast_whenTurnLeft_thenFacingNorth() {
//      Mower mower = new Mower(1, 5, E, environment);
//      Position expectedPosition = new Position(1, 5, N);
//      mower.execute(G);
//      assertThat(mower.getPosition()).isEqualTo(expectedPosition);
//    }
//  }
//
//  @Nested
//  class TurnRight{
//    @Test
//    void givenMowerFacingNorth_whenTurnRight_thenFacingEast() {
//      Mower mower = new Mower(1, 5, N, environment);
//      Position expectedPosition = new Position(1, 5, E);
//      mower.execute(D);
//      assertThat(mower.getPosition()).isEqualTo(expectedPosition);
//    }
//
//    @Test
//    void givenMowerFacingEast_whenTurnRight_thenFacingSouth() {
//      Mower mower = new Mower(1, 5, E, environment);
//      Position expectedPosition = new Position(1, 5, S);
//      mower.execute(D);
//      assertThat(mower.getPosition()).isEqualTo(expectedPosition);
//    }
//
//    @Test
//    void givenMowerFacingSouth_whenTurnRight_thenFacingWest() {
//      Mower mower = new Mower(1, 5, S, environment);
//      Position expectedPosition = new Position(1, 5, W);
//      mower.execute(D);
//      assertThat(mower.getPosition()).isEqualTo(expectedPosition);
//    }
//
//    @Test
//    void givenMowerFacingWest_whenTurnRight_thenFacingRight() {
//      Mower mower = new Mower(1, 5, W, environment);
//      Position expectedPosition = new Position(1, 5, N);
//      mower.execute(D);
//      assertThat(mower.getPosition()).isEqualTo(expectedPosition);
//    }
//  }
//
//  @Nested
//  class ExecuteCommandSequence{
//    @Test
//    void givenPosition_1_2_N_whenExecuteFirstSequence_thenPosition_1_3_N() {
//      // GIVEN
//      Mower mower = new Mower(1, 2, N, environment);
//
//      // WHEN
//      mower.execute(G);
//      mower.execute(A);
//      mower.execute(G);
//      mower.execute(A);
//      mower.execute(G);
//      mower.execute(A);
//      mower.execute(G);
//      mower.execute(A);
//      mower.execute(A);
//
//      // THEN
//      Position expectedPosition = new Position(1, 3, N);
//      assertThat(mower.getPosition()).isEqualTo(expectedPosition);
//    }
//
//    @Test
//    void givenPosition_3_3_E_whenExecuteSecondSequence_thenPosition_5_1_E() {
//      // GIVEN
//      Mower mower = new Mower(3, 3, E, environment);
//
//      // WHEN
//      mower.execute(A);
//      mower.execute(A);
//      mower.execute(D);
//      mower.execute(A);
//      mower.execute(A);
//      mower.execute(D);
//      mower.execute(A);
//      mower.execute(D);
//      mower.execute(D);
//      mower.execute(A);
//
//      // THEN
//      Position expectedPosition = new Position(5, 1, E);
//      assertThat(mower.getPosition()).isEqualTo(expectedPosition);
//    }
//  }
}
