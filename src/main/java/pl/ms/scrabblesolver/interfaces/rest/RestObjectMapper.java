package pl.ms.scrabblesolver.interfaces.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/*
 * Created by Marcin on 2017-03-19.
 */
public class RestObjectMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestObjectMapper.class);


    // object mapper with default properties - do not use if you want to modify
    private static final ObjectMapper DEFAULT_INSTANCE = createObjectMapper();
    private static final ObjectMapper PRETTY_PRINT_INSTANCE;

    static {
        PRETTY_PRINT_INSTANCE = createObjectMapper();
        PRETTY_PRINT_INSTANCE.configure(SerializationFeature.INDENT_OUTPUT, true); // format output
    }

    /**
     * creates an object-mapper with selfcare basic stuff - use this method if you want customized properties
     */
    public static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // ignore unknown properties
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); // write as ISODate
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS); // include null values

        return objectMapper;
    }

    public static <T> T readValueDefault(String json, Class<T> type) {
        try {
            return StringUtils.isEmpty(json) ? null : DEFAULT_INSTANCE.readValue(json, type);
        } catch (IOException e) {
            RuntimeException result = new RuntimeException(String.format("Cannot deserialize %s from json string: %s", type.getSimpleName(), json), e);
            LOGGER.warn("ObjectMapperError", result);
            throw result;
        }
    }

    public static String writeValueAsStringDefault(Object object) {
        return writeValueAsString(DEFAULT_INSTANCE, object);
    }

    public static String writeValueAsStringPretty(Object object) {
        return writeValueAsString(PRETTY_PRINT_INSTANCE, object);
    }

    private static String writeValueAsString(ObjectMapper objectMapper, Object object) {
        try {
            return object != null ? objectMapper.writeValueAsString(object) : null;
        } catch (JsonProcessingException e) {
            RuntimeException result = new RuntimeException(String.format("Cannot serialize %s to json. Given object: %s",
                    object.getClass().getSimpleName(), object.toString()), e);
            LOGGER.warn("ObjectMapperError", result);
            throw result;
        }
    }
}
