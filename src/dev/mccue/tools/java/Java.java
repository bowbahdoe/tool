package dev.mccue.tools.java;

import dev.mccue.tools.AbstractToolRunner;
import dev.mccue.tools.ExitStatusException;
import dev.mccue.tools.Tool;
import dev.mccue.tools.javac.Javac;
import dev.mccue.tools.javac.JavacArguments;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public final class Java
        extends AbstractToolRunner<Java, JavaArguments> {
    private Java(Tool tool, JavaArguments arguments) {
        super(tool, arguments);
    }

    private Java() {
        super(
                Tool.ofSubprocess("java"),
                new JavaArguments()
        );
    }

    public static Java runner() {
        return new Java();
    }

    public static Java runner(Tool tool) {
        return new Java(tool, new JavaArguments());
    }

    public static Java runner(Tool tool, JavaArguments arguments) {
        return new Java(tool, arguments);
    }

    public static Java runner(JavaArguments arguments) {
        return new Java(
                Tool.ofSubprocess("java"),
                arguments
        );
    }

    public static Java runner(Consumer<? super JavaArguments> consumer) {
        var java = runner();
        consumer.accept(java.arguments);
        return java;
    }

    public static Java runner(Tool tool, Consumer<? super JavaArguments> consumer) {
        var java = runner(tool);
        consumer.accept(java.arguments);
        return java;
    }

    public static void run(Tool tool, JavaArguments arguments) throws ExitStatusException {
        new Java(tool, arguments).run();
    }

    public static void run(JavaArguments arguments) throws ExitStatusException {
        runner(arguments).run();
    }

    public static void run(Tool tool, Consumer<? super JavaArguments> consumer) throws ExitStatusException {
        runner(tool, consumer).run();
    }

    public static void run(Consumer<? super JavaArguments> consumer) throws ExitStatusException {
        runner(consumer).run();
    }
}
