package dev.mccue.tools.javadoc;

import dev.mccue.tools.AbstractToolRunner;
import dev.mccue.tools.ExitStatusException;
import dev.mccue.tools.Tool;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public final class Javadoc
        extends AbstractToolRunner<Javadoc, JavadocArguments> {
    private Javadoc(Tool tool, JavadocArguments arguments) {
        super(tool, arguments);
    }

    private Javadoc() {
        super(
                Tool.ofToolProvider("javadoc"),
                new JavadocArguments()
        );
    }

    public static Javadoc runner() {
        return new Javadoc();
    }

    public static Javadoc runner(Tool tool) {
        return new Javadoc(tool, new JavadocArguments());
    }

    public static Javadoc runner(Tool tool, JavadocArguments arguments) {
        return new Javadoc(tool, arguments);
    }

    public static Javadoc runner(JavadocArguments arguments) {
        return new Javadoc(
                Tool.ofToolProvider("javadoc"),
                arguments
        );
    }

    public static Javadoc runner(Consumer<? super JavadocArguments> consumer) {
        var javadoc = runner();
        consumer.accept(javadoc.arguments);
        return javadoc;
    }

    public static Javadoc runner(Tool tool, Consumer<? super JavadocArguments> consumer) {
        var javadoc = runner(tool);
        consumer.accept(javadoc.arguments);
        return javadoc;
    }

    public static void run(Tool tool, JavadocArguments arguments) throws ExitStatusException {
        new Javadoc(tool, arguments).run();
    }

    public static void run(JavadocArguments arguments) throws ExitStatusException {
        runner(arguments).run();
    }

    public static void run(Tool tool, Consumer<? super JavadocArguments> consumer) throws ExitStatusException {
        runner(tool, consumer).run();
    }

    public static void run(Consumer<? super JavadocArguments> consumer) throws ExitStatusException {
        runner(consumer).run();
    }
}
