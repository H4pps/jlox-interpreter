package lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import lox.scanner.Scanner;
import lox.token.Token;
import lox.token.TokenType;
import lox.parser.Expr;
import lox.parser.Parser;
import lox.parser.AstPrinter;

public class Lox {
  static boolean hadError = false; // it is better to have some kind of "ErrorReporter" interface
  public static void main(String[] args) throws IOException {
    if (args.length > 1) {
      System.out.println("Usage: jlox [script]");
      System.exit(64); 
    } else if (args.length == 1) {
      runFile(args[0]);
    } else {
      runPrompt();
    }
  }
  
  // reading source file
  private static void runFile(String path) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(path));
    run(new String(bytes, Charset.defaultCharset()));

    if (hadError) {
      System.exit(65);
    }
  }

  private static void runPrompt() throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    for (;;) {
      System.out.print("> ");
      String line = reader.readLine();
      if (line == null) { // we are hitting ctrl/cmd + d
        break;
      }
      run(line);
      hadError = false;
    }
  }

  private static void run(String source) {
    Scanner scanner = new Scanner(source);
    List<Token> tokens = scanner.scanTokens();
    Parser parser = new Parser(tokens);
    Expr expression = parser.parse();

    if (hadError) {
      return;
    }

    System.out.println(new AstPrinter().print(expression));
    // for (Token token : tokens) {
    //   System.out.println(token);
    // }
  }

  public static void error(int line, String message) {
    report(line, "", message);
  }

  public static void error(Token token, String message) {
    if (token.type == TokenType.EOF) {
      report(token.line, " at end", message);
    } else {
      report(token.line, " at '" + token.lexeme + "'", message);
    }
  }

  private static void report(int line, String where, String message) {
    System.err.println("[line " + line + "] Error" + where + ": " + message);
    hadError = true;
  }
}