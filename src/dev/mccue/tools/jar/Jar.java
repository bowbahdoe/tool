package dev.mccue.tools.jar;

import dev.mccue.tools.AbstractToolOperation;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public class Jar extends AbstractToolOperation<Jar, JarOptions> {
    public Jar(ToolProvider toolProvider, JarOptions options) {
        super(toolProvider, options);
    }

    public Jar(Consumer<? super JarOptions> consumer) {
        super(ToolProvider.findFirst("jar").orElseThrow(), new JarOptions());
        consumer.accept(this.options);
    }

    public static void execute(ToolProvider toolProvider, JarOptions options) throws Exception {
        new Jar(toolProvider, options).execute();
    }

    public static void execute(Consumer<? super JarOptions> consumer) throws Exception {
        new Jar(consumer).execute();
    }
}
