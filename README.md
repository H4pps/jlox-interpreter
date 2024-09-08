# jlox-interpreter

## Overview
jlox-interpreter is an implementation of the Lox programming language in Java. Written as part of the book "Crafting Interpreters" by Robert Nystrom with some modifications.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- A terminal or command prompt

### Building and Running
To build and run the interpreter, you can use the provided Makefile commands:

- **Run the interpreter with REPL:**
  ```sh
  make ub
  ```

- **Run the interpreter with a script:**
  ```sh
  make ubf
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
