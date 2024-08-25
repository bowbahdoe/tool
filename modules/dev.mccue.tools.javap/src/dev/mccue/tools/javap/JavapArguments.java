package dev.mccue.tools.javap;

import dev.mccue.tools.ToolArguments;
import dev.mccue.tools.java.JavaArguments;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public final class JavapArguments extends ToolArguments {
    static String toArgumentString(Object o) {
        return o == null ? "" : o.toString();
    }

    public JavapArguments() {
        super();
    }

    public JavapArguments(Collection<? extends String> c) {
        super(c);
    }

    // Usage: javap <options> <classes>
    //where possible options include:
    public JavapArguments classes(Object... classes) {
        return classes(Arrays.asList(classes));
    }

    public JavapArguments classes(List<?> classes) {
        classes.forEach(c -> add(toArgumentString(c)));
        return this;
    }

    //  --help -help -h -?               Print this help message
    public JavapArguments __help() {
        add("--help");
        return this;
    }

    public JavapArguments _help() {
        add("--help");
        return this;
    }

    public JavapArguments _h() {
        add("-h");
        return this;
    }

    //  -version                         Version information
    public JavapArguments _version() {
        add("-version");
        return this;
    }

    //  -v  -verbose                     Print additional information
    public JavapArguments _v() {
        add("-v");
        return this;
    }

    public JavapArguments _verbose() {
        add("-verbose");
        return this;
    }

    //  -l                               Print line number and local variable tables
    public JavapArguments _l() {
        add("-l");
        return this;
    }

    //  -public                          Show only public classes and members
    public JavapArguments _public() {
        add("-public");
        return this;
    }

    //  -protected                       Show protected/public classes and members
    public JavapArguments _protected() {
        add("-protected");
        return this;
    }

    //  -package                         Show package/protected/public classes
    //                                   and members (default)
    public JavapArguments _package() {
        add("-package");
        return this;
    }

    //  -p  -private                     Show all classes and members
    public JavapArguments _p() {
        add("-p");
        return this;
    }

    public JavapArguments _private() {
        add("-private");
        return this;
    }

    //  -c                               Disassemble the code
    public JavapArguments _c() {
        add("-c");
        return this;
    }

    //  -s                               Print internal type signatures
    public JavapArguments _s() {
        add("-s");
        return this;
    }

    //  -sysinfo                         Show system info (path, size, date, SHA-256 hash)
    //                                   of class being processed
    public JavapArguments _sysinfo() {
        add("-sysinfo");
        return this;
    }

    //  -constants                       Show final constants
    public JavapArguments _constants() {
        add("-constants");
        return this;
    }

    //  --module <module>  -m <module>   Specify module containing classes to be disassembled
    public JavapArguments __module(Object module) {
        add("--module");
        add(toArgumentString(module));
        return this;
    }

    public JavapArguments _m(Object module) {
        add("-m");
        add(toArgumentString(module));
        return this;
    }

    //  -J<vm-option>                    Specify a VM option
    public JavapArguments _J(Object flag) {
        add("-J" + toArgumentString(flag));
        return this;
    }

    public JavapArguments _J(Consumer<JavaArguments> consumer) {
        var opts = new JavaArguments();
        consumer.accept(opts);
        opts.forEach(opt -> add("-J" + opt));
        return this;
    }

    //  --module-path <path>             Specify where to find application modules
    public JavapArguments __module_path(Object... path) {
        return __module_path(Arrays.asList(path));
    }

    public JavapArguments __module_path(List<?> path) {
        add("--class-path");
        add(path.stream().map(JavapArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    //  --system <jdk>                   Specify where to find system modules
    public JavapArguments __system(Object jdk) {
        add("--system");
        add(toArgumentString(jdk));
        return this;
    }

    //  --class-path <path>              Specify where to find user class files
    public JavapArguments __class_path(Object... path) {
        return __class_path(Arrays.asList(path));
    }

    public JavapArguments __class_path(List<?> path) {
        add("--class-path");
        add(path.stream().map(JavapArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    //  -classpath <path>                Specify where to find user class files
    public JavapArguments _classpath(Object... path) {
        return _classpath(Arrays.asList(path));
    }

    public JavapArguments _classpath(List<?> path) {
        add("-classpath");
        add(path.stream().map(JavapArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    //  -cp <path>                       Specify where to find user class files
    public JavapArguments _cp(Object... path) {
        return _bootclasspath(Arrays.asList(path));
    }

    public JavapArguments _cp(List<?> path) {
        add("-cp");
        add(path.stream().map(JavapArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    //  -bootclasspath <path>            Override location of bootstrap class files
    public JavapArguments _bootclasspath(Object... path) {
        return _bootclasspath(Arrays.asList(path));
    }

    public JavapArguments _bootclasspath(List<?> path) {
        add("-bootclasspath");
        add(path.stream().map(JavapArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    //  --multi-release <version>        Specify the version to use in multi-release JAR files
    public JavapArguments __multi_release(Object version) {
        add("--multi-release");
        add(toArgumentString(version));
        return this;
    }

    //
    //GNU-style options may use = instead of whitespace to separate the name of an option
    //from its value.
    //
    //Each class to be shown may be specified by a filename, a URL, or by its fully
    //qualified class name. Examples:
    //   path/to/MyClass.class
    //   jar:file:///path/to/MyJar.jar!/mypkg/MyClass.class
    //   java.lang.Object
}
