package dev.mccue.tools.jlink;

import dev.mccue.tools.ToolOptions;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JLinkOptions extends ToolOptions {
    static String toArgumentString(Object o) {
        return o == null ? "" : o.toString();
    }
    // Usage: jlink <options> --module-path <modulepath> --add-modules <module>[,<module>...]
//Possible options include:
//      --add-modules <mod>[,<mod>...]    Root modules to resolve in addition to the
//                                        initial modules. <mod> can also be ALL-MODULE-PATH.
    public JLinkOptions addModules(Object... path) {
        return addModules(Arrays.asList(path));
    }

    public JLinkOptions addModules(List<?> path) {
        add("--add-modules");
        add(path.stream().map(JLinkOptions::toArgumentString).collect(Collectors.joining(File.pathSeparator)));
        return this;
    }

    //      --bind-services                   Link in service provider modules and
    //                                        their dependences
    public JLinkOptions bindServices() {
        add("--bind-services");
        return this;
    }

    //      --compress <compress>             Compression to use in compressing resources:
//                                        Accepted values are:
//                                        zip-[0-9], where zip-0 provides no compression,
//                                        and zip-9 provides the best compression.
//                                        Default is zip-6.
//                                        Deprecated values to be removed in a future release:
//                                        0:  No compression. Equivalent to zip-0.
//                                        1:  Constant String Sharing
//                                        2:  Equivalent to zip-6.
    public JLinkOptions compress(Object value) {
        add("--compress");
        add(toArgumentString(value));
        return this;
    }

    //      --disable-plugin <pluginname>     Disable the plugin mentioned
    public JLinkOptions disablePlugin(Object pluginName) {
        add("--disable-plugin");
        add(toArgumentString(pluginName));
        return this;
    }

    //      --endian <little|big>             Byte order of generated jimage
    //                                        (default:native)
    public enum Endianness {
        LITTLE,
        BIG,
        NATIVE
    }

    public JLinkOptions endian(Endianness endianness) {
        switch (endianness) {
            case LITTLE -> {
                add("--endian");
                add("little");
            }
            case BIG -> {
                add("--endian");
                add("big");
            }
            case NATIVE -> {}
        }

        return this;
    }


//  -h, --help, -?                        Print this help message
    public JLinkOptions h() {
        add("-h");
        return this;
    }

    public JLinkOptions help() {
        add("--help");
        return this;
    }

//      --ignore-signing-information      Suppress a fatal error when signed
//                                        modular JARs are linked in the image.
//                                        The signature related files of the
//                                        signed modular JARs are not copied to
//                                        the runtime image.
    public JLinkOptions ignoreSigningInformation() {
        add("--ignore-signing-information");
        return this;
    }

//      --launcher <name>=<module>[/<mainclass>]
//                                        Add a launcher command of the given
//                                        name for the module and the main class
//                                        if specified
    public JLinkOptions launcher(Object name, Object module) {
        add("--launcher");
        add(toArgumentString(name) + "=" + toArgumentString(module));
        return this;
    }

    public JLinkOptions launcher(Object name, Object module, Object mainClass) {
        add("--launcher");
        add(toArgumentString(name) + "=" + toArgumentString(module) + "/" + toArgumentString(mainClass));
        return this;
    }

//      --limit-modules <mod>[,<mod>...]  Limit the universe of observable
//                                        modules
    public JLinkOptions limitModules(Object... modules) {
        return limitModules(Arrays.asList(modules));
    }

    public JLinkOptions limitModules(List<?> modules) {
        add("--limit-modules");
        add(modules.stream().map(JLinkOptions::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }
//      --list-plugins                    List available plugins
    public JLinkOptions linkPlugins() {
        add("--list-plugins");
        return this;
    }

//  -p, --module-path <path>              Module path.
//                                        If not specified, the JDKs jmods directory
//                                        will be used, if it exists. If specified,
//                                        but it does not contain the java.base module,
//                                        the JDKs jmods directory will be added,
//                                        if it exists.
    public JLinkOptions modulePath(Object... modules) {
        return modulePath(Arrays.asList(modules));
    }

    public JLinkOptions modulePath(List<?> modules) {
        add("--module-path");
        add(modules.stream().map(JLinkOptions::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }

    public JLinkOptions p(Object... modules) {
        return p(Arrays.asList(modules));
    }

    public JLinkOptions p(List<?> modules) {
        add("-p");
        add(modules.stream().map(JLinkOptions::toArgumentString).collect(Collectors.joining(",")));
        return this;
    }
//      --no-header-files                 Exclude include header files
    public JLinkOptions noHeaderFiles() {
        add("--no-header-files");
        return this;
    }

//      --no-man-pages                    Exclude man pages
    public JLinkOptions noManPages() {
        add("--no-man-pages");
        return this;
    }

//      --output <path>                   Location of output path
    public JLinkOptions output(Object path) {
        add("--output");
        add(toArgumentString(path));
        return this;
    }

//      --save-opts <filename>            Save jlink options in the given file
    public JLinkOptions saveOpts(Object filename) {
        add("--save-opts");
        add(toArgumentString(filename));
        return this;
    }
//  -G, --strip-debug                     Strip debug information
    public JLinkOptions G() {
        add("-G");
        return this;
    }

    public JLinkOptions stripDebug() {
        add("--strip-debug");
        return this;
    }

//      --suggest-providers [<name>,...]  Suggest providers that implement the
//                                        given service types from the module path
//  -v, --verbose                         Enable verbose tracing
    public JLinkOptions v() {
        add("-v");
        return this;
    }

    public JLinkOptions verbose() {
        add("--verbose");
        return this;
    }

//      --version                         Version information
    public JLinkOptions version() {
        add("--version");
        return this;
    }

//      @<filename>                       Read options from file
}
