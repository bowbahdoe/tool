package dev.mccue.tool.unix;

import dev.mccue.tool.AbstractToolOperation;

import java.util.List;
import java.util.function.Consumer;

public class Rm extends AbstractToolOperation<Rm, RmOptions> {
    public Rm(RmOptions options) {
        super(List.of("rm"), options);
    }

    public Rm(Consumer<? super RmOptions> consumer) {
        this(new RmOptions());
        consumer.accept(this.options);
    }

    public static void execute(RmOptions options) throws Exception {
        new Rm(options).execute();
    }

    public static void execute(Consumer<? super RmOptions> consumer) throws Exception {
        new Rm(consumer).execute();
    }
}
