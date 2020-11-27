package com.example.mower;

import java.util.function.BiFunction;
import java.util.function.Function;

public enum Command {
  A(executeCommandService -> executeCommandService::goForward),
  G(executeCommandService -> (immutablePosition, environment) -> immutablePosition.turnLeft()),
  D(executeCommandService -> (immutablePosition, environment) -> immutablePosition.turnRight());

  public final Function<ExecuteCommandService, BiFunction<ImmutablePosition, Environment, ImmutablePosition>> execute;

  Command(Function<ExecuteCommandService, BiFunction<ImmutablePosition, Environment, ImmutablePosition>> execute) {
    this.execute = execute;
  }
}
