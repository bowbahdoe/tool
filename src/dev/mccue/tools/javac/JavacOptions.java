package dev.mccue.tools.javac;

import dev.mccue.tools.ExitStatusException;
import dev.mccue.tools.ToolOptions;
import dev.mccue.tools.java.JavaOptions;
import dev.mccue.tools.javap.JavapOptions;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public final class JavacOptions extends ToolOptions {
    static String toArgumentString(Object o) {
        return o == null ? "" : o.toString();
    }

    public JavacOptions() {
        super();
    }

    public JavacOptions(Collection<? extends String> c) {
        super(c);
    }

// Usage: javac <options> <source files>
    public JavacOptions sourceFiles(Object... files) {
        return sourceFiles(Arrays.asList(files));
    }

    public JavacOptions sourceFiles(List<?> files) {
        files.forEach(file -> add(toArgumentString(file)));
        return this;
    }

//where possible options include:
//  @<filename>                  Read options and filenames from file
    public JavacOptions argumentFile(Object filename) {
        add("@" + toArgumentString(filename));
        return this;
    }


//  -Akey[=value]                Options to pass to annotation processors
    public JavacOptions _A(Object key) {
        add("-A" + toArgumentString(key));
        return this;
    }

    public JavacOptions _A(Object key, Object value) {
        add("-A" + toArgumentString(key) + "=" + toArgumentString(value));
        return this;
    }

//  --add-modules <module>(,<module>)*
//        Root modules to resolve in addition to the initial modules,
//        or all modules on the module path if <module> is ALL-MODULE-PATH.
    public JavacOptions __add_modules(Object... modules) {
        return __add_modules(Arrays.asList(modules));
    }

    public JavacOptions __add_modules(List<?> modules) {
        add("--add-modules");
        add(modules.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

//  --boot-class-path <path>, -bootclasspath <path>
//        Override location of bootstrap class files
    public JavacOptions __boot_class_path(Object... path) {
        return __boot_class_path(Arrays.asList(path));
    }

    public JavacOptions __boot_class_path(List<?> path) {
        add("--boot-class-path");
        add(path.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    public JavacOptions _bootclasspath(Object... path) {
        return __boot_class_path(Arrays.asList(path));
    }

    public JavacOptions _bootclasspath(List<?> path) {
        add("--boot-class-path");
        add(path.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

//  --class-path <path>, -classpath <path>, -cp <path>
//        Specify where to find user class files and annotation processors
    public JavacOptions __class_path(Object... path) {
        return __class_path(Arrays.asList(path));
    }

    public JavacOptions __class_path(List<?> path) {
        add("--class-path");
        add(path.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    public JavacOptions _cp(Object... path) {
        return _cp(Arrays.asList(path));
    }

    public JavacOptions _cp(List<?> path) {
        add("-cp");
        add(path.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

//  -d <directory>               Specify where to place generated class files
    public JavacOptions _d(Object directory) {
        add("-d");
        add(toArgumentString(directory));
        return this;
    }

//  -deprecation
//        Output source locations where deprecated APIs are used
    public JavacOptions _deprecation() {
        add("-deprecation");
        return this;
    }

//  --enable-preview
//        Enable preview language features.
//        To be used in conjunction with either -source or --release.
    public JavacOptions __enable_preview() {
        add("--enable-preview");
        return this;
    }

//  -encoding <encoding>         Specify character encoding used by source files
    public JavacOptions _encoding(Object encoding) {
        add("-encoding");
        add(toArgumentString(encoding));
        return this;
    }

//  -endorseddirs <dirs>         Override location of endorsed standards path
    public JavacOptions _endorseddirs(Object... dirs) {
        return _endorseddirs(Arrays.asList(dirs));
    }

    public JavacOptions _endorseddirs(List<?> dirs) {
        add("-endorseddirs");
        add(dirs.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

//  -extdirs <dirs>              Override location of installed extensions
    public JavacOptions _extdirs(Object... dirs) {
        return _extdirs(Arrays.asList(dirs));
    }

    public JavacOptions _extdirs(List<?> dirs) {
        add("-extdirs");
        add(dirs.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    //  -g                           Generate all debugging info
    //  -g:{lines,vars,source}       Generate only some debugging info
    //  -g:none                      Generate no debugging info

    public enum DebuggingInfo {
        ALL, NONE, LINES, VAR, SOURCE
    }

    public JavacOptions _g() {
        add("-g");
        return this;
    }

    public JavacOptions _g(DebuggingInfo option) {
        if (option.equals(DebuggingInfo.ALL)) {
            add("-g");
        } else {
            add("-g:" + option.name().toLowerCase());
        }
        return this;
    }

//  -h <directory>
//        Specify where to place generated native header files
    public JavacOptions _h(Object directory) {
        add("-h");
        add(toArgumentString(directory));
        return this;
    }

//  --help, -help, -?            Print this help message
    public JavacOptions __help() {
        add("--help");
        return this;
    }

    public JavacOptions _help() {
        add("-help");
        return this;
    }

//  --help-extra, -X             Print help on extra options
    public JavacOptions __help_extra() {
        add("--help-extra");
        return this;
    }

    public JavacOptions _X() {
        add("-X");
        return this;
    }
//  -implicit:{none,class}
//        Specify whether to generate class files for implicitly referenced files
    public enum Implicit {
        NONE, CLASS
    }

    public JavacOptions _implicit(Implicit implicit) {
        add("-implicit:" + (implicit == Implicit.NONE ? "none" : "class"));
        return this;
    }

//  -J<flag>                     Pass <flag> directly to the runtime system
    public JavacOptions _J(Object flag) {
        add("-J" + toArgumentString(flag));
        return this;
    }

    public JavacOptions _J(Consumer<JavaOptions> consumer) {
        var opts = new JavaOptions();
        consumer.accept(opts);
        opts.forEach(opt -> add("-J" + opt));
        return this;
    }

//  --limit-modules <module>(,<module>)*
//        Limit the universe of observable modules
    public JavacOptions __limit_modules(Object... modules) {
        return __limit_modules(Arrays.asList(modules));
    }

    public JavacOptions __limit_modules(List<?> modules) {
        add("--limit-modules");
        add(modules.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }
//  --module <module>(,<module>)*, -m <module>(,<module>)*
//        Compile only the specified module(s), check timestamps
    public JavacOptions __module(Object... modules) {
        return __module(Arrays.asList(modules));
    }

    public JavacOptions __module(List<?> modules) {
        add("--module");
        add(modules.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

    public JavacOptions _m(Object... modules) {
        return _m(Arrays.asList(modules));
    }

    public JavacOptions _m(List<?> modules) {
        add("-m");
        add(modules.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }
//  --module-path <path>, -p <path>
//        Specify where to find application modules
    public JavacOptions __module_path(Object... modules) {
        return __module_path(Arrays.asList(modules));
    }

    public JavacOptions __module_path(List<?> modules) {
        add("--module-path");
        add(modules.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

    public JavacOptions _p(Object... modules) {
        return _p(Arrays.asList(modules));
    }

    public JavacOptions _p(List<?> modules) {
        add("-p");
        add(modules.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

//  --module-source-path <module-source-path>
//        Specify where to find input source files for multiple modules
    public JavacOptions __module_source_pat(Object moduleSourcePath) {
        add("--module-source-path");
        add(toArgumentString(moduleSourcePath));
        return this;
    }

//  --module-version <version>
//        Specify version of modules that are being compiled
    public JavacOptions __module_version(Object version) {
        add("--module-version");
        add(toArgumentString(version));
        return this;
    }

//  -nowarn                      Generate no warnings
    public JavacOptions _nowarn() {
        add("-nowarn");
        return this;
    }

//  -parameters
//        Generate metadata for reflection on method parameters
    public JavacOptions _parameters() {
        add("-parameters");
        return this;
    }

//  -proc:{none,only,full}
//        Control whether annotation processing and/or compilation is done.
    public enum Processing {
        NONE, ONLY
    }

    public JavacOptions _proc(Processing processing) {
        add("-proc:" + processing.name().toLowerCase());
        return this;
    }

//  -processor <class1>[,<class2>,<class3>...]
//        Names of the annotation processors to run;
//        bypasses default discovery process
    public JavacOptions _processor(Object... classNames) {
        return _processor(Arrays.asList(classNames));
    }

    public JavacOptions _processor(List<?> classNames) {
        add("-processor");
        add(classNames.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

//  --processor-module-path <path>
//        Specify a module path where to find annotation processors
    public JavacOptions __processor_module_path(Object... path) {
        return __processor_module_path(Arrays.asList(path));
    }

    public JavacOptions __processor_module_path(List<?> path) {
        add("--processor-module-path");
        add(path.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

//  --processor-path <path>, -processorpath <path>
//        Specify where to find annotation processors
    public JavacOptions __processor_path(Object... path) {
        return __processor_path(Arrays.asList(path));
    }

    public JavacOptions __processor_path(List<?> path) {
        add("--processor-path");
        add(path.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

//  -profile <profile>
//        Check that API used is available in the specified profile.
//        This option is deprecated and may be removed in a future release.
    public JavacOptions _profile(Object profile) {
        add(toArgumentString(profile));
        return this;
    }

//  --release <release>
//        Compile for the specified Java SE release.
//        Supported releases:
//            8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22
    public JavacOptions __release(Object value) {
        add("--release");
        add(toArgumentString(value));
        return this;
    }

//  -s <directory>               Specify where to place generated source files
    public JavacOptions _s(Object value) {
        add("-s");
        add(toArgumentString(value));
        return this;
    }

//  --source <release>, -source <release>
//        Provide source compatibility with the specified Java SE release.
//        Supported releases:
//            8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22
    public JavacOptions __source(Object value) {
        add("--source");
        add(toArgumentString(value));
        return this;
    }

    public JavacOptions _source(Object value) {
        add("-source");
        add(toArgumentString(value));
        return this;
    }

//  --source-path <path>, -sourcepath <path>
//        Specify where to find input source files
    public JavacOptions __source_path(Object... path) {
        return __source_path(Arrays.asList(path));
    }

    public JavacOptions __source_path(List<?> path) {
        add("--source-path");
        add(path.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    public JavacOptions _sourcepath(Object... path) {
        return _sourcepath(Arrays.asList(path));
    }

    public JavacOptions _sourcepath(List<?> path) {
        add("-sourcepath");
        add(path.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

//  --system <jdk>|none          Override location of system modules
    public JavacOptions __system(Object value) {
        add("--system");
        add(toArgumentString(value));
        return this;
    }

//  --target <release>, -target <release>
//        Generate class files suitable for the specified Java SE release.
//        Supported releases:
//            8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22
    public JavacOptions __target(Object value) {
        add("--target");
        add(toArgumentString(value));
        return this;
    }

    public JavacOptions _target(Object value) {
        add("-target");
        add(toArgumentString(value));
        return this;
    }

//  --upgrade-module-path <path>
//        Override location of upgradeable modules
    public JavacOptions __upgrade_module_path(Object... path) {
        return __upgrade_module_path(Arrays.asList(path));
    }

    public JavacOptions __upgrade_module_path(List<?> path) {
        add("--upgrade-module-path");
        add(path.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

//  -verbose                     Output messages about what the compiler is doing
    public JavacOptions _verbose() {
        add("-verbose");
        return this;
    }

//  --version, -version          Version information
    public JavacOptions __version() {
        add("--version");
        return this;
    }

    public JavacOptions _version() {
        add("-version");
        return this;
    }

//  -Werror                      Terminate compilation if warnings occur
    public JavacOptions _Werror() {
        add("-Werror");
        return this;
    }
}
