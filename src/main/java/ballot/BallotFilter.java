package ballot;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.util.ArrayList;
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
        List<Table> removed = new ArrayList<>();
        valid.addAll(t);

        System.out.printf("-- %d tables in input", valid.size());

        for(Table vt : valid) {
            boolean tableValid = true;
            for(String name : vt.getNamesStream().collect(Collectors.toList())) {
                if(!names.contains(name)) names.add(name);
                else tableValid = false;
            }
            if(tableValid) output.add(vt);
            else removed.add(vt);
        }

        String[] header = {"Email", "Head", "Year", "Name1", "Name2", "Name3", "Name4", "Name5", "Name6", "Name7", "Name8", "Name9"};
        try(CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(csvPath), CSVFormat.DEFAULT.withHeader(header))) {
            for(Table table : output) {
                csvPrinter.printRecord(table.getAsList());
            }
            System.out.println("-- Printed CSV file to "+csvPath);
            System.out.printf("-- %d tables in output\n", output.size());
            removed.forEach(System.out::println);
            csvPrinter.flush();
            csvPrinter.close();
        } catch (Exception e) {
            System.out.println("-- Error writing csv");
            e.printStackTrace();
        }

        try(CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(csvPath.replace("clean", "removed")), CSVFormat.DEFAULT.withHeader(header))) {
            for(Table rem: removed) {
                csvPrinter.printRecord(rem.getAsList());
            }
            System.out.println("-- Wrote removed tables csv");
            csvPrinter.flush();
            csvPrinter.close();
        } catch (Exception e) {
            System.out.println("-- Error writing removed tables csv");
            e.printStackTrace();
        }
    }
}
