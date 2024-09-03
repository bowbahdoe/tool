package dev.mccue.tools;

import java.util.Arrays;
import java.util.List;
import java.util.spi.ToolProvider;

public sealed interface Tool
    permits AbstractTool {
    void run(List<String> args) throws ExitStatusException;

    default void run(String... args) throws ExitStatusException {
        run(Arrays.asList(args));
    }

    static Tool ofToolProvider(ToolProvider toolProvider) {
        return new ToolProviderTool(toolProvider);
    }

    static Tool ofToolProvider(String toolProviderName) {
        return new ToolProviderTool(ToolProvider.findFirst(toolProviderName).orElseThrow());
    }

    static Tool ofSubprocess(List<String> commandPrefix) {
        return new SubprocessTool(commandPrefix);
    }

    static Tool ofSubprocess(String commandPrefix) {
        return new SubprocessTool(List.of(commandPrefix));
    }

    default ToolRunner runner() {
        return ToolRunner.of(this);
    }
}
