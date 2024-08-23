package dev.mccue.tools.java;

import dev.mccue.tools.AbstractToolOperation;

import java.util.List;
import java.util.function.Consumer;

public class Java
        extends AbstractToolOperation<Java, JavaOptions> {
    public Java(JavaOptions options) {
        super(List.of("java"), options);
    }

    public Java(Consumer<? super JavaOptions> consumer) {
        this(new JavaOptions());
        consumer.accept(this.options);
    }
}
