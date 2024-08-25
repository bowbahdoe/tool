package dev.mccue.tools.jmod;

import dev.mccue.tools.AbstractToolOperation;
import dev.mccue.tools.Tool;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public final class JMod
        extends AbstractToolOperation<JMod, JModArguments> {
    public JMod(ToolProvider toolProvider, JModArguments arguments) {
        super(Tool.ofToolProvider(toolProvider),  arguments);
    }

    public JMod(JModArguments arguments) {
        super(
                Tool.ofToolProvider(
                        ToolProvider.findFirst("jmod").orElseThrow()
                ),
                arguments
        );
    }

    public JMod(Consumer<? super JModArguments> consumer) {
        this(new JModArguments());
        consumer.accept(this.arguments);
    }

    public static void execute(ToolProvider toolProvider, JModArguments arguments) throws Exception {
        new JMod(toolProvider, arguments).execute();
    }

    public static void execute(JModArguments arguments) throws Exception {
        new JMod(arguments).execute();
    }

    public static void execute(Consumer<? super JModArguments> consumer) throws Exception {
        new JMod(consumer).execute();
    }
}
