package kg.itacademy.drawing.canvas;

import kg.itacademy.drawing.command.Command;
import kg.itacademy.drawing.command.result.CommandResult;
import kg.itacademy.drawing.command.result.FailedResult;
import kg.itacademy.drawing.command.result.OkResult;
import kg.itacademy.drawing.command.result.PartiallyAppliedResult;
import kg.itacademy.drawing.primitive.Pixel;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;

/** Manager to rule canvas, apply some commands on it and show on display the results */
@Component
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
      boolean res = true;
      for (Pixel pixel : command.draw(canvas)) {
        if (!canvas.drawPixel(pixel)) {
          res = false;
        }
      }
      if (res) {
        return new OkResult(command);
      } else {
        return new PartiallyAppliedResult(command);
      }
    } catch (Exception e) {
      return new FailedResult(command, e);
    }
  }

  public void show(Writer writer) throws IOException {
    canvas.show(writer);
  }
}
