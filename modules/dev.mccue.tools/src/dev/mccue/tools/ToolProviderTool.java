package dev.mccue.tools;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.function.Consumer;
import java.util.spi.ToolProvider;

final class ToolProviderTool extends AbstractTool {
    private final ToolProvider toolProvider;

    ToolProviderTool(ToolProvider toolProvider) {
        this.toolProvider = toolProvider;
    }

    @Override
    public void run(String[] args) throws ExitStatusException {
        run(args, null, null, System.err::println);
    }

    @Override
    public void run(List<String> args) throws ExitStatusException {
        run(args.toArray(String[]::new));
    }


    @Override
    void run(
            String[] arguments,
            OutputStream redirectOutput,
            OutputStream redirectError,
            Consumer<? super String> echoCommand
    ) throws ExitStatusException {
        if (echoCommand != null) {
            var sb = new StringBuilder();
            sb.append(toolProvider.name());
            if (!(arguments.length == 0)) {
                sb.append(" ");
                sb.append(String.join(" ", arguments));
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
                        arguments
                )
        );
    }
}
