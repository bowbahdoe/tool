package dev.mccue.tools;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

final class SubprocessTool implements Tool {
    private final List<String> commandPrefix;
    private OutputStream redirectOutput;
    private OutputStream redirectError;
    private Consumer<? super String> echoCommand;

    SubprocessTool(List<String> commandPrefix) {
        this.commandPrefix = commandPrefix;
        this.redirectOutput = null;
        this.redirectError = null;
        this.echoCommand = System.err::println;
    }

    @Override
    public void run(String[] args) throws ExitStatusException {
        if (echoCommand != null) {
            var sb = new StringBuilder();
            sb.append(String.join(" ", commandPrefix));
            if (!(args.length == 0)) {
                sb.append(" ");
                sb.append(String.join(" ", args));
            }
            echoCommand.accept(sb.toString());
        }

        var allArgs = new ArrayList<>(commandPrefix);
        allArgs.addAll(Arrays.asList(args));
        try {
            var pb = new ProcessBuilder(allArgs);
            pb.redirectOutput(ProcessBuilder.Redirect.PIPE);
            pb.redirectError(ProcessBuilder.Redirect.PIPE);

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
