package kg.itacademy.drawing.command;

import static java.lang.Integer.parseInt;

import java.util.Deque;
import java.util.NoSuchElementException;
import kg.itacademy.drawing.algo.BresenhamAlgo;
import kg.itacademy.drawing.canvas.Canvas;
import kg.itacademy.drawing.primitive.Colour;
import kg.itacademy.drawing.primitive.Point;
import kg.itacademy.drawing.primitive.Shape;

public class CommandLine implements Command {

  public static final Colour LINE_COLOUR = new Colour('x');
  private static final BresenhamAlgo lineAlgo = new BresenhamAlgo();

  private final Point start;
  private final Point end;

  public CommandLine(Deque<String> args) {
    try {
      start = new Point(parseInt(args.pop()), parseInt(args.pop()));
      end = new Point(parseInt(args.pop()), parseInt(args.pop()));

    } catch (NoSuchElementException | NumberFormatException e) {
      throw new IllegalArgumentException(
          "Arguments for CommandLine should have 4 coordinates which represent as 4 integer values");
    }
  }

  public Point getStart() {
    return start;
  }

  public Point getEnd() {
    return end;
  }

  @Override
  public Shape draw(Canvas canvas) {
    return lineAlgo.plotLine(start, end, LINE_COLOUR);
  }
}
