package com.example.currencychecker.config;

import com.example.currencychecker.api.controller.dto.response.OpenexchangeDtoResponse.*;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Log
public class MapDeserializer extends StdDeserializer<Map<Object, Object>> implements ContextualDeserializer {

    private Class<?> keyAs;

    private Class<?> contentAs;


    public MapDeserializer() {
        this(null);
    }

    protected MapDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Map<Object, Object> deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        return this.deserialize(p, ctxt, new HashMap<>());
    }

    @Override
    public Map<Object, Object> deserialize(JsonParser p, DeserializationContext ctxt,
                                           Map<Object, Object> intoValue) throws IOException, JsonProcessingException {
        JsonNode node = p.readValueAsTree();
        ObjectCodec codec = p.getCodec();
        if (node.isArray()) {
            node.forEach(entry -> {
                try {
                    JsonNode keyNode = entry.get(0);
                    JsonNode valueNode = entry.get(1);
                    intoValue.put(keyNode.traverse(codec).readValueAs(this.keyAs).toString(),
                            Float.parseFloat(valueNode.traverse(codec).readValueAs(this.contentAs).toString()));
                } catch (NullPointerException | IOException e) {
                    log.warning(e.getMessage());
                }
            });
        }
        return intoValue;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        JsonDeserialize jsonDeserialize = property.getAnnotation(JsonDeserialize.class);
        this.keyAs = jsonDeserialize.keyAs();
        this.contentAs = jsonDeserialize.contentAs();
        return this;
    }

    /*@Override
    public Map<String, Float> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        int id = (Integer) ((IntNode) node.get("id")).numberValue();
        String itemName = node.get("itemName").asText();
        int userId = (Integer) ((IntNode) node.get("createdBy")).numberValue();

        return new Item(id, itemName, new User(userId, null));


        return Map.of();
    }*/
}
