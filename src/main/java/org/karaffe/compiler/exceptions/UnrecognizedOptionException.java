package org.karaffe.compiler.exceptions;

import java.text.MessageFormat;

import org.karaffe.compiler.Messages;

public class UnrecognizedOptionException extends KaraffeException {
    private static final long serialVersionUID = -8210305739661498194L;

    public UnrecognizedOptionException(final String cmd) {
        super(MessageFormat.format(Messages.UNRECOGNIZED_OPTION.format(), cmd));
    }
}
