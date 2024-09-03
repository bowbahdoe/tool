package dev.mccue.tools;

import java.io.OutputStream;
import java.util.List;
import java.util.function.Consumer;

public sealed interface ToolRunner permits AbstractToolRunner {
    void run() throws ExitStatusException;

    void run(List<String> extraArguments) throws ExitStatusException;

    void run(String... extraArguments) throws ExitStatusException;

    ToolRunner echoCommand(boolean echo);

    ToolRunner echoCommand(Consumer<? super String> consumer);

    ToolRunner redirectOutput(OutputStream outputStream);

    ToolRunner redirectError(OutputStream outputStream);

    ToolArguments arguments();

    static ToolRunner of(Tool tool) {
        return new BasicRunner(switch (tool) { case AbstractTool abstractTool -> abstractTool; }, new BasicToolArguments());
    }
}
