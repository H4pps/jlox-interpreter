ub:
	cd src && javac lox/Lox.java && java lox/Lox && find . -type f -name '*.class' -delete	

ubf:
	cd src && javac lox/Lox.java && java lox/Lox ../test.lox && find . -type f -name '*.class' -delete

utree:
	cd src && javac tools/GenerateAST.java && java tools/GenerateAST lox/parser && find . -type f -name '*.class' -delete

uptree:
	cd src && javac lox/parser/AstPrinter.java && java lox/parser/AstPrinter && find . -type f -name '*.class' -delete