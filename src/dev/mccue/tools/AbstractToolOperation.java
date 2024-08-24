package dev.mccue.tools;

import java.util.function.Consumer;

public abstract class AbstractToolOperation<
        Self extends AbstractToolOperation<Self, Arguments>,
        Arguments extends ToolArguments
        >
        extends AbstractOperation<Self> {
    protected final Arguments arguments;
    protected final Tool tool;
    private Consumer<String> logger
            = System.err::println;

    protected AbstractToolOperation(
            Tool tool,
            Arguments arguments
    ) {
        this.tool = tool;
        this.arguments = arguments;
    }

    @Override
    public void execute() throws ExitStatusException {
        var args = arguments.toArray(String[]::new);
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
