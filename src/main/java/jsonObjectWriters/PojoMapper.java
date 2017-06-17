package jsonObjectWriters;

import jsonMapperFactory.MapperFactory;
import serializationUtils.JsonWriter;
import static serializationUtils.WorkWithModifiersOfFields.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @author Bulgakov
 * @since 11.12.2016
 */
public class PojoMapper implements JsonMapper<Object> {
    private MapperFactory mapperFactory;
    private ArrayList<Field> fields;

    public PojoMapper(Class<?> classExample, MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
        this.fields = new ArrayList<Field>();
        for (Class currentClass = classExample; currentClass != Object.class; currentClass = currentClass.getSuperclass()){
            fields.addAll(getFieldsForSerialization(currentClass));
        }
    }

    /**
     * Method serializes custom object.
     *
     * @param obj object to be serialized
     * @param writer output writer
     */
    public void write(Object obj, JsonWriter writer) {
        if(obj == null){
            writer.writeNull();
        }else{
            writer.writeObjectBegin();
                for (int i = 0; i < fields.size(); i++) {
                    Field currentField = fields.get(i);
                    currentField.setAccessible(true);
                    writer.writeString(currentField.getName());
                    writer.writePropertySeparator();

                    try {
                        Object objectForSerialization = currentField.get(obj);
                        if (objectForSerialization == null){
                            writer.writeNull();
                        }else{
                            JsonMapper mapper = mapperFactory.create(objectForSerialization.getClass());
                            mapper.write(objectForSerialization, writer);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    if (i != fields.size() - 1) writer.writeSeparator();
                }
            writer.writeObjectEnd();
        }
    }

    /**
     * Method gets fields of custom class for serialization.
     *
     * @param currentClass custom class
     * @return list of fields
     */
    private ArrayList<Field> getFieldsForSerialization(Class currentClass) {
        ArrayList<Field> resultForSerialization = new ArrayList<Field>();
        ArrayList<Field> publicFields = getPublicFieldsOfClass(currentClass);
        ArrayList<Field> notPublicFields = getNotPublicFieldsOfClass(currentClass);

        publicFields = removeFieldsWithAnnotation(publicFields, "JsonIgnore");
        notPublicFields = removeFieldsWithAnnotation(notPublicFields, "JsonIgnore");
        notPublicFields = removeFieldsWithoutAnnotation(notPublicFields, "JsonProperty");
        resultForSerialization.addAll(publicFields);
        resultForSerialization.addAll(notPublicFields);

        resultForSerialization = removeTransientFields(resultForSerialization);
        return  resultForSerialization;
    }

}
