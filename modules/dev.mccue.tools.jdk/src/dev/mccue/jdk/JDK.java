package dev.mccue.jdk;

import dev.mccue.tools.Tool;

import java.nio.file.Files;
import java.nio.file.Path;

public interface JDK {
    interface Tools {
        Tool java();
        Tool javac();
        Tool javadoc();
        Tool javap();
        Tool jlink();
        Tool jmod();
        Tool jpackage();
    }

    Tools tools();

    Path jmods();

    static JDK ofDirectory(Path directory) {
        if (Files.exists(Path.of(directory.toString(), "Contents"))) {
            // We are looking at a Mac JDK
            directory = Path.of(directory.toString(), "Contents", "Home");
        }

        record JDKImpl(Path bin, Path jmods) implements JDK {
            record Tools(Path bin) implements JDK.Tools {
                @Override
                public Tool java() {
                    return Tool.ofSubprocess(
                            Path.of(bin.toString(), "java").toString()
                    );
                }

                @Override
                public Tool javac() {
                    return Tool.ofSubprocess(
                            Path.of(bin.toString(), "javac").toString()
                    );
                }

                @Override
                public Tool javadoc() {
                    return Tool.ofSubprocess(
                            Path.of(bin.toString(), "javadoc").toString()
                    );
                }

                @Override
                public Tool javap() {
                    return Tool.ofSubprocess(
                            Path.of(bin.toString(), "javap").toString()
                    );
                }

                @Override
                public Tool jlink() {
                    return Tool.ofSubprocess(
                            Path.of(bin.toString(), "jlink").toString()
                    );
                }

                @Override
                public Tool jmod() {
                    return Tool.ofSubprocess(
                            Path.of(bin.toString(), "jmod").toString()
                    );
                }

                @Override
                public Tool jpackage() {
                    return Tool.ofSubprocess(
                            Path.of(bin.toString(), "jpackage").toString()
                    );
                }
            }

            public JDK.Tools tools() {
                return new Tools(bin);
            }

            @Override
            public Path jmods() {
                return jmods;
            }
        }

        var bin = Path.of(directory.toString(), "bin");
        var jmods = Path.of(directory.toString(), "jmods");
        return new JDKImpl(bin, jmods);
    }
}
