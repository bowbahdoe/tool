package dev.mccue.tools.junit;

import dev.mccue.tools.ToolArguments;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class JUnitArguments extends ToolArguments {
    static String toArgumentString(Object o) {
        return o == null ? "" : o.toString();
    }

    public JUnitArguments() {}

    public JUnitArguments(Collection<? extends String> c) {
        super(c);
    }

    public JUnitArguments discover() {
        add("discover");
        return this;
    }

    public JUnitArguments execute() {
        add("execute");
        return this;
    }

    public JUnitArguments engines() {
        add("engines");
        return this;
    }

    // Usage: junit execute [OPTIONS]
    //Execute tests
    //      [@<filename>...]       One or more argument files containing options.
    //      --disable-banner       Disable print out of the welcome message.
    public JUnitArguments __disable_banner() {
        add("--disable-banner");
        return this;
    }

    //      --disable-ansi-colors  Disable ANSI colors in output (not supported by all terminals).
    public JUnitArguments __disable_ansi_colors() {
        add("--disable-ansi-colors");
        return this;
    }

    //  -h, --help                 Display help information.
    public JUnitArguments _h() {
        add("-h");
        return this;
    }

    public JUnitArguments __help() {
        add("-h");
        return this;
    }
    //
    //SELECTORS
    //
    //      --scan-classpath, --scan-class-path[=PATH]
    //                             Scan all directories on the classpath or explicit classpath roots. Without arguments, only directories on the system classpath as well as additional classpath entries supplied
    //                               via -cp (directories and JAR files) are scanned. Explicit classpath roots that are not on the classpath will be silently ignored. This option can be repeated.
    public JUnitArguments __scan_classpath() {
        add("--scan-classpath");
        return this;
    }

    public JUnitArguments __scan_classpath(Object... path) {
        return __scan_classpath(Arrays.asList(path));
    }


    public JUnitArguments __scan_classpath(List<?> path) {
        add("--scan-classpath");
        add(path.stream().map(JUnitArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    //      --scan-modules         Scan all resolved modules for test discovery.
    public JUnitArguments __scan_modules() {
        add("--scan-modules");
        return this;
    }

    //  -u, --select-uri=URI       Select a URI for test discovery. This option can be repeated.
    public JUnitArguments _u(Object file) {
        add("-u");
        add(toArgumentString(file));
        return this;
    }


    public JUnitArguments __select_uri(Object file) {
        add("--select-uri");
        add(toArgumentString(file));
        return this;
    }

    //  -f, --select-file=FILE     Select a file for test discovery. This option can be repeated.
    public JUnitArguments _f(Object file) {
        add("-f");
        add(toArgumentString(file));
        return this;
    }

    public JUnitArguments __select_file(Object file) {
        add("--select-file");
        add(toArgumentString(file));
        return this;
    }


    //  -d, --select-directory=DIR Select a directory for test discovery. This option can be repeated.
    //  -o, --select-module=NAME   Select single module for test discovery. This option can be repeated.
    public JUnitArguments _o(Object name) {
        add("-o");
        add(toArgumentString(name));
        return this;
    }

    public JUnitArguments __select_module(Object name) {
        add("--select-module");
        add(toArgumentString(name));
        return this;
    }

    //  -p, --select-package=PKG   Select a package for test discovery. This option can be repeated.
    public JUnitArguments _p(Object pkg) {
        add("-p");
        add(toArgumentString(pkg));
        return this;
    }

    public JUnitArguments __select_package(Object pkg) {
        add("--select-package");
        add(toArgumentString(pkg));
        return this;
    }

    //  -c, --select-class=CLASS   Select a class for test discovery. This option can be repeated.
    public JUnitArguments _c(Object class_) {
        add("-c");
        add(toArgumentString(class_));
        return this;
    }

    public JUnitArguments __select_class(Object class_) {
        add("--select-class");
        add(toArgumentString(class_));
        return this;
    }

    //  -m, --select-method=NAME   Select a method for test discovery. This option can be repeated.
    public JUnitArguments _m(Object name) {
        add("-m");
        add(toArgumentString(name));
        return this;
    }

    public JUnitArguments __select_method(Object name) {
        add("--select-method");
        add(toArgumentString(name));
        return this;
    }

    //  -r, --select-resource=RESOURCE
    //                             Select a classpath resource for test discovery. This option can be repeated.
    public JUnitArguments _r(Object resource) {
        add("-r");
        add(toArgumentString(resource));
        return this;
    }

    public JUnitArguments __select_resource(Object resource) {
        add("--select-resource");
        add(toArgumentString(resource));
        return this;
    }
    //  -i, --select-iteration=TYPE:VALUE[INDEX(..INDEX)?(,INDEX(..INDEX)?)*]
    //                             Select iterations for test discovery (e.g. method:com.acme.Foo#m()[1..2]). This option can be repeated.
    public JUnitArguments _i(Object expr) {
        add("-i");
        add(toArgumentString(expr));
        return this;
    }

    public JUnitArguments __select_iteration(Object expr) {
        add("--select-iteration");
        add(toArgumentString(expr));
        return this;
    }

    //FILTERS
    //
    //  -n, --include-classname=PATTERN
    //                             Provide a regular expression to include only classes whose fully qualified names match. To avoid loading classes unnecessarily, the default pattern only includes class names that
    //                               begin with "Test" or end with "Test" or "Tests". When this option is repeated, all patterns will be combined using OR semantics. Default: ^(Test.*|.+[.$]Test.*|.*Tests?)$
    public JUnitArguments _n(Object pattern) {
        add("-n");
        add(toArgumentString(pattern));
        return this;
    }

    public JUnitArguments __include_classname(Object pattern) {
        add("--include-classname");
        add(toArgumentString(pattern));
        return this;
    }

    //  -N, --exclude-classname=PATTERN
    //                             Provide a regular expression to exclude those classes whose fully qualified names match. When this option is repeated, all patterns will be combined using OR semantics.
    public JUnitArguments _N(Object pattern) {
        add("-N");
        add(toArgumentString(pattern));
        return this;
    }

    public JUnitArguments __exclude_classname(Object pattern) {
        add("--exclude-classname");
        add(toArgumentString(pattern));
        return this;
    }
    //      --include-package=PKG  Provide a package to be included in the test run. This option can be repeated.
    public JUnitArguments __include_package(Object pkg) {
        add("--include-package");
        add(toArgumentString(pkg));
        return this;
    }

    //      --exclude-package=PKG  Provide a package to be excluded from the test run. This option can be repeated.
    public JUnitArguments __exclude_package(Object pkg) {
        add("--exclude-package");
        add(toArgumentString(pkg));
        return this;
    }

    //  -t, --include-tag=TAG      Provide a tag or tag expression to include only tests whose tags match. When this option is repeated, all patterns will be combined using OR semantics.
    public JUnitArguments _t(Object tag) {
        add("-t");
        add(toArgumentString(tag));
        return this;
    }
    public JUnitArguments __include_tag(Object tag) {
        add("--include-tag");
        add(toArgumentString(tag));
        return this;
    }

    //  -T, --exclude-tag=TAG      Provide a tag or tag expression to exclude those tests whose tags match. When this option is repeated, all patterns will be combined using OR semantics.
    public JUnitArguments _T(Object tag) {
        add("-T");
        add(toArgumentString(tag));
        return this;
    }

    public JUnitArguments __exclude_tag(Object tag) {
        add("--exclude-tag");
        add(toArgumentString(tag));
        return this;
    }
    //  -e, --include-engine=ID    Provide the ID of an engine to be included in the test run. This option can be repeated.
    public JUnitArguments _e(Object id) {
        add("-e");
        add(toArgumentString(id));
        return this;
    }

    public JUnitArguments __include_engine(Object id) {
        add("--include-engine");
        add(toArgumentString(id));
        return this;
    }
    //  -E, --exclude-engine=ID    Provide the ID of an engine to be excluded from the test run. This option can be repeated.
    public JUnitArguments _E(Object id) {
        add("-E");
        add(toArgumentString(id));
        return this;
    }

    public JUnitArguments __exclude_engine(Object id) {
        add("--exclude-engine");
        add(toArgumentString(id));
        return this;
    }
    //
    //RUNTIME CONFIGURATION
    //
    //      -cp, --classpath, --class-path=PATH
    //                             Provide additional classpath entries -- for example, for adding engines and their dependencies. This option can be repeated.
    public JUnitArguments __class_path(Object... path) {
        return __class_path(Arrays.asList(path));
    }

    public JUnitArguments __class_path(List<?> path) {
        add("--class-path");
        add(path.stream().map(JUnitArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    public JUnitArguments _classpath(Object... path) {
        return __class_path(Arrays.asList(path));
    }

    public JUnitArguments _classpath(List<?> path) {
        add("-classpath");
        add(path.stream().map(JUnitArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    public JUnitArguments _cp(Object... path) {
        return _cp(Arrays.asList(path));
    }

    public JUnitArguments _cp(List<?> path) {
        add("-cp");
        add(path.stream().map(JUnitArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }
    //      --config=KEY=VALUE     Set a configuration parameter for test discovery and execution. This option can be repeated.
    public JUnitArguments __config(Object key, Object value) {
        add("--config");
        add(toArgumentString(key) + "=" + toArgumentString(value));
        return this;
    }

    //CONSOLE OUTPUT
    //
    //      --color-palette=FILE   Specify a path to a properties file to customize ANSI style of output (not supported by all terminals).
    public JUnitArguments __color_palette(Object file) {
        add("--color-palette");
        add(toArgumentString(file));
        return this;
    }

    //      --single-color         Style test output using only text attributes, no color (not supported by all terminals).
    public JUnitArguments __single_color() {
        add("--single-color");
        return this;
    }

    //      --details=MODE         Select an output details mode for when tests are executed. Use one of: none, summary, flat, tree, verbose, testfeed. If 'none' is selected, then only the summary and test
    //                               failures are shown. Default: tree.
    public enum Details {
        NONE("none"),
        SUMMARY("summary"),
        FLAT("flat"),
        TREE("tree"),
        VERBOSE("verbose"),
        TESTFEED("testfeed");

        final String value;

        Details(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "value";
        }
    }

    public JUnitArguments __details(Details details) {
        add("--details");
        add(toArgumentString(details));
        return this;
    }

    //      --details-theme=THEME  Select an output details tree theme for when tests are executed. Use one of: ascii, unicode. Default is detected based on default character encoding.
    public JUnitArguments __details_theme(Object theme) {
        add("--details-theme");
        add(toArgumentString(theme));
        return this;
    }
    //
    //REPORTING
    //
    //      --fail-if-no-tests     Fail and return exit status code 2 if no tests are found.
    public JUnitArguments __fail_if_no_tests() {
        add("--fail-if-no-tests");
        return this;
    }

    //      --reports-dir=DIR      Enable report output into a specified local directory (will be created if it does not exist).
    public JUnitArguments __reports_dir(Object dir) {
        add("--reports-dir");
        add(toArgumentString(dir));
        return this;
    }

    public JUnitArguments argumentFile(Object filename) {
        add("@" + toArgumentString(filename));
        return this;
    }
}
