package dev.mccue.tools;

import java.util.List;
import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public interface Tool {
    void run(String[] args) throws ExitStatusException;

    void log(Consumer<? super String> out, String[] args);

    static Tool ofToolProvider(ToolProvider toolProvider) {
        return new ToolProviderTool(toolProvider);
    }

    static Tool ofSubprocess(List<String> commandPrefix) {
        return new SubprocessTool(commandPrefix);
    }
}
