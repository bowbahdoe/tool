package dev.mccue.tools.jar;

import dev.mccue.tools.AbstractToolOperation;
import dev.mccue.tools.Tool;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public final class Jar extends AbstractToolOperation<Jar, JarArguments> {
    public Jar(ToolProvider toolProvider, JarArguments arguments) {
        super(Tool.ofToolProvider(toolProvider),  arguments);
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

    public static void execute(ToolProvider toolProvider, JarArguments arguments) throws Exception {
        new Jar(toolProvider,  arguments).execute();
    }

    public static void execute(Consumer<? super JarArguments> consumer) throws Exception {
        new Jar(consumer).execute();
    }
}
