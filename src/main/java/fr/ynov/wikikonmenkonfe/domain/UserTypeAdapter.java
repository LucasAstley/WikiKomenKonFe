package main.java.fr.ynov.wikikonmenkonfe.domain;

import com.google.gson.*;

import java.lang.reflect.Type;

public class UserTypeAdapter implements JsonSerializer<User>, JsonDeserializer<User> {

    @Override
    public JsonElement serialize(User src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("name", new JsonPrimitive(src.getName()));
        result.add("type", new JsonPrimitive(src.getClass().getSimpleName()));
        return result;
    }

    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String name = jsonObject.get("name").getAsString();

        // Default to Reader if type isn't specified
        if (!jsonObject.has("type")) {
            return new Reader(name);
        }

        String type = jsonObject.get("type").getAsString();
        return switch (type) {
            case "Writer" -> new Writer(name);
            case "Moderator" -> new Moderator(name);
            case "Admin" -> new Admin(name);
            default -> new Reader(name);
        };
    }
}