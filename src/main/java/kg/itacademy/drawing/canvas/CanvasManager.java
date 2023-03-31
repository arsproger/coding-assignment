package kg.itacademy.drawing.canvas;

import java.io.IOException;
import java.io.Writer;
import kg.itacademy.drawing.command.Command;
import kg.itacademy.drawing.command.result.CommandResult;
import kg.itacademy.drawing.command.result.FailedResult;
import kg.itacademy.drawing.command.result.OkResult;
import kg.itacademy.drawing.command.result.PartiallyAppliedResult;
import kg.itacademy.drawing.primitive.Pixel;

/** Manager to rule canvas, apply some commands on it and show on display the results */
public class CanvasManager {

  private final Canvas canvas;

  public CanvasManager(Canvas canvas) {
    this.canvas = canvas;
  }

  /**
   * Apply command on the canvas
   *
   * @return command result with some information about apply process
   */
  public CommandResult applyCommand(Command command) {

    try {
      for (Pixel pixel : command.draw(canvas)) {
        if (!canvas.drawPixel(pixel)) {
          return new PartiallyAppliedResult(command);
        }
      }
      return new OkResult(command);
    } catch (Exception e) {
      return new FailedResult(command, e);
    }
  }

  public void show(Writer writer) throws IOException {
    canvas.show(writer);
  }
}
