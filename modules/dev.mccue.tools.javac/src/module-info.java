module dev.mccue.tools.javac {
    requires jdk.compiler;
    requires transitive dev.mccue.tools;
    requires transitive dev.mccue.tools.java;

    exports dev.mccue.tools.javac;
}