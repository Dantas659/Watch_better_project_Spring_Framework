package br.com.watch_better.service.dataConverter;

public interface IconversionService {
    <T> T dataFetcher(String json, Class<T> valueType);
    
}
