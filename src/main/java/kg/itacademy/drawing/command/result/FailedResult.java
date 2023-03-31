package kg.itacademy.drawing.command.result;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import kg.itacademy.drawing.command.Command;

public class FailedResult extends CommandResult {

  private final Exception e;

  public FailedResult(Command command, Exception e) {
    super(command);
    this.e = e;
  }

  @Override
  public void printCommandResult(Writer standard, PrintWriter error) throws IOException {
    error.write(getCommand().getClass().getSimpleName() + " failed with message " + e.getMessage());
    e.printStackTrace(error);
  }
}
