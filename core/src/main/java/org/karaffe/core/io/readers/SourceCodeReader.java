package org.karaffe.core.io.readers;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import org.karaffe.core.SourceCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface SourceCodeReader<T> extends RawSourceCodeReadable<T>, SourceCodeReadable<T> {
}

class DirectSourceReader implements RawSourceCodeReadable<String> {

    @Override
    public Optional<String> read(String src) {
        return Optional.of(src.replaceAll("\r\n", "\n").replaceAll("\r", "\n"));
    }

}

class LocalFileSystemSourceCodeReader implements SourceCodeReader<Path> {
    private static final Logger logger = LoggerFactory.getLogger(LocalFileSystemSourceCodeReader.class);

    @Override
    public Optional<String> read(Path src) {
        return read(src, (path, source) -> source);
    }

    @Override
    public Optional<SourceCode> readAsSourceCode(Path src) {
        return read(src, (path, source) -> new SourceCode(path, source));
    }

    private <R> Optional<R> read(Path src, BiFunction<Path, String, R> f) {
        logger.debug("Reading... {}", src.toAbsolutePath());
        try {
            List<String> lines = Files.readAllLines(src, Charset.forName("UTF-8"));
            logger.debug("ok");
            if (lines.isEmpty()) {
                return Optional.of(f.apply(src, ""));
            }
            return lines.stream().map(line -> line + "\n").reduce((l, r) -> l + r).map(s -> f.apply(src, s));
        } catch (IOException e) {
            logger.error("File read error: {}", e);
            return Optional.empty();
        }
    }
}
