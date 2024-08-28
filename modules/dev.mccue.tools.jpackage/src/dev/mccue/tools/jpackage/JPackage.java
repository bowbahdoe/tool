package dev.mccue.tools.jpackage;

import dev.mccue.tools.AbstractToolRunner;
import dev.mccue.tools.ExitStatusException;
import dev.mccue.tools.Tool;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public final class JPackage
        extends AbstractToolRunner<JPackage, JPackageArguments> {
    private JPackage(Tool tool, JPackageArguments arguments) {
        super(tool, arguments);
    }

    private JPackage() {
        super(
                Tool.ofToolProvider("jpackage"),
                new JPackageArguments()
        );
    }

    public static JPackage runner() {
        return new JPackage();
    }

    public static JPackage runner(Tool tool) {
        return new JPackage(tool, new JPackageArguments());
    }

    public static JPackage runner(Tool tool, JPackageArguments arguments) {
        return new JPackage(tool, arguments);
    }

    public static JPackage runner(JPackageArguments arguments) {
        return new JPackage(
                Tool.ofToolProvider("jpackage"),
                arguments
        );
    }

    public static JPackage runner(Consumer<? super JPackageArguments> consumer) {
        var jpackage = runner();
        consumer.accept(jpackage.arguments);
        return jpackage;
    }

    public static JPackage runner(Tool tool, Consumer<? super JPackageArguments> consumer) {
        var jpackage = runner(tool);
        consumer.accept(jpackage.arguments);
        return jpackage;
    }

    public static void run(Tool tool, JPackageArguments arguments) throws ExitStatusException {
        new JPackage(tool, arguments).run();
    }

    public static void run(JPackageArguments arguments) throws ExitStatusException {
        runner(arguments).run();
    }

    public static void run(Tool tool, Consumer<? super JPackageArguments> consumer) throws ExitStatusException {
        runner(tool, consumer).run();
    }

    public static void run(Consumer<? super JPackageArguments> consumer) throws ExitStatusException {
        runner(consumer).run();
    }
}
