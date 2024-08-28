package dev.mccue.tools.jlink;

import dev.mccue.tools.AbstractToolRunner;
import dev.mccue.tools.ExitStatusException;
import dev.mccue.tools.Tool;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public final class JLink
        extends AbstractToolRunner<JLink, JLinkArguments> {
    private JLink(Tool tool, JLinkArguments arguments) {
        super(tool, arguments);
    }

    private JLink() {
        super(
                Tool.ofToolProvider("jlink"),
                new JLinkArguments()
        );
    }

    public static JLink runner() {
        return new JLink();
    }

    public static JLink runner(Tool tool) {
        return new JLink(tool, new JLinkArguments());
    }

    public static JLink runner(Tool tool, JLinkArguments arguments) {
        return new JLink(tool, arguments);
    }

    public static JLink runner(JLinkArguments arguments) {
        return new JLink(
                Tool.ofToolProvider("jlink"),
                arguments
        );
    }

    public static JLink runner(Consumer<? super JLinkArguments> consumer) {
        var jlink = runner();
        consumer.accept(jlink.arguments);
        return jlink;
    }

    public static JLink runner(Tool tool, Consumer<? super JLinkArguments> consumer) {
        var jlink = runner(tool);
        consumer.accept(jlink.arguments);
        return jlink;
    }

    public static void run(Tool tool, JLinkArguments arguments) throws ExitStatusException {
        new JLink(tool, arguments).run();
    }

    public static void run(JLinkArguments arguments) throws ExitStatusException {
        runner(arguments).run();
    }

    public static void run(Tool tool, Consumer<? super JLinkArguments> consumer) throws ExitStatusException {
        runner(tool, consumer).run();
    }


    public static void run(Consumer<? super JLinkArguments> consumer) throws ExitStatusException {
        runner(consumer).run();
    }
}
