package com.example.mower;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MowerApplication {
  private final Path inputFilePath;

  public MowerApplication(String inputFilePathString) {
    inputFilePath = Paths.get(inputFilePathString);
    if (!Files.exists(inputFilePath)){
      throw new RuntimeException(new FileNotFoundException(inputFilePathString));
    }
  }
}
