package com.example.mower;

import io.vavr.control.Try;
import lombok.SneakyThrows;

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
  private final CommandExecutorFactory commandExecutorFactory;

  @SneakyThrows
  public MowerApplication(String inputFilePathString, String outputFilePathString, CommandExecutorFactory commandExecutorFactory) {
    inputFilePath = Paths.get(inputFilePathString);
    if (!Files.exists(inputFilePath)) {
      throw new FileNotFoundException(inputFilePathString);
    }

    outputFilePath = Paths.get(outputFilePathString);
    if (!Files.exists(outputFilePath)) {
      Try.of(() -> Files.createFile(outputFilePath)).get();
    }
    this.commandExecutorFactory = commandExecutorFactory;
  }

  public void run() {
    List<String> inputFileContent = Try.of(() -> Files.readAllLines(inputFilePath)).get();
    String environmentString = inputFileContent.stream().findFirst().orElseThrow(() -> new FileFormatException(inputFilePath.toString() + " is empty"));
    Environment environment = createEnvironment(environmentString);
    Iterator<String> inputFileIterator = inputFileContent.stream().skip(1).iterator();
    while (inputFileIterator.hasNext()) {
      IExecuteCommands mower = commandExecutorFactory.createMower(inputFileIterator.next(), environment);
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

  public List<Command> createInstructions(String commandsString) {
    return Arrays.stream(commandsString.split("|"))
        .map(Command::valueOf)
        .collect(Collectors.toList());
  }
}
