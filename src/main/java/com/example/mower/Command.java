package com.example.mower;

import java.util.function.BiFunction;
import java.util.function.Function;

public enum Command {
  A(executeCommandService -> executeCommandService::goForward),
  G(executeCommandService -> (immutablePosition, environment) -> immutablePosition.turnLeft()),
  D(executeCommandService -> (immutablePosition, environment) -> immutablePosition.turnRight());

  public final Function<ExecuteCommandService, BiFunction<Position, Environment, Position>> execute;

  Command(Function<ExecuteCommandService, BiFunction<Position, Environment, Position>> execute) {
    this.execute = execute;
  }
}
