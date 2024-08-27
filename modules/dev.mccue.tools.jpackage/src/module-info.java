module dev.mccue.tools.jpackage {
    requires jdk.jpackage;
    requires transitive dev.mccue.tools;
    requires transitive dev.mccue.tools.jlink;

    exports dev.mccue.tools.jpackage;
}