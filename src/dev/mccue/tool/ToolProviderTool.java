package dev.mccue.tool;

import java.io.PrintStream;
import java.util.spi.ToolProvider;

record ToolProviderTool(ToolProvider toolProvider) implements Tool {

    @Override
    public void run(String[] args) throws ExitStatusException {
        ExitStatusException.throwOnFailure(
                toolProvider.run(System.out, System.err, args)
        );
    }

    @Override
    public void log(PrintStream out, String[] args) {
        out.print(toolProvider.name());
        if (!(args.length == 0)) {
            out.print(" ");
            out.print(String.join(" ", args));
        }
        out.println();
    }
}
