package dev.mccue.tools.javac;

import dev.mccue.tools.AbstractToolOperation;
import dev.mccue.tools.ExitStatusException;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public final class Javac extends AbstractToolOperation<Javac, JavacOptions> {
    public Javac(ToolProvider toolProvider, JavacOptions options) {
        super(toolProvider, options);
    }

    public Javac(JavacOptions options) {
        super(
                ToolProvider.findFirst("javac").orElseThrow(),
                options
        );
    }

    public Javac() {
        this(new JavacOptions());
    }

    public Javac(Consumer<? super JavacOptions> consumer) {
        this(new JavacOptions());
        consumer.accept(this.options);
    }

    public static void execute(ToolProvider toolProvider, JavacOptions options) throws ExitStatusException {
        new Javac(toolProvider, options).execute();
    }

    public static void execute(JavacOptions options) throws ExitStatusException {
        new Javac(options).execute();
    }

    public static void execute(Consumer<? super JavacOptions> consumer) throws ExitStatusException {
        new Javac(consumer).execute();
    }
}
