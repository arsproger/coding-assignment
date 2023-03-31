package kg.itacademy.drawing.command;

import java.util.Collections;
import kg.itacademy.drawing.canvas.Canvas;
import kg.itacademy.drawing.primitive.Shape;

public class NopCommand implements Command {

  @Override
  public Shape draw(Canvas canvas) {
    // do nothing
    return new Shape(Collections::emptyIterator);
  }
}
