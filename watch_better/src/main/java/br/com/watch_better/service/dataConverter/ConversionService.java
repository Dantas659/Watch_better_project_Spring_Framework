package br.com.watch_better.service.dataConverter;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConversionService implements IconversionService {
    private ObjectMapper object = new ObjectMapper();

    @Override
    public <T> T dataFetcher(String json, Class<T> valueType) {
        try {
            return object.readValue(json, valueType);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }


}
