package dev.mccue.tools;

import java.io.OutputStream;
import java.util.function.Consumer;

sealed abstract class AbstractTool
        implements Tool
        permits SubprocessTool, ToolProviderTool {
    abstract void run(
            String[] arguments,
            OutputStream redirectOutput,
            OutputStream redirectError,
            Consumer<? super String> echoCommand
    ) throws ExitStatusException;
}
