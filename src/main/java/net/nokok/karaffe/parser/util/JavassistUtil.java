/**
 *
 * Karaffe Programming Language
 *   __ _____   ___  ___   ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / , \/ __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package net.nokok.karaffe.parser.util;

import java.util.Optional;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

public class JavassistUtil {

    public static Optional<CtClass> getCtClassWithName(String name) {
        try {
            return Optional.of(ClassPool.getDefault().get(name));
        } catch (NotFoundException ex) {
            return Optional.empty();
        }
    }
}
