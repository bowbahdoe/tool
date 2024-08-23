package dev.mccue.tool.jar;

import dev.mccue.tool.ToolOptions;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JarOptions extends ToolOptions {

    static String toArgumentString(Object o) {
        return o == null ? "" : o.toString();
    }

    //Usage: jar [OPTION...] [ [--release VERSION] [-C dir] files] ...
    //jar creates an archive for classes and resources, and can manipulate or
    //restore individual classes or resources from an archive.
    //
    // Examples:
    // # Create an archive called classes.jar with two class files:
    // jar --create --file classes.jar Foo.class Bar.class
    // # Create an archive using an existing manifest, with all the files in foo/:
    // jar --create --file classes.jar --manifest mymanifest -C foo/ .
    // # Create a modular jar archive, where the module descriptor is located in
    // # classes/module-info.class:
    // jar --create --file foo.jar --main-class com.foo.Main --module-version 1.0
    //     -C foo/ classes resources
    // # Update an existing non-modular jar to a modular jar:
    // jar --update --file foo.jar --main-class com.foo.Main --module-version 1.0
    //     -C foo/ module-info.class
    // # Create a multi-release jar, placing some files in the META-INF/versions/9 directory:
    // jar --create --file mr.jar -C foo classes --release 9 -C foo9 classes
    //
    //To shorten or simplify the jar command, you can specify arguments in a separate
    //text file and pass it to the jar command with the at sign (@) as a prefix.
    //
    // Examples:
    // # Read additional options and list of class files from the file classes.list
    // jar --create --file my.jar @classes.list
    //
    //
    // Main operation mode:



    //  -c, --create               Create the archive. When the archive file name specified
    //                             by -f or --file contains a path, missing parent directories
    //                             will also be created

    /**
     * Create the archive. When the archive file name specified
     * by -f or --file contains a path, missing parent directories
     * will also be created
     */
    public JarOptions c() {
        add("-c");
        return this;
    }

    /**
     * Create the archive. When the archive file name specified
     * by -f or --file contains a path, missing parent directories
     * will also be created
     */
    public JarOptions create() {
        add("--create");
        return this;
    }

    //  -i, --generate-index=FILE  Generate index information for the specified jar
    //                             archives. This option is deprecated and may be
    //                             removed in a future release.

    //  -t, --list                 List the table of contents for the archive

    /**
     * List the table of contents for the archive
     */
    public JarOptions t() {
        add("-t");
        return this;
    }


    /**
     * List the table of contents for the archive
     */
    public JarOptions list() {
        add("-t");
        return this;
    }

    //  -u, --update               Update an existing jar archive

    public JarOptions u() {
        add("-u");
        return this;
    }

    public JarOptions update() {
        add("--update");
        return this;
    }

    //  -x, --extract              Extract named (or all) files from the archive


    public JarOptions x() {
        add("-x");
        return this;
    }

    public JarOptions extract() {
        add("--extract");
        return this;
    }

    //  -d, --describe-module      Print the module descriptor, or automatic module name

    public JarOptions d() {
        add("-d");
        return this;
    }

    public JarOptions describeModule() {
        add("--describe-module");
        return this;
    }

    //      --validate             Validate the contents of the jar archive. This option
    //                             will validate that the API exported by a multi-release
    //                             jar archive is consistent across all different release
    //                             versions.

    public JarOptions validate() {
        add("--validate");
        return this;
    }

    //
    // Operation modifiers valid in any mode:
    //
    //  -C DIR                     Change to the specified directory and include the
    //                             following file

    public JarOptions C(Object dir, Object file) {
        add("-C");
        add(toArgumentString(dir));
        add(toArgumentString(file));
        return this;
    }

    //  -f, --file=FILE            The archive file name. When omitted, either stdin or
    //                             stdout is used based on the operation

    public JarOptions f(Object file) {
        add("-f");
        add(toArgumentString(file));
        return this;
    }

    public JarOptions file(Object file) {
        add("--file");
        add(toArgumentString(file));
        return this;
    }

    //      --release VERSION      Places all following files in a versioned directory
    //                             of the jar (i.e. META-INF/versions/VERSION/)
    public JarOptions release(Object version) {
        add("--release");
        add(toArgumentString(version));
        return this;
    }

    //  -v, --verbose              Generate verbose output on standard output
    public JarOptions v() {
        add("-v");
        return this;
    }

    public JarOptions verbose() {
        add("--verbose");
        return this;
    }

    // Operation modifiers valid only in create and update mode:
    //
    //  -e, --main-class=CLASSNAME The application entry point for stand-alone
    //                             applications bundled into a modular, or executable,
    //                             jar archive
    public JarOptions e(Object className) {
        add("-e");
        add(toArgumentString(className));
        return this;
    }

    public JarOptions mainClass(Object className) {
        add("--main-class");
        add(toArgumentString(className));
        return this;
    }


    //  -m, --manifest=FILE        Include the manifest information from the given
    //                             manifest file
    public JarOptions m(Object file) {
        add("-m");
        add(toArgumentString(file));
        return this;
    }

    public JarOptions manifest(Object file) {
        add("--mainifest");
        add(toArgumentString(file));
        return this;
    }

    //  -M, --no-manifest          Do not create a manifest file for the entries
    public JarOptions M() {
        add("-M");
        return this;
    }

    public JarOptions noManifest() {
        add("--no-manifest");
        return this;
    }

    //      --module-version=VERSION    The module version, when creating a modular
    //                             jar, or updating a non-modular jar
    public JarOptions moduleVersion(Object version) {
        add("--module-version");
        add(toArgumentString(version));
        return this;
    }

    //      --hash-modules=PATTERN Compute and record the hashes of modules
    //                             matched by the given pattern and that depend upon
    //                             directly or indirectly on a modular jar being
    //                             created or a non-modular jar being updated
    public JarOptions hashModules(Object pattern) {
        add("--hash-modules");
        add(toArgumentString(pattern));
        return this;
    }

    //  -p, --module-path          Location of module dependence for generating
    //                             the hash
    public JarOptions p(Object... path) {
        return p(Arrays.asList(path));
    }

    public JarOptions p(List<?> path) {
        add("-p");
        add(
                path
                        .stream()
                        .map(JarOptions::toArgumentString)
                        .collect(Collectors.joining(File.pathSeparator))
        );
        return this;
    }

    public JarOptions modulePath(Object... path) {
        return modulePath(Arrays.asList(path));
    }

    public JarOptions modulePath(List<?> path) {
        add("--module-path");
        add(
                path
                        .stream()
                        .map(JarOptions::toArgumentString)
                        .collect(Collectors.joining(File.pathSeparator))
        );
        return this;
    }



    // Operation modifiers valid only in create, update, and generate-index mode:
    //
    //  -0, --no-compress          Store only; use no ZIP compression
    public JarOptions _0() {
        add("-0");
        return this;
    }

    public JarOptions noCompress() {
        add("--no-compress");
        return this;
    }

    //      --date=TIMESTAMP       The timestamp in ISO-8601 extended offset date-time with
    //                             optional time-zone format, to use for the timestamps of
    //                             entries, e.g. "2022-02-12T12:30:00-05:00"
    public JarOptions date(Object timestamp) {
        add("--date");
        add(toArgumentString(timestamp));
        return this;
    }

    //
    // Other options:
    //
    //  -?, -h, --help[:compat]    Give this, or optionally the compatibility, help
    public JarOptions h() {
        add("-h");
        return this;
    }

    public JarOptions help() {
        add("--help");
        return this;
    }

    public JarOptions helpCompat() {
        add("--help:compat");
        return this;
    }

    //      --help-extra           Give help on extra options
    public JarOptions helpExtra() {
        add("--help-extra");
        return this;
    }

    //      --version              Print program version
    public JarOptions version() {
        add("--version");
        return this;
    }

    //
    // An archive is a modular jar if a module descriptor, 'module-info.class', is
    // located in the root of the given directories, or the root of the jar archive
    // itself. The following operations are only valid when creating a modular jar,
    // or updating an existing non-modular jar: '--module-version',
    // '--hash-modules', and '--module-path'.
    //
    // Mandatory or optional arguments to long options are also mandatory or optional
    // for any corresponding short options.
}
