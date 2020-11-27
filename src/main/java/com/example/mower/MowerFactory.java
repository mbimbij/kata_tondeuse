package com.example.mower;

public class MowerFactory implements CommandExecutorFactory {
  @Override
  public IExecuteCommands createCommandExecutor(String mowerString, Environment environment) {
    String[] strings = mowerString.split(" ");
    if (strings.length != 3) {
      throw new FileFormatException(String.format("\"%s\" is not a valid description of mower. It should have only 3 elements", mowerString));
    }
    int x = Integer.parseInt(strings[0]);
    int y = Integer.parseInt(strings[1]);
    Orientation orientation = Orientation.valueOf(strings[2]);
    Position initialPosition = new Position(x, y, orientation);
    return new Mower(initialPosition, environment);
  }
}
