package kg.itacademy.drawing.controller;

import kg.itacademy.drawing.canvas.CanvasManager;
import kg.itacademy.drawing.command.CommandParser;
import kg.itacademy.drawing.command.IllegalCommandException;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final CommandParser commandParser;

    @Autowired
    public DrawingController(CanvasManager canvasManager, CommandParser commandParser) {
        this.canvasManager = canvasManager;
        this.commandParser = commandParser;
    }

    @GetMapping("/show")
    public String getCanvas() throws IOException {
        StringWriter stringWriter = new StringWriter();
        canvasManager.show(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    @PutMapping("/command")
    public String drawOnCanvas(@RequestParam String value) {
        try {
            canvasManager.applyCommand(commandParser.parseCommand(value));
            StringWriter stringWriter = new StringWriter();
            canvasManager.show(new PrintWriter(stringWriter));
            return stringWriter.toString();
        } catch (IllegalCommandException | IllegalArgumentException | IOException e) {
            return "Verify your input! Error message: " + e.getMessage();
        }
    }

}
