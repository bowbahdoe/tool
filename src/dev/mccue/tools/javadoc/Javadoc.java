package dev.mccue.tools.javadoc;

import dev.mccue.tools.AbstractToolOperation;
import dev.mccue.tools.Tool;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public final class Javadoc
        extends AbstractToolOperation<Javadoc, JavadocArguments> {
    public Javadoc(ToolProvider toolProvider, JavadocArguments arguments) {
        super(Tool.ofToolProvider(toolProvider), arguments);
    }

    public Javadoc(JavadocArguments arguments) {
        super(
                Tool.ofToolProvider(ToolProvider.findFirst("javac").orElseThrow()),
                arguments
        );
    }

    public Javadoc(Consumer<? super JavadocArguments> consumer) {
        this(new JavadocArguments());
        consumer.accept(this.arguments);
    }

    public static void execute(ToolProvider toolProvider, JavadocArguments arguments) throws Exception {
        new Javadoc(toolProvider, arguments).execute();
    }

    public static void execute(JavadocArguments arguments) throws Exception {
        new Javadoc(arguments).execute();
    }

    public static void execute(Consumer<? super JavadocArguments> consumer) throws Exception {
        new Javadoc(consumer).execute();
    }
}
