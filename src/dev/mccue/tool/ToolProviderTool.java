package dev.mccue.tool;

import java.util.function.Consumer;
import java.util.spi.ToolProvider;

record ToolProviderTool(ToolProvider toolProvider) implements Tool {

    @Override
    public void run(String[] args) throws ExitStatusException {
        ExitStatusException.throwOnFailure(
                toolProvider.run(System.out, System.err, args)
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
}
