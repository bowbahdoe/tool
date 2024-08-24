package dev.mccue.tools.java;

import dev.mccue.tools.AbstractToolOperation;
import dev.mccue.tools.ExitStatusException;
import dev.mccue.tools.Tool;

import java.util.function.Consumer;

public final class Java
        extends AbstractToolOperation<Java, JavaArguments> {
    public Java(JavaArguments options) {
        super(Tool.ofSubprocess("java"), options);
    }

    public Java(Consumer<? super JavaArguments> consumer) {
        this(new JavaArguments());
        consumer.accept(this.arguments);
    }


    public Java() {
        this(new JavaArguments());
    }

    public static void execute(JavaArguments options) throws ExitStatusException {
        new Java(options).execute();
    }

    public static void execute(Consumer<? super JavaArguments> consumer) throws ExitStatusException {
        new Java(consumer).execute();
    }
}
