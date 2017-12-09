package org.karaffe.compiler;

public enum Messages {
    ILLEGAL_FORMAT_OPTION("Invalid Command format.\n-'{'ALIAS_NAME'}' or --'{'FULL_NAME'}'='{'VALUE'}' are valid.: {0}"),
    UNRECOGNIZED_OPTION("Unrecognized Option : {0}"),

    ;
    private final String format;

    private Messages(String format) {
        this.format = format;
    }

    public String format() {
        return this.format;
    }

}
