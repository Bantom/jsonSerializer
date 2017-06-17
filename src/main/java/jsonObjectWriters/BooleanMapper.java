package jsonObjectWriters;

import serializationUtils.JsonWriter;
/**
 * @author Bulgakov
 * @since 11.12.2016
 */
public class BooleanMapper implements JsonMapper<Boolean>{

    /**
     * Method serializes boolean object.
     *
     * @param obj boolean object to be serialized
     * @param writer output writer
     */
    public void write(Boolean obj, JsonWriter writer) {
        if(obj == null){
            writer.writeNull();
        }else{
            writer.writeBoolean(obj);
        }
    }
}
