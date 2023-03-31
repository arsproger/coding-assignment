package kg.itacademy.drawing.command;

import static java.lang.Integer.parseInt;

import java.util.Deque;
import java.util.NoSuchElementException;
import kg.itacademy.drawing.algo.FloodFill4;
import kg.itacademy.drawing.canvas.Canvas;
import kg.itacademy.drawing.primitive.Colour;
import kg.itacademy.drawing.primitive.Point;
import kg.itacademy.drawing.primitive.Shape;

public class CommandBucketFill implements Command {
  private static final FloodFill4 fillAlgo = new FloodFill4();

  private final Point startPoint;
  private final Colour colour;

  public CommandBucketFill(Deque<String> args) {
    try {
      startPoint = new Point(parseInt(args.pop()), parseInt(args.pop()));
      String colour = args.pop();
      if (colour.length() != 1) {
        throw new IllegalArgumentException("Colour should be represent as single character");
      }
      this.colour = new Colour(colour.toCharArray()[0]);

    } catch (NoSuchElementException | NumberFormatException e) {
      throw new IllegalArgumentException(
          "Arguments for CommandBucketFill should have 2 coordinates (2 integer values) "
              + "and colour (any ASCII character)");
    }
  }

  public Point getStartPoint() {
    return startPoint;
  }

  public Colour getColour() {
    return colour;
  }

  @Override
  public Shape draw(Canvas canvas) {
    return fillAlgo.fill(canvas, startPoint, colour);
  }
}
