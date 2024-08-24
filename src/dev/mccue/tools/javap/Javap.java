package dev.mccue.tools.javap;

import dev.mccue.tools.AbstractToolOperation;
import dev.mccue.tools.Tool;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public final class Javap extends AbstractToolOperation<Javap, JavapOptions> {
    public Javap(ToolProvider toolProvider, JavapOptions options) {
        super(Tool.ofToolProvider(toolProvider), options);
    }

    public Javap(JavapOptions options) {
        super(
                Tool.ofToolProvider(ToolProvider.findFirst("javac").orElseThrow()),
                options
        );
    }

    public Javap(Consumer<? super JavapOptions> consumer) {
        this(new JavapOptions());
        consumer.accept(this.options);
    }

    public static void execute(ToolProvider toolProvider, JavapOptions options) throws Exception {
        new Javap(toolProvider, options).execute();
    }

    public static void execute(JavapOptions options) throws Exception {
        new Javap(options).execute();
    }

    public static void execute(Consumer<? super JavapOptions> consumer) throws Exception {
        new Javap(consumer).execute();
    }
}
