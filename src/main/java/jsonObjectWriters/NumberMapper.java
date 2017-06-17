package jsonObjectWriters;

import serializationUtils.JsonWriter;

/**
 * @author Bulgakov
 * @since 11.12.2016
 */
public class NumberMapper implements JsonMapper<Number> {
    /**
     * Method serializes number object.
     *
     * @param obj number object to be serialized
     * @param writer output writer
     */
    public void write(Number obj, JsonWriter writer) {
        if(obj == null){
            writer.writeNull();
        }else{
            writer.writeNumber(obj);
        }
    }
}
