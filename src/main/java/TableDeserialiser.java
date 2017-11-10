import com.google.gson.*;

import java.lang.reflect.Type;

public class TableDeserialiser implements JsonDeserializer<Table> {
    @Override
    public Table deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jobj = json.getAsJsonObject();

        final String email = jobj.get("emailAddress").getAsString();
        final String first = jobj.get("firstName").getAsString();
        final String last = jobj.get("lastName").getAsString();
        final String head = first + " " + last;

        final boolean vip = jobj.get("vip").getAsBoolean();
        final String year = jobj.get("year").getAsString();

        String[] names = new String[9];
        for(int i = 1; i < 10; i++) {
            names[i-1] = jobj.get("name"+i).getAsString();
        }

        return new Table(email, head, names, vip, year);
    }
}
