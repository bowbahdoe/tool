package dev.mccue.tools.javac;

import dev.mccue.tools.AbstractToolRunner;
import dev.mccue.tools.ExitStatusException;
import dev.mccue.tools.Tool;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public final class Javac extends AbstractToolRunner<Javac, JavacArguments> {
    private Javac(Tool tool, JavacArguments arguments) {
        super(tool, arguments);
    }

    private Javac() {
        super(
                Tool.ofToolProvider("javac"),
                new JavacArguments()
        );
    }

    public static Javac runner() {
        return new Javac();
    }

    public static Javac runner(Tool tool) {
        return new Javac(tool, new JavacArguments());
    }

    public static Javac runner(Tool tool, JavacArguments arguments) {
        return new Javac(tool, arguments);
    }

    public static Javac runner(JavacArguments arguments) {
        return new Javac(
                Tool.ofToolProvider("javac"),
                arguments
        );
    }

    public static Javac runner(Consumer<? super JavacArguments> consumer) {
        var javac = runner();
        consumer.accept(javac.arguments);
        return javac;
    }

    public static Javac runner(Tool tool, Consumer<? super JavacArguments> consumer) {
        var javac = runner(tool);
        consumer.accept(javac.arguments);
        return javac;
    }

    public static void run(Tool tool, JavacArguments arguments) throws ExitStatusException {
        new Javac(tool, arguments).run();
    }

    public static void run(JavacArguments arguments) throws ExitStatusException {
        runner(arguments).run();
    }

    public static void run(Tool tool, Consumer<? super JavacArguments> consumer) throws ExitStatusException {
        runner(tool, consumer).run();
    }

    public static void run(Consumer<? super JavacArguments> consumer) throws ExitStatusException {
        runner(consumer).run();
    }
}
