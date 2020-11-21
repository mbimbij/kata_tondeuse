package com.example.mower;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MowerApplicationTest {

  private final String inputFilePathString = "src/test/resources/testInputFile.txt";

  @Test
  void givenExistingFile_whenCreateMowerApplication_thenExceptionIsNotThrown() {
    Assertions.assertDoesNotThrow(() -> new MowerApplication(inputFilePathString));
  }

  @Test
  void givenNonExistingFile_whenCreateMowerApplication_thenExceptionIsThrown() {
    String inputFilePathString = "src/test/resources/notExistingFile.txt";
    Assertions.assertThrows(RuntimeException.class, () -> new MowerApplication(inputFilePathString));
  }

  @Test
  void canCreateEnvironmentFromFirstLine() {
    String environmentString = "5 6";
    MowerApplication mowerApplication = new MowerApplication(inputFilePathString);
    Environment environment = mowerApplication.createEnvironment(environmentString);
    SoftAssertions.assertSoftly(softAssertions -> {
      softAssertions.assertThat(environment.getXLimit()).isEqualTo(5);
      softAssertions.assertThat(environment.getYLimit()).isEqualTo(6);
    });
  }
}
