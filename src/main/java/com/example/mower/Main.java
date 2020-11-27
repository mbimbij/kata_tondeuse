package com.example.mower;

public class Main {
  public static void main(String[] args) {
    String inputFilePath = "src/main/resources/inputFile.txt";
    String outputFilePath = "src/main/resources/outputFile.txt";
//    CommandExecutorFactory commandExecutorFactory = new MowerFactory();
    CommandExecutorFactory commandExecutorFactory = new MowerFactory();
    MowerApplication mowerApplication = new MowerApplication(inputFilePath, outputFilePath, commandExecutorFactory);
    mowerApplication.run();
  }
}
