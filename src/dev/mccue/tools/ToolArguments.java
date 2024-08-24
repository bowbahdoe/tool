package dev.mccue.tools;

import java.util.ArrayList;
import java.util.Collection;

public abstract class ToolArguments extends ArrayList<String> {
    public ToolArguments() {
        super();
    }

    public ToolArguments(Collection<? extends String> c) {
        super(c);
    }
}
