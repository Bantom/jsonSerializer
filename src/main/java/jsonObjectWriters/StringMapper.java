package jsonObjectWriters;

import serializationUtils.JsonWriter;

/**
 * @author Bulgakov
 * @since 11.12.2016
 */
public class StringMapper implements JsonMapper<String> {

    /**
     * Method serializes string object.
     *
     * @param obj string object to be serialized
     * @param writer output writer
     */
    public void write(String obj, JsonWriter writer) {
        if(obj == null){
            writer.writeNull();
        }else{
            writer.writeString(obj);
        }
    }
}
