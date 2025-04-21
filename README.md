# My-Physics-Engine

This is an old project that I made, it has a somewhat convoluted setup procedure.
This project requires [JavaFX](https://openjfx.io/ ) to work.

The guide here will tell you how to compile and run in a command line terminal with java 17 installed and JFX 20.0.2 or newer.

1. Make sure you know where the JFX install path is, it will be referred to as `$JFXDIR$` from here on.
2. Download the source code
3. Open powershell, cmd or equivalent in the src code folder
4. Compile the code: `javac --module-path $JFXDIR$\lib --add-modules javafx.controls,javafx.fxml TestRunnerTwoLoop.java`
    * Note that TestRunnerTwoLoop.java may be replaced by TestRunnerOneCollider or Runner.java
5. Run the code: `java --module-path $JFXDIR$\lib --add-modules javafx.controls,javafx.fxml TestRunnerTwoLoop`

The same or similar process is used to run the other runners.
