package jsonSerializer;

import jsonMapperFactory.JsonMapperFactory;
import jsonMapperFactory.MapperFactory;
import jsonObjectWriters.JsonMapper;
import serializationUtils.IndentedJsonWriter;
import serializationUtils.JsonWriter;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Bulgakov
 * @since 18.12.2016
 */
public class JsonSerializer {
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private JsonMapperFactory mappersCache;
    private Boolean indent = false;
    private Integer indentSize = 2;

    public JsonSerializer(JsonMapperFactory mapperFactory) {
        this.mappersCache = mapperFactory;
    }

    public JsonSerializer() {
        this(new MapperFactory());
    }

    /**
     * Method returns size of indent.
     *
     * @return size of indent.
     */
    public Integer getIndentSize() {
        return indentSize;
    }

    /**
     * Method sets new size of indent.
     *
     * @param indentSize size of indent
     */
    public void setIndentSize(Integer indentSize) {
        this.indentSize = indentSize;
    }

    /**
     * Method gets status of beautiful design of objects that have been serialized.
     * @return current indent.
     */
    public Boolean isIndent() {
        return indent;
    }

    /**
     * Method turns on/off beautiful design of objects that have been serialized.
     * @param indent param is used for turning on/off beautiful design of objects that have been serialized.
     */
    public void setIndent(Boolean indent) {
        this.indent = indent;
    }

    /**
     * Method serializes object from param.
     * It uses default charset {@link jsonSerializer.JsonSerializer#DEFAULT_CHARSET}.
     *
     * @param obj object for serialization
     * @return json string of object that has been serialized.
     * @throws IOException
     */
    public String serialize(Object obj) throws IOException {
        Writer stringWriter = new StringWriter();
        this.serialize(obj, stringWriter);
        return stringWriter.toString();
    }

    /**
     * Method serializes object from param in output stream from params.
     * It uses default charset {@link jsonSerializer.JsonSerializer#DEFAULT_CHARSET}.
     *
     * @param obj object for serialization
     * @param stream output stream
     * @throws IOException
     */
    public void serialize(Object obj, OutputStream stream) throws IOException {
        serialize(obj, stream, DEFAULT_CHARSET);
    }

    /**
     * Method serializes object from param in output stream from params using charset from params.
     *
     * @param obj object for serialization
     * @param stream output stream
     * @param charset charset is used for serialization
     * @throws IOException
     */
    public void serialize(Object obj, OutputStream stream, Charset charset) throws IOException {
        serialize(obj, new OutputStreamWriter(stream, charset));
    }

    /**
     * Method serializes object from param .
     *
     * @param obj object for serialization
     * @param outputWriter output writer
     * @throws IOException
     */
    public void serialize(Object obj, Writer outputWriter) throws IOException {
        JsonWriter jsonWriter;
        if (indent) {
            jsonWriter = new IndentedJsonWriter(outputWriter, indentSize);
        } else {
            jsonWriter = new JsonWriter(outputWriter);
        }
        if (obj == null) {
            jsonWriter.writeNull();
        } else {
            mappersCache.create(obj.getClass()).write(obj, jsonWriter);
        }
        jsonWriter.flush();
    }
}
