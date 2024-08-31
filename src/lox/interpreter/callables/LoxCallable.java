package lox.interpreter.callables;

import java.util.List;

import lox.interpreter.Interpreter;

public interface LoxCallable {
  int arity();
  Object call(Interpreter interpreter, List<Object> arguments);
}
