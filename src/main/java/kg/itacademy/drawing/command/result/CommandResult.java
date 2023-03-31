package kg.itacademy.drawing.command.result;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import kg.itacademy.drawing.command.Command;

public abstract class CommandResult {
  private final Command command;

  public CommandResult(Command command) {
    this.command = command;
  }

  protected Command getCommand() {
    return command;
  }

  public abstract void printCommandResult(Writer standard, PrintWriter error) throws IOException;
}
