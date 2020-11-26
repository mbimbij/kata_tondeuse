package com.example.mower;

import io.vavr.control.Try;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class MowerApplication {
  private final Path inputFilePath;
  private final Path outputFilePath;

  public MowerApplication(String inputFilePathString, String outputFilePathString) {
    inputFilePath = Paths.get(inputFilePathString);
    if (!Files.exists(inputFilePath)) {
      throw new RuntimeException(new FileNotFoundException(inputFilePathString));
    }

    outputFilePath = Paths.get(outputFilePathString);
    if (!Files.exists(outputFilePath)) {
      Try.of(() -> Files.createFile(outputFilePath)).get();
    }
  }

  public void run() {
    List<String> inputFileContent = Try.of(() -> Files.readAllLines(inputFilePath)).get();
    if (inputFileContent.isEmpty()) {
      throw new FileFormatException(inputFilePath.toString() + " is empty");
    }
    String environmentString = inputFileContent.stream().findFirst().orElseThrow(() -> new FileFormatException("input is unexpectedly empty"));
    Environment environment = createEnvironment(environmentString);
    Iterator<String> inputFileIterator = inputFileContent.stream().skip(1).iterator();
    while (inputFileIterator.hasNext()) {
      Mower mower = createMower(inputFileIterator.next(), environment);
      mower.executeCommands(createInstructions(inputFileIterator.next()));
      Try.of(() -> Files.writeString(outputFilePath, formatPosition(mower.getPosition()) + System.lineSeparator(), StandardOpenOption.APPEND));
    }
  }

  private CharSequence formatPosition(Position position) {
    return String.join(" ", String.valueOf(position.getX()), String.valueOf(position.getY()), position.getOrientation().name());
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

  public Mower createMower(String mowerString, Environment environment) {
    String[] strings = mowerString.split(" ");
    if (strings.length != 3) {
      throw new FileFormatException(String.format("\"%s\" is not a valid description of mower. It should have only 3 elements", mowerString));
    }
    int x = Integer.parseInt(strings[0]);
    int y = Integer.parseInt(strings[1]);
    Orientation orientation = Orientation.valueOf(strings[2]);
    return new Mower(x, y, orientation, environment);
  }


  public List<Command> createInstructions(String commandsString) {
    return Arrays.stream(commandsString.split("|"))
        .map(Command::valueOf)
        .collect(Collectors.toList());
  }
}
