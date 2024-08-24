package dev.mccue.tools;

import java.util.List;
import java.util.function.Consumer;
import java.util.spi.ToolProvider;

public abstract class AbstractToolOperation<
        Self extends AbstractToolOperation<Self, Options>,
        Options extends ToolOptions
        >
        extends AbstractOperation<Self> {
    protected final Options options;
    protected final Tool tool;
    private Consumer<String> logger
            = System.err::println;

    protected AbstractToolOperation(
            Tool tool,
            Options options
    ) {
        this.tool = tool;
        this.options = options;
    }

    @Override
    public void execute() throws ExitStatusException {
        var args = options.toArray(String[]::new);
        if (!silent()) {
            tool.log(logger, args);
        }
        tool.run(args);
    }

    @Override
    public void executeOnce() throws ExitStatusException {
        executeOnce(null);
    }

    @Override
    public void executeOnce(Runnable setup)
            throws ExitStatusException {
        if (executed) {
            return;
        }
        executed = true;

        if (setup != null) {
            setup.run();
        }
        execute();
    }

    @SuppressWarnings("unchecked")
    public Self logger(Consumer<String> consumer) {
        this.logger = consumer;
        return (Self) this;
    }
}
