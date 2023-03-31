package kg.itacademy.drawing.canvas;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.ArrayDeque;
import java.util.Arrays;
import kg.itacademy.drawing.command.CommandLine;
import kg.itacademy.drawing.command.result.FailedResult;
import kg.itacademy.drawing.command.result.OkResult;
import kg.itacademy.drawing.command.result.PartiallyAppliedResult;
import org.junit.jupiter.api.Test;

class CanvasManagerTest {

  @Test
  void commandsResultTest() {
    ConsoleCanvas canvas = new ConsoleCanvas();
    CanvasManager manager = new CanvasManager(canvas);
    assertInstanceOf(FailedResult.class, manager.applyCommand(newLine(0, 0)));

    int width = 10;
    int height = 10;
    canvas.init(width, height);
    assertInstanceOf(OkResult.class, manager.applyCommand(newLine(0, 0)));
    assertInstanceOf(
        PartiallyAppliedResult.class, manager.applyCommand(newLine(width * 10, height * 10)));
  }

  private CommandLine newLine(int endX, int endY) {
    return new CommandLine(
        new ArrayDeque<>(Arrays.asList("0", "0", String.valueOf(endX), String.valueOf(endY))));
  }
}
