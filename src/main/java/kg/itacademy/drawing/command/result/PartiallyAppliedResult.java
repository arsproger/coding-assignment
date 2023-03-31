package kg.itacademy.drawing.command.result;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import kg.itacademy.drawing.command.Command;

public class PartiallyAppliedResult extends CommandResult {

  public PartiallyAppliedResult(Command command) {
    super(command);
  }

  @Override
  public void printCommandResult(Writer standard, PrintWriter error) throws IOException {
    standard.write(
        "[WARN] "
            + getCommand().getClass().getSimpleName()
            + " was partially applied (there were some invisible pixels)");
  }
}
