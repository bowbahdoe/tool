package dev.mccue.tools;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public non-sealed abstract class AbstractToolRunner<
        Self extends AbstractToolRunner<Self, Arguments>,
        Arguments extends ToolArguments
        > implements ToolRunner {
    protected final Arguments arguments;
    protected final Tool tool;
    private OutputStream redirectOutput;
    private OutputStream redirectError;
    private Consumer<? super String> echoCommand;

    protected AbstractToolRunner(
            Tool tool,
            Arguments arguments
    ) {
        this.tool = tool;
        this.arguments = arguments;
        this.redirectOutput = null;
        this.redirectError = null;
        this.echoCommand = System.err::println;
    }

    @Override
    public final void run() throws ExitStatusException {
        var args = arguments.toArray(String[]::new);
        (switch (tool) { case AbstractTool abstractTool -> abstractTool; })
                .run(args, redirectOutput, redirectError, echoCommand);
    }

    @Override
    public final void run(List<String> extraArguments) throws ExitStatusException {
        arguments.addAll(extraArguments);
        run();
    }

    @Override
    public final void run(String... extraArguments) throws ExitStatusException {
        arguments.addAll(Arrays.asList(extraArguments));
        run();
    }

    @Override
    @SuppressWarnings("unchecked")
    public final Self redirectOutput(OutputStream outputStream) {
        this.redirectOutput = outputStream;
        return (Self) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public final Self redirectError(OutputStream outputStream) {
        this.redirectError = outputStream;
        return (Self) this;
    }


    @Override
    @SuppressWarnings("unchecked")
    public final Self echoCommand(boolean echoCommand) {
        if (echoCommand) {
            if (this.echoCommand == null) {
                this.echoCommand = System.err::println;
            }
        }
        else {
            this.echoCommand = null;
        }

        return (Self) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public final Self echoCommand(Consumer<? super String> consumer) {
        this.echoCommand = consumer;
        return (Self) this;
    }

    public final Arguments arguments() {
        return arguments;
    }
}
