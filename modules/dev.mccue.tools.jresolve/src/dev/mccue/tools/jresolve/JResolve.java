package dev.mccue.tools.jresolve;

import dev.mccue.tools.AbstractToolRunner;
import dev.mccue.tools.ExitStatusException;
import dev.mccue.tools.Tool;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public final class JResolve extends AbstractToolRunner<JResolve, JResolveArguments> {

    private JResolve(Tool tool, JResolveArguments arguments) {
        super(tool, arguments);
    }

    private JResolve() {
        super(
                Tool.ofToolProvider(ToolProvider.findFirst("jresolve").orElseThrow()),
                new JResolveArguments()
        );
    }

    public static JResolve runner() {
        return new JResolve();
    }

    public static JResolve runner(Tool tool) {
        return new JResolve(tool, new JResolveArguments());
    }

    public static JResolve runner(Tool tool, JResolveArguments arguments) {
        return new JResolve(tool, arguments);
    }

    public static JResolve runner(JResolveArguments arguments) {
        return new JResolve(
                Tool.ofToolProvider(ToolProvider.findFirst("jresolve").orElseThrow()),
                arguments
        );
    }

    public static JResolve runner(Consumer<? super JResolveArguments> consumer) {
        var jpackage = runner();
        consumer.accept(jpackage.arguments);
        return jpackage;
    }

    public static JResolve runner(Tool tool, Consumer<? super JResolveArguments> consumer) {
        var jpackage = runner(tool);
        consumer.accept(jpackage.arguments);
        return jpackage;
    }

    public static void run(Tool tool, JResolveArguments arguments) throws ExitStatusException {
        new JResolve(tool, arguments).run();
    }

    public static void run(JResolveArguments arguments) throws ExitStatusException {
        runner(arguments).run();
    }

    public static void run(Tool tool, Consumer<? super JResolveArguments> consumer) throws ExitStatusException {
        runner(tool, consumer).run();
    }

    public static void run(Consumer<? super JResolveArguments> consumer) throws ExitStatusException {
        runner(consumer).run();
    }
}
