package kg.itacademy.drawing.algo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import kg.itacademy.drawing.canvas.Canvas;
import kg.itacademy.drawing.canvas.ConsoleCanvas;
import kg.itacademy.drawing.primitive.Colour;
import kg.itacademy.drawing.primitive.Pixel;
import kg.itacademy.drawing.primitive.Point;
import kg.itacademy.drawing.primitive.Shape;
import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.Test;

public class FloodFill4Test {

  @Test
  public void fill() {
    FloodFill4 algo = new FloodFill4();
    Canvas canvas = new ConsoleCanvas();
    canvas.init(3, 3);

    Shape fill = algo.fill(canvas, new Point(1, 1), new Colour('o'));
    assertEquals(
        Arrays.asList(
            new Pixel(0, 0, 'o'),
            new Pixel(0, 1, 'o'),
            new Pixel(0, 2, 'o'),
            new Pixel(1, 0, 'o'),
            new Pixel(1, 1, 'o'),
            new Pixel(1, 2, 'o'),
            new Pixel(2, 0, 'o'),
            new Pixel(2, 1, 'o'),
            new Pixel(2, 2, 'o')),
        IterableUtils.toList(fill).stream()
            .sorted(Comparator.comparing(Pixel::getX).thenComparing(Pixel::getY))
            .collect(Collectors.toList()));
  }
}
