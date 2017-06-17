package jsonObjectWriters;

import jsonMapperFactory.MapperFactory;
import serializationUtils.JsonWriter;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Bulgakov
 * @since 11.12.2016
 */
public class CollectionMapper implements JsonMapper<Collection<?>> {
    private MapperFactory mapperFactory;

    public CollectionMapper(MapperFactory mapperFactory){
        this.mapperFactory = mapperFactory;
    }

    /**
     * Method serializes collection object.
     *
     * @param obj collection object to be serialized
     * @param writer output writer
     */
    public void write(Collection<?> obj, JsonWriter writer) {
        if (obj == null) {
            writer.writeNull();
        } else {
            Object tmp;
            writer.writeArrayBegin();
            Iterator iterator = obj.iterator();
            while(iterator.hasNext()){
                tmp = iterator.next();
                if (tmp == null) {
                    writer.writeNull();
                }else {
                    JsonMapper mapper = mapperFactory.create(tmp.getClass());
                    mapper.write(tmp, writer);
                }
                if (iterator.hasNext()) writer.writeSeparator();
            }
            writer.writeArrayEnd();
        }
    }
}
