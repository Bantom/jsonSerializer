package jsonMapperFactory;

import jsonObjectWriters.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Bulgakov
 * @since 18.12.2016
 */
public class MapperFactory implements JsonMapperFactory {
    private Map<Class<?>, JsonMapper> mappersCache;
    JsonMapper primitiveArrayMapper;
    JsonMapper objectArrayMapper;

    public MapperFactory() {
        mappersCache = new HashMap<Class<?>, JsonMapper>();

        NumberMapper numberMapper = new NumberMapper();
        BooleanMapper booleanMapper = new BooleanMapper();
        CharacterMapper characterMapper = new CharacterMapper();
        StringMapper stringMapper = new StringMapper();
        CollectionMapper collectionMapper = new CollectionMapper(this);
        MapMapper mapMapper = new MapMapper(this);

        mappersCache.put(Byte.class, numberMapper);
        mappersCache.put(byte.class, numberMapper);
        mappersCache.put(Short.class, numberMapper);
        mappersCache.put(short.class, numberMapper);
        mappersCache.put(Integer.class, numberMapper);
        mappersCache.put(int.class, numberMapper);
        mappersCache.put(Long.class, numberMapper);
        mappersCache.put(long.class, numberMapper);
        mappersCache.put(Float.class, numberMapper);
        mappersCache.put(float.class, numberMapper);
        mappersCache.put(Double.class, numberMapper);
        mappersCache.put(double.class, numberMapper);
        mappersCache.put(Boolean.class, booleanMapper);
        mappersCache.put(Character.class, characterMapper);
        mappersCache.put(char.class, characterMapper);
        mappersCache.put(String.class, stringMapper);

        mappersCache.put(Collection.class, collectionMapper);
        mappersCache.put(Map.class, mapMapper);

        primitiveArrayMapper = new PrimitiveArrayMapper(this);
        objectArrayMapper = new ObjectArrayMapper(this);
    }

    /**
     * Method gets mapper for class from params.
     *
     * @param classExample class of object
     * @return mapper for class from params.
     */
    public JsonMapper create(Class<?> classExample) {
        if(mappersCache.containsKey(classExample)){
            return mappersCache.get(classExample);
        }
        if (Map.class.isAssignableFrom(classExample)) {
            return mappersCache.get(Map.class);
        }
        if (Collection.class.isAssignableFrom(classExample)) {
            return mappersCache.get(Collection.class);
        }
        if (classExample.isArray()) {
            if (classExample.getComponentType().isPrimitive()) {
                return primitiveArrayMapper;
            } else {
                return objectArrayMapper;
            }
        }

        JsonMapper mapper = mappersCache.get(classExample);
        if (mapper == null) {
            mapper = createPojoMapper(classExample);
            mappersCache.put(classExample, mapper);
        }
        return mapper;
    }

    /**
     * Method creates new Mapper for custom object.
     *
     * @param classExample class of custom object
     * @return mapper for custom object
     */
    protected JsonMapper createPojoMapper(Class<?> classExample) {
        return new PojoMapper(classExample,this);
    }
}
