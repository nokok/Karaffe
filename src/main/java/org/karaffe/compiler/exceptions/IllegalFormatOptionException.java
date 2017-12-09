package org.karaffe.compiler.exceptions;

import java.text.MessageFormat;

import org.karaffe.compiler.Messages;

public class IllegalFormatOptionException extends KaraffeException {
    private static final long serialVersionUID = -2846833949169869177L;

    public IllegalFormatOptionException(String cmd) {
        super(MessageFormat.format(Messages.ILLEGAL_FORMAT_OPTION.format(), cmd));
    }
}
