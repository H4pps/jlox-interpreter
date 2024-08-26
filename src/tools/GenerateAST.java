package tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

// used to generate the code for all of the expressions
public class GenerateAST {
  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
      System.err.println("Usage: generate_ast <output directory>");
      System.exit(64);
    }

    String outputDir = args[0];
    defineAST(outputDir, "Expr", Arrays.asList(
      "Binary   : Expr left, Token operator, Expr right",
      "Grouping : Expr expression",
      "Literal  : Object value",
      "Unary    : Token operator, Expr right"
    ));

    defineAST(outputDir, "Stmt", Arrays.asList(
      "Expression : Expr expression",
      "Print      : Expr expression"
    ));
  }

  private static void defineAST(String outputDir, String baseName, List<String> types)
    throws IOException {

    String path = outputDir + "/" + baseName + ".java";
    try (PrintWriter writer = new PrintWriter(path, "UTF-8")) {
      writer.println("package lox.parser;");
      writer.println();
      writer.println("import java.util.List;");
      writer.println("import lox.token.Token;");
      writer.println();
      writer.println("public abstract class " + baseName + " {");

      defineVisitor(writer, baseName, types);

      for (String type : types) {
        String className = type.split(":")[0].trim();
        String fields = type.split(":")[1].trim(); 
        defineType(writer, baseName, className, fields);
      }

      writer.println();
      writer.println("  public abstract <R> R accept(Visitor<R> visitor);");

      writer.println("}");
    }
  }

  private static void defineVisitor(
    PrintWriter writer, String baseName, List<String> types) {
    writer.println("  public interface Visitor<R> {");

    for (String type : types) {
      String typeName = type.split(":")[0].trim();
      writer.println("    R visit" + typeName + baseName + "(" +
          typeName + " " + baseName.toLowerCase() + ");");
    }

    writer.println("  }");
    writer.println();
  }

  private static void defineType(PrintWriter writer, String baseName, String className, String fieldList) {
    writer.println("  public static class " + className + " extends " + baseName + " {");

    // constructor
    writer.println("    public " + className + "(" + fieldList + ") {");

    // fields initialization
    String[] fields = fieldList.split(", ");
    for (String field : fields) {
      String name = field.split(" ")[1];
      writer.println("      this." + name + " = " + name + ";");
    }

    writer.println("    }");

    // visitor
    writer.println();
    writer.println("    @Override");
    writer.println("    public <R> R accept(Visitor<R> visitor) {");
    writer.println("      return visitor.visit" +
        className + baseName + "(this);");
    writer.println("    }");

    // fields
    writer.println();
    for (String field : fields) {
      writer.println("    public final " + field + ";");
    }

    writer.println("  }\n");
  }
}