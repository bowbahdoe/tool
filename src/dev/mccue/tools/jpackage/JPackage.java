package dev.mccue.tools.jpackage;

import dev.mccue.tools.AbstractToolOperation;
import dev.mccue.tools.Tool;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public final class JPackage
        extends AbstractToolOperation<JPackage, JPackageOptions> {
    public JPackage(ToolProvider toolProvider, JPackageOptions options) {
        super(Tool.ofToolProvider(toolProvider), options);
    }

    public JPackage(JPackageOptions options) {
        super(
                Tool.ofToolProvider(ToolProvider.findFirst("jpackage").orElseThrow()),
                options
        );
    }

    public JPackage(Consumer<? super JPackageOptions> consumer) {
        this(new JPackageOptions());
        consumer.accept(this.options);
    }

    public static void execute(ToolProvider toolProvider, JPackageOptions options) throws Exception {
        new JPackage(toolProvider, options).execute();
    }

    public static void execute(JPackageOptions options) throws Exception {
        new JPackage(options).execute();
    }

    public static void execute(Consumer<? super JPackageOptions> consumer) throws Exception {
        new JPackage(consumer).execute();
    }
}
