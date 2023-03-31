package kg.itacademy.drawing.algo;

import java.util.*;
import kg.itacademy.drawing.canvas.Canvas;
import kg.itacademy.drawing.primitive.Colour;
import kg.itacademy.drawing.primitive.Pixel;
import kg.itacademy.drawing.primitive.Point;
import kg.itacademy.drawing.primitive.Shape;

/**
 * Flood fill, also called seed fill, is a flooding algorithm that determines and alters the area
 * connected to a given node in a multi-dimensional array with some matching attribute.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Flood_fill">Flood fill algorithm</a>
 */
public class FloodFill4 {

  public Shape fill(Canvas canvas, Point startPoint, Colour colour) {

    Colour startPixelColour;

    Optional<Pixel> optionalPixel = canvas.getPixel(startPoint);
    if (!optionalPixel.isPresent() || optionalPixel.get().getColour().equals(colour)) {
      return new Shape(Collections::emptyIterator);
    } else {
      startPixelColour = optionalPixel.get().getColour();
    }

    final Deque<Point> stack = new ArrayDeque<>(Collections.singletonList(startPoint));
    return new Shape(
        () ->
            new Iterator<Pixel>() {
              final Set<Point> alreadyWatched = new HashSet<>();
              // there is no implementation of Deque based on LinkedHashSet =(
              final Set<Point> inProcess = new HashSet<>();

              @Override
              public boolean hasNext() {
                return !stack.isEmpty();
              }

              @Override
              public Pixel next() {
                Point point = stack.pop();
                inProcess.remove(point);
                alreadyWatched.add(point);
                Pixel result = new Pixel(point, colour);

                checkAndPush(new Point(point.getX() - 1, point.getY()));
                checkAndPush(new Point(point.getX() + 1, point.getY()));
                checkAndPush(new Point(point.getX() + 1, point.getY() + 1));
                checkAndPush(new Point(point.getX(), point.getY() + 1));
                checkAndPush(new Point(point.getX(), point.getY() - 1));

                return result;
              }

              private void checkAndPush(Point point) {
                if (alreadyWatched.contains(point) || inProcess.contains(point)) {
                  return;
                }

                Optional<Pixel> optionalPixel = canvas.getPixel(point);
                if (optionalPixel.isPresent()
                    && optionalPixel.get().getColour().equals(startPixelColour)) {
                  stack.push(point);
                  inProcess.add(point);
                }
              }
            });
  }
}
