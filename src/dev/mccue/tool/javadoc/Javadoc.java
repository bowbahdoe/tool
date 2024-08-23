package dev.mccue.tool.javadoc;

import dev.mccue.tool.AbstractToolOperation;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public class Javadoc
        extends AbstractToolOperation<Javadoc, JavadocOptions> {
    public Javadoc(ToolProvider toolProvider, JavadocOptions options) {
        super(toolProvider, options);
    }

    public Javadoc(JavadocOptions options) {
        super(
                ToolProvider.findFirst("javac").orElseThrow(),
                options
        );
    }

    public Javadoc(Consumer<? super JavadocOptions> consumer) {
        this(new JavadocOptions());
        consumer.accept(this.options);
    }

    public static void execute(ToolProvider toolProvider, JavadocOptions options) throws Exception {
        new Javadoc(toolProvider, options).execute();
    }

    public static void execute(JavadocOptions options) throws Exception {
        new Javadoc(options).execute();
    }

    public static void execute(Consumer<? super JavadocOptions> consumer) throws Exception {
        new Javadoc(consumer).execute();
    }
}
