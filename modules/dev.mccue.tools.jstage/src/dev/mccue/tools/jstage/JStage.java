package dev.mccue.tools.jstage;

import dev.mccue.tools.AbstractToolRunner;
import dev.mccue.tools.ExitStatusException;
import dev.mccue.tools.Tool;

import java.util.function.Consumer;

public final class JStage extends AbstractToolRunner<JStage, JStageArguments> {
    private JStage(Tool tool, JStageArguments arguments) {
        super(tool, arguments);
    }

    private JStage() {
        super(
                Tool.ofToolProvider("jstage"),
                new JStageArguments()
        );
    }

    public static JStage runner() {
        return new JStage();
    }

    public static JStage runner(Tool tool) {
        return new JStage(tool, new JStageArguments());
    }

    public static JStage runner(Tool tool, JStageArguments arguments) {
        return new JStage(tool, arguments);
    }

    public static JStage runner(JStageArguments arguments) {
        return new JStage(
                Tool.ofToolProvider("jstage"),
                arguments
        );
    }

    public static JStage runner(Consumer<? super JStageArguments> consumer) {
        var jpackage = runner();
        consumer.accept(jpackage.arguments);
        return jpackage;
    }

    public static JStage runner(Tool tool, Consumer<? super JStageArguments> consumer) {
        var jpackage = runner(tool);
        consumer.accept(jpackage.arguments);
        return jpackage;
    }

    public static void run(Tool tool, JStageArguments arguments) throws ExitStatusException {
        new JStage(tool, arguments).run();
    }

    public static void run(JStageArguments arguments) throws ExitStatusException {
        runner(arguments).run();
    }

    public static void run(Tool tool, Consumer<? super JStageArguments> consumer) throws ExitStatusException {
        runner(tool, consumer).run();
    }

    public static void run(Consumer<? super JStageArguments> consumer) throws ExitStatusException {
        runner(consumer).run();
    }
}