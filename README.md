# jlox-interpreter

## Overview
jlox-interpreter is an implementation of the Lox programming language, as described in the book "Crafting Interpreters" by Robert Nystrom. This project includes a scanner, parser, interpreter, and error handling for the Lox language.

## Project Structure
The project is organized into several packages, each responsible for different aspects of the interpreter:

- `lox`: Contains the main entry point and overall control logic.
- `lox.scanner`: Responsible for lexical analysis, converting source code into tokens.
- `lox.token`: Defines the `Token` class and `TokenType` enum.
- `lox.parser`: Handles syntax analysis, converting tokens into an abstract syntax tree (AST).
- `lox.interpreter`: Executes the AST, implementing the semantics of the Lox language.
- `lox.errors.runtime`: Contains runtime error handling classes.
- `lox.interpreter.callables`: Defines callable entities like functions.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- A terminal or command prompt

### Building and Running
To build and run the interpreter, you can use the provided Makefile commands:

- **Run the interpreter without a script:**
  ```sh
  make ub
  ```

- **Run the interpreter with a script:**
  ```sh
  make ubf
  ```

- **Generate the AST classes:**
  ```sh
  make utree
  ```

- **Run the AST printer:**
  ```sh
  make uptree
  ```

- **Clean up compiled class files:**
  ```sh
  make rem
  ```

### Usage
You can run the interpreter in two modes: interactive prompt and script mode.

- **Interactive Prompt:**
  ```sh
  java lox.Lox
  ```

- **Script Mode:**
  ```sh
  java lox.Lox path/to/your/script.lox
  ```

## Code Overview

### Main Class
The main entry point of the interpreter is the `Lox` class:
