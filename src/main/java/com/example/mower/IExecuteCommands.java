package com.example.mower;

import java.util.Collection;

public interface IExecuteCommands {
  Position getPosition();
  void executeCommands(Collection<Command> commands);
}
