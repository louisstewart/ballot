package ballot;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BallotFilter {
    private Ballot b;

    public BallotFilter(Ballot b) {
        this.b = b;
    }

    /**
     * Read the tables from the ballot, check them for validity and print to a csv
     *
     * @param csvPath - path to print csv to
     */
    public void readSortPrint(String csvPath) {
        List<Table> t = this.b.getTables();
        Set<Table> valid = new HashSet<>();
        Set<String> names = new HashSet<>();
        Set<Table> output = new HashSet<>();
        valid.addAll(t);

        for(Table vt : valid) {
            boolean tableValid = true;
            for(String name : vt.getNamesStream().collect(Collectors.toList())) {
                if(!names.contains(name)) names.add(name);
                else tableValid = false;
            }
            if(tableValid) output.add(vt);
        }

        String[] header = {"Email", "Head", "Year", "Name1", "Name2", "Name3", "Name4", "Name5", "Name6", "Name7", "Name8", "Name9"};
        try(CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(csvPath), CSVFormat.DEFAULT.withHeader(header))) {
            for(Table table : output) {
                csvPrinter.printRecord(table.getAsList());
            }
            System.out.println("-- Printed CSV file to "+csvPath);
            csvPrinter.flush();
            csvPrinter.close();
        } catch (Exception e) {
            System.out.println("-- Error writing csv");
            e.printStackTrace();
        }
    }
}
