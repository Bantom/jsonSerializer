package jsonObjectWriters;

import serializationUtils.JsonWriter;

/**
 * @author Bulgakov
 * @since 11.12.2016
 */
public class CharacterMapper implements JsonMapper<Character> {

    /**
     * Method serializes character object.
     *
     * @param obj character object to be serialized
     * @param writer output writer
     */
    public void write(Character obj, JsonWriter writer) {
        if(obj == null){
            writer.writeNull();
        }else{
            writer.writeString(obj.toString());
        }
    }
}
