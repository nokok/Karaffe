/**
 *
 * Karaffe Programming Language
 *   __ _____   ___  ___   ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / ,\ / __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package karaffe.lang;

/**
 * Karaffe code
 * ////////
 * type Any
 * string : Void to String = undefined
 * hash : Void to String = undefined
 * ////////
 *
 * @author noko
 */
public class Any extends Object {

    /**
     *
     *
     * @return Karaffe String
     */
    public String string() {
        return new Undefined().string();
    }

    public String hash() {
        return new Undefined().hash();
    }
}
