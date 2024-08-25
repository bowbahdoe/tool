package dev.mccue.tools;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.function.Consumer;
import java.util.spi.ToolProvider;

final class ToolProviderTool implements Tool {
    private final ToolProvider toolProvider;
    private OutputStream redirectOutput;
    private OutputStream redirectError;
    private Consumer<? super String> echoCommand;


    ToolProviderTool(ToolProvider toolProvider) {
        this.toolProvider = toolProvider;
        this.redirectOutput = null;
        this.redirectError = null;
        this.echoCommand = System.err::println;
    }

    @Override
    public void run(String[] args) throws ExitStatusException {
        if (echoCommand != null) {
            var sb = new StringBuilder();
            sb.append(toolProvider.name());
            if (!(args.length == 0)) {
                sb.append(" ");
                sb.append(String.join(" ", args));
            }
            echoCommand.accept(sb.toString());
        }
        ExitStatusException.throwOnFailure(
                toolProvider.run(
                        redirectOutput == null
                                ? System.out
                                : new PrintStream(redirectOutput),
                        redirectError == null
                                ? System.err
                                : new PrintStream(redirectError),
                        args
                )
        );
    }

    @Override
    public void run(List<String> args) throws ExitStatusException {
        run(args.toArray(String[]::new));
    }

    @Override
    public Tool redirectOutput(OutputStream outputStream) {
        this.redirectOutput = outputStream;
        return this;
    }

    @Override
    public Tool redirectError(OutputStream outputStream) {
        this.redirectError = outputStream;
        return this;
    }

    @Override
    public Tool echoCommand(boolean echoCommand) {
        if (echoCommand) {
            if (this.echoCommand == null) {
                this.echoCommand = System.err::println;
            }
        }
        else {
            this.echoCommand = null;
        }

        return this;
    }

    @Override
    public Tool echoCommand(Consumer<? super String> consumer) {
        this.echoCommand = consumer;
        return this;
    }
}
