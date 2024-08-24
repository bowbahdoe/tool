package dev.mccue.tools.jlink;

import dev.mccue.tools.AbstractToolOperation;
import dev.mccue.tools.Tool;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public final class JLink
        extends AbstractToolOperation<JLink, JLinkOptions> {
    public JLink(ToolProvider toolProvider, JLinkOptions options) {
        super(Tool.ofToolProvider(toolProvider), options);
    }

    public JLink(JLinkOptions options) {
        super(
                Tool.ofToolProvider(
                        ToolProvider.findFirst("jlink").orElseThrow()
                ),
                options
        );
    }

    public JLink(Consumer<? super JLinkOptions> consumer) {
        this(new JLinkOptions());
        consumer.accept(this.options);
    }

    public static void execute(ToolProvider toolProvider, JLinkOptions options) throws Exception {
        new JLink(toolProvider, options).execute();
    }

    public static void execute(JLinkOptions options) throws Exception {
        new JLink(options).execute();
    }

    public static void execute(Consumer<? super JLinkOptions> consumer) throws Exception {
        new JLink(consumer).execute();
    }
}
