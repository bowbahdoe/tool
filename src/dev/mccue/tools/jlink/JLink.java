package dev.mccue.tools.jlink;

import dev.mccue.tools.AbstractToolOperation;
import dev.mccue.tools.Tool;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public final class JLink
        extends AbstractToolOperation<JLink, JLinkArguments> {
    public JLink(ToolProvider toolProvider, JLinkArguments options) {
        super(Tool.ofToolProvider(toolProvider), options);
    }

    public JLink(JLinkArguments options) {
        super(
                Tool.ofToolProvider(
                        ToolProvider.findFirst("jlink").orElseThrow()
                ),
                options
        );
    }

    public JLink(Consumer<? super JLinkArguments> consumer) {
        this(new JLinkArguments());
        consumer.accept(this.arguments);
    }

    public static void execute(ToolProvider toolProvider, JLinkArguments arguments) throws Exception {
        new JLink(toolProvider, arguments).execute();
    }

    public static void execute(JLinkArguments arguments) throws Exception {
        new JLink(arguments).execute();
    }

    public static void execute(Consumer<? super JLinkArguments> consumer) throws Exception {
        new JLink(consumer).execute();
    }
}
