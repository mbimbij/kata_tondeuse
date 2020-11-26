package com.example.mower;

public class CommandExecutorFactory {
  public static IExecuteCommands createMower(String mowerString, Environment environment) {
    String[] strings = mowerString.split(" ");
    if (strings.length != 3) {
      throw new FileFormatException(String.format("\"%s\" is not a valid description of mower. It should have only 3 elements", mowerString));
    }
    int x = Integer.parseInt(strings[0]);
    int y = Integer.parseInt(strings[1]);
    Orientation orientation = Orientation.valueOf(strings[2]);
    return new Mower(x, y, orientation, environment);
  }
}
