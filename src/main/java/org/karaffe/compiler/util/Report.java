package org.karaffe.compiler.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.karaffe.compiler.pos.Position;

public class Report {
    private final String title;
    private final ReportType type;
    private final Position position;
    private final String message;
    private final List<AdditionalInfo> infomations;

    public Report(String title, ReportType type, Position position, String message, List<AdditionalInfo> infomations) {
        this.title = title;
        this.type = type;
        this.position = position;
        this.message = message;
        this.infomations = infomations;
    }

    public static Report createError(String title, Position position, String message, AdditionalInfo... infomations) {
        return new Report(title, ReportType.ERROR, position, message, new ArrayList<>(Arrays.asList(infomations)));
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

        public FoundAndRequired(String found, String required) {
            this.found = found;
            this.required = required;
        }

        @Override
        public List<String> message() {
            List<String> r = new ArrayList<>(2);
            r.add("found   : " + this.found);
            r.add("required: " + this.required);
            return r;
        }

    }
}
