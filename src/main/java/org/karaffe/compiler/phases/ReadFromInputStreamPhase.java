package org.karaffe.compiler.phases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

public class ReadFromInputStreamPhase<T extends InputStream> extends AbstractTransformer<T, String> {
    public ReadFromInputStreamPhase(Class<T> inputType) {
        super(inputType, String.class);
    }

    @Override
    public Optional<String> transform(T input) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            return Optional.of(stringBuilder.toString());
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
