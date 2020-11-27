package com.example.mower;

public interface CommandExecutorFactory {
  IExecuteCommands createCommandExecutor(String executorString, Environment environment);
}
