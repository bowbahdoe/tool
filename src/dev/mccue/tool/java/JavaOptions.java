package dev.mccue.tool.java;

import dev.mccue.tool.ToolOptions;

public class JavaOptions extends ToolOptions {
    static String toArgumentString(Object o) {
        return o == null ? "" : o.toString();
    }
}
