package dev.mccue.tools.java;

import dev.mccue.tools.AbstractToolRunner;
import dev.mccue.tools.ExitStatusException;
import dev.mccue.tools.Tool;

import java.util.function.Consumer;

public final class Java
        extends AbstractToolRunner<Java, JavaArguments> {
    public Java(JavaArguments arguments) {
        super(Tool.ofSubprocess("java"),  arguments);
    }

    public Java(Consumer<? super JavaArguments> consumer) {
        this(new JavaArguments());
        consumer.accept(this.arguments);
    }


    public Java() {
        this(new JavaArguments());
    }

    public static void run(JavaArguments arguments) throws ExitStatusException {
        new Java( arguments).run();
    }

    public static void run(Consumer<? super JavaArguments> consumer) throws ExitStatusException {
        new Java(consumer).run();
    }
}
