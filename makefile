ub:
	cd src && javac lox/Lox.java && java lox/Lox && find . -type f -name '*.class' -delete	

ubf:
	cd src && javac lox/Lox.java && java lox/Lox ../test.lox && find . -type f -name '*.class' -delete
