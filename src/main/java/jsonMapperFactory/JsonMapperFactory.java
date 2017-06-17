package jsonMapperFactory;

import jsonObjectWriters.JsonMapper;
import jsonSerializer.JsonSerializer;

/**
 * @author Bulgakov
 * @since 18.12.2016
 */
public interface JsonMapperFactory {
    public JsonMapper create(Class<?> clazz);
}
