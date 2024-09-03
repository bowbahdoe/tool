package dev.mccue.tools;

import java.io.File;
import java.nio.file.Path;
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
        return ofSubprocess(commandPrefix, (File) null);
    }

    static Tool ofSubprocess(String commandPrefix) {
        return ofSubprocess(commandPrefix, (File) null);
    }

    static Tool ofSubprocess(List<String> commandPrefix, Path directory) {
        return ofSubprocess(commandPrefix, directory.toFile());
    }

    static Tool ofSubprocess(String commandPrefix, Path directory) {
        return ofSubprocess(commandPrefix, directory.toFile());
    }

    static Tool ofSubprocess(List<String> commandPrefix, File directory) {
        return new SubprocessTool(commandPrefix, directory);
    }

    static Tool ofSubprocess(String commandPrefix, File directory) {
        return new SubprocessTool(List.of(commandPrefix), directory);
    }

    default ToolRunner runner() {
        return ToolRunner.of(this);
    }
}
