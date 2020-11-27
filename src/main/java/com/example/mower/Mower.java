package com.example.mower;

import java.util.Collection;

public class Mower implements IExecuteCommands {

  private Position initialPosition;
  private Position finalPosition;
  private Environment environment;

  public Mower(Position initialPosition, Environment environment) {
    this.initialPosition = initialPosition;
    this.environment = environment;
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
            (position, command) -> command.execute(position,environment),
            (position, position2) -> position);
  }
}

