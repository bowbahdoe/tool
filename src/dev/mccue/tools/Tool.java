package dev.mccue.tools;

import java.io.OutputStream;
import java.util.List;
import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public sealed interface Tool
    permits SubprocessTool, ToolProviderTool {
    void run(String... args) throws ExitStatusException;

    void log(Consumer<? super String> out, String... args);

    default void logAndRun(Consumer<? super String> out, String... args) {
        log(out, args);
    }

    default void logAndRun(String... args) throws ExitStatusException {
        log(System.err::println, args);
        run(args);
    }

    default void run(List<String> args) throws ExitStatusException {
        run(args.toArray(String[]::new));
    }

    default void log(Consumer<? super String> out, List<String> args) {
        log(out, args.toArray(String[]::new));
    }

    default void logAndRun(Consumer<? super String> out, List<String> args) throws ExitStatusException {
        log(out, args);
        run(args);
    }

    default void logAndRun(List<String> args) throws ExitStatusException {
        log(System.err::println, args);
        run(args);
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

    Tool redirectOutput(OutputStream outputStream);

    Tool redirectError(OutputStream outputStream);
}
