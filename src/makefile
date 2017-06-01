JFLAGS = -g
JC = javac
JVM = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Board.java \
	CastingOffice.java \
	Deck.java \
	Player.java \
	Role.java \
	Scene.java \
	Trailers.java \
	Set.java \
	XMLParse.java \
	BoardLayersListener.java \
	Deadwood.java

MAIN = DeadWood
default: classes

classes: $(CLASSES:.java=.class)

run : classes
	$(JVM) $(MAIN).class
