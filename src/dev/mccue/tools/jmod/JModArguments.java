package dev.mccue.tools.jmod;

import dev.mccue.tools.ToolArguments;
import dev.mccue.tools.jar.JarArguments;
import dev.mccue.tools.javac.JavacArguments;
import dev.mccue.tools.jpackage.JPackageArguments;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class JModArguments extends ToolArguments {
    static String toArgumentString(Object o) {
        return o == null ? "" : o.toString();
    }

    public JModArguments() {
        super();
    }

    public JModArguments(Collection<? extends String> c) {
        super(c);
    }

    public JModArguments jmodFile(Object file) {
        add(toArgumentString(file));
        return this;
    }

    // Usage: jmod (create|extract|list|describe|hash) <OPTIONS> <jmod-file>
    //
    //Main operation modes:
    //  create    - Creates a new jmod archive
    public JModArguments create() {
        add("create");
        return this;
    }
    //  extract   - Extracts all the files from the archive
    public JModArguments extract() {
        add("extract");
        return this;
    }
    //  list      - Prints the names of all the entries
    public JModArguments list() {
        add("list");
        return this;
    }
    //  describe  - Prints the module details
    public JModArguments describe() {
        add("describe");
        return this;
    }
    //  hash      - Records hashes of tied modules.
    public JModArguments hash() {
        add("hash");
        return this;
    }
    //
    // Option                              Description
    // ------                              -----------
    //  -?, -h, --help                      Print this help message
    public JModArguments _h() {
        add("-h");
        return this;
    }

    public JModArguments __help() {
        add("-h");
        return this;
    }
    //  --class-path <path>                 Application jar files|dir containing
    //                                        classes
    public JModArguments __class_path(Object... path) {
        return __class_path(Arrays.asList(path));
    }

    public JModArguments __class_path(List<?> path) {
        add("--class-path");
        add(path.stream().map(JModArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }
    //  --cmds <path>                       Location of native commands
    public JModArguments __cmds(Object path) {
        add("--cmds");
        add(toArgumentString(path));
        return this;
    }
    //  --compress <compress>               Compression to use when creating the
    //                                        JMOD archive. Accepted values are:
    //                                        zip-[0-9], where zip-0 provides no
    //                                        compression, and zip-9 provides the
    //                                        best compression. Default is zip-6.
    public JModArguments __compress(Object compress) {
        add("--compress");
        add(toArgumentString(compress));
        return this;
    }

    //  --config <path>                     Location of user-editable config files
    public JModArguments __config(Object path) {
        add("--config");
        add(toArgumentString(path));
        return this;
    }

    //  --date <date>                       Date and time for the timestamps of
    //                                        entries, specified in ISO-8601
    //                                        extended offset date-time with
    //                                        optional time-zone format, e.g.
    //                                        "2022-02-12T12:30:00-05:00"
    public JModArguments __date(Object date) {
        add("--date");
        add(toArgumentString(date));
        return this;
    }

    //  --dir <path>                        Target directory for extract
    public JModArguments __dir(Object path) {
        add("--dir");
        add(toArgumentString(path));
        return this;
    }

    //  --dry-run                           Dry run of hash mode
    public JModArguments __dry_run() {
        add("--dry-run");
        return this;
    }

    //  --exclude <pattern-list>            Exclude files matching the supplied
    //                                        comma separated pattern list, each
    //                                        element using one the following
    //                                        forms: <glob-pattern>, glob:<glob-
    //                                        pattern> or regex:<regex-pattern>
    public JModArguments __exclude(Object... patternList) {
        return __exclude(Arrays.asList(patternList));
    }

    public JModArguments __exclude(List<?> patternList) {
        add("--exclude");
        add(patternList.stream().map(JModArguments::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }
    //  --hash-modules <regex-pattern>      Compute and record hashes to tie a
    //                                        packaged module with modules
    //                                        matching the given <regex-pattern>
    //                                        and depending upon it directly or
    //                                        indirectly. The hashes are recorded
    //                                        in the JMOD file being created, or a
    //                                        JMOD file or modular JAR on the
    //                                        module path specified the jmod hash
    //                                        command.
    public JModArguments __hash_modules(Object regexPattern) {
        add("--hash-modules");
        add(toArgumentString(regexPattern));
        return this;
    }
    //  --header-files <path>               Location of header files
    public JModArguments __header_files(Object path) {
        add("--header-files");
        add(toArgumentString(path));
        return this;
    }

    //  --help-extra                        Print help on extra options
    public JModArguments __help_extra() {
        add("--help-extra");
        return this;
    }

    //  --legal-notices <path>              Location of legal notices
    public JModArguments __legal_notices(Object path) {
        add("--legal-notices");
        add(toArgumentString(path));
        return this;
    }
    //  --libs <path>                       Location of native libraries
    public JModArguments __libs(Object path) {
        add("--libs");
        add(toArgumentString(path));
        return this;
    }
    //  --main-class <String: class-name>   Main class
    public JModArguments __main_class(Object className) {
        add("--main-class");
        add(toArgumentString(className));
        return this;
    }

    //  --man-pages <path>                  Location of man pages
    public JModArguments __man_pages(Object path) {
        add("--man-pages");
        add(toArgumentString(path));
        return this;
    }
    //  --module-version <module-version>   Module version
    public JModArguments __module_version(Object version) {
        add("--module-version");
        add(toArgumentString(version));
        return this;
    }
    //  -p, --module-path <path>            Module path
    public JModArguments _p(Object... path) {
        return _p(Arrays.asList(path));
    }

    public JModArguments _p(List<?> path) {
        add("-p");
        add(
                path
                        .stream()
                        .map(JModArguments::toArgumentString)
                        .collect(Collectors.joining(File.pathSeparator))
        );
        return this;
    }

    public JModArguments __module_path(Object... path) {
        return __module_path(Arrays.asList(path));
    }

    public JModArguments __module_path(List<?> path) {
        add("--module-path");
        add(
                path
                        .stream()
                        .map(JModArguments::toArgumentString)
                        .collect(Collectors.joining(File.pathSeparator))
        );
        return this;
    }
    //  --target-platform <String: target-  Target platform
    //    platform>
    public JModArguments __target_platform(Object targetPlatform) {
        add("--target-platform");
        add(toArgumentString(targetPlatform));
        return this;
    }

    //  --version                           Version information
    public JModArguments __version() {
        add("--version");
        return this;
    }

    //  @<filename>                         Read options from the specified file
    public JModArguments argumentFile(Object filename) {
        add("@" + toArgumentString(filename));
        return this;
    }
}
