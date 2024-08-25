package dev.mccue.tools.jar;

import dev.mccue.tools.AbstractToolRunner;
import dev.mccue.tools.ExitStatusException;
import dev.mccue.tools.Tool;
import dev.mccue.tools.javadoc.JavadocArguments;
import dev.mccue.tools.jmod.JMod;
import dev.mccue.tools.jmod.JModArguments;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public final class Jar extends AbstractToolRunner<Jar, JarArguments> {
    private Jar(Tool tool, JarArguments arguments) {
        super(tool, arguments);
    }

    private Jar() {
        super(
                Tool.ofToolProvider(ToolProvider.findFirst("jar").orElseThrow()),
                new JarArguments()
        );
    }

    public static Jar runner() {
        return new Jar();
    }

    public static Jar runner(Tool tool) {
        return new Jar(tool, new JarArguments());
    }

    public static Jar runner(Tool tool, JarArguments arguments) {
        return new Jar(tool, arguments);
    }

    public static Jar runner(JarArguments arguments) {
        return new Jar(
                Tool.ofToolProvider(ToolProvider.findFirst("jar").orElseThrow()),
                arguments
        );
    }

    public static Jar runner(Consumer<? super JarArguments> consumer) {
        var jar = runner();
        consumer.accept(jar.arguments);
        return jar;
    }

    public static Jar runner(Tool tool, Consumer<? super JarArguments> consumer) {
        var jar = runner(tool);
        consumer.accept(jar.arguments);
        return jar;
    }

    public static void run(Tool tool, JarArguments arguments) throws ExitStatusException {
        new Jar(tool, arguments).run();
    }

    public static void run(JarArguments arguments) throws ExitStatusException {
        runner(arguments).run();
    }

    public static void run(Tool tool, Consumer<? super JarArguments> consumer) throws ExitStatusException {
        runner(tool, consumer).run();
    }

    public static void run(Consumer<? super JarArguments> consumer) throws ExitStatusException {
        runner(consumer).run();
    }

}
