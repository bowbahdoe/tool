package dev.mccue.tools;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

record SubprocessTool(List<String> commandPrefix) implements Tool {
    @Override
    public void run(String[] args) throws ExitStatusException {
        var allArgs = new ArrayList<>(commandPrefix);
        allArgs.addAll(Arrays.asList(args));
        try {
            int exit = new ProcessBuilder(allArgs)
                    .inheritIO()
                    .start()
                    .waitFor();
            ExitStatusException.throwOnFailure(exit);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public void log(Consumer<? super String> logger, String[] args) {
        var sb = new StringBuilder();
        sb.append(String.join(" ", commandPrefix));
        if (!(args.length == 0)) {
            sb.append(" ");
            sb.append(String.join(" ", args));
        }
        logger.accept(sb.toString());
    }
}
