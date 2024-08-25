package dev.mccue.tools.javac;

import dev.mccue.tools.ToolArguments;
import dev.mccue.tools.java.JavaArguments;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public final class JavacArguments extends ToolArguments {
    static String toArgumentString(Object o) {
        return o == null ? "" : o.toString();
    }

    public JavacArguments() {
        super();
    }

    public JavacArguments(Collection<? extends String> c) {
        super(c);
    }

// Usage: javac <options> <source files>
    public JavacArguments sourceFiles(Object... files) {
        return sourceFiles(Arrays.asList(files));
    }

    public JavacArguments sourceFiles(List<?> files) {
        files.forEach(file -> add(toArgumentString(file)));
        return this;
    }

//where possible options include:
//  @<filename>                  Read options and filenames from file
    public JavacArguments argumentFile(Object filename) {
        add("@" + toArgumentString(filename));
        return this;
    }


//  -Akey[=value]                Options to pass to annotation processors
    public JavacArguments _A(Object key) {
        add("-A" + toArgumentString(key));
        return this;
    }

    public JavacArguments _A(Object key, Object value) {
        add("-A" + toArgumentString(key) + "=" + toArgumentString(value));
        return this;
    }

//  --add-modules <module>(,<module>)*
//        Root modules to resolve in addition to the initial modules,
//        or all modules on the module path if <module> is ALL-MODULE-PATH.
    public JavacArguments __add_modules(Object... modules) {
        return __add_modules(Arrays.asList(modules));
    }

    public JavacArguments __add_modules(List<?> modules) {
        add("--add-modules");
        add(modules.stream().map(JavacArguments::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

//  --boot-class-path <path>, -bootclasspath <path>
//        Override location of bootstrap class files
    public JavacArguments __boot_class_path(Object... path) {
        return __boot_class_path(Arrays.asList(path));
    }

    public JavacArguments __boot_class_path(List<?> path) {
        add("--boot-class-path");
        add(path.stream().map(JavacArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    public JavacArguments _bootclasspath(Object... path) {
        return __boot_class_path(Arrays.asList(path));
    }

    public JavacArguments _bootclasspath(List<?> path) {
        add("--boot-class-path");
        add(path.stream().map(JavacArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

//  --class-path <path>, -classpath <path>, -cp <path>
//        Specify where to find user class files and annotation processors
    public JavacArguments __class_path(Object... path) {
        return __class_path(Arrays.asList(path));
    }

    public JavacArguments __class_path(List<?> path) {
        add("--class-path");
        add(path.stream().map(JavacArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    public JavacArguments _cp(Object... path) {
        return _cp(Arrays.asList(path));
    }

    public JavacArguments _cp(List<?> path) {
        add("-cp");
        add(path.stream().map(JavacArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

//  -d <directory>               Specify where to place generated class files
    public JavacArguments _d(Object directory) {
        add("-d");
        add(toArgumentString(directory));
        return this;
    }

//  -deprecation
//        Output source locations where deprecated APIs are used
    public JavacArguments _deprecation() {
        add("-deprecation");
        return this;
    }

//  --enable-preview
//        Enable preview language features.
//        To be used in conjunction with either -source or --release.
    public JavacArguments __enable_preview() {
        add("--enable-preview");
        return this;
    }

//  -encoding <encoding>         Specify character encoding used by source files
    public JavacArguments _encoding(Object encoding) {
        add("-encoding");
        add(toArgumentString(encoding));
        return this;
    }

//  -endorseddirs <dirs>         Override location of endorsed standards path
    public JavacArguments _endorseddirs(Object... dirs) {
        return _endorseddirs(Arrays.asList(dirs));
    }

    public JavacArguments _endorseddirs(List<?> dirs) {
        add("-endorseddirs");
        add(dirs.stream().map(JavacArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

//  -extdirs <dirs>              Override location of installed extensions
    public JavacArguments _extdirs(Object... dirs) {
        return _extdirs(Arrays.asList(dirs));
    }

    public JavacArguments _extdirs(List<?> dirs) {
        add("-extdirs");
        add(dirs.stream().map(JavacArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    //  -g                           Generate all debugging info
    //  -g:{lines,vars,source}       Generate only some debugging info
    //  -g:none                      Generate no debugging info

    public enum DebuggingInfo {
        ALL, NONE, LINES, VAR, SOURCE
    }

    public JavacArguments _g() {
        add("-g");
        return this;
    }

    public JavacArguments _g(DebuggingInfo option) {
        if (option.equals(DebuggingInfo.ALL)) {
            add("-g");
        } else {
            add("-g:" + option.name().toLowerCase());
        }
        return this;
    }

//  -h <directory>
//        Specify where to place generated native header files
    public JavacArguments _h(Object directory) {
        add("-h");
        add(toArgumentString(directory));
        return this;
    }

//  --help, -help, -?            Print this help message
    public JavacArguments __help() {
        add("--help");
        return this;
    }

    public JavacArguments _help() {
        add("-help");
        return this;
    }

//  --help-extra, -X             Print help on extra options
    public JavacArguments __help_extra() {
        add("--help-extra");
        return this;
    }

    public JavacArguments _X() {
        add("-X");
        return this;
    }
//  -implicit:{none,class}
//        Specify whether to generate class files for implicitly referenced files
    public enum Implicit {
        NONE, CLASS
    }

    public JavacArguments _implicit(Implicit implicit) {
        add("-implicit:" + (implicit == Implicit.NONE ? "none" : "class"));
        return this;
    }

//  -J<flag>                     Pass <flag> directly to the runtime system
    public JavacArguments _J(Object flag) {
        add("-J" + toArgumentString(flag));
        return this;
    }

    public JavacArguments _J(Consumer<JavaArguments> consumer) {
        var opts = new JavaArguments();
        consumer.accept(opts);
        opts.forEach(opt -> add("-J" + opt));
        return this;
    }

//  --limit-modules <module>(,<module>)*
//        Limit the universe of observable modules
    public JavacArguments __limit_modules(Object... modules) {
        return __limit_modules(Arrays.asList(modules));
    }

    public JavacArguments __limit_modules(List<?> modules) {
        add("--limit-modules");
        add(modules.stream().map(JavacArguments::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }
//  --module <module>(,<module>)*, -m <module>(,<module>)*
//        Compile only the specified module(s), check timestamps
    public JavacArguments __module(Object... modules) {
        return __module(Arrays.asList(modules));
    }

    public JavacArguments __module(List<?> modules) {
        add("--module");
        add(modules.stream().map(JavacArguments::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

    public JavacArguments _m(Object... modules) {
        return _m(Arrays.asList(modules));
    }

    public JavacArguments _m(List<?> modules) {
        add("-m");
        add(modules.stream().map(JavacArguments::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }
//  --module-path <path>, -p <path>
//        Specify where to find application modules
    public JavacArguments __module_path(Object... modules) {
        return __module_path(Arrays.asList(modules));
    }

    public JavacArguments __module_path(List<?> modules) {
        add("--module-path");
        add(modules.stream().map(JavacArguments::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

    public JavacArguments _p(Object... modules) {
        return _p(Arrays.asList(modules));
    }

    public JavacArguments _p(List<?> modules) {
        add("-p");
        add(modules.stream().map(JavacArguments::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

//  --module-source-path <module-source-path>
//        Specify where to find input source files for multiple modules
    public JavacArguments __module_source_path(Object moduleSourcePath) {
        add("--module-source-path");
        add(toArgumentString(moduleSourcePath));
        return this;
    }

//  --module-version <version>
//        Specify version of modules that are being compiled
    public JavacArguments __module_version(Object version) {
        add("--module-version");
        add(toArgumentString(version));
        return this;
    }

//  -nowarn                      Generate no warnings
    public JavacArguments _nowarn() {
        add("-nowarn");
        return this;
    }

//  -parameters
//        Generate metadata for reflection on method parameters
    public JavacArguments _parameters() {
        add("-parameters");
        return this;
    }

//  -proc:{none,only,full}
//        Control whether annotation processing and/or compilation is done.
    public enum Processing {
        NONE, ONLY
    }

    public JavacArguments _proc(Processing processing) {
        add("-proc:" + processing.name().toLowerCase());
        return this;
    }

//  -processor <class1>[,<class2>,<class3>...]
//        Names of the annotation processors to run;
//        bypasses default discovery process
    public JavacArguments _processor(Object... classNames) {
        return _processor(Arrays.asList(classNames));
    }

    public JavacArguments _processor(List<?> classNames) {
        add("-processor");
        add(classNames.stream().map(JavacArguments::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

//  --processor-module-path <path>
//        Specify a module path where to find annotation processors
    public JavacArguments __processor_module_path(Object... path) {
        return __processor_module_path(Arrays.asList(path));
    }

    public JavacArguments __processor_module_path(List<?> path) {
        add("--processor-module-path");
        add(path.stream().map(JavacArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

//  --processor-path <path>, -processorpath <path>
//        Specify where to find annotation processors
    public JavacArguments __processor_path(Object... path) {
        return __processor_path(Arrays.asList(path));
    }

    public JavacArguments __processor_path(List<?> path) {
        add("--processor-path");
        add(path.stream().map(JavacArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

//  -profile <profile>
//        Check that API used is available in the specified profile.
//        This option is deprecated and may be removed in a future release.
    public JavacArguments _profile(Object profile) {
        add(toArgumentString(profile));
        return this;
    }

//  --release <release>
//        Compile for the specified Java SE release.
//        Supported releases:
//            8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22
    public JavacArguments __release(Object value) {
        add("--release");
        add(toArgumentString(value));
        return this;
    }

//  -s <directory>               Specify where to place generated source files
    public JavacArguments _s(Object value) {
        add("-s");
        add(toArgumentString(value));
        return this;
    }

//  --source <release>, -source <release>
//        Provide source compatibility with the specified Java SE release.
//        Supported releases:
//            8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22
    public JavacArguments __source(Object value) {
        add("--source");
        add(toArgumentString(value));
        return this;
    }

    public JavacArguments _source(Object value) {
        add("-source");
        add(toArgumentString(value));
        return this;
    }

//  --source-path <path>, -sourcepath <path>
//        Specify where to find input source files
    public JavacArguments __source_path(Object... path) {
        return __source_path(Arrays.asList(path));
    }

    public JavacArguments __source_path(List<?> path) {
        add("--source-path");
        add(path.stream().map(JavacArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    public JavacArguments _sourcepath(Object... path) {
        return _sourcepath(Arrays.asList(path));
    }

    public JavacArguments _sourcepath(List<?> path) {
        add("-sourcepath");
        add(path.stream().map(JavacArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

//  --system <jdk>|none          Override location of system modules
    public JavacArguments __system(Object value) {
        add("--system");
        add(toArgumentString(value));
        return this;
    }

//  --target <release>, -target <release>
//        Generate class files suitable for the specified Java SE release.
//        Supported releases:
//            8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22
    public JavacArguments __target(Object value) {
        add("--target");
        add(toArgumentString(value));
        return this;
    }

    public JavacArguments _target(Object value) {
        add("-target");
        add(toArgumentString(value));
        return this;
    }

//  --upgrade-module-path <path>
//        Override location of upgradeable modules
    public JavacArguments __upgrade_module_path(Object... path) {
        return __upgrade_module_path(Arrays.asList(path));
    }

    public JavacArguments __upgrade_module_path(List<?> path) {
        add("--upgrade-module-path");
        add(path.stream().map(JavacArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

//  -verbose                     Output messages about what the compiler is doing
    public JavacArguments _verbose() {
        add("-verbose");
        return this;
    }

//  --version, -version          Version information
    public JavacArguments __version() {
        add("--version");
        return this;
    }

    public JavacArguments _version() {
        add("-version");
        return this;
    }

//  -Werror                      Terminate compilation if warnings occur
    public JavacArguments _Werror() {
        add("-Werror");
        return this;
    }
}
