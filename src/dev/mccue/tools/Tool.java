package dev.mccue.tools;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public sealed interface Tool
    permits SubprocessTool, ToolProviderTool {
    void run(List<String> args) throws ExitStatusException;

    default void run(String... args) throws ExitStatusException {
        run(Arrays.asList(args));
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

    Tool echoCommand(boolean echoCommand);

    Tool echoCommand(Consumer<? super String> consumer);
}
