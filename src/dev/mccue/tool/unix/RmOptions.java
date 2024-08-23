package dev.mccue.tool.unix;

import dev.mccue.tool.ToolOptions;

import java.util.Arrays;
import java.util.List;

public class RmOptions extends ToolOptions {
    static String toArgumentString(Object o) {
        return o == null ? "" : o.toString();
    }

    public RmOptions f() {
        add("-f");
        return this;
    }

    public RmOptions i() {
        add("-i");
        return this;
    }

    public RmOptions d() {
        add("-d");
        return this;
    }

    public RmOptions I() {
        add("-I");
        return this;
    }

    public RmOptions R() {
        add("-R");
        return this;
    }

    public RmOptions r() {
        add("-r");
        return this;
    }

    public RmOptions v() {
        add("-v");
        return this;
    }

    public RmOptions W() {
        add("-W");
        return this;
    }

    public RmOptions x() {
        add("-x");
        return this;
    }

    public RmOptions files(Object... files) {
        return files(Arrays.asList(files));
    }

    public RmOptions files(List<?> files) {
        files.forEach(file -> add(toArgumentString(file)));
        return this;
    }
}
