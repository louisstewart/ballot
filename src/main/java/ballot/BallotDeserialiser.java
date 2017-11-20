package ballot;

import ballot.Ballot;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BallotDeserialiser implements JsonDeserializer<Ballot> {
    final Gson  gson = new GsonBuilder().registerTypeAdapter(Table.class, new TableDeserialiser()).create();


    @Override
    public Ballot deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jobj = json.getAsJsonObject();
        JsonObject ballot = jobj.get("ballot").getAsJsonObject();
        List<Table> tables = ballot.entrySet().stream().map(i -> gson.fromJson(i.getValue(), Table.class)).collect(Collectors.toCollection(ArrayList::new));

        return new Ballot(tables);
    }
}
