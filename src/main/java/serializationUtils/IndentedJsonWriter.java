package serializationUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Bulgakov
 * @since 18.12.2016
 */
public class IndentedJsonWriter extends JsonWriter {
    private static Logger log = Logger.getLogger(IndentedJsonWriter.class.getName());
    private static final String NEW_LINE = "\n";
    private static final String INDENT_SEPARATOR = " ";
    private static final char SPACE = ' ';
    private Integer indentSize = 2;
    private Integer currentLevel = 0;
    private Boolean separatorLast;

    private Map<Integer, String> newLineIndentMap;

    public IndentedJsonWriter(Writer outputWriter) {
        super(outputWriter);
        newLineIndentMap = new HashMap<Integer, String>();
    }

    public IndentedJsonWriter(Writer outputWriter, int indentSize) {
        this(outputWriter);
        this.indentSize = indentSize;
    }

    public int getIndentSize() {
        return indentSize;
    }

    public void setIndentSize(int indentSize) {
        this.indentSize = indentSize;
    }

    @Override
    public void writeObjectBegin() {
        try {
            currentLevel++;
            outputWriter.append(OBJECT_BEGIN).write(NEW_LINE);
            outputWriter.write(getNewLineIndentString());
        } catch (IOException e) {
            log.info("IndentedJsonWriter exception - method writeObjectBegin()");
        }
    }

    @Override
    public void writeObjectEnd() {
        try {
            currentLevel--;
            outputWriter.write(NEW_LINE);
            outputWriter.write(getNewLineIndentString());
            outputWriter.write(OBJECT_END);
        } catch (IOException e) {
            log.info("IndentedJsonWriter exception - method writeObjectEnd()");
        }
    }

    @Override
    public void writeArrayBegin() {
        try {
            currentLevel++;
            outputWriter.append(ARRAY_BEGIN).write(NEW_LINE);
            outputWriter.write(getNewLineIndentString());
        } catch (IOException e) {
            log.info("IndentedJsonWriter exception - method writeArrayBegin()");
        }
    }

    @Override
    public void writeArrayEnd() {
        separatorLast = false;
        try {
            currentLevel--;
            outputWriter.write(NEW_LINE);
            outputWriter.write(getNewLineIndentString());
            outputWriter.write(ARRAY_END);
        } catch (IOException e) {
            log.info("IndentedJsonWriter exception - method writeArrayEnd()");
        }
    }

    @Override
    public void writePropertySeparator() {
        try {
            outputWriter.append(SPACE).append(PROPERTY_SEPARATOR).append(SPACE);
        } catch (IOException e) {
            log.info("IndentedJsonWriter exception - method writePropertySeparator()");
        }
    }

    @Override
    public void writeSeparator() {
        try {
            outputWriter.append(SEPARATOR).write(NEW_LINE);
            outputWriter.write(getNewLineIndentString());
        } catch (IOException e) {
            log.info("IndentedJsonWriter exception - method writeSeparator()");
        }
    }

    private String getNewLineIndentString() {
        String indentString = newLineIndentMap.get(currentLevel);
        if (indentString != null) {
            return indentString;
        } else {
            StringBuilder string = new StringBuilder("");
            for (int i = 0; i < currentLevel * indentSize; i++) {
                string.append(INDENT_SEPARATOR);
            }
            indentString = string.toString();
            newLineIndentMap.put(currentLevel, indentString);
            return indentString;
        }
    }
}
