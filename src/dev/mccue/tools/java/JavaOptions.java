package dev.mccue.tools.java;

import dev.mccue.tools.ToolOptions;

public class JavaOptions extends ToolOptions {
    static String toArgumentString(Object o) {
        return o == null ? "" : o.toString();
    }
}
