package main.java.fr.ynov.wikikonmenkonfe.domain;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * This class is a custom Gson TypeAdapter for the User class
 * It handle converting User objects to JSON and vice versa
 */
public class UserTypeAdapter implements JsonSerializer<User>, JsonDeserializer<User> {

    /**
     * This method is used to convert a User object to JSON
     *
     * @param src       the User object to convert
     * @param typeOfSrc the type of the object
     * @param context   the context of the conversion
     * @return the JSON representation of the User object
     */
    @Override
    public JsonElement serialize(User src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("name", new JsonPrimitive(src.getName()));
        result.add("type", new JsonPrimitive(src.getClass().getSimpleName()));
        return result;
    }

    /**
     * This method is used to convert JSON to a User object
     *
     * @param json    the JSON representation of the User object
     * @param typeOfT the type of the object
     * @param context the context of the conversion
     * @return the User object
     * @throws JsonParseException if the JSON is not valid
     */
    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String name = jsonObject.get("name").getAsString();

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