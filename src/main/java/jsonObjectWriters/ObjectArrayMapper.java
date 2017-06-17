package jsonObjectWriters;

import jsonMapperFactory.MapperFactory;
import serializationUtils.JsonWriter;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author Bulgakov
 * @since 11.12.2016
 */
public class ObjectArrayMapper implements JsonMapper<Object[]> {
    private MapperFactory mapperFactory;

    public ObjectArrayMapper(MapperFactory mapperFactory){
        this.mapperFactory = mapperFactory;
    }

    /**
     * Method serializes array of objects.
     *
     * @param obj array of objects to be serialized
     * @param writer output writer
     */
    public void write(Object[] obj, JsonWriter writer) {
        if (obj == null){
            writer.writeNull();
        }else{
            Object tmp;
            writer.writeArrayBegin();
            for (int i = 0; i < obj.length; i++) {
                tmp = obj[i];
                if(tmp == null){
                    writer.writeNull();
                }else{
                    JsonMapper mapper = mapperFactory.create(tmp.getClass());
                    mapper.write(tmp, writer);
                }
                if (i != obj.length - 1) writer.writeSeparator();
            }
            writer.writeArrayEnd();
        }
    }
}
