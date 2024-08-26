package lox.errors.runtime;

import lox.token.Token;

public class RuntimeError extends RuntimeException {
  public final Token token;
  
  public RuntimeError(Token token, String message) {
    super(message);
    this.token = token;
  }
}
