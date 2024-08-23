package dev.mccue.tools.javadoc;

import dev.mccue.tools.ToolOptions;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JavadocOptions extends ToolOptions {
    static String toArgumentString(Object o) {
        return o == null ? "" : o.toString();
    }

    // Usage:
    //    javadoc [options] [packagenames] [sourcefiles] [@files]
    //where options include:
    public JavadocOptions packageNames(Object... packageNames) {
        return sourceFiles(Arrays.asList(packageNames));
    }

    public JavadocOptions packageNames(List<?> packageNames) {
        packageNames.forEach(file -> add(toArgumentString(file)));
        return this;
    }

    public JavadocOptions sourceFiles(Object... sourceFiles) {
        return sourceFiles(Arrays.asList(sourceFiles));
    }

    public JavadocOptions sourceFiles(List<?> sourceFiles) {
        sourceFiles.forEach(file -> add(toArgumentString(sourceFiles)));
        return this;
    }

    //    @<file>       Read options and filenames from file
    public JavadocOptions argumentFile(Object filename) {
        add("@" + toArgumentString(filename));
        return this;
    }

    //    --add-modules <module>(,<module>)*
    //                  Root modules to resolve in addition to the initial modules,
    //                  or all modules on the module path if <module> is
    //                  ALL-MODULE-PATH.
    public JavadocOptions addModules(Object... path) {
        return addModules(Arrays.asList(path));
    }

    public JavadocOptions addModules(List<?> path) {
        add("--add-modules");
        add(path.stream().map(JavadocOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    //    -bootclasspath <path>
    //                  Override location of platform class files used for non-modular
    //                  releases
    public JavadocOptions bootClassPath(Object... path) {
        return bootClassPath(Arrays.asList(path));
    }

    public JavadocOptions bootClassPath(List<?> path) {
        add("--boot-class-path");
        add(path.stream().map(JavadocOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    //    -breakiterator
    //                  Compute first sentence with BreakIterator
    public JavadocOptions breakIterator() {
        add("-breakiterator");
        return this;
    }

    //    --class-path <path>, -classpath <path>, -cp <path>
    //                  Specify where to find user class files
    public JavadocOptions classPath(Object... path) {
        return classPath(Arrays.asList(path));
    }

    public JavadocOptions classPath(List<?> path) {
        add("--class-path");
        add(path.stream().map(JavadocOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    public JavadocOptions cp(Object... path) {
        return cp(Arrays.asList(path));
    }

    public JavadocOptions cp(List<?> path) {
        add("-cp");
        add(path.stream().map(JavadocOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    //    -doclet <class>
    //                  Generate output via alternate doclet
    public JavadocOptions doclet(Object klass) {
        add("-doclet");
        add(toArgumentString(klass));
        return this;
    }

    //    -docletpath <path>
    //                  Specify where to find doclet class files
    public JavadocOptions docletPath(Object path) {
        add("-docletpath");
        add(toArgumentString(path));
        return this;
    }

    //    --enable-preview
    //                  Enable preview language features. To be used in conjunction with
    //                  either -source or --release.
    public JavadocOptions enablePreview() {
        add("--enable-preview");
        return this;
    }

    //    -encoding <name>
    //                  Source file encoding name
    public JavadocOptions encoding(Object name) {
        add("-encoding");
        add(toArgumentString(name));
        return this;
    }

    //    -exclude <pkglist>
    //                  Specify a list of packages to exclude
    public JavadocOptions exclude(Object... pkglist) {
        return exclude(Arrays.asList(pkglist));
    }

    public JavadocOptions exclude(List<?> pkglist) {
        add(pkglist.stream().map(JavadocOptions::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

    //    --expand-requires <value>
    //                  Instructs the tool to expand the set of modules to be
    //                  documented. By default, only the modules given explicitly on
    //                  the command line will be documented. A value of "transitive"
    //                  will additionally include all "requires transitive"
    //                  dependencies of those modules. A value of "all" will include
    //                  all dependencies of those modules.
    public JavadocOptions expandRequires(Object value) {
        add("--expand-requires");
        add(toArgumentString(value));
        return this;
    }

    //    -extdirs <dirlist>
    //                  Override location of installed extensions
    public JavadocOptions extDirs(Object... dirlist) {
        return extDirs(Arrays.asList(dirlist));
    }

    public JavadocOptions extDirs(List<?> dirlist) {
        add(dirlist.stream().map(JavadocOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    //    --help, -help, -?, -h
    //                  Display command-line options and exit
    public JavadocOptions help() {
        add("--help");
        return this;
    }

    public JavadocOptions h() {
        add("-h");
        return this;
    }
    //    --help-extra, -X
    //                  Print a synopsis of nonstandard options and exit
    public JavadocOptions helpExtra() {
        add("--help-extra");
        return this;
    }

    public JavadocOptions X() {
        add("-X");
        return this;
    }

    //    -J<flag>      Pass <flag> directly to the runtime system
    public JavadocOptions J(Object flag) {
        add("-J" + toArgumentString(flag));
        return this;
    }

    //    --limit-modules <module>(,<module>)*
    //                  Limit the universe of observable modules
    public JavadocOptions limitModules(Object... dirlist) {
        return limitModules(Arrays.asList(dirlist));
    }

    public JavadocOptions limitModules(List<?> dirlist) {
        add(dirlist.stream().map(JavadocOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    //    -locale <name>
    //                  Locale to be used, e.g. en_US or en_US_WIN
    public JavadocOptions locale(Object name) {
        add(toArgumentString(name));
        return this;
    }

    //    --module <module>(,<module>)*
    //                  Document the specified module(s)
    public JavadocOptions module(Object... modules) {
        return module(Arrays.asList(modules));
    }

    public JavadocOptions module(List<?> modules) {
        add(modules.stream().map(JavadocOptions::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

    //    --module-path <path>, -p <path>
    //                  Specify where to find application modules
    public JavadocOptions modulePath(Object... paths) {
        return modulePath(Arrays.asList(paths));
    }

    public JavadocOptions modulePath(List<?> paths) {
        add(paths.stream().map(JavadocOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    //    --module-source-path <path>
    //                  Specify where to find input source files for multiple modules
    public JavadocOptions moduleSourcePath(Object... paths) {
        return moduleSourcePath(Arrays.asList(paths));
    }

    public JavadocOptions moduleSourcePath(List<?> paths) {
        add(paths.stream().map(JavadocOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    //    -package
    //                  Show package/protected/public types and members. For
    //                  named modules, show all packages and all module details.
    public JavadocOptions package_() {
        add("-package");
        return this;
    }

    //    -private
    //                  Show all types and members. For named modules,
    //                  show all packages and all module details.
    public JavadocOptions private_() {
        add("-private");
        return this;
    }

    //    -protected
    //                  Show protected/public types and members (default). For
    //                  named modules, show exported packages and the module's API.
    public JavadocOptions protected_() {
        add("-protected");
        return this;
    }

    //    -public
    //                  Show only public types and members. For named modules,
    //                  show exported packages and the module's API.
    public JavadocOptions public_() {
        add("-public");
        return this;
    }

    //    -quiet        Do not display status messages
    public JavadocOptions quiet() {
        add("-quiet");
        return this;
    }

    //    --release <release>
    //                  Provide source compatibility with specified release
    public JavadocOptions release() {
        add("--release");
        return this;
    }

    //    --show-members <value>
    //                  Specifies which members (fields, methods, etc.) will be
    //                  documented, where value can be one of "public", "protected",
    //                  "package" or "private". The default is "protected", which will
    //                  show public and protected members, "public" will show only
    //                  public members, "package" will show public, protected and
    //                  package members and "private" will show all members.
    //    --show-module-contents <value>
    //                  Specifies the documentation granularity of module
    //                  declarations. Possible values are "api" or "all".
    //    --show-packages <value>
    //                  Specifies which modules packages will be documented. Possible
    //                  values are "exported" or "all" packages.
    //    --show-types <value>
    //                  Specifies which types (classes, interfaces, etc.) will be
    //                  documented, where value can be one of "public", "protected",
    //                  "package" or "private". The default is "protected", which will
    //                  show public and protected types, "public" will show only
    //                  public types, "package" will show public, protected and
    //                  package types and "private" will show all types.
    //    --source <release>, -source <release>
    //                  Provide source compatibility with specified release
    public JavadocOptions source(Object release) {
        add("--source");
        add(toArgumentString(release));
        return this;
    }

    //    --source-path <path>, -sourcepath <path>
    //                  Specify where to find source files
    //    -subpackages <subpkglist>
    //                  Specify subpackages to recursively load
    //    --system <jdk>
    //                  Override location of system modules used for modular releases
    //    --upgrade-module-path <path>
    //                  Override location of upgradeable modules
    //    -verbose      Output messages about what Javadoc is doing
    public JavadocOptions verbose() {
        add("-verbose");
        return this;
    }

    //    --version     Print version information
    //    -Werror       Report an error if any warnings occur
    //
    //Provided by the Standard doclet:
    //    --add-script <file>
    //                  Add a script file to the generated documentation
    //    --add-stylesheet <file>
    //                  Add a stylesheet file to the generated documentation
    //    --allow-script-in-comments
    //                  Allow JavaScript in options and comments
    //    -author       Include @author paragraphs
    //    -bottom <html-code>
    //                  Include bottom text for each page
    //    -charset <charset>
    //                  Charset for cross-platform viewing of generated documentation
    //    -d <directory>
    //                  Destination directory for output files
    //    -docencoding <name>
    //                  Specify the character encoding for the output
    //    -docfilessubdirs
    //                  Recursively copy doc-file subdirectories
    //    -doctitle <html-code>
    //                  Include title for the overview page
    //    -excludedocfilessubdir <name>,<name>,...
    //                  Exclude any doc-files subdirectories with given name.
    //                  ':' can also be used anywhere in the argument as a separator.
    //    -footer <html-code>
    //                  Include footer text for each page
    //    -group <name> <g1>,<g2>...
    //                  Group specified elements together in overview page.
    //                  ':' can also be used anywhere in the argument as a separator.
    //    -header <html-code>
    //                  Include header text for each page
    //    -helpfile <file>
    //                  Include file that help link links to
    //    -html5        Generate HTML 5 output. This option is no longer required.
    //    --javafx, -javafx
    //                  Enable JavaFX functionality
    //    -keywords     Include HTML meta tags with package, class and member info
    //    -link <url>   Create links to javadoc output at <url>
    //    --link-modularity-mismatch (warn|info)
    //                  Report external documentation with wrong modularity with either
    //                  a warning or informational message. The default behaviour is to
    //                  report a warning.
    //    -linkoffline <url1> <url2>
    //                  Link to docs at <url1> using package list at <url2>
    //    --link-platform-properties <url>
    //                  Link to platform documentation URLs declared in properties file at <url>
    //    -linksource   Generate source in HTML
    //    --main-stylesheet <file>, -stylesheetfile <file>
    //                  File to change style of the generated documentation
    //    -nocomment    Suppress description and tags, generate only declarations
    //    -nodeprecated
    //                  Do not include @deprecated information
    //    -nodeprecatedlist
    //                  Do not generate deprecated list
    //    -nohelp       Do not generate help link
    //    -noindex      Do not generate index
    //    -nonavbar     Do not generate navigation bar
    //    --no-platform-links
    //                  Do not generate links to the platform documentation
    //    -noqualifier <name1>,<name2>,...
    //                  Exclude the list of qualifiers from the output.
    //                  ':' can also be used anywhere in the argument as a separator.
    //    -nosince      Do not include @since information
    public JavadocOptions noSince() {
        add("-nosince");
        return this;
    }

    //    -notimestamp  Do not include hidden time stamp
    public JavadocOptions noTimestamp() {
        add("-notimestamp");
        return this;
    }

    //    -notree       Do not generate class hierarchy
    public JavadocOptions noTree() {
        add("-notree");
        return this;
    }

    //    --override-methods (detail|summary)
    //                  Document overridden methods in the detail or summary sections.
    //                  The default is 'detail'.
    public enum OverrideMethods {
        DETAIL("detail"),
        SUMMARY("summary");

        final String value;

        OverrideMethods(String value) {
            this.value = value;
        }
    }

    public JavadocOptions overrideMethods(OverrideMethods overrideMethods) {
        add("--override-methods");
        add(overrideMethods.value);
        return this;
    }


    //    -overview <file>
    //                  Read overview documentation from HTML file
    //    -serialwarn   Generate warning about @serial tag
    public JavadocOptions serialWarn() {
        add("-serialwarn");
        return this;
    }

    //    --since <release>(,<release>)*
    //                  Document new and deprecated API in the specified releases
    //    --since-label <text>
    //                  Provide text to use in the heading of the "New API" page
    public JavadocOptions sinceLabel(Object text) {
        add("--since-label");
        add(toArgumentString(text));
        return this;
    }

    //    --snippet-path <path>
    //                  The path for external snippets
    public JavadocOptions snippetPath(Object path) {
        add("--snippet-path");
        add(toArgumentString(path));
        return this;
    }

    //    -sourcetab <tab length>
    //                  Specify the number of spaces each tab takes up in the source
    public JavadocOptions sourceTab(Object tabLength) {
        add("-sourcetab");
        add(toArgumentString(tabLength));
        return this;
    }

    //    --spec-base-url
    //                  Specify a base URL for relative URLs in @spec tags
    //    -splitindex   Split index into one file per letter
    public JavadocOptions splitIndex() {
        add("-splitindex");
        return this;
    }

    //    -tag <name>:<locations>:<header>
    //                  Specify single argument custom tags
    //    -taglet       The fully qualified name of Taglet to register
    public JavadocOptions taglet(Object name) {
        add("-taglet");
        add(toArgumentString(name));
        return this;
    }

    //    -tagletpath   The path to Taglets
    public JavadocOptions tagletPath(Object path) {
        add("-tagletpath");
        add(toArgumentString(path));
        return this;
    }

    //    -top <html-code>
    //                  Include top text for each page
    public JavadocOptions top(Object htmlCode) {
        add("-top");
        add(toArgumentString(htmlCode));
        return this;
    }

    //    -use          Create class and package usage pages
    public JavadocOptions use() {
        add("-use");
        return this;
    }

    //    -version      Include @version paragraphs
    public JavadocOptions version() {
        add("-version");
        return this;
    }

    //    -windowtitle <text>
    //                  Browser window title for the documentation
    public JavadocOptions windowTitle(Object text) {
        add("-windowtitle");
        add(toArgumentString(text));
        return this;
    }
}
