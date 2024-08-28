package dev.mccue.tools.jmod;

import dev.mccue.tools.AbstractToolRunner;
import dev.mccue.tools.ExitStatusException;
import dev.mccue.tools.Tool;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public final class JMod
        extends AbstractToolRunner<JMod, JModArguments> {
    private JMod(Tool tool, JModArguments arguments) {
        super(tool, arguments);
    }

    private JMod() {
        super(
                Tool.ofToolProvider("jmod"),
                new JModArguments()
        );
    }

    public static JMod runner() {
        return new JMod();
    }

    public static JMod runner(Tool tool) {
        return new JMod(tool, new JModArguments());
    }

    public static JMod runner(Tool tool, JModArguments arguments) {
        return new JMod(tool, arguments);
    }

    public static JMod runner(JModArguments arguments) {
        return new JMod(
                Tool.ofToolProvider("jmod"),
                arguments
        );
    }

    public static JMod runner(Consumer<? super JModArguments> consumer) {
        var jmod = runner();
        consumer.accept(jmod.arguments);
        return jmod;
    }

    public static JMod runner(Tool tool, Consumer<? super JModArguments> consumer) {
        var jmod = runner(tool);
        consumer.accept(jmod.arguments);
        return jmod;
    }

    public static void run(Tool tool, JModArguments arguments) throws ExitStatusException {
        new JMod(tool, arguments).run();
    }

    public static void run(JModArguments arguments) throws ExitStatusException {
        runner(arguments).run();
    }

    public static void run(Tool tool, Consumer<? super JModArguments> consumer) throws ExitStatusException {
        runner(tool, consumer).run();
    }


    public static void run(Consumer<? super JModArguments> consumer) throws ExitStatusException {
        runner(consumer).run();
    }
}
