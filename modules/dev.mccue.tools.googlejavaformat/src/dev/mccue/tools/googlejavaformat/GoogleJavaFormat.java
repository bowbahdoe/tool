package dev.mccue.tools.googlejavaformat;

import dev.mccue.tools.AbstractToolRunner;
import dev.mccue.tools.ExitStatusException;
import dev.mccue.tools.Tool;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.function.Consumer;

public final class GoogleJavaFormat extends AbstractToolRunner<GoogleJavaFormat, GoogleJavaFormatArguments> {
    private GoogleJavaFormat(Tool tool, GoogleJavaFormatArguments arguments) {
        super(tool, arguments);
    }

    public static void download(URI source, Path destination) throws IOException {
        try (var stream = source.toURL().openStream()) {
            Files.copy(stream, destination, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public static void downloadVersion(String version, Path destination) throws IOException {
        download(
                URI.create("https://github.com/google/google-java-format/releases/download/v%s/google-java-format-%s-all-deps.jar".formatted(version, version)),
                destination
        );
    }

    public static GoogleJavaFormat runner(Path jarPath) {
        return runner(jarPath, new GoogleJavaFormatArguments());
    }

    public static GoogleJavaFormat runner(Path jarPath, GoogleJavaFormatArguments arguments) {
        return new GoogleJavaFormat(
                Tool.ofSubprocess(List.of("java", "-jar", jarPath.toString())),
                arguments
        );
    }

    public static GoogleJavaFormat runner(Path jarPath, Consumer<? super GoogleJavaFormatArguments> consumer) {
        var jar = runner(jarPath);
        consumer.accept(jar.arguments);
        return jar;
    }

    public static void run(Path jarPath, GoogleJavaFormatArguments arguments) throws ExitStatusException {
        runner(
                jarPath,
                arguments
        ).run();
    }

    public static void run(Path jarPath, Consumer<? super GoogleJavaFormatArguments> consumer) throws ExitStatusException {
        runner(jarPath, consumer).run();
    }
}
