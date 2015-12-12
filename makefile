JFLAGS = -g
JC = javac
SRC_DIR = src/
BIN_DIR = bin/

# make .class files
classes:
	mkdir $(BIN_DIR)
	$(JC) $(JFLAGS) $(SRC_DIR)*.java -d $(BIN_DIR)

# delete all .class files
clean:
	rm -r $(BIN_DIR)

# default is make .class files
default: classes

