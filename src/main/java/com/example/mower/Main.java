package com.example.mower;

public class Main {
  public static void main(String[] args) {
    String inputFilePath = "src/main/resources/inputFile.txt";
    String outputFilePath = "src/main/resources/outputFile.txt";
    MowerApplication mowerApplication = new MowerApplication(inputFilePath, outputFilePath);
    mowerApplication.run();
  }
}
