package com.example.mower;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static com.example.mower.Command.A;
import static com.example.mower.Command.G;
import static com.example.mower.Orientation.N;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MowerApplicationTest {

  private final String inputFilePathString = "src/test/resources/testInputFile.txt";
  private final String outputFilePathString = "src/test/resources/testOutputFile.txt";
  private MowerApplication mowerApplication;

  @BeforeEach
  void setUp() throws IOException {
    clearOuputFile();
    mowerApplication = new MowerApplication(inputFilePathString, outputFilePathString, new MowerFactory());
  }

  private void clearOuputFile() throws IOException {
    Path outputFilePath = Paths.get(outputFilePathString);
    if (!Files.exists(outputFilePath)) {
      Files.createFile(outputFilePath);
    }
    PrintWriter pw = new PrintWriter(outputFilePathString);
    pw.close();
  }

  @Test
  void givenExistingFile_whenCreateMowerApplication_thenExceptionIsNotThrown() {
    assertDoesNotThrow(() -> new MowerApplication(inputFilePathString, outputFilePathString, new MowerFactory()));
  }

  @Test
  void givenNonExistingFile_whenCreateMowerApplication_thenExceptionIsThrown() {
    String inputFilePathString = "src/test/resources/notExistingFile.txt";
    assertThrows(FileNotFoundException.class, () -> new MowerApplication(inputFilePathString, outputFilePathString, new MowerFactory()));
  }

  @Test
  void canCreateEnvironmentFromString() {
    String environmentString = "5 6";
    Environment environment = mowerApplication.createEnvironment(environmentString);
    SoftAssertions.assertSoftly(softAssertions -> {
      softAssertions.assertThat(environment.getXLimit()).isEqualTo(5);
      softAssertions.assertThat(environment.getYLimit()).isEqualTo(6);
    });
  }

  @Test
  void canCreateMowerFromString() {
    String mowerString = "1 2 N";
    IExecuteCommands executeCommands = new MowerFactory().createCommandExecutor(mowerString, null);
    Position expectedInitialPosition = new Position(1, 2, N);
    assertThat(executeCommands.getPosition()).isEqualTo(expectedInitialPosition);
  }

  @Test
  void canCreateCommandsFromString() {
    // GIVEN
    String commandsString = "GAGAGAGAA";

    // WHEN
    List<Command> commands = mowerApplication.createInstructions(commandsString);

    // THEN
    assertThat(commands).containsExactly(G, A, G, A, G, A, G, A, A);
  }

  @Test
  void givenTestInputFile_whenRun_thenOutputFileHasExpectedContent() throws IOException {
    // WHEN
    mowerApplication.run();

    // THEN
    List<String> expectedOutputFileContent = Arrays.asList(
        "1 3 N",
        "5 1 E");
    Path outputFilePath = Paths.get(outputFilePathString);
    List<String> outputFileContent = Files.readAllLines(outputFilePath);
    assertThat(outputFileContent).containsExactlyElementsOf(expectedOutputFileContent);
  }
}
