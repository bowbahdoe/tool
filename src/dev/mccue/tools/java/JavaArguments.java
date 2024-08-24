package dev.mccue.tools.java;

import dev.mccue.tools.ExitStatusException;
import dev.mccue.tools.ToolArguments;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class JavaArguments extends ToolArguments {
    static String toArgumentString(Object o) {
        return o == null ? "" : o.toString();
    }

    public JavaArguments() {
        super();
    }

    public JavaArguments(Collection<? extends String> c) {
        super(c);
    }

    // Usage: java [options] <mainclass> [args...]
    //           (to execute a class)
    //   or  java [options] -jar <jarfile> [args...]
    //           (to execute a jar file)
    //   or  java [options] -m <module>[/<mainclass>] [args...]
    //       java [options] --module <module>[/<mainclass>] [args...]
    //           (to execute the main class in a module)
    //   or  java [options] <sourcefile> [args]
    //           (to execute a source-file program)
    //
    // Arguments following the main class, source file, -jar <jarfile>,
    // -m or --module <module>/<mainclass> are passed as the arguments to
    // main class.
    //
    // where options include:
    //
    //    -cp <class search path of directories and zip/jar files>
    //    -classpath <class search path of directories and zip/jar files>
    //    --class-path <class search path of directories and zip/jar files>
    //                  A : separated list of directories, JAR archives,
    //                  and ZIP archives to search for class files.
    //    -p <module path>
    //    --module-path <module path>...
    //                  A : separated list of elements, each element is a file path
    //                  to a module or a directory containing modules. Each module is either
    //                  a modular JAR or an exploded-module directory.
    //    --upgrade-module-path <module path>...
    //                  A : separated list of elements, each element is a file path
    //                  to a module or a directory containing modules to replace
    //                  upgradeable modules in the runtime image. Each module is either
    //                  a modular JAR or an exploded-module directory.
    //    --add-modules <module name>[,<module name>...]
    //                  root modules to resolve in addition to the initial module.
    //                  <module name> can also be ALL-DEFAULT, ALL-SYSTEM,
    //                  ALL-MODULE-PATH.
    public JavaArguments __add_modules(Object... moduleNames) {
        return __add_modules(Arrays.asList(moduleNames));
    }

    public JavaArguments __add_modules(List<?> moduleNames) {
        add("--add-modules");
        add(moduleNames.stream().map(JavaArguments::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

    //    --enable-native-access <module name>[,<module name>...]
    //                  allow code in modules to access code and data outside the Java runtime.
    //                  <module name> can also be ALL-UNNAMED to indicate code on the class path.
    public JavaArguments __enable_native_access(Object... moduleNames) {
        return __enable_native_access(Arrays.asList(moduleNames));
    }

    public JavaArguments __enable_native_access(List<?> moduleNames) {
        add("--enable-native-access");
        add(moduleNames.stream().map(JavaArguments::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }
    //    --list-modules
    //                  list observable modules and exit
    public JavaArguments __list_modules() {
        add("--list-modules");
        return this;
    }

    //    -d <module name>
    //    --describe-module <module name>
    //                  describe a module and exit
    public JavaArguments _d(Object moduleName) {
        add("-d");
        add(toArgumentString(moduleName));
        return this;
    }

    public JavaArguments __describe_module(Object moduleName) {
        add("--describe-module");
        add(toArgumentString(moduleName));
        return this;
    }

    //    --dry-run     create VM and load main class but do not execute main method.
    //                  The --dry-run option may be useful for validating the
    //                  command-line options such as the module system configuration.
    public JavaArguments __dry_run() {
        add("--dry-run");
        return this;
    }

    //    --validate-modules
    //                  validate all modules and exit
    //                  The --validate-modules option may be useful for finding
    //                  conflicts and other errors with modules on the module path.
    public JavaArguments __validate_modules() {
        add("--validate-modules");
        return this;
    }

    //    -D<name>=<value>
    //                  set a system property
    public JavaArguments _D(Object name, Object value) {
        add("-D" + toArgumentString(name) + "=" + toArgumentString(value));
        return this;
    }

    //    -verbose:[class|module|gc|jni]
    //                  enable verbose output for the given subsystem
    //    -version      print product version to the error stream and exit
    public JavaArguments _version() {
        add("-version");
        return this;
    }

    //    --version     print product version to the output stream and exit
    public JavaArguments __version() {
        add("--version");
        return this;
    }

    //    -showversion  print product version to the error stream and continue
    public JavaArguments _showversion() {
        add("-showversion");
        return this;
    }

    //    --show-version
    //                  print product version to the output stream and continue
    public JavaArguments __show_version() {
        add("--show-version");
        return this;
    }
    //    --show-module-resolution
    //                  show module resolution output during startup
    public JavaArguments __show_module_resolution() {
        add("--show-module-resolution");
        return this;
    }

    //    -? -h -help
    //                  print this help message to the error stream
    public JavaArguments _h() {
        add("-h");
        return this;
    }

    public JavaArguments _help() {
        add("-help");
        return this;
    }

    //    --help        print this help message to the output stream
    public JavaArguments __help() {
        add("--help");
        return this;
    }
    //    -X            print help on extra options to the error stream
    public JavaArguments _X() {
        add("-X");
        return this;
    }
    //    --help-extra  print help on extra options to the output stream
    public JavaArguments __help_extra() {
        add("--help-extra");
        return this;
    }
    //    -ea[:<packagename>...|:<classname>]
    //    -enableassertions[:<packagename>...|:<classname>]
    //                  enable assertions with specified granularity
    //    -da[:<packagename>...|:<classname>]
    //    -disableassertions[:<packagename>...|:<classname>]
    //                  disable assertions with specified granularity
    //    -esa | -enablesystemassertions
    //                  enable system assertions
    //    -dsa | -disablesystemassertions
    //                  disable system assertions
    //    -agentlib:<libname>[=<options>]
    //                  load native agent library <libname>, e.g. -agentlib:jdwp
    //                  see also -agentlib:jdwp=help
    //    -agentpath:<pathname>[=<options>]
    //                  load native agent library by full pathname
    //    -javaagent:<jarpath>[=<options>]
    //                  load Java programming language agent, see java.lang.instrument
    //    -splash:<imagepath>
    //                  show splash screen with specified image
    //                  HiDPI scaled images are automatically supported and used
    //                  if available. The unscaled image filename, e.g. image.ext,
    //                  should always be passed as the argument to the -splash option.
    //                  The most appropriate scaled image provided will be picked up
    //                  automatically.
    //                  See the SplashScreen API documentation for more information
    public JavaArguments _splash(Object imagepath) {
        add("-splash" + toArgumentString(imagepath));
        return this;
    }

    //    @argument files
    //                  one or more argument files containing options
    //    --disable-@files
    //                  prevent further argument file expansion
    //    --enable-preview
    //                  allow classes to depend on preview features of this release
    public JavaArguments __enable_preview() {
        add("--enable-preview");
        return this;
    }

    //To specify an argument for a long option, you can use --<name>=<value> or
    //--<name> <value>.

    //     -Xbatch           disable background compilation
    //    -Xbootclasspath/a:<directories and zip/jar files separated by :>
    //                      append to end of bootstrap class path
    //    -Xcheck:jni       perform additional checks for JNI functions
    public JavaArguments _Xcheck_jni() {
        add("-Xcheck:jni");
        return this;
    }
    //    -Xcomp            forces compilation of methods on first invocation
    public JavaArguments _Xcomp() {
        add("-Xdiag");
        return this;
    }
    //    -Xdebug           does nothing; deprecated for removal in a future release.
    //    -Xdiag            show additional diagnostic messages
    public JavaArguments _Xdiag() {
        add("-Xdiag");
        return this;
    }
    //    -Xfuture          enable strictest checks, anticipating future default.
    //                      This option is deprecated and may be removed in a
    //                      future release.
    //    -Xint             interpreted mode execution only
    public JavaArguments _Xint() {
        add("-Xint");
        return this;
    }
    //    -Xinternalversion
    //                      displays more detailed JVM version information than the
    //                      -version option
    //    -Xlog:<opts>      Configure or enable logging with the Java Virtual
    //                      Machine (JVM) unified logging framework. Use -Xlog:help
    //                      for details.
    //    -Xloggc:<file>    log GC status to a file with time stamps.
    //                      This option is deprecated and may be removed in a
    //                      future release. It is replaced by -Xlog:gc:<file>.
    //    -Xmixed           mixed mode execution (default)
    public JavaArguments _Xmixed() {
        add("-Xmixed");
        return this;
    }

    //    -Xmn<size>        sets the initial and maximum size (in bytes) of the heap
    //                      for the young generation (nursery)
    public JavaArguments _Xmn(Object size) {
        add("-Xmn" + toArgumentString(size));
        return this;
    }
    //    -Xms<size>        set initial Java heap size
    public JavaArguments _Xms(Object size) {
        add("-Xms" + toArgumentString(size));
        return this;
    }
    //    -Xmx<size>        set maximum Java heap size
    public JavaArguments _Xmx(Object size) {
        add("-Xmx" + toArgumentString(size));
        return this;
    }
    //    -Xnoclassgc       disable class garbage collection
    //    -Xrs              reduce use of OS signals by Java/VM (see documentation)
    //    -Xshare:auto      use shared class data if possible (default)
    //    -Xshare:off       do not attempt to use shared class data
    //    -Xshare:on        require using shared class data, otherwise fail.
    //                      This is a testing option and may lead to intermittent
    //                      failures. It should not be used in production environments.
    //    -XshowSettings    show all settings and continue
    //    -XshowSettings:all
    //                      show all settings in verbose detail and continue
    //    -XshowSettings:locale
    //                      show all locale related settings and continue
    //    -XshowSettings:properties
    //                      show all property settings and continue
    //    -XshowSettings:vm
    //                      show all vm related settings and continue
    //    -XshowSettings:security
    //                      show all security settings and continue
    //    -XshowSettings:security:all
    //                      show all security settings and continue
    //    -XshowSettings:security:properties
    //                      show security properties and continue
    //    -XshowSettings:security:providers
    //                      show static security provider settings and continue
    //    -XshowSettings:security:tls
    //                      show TLS related security settings and continue
    //    -XshowSettings:system
    //                      (Linux Only) show host system or container
    //                      configuration and continue
    //    -Xss<size>        set java thread stack size
    //                      The actual size may be rounded up to a multiple of the
    //                      system page size as required by the operating system.
    public JavaArguments _Xss(Object size) {
        add("-Xss" + toArgumentString(size));
        return this;
    }

    //    -Xverify          sets the mode of the bytecode verifier
    //                      Note that option -Xverify:none is deprecated and
    //                      may be removed in a future release.
    //    --add-reads <module>=<target-module>(,<target-module>)*
    //                      updates <module> to read <target-module>, regardless
    //                      of module declaration.
    //                      <target-module> can be ALL-UNNAMED to read all unnamed
    //                      modules.
    //    --add-exports <module>/<package>=<target-module>(,<target-module>)*
    //                      updates <module> to export <package> to <target-module>,
    //                      regardless of module declaration.
    //                      <target-module> can be ALL-UNNAMED to export to all
    //                      unnamed modules.
    //    --add-opens <module>/<package>=<target-module>(,<target-module>)*
    //                      updates <module> to open <package> to
    //                      <target-module>, regardless of module declaration.
    //    --limit-modules <module name>[,<module name>...]
    //                      limit the universe of observable modules
    //    --patch-module <module>=<file>(:<file>)*
    //                      override or augment a module with classes and resources
    //                      in JAR files or directories.
    //    --source <version>
    //                      set the version of the source in source-file mode.
    //    --finalization=<value>
    //                      controls whether the JVM performs finalization of objects,
    //                      where <value> is one of "enabled" or "disabled".
    //                      Finalization is enabled by default.
    //
    //These extra options are subject to change without notice.
    //
    //
    //The following options are macOS specific:
    //    -XstartOnFirstThread
    //                      run the main() method on the first (AppKit) thread
    //    -Xdock:name=<application name>
    //                      override default application name displayed in dock
    //    -Xdock:icon=<path to icon file>
    //                      override default icon displayed in dock

}
