package dev.mccue.tools.javap;

import dev.mccue.tools.ToolOptions;

public class JavapOptions extends ToolOptions {
    // Usage: javap <options> <classes>
    //where possible options include:
    //  --help -help -h -?               Print this help message
    public JavapOptions help() {
        add("--help");
        return this;
    }

    public JavapOptions h() {
        add("-h");
        return this;
    }

    //  -version                         Version information
    public JavapOptions version() {
        add("-version");
        return this;
    }

    //  -v  -verbose                     Print additional information
    public JavapOptions v() {
        add("-v");
        return this;
    }

    public JavapOptions verbose() {
        add("-verbose");
        return this;
    }

    //  -l                               Print line number and local variable tables
    public JavapOptions l() {
        add("-l");
        return this;
    }

    //  -public                          Show only public classes and members
    public JavapOptions public_() {
        add("-public");
        return this;
    }

    //  -protected                       Show protected/public classes and members
    public JavapOptions protected_() {
        add("-protected");
        return this;
    }

    //  -package                         Show package/protected/public classes
    //                                   and members (default)
    public JavapOptions package_() {
        add("-package");
        return this;
    }

    //  -p  -private                     Show all classes and members
    public JavapOptions p() {
        add("-p");
        return this;
    }

    public JavapOptions private_() {
        add("-private");
        return this;
    }

    //  -c                               Disassemble the code
    public JavapOptions c() {
        add("-c");
        return this;
    }

    //  -s                               Print internal type signatures
    public JavapOptions s() {
        add("-s");
        return this;
    }

    //  -sysinfo                         Show system info (path, size, date, SHA-256 hash)
    //                                   of class being processed
    public JavapOptions sysinfo() {
        add("-sysinfo");
        return this;
    }

    //  -constants                       Show final constants
    public JavapOptions constants() {
        add("-constants");
        return this;
    }

    //  --module <module>  -m <module>   Specify module containing classes to be disassembled
    //  -J<vm-option>                    Specify a VM option
    //  --module-path <path>             Specify where to find application modules
    //  --system <jdk>                   Specify where to find system modules
    //  --class-path <path>              Specify where to find user class files
    //  -classpath <path>                Specify where to find user class files
    //  -cp <path>                       Specify where to find user class files
    //  -bootclasspath <path>            Override location of bootstrap class files
    //  --multi-release <version>        Specify the version to use in multi-release JAR files
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
