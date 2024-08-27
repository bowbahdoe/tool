module dev.mccue.tools.jlink {
    requires jdk.jlink;
    requires transitive dev.mccue.tools;
    requires transitive dev.mccue.tools.java;

    exports dev.mccue.tools.jlink;
}