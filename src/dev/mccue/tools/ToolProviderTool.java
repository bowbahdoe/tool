package dev.mccue.tools;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;
import java.util.spi.ToolProvider;

final class ToolProviderTool implements Tool {
    private final ToolProvider toolProvider;
    private OutputStream redirectOutput;
    private OutputStream redirectError;


    ToolProviderTool(ToolProvider toolProvider) {
        this.toolProvider = toolProvider;
        this.redirectOutput = null;
        this.redirectError = null;
    }

    @Override
    public void run(String[] args) throws ExitStatusException {
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
    public void log(Consumer<? super String> logger, String[] args) {
        var sb = new StringBuilder();
        sb.append(toolProvider.name());
        if (!(args.length == 0)) {
            sb.append(" ");
            sb.append(String.join(" ", args));
        }
        logger.accept(sb.toString());
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
}
