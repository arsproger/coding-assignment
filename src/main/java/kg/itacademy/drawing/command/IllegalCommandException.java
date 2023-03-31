package kg.itacademy.drawing.command;

public class IllegalCommandException extends RuntimeException {

  public IllegalCommandException(String message) {
    super(message);
  }
}
