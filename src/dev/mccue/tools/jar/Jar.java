package dev.mccue.tools.jar;

import dev.mccue.tools.AbstractToolOperation;
import dev.mccue.tools.Tool;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public final class Jar extends AbstractToolOperation<Jar, JarArguments> {
    public Jar(ToolProvider toolProvider, JarArguments options) {
        super(Tool.ofToolProvider(toolProvider), options);
    }

    public Jar(Consumer<? super JarArguments> consumer) {
        super(
                Tool.ofToolProvider(
                        ToolProvider.findFirst("jar").orElseThrow()
                ),
                new JarArguments()
        );
        consumer.accept(this.arguments);
    }

    public static void execute(ToolProvider toolProvider, JarArguments options) throws Exception {
        new Jar(toolProvider, options).execute();
    }

    public static void execute(Consumer<? super JarArguments> consumer) throws Exception {
        new Jar(consumer).execute();
    }
}
