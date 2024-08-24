package dev.mccue.tools;

import java.util.List;
import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public interface Tool {
    void run(String... args) throws ExitStatusException;

    void log(Consumer<? super String> out, String... args);

    default void logAndRun(Consumer<? super String> out, String... args) {
        log(out, args);
    }

    default void logAndRun(String... args) {
        log(System.err::println, args);
    }

    default void run(List<String> args) throws ExitStatusException {
        run(args.toArray(String[]::new));
    }

    default void log(Consumer<? super String> out, List<String> args) {
        log(out, args.toArray(String[]::new));
    }

    default void logAndRun(Consumer<? super String> out, List<String> args) {
        log(out, args);
    }

    default void logAndRun(List<String> args) {
        log(System.err::println, args);
    }

    static Tool ofToolProvider(ToolProvider toolProvider) {
        return new ToolProviderTool(toolProvider);
    }

    static Tool ofSubprocess(List<String> commandPrefix) {
        return new SubprocessTool(commandPrefix);
    }

    static Tool ofSubprocess(String commandPrefix) {
        return new SubprocessTool(List.of(commandPrefix));
    }
}
