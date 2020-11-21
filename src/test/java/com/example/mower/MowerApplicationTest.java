package com.example.mower;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MowerApplicationTest {
  @Test
  void givenExistingFile_whenCreateMowerApplication_thenExceptionIsNotThrown() {
    String inputFilePathString = "src/test/resources/testInputFile.txt";
    Assertions.assertDoesNotThrow(() -> new MowerApplication(inputFilePathString));
  }

  @Test
  void givenNonExistingFile_whenCreateMowerApplication_thenExceptionIsThrown() {
    String inputFilePathString = "src/test/resources/notExistingFile.txt";
    Assertions.assertThrows(RuntimeException.class, () -> new MowerApplication(inputFilePathString));
  }
}
