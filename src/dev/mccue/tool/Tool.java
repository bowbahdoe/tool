package dev.mccue.tool;

import java.io.PrintStream;
import java.util.List;
import java.util.spi.ToolProvider;

public interface Tool {
    void run(String[] args) throws ExitStatusException;

    void log(PrintStream out, String[] args);

    static Tool ofToolProvider(ToolProvider toolProvider) {
        return new ToolProviderTool(toolProvider);
    }

    static Tool ofSubprocess(List<String> commandPrefix) {
        return new SubprocessTool(commandPrefix);
    }
}
