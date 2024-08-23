package dev.mccue.tool;

import java.io.IOException;
import java.io.PrintStream;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public void log(PrintStream out, String[] args) {
        out.print(String.join(" ", commandPrefix));
        if (!(args.length == 0)) {
            out.print(" ");
            out.print(String.join(" ", args));
        }
        out.println();
    }
}
