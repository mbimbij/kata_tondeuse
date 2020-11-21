package com.example.mower;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class MowerApplication {
  private final Path inputFilePath;

  public MowerApplication(String inputFilePathString) {
    inputFilePath = Paths.get(inputFilePathString);
    if (!Files.exists(inputFilePath)) {
      throw new RuntimeException(new FileNotFoundException(inputFilePathString));
    }
  }

  public Environment createEnvironment(String environmentString) {
    int[] upperRightCornerCoordinates = Arrays.stream(environmentString.split(" "))
        .mapToInt(Integer::parseInt)
        .toArray();
    if (upperRightCornerCoordinates.length != 2) {
      throw new FileFormatException(String.format("\"%s\" is not a valid description of the environment. It should have only 2 elements", environmentString));
    }
    int xLimit = upperRightCornerCoordinates[0];
    int yLimit = upperRightCornerCoordinates[1];
    return new Environment(xLimit, yLimit);
  }
}
