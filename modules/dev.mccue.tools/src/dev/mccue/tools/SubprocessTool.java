package dev.mccue.tools;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

final class SubprocessTool extends AbstractTool {
    private final List<String> commandPrefix;
    private final File directory;

    SubprocessTool(List<String> commandPrefix, File directory) {
        this.commandPrefix = commandPrefix;
        this.directory = directory;
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
            sb.append(String.join(" ", commandPrefix));
            if (!(arguments.length == 0)) {
                sb.append(" ");
                sb.append(String.join(" ", arguments));
            }
            echoCommand.accept(sb.toString());
        }

        var allArgs = new ArrayList<>(commandPrefix);
        allArgs.addAll(Arrays.asList(arguments));
        try {
            var pb = new ProcessBuilder(allArgs);
            pb.redirectOutput(ProcessBuilder.Redirect.PIPE);
            pb.redirectError(ProcessBuilder.Redirect.PIPE);

            if (directory != null) {
                pb.directory(directory);
            }

            var process = pb.start();
            var t1 = Thread.startVirtualThread(() -> {
                try {
                    process.getInputStream()
                            .transferTo(redirectOutput == null ? System.out : redirectOutput);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
            var t2 = Thread.startVirtualThread(() -> {
                try {
                    process.getErrorStream()
                            .transferTo(redirectError == null ? System.err : redirectError);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
            int exit = process.waitFor();
            ExitStatusException.throwOnFailure(exit);
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
