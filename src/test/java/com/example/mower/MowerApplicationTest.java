package com.example.mower;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.example.mower.Command.A;
import static com.example.mower.Command.G;
import static com.example.mower.Orientation.N;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MowerApplicationTest {

  private final String inputFilePathString = "src/test/resources/testInputFile.txt";

  @Test
  void givenExistingFile_whenCreateMowerApplication_thenExceptionIsNotThrown() {
    assertDoesNotThrow(() -> new MowerApplication(inputFilePathString));
  }

  @Test
  void givenNonExistingFile_whenCreateMowerApplication_thenExceptionIsThrown() {
    String inputFilePathString = "src/test/resources/notExistingFile.txt";
    assertThrows(RuntimeException.class, () -> new MowerApplication(inputFilePathString));
  }

  @Test
  void canCreateEnvironmentFromString() {
    String environmentString = "5 6";
    MowerApplication mowerApplication = new MowerApplication(inputFilePathString);
    Environment environment = mowerApplication.createEnvironment(environmentString);
    SoftAssertions.assertSoftly(softAssertions -> {
      softAssertions.assertThat(environment.getXLimit()).isEqualTo(5);
      softAssertions.assertThat(environment.getYLimit()).isEqualTo(6);
    });
  }

  @Test
  void canCreateMowerFromString() {
    String mowerString = "1 2 N";
    MowerApplication mowerApplication = new MowerApplication(inputFilePathString);
    Mower mower = mowerApplication.createMower(mowerString, null);
    Position expectedInitialPosition = new Position(1, 2, N);
    assertThat(mower.getPosition()).isEqualTo(expectedInitialPosition);
  }

  @Test
  void canCreateCommandsFromString() {
    String commandsString = "GAGAGAGAA";
    MowerApplication mowerApplication = new MowerApplication(inputFilePathString);
    List<Command> commands = mowerApplication.createInstructions(commandsString);
    assertThat(commands).containsExactly(G, A, G, A, G, A, G, A, A);
  }
}
