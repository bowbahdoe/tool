module dev.mccue.tools.javadoc {
    requires jdk.javadoc;
    requires transitive dev.mccue.tools;
    requires transitive dev.mccue.tools.java;

    exports dev.mccue.tools.javadoc;
}