package org.karaffe.compiler.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.karaffe.compiler.pos.Position;

public class Report {
    private final String title;
    private final ReportType type;
    private final Position position;
    private final String message;
    private final List<AdditionalInfo> informations;

    public Report(String title, ReportType type, Position position, String message, List<AdditionalInfo> infomations) {
        this.title = title;
        this.type = type;
        this.position = position;
        this.message = message;
        this.informations = infomations;
    }

    public static Report createError(String title, Position position, String message, AdditionalInfo... informations) {
        return new Report(title, ReportType.ERROR, position, message, new ArrayList<>(Arrays.asList(informations)));
    }

    public ReportType getType() {
        return this.type;
    }

    public Position position() {
        return this.position;
    }

    public String title() {
        return this.title;
    }

    public String message() {
        return this.message;
    }

    public Optional<StringCodePart> codePart() {
        return this.informations.stream().filter(StringCodePart.class::isInstance).map(StringCodePart.class::cast).findFirst();
    }

    public List<AdditionalInfo> informations() {
        return this.informations.stream().filter(i -> !StringCodePart.class.isInstance(i)).collect(Collectors.toList());
    }

    public enum ReportType {
        ERROR,
        WARN,
        INFO,

    }

    public static interface AdditionalInfo {
        public List<String> message();
    }

    public static class Todo implements AdditionalInfo {

        private final String todoMessage;

        public Todo(String todoMessage) {
            this.todoMessage = todoMessage;
        }

        @Override
        public List<String> message() {
            return Arrays.asList("[TODO] : " + this.todoMessage);
        }

    }

    public static class StringCodePart implements AdditionalInfo {

        private final String code;

        public StringCodePart(String code) {
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

    public static class FoundAndRequired implements AdditionalInfo {

        private final String found;
        private final String required;

        public FoundAndRequired(String found, String required) {
            this.found = found;
            this.required = required;
        }

        @Override
        public List<String> message() {
            List<String> r = new ArrayList<>(2);
            r.add("found    : " + this.found);
            r.add("required : " + this.required);
            return r;
        }

    }
}
