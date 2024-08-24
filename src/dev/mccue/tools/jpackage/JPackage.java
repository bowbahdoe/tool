package dev.mccue.tools.jpackage;

import dev.mccue.tools.AbstractToolOperation;
import dev.mccue.tools.Tool;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public final class JPackage
        extends AbstractToolOperation<JPackage, JPackageArguments> {
    public JPackage(ToolProvider toolProvider, JPackageArguments options) {
        super(Tool.ofToolProvider(toolProvider), options);
    }

    public JPackage(JPackageArguments options) {
        super(
                Tool.ofToolProvider(ToolProvider.findFirst("jpackage").orElseThrow()),
                options
        );
    }

    public JPackage(Consumer<? super JPackageArguments> consumer) {
        this(new JPackageArguments());
        consumer.accept(this.arguments);
    }

    public static void execute(ToolProvider toolProvider, JPackageArguments options) throws Exception {
        new JPackage(toolProvider, options).execute();
    }

    public static void execute(JPackageArguments options) throws Exception {
        new JPackage(options).execute();
    }

    public static void execute(Consumer<? super JPackageArguments> consumer) throws Exception {
        new JPackage(consumer).execute();
    }
}
