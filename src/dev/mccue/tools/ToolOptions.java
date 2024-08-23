package dev.mccue.tools;

import java.util.ArrayList;
import java.util.Collection;

public abstract class ToolOptions extends ArrayList<String> {
    public ToolOptions() {
        super();
    }

    public ToolOptions(Collection<? extends String> c) {
        super(c);
    }
}
