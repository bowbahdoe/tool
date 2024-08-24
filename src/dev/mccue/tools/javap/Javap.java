package dev.mccue.tools.javap;

import dev.mccue.tools.AbstractToolOperation;
import dev.mccue.tools.Tool;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public final class Javap extends AbstractToolOperation<Javap, JavapArguments> {
    public Javap(ToolProvider toolProvider, JavapArguments options) {
        super(Tool.ofToolProvider(toolProvider), options);
    }

    public Javap(JavapArguments arguments) {
        super(
                Tool.ofToolProvider(ToolProvider.findFirst("javac").orElseThrow()),
                arguments
        );
    }

    public Javap(Consumer<? super JavapArguments> consumer) {
        this(new JavapArguments());
        consumer.accept(this.arguments);
    }

    public static void execute(ToolProvider toolProvider, JavapArguments arguments) throws Exception {
        new Javap(toolProvider, arguments).execute();
    }

    public static void execute(JavapArguments arguments) throws Exception {
        new Javap(arguments).execute();
    }

    public static void execute(Consumer<? super JavapArguments> consumer) throws Exception {
        new Javap(consumer).execute();
    }
}
