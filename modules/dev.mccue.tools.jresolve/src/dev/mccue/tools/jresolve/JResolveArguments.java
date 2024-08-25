package dev.mccue.tools.jresolve;
import dev.mccue.tools.ToolArguments;

import java.util.Arrays;
import java.util.List;

public final class JResolveArguments extends ToolArguments {
    static String toArgumentString(Object o) {
        return o == null ? "" : o.toString();
    }

    public JResolveArguments dependencies(Object... dependencies) {
        return dependencies(Arrays.asList(dependencies));
    }

    public JResolveArguments dependencies(List<?> dependencies) {
        dependencies.forEach(dependency -> add(toArgumentString(dependency)));
        return this;
    }

    public JResolveArguments __output_directory(Object path) {
        add("--output-directory");
        add(toArgumentString(path));
        return this;
    }

    public JResolveArguments __output_file(Object path) {
        add("--output-file");
        add(toArgumentString(path));
        return this;
    }

    public JResolveArguments __cache_path(Object path) {
        add("--cache-path");
        add(toArgumentString(path));
        return this;
    }

    public JResolveArguments __enrich_pom(Object path) {
        add("--enrich-pom");
        add(toArgumentString(path));
        return this;
    }

    public JResolveArguments __maven_repositories_file(Object path) {
        add("--maven-repositories-file");
        add(toArgumentString(path));
        return this;
    }

    public JResolveArguments __purge_output_directory() {
        add("--purge-output-directory");
        return this;
    }

    public JResolveArguments __use_module_names() {
        add("--use-module-names");
        return this;
    }

    public JResolveArguments __print_tree() {
        add("--print-tree");
        return this;
    }

    public JResolveArguments _V() {
        add("-V");
        return this;
    }

    public JResolveArguments __version() {
        add("--version");
        return this;
    }

    public JResolveArguments _h() {
        add("-h");
        return this;
    }

    public JResolveArguments __help() {
        add("--help");
        return this;
    }
}
