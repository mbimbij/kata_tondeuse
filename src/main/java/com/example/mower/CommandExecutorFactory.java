package com.example.mower;

public interface CommandExecutorFactory {
  IExecuteCommands createMower(String executorString, Environment environment);
}
