package dev.mccue.tools.pmd;

import dev.mccue.tools.ToolArguments;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class PMDArguments extends ToolArguments {
    static String toArgumentString(Object o) {
        return o == null ? "" : o.toString();
    }

    public PMDArguments() {
        super();
    }

    public PMDArguments(Collection<? extends String> c) {
        super(c);
    }

    // Usage: pmd check [-bDh] [--no-cache] [--[no-]fail-on-error] [--[no-]
    //                 fail-on-violation] [--[no-]progress] [--show-suppressed]
    //                 [--aux-classpath=<auxClasspath>] [--cache=<cacheLocation>]
    //                 [-e=<encoding>] [-f=<format>] [--file-list=<fileListPath>]
    //                 [--force-language=<forceLanguage>]
    //                 [--ignore-list=<ignoreListPath>]
    //                 [--minimum-priority=<minimumPriority>] [-r=<reportFile>]
    //                 [--suppress-marker=<suppressMarker>] [-t=<threads>] [-u=<uri>]
    //                 [-P=<String=String>]... [--use-version=<languageVersion>]...
    //                 [-d=<inputPaths>[,<inputPaths>...]...]... -R=<rulesets>[,
    //                 <rulesets>...]... [-R=<rulesets>[,<rulesets>...]...]...
    //                 [-z=<relativizePathsWith>[,<relativizePathsWith>...]...]...
    //                 [<positionalInputPaths>...]
    //The PMD standard source code analyzer
    //      [<positionalInputPaths>...]
    //                             Path to a source file, or directory containing
    //                               source files to analyze. Equivalent to using
    //                               --dir.

    public PMDArguments positionalInputPaths(Object... paths) {
        return positionalInputPaths(Arrays.asList(paths));
    }


    public PMDArguments positionalInputPaths(List<?> paths) {
        paths.forEach(path -> add(toArgumentString(path)));
        return this;
    }

    //      --aux-classpath=<auxClasspath>
    //                             Specifies the classpath for libraries used by the
    //                               source code. This is used to resolve types in
    //                               Java source files. The platform specific path
    //                               delimiter (":" on Linux, ";" on Windows) is used
    //                               to separate the entries. Alternatively, a single
    //                               'file:' URL to a text file containing path
    //                               elements on consecutive lines can be specified.
    public PMDArguments __aux_classpath(Object... paths) {
        return __aux_classpath(Arrays.asList(paths));
    }

    public PMDArguments __aux_classpath(List<?> paths) {
        add("--aux-classpath");
        add(paths.stream().map(PMDArguments::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }
    //  -b, --benchmark            Benchmark mode - output a benchmark report upon
    //                               completion; default to System.err.
    public PMDArguments _b() {
        add("-b");
        return this;
    }

    public PMDArguments __benchmark() {
        add("--benchmark");
        return this;
    }
    //      --cache=<cacheLocation>
    //                             Specify the location of the cache file for
    //                               incremental analysis. This should be the full
    //                               path to the file, including the desired file
    //                               name (not just the parent directory). If the
    //                               file doesn't exist, it will be created on the
    //                               first run. The file will be overwritten on each
    //                               run with the most up-to-date rule violations.
    public PMDArguments __cache(Object cacheLocation) {
        add("--cache");
        add(toArgumentString(cacheLocation));
        return this;
    }

    //  -d, --dir=<inputPaths>[,<inputPaths>...]...
    //                             Path to a source file, or directory containing
    //                               source files to analyze. Zip and Jar files are
    //                               also supported, if they are specified directly
    //                               (archive files found while exploring a directory
    //                               are not recursively expanded). This option can
    //                               be repeated, and multiple arguments can be
    //                               provided to a single occurrence of the option.
    //                               One of --dir, --file-list or --uri must be
    //                               provided.
    public PMDArguments _d(Object... paths) {
        return _d(Arrays.asList(paths));
    }

    public PMDArguments _d(List<?> paths) {
        add("-d");
        add(paths.stream().map(PMDArguments::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

    public PMDArguments __dir(Object... paths) {
        return __dir(Arrays.asList(paths));
    }

    public PMDArguments __dir(List<?> paths) {
        add("--dir");
        add(paths.stream().map(PMDArguments::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

    //  -D, -v, --debug, --verbose Debug mode.
    public PMDArguments _D() {
        add("-D");
        return this;
    }

    public PMDArguments _v() {
        add("-v");
        return this;
    }

    public PMDArguments __debug() {
        add("--debug");
        return this;
    }

    public PMDArguments __verbose() {
        add("--verbose");
        return this;
    }

    //  -e, --encoding=<encoding>  Specifies the character set encoding of the source
    //                               code files
    //                               Default: UTF-8
    public PMDArguments _e(Object encoding) {
        add("-e");
        add(toArgumentString(encoding));
        return this;
    }

    public PMDArguments __encoding(Object encoding) {
        add("--encoding");
        add(toArgumentString(encoding));
        return this;
    }
    //  -f, --format=<format>      Report format.
    //                             Valid values: codeclimate, csv, emacs, empty,
    //                               html, ideaj, json, sarif, summaryhtml, text,
    //                               textcolor, textpad, vbhtml, xml, xslt, yahtml
    //                             Alternatively, you can provide the fully qualified
    //                               name of a custom Renderer in the classpath.
    //                               Default: text
    public PMDArguments _f(Object format) {
        add("-f");
        add(toArgumentString(format));
        return this;
    }

    public PMDArguments __format(Object format) {
        add("--format");
        add(toArgumentString(format));
        return this;
    }

    //      --file-list=<fileListPath>
    //                             Path to a file containing a list of files to
    //                               analyze, one path per line. One of --dir,
    //                               --file-list or --uri must be provided.
    public PMDArguments __file_list(Object fileListPath) {
        add("--file-list");
        add(toArgumentString(fileListPath));
        return this;
    }

    //      --force-language=<forceLanguage>
    //                             Force a language to be used for all input files,
    //                               irrespective of file names. When using this
    //                               option, the automatic language selection by
    //                               extension is disabled, and PMD tries to parse
    //                               all input files with the given language's
    //                               parser. Parsing errors are ignored.
    //                             Valid values: apex, ecmascript, html, java, jsp,
    //                               kotlin, modelica, plsql, pom, scala, swift,
    //                               velocity, visualforce, wsdl, xml, xsl
    public PMDArguments __force_language(Object forceLanguage) {
        add("--force-language");
        add(toArgumentString(forceLanguage));
        return this;
    }

    //  -h, --help                 Show this help message and exit.
    public PMDArguments _h() {
        add("-h");
        return this;
    }

    public PMDArguments __help() {
        add("--help");
        return this;
    }

    //      --ignore-list=<ignoreListPath>
    //                             Path to a file containing a list of files to
    //                               exclude from the analysis, one path per line.
    //                               This option can be combined with --dir,
    //                               --file-list and --uri.
    public PMDArguments __ignore_list(Object ignoreListPath) {
        add("--ignore-list");
        add(toArgumentString(ignoreListPath));
        return this;
    }

    //      --minimum-priority=<minimumPriority>
    //                             Rule priority threshold; rules with lower priority
    //                               than configured here won't be used.
    //                             Valid values (case insensitive): HIGH,
    //                               MEDIUM_HIGH, MEDIUM, MEDIUM_LOW, LOW
    //                               Default: Low
    public PMDArguments __minimum_priority(Object minimumPriority) {
        add("--minimum-priority");
        add(toArgumentString(minimumPriority));
        return this;
    }

    //      --no-cache             Explicitly disable incremental analysis. The
    //                               '-cache' option is ignored if this switch is
    //                               present in the command line.
    public PMDArguments __no_cache() {
        add("--no-cache");
        return this;
    }

    //      --[no-]fail-on-error   By default PMD exits with status 5 if recoverable
    //                               errors occurred (whether or not there are
    //                               violations or duplications). Disable this option
    //                               with '--no-fail-on-error' to exit with 0
    //                               instead. In any case, a report with the found
    //                               violations or duplications will be written.
    public PMDArguments __no_fail_on_error() {
        add("--no-fail-on-error");
        return this;
    }

    public PMDArguments __fail_on_error() {
        add("--fail-on-error");
        return this;
    }

    //      --[no-]fail-on-violation
    //                             By default PMD exits with status 4 if violations
    //                               or duplications are found. Disable this option
    //                               with '--no-fail-on-violation' to exit with 0
    //                               instead. In any case a report with the found
    //                               violations or duplications will be written.
    public PMDArguments __no_fail_on_violation() {
        add("--no-fail-on-violation");
        return this;
    }

    public PMDArguments __fail_on_violation() {
        add("--fail-on-violation");
        return this;
    }
    //      --[no-]progress        Enables / disables progress bar indicator of live
    //                               analysis progress.
    public PMDArguments __no_progress() {
        add("--no-progress");
        return this;
    }

    public PMDArguments __progress() {
        add("--progress");
        return this;
    }

    //  -P, --property=<String=String>
    //                             Key-value pair defining a property for the report
    //                               format.
    //                             Supported values for each report format:
    //                             csv:
    //                               problem - Include Problem column
    //                                 Default: true
    //                               package - Include Package column
    //                                 Default: true
    //                               file - Include File column
    //                                 Default: true
    //                               priority - Include Priority column
    //                                 Default: true
    //                               line - Include Line column
    //                                 Default: true
    //                               desc - Include Description column
    //                                 Default: true
    //                               ruleSet - Include Rule set column
    //                                 Default: true
    //                               rule - Include Rule column
    //                                 Default: true
    //                             html:
    //                               linkPrefix - Path to HTML source.
    //                               linePrefix - Prefix for line number anchor in
    //                               the source file.
    //                                 Default: Optional.empty
    //                               htmlExtension - Replace file extension with .
    //                               html for the links.
    //                                 Default: false
    //                             ideaj:
    //                               fileName - File name.
    //                               sourcePath - Source path.
    //                               classAndMethodName - Class and Method name, pass
    //                               '.method' when processing a directory.
    //                             summaryhtml:
    //                               linkPrefix - Path to HTML source.
    //                               linePrefix - Prefix for line number anchor in
    //                               the source file.
    //                                 Default: Optional.empty
    //                               htmlExtension - Replace file extension with .
    //                               html for the links.
    //                                 Default: false
    //                             xml:
    //                               encoding - XML encoding format
    //                                 Default: UTF-8
    //                             xslt:
    //                               encoding - XML encoding format
    //                                 Default: UTF-8
    //                               xsltFilename - The XSLT file name.
    //                             yahtml:
    //                               outputDir - Output directory.
    //                                 Default: .
    public PMDArguments _P(Object key, Object value) {
        add("-P");
        add(toArgumentString(key) + "=" + toArgumentString(value));
        return this;
    }

    public PMDArguments __property(Object key, Object value) {
        add("--property");
        add(toArgumentString(key) + "=" + toArgumentString(value));
        return this;
    }
    //  -r, --report-file=<reportFile>
    //                             Path to a file to which report output is written.
    //                               The file is created if it does not exist. If
    //                               this option is not specified, the report is
    //                               rendered to standard output.
    public PMDArguments _r(Object reportFile) {
        add("-r");
        add(toArgumentString(reportFile));
        return this;
    }

    public PMDArguments __report_file(Object reportFile) {
        add("--report-file");
        add(toArgumentString(reportFile));
        return this;
    }
    //  -R, --rulesets=<rulesets>[,<rulesets>...]...
    //                             Path to a ruleset xml file. The path may reference
    //                               a resource on the classpath of the application,
    //                               be a local file system path, or a URL. The
    //                               option can be repeated, and multiple arguments
    //                               separated by comma can be provided to a single
    //                               occurrence of the option.
    public PMDArguments _R(Object... rulesets) {
        return _R(Arrays.asList(rulesets));
    }

    public PMDArguments _R(List<?> rulesets) {
        add("-R");
        add(rulesets.stream().map(PMDArguments::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

    public PMDArguments __rulesets(Object... rulesets) {
        return __rulesets(Arrays.asList(rulesets));
    }

    public PMDArguments __rulesets(List<?> rulesets) {
        add("--rulesets");
        add(rulesets.stream().map(PMDArguments::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }
    //      --show-suppressed      Report should show suppressed rule violations.
    public PMDArguments __show_suppressed() {
        add("--show-suppressed");
        return this;
    }
    //      --suppress-marker=<suppressMarker>
    //                             Specifies the string that marks a line which PMD
    //                               should ignore.
    //                               Default: NOPMD
    public PMDArguments __suppress_marker(Object suppressMarker) {
        add("--suppress-marker");
        add(toArgumentString(suppressMarker));
        return this;
    }

    //  -t, --threads=<threads>    Sets the number of threads used by PMD.
    //                               Default: 1
    public PMDArguments _t(Object threads) {
        add("-t");
        add(toArgumentString(threads));
        return this;
    }

    public PMDArguments __threads(Object threads) {
        add("--threads");
        add(toArgumentString(threads));
        return this;
    }
    //  -u, --uri=<uri>            Database URI for sources. One of --dir,
    //                               --file-list or --uri must be provided.
    public PMDArguments _u(Object uri) {
        add("-u");
        add(toArgumentString(uri));
        return this;
    }

    public PMDArguments __uri(Object uri) {
        add("--uri");
        add(toArgumentString(uri));
        return this;
    }
    //      --use-version=<languageVersion>
    //                             The language version PMD should use when parsing
    //                               source code.
    //                             Valid values: apex-52, apex-53, apex-54, apex-55,
    //                               apex-56, apex-57, apex-58, apex-59, apex-60,
    //                               ecmascript-3, ecmascript-5, ecmascript-6,
    //                               ecmascript-7, ecmascript-8, ecmascript-9,
    //                               ecmascript-ES2015, ecmascript-ES2016,
    //                               ecmascript-ES2017, ecmascript-ES2018,
    //                               ecmascript-ES6, html-4, html-5, java-1.10,
    //                               java-1.3, java-1.4, java-1.5, java-1.6, java-1.
    //                               7, java-1.8, java-1.9, java-10, java-11,
    //                               java-12, java-13, java-14, java-15, java-16,
    //                               java-17, java-18, java-19, java-20, java-21,
    //                               java-21-preview, java-22, java-22-preview,
    //                               java-5, java-6, java-7, java-8, java-9, jsp-2,
    //                               jsp-3, kotlin-1.6, kotlin-1.7, kotlin-1.8,
    //                               modelica-3.4, modelica-3.5, plsql-11g, plsql-12.
    //                               1, plsql-12.2, plsql-12c_Release_1,
    //                               plsql-12c_Release_2, plsql-18c, plsql-19c,
    //                               plsql-21c, pom-4.0.0, scala-2.10, scala-2.11,
    //                               scala-2.12, scala-2.13, swift-4.2, swift-5.0,
    //                               swift-5.1, swift-5.2, swift-5.3, swift-5.4,
    //                               swift-5.5, swift-5.6, swift-5.7, swift-5.8,
    //                               swift-5.9, velocity-2.0, velocity-2.1,
    //                               velocity-2.2, velocity-2.3, visualforce-52,
    //                               visualforce-53, visualforce-54, visualforce-55,
    //                               visualforce-56, visualforce-57, visualforce-58,
    //                               visualforce-59, visualforce-60, wsdl-1.1, wsdl-2.
    //                               0, xml-1.0, xml-1.1, xsl-1.0, xsl-2.0, xsl-3.0
    public PMDArguments __use_version(Object languageVersion) {
        add("--use-version");
        add(toArgumentString(languageVersion));
        return this;
    }

    //  -z, --relativize-paths-with=<relativizePathsWith>[,
    //        <relativizePathsWith>...]...
    //                             Path relative to which directories are rendered in
    //                               the report. This option allows shortening
    //                               directories in the report; without it, paths are
    //                               rendered as mentioned in the source directory
    //                               (option "--dir"). The option can be repeated, in
    //                               which case the shortest relative path will be
    //                               used. If the root path is mentioned (e.g. "/" or
    //                               "C:\"), then the paths will be rendered as
    //                               absolute.
    public PMDArguments _z(Object... relativizePathsWith) {
        return _z(Arrays.asList(relativizePathsWith));
    }

    public PMDArguments _z(List<?> relativizePathsWith) {
        add("-z");
        add(relativizePathsWith.stream().map(PMDArguments::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

    public PMDArguments __relativize_paths_with(Object... relativizePathsWith) {
        return __relativize_paths_with(Arrays.asList(relativizePathsWith));
    }

    public PMDArguments __relativize_paths_with(List<?> relativizePathsWith) {
        add("--relativize-paths-with");
        add(relativizePathsWith.stream().map(PMDArguments::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

    // Usage: pmd cpd [-Dh] [--ignore-annotations] [--ignore-identifiers]
    //               [--ignore-literal-sequences] [--ignore-literals]
    //               [--ignore-sequences] [--ignore-usings] [--[no-]fail-on-error] [--
    //               [no-]fail-on-violation] [--no-skip-blocks] [--non-recursive]
    //               [--skip-duplicate-files] [--skip-lexical-errors] [-e=<encoding>]
    //               [-f=<rendererName>] [--file-list=<fileListPath>] [-l=<language>]
    //               --minimum-tokens=<minimumTokens>
    //               [--skip-blocks-pattern=<skipBlocksPattern>] [-u=<uri>]
    //               [-d=<inputPaths>[,<inputPaths>...]...]...
    //               [--exclude=<excludes>...]... [-z=<relativizePathsWith>[,
    //               <relativizePathsWith>...]...]... [<positionalInputPaths>...]
    public PMDArguments cpd() {
        add("cpd");
        return this;
    }

    //Copy/Paste Detector - find duplicate code



    //      --exclude=<excludes>...
    //                             Files to be excluded from the analysis
    //                               Default: []


    //      --ignore-annotations   Ignore language annotations when comparing text.
    public PMDArguments __ignore_annotations() {
        add("--ignore-annotations");
        return this;
    }
    //      --ignore-identifiers   Ignore names of classes, methods, variables,
    //                               constants, etc. when comparing text.
    public PMDArguments __ignore_identifiers() {
        add("--ignore-identifiers");
        return this;
    }
    //      --ignore-literal-sequences
    //                             Ignore sequences of literals such as list
    //                               initializers.
    public PMDArguments __ignore_literal_sequences() {
        add("---ignore-literal-sequences");
        return this;
    }
    //      --ignore-literals      Ignore literal values such as numbers and strings
    //                               when comparing text.
    public PMDArguments __ignore_literals() {
        add("---ignore-literals");
        return this;
    }
    //      --ignore-sequences     Ignore sequences of identifiers and literals
    public PMDArguments __ignore_sequences() {
        add("---ignore-sequences");
        return this;
    }
    //      --ignore-usings        Ignore using directives in C#
    public PMDArguments __ignore_usings() {
        add("---ignore-usings");
        return this;
    }
    //  -l, --language=<language>  The source code language.
    //                             Valid values: apex, coco, cpp, cs, dart,
    //                               ecmascript, fortran, gherkin, go, groovy, html,
    //                               java, jsp, julia, kotlin, lua, matlab, modelica,
    //                               objectivec, perl, php, plsql, pom, python, ruby,
    //                               scala, swift, tsql, typescript, velocity,
    //                               visualforce, wsdl, xml, xsl
    //                               Default: java
    public PMDArguments _l(Object language) {
        add("-l");
        add(toArgumentString(language));
        return this;
    }

    public PMDArguments __language(Object language) {
        add("--language");
        add(toArgumentString(language));
        return this;
    }

    //      --minimum-tokens=<minimumTokens>
    //                             The minimum token length which should be reported
    //                               as a duplicate.
    //                               Default: 0
    public PMDArguments __minimum_tokens(Object minimumTokens) {
        add("--minimum-tokens");
        add(toArgumentString(minimumTokens));
        return this;
    }


    //      --no-skip-blocks       Do not skip code blocks marked with
    //                               --skip-blocks-pattern (e.g. #if 0 until #endif).
    public PMDArguments __no_skip_blocks() {
        add("--no-skip-blocks");
        return this;
    }

    //      --non-recursive        Don't scan subdirectiories.
    public PMDArguments __non_recursive() {
        add("--non-recursive");
        return this;
    }

    //      --skip-blocks-pattern=<skipBlocksPattern>
    //                             Pattern to find the blocks to skip. Start and End
    //                               pattern separated by |.
    //                               Default: #if 0|#endif
    public PMDArguments __skip_blocks_pattern(Object skipBlocksPattern) {
        add("--skip-blocks-pattern");
        add(toArgumentString(skipBlocksPattern));
        return this;
    }

    //      --skip-duplicate-files Ignore multiple copies of files of the same name
    //                               and length in comparison.
    public PMDArguments __skip_duplicate_files() {
        add("--skip-duplicate-files");
        return this;
    }

    //      --skip-lexical-errors  Skip files which can't be tokenized due to invalid
    //                               characters, instead of aborting with an error.
    //                               Deprecated - use --[no-]fail-on-error instead.
    public PMDArguments __skip_lexical_errors() {
        add("--skip-lexical-errors");
        return this;
    }
}
