package serializationUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * @author Bulgakov
 * @since 18.12.2016
 */
public class JsonWriter {
    private static Logger log = Logger.getLogger(JsonWriter.class.getName());
    protected Writer outputWriter;

    public JsonWriter(Writer outputWriter) {
        this.outputWriter = outputWriter;
    }

    protected static final char OBJECT_BEGIN = '{';
    protected static final char OBJECT_END = '}';
    protected static final char ARRAY_BEGIN = '[';
    protected static final char ARRAY_END = ']';
    protected static final char SEPARATOR = ',';
    protected static final char PROPERTY_SEPARATOR = ':';
    protected static final char STRING_SEPARATOR = '\"';
    protected static final String NULL = "null";

    /**
     * Method writes symbol of the beginning of the object in Writer.
     */
    public void writeObjectBegin() {
        try {
            outputWriter.write(OBJECT_BEGIN);
        } catch (IOException e) {
            log.info("JsonWriter exception - method writeObjectBegin()");
        }
    }

    /**
     * Method writes symbol of the ending of the object in Writer.
     */
    public void writeObjectEnd() {
        try {
            outputWriter.write(OBJECT_END);
        } catch (IOException e) {
            log.info("JsonWriter exception - method writeObjectEnd()");
        }
    }

    /**
     * Method writes symbol of the beginning of the array in Writer.
     */
    public void writeArrayBegin() {
        try {
            outputWriter.write(ARRAY_BEGIN);
        } catch (IOException e) {
            log.info("JsonWriter exception - method writeArrayBegin()");
        }
    }

    /**
     * Method writes symbol of the ending of the array in Writer.
     */
    public void writeArrayEnd() {
        try {
            outputWriter.write(ARRAY_END);
        } catch (IOException e) {
            log.info("JsonWriter exception - method writeArrayEnd()");
        }

    }

    /**
     * Method writes string from params with separator symbols in Writer.
     *
     * @param string string for serialization
     */
    public void writeString(String string) {
        try {
            outputWriter.append(STRING_SEPARATOR).append(string).append(STRING_SEPARATOR);
        } catch (IOException e) {
            log.info("JsonWriter exception - method writeString(String string)");
        }
    }

    /**
     * Method writes number in Writer.
     *
     * @param number number for serialization
     */
    public void writeNumber(Number number) {
        try {
            outputWriter.write(number.toString());
        } catch (IOException e) {
            log.info("JsonWriter exception - method writeNumber(Number number)");
        }
    }

    /**
     * Method writes comma in Writer.
     */
    public void writeSeparator() {
        try {
            outputWriter.write(SEPARATOR);
        } catch (IOException e) {
            log.info("JsonWriter exception - method writeSeparator()");
        }
    }

    /**
     * Method writes colon in Writer.
     */
    public void writePropertySeparator() {
        try {
            outputWriter.write(PROPERTY_SEPARATOR);
        } catch (IOException e) {
            log.info("JsonWriter exception - method writePropertySeparator()");
        }
    }

    /**
     * Method writes boolean variable from param in Writer.
     *
     * @param value boolean for serialization
     */
    public void writeBoolean(Boolean value) {
        try {
            outputWriter.write(value.toString());
        } catch (IOException e) {
            log.info("JsonWriter exception - method writeBoolean(Boolean value)");
        }

    }

    /**
     * Method writes Null in Writer.
     */
    public void writeNull() {
        try {
            outputWriter.write(NULL);
        } catch (IOException e) {
            log.info("JsonWriter exception - method writeNull()");
        }
    }

    /**
     * Method flushes the Writer.
     */
    public void flush() {
        try {
            outputWriter.flush();
        } catch (IOException e) {
            log.info("JsonWriter exception - method flush()");
        }
    }

}
