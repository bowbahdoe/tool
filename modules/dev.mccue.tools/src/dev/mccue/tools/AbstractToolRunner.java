package dev.mccue.tools;

import java.io.OutputStream;
import java.util.function.Consumer;

public abstract class AbstractToolRunner<
        Self extends AbstractToolRunner<Self, Arguments>,
        Arguments extends ToolArguments
        > implements ToolRunner {
    protected final Arguments arguments;
    protected final Tool tool;

    protected AbstractToolRunner(
            Tool tool,
            Arguments arguments
    ) {
        this.tool = tool;
        this.arguments = arguments;
    }

    @Override
    public void run() throws ExitStatusException {
        var args = arguments.toArray(String[]::new);
        tool.run(args);
    }

    @SuppressWarnings("unchecked")
    public Self echoCommand(boolean echo) {
        this.tool.echoCommand(echo);
        return (Self) this;
    }

    @SuppressWarnings("unchecked")
    public Self echoCommand(Consumer<String> consumer) {
        this.tool.echoCommand(consumer);
        return (Self) this;
    }

    @SuppressWarnings("unchecked")
    public Self redirectOutput(OutputStream outputStream) {
        this.tool.redirectOutput(outputStream);
        return (Self) this;
    }

    @SuppressWarnings("unchecked")
    public Self redirectError(OutputStream outputStream) {
        this.tool.redirectError(outputStream);
        return (Self) this;
    }

    public Arguments arguments() {
        return arguments;
    }
}
