package jsonObjectWriters;

import serializationUtils.JsonWriter;

/**
 * @author Bulgakov
 * @since 11.12.2016
 */
public interface JsonMapper<T> {
    public void write(T obj, JsonWriter writer);
}
