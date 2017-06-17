package jsonObjectWriters;

import jsonMapperFactory.MapperFactory;
import serializationUtils.JsonWriter;

import java.lang.reflect.Array;

/**
 * @author Bulgakov
 * @since 11.12.2016
 */
public class PrimitiveArrayMapper implements JsonMapper<Object> {
    private MapperFactory mapperFactory;

    public PrimitiveArrayMapper(MapperFactory mapperFactory){
        this.mapperFactory = mapperFactory;
    }

    /**
     * Method serializes array of primitives.
     *
     * @param obj array of primitives to be serialized
     * @param writer output writer
     */
    public void write(Object obj, JsonWriter writer) {
        if (obj == null){
            writer.writeNull();
        }else{
            writer.writeArrayBegin();
            if(Array.getLength(obj) > 0) {
                JsonMapper mapper = mapperFactory.create(Array.get(obj, 0).getClass());
                for (int i = 0; i < Array.getLength(obj); i++) {
                    Object tmp = Array.get(obj, i);
                    if (tmp == null) {
                        writer.writeNull();
                    } else {
                        mapper.write(tmp, writer);
                    }
                    if (i != Array.getLength(obj) - 1) writer.writeSeparator();
                }
            }
            writer.writeArrayEnd();
        }
    }
}
