package dev.mccue.tools.pmd;

import dev.mccue.tools.AbstractToolRunner;
import dev.mccue.tools.ExitStatusException;
import dev.mccue.tools.Tool;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.function.Consumer;
import java.util.zip.ZipInputStream;

public final class PMD extends AbstractToolRunner<PMD, PMDArguments> {
    private static final boolean IS_WINDOWS = System.getProperty("os.name")
            .toLowerCase()
            .startsWith("win");

    private PMD(Tool tool, PMDArguments arguments) {
        super(tool, arguments);
    }

    public static void download(URI source, Path destination) throws IOException {
        download(source, destination, Files.createTempDirectory("pmd-download"));
    }

    public static void download(URI source, Path destination, Path tempDir) throws IOException {
        Files.createDirectories(destination);
        Files.createDirectories(tempDir);

        var downloadedZipPath = Path.of(tempDir.toString(), "pmd.zip");
        try (var stream = source.toURL().openStream()) {
            Files.copy(stream, downloadedZipPath, StandardCopyOption.REPLACE_EXISTING);
        }

        downloadedZipPath = downloadedZipPath.toAbsolutePath();
        try (var is = Files.newInputStream(downloadedZipPath);
             var zis = new ZipInputStream(is)) {

            for (
                    var entry = zis.getNextEntry();
                    entry != null;
                    entry = zis.getNextEntry()
            ) {
                var entryName = entry.getName().replaceFirst(
                        "pmd-bin-([0-9])+\\.([0-9])+\\.([0-9])+/", ""
                );
                var resolvedPath = destination.resolve(entryName).normalize();
                if (!resolvedPath.startsWith(destination)) {
                    throw new RuntimeException("Improper zip file");
                }

                if (entry.isDirectory()) {
                    Files.createDirectories(resolvedPath);
                } else {
                    Files.createDirectories(resolvedPath.getParent());
                    Files.copy(zis, resolvedPath, StandardCopyOption.REPLACE_EXISTING);

                    if (resolvedPath.endsWith("pmd") && !IS_WINDOWS) {
                        try {
                            Tool.ofSubprocess("chmod")
                                    .echoCommand(false)
                                    .run("+x", resolvedPath.toString());
                        } catch (ExitStatusException e) {
                            throw new IOException(e);
                        }
                    }
                }
            }
        }
    }

    public static void downloadVersion(String version, Path destination) throws IOException {
        downloadVersion(
                version,
                Files.createTempDirectory("pmd-download"),
                destination
        );
    }

    public static void downloadVersion(String version, Path tempDir, Path destination) throws IOException {
        download(
                URI.create("https://github.com/pmd/pmd/releases/download/pmd_releases%2F" + version + "/pmd-dist-" + version + "-bin.zip"),
                destination
        );
    }

    public static PMD runner() {
        return runner(new PMDArguments());
    }

    public static PMD runner(Path jarPath) {
        return runner(jarPath, new PMDArguments());
    }

    public static PMD runner(PMDArguments arguments) {
        return new PMD(
                Tool.ofSubprocess("pmd"),
                arguments
        );
    }

    public static PMD runner(Consumer<? super PMDArguments> consumer) {
        var jar = runner();
        consumer.accept(jar.arguments);
        return jar;
    }

    public static PMD runner(Path pmdPath, PMDArguments arguments) {
        return new PMD(
                Tool.ofSubprocess(Path.of(pmdPath.toString(), "bin", IS_WINDOWS ? "pmd.bat" : "pmd").toString()),
                arguments
        );
    }

    public static PMD runner(Path pmdPath, Consumer<? super PMDArguments> consumer) {
        var jar = runner(pmdPath);
        consumer.accept(jar.arguments);
        return jar;
    }

    public static void run(Path pmdPath, PMDArguments arguments) throws ExitStatusException {
        runner(
                pmdPath,
                arguments
        ).run();
    }

    public static void run(Path pmdPath, Consumer<? super PMDArguments> consumer) throws ExitStatusException {
        runner(pmdPath, consumer).run();
    }

    public static void run(PMDArguments arguments) throws ExitStatusException {
        runner(
                arguments
        ).run();
    }

    public static void run(Consumer<? super PMDArguments> consumer) throws ExitStatusException {
        runner(consumer).run();
    }

    /* public static void main(String[] args) throws Exception {
        PMD.run(Path.of("tools/pmd"), arguments -> {
            arguments.add("generate-completion");
            /*
            cpd
              designer             The PMD visual rule designer
  cpd-gui              GUI for the Copy/Paste Detector
                         Warning: May not support the full CPD feature set
  ast-dump             Dumps the AST of parsing source code
  generate-completion  Generate bash/zsh completion script for pmd.

            arguments.add("--help");
        });
    } */
}
