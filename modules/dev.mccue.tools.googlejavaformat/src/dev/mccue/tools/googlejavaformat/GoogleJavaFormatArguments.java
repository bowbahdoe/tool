package dev.mccue.tools.googlejavaformat;

import dev.mccue.tools.ToolArguments;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class GoogleJavaFormatArguments extends ToolArguments {
    static String toArgumentString(Object o) {
        return o == null ? "" : o.toString();
    }

    public GoogleJavaFormatArguments() {
        super();
    }

    public GoogleJavaFormatArguments(Collection<? extends String> c) {
        super(c);
    }

    // java -jar format.jar --help
    //
    //Usage: google-java-format [options] file(s)
    public GoogleJavaFormatArguments files(Object... files) {
        return files(Arrays.asList(files));
    }

    public GoogleJavaFormatArguments files(List<?> files) {
        files.forEach(file -> add(toArgumentString(file)));
        return this;
    }

    //
    //Options:
    //  -i, -r, -replace, --replace
    //    Send formatted output back to files, not stdout.
    public GoogleJavaFormatArguments _i() {
        add("-i");
        return this;
    }

    public GoogleJavaFormatArguments _r() {
        add("-r");
        return this;
    }

    public GoogleJavaFormatArguments _replace() {
        add("-replace");
        return this;
    }

    public GoogleJavaFormatArguments __replace() {
        add("--replace");
        return this;
    }

    //  -
    //    Format stdin -> stdout
    //  --assume-filename, -assume-filename
    //    File name to use for diagnostics when formatting standard input (default is <stdin>).
    public GoogleJavaFormatArguments __assume_filename(Object filename) {
        add("--assume-filename");
        add(toArgumentString(filename));
        return this;
    }

    public GoogleJavaFormatArguments _assume_filename(Object filename) {
        add("-assume-filename");
        add(toArgumentString(filename));
        return this;
    }

    //  --aosp, -aosp, -a
    //    Use AOSP style instead of Google Style (4-space indentation).
    public GoogleJavaFormatArguments __aosp() {
        add("--aosp");
        return this;
    }

    public GoogleJavaFormatArguments _aosp() {
        add("-aosp");
        return this;
    }

    public GoogleJavaFormatArguments _a() {
        add("-a");
        return this;
    }

    //  --fix-imports-only
    //    Fix import order and remove any unused imports, but do no other formatting.
    public GoogleJavaFormatArguments __fix_imports_only() {
        add("--fix-imports-only");
        return this;
    }

    //  --skip-sorting-imports
    //    Do not fix the import order. Unused imports will still be removed.
    public GoogleJavaFormatArguments __skip_sorting_imports() {
        add("--skip-sorting-imports");
        return this;
    }

    //  --skip-removing-unused-imports
    //    Do not remove unused imports. Imports will still be sorted.
    public GoogleJavaFormatArguments __skip_removing_unused_imports() {
        add("--skip-removing-unused-imports");
        return this;
    }

    //  --skip-reflowing-long-strings
    //    Do not reflow string literals that exceed the column limit.
    public GoogleJavaFormatArguments __skip_reflowing_long_strings() {
        add("--skip-reflowing-long-strings");
        return this;
    }
    //  --skip-javadoc-formatting
    //    Do not reformat javadoc.
    public GoogleJavaFormatArguments __skip_javadoc_formatting() {
        add("--skip-javadoc-formatting");
        return this;
    }

    //  --dry-run, -n
    //    Prints the paths of the files whose contents would change if the formatter were run normally.
    public GoogleJavaFormatArguments __dry_run() {
        add("--dry-run");
        return this;
    }

    public GoogleJavaFormatArguments _n() {
        add("-n");
        return this;
    }
    //  --set-exit-if-changed
    //    Return exit code 1 if there are any formatting changes.
    public GoogleJavaFormatArguments __set_exit_if_changed() {
        add("--set-exit-if-changed");
        return this;
    }
    //  --lines, -lines, --line, -line
    //    Line range(s) to format, e.g. the first 5 lines are 1:5 (1-based; default is all).
    public GoogleJavaFormatArguments __lines(Object lines) {
        add("--lines");
        add(toArgumentString(lines));
        return this;
    }

    public GoogleJavaFormatArguments _lines(Object lines) {
        add("-lines");
        add(toArgumentString(lines));
        return this;
    }

    public GoogleJavaFormatArguments __line(Object lines) {
        add("--line");
        add(toArgumentString(lines));
        return this;
    }

    public GoogleJavaFormatArguments _line(Object lines) {
        add("-line");
        add(toArgumentString(lines));
        return this;
    }
    //  --offset, -offset
    //    Character offset to format (0-based; default is all).
    public GoogleJavaFormatArguments __offset(Object offset) {
        add("--offset");
        add(toArgumentString(offset));
        return this;
    }

    public GoogleJavaFormatArguments _offset(Object offset) {
        add("-offset");
        add(toArgumentString(offset));
        return this;
    }
    //  --length, -length
    //    Character length to format.
    public GoogleJavaFormatArguments __length(Object length) {
        add("--length");
        add(toArgumentString(length));
        return this;
    }

    public GoogleJavaFormatArguments _length(Object length) {
        add("-length");
        add(toArgumentString(length));
        return this;
    }

    //  --help, -help, -h
    //    Print this usage statement.
    public GoogleJavaFormatArguments __help() {
        add("--help");
        return this;
    }

    public GoogleJavaFormatArguments _help() {
        add("-help");
        return this;
    }

    public GoogleJavaFormatArguments _h() {
        add("-h");
        return this;
    }
    //  --version, -version, -v
    //    Print the version.
    public GoogleJavaFormatArguments __version() {
        add("--version");
        return this;
    }

    public GoogleJavaFormatArguments _version() {
        add("-version");
        return this;
    }

    public GoogleJavaFormatArguments _v() {
        add("-v");
        return this;
    }

    //  @<filename>
    //    Read options and filenames from file.
    public GoogleJavaFormatArguments argumentFile(Object filename) {
        add("@" + toArgumentString(filename));
        return this;
    }
    //
    //If -i is given with -, the result is sent to stdout.
    //The --lines, --offset, and --length flags may be given more than once.
    //The --offset and --length flags must be given an equal number of times.
    //If --lines, --offset, or --length are given, only one file (or -) may be given.
}
