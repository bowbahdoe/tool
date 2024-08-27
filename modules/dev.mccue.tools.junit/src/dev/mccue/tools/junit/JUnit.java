package dev.mccue.tools.junit;

import dev.mccue.tools.AbstractToolRunner;
import dev.mccue.tools.ExitStatusException;
import dev.mccue.tools.Tool;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public final class JUnit extends AbstractToolRunner<JUnit, JUnitArguments> {
    private JUnit(Tool tool, JUnitArguments arguments) {
        super(tool, arguments);
    }

    private JUnit() {
        super(
                Tool.ofToolProvider(ToolProvider.findFirst("jstage").orElseThrow()),
                new JUnitArguments()
        );
    }

    public static JUnit runner() {
        return new JUnit();
    }

    public static JUnit runner(Tool tool) {
        return new JUnit(tool, new JUnitArguments());
    }

    public static JUnit runner(Tool tool, JUnitArguments arguments) {
        return new JUnit(tool, arguments);
    }

    public static JUnit runner(JUnitArguments arguments) {
        return new JUnit(
                Tool.ofToolProvider(ToolProvider.findFirst("jstage").orElseThrow()),
                arguments
        );
    }

    public static JUnit runner(Consumer<? super JUnitArguments> consumer) {
        var jpackage = runner();
        consumer.accept(jpackage.arguments);
        return jpackage;
    }

    public static JUnit runner(Tool tool, Consumer<? super JUnitArguments> consumer) {
        var jpackage = runner(tool);
        consumer.accept(jpackage.arguments);
        return jpackage;
    }

    public static void run(Tool tool, JUnitArguments arguments) throws ExitStatusException {
        new JUnit(tool, arguments).run();
    }

    public static void run(JUnitArguments arguments) throws ExitStatusException {
        runner(arguments).run();
    }

    public static void run(Tool tool, Consumer<? super JUnitArguments> consumer) throws ExitStatusException {
        runner(tool, consumer).run();
    }

    public static void run(Consumer<? super JUnitArguments> consumer) throws ExitStatusException {
        runner(consumer).run();
    }
}
