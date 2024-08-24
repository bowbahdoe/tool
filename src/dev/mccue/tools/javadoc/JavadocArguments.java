package dev.mccue.tools.javadoc;

import dev.mccue.tools.ToolArguments;
import dev.mccue.tools.java.JavaArguments;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public final class JavadocArguments extends ToolArguments {
    static String toArgumentString(Object o) {
        return o == null ? "" : o.toString();
    }

    public JavadocArguments() {
        super();
    }

    public JavadocArguments(Collection<? extends String> c) {
        super(c);
    }

    // Usage:
    //    javadoc [options] [packagenames] [sourcefiles] [@files]
    //where options include:
    public JavadocArguments packageNames(Object... packageNames) {
        return sourceFiles(Arrays.asList(packageNames));
    }

    public JavadocArguments packageNames(List<?> packageNames) {
        packageNames.forEach(file -> add(toArgumentString(file)));
        return this;
    }

    public JavadocArguments sourceFiles(Object... sourceFiles) {
        return sourceFiles(Arrays.asList(sourceFiles));
    }

    public JavadocArguments sourceFiles(List<?> sourceFiles) {
        sourceFiles.forEach(file -> add(toArgumentString(sourceFiles)));
        return this;
    }

    //    @<file>       Read options and filenames from file
    public JavadocArguments argumentFile(Object filename) {
        add("@" + toArgumentString(filename));
        return this;
    }

    //    --add-modules <module>(,<module>)*
    //                  Root modules to resolve in addition to the initial modules,
    //                  or all modules on the module path if <module> is
    //                  ALL-MODULE-PATH.
    public JavadocArguments addModules(Object... path) {
        return addModules(Arrays.asList(path));
    }

    public JavadocArguments addModules(List<?> path) {
        add("--add-modules");
        add(path.stream().map(JavadocArguments::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

    //    -bootclasspath <path>
    //                  Override location of platform class files used for non-modular
    //                  releases
    public JavadocArguments bootClassPath(Object... path) {
        return bootClassPath(Arrays.asList(path));
    }

    public JavadocArguments bootClassPath(List<?> path) {
        add("--boot-class-path");
        add(path.stream().map(JavadocArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    //    -breakiterator
    //                  Compute first sentence with BreakIterator
    public JavadocArguments _breakiterator() {
        add("-breakiterator");
        return this;
    }

    //    --class-path <path>, -classpath <path>, -cp <path>
    //                  Specify where to find user class files
    public JavadocArguments __class_path(Object... path) {
        return __class_path(Arrays.asList(path));
    }

    public JavadocArguments __class_path(List<?> path) {
        add("--class-path");
        add(path.stream().map(JavadocArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    public JavadocArguments _classpath(Object... path) {
        return __class_path(Arrays.asList(path));
    }

    public JavadocArguments _classpath(List<?> path) {
        add("-classpath");
        add(path.stream().map(JavadocArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    public JavadocArguments _cp(Object... path) {
        return _cp(Arrays.asList(path));
    }

    public JavadocArguments _cp(List<?> path) {
        add("-cp");
        add(path.stream().map(JavadocArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    //    -doclet <class>
    //                  Generate output via alternate doclet
    public JavadocArguments _doclet(Object klass) {
        add("-doclet");
        add(toArgumentString(klass));
        return this;
    }

    //    -docletpath <path>
    //                  Specify where to find doclet class files
    public JavadocArguments _docletpath(Object path) {
        add("-docletpath");
        add(toArgumentString(path));
        return this;
    }

    //    --enable-preview
    //                  Enable preview language features. To be used in conjunction with
    //                  either -source or --release.
    public JavadocArguments __enable_preview() {
        add("--enable-preview");
        return this;
    }

    //    -encoding <name>
    //                  Source file encoding name
    public JavadocArguments _encoding(Object name) {
        add("-encoding");
        add(toArgumentString(name));
        return this;
    }

    //    -exclude <pkglist>
    //                  Specify a list of packages to exclude
    public JavadocArguments _exclude(Object... pkglist) {
        return _exclude(Arrays.asList(pkglist));
    }

    public JavadocArguments _exclude(List<?> pkglist) {
        add(pkglist.stream().map(JavadocArguments::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

    //    --expand-requires <value>
    //                  Instructs the tool to expand the set of modules to be
    //                  documented. By default, only the modules given explicitly on
    //                  the command line will be documented. A value of "transitive"
    //                  will additionally include all "requires transitive"
    //                  dependencies of those modules. A value of "all" will include
    //                  all dependencies of those modules.
    public JavadocArguments __expand_requires(Object value) {
        add("--expand-requires");
        add(toArgumentString(value));
        return this;
    }

    //    -extdirs <dirlist>
    //                  Override location of installed extensions
    public JavadocArguments _extdirs(Object... dirlist) {
        return _extdirs(Arrays.asList(dirlist));
    }

    public JavadocArguments _extdirs(List<?> dirlist) {
        add(dirlist.stream().map(JavadocArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    //    --help, -help, -?, -h
    //                  Display command-line options and exit
    public JavadocArguments __help() {
        add("--help");
        return this;
    }

    public JavadocArguments _help() {
        add("-help");
        return this;
    }

    public JavadocArguments _h() {
        add("-h");
        return this;
    }
    //    --help-extra, -X
    //                  Print a synopsis of nonstandard options and exit
    public JavadocArguments __help_extra() {
        add("--help-extra");
        return this;
    }

    public JavadocArguments _X() {
        add("-X");
        return this;
    }

    //    -J<flag>      Pass <flag> directly to the runtime system
    public JavadocArguments _J(Object flag) {
        add("-J" + toArgumentString(flag));
        return this;
    }

    public JavadocArguments _J(Consumer<JavaArguments> consumer) {
        var opts = new JavaArguments();
        consumer.accept(opts);
        opts.forEach(opt -> add("-J" + opt));
        return this;
    }

    //    --limit-modules <module>(,<module>)*
    //                  Limit the universe of observable modules
    public JavadocArguments __limit_modules(Object... dirlist) {
        return __limit_modules(Arrays.asList(dirlist));
    }

    public JavadocArguments __limit_modules(List<?> dirlist) {
        add(dirlist.stream().map(JavadocArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    //    -locale <name>
    //                  Locale to be used, e.g. en_US or en_US_WIN
    public JavadocArguments _locale(Object name) {
        add(toArgumentString(name));
        return this;
    }

    //    --module <module>(,<module>)*
    //                  Document the specified module(s)
    public JavadocArguments __module(Object... modules) {
        return __module(Arrays.asList(modules));
    }

    public JavadocArguments __module(List<?> modules) {
        add(modules.stream().map(JavadocArguments::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

    //    --module-path <path>, -p <path>
    //                  Specify where to find application modules
    public JavadocArguments __module_path(Object... paths) {
        return __module_path(Arrays.asList(paths));
    }

    public JavadocArguments __module_path(List<?> paths) {
        add(paths.stream().map(JavadocArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    //    --module-source-path <path>
    //                  Specify where to find input source files for multiple modules
    public JavadocArguments __module_source_path(Object... paths) {
        return __module_source_path(Arrays.asList(paths));
    }

    public JavadocArguments __module_source_path(List<?> paths) {
        add(paths.stream().map(JavadocArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    //    -package
    //                  Show package/protected/public types and members. For
    //                  named modules, show all packages and all module details.
    public JavadocArguments _package() {
        add("-package");
        return this;
    }

    //    -private
    //                  Show all types and members. For named modules,
    //                  show all packages and all module details.
    public JavadocArguments _private() {
        add("-private");
        return this;
    }

    //    -protected
    //                  Show protected/public types and members (default). For
    //                  named modules, show exported packages and the module's API.
    public JavadocArguments _protected() {
        add("-protected");
        return this;
    }

    //    -public
    //                  Show only public types and members. For named modules,
    //                  show exported packages and the module's API.
    public JavadocArguments _public() {
        add("-public");
        return this;
    }

    //    -quiet        Do not display status messages
    public JavadocArguments _quiet() {
        add("-quiet");
        return this;
    }

    //    --release <release>
    //                  Provide source compatibility with specified release
    public JavadocArguments __release() {
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
    public enum ShowMembers {
        PUBLIC("public"),
        PROTECTED("protected"),
        PACKAGE("package"),
        PRIVATE("private");

        final String value;

        ShowMembers(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public JavadocArguments __show_members(ShowMembers showMembers) {
        add("--show-members");
        add(toArgumentString(showMembers));
        return this;
    }

    //    --show-module-contents <value>
    //                  Specifies the documentation granularity of module
    //                  declarations. Possible values are "api" or "all".
    public enum ShowModuleContents {
        API("api"),
        ALL("all");

        final String value;

        ShowModuleContents(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public JavadocArguments __show_module_contents(ShowModuleContents showModuleContents) {
        add("--show-module-contents");
        add(toArgumentString(showModuleContents));
        return this;
    }

    //    --show-packages <value>
    //                  Specifies which modules packages will be documented. Possible
    //                  values are "exported" or "all" packages.
    public enum ShowPackages {
        EXPORTED("exported"),
        ALL("all");

        final String value;

        ShowPackages(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public JavadocArguments __show_packages(ShowPackages value) {
        add("--show-packages");
        add(toArgumentString(value));
        return this;
    }

    //    --show-types <value>
    //                  Specifies which types (classes, interfaces, etc.) will be
    //                  documented, where value can be one of "public", "protected",
    //                  "package" or "private". The default is "protected", which will
    //                  show public and protected types, "public" will show only
    //                  public types, "package" will show public, protected and
    //                  package types and "private" will show all types.
    public enum ShowTypes {
        PUBLIC("public"),
        PROTECTED("protected"),
        PACKAGE("package"),
        PRIVATE("private");

        final String value;

        ShowTypes(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public JavadocArguments __show_types(ShowTypes value) {
        add("--show-types");
        add(toArgumentString(value));
        return this;
    }
    //    --source <release>, -source <release>
    //                  Provide source compatibility with specified release
    public JavadocArguments source(Object release) {
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
    public JavadocArguments __system(Object jdk) {
        add("--system");
        add(toArgumentString(jdk));
        return this;
    }

    //    --upgrade-module-path <path>
    //                  Override location of upgradeable modules
    public JavadocArguments __upgrade_module_path(Object path) {
        add("--upgrade-module-path");
        add(toArgumentString(path));
        return this;
    }

    //    -verbose      Output messages about what Javadoc is doing
    public JavadocArguments _verbose() {
        add("-verbose");
        return this;
    }

    //    --version     Print version information
    public JavadocArguments __version() {
        add("--version");
        return this;
    }

    //    -Werror       Report an error if any warnings occur
    public JavadocArguments _Werror() {
        add("-Werror");
        return this;
    }

    //
    //Provided by the Standard doclet:
    //    --add-script <file>
    //                  Add a script file to the generated documentation
    public JavadocArguments __add_script(Object file) {
        add("--add-script");
        add(toArgumentString(file));
        return this;
    }

    //    --add-stylesheet <file>
    //                  Add a stylesheet file to the generated documentation
    public JavadocArguments __add_stylesheet(Object file) {
        add("--add-stylesheet");
        add(toArgumentString(file));
        return this;
    }

    //    --allow-script-in-comments
    //                  Allow JavaScript in options and comments
    public JavadocArguments __allow_script_in_comments() {
        add("--allow-script-in-comments");
        return this;
    }

    //    -author       Include @author paragraphs
    public JavadocArguments _author() {
        add("-author");
        return this;
    }

    //    -bottom <html-code>
    //                  Include bottom text for each page
    public JavadocArguments _bottom(Object htmlCode) {
        add("-bottom");
        add(toArgumentString(htmlCode));
        return this;
    }

    //    -charset <charset>
    //                  Charset for cross-platform viewing of generated documentation
    public JavadocArguments _charset(Object charset) {
        add("-charset");
        add(toArgumentString(charset));
        return this;
    }

    //    -d <directory>
    //                  Destination directory for output files
    public JavadocArguments _d(Object directory) {
        add("-d");
        add(toArgumentString(directory));
        return this;
    }

    //    -docencoding <name>
    //                  Specify the character encoding for the output
    public JavadocArguments docencoding(Object name) {
        add("-docencoding");
        add(toArgumentString(name));
        return this;
    }
    //    -docfilessubdirs
    //                  Recursively copy doc-file subdirectories
    public JavadocArguments _docfilessubdirs() {
        add("-docfilessubdirs");
        return this;
    }

    //    -doctitle <html-code>
    //                  Include title for the overview page
    public JavadocArguments _doctitle(Object htmlCode) {
        add("-doctitle");
        add(toArgumentString(htmlCode));
        return this;
    }

    //    -excludedocfilessubdir <name>,<name>,...
    //                  Exclude any doc-files subdirectories with given name.
    //                  ':' can also be used anywhere in the argument as a separator.
    public JavadocArguments _excludedocfilessubdir(Object... names) {
        return _excludedocfilessubdir(Arrays.asList(names));
    }

    public JavadocArguments _excludedocfilessubdir(List<?> names) {
        names.stream().map(JavadocArguments::toArgumentString).collect(Collectors.joining(","));
        return this;
    }

    //    -footer <html-code>
    //                  Include footer text for each page
    public JavadocArguments _footer(Object htmlCode) {
        add("-footer");
        add(toArgumentString(htmlCode));
        return this;
    }

    //    -group <name> <g1>,<g2>...
    //                  Group specified elements together in overview page.
    //                  ':' can also be used anywhere in the argument as a separator.
    public JavadocArguments _group(Object name, Object... groups) {
        return _group(name, Arrays.asList(groups));
    }

    public JavadocArguments _group(Object name, List<?> groups) {
        add("-group");
        add(toArgumentString(name));
        add(groups.stream().map(JavadocArguments::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

    //    -header <html-code>
    //                  Include header text for each page
    public JavadocArguments _header(Object htmlCode) {
        add("-header");
        add(toArgumentString(htmlCode));
        return this;
    }

    //    -helpfile <file>
    //                  Include file that help link links to
    public JavadocArguments _helpfile(Object file) {
        add("-helpfile");
        add(toArgumentString(file));
        return this;
    }

    //    -html5        Generate HTML 5 output. This option is no longer required.
    //    --javafx, -javafx
    //                  Enable JavaFX functionality
    //    -keywords     Include HTML meta tags with package, class and member info
    public JavadocArguments _keywords() {
        add("-keywords");
        return this;
    }

    //    -link <url>   Create links to javadoc output at <url>
    public JavadocArguments _link(Object url) {
        add("-link");
        add(toArgumentString(url));
        return this;
    }

    //    --link-modularity-mismatch (warn|info)
    //                  Report external documentation with wrong modularity with either
    //                  a warning or informational message. The default behaviour is to
    //                  report a warning.
    public enum LinkModularityMismatch {
        WARN("warn"),
        INFO("info");

        final String value;

        LinkModularityMismatch(String value) {
            this.value = value;
        }
    }

    public JavadocArguments __link_modularity_mismatch(LinkModularityMismatch linkModularityMismatch) {
        add("--link-modularity-mismatch");
        add(linkModularityMismatch.value);
        return this;
    }

    //    -linkoffline <url1> <url2>
    //                  Link to docs at <url1> using package list at <url2>
    public JavadocArguments _linkoffline(Object url1, Object url2) {
        add("-linkoffline");
        add(toArgumentString(url1));
        add(toArgumentString(url2));
        return this;
    }

    //    --link-platform-properties <url>
    //                  Link to platform documentation URLs declared in properties file at <url>
    //    -linksource   Generate source in HTML
    //    --main-stylesheet <file>, -stylesheetfile <file>
    //                  File to change style of the generated documentation
    public JavadocArguments __main_stylesheet(Object file) {
        add("--main-stylesheet");
        add(toArgumentString(file));
        return this;
    }

    public JavadocArguments _stylesheetfile(Object file) {
        add("-stylesheetfile");
        add(toArgumentString(file));
        return this;
    }

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
    public JavadocArguments __no_platform_links(Object... names) {
        add("--no-platform-links");
        return this;
    }

    //    -noqualifier <name1>,<name2>,...
    //                  Exclude the list of qualifiers from the output.
    //                  ':' can also be used anywhere in the argument as a separator.
    public JavadocArguments _noqualifier(Object... names) {
        return _noqualifier(Arrays.asList(names));
    }

    public JavadocArguments _noqualifier(List<?> names) {
        add("-noqualifier");
        add(names.stream().map(JavadocArguments::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }
    //    -nosince      Do not include @since information
    public JavadocArguments _nosince() {
        add("-nosince");
        return this;
    }

    //    -notimestamp  Do not include hidden time stamp
    public JavadocArguments _notimestamp() {
        add("-notimestamp");
        return this;
    }

    //    -notree       Do not generate class hierarchy
    public JavadocArguments _notree() {
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

    public JavadocArguments __override_methods(OverrideMethods overrideMethods) {
        add("--override-methods");
        add(overrideMethods.value);
        return this;
    }

    //    -overview <file>
    //                  Read overview documentation from HTML file
    public JavadocArguments _overview(Object file) {
        add("-overview");
        add(toArgumentString(file));
        return this;
    }

    //    -serialwarn   Generate warning about @serial tag
    public JavadocArguments _serialwarn() {
        add("-serialwarn");
        return this;
    }

    //    --since <release>(,<release>)*
    //                  Document new and deprecated API in the specified releases
    public JavadocArguments __since(Object... releases) {
        return __since(Arrays.asList(releases));
    }

    public JavadocArguments __since(List<?> releases) {
        add(releases.stream().map(JavadocArguments::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

    //    --since-label <text>
    //                  Provide text to use in the heading of the "New API" page
    public JavadocArguments __since_label(Object text) {
        add("--since-label");
        add(toArgumentString(text));
        return this;
    }

    //    --snippet-path <path>
    //                  The path for external snippets
    public JavadocArguments __snippet_path(Object path) {
        add("--snippet-path");
        add(toArgumentString(path));
        return this;
    }

    //    -sourcetab <tab length>
    //                  Specify the number of spaces each tab takes up in the source
    public JavadocArguments _sourcetab(Object tabLength) {
        add("-sourcetab");
        add(toArgumentString(tabLength));
        return this;
    }

    //    --spec-base-url
    //                  Specify a base URL for relative URLs in @spec tags
    //    -splitindex   Split index into one file per letter
    public JavadocArguments _splitindex() {
        add("-splitindex");
        return this;
    }

    //    -tag <name>:<locations>:<header>
    //                  Specify single argument custom tags
    public JavadocArguments _tag(Object name, Object locations, Object header) {
        add("-tag");
        add(toArgumentString(name) + ":" + toArgumentString(locations) + ":" + toArgumentString(header));
        return this;
    }

    //    -taglet       The fully qualified name of Taglet to register
    public JavadocArguments _taglet(Object name) {
        add("-taglet");
        add(toArgumentString(name));
        return this;
    }

    //    -tagletpath   The path to Taglets
    public JavadocArguments _tagletpath(Object path) {
        add("-tagletpath");
        add(toArgumentString(path));
        return this;
    }

    //    -top <html-code>
    //                  Include top text for each page
    public JavadocArguments _top(Object htmlCode) {
        add("-top");
        add(toArgumentString(htmlCode));
        return this;
    }

    //    -use          Create class and package usage pages
    public JavadocArguments _use() {
        add("-use");
        return this;
    }

    //    -version      Include @version paragraphs
    public JavadocArguments _version() {
        add("-version");
        return this;
    }

    //    -windowtitle <text>
    //                  Browser window title for the documentation
    public JavadocArguments _windowtitle(Object text) {
        add("-windowtitle");
        add(toArgumentString(text));
        return this;
    }
}
