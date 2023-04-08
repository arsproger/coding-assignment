package kg.itacademy.drawing.command;

import kg.itacademy.drawing.canvas.Canvas;
import kg.itacademy.drawing.primitive.Shape;
import org.springframework.stereotype.Component;

/** Common interface for various of commands */
@Component
public interface Command {

  /**
   * Draw something using {@link Canvas} interface
   *
   * @return a shape as an iterator of pixes drawn by this command
   */
  Shape draw(Canvas canvas);
}
