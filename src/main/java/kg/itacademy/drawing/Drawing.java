package kg.itacademy.drawing;

import java.io.*;
import java.util.Scanner;
import kg.itacademy.drawing.canvas.CanvasManager;
import kg.itacademy.drawing.canvas.ConsoleCanvas;
import kg.itacademy.drawing.command.*;
import kg.itacademy.drawing.command.result.CommandResult;

public class Drawing {

  public static void main(String[] args) throws IOException {

    try (Scanner scanner = new Scanner(System.in);
        Writer writer = new PrintWriter(System.out, true);
        PrintWriter error = new PrintWriter(System.err, true)) {

      CanvasManager manager = new CanvasManager(new ConsoleCanvas());

      CommandParser parser = new CommandParser();
      Command command = new NopCommand();
      do {
        writer.write(System.lineSeparator());

        writer.write("Input command: ");
        writer.flush();
        error.flush();
        String input = scanner.nextLine();

        try {
          command = parser.parseCommand(input);
        } catch (IllegalCommandException | IllegalArgumentException e) {
          System.err.println("Verify your input! Error message: " + e.getMessage());
          continue;
        }

        CommandResult commandResult = manager.applyCommand(command);
        commandResult.printCommandResult(writer, error);
        writer.append(System.lineSeparator());

        manager.show(writer);
        writer.write(System.lineSeparator());

      } while (!command.getClass().equals(Quit.class));
    }
  }
}
