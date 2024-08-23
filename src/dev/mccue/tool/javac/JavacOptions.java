package dev.mccue.tool.javac;

import dev.mccue.tool.ToolOptions;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JavacOptions extends ToolOptions {
    static String toArgumentString(Object o) {
        return o == null ? "" : o.toString();
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
    public JavacOptions A(Object key) {
        add("-A" + toArgumentString(key));
        return this;
    }

    public JavacOptions A(Object key, Object value) {
        add("-A" + toArgumentString(key) + "=" + toArgumentString(value));
        return this;
    }

//  --add-modules <module>(,<module>)*
//        Root modules to resolve in addition to the initial modules,
//        or all modules on the module path if <module> is ALL-MODULE-PATH.
    public JavacOptions addModules(Object... modules) {
        addModules(Arrays.asList(modules));
        return this;
    }

    public JavacOptions addModules(List<?> modules) {
        add("--add-modules");
        add(modules.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

//  --boot-class-path <path>, -bootclasspath <path>
//        Override location of bootstrap class files
    public JavacOptions bootClassPath(Object... path) {
        return bootClassPath(Arrays.asList(path));
    }

    public JavacOptions bootClassPath(List<?> path) {
        add("--boot-class-path");
        add(path.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

//  --class-path <path>, -classpath <path>, -cp <path>
//        Specify where to find user class files and annotation processors
    public JavacOptions classPath(Object... path) {
        return classPath(Arrays.asList(path));
    }

    public JavacOptions classPath(List<?> path) {
        add("--class-path");
        add(path.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    public JavacOptions cp(Object... path) {
        return cp(Arrays.asList(path));
    }

    public JavacOptions cp(List<?> path) {
        add("-cp");
        add(path.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

//  -d <directory>               Specify where to place generated class files
    public JavacOptions d(Object directory) {
        add("-d");
        add(toArgumentString(directory));
        return this;
    }

//  -deprecation
//        Output source locations where deprecated APIs are used
    public JavacOptions deprecation() {
        add("-deprecation");
        return this;
    }

//  --enable-preview
//        Enable preview language features.
//        To be used in conjunction with either -source or --release.
    public JavacOptions enablePreview() {
        add("--enable-preview");
        return this;
    }

//  -encoding <encoding>         Specify character encoding used by source files
    public JavacOptions encoding(Object encoding) {
        add("-encoding");
        add(toArgumentString(encoding));
        return this;
    }

//  -endorseddirs <dirs>         Override location of endorsed standards path
    public JavacOptions endorsedDirs(Object... dirs) {
        return endorsedDirs(Arrays.asList(dirs));
    }

    public JavacOptions endorsedDirs(List<?> dirs) {
        add("endorseddirs");
        add(dirs.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

//  -extdirs <dirs>              Override location of installed extensions
    public JavacOptions extDirs(Object... dirs) {
        return extDirs(Arrays.asList(dirs));
    }

    public JavacOptions extDirs(List<?> dirs) {
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

    public JavacOptions g() {
        add("-g");
        return this;
    }

    public JavacOptions g(DebuggingInfo option) {
        if (option.equals(DebuggingInfo.ALL)) {
            add("-g");
        } else {
            add("-g:" + option.name().toLowerCase());
        }
        return this;
    }

//  -h <directory>
//        Specify where to place generated native header files
    public JavacOptions h(Object directory) {
        add("-h");
        add(toArgumentString(directory));
        return this;
    }

//  --help, -help, -?            Print this help message
    public JavacOptions help() {
        add("--help");
        return this;
    }

//  --help-extra, -X             Print help on extra options
    public JavacOptions helpExtra() {
        add("--help-extra");
        return this;
    }

    public JavacOptions X() {
        add("-X");
        return this;
    }
//  -implicit:{none,class}
//        Specify whether to generate class files for implicitly referenced files
    public enum Implicit {
        NONE, CLASS
    }

    public JavacOptions implicit(Implicit implicit) {
        add("-implicit:" + (implicit == Implicit.NONE ? "none" : "class"));
        return this;
    }

//  -J<flag>                     Pass <flag> directly to the runtime system
    public JavacOptions J(Object flag) {
        add("-J" + toArgumentString(flag));
        return this;
    }

//  --limit-modules <module>(,<module>)*
//        Limit the universe of observable modules
    public JavacOptions limitModules(Object... modules) {
        return limitModules(Arrays.asList(modules));
    }

    public JavacOptions limitModules(List<?> modules) {
        add("--limit-modules");
        add(modules.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }
//  --module <module>(,<module>)*, -m <module>(,<module>)*
//        Compile only the specified module(s), check timestamps
    public JavacOptions module(Object... modules) {
        return module(Arrays.asList(modules));
    }

    public JavacOptions module(List<?> modules) {
        add("--modules");
        add(modules.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

    public JavacOptions m(Object... modules) {
        return m(Arrays.asList(modules));
    }

    public JavacOptions m(List<?> modules) {
        add("-m");
        add(modules.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }
//  --module-path <path>, -p <path>
//        Specify where to find application modules
    public JavacOptions modulePath(Object... modules) {
        return modulePath(Arrays.asList(modules));
    }

    public JavacOptions modulePath(List<?> modules) {
        add("--module-path");
        add(modules.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

    public JavacOptions p(Object... modules) {
        return p(Arrays.asList(modules));
    }

    public JavacOptions p(List<?> modules) {
        add("-p");
        add(modules.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

//  --module-source-path <module-source-path>
//        Specify where to find input source files for multiple modules
    public JavacOptions moduleSourcePath(Object moduleSourcePath) {
        add("--module-source-path");
        add(toArgumentString(moduleSourcePath));
        return this;
    }

//  --module-version <version>
//        Specify version of modules that are being compiled
    public JavacOptions moduleVersion(Object version) {
        add("--module-version");
        add(toArgumentString(version));
        return this;
    }

//  -nowarn                      Generate no warnings
    public JavacOptions noWarn() {
        add("-nowarn");
        return this;
    }

//  -parameters
//        Generate metadata for reflection on method parameters
    public JavacOptions parameters() {
        add("-parameters");
        return this;
    }

//  -proc:{none,only,full}
//        Control whether annotation processing and/or compilation is done.
    public enum Processing {
        NONE, ONLY
    }

    public JavacOptions proc(Processing processing) {
        add("-proc:" + processing.name().toLowerCase());
        return this;
    }

//  -processor <class1>[,<class2>,<class3>...]
//        Names of the annotation processors to run;
//        bypasses default discovery process
    public JavacOptions processor(Object... classNames) {
        return processor(Arrays.asList(classNames));
    }

    public JavacOptions processor(List<?> classNames) {
        add("-processor");
        add(classNames.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

//  --processor-module-path <path>
//        Specify a module path where to find annotation processors
    public JavacOptions processorModulePath(Object... path) {
        return processorModulePath(Arrays.asList(path));
    }

    public JavacOptions processorModulePath(List<?> path) {
        add("--processor-module-path");
        add(path.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

//  --processor-path <path>, -processorpath <path>
//        Specify where to find annotation processors
    public JavacOptions processorPath(Object... path) {
        return processorPath(Arrays.asList(path));
    }

    public JavacOptions processorPath(List<?> path) {
        add("--processor-path");
        add(path.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

//  -profile <profile>
//        Check that API used is available in the specified profile.
//        This option is deprecated and may be removed in a future release.
    public JavacOptions profile(Object profile) {
        add(toArgumentString(profile));
        return this;
    }

//  --release <release>
//        Compile for the specified Java SE release.
//        Supported releases:
//            8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22
    public JavacOptions release(Object value) {
        add("--release");
        add(toArgumentString(value));
        return this;
    }

//  -s <directory>               Specify where to place generated source files
    public JavacOptions s(Object value) {
        add("-s");
        add(toArgumentString(value));
        return this;
    }

//  --source <release>, -source <release>
//        Provide source compatibility with the specified Java SE release.
//        Supported releases:
//            8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22
    public JavacOptions source(Object value) {
        add("--source");
        add(toArgumentString(value));
        return this;
    }

//  --source-path <path>, -sourcepath <path>
//        Specify where to find input source files
    public JavacOptions sourcePath(Object... path) {
        return sourcePath(Arrays.asList(path));
    }

    public JavacOptions sourcePath(List<?> path) {
        add("--source-path");
        add(path.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

//  --system <jdk>|none          Override location of system modules
    public JavacOptions system(Object value) {
        add("--system");
        add(toArgumentString(value));
        return this;
    }

//  --target <release>, -target <release>
//        Generate class files suitable for the specified Java SE release.
//        Supported releases:
//            8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22
    public JavacOptions target(Object value) {
        add("--target");
        add(toArgumentString(value));
        return this;
    }

//  --upgrade-module-path <path>
//        Override location of upgradeable modules
    public JavacOptions upgradeModulePath(Object... path) {
        return upgradeModulePath(Arrays.asList(path));
    }

    public JavacOptions upgradeModulePath(List<?> path) {
        add("--upgrade-module-path");
        add(path.stream().map(JavacOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

//  -verbose                     Output messages about what the compiler is doing
    public JavacOptions verbose() {
        add("-verbose");
        return this;
    }

//  --version, -version          Version information
    public JavacOptions version() {
        add("--version");
        return this;
    }

//  -Werror                      Terminate compilation if warnings occur
    public JavacOptions Werror() {
        add("-Werror");
        return this;
    }
}
