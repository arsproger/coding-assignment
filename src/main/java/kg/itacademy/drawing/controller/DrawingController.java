package kg.itacademy.drawing.controller;

import kg.itacademy.drawing.canvas.CanvasManager;
import kg.itacademy.drawing.canvas.ConsoleCanvas;
import kg.itacademy.drawing.command.Command;
import kg.itacademy.drawing.command.CommandParser;
import kg.itacademy.drawing.command.IllegalCommandException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@RestController
public class DrawingController {
    private final CanvasManager canvasManager;

    public DrawingController() {
        canvasManager = new CanvasManager(new ConsoleCanvas());
    }

    @GetMapping("/show")
    public String getCanvas() throws IOException {
        StringWriter writer = new StringWriter();
        canvasManager.show(new PrintWriter(writer));
        return writer.toString();
    }

    @PutMapping("/command")
    public String drawOnCanvas(@RequestParam String value) {
        CommandParser parser = new CommandParser();
        try {
            Command commandObj = parser.parseCommand(value);
            canvasManager.applyCommand(commandObj);
            StringWriter writer = new StringWriter();
            canvasManager.show(new PrintWriter(writer));
            return writer.toString();
        } catch (IllegalCommandException | IllegalArgumentException | IOException e) {
            return "Verify your input! Error message: " + e.getMessage();
        }
    }

}
