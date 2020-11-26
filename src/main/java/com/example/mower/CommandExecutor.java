package com.example.mower;

import java.util.Collection;

public class CommandExecutor implements IExecuteCommands {

  private Position currentPosition;
  private Environment environment;

  public CommandExecutor(Position initialPosition, Environment environment) {
    this.currentPosition = initialPosition;
    this.environment = environment;
  }

  @Override
  public Position getPosition() {
    return currentPosition;
  }

  @Override
  public void executeCommands(Collection<Command> commands) {
    currentPosition = commands.stream().map(command -> command.execute(currentPosition, environment)).reduce((p1,p2) -> p2).orElse(currentPosition);
  }
}
