package dev.mccue.tools.java;

import dev.mccue.tools.AbstractToolOperation;
import dev.mccue.tools.Tool;

import java.util.List;
import java.util.function.Consumer;

public final class Java
        extends AbstractToolOperation<Java, JavaOptions> {
    public Java(JavaOptions options) {
        super(Tool.ofSubprocess("java"), options);
    }

    public Java(Consumer<? super JavaOptions> consumer) {
        this(new JavaOptions());
        consumer.accept(this.options);
    }
}
