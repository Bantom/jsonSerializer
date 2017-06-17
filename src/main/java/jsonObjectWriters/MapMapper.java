package jsonObjectWriters;

import jsonMapperFactory.MapperFactory;
import serializationUtils.JsonWriter;

import java.util.Iterator;
import java.util.Map;

/**
 * @author Bulgakov
 * @since 11.12.2016
 */
public class MapMapper implements JsonMapper<Map<?, ?>> {
    private MapperFactory mapperFactory;

    public MapMapper(MapperFactory mapperFactory){
        this.mapperFactory = mapperFactory;
    }

    /**
     * Method serializes Map object.
     *
     * @param obj map object to be serialized
     * @param writer output writer
     */
    public void write(Map<?, ?> obj, JsonWriter writer) {
        if(obj == null){
            writer.writeNull();
        }else{
            Iterator iterator = obj.keySet().iterator();
            Object key;
            Object value;

            writer.writeObjectBegin();
            while(iterator.hasNext()){
                key = iterator.next();
                if (key.toString().equals("")) {
                    throw new IllegalStateException();
                }
                writer.writeString(key.toString());
                writer.writePropertySeparator();

                value = obj.get(key);
                if (value == null) {
                    writer.writeNull();
                }else{
                    JsonMapper mapper = mapperFactory.create(value.getClass());
                    mapper.write(value, writer);
                }
                if (iterator.hasNext()) writer.writeSeparator();
            }
            writer.writeObjectEnd();
        }
    }
}
