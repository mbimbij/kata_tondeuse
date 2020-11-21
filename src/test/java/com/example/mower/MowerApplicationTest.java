package com.example.mower;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MowerApplicationTest {
  @Test
  void givenExistingFile_canCreateMowerApplication_andNoExceptionThrown() {
    String inputFilePathString = "src/test/resources/testInputFile.txt";
    Assertions.assertDoesNotThrow(() -> new MowerApplication(inputFilePathString));
  }
}
