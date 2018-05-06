package org.karaffe.compiler.base.util.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class SystemPropertyConfigurator implements Configurator {

    private final Logger logger = LoggerFactory.getLogger(SystemPropertyConfigurator.class);

    private final Map<String, String> configureMap;

    public SystemPropertyConfigurator(String[] args) {
        this.configureMap = buildConfigureMap(args);
    }

    private Map<String, String> buildConfigureMap(String[] args) {
        logger.debug("buildConfigureMap");
        logger.debug(Arrays.toString(args));
        Map<String, String> map = new HashMap<>();
        Stream.of(args).filter(f -> f.startsWith("-D")).forEach(arg -> {
            String[] prop = arg.split("=");
            if (prop.length == 1) {
                String key = prop[0].replace("-D", "");
                map.put(key, "");
            }
            if (prop.length >= 2) {
                StringBuilder valueBuilder = new StringBuilder();
                String key = prop[0].replace("-D", "");
                for (String vp : Arrays.copyOfRange(prop, 1, prop.length)) {
                    valueBuilder.append(vp).append("=");
                }
                valueBuilder.setLength(valueBuilder.length() - 1);
                map.put(key, valueBuilder.toString());
            }
        });
        logger.debug(map.toString());
        return map;
    }

    @Override
    public void update() {
        this.configureMap.entrySet().stream().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
    }

    public Map<String, String> getConfigureMap() {
        return Collections.unmodifiableMap(this.configureMap);
    }
}
