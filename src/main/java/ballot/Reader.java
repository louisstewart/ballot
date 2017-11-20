package ballot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Reader {

    public static void main(String[] args) {
        Resource jsonFile = new ClassPathResource("fs-ballot-export.json");
        Gson gson = new GsonBuilder().registerTypeAdapter(Ballot.class, new BallotDeserialiser()).create();
        try {
            File f = jsonFile.getFile();
            String json = new String(Files.readAllBytes(Paths.get(f.getAbsolutePath())));
            System.out.println(json);
            Ballot b = gson.fromJson(json, Ballot.class);
            BallotFilter bf = new BallotFilter(b);
            bf.readSortPrint(new ClassPathResource("tables_clean.csv").getPath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
