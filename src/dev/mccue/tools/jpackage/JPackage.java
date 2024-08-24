package dev.mccue.tools.jpackage;

import dev.mccue.tools.AbstractToolOperation;
import dev.mccue.tools.Tool;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public final class JPackage
        extends AbstractToolOperation<JPackage, JPackageArguments> {
    public JPackage(ToolProvider toolProvider, JPackageArguments arguments) {
        super(Tool.ofToolProvider(toolProvider),  arguments);
    }

    public JPackage(JPackageArguments arguments) {
        super(
                Tool.ofToolProvider(ToolProvider.findFirst("jpackage").orElseThrow()),
                 arguments
        );
    }

    public JPackage(Consumer<? super JPackageArguments> consumer) {
        this(new JPackageArguments());
        consumer.accept(this.arguments);
    }

    public static void execute(ToolProvider toolProvider, JPackageArguments arguments) throws Exception {
        new JPackage(toolProvider,  arguments).execute();
    }

    public static void execute(JPackageArguments arguments) throws Exception {
        new JPackage( arguments).execute();
    }

    public static void execute(Consumer<? super JPackageArguments> consumer) throws Exception {
        new JPackage(consumer).execute();
    }
}
