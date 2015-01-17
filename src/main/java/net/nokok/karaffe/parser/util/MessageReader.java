/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Properties;

public class MessageReader {

    private final Properties properties = new Properties();

    public MessageReader() {

        Locale defaultLocale = Locale.getDefault();
        try (FileReader reader = new FileReader(Paths.get("locale", defaultLocale.toString() + ".properties").toFile());) {
            properties.load(reader);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getMessage(ErrorType type) {
        return properties.getProperty(type.toString());
    }
}
