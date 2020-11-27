package com.example.mower;

import java.util.Collection;

public class CommandExecutor implements IExecuteCommands {

  private ImmutablePosition currentPosition;
  private Environment environment;
  private ExecuteCommandService executeCommandService;

  public CommandExecutor(ImmutablePosition initialPosition, Environment environment, ExecuteCommandService executeCommandService) {
    this.currentPosition = initialPosition;
    this.environment = environment;
    this.executeCommandService = executeCommandService;
  }

  @Override
  public Position getPosition() {
    return currentPosition;
  }

  @Override
  public void executeCommands(Collection<Command> commands) {
    currentPosition = commands.stream()
        .map(command -> command.execute
            .apply(executeCommandService)
            .apply(currentPosition, environment))
        .reduce((p1, p2) -> p2)
        .orElse(currentPosition);
  }
}
