package dev.mccue.tools.javap;

import dev.mccue.tools.AbstractToolRunner;
import dev.mccue.tools.ExitStatusException;
import dev.mccue.tools.Tool;
import dev.mccue.tools.javadoc.JavadocArguments;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public final class Javap extends AbstractToolRunner<Javap, JavapArguments> {
    private Javap(Tool tool, JavapArguments arguments) {
        super(tool, arguments);
    }

    private Javap() {
        super(
                Tool.ofToolProvider(ToolProvider.findFirst("javap").orElseThrow()),
                new JavapArguments()
        );
    }

    public static Javap runner() {
        return new Javap();
    }

    public static Javap runner(Tool tool) {
        return new Javap(tool, new JavapArguments());
    }

    public static Javap runner(Tool tool, JavapArguments arguments) {
        return new Javap(tool, arguments);
    }

    public static Javap runner(JavapArguments arguments) {
        return new Javap(
                Tool.ofToolProvider(ToolProvider.findFirst("javap").orElseThrow()),
                arguments
        );
    }

    public static Javap runner(Consumer<? super JavapArguments> consumer) {
        var javap = runner();
        consumer.accept(javap.arguments);
        return javap;
    }

    public static Javap runner(Tool tool, Consumer<? super JavapArguments> consumer) {
        var javap = runner(tool);
        consumer.accept(javap.arguments);
        return javap;
    }

    public static void run(Tool tool, JavapArguments arguments) throws ExitStatusException {
        runner(tool, arguments).run();
    }

    public static void run(JavapArguments arguments) throws ExitStatusException {
        runner(arguments).run();
    }

    public static void run(Tool tool, Consumer<? super JavapArguments> consumer) throws ExitStatusException {
        runner(tool, consumer).run();
    }


    public static void run(Consumer<? super JavapArguments> consumer) throws ExitStatusException {
        runner(consumer).run();
    }
}
