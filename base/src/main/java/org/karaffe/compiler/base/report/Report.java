package org.karaffe.compiler.base.report;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.util.Platform;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Report {
    private final String title;
    private final ReportType type;
    private final Position position;
    private final String message;
    private final List<AdditionalInfo> informations;

    public Report(final String title, final ReportType type, final Position position, final String message, final List<AdditionalInfo> infomations) {
        this.title = Objects.requireNonNull(title);
        this.type = Objects.requireNonNull(type);
        this.position = Objects.requireNonNull(position);
        this.message = Objects.requireNonNull(message);
        this.informations = Objects.requireNonNull(infomations);
    }

    public static Report createError(final String title, final Position position, final String message, final AdditionalInfo... informations) {
        return new Report(title, ReportType.ERROR, position, message, new ArrayList<>(Arrays.asList(informations)));
    }

    public Optional<StringCodePart> codePart() {
        return this.informations.stream().filter(StringCodePart.class::isInstance).map(StringCodePart.class::cast).findFirst();
    }

    public ReportType getType() {
        return this.type;
    }

    public List<AdditionalInfo> informations() {
        return this.informations.stream().filter(i -> !StringCodePart.class.isInstance(i)).collect(Collectors.toList());
    }

    public String message() {
        return this.message;
    }

    public Position position() {
        return this.position;
    }

    public String title() {
        return this.title;
    }

    public enum ReportType {
        ERROR,
        WARN,
        INFO,

    }

    public static interface AdditionalInfo {
        public List<String> message();
    }

    public static class FoundAndRequired implements AdditionalInfo {

        private final String found;
        private final String required;

        public FoundAndRequired(final String found, final String required) {
            this.found = found;
            this.required = required;
        }

        @Override
        public List<String> message() {
            final List<String> r = new ArrayList<>(2);
            r.add("found    : " + this.found);
            r.add("required : " + this.required);
            return r;
        }

    }

    public static class StringCodePart implements AdditionalInfo {

        private final String code;

        public StringCodePart(final String code) {
            this.code = code;
        }

        @Override
        public List<String> message() {
            return Arrays.asList(this.code);
        }

        public String sourceCode() {
            return this.code;
        }

    }

    public static class Todo implements AdditionalInfo {

        private final String todoMessage;

        public Todo(final String todoMessage) {
            this.todoMessage = todoMessage;
        }

        @Override
        public List<String> message() {
            return Arrays.asList("[TODO] : " + this.todoMessage);
        }

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(reportTypeToString(this.type)).append(" ").append(this.title).append(" ").append(this.position).append(System.lineSeparator());
        if (!this.message.isEmpty()) {
            builder.append(this.message).append(System.lineSeparator());
        }
        return builder.toString();
    }

    private String reportTypeToString(ReportType type) {
        if (type == ReportType.ERROR) {
            return Platform.ANSI_RED + type + Platform.ANSI_RESET;
        } else if (type == ReportType.WARN) {
            return Platform.ANSI_YELLOW + type + Platform.ANSI_RESET;
        } else if (type == ReportType.INFO){
            return Platform.ANSI_CYAN + title + Platform.ANSI_RESET;
        }
        return type.toString();
    }
}
