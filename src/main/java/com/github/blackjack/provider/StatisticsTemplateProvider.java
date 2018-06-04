package com.github.blackjack.provider;

import com.github.blackjack.model.StatisticsTemplate;
import com.google.inject.Provider;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StatisticsTemplateProvider implements Provider<StatisticsTemplate> {
    private static final String TEMPLATE_FILE = "/stats_template.txt";
    private String templateFile = TEMPLATE_FILE;

    @Override
    public StatisticsTemplate get() {
        return loadFromFile();
    }

    private StatisticsTemplate loadFromFile() {
        StringBuilder templateBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(templateFile)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                templateBuilder.append(line).append("\n");
            }
        } catch (Exception e) {
            throw new RuntimeException(templateFile + " not found");
        }

        return new StatisticsTemplate(templateBuilder.toString());
    }

    public void setTemplateFile(String templateFile){
        this.templateFile = templateFile;
    }
}