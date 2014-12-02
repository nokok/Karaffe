/**
 *
 * Karaffe Programming Language
 *   __ _____   ___  ___   ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / , \/ __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package net.nokok.karaffe;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Properties;

public class MessageReader {

    private final Properties properties = new Properties();

    public MessageReader() {
        try {
            Locale defaultLocale = Locale.getDefault();
            properties.load(new FileReader(Paths.get("locale", defaultLocale.toString() + ".properties").toFile()));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String compilerErrorFileNotFoundException() {
        return properties.getProperty("compiler.error.fileNotFound");
    }

    public String compilerErrorSyntaxError() {
        return properties.getProperty("compiler.error.syntaxError");
    }
}
