package org.karaffe.core.io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LocalFileSystemSourceCodeReader implements RawSourceCodeReadable<Path> {
    private static final Logger logger = LoggerFactory.getLogger(LocalFileSystemSourceCodeReader.class);

    @Override
    public Optional<String> read(Path src) {
        logger.debug("Reading... {}", src.toAbsolutePath());
        try {
            List<String> lines = Files.readAllLines(src, Charset.forName("UTF-8"));
            logger.debug("ok");
            if (lines.isEmpty()) {
                return Optional.of(""); // Empty file
            }
            Optional<String> source = lines.stream().map(line -> line + "\n").reduce((l, r) -> l + r);
            return source;
        } catch (IOException e) {
            logger.error("File read error: {}", src, e);
            return Optional.empty();
        }
    }
}
