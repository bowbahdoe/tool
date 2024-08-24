package dev.mccue.tools.javac;

import dev.mccue.tools.AbstractToolOperation;
import dev.mccue.tools.ExitStatusException;
import dev.mccue.tools.Tool;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public final class Javac extends AbstractToolOperation<Javac, JavacArguments> {
    public Javac(ToolProvider toolProvider, JavacArguments arguments) {
        super(Tool.ofToolProvider(toolProvider), arguments);
    }

    public Javac(JavacArguments arguments) {
        super(
                Tool.ofToolProvider(ToolProvider.findFirst("javac").orElseThrow()),
                arguments
        );
    }

    public Javac() {
        this(new JavacArguments());
    }

    public Javac(Consumer<? super JavacArguments> consumer) {
        this(new JavacArguments());
        consumer.accept(this.arguments);
    }

    public static void execute(ToolProvider toolProvider, JavacArguments arguments) throws ExitStatusException {
        new Javac(toolProvider, arguments).execute();
    }

    public static void execute(JavacArguments arguments) throws ExitStatusException {
        new Javac(arguments).execute();
    }

    public static void execute(Consumer<? super JavacArguments> consumer) throws ExitStatusException {
        new Javac(consumer).execute();
    }
}
