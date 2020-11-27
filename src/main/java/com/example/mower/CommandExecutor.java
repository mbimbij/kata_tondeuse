package com.example.mower;

import java.util.Collection;

public class CommandExecutor implements IExecuteCommands {

  private Position initialPosition;
  private Position finalPosition;
  private Environment environment;
  private ExecuteCommandService executeCommandService;

  public CommandExecutor(Position initialPosition, Environment environment, ExecuteCommandService executeCommandService) {
    this.initialPosition = initialPosition;
    this.environment = environment;
    this.executeCommandService = executeCommandService;
  }

  @Override
  public Position getPosition() {
    if (finalPosition == null){
      return initialPosition;
    }else{
      return finalPosition;
    }
  }

  @Override
  public void executeCommands(Collection<Command> commands) {
    finalPosition = commands.stream()
        .reduce(initialPosition,
            (position, command) -> command.execute
                .apply(executeCommandService)
                .apply(position, environment),
            (position, position2) -> position);
  }
}

