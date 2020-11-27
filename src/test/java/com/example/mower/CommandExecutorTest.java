package com.example.mower;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.example.mower.Command.*;
import static com.example.mower.Orientation.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class CommandExecutorTest {

  private Environment environment;

  @BeforeEach
  void setUp() {
    environment = new Environment(5, 5);
  }

  @Test
  public void whenCreateMowerWithInitialPosition_thenPositionisAsExpected() {
    CommandExecutor mower = new CommandExecutor(new Position(1, 2, N), environment, new ExecuteCommandService());
    Position expectedPosition = new Position(1, 2, N);
    assertThat(mower.getPosition()).isEqualTo(expectedPosition);
  }

  @Nested
  class GoForward {
    @Test
    void givenMowerFacingNorth_andNotFacingLimit_whenGoForward_thenYCoordinateIncreasesBy1() {
      CommandExecutor commandExecutor = new CommandExecutor(new Position(1, 2, N), environment, new ExecuteCommandService());
      Position expectedPosition = new Position(1, 3, N);
      commandExecutor.executeCommands(Collections.singleton(A));
      assertThat(commandExecutor.getPosition()).isEqualTo(expectedPosition);
    }

    @Test
    void givenMowerFacingNorth_andFacingLimit_whenGoForward_thenYCoordinateDoesNotChange() {
      CommandExecutor commandExecutor = new CommandExecutor(new Position(1, 5, N), environment, new ExecuteCommandService());
      Position expectedPosition = new Position(1, 5, N);
      commandExecutor.executeCommands(Collections.singleton(A));
      assertThat(commandExecutor.getPosition()).isEqualTo(expectedPosition);
    }

    @Test
    void givenMowerFacingWest_whenGoForward_thenXCoordinateDecreasesBy1() {
      CommandExecutor commandExecutor = new CommandExecutor(new Position(1, 5, W), environment, new ExecuteCommandService());
      Position expectedPosition = new Position(0, 5, W);
      commandExecutor.executeCommands(Collections.singleton(A));
      assertThat(commandExecutor.getPosition()).isEqualTo(expectedPosition);
    }

    @Test
    void givenMowerFacingWest_andFacingLimit_whenGoForward_thenXCoordinateDoesNotChange() {
      CommandExecutor commandExecutor = new CommandExecutor(new Position(0, 5, W), environment, new ExecuteCommandService());
      Position expectedPosition = new Position(0, 5, W);
      commandExecutor.executeCommands(Collections.singleton(A));
      assertThat(commandExecutor.getPosition()).isEqualTo(expectedPosition);
    }

    @Test
    void givenMowerFacingEast_whenGoForward_thenXCoordinateDecreasesBy1() {
      CommandExecutor commandExecutor = new CommandExecutor(new Position(1, 5, E), environment, new ExecuteCommandService());
      Position expectedPosition = new Position(2, 5, E);
      commandExecutor.executeCommands(Collections.singleton(A));
      assertThat(commandExecutor.getPosition()).isEqualTo(expectedPosition);
    }

    @Test
    void givenMowerFacingEast_andFacingBorder_whenGoForward_thenXCoordinateDoesNotChange() {
      CommandExecutor commandExecutor = new CommandExecutor(new Position(5, 5, E), environment, new ExecuteCommandService());
      Position expectedPosition = new Position(5, 5, E);
      commandExecutor.executeCommands(Collections.singleton(A));
      assertThat(commandExecutor.getPosition()).isEqualTo(expectedPosition);
    }

    @Test
    void givenMowerFacingSouth_whenGoForward_thenYCoordinateDecreasesBy1() {
      CommandExecutor commandExecutor = new CommandExecutor(new Position(1, 5, S), environment, new ExecuteCommandService());
      Position expectedPosition = new Position(1, 4, S);
      commandExecutor.executeCommands(Collections.singleton(A));
      assertThat(commandExecutor.getPosition()).isEqualTo(expectedPosition);
    }

    @Test
    void givenMowerFacingSouth_andFacingBorder_whenGoForward_thenYCoordinateDoesNotChange() {
      CommandExecutor commandExecutor = new CommandExecutor(new Position(1, 0, S), environment, new ExecuteCommandService());
      Position expectedPosition = new Position(1, 0, S);
      commandExecutor.executeCommands(Collections.singleton(A));
      assertThat(commandExecutor.getPosition()).isEqualTo(expectedPosition);
    }
  }

  @Nested
  class TurnLeft {
    @Test
    void givenMowerFacingNorth_whenTurnLeft_thenFacingWest() {
      CommandExecutor commandExecutor = new CommandExecutor(new Position(1, 5, N), environment, new ExecuteCommandService());
      Position expectedPosition = new Position(1, 5, W);
      commandExecutor.executeCommands(Collections.singleton(G));
      assertThat(commandExecutor.getPosition()).isEqualTo(expectedPosition);
    }

    @Test
    void givenMowerFacingWest_whenTurnLeft_thenFacingSouth() {
      CommandExecutor commandExecutor = new CommandExecutor(new Position(1, 5, W), environment, new ExecuteCommandService());
      Position expectedPosition = new Position(1, 5, S);
      commandExecutor.executeCommands(Collections.singleton(G));
      assertThat(commandExecutor.getPosition()).isEqualTo(expectedPosition);
    }

    @Test
    void givenMowerFacingSouth_whenTurnLeft_thenFacingEast() {
      CommandExecutor commandExecutor = new CommandExecutor(new Position(1, 5, S), environment, new ExecuteCommandService());
      Position expectedPosition = new Position(1, 5, E);
      commandExecutor.executeCommands(Collections.singleton(G));
      assertThat(commandExecutor.getPosition()).isEqualTo(expectedPosition);
    }

    @Test
    void givenMowerFacingEast_whenTurnLeft_thenFacingNorth() {
      CommandExecutor commandExecutor = new CommandExecutor(new Position(1, 5, E), environment, new ExecuteCommandService());
      Position expectedPosition = new Position(1, 5, N);
      commandExecutor.executeCommands(Collections.singleton(G));
      assertThat(commandExecutor.getPosition()).isEqualTo(expectedPosition);
    }
  }

  @Nested
  class TurnRight {
    @Test
    void givenMowerFacingNorth_whenTurnRight_thenFacingEast() {
      CommandExecutor commandExecutor = new CommandExecutor(new Position(1, 5, N), environment, new ExecuteCommandService());
      Position expectedPosition = new Position(1, 5, E);
      commandExecutor.executeCommands(Collections.singleton(D));
      assertThat(commandExecutor.getPosition()).isEqualTo(expectedPosition);
    }

    @Test
    void givenMowerFacingEast_whenTurnRight_thenFacingSouth() {
      CommandExecutor commandExecutor = new CommandExecutor(new Position(1, 5, E), environment, new ExecuteCommandService());
      Position expectedPosition = new Position(1, 5, S);
      commandExecutor.executeCommands(Collections.singleton(D));
      assertThat(commandExecutor.getPosition()).isEqualTo(expectedPosition);
    }

    @Test
    void givenMowerFacingSouth_whenTurnRight_thenFacingWest() {
      CommandExecutor commandExecutor = new CommandExecutor(new Position(1, 5, S), environment, new ExecuteCommandService());
      Position expectedPosition = new Position(1, 5, W);
      commandExecutor.executeCommands(Collections.singleton(D));
      assertThat(commandExecutor.getPosition()).isEqualTo(expectedPosition);
    }

    @Test
    void givenMowerFacingWest_whenTurnRight_thenFacingRight() {
      CommandExecutor commandExecutor = new CommandExecutor(new Position(1, 5, W), environment, new ExecuteCommandService());
      Position expectedPosition = new Position(1, 5, N);
      commandExecutor.executeCommands(Collections.singleton(D));
      assertThat(commandExecutor.getPosition()).isEqualTo(expectedPosition);
    }
  }

  @Nested
  class ExecuteCommandSequence {
    @Test
    void givenPosition_1_2_N_whenExecuteFirstSequence_thenPosition_1_3_N() {
      // GIVEN
      CommandExecutor commandExecutor = new CommandExecutor(new Position(1, 2, N), environment, new ExecuteCommandService());
      Position expectedPosition = new Position(1, 3, N);

      // WHEN
      commandExecutor.executeCommands(asList(G, A, G, A, G, A, G, A, A));

      // THEN
      assertThat(commandExecutor.getPosition()).isEqualTo(expectedPosition);
    }

    @Test
    void givenPosition_3_3_E_whenExecuteSecondSequence_thenPosition_5_1_E() {
      // GIVEN
      CommandExecutor commandExecutor = new CommandExecutor(new Position(3, 3, E), environment, new ExecuteCommandService());
      Position expectedPosition = new Position(5, 1, E);

      // WHEN
      commandExecutor.executeCommands(asList(A, A, D, A, A, D, A, D, D, A));

      // THEN
      assertThat(commandExecutor.getPosition()).isEqualTo(expectedPosition);
    }
  }
}
