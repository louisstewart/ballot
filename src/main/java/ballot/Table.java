package ballot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * ballot.Table pojo for json deserialiser
 */
public class Table {
    private String email;
    private String head;
    private String[] names;
    private boolean  vip;
    private String year;

    public Table(String email, String head, String[] names, boolean vip, String year) {
       if(names.length != 9) throw new AssertionError("Not enough names");
       this.email = email;
       this.head = head;
       this.names = names;
       this.vip = vip;
       this.year = year;
    }

    public int hashCode() {
        List<String> names = new ArrayList<>();
        Collections.addAll(names, this.names);
        names.add(head);
        return names.stream().map(name -> name.toLowerCase().replace(" ", "")).reduce("", (a, b)-> a+b).hashCode();
    }

    public List<String> getAsList() {
        List<String> table = new ArrayList<>();
        table.add(email);
        table.add(head);
        table.add(year);
        Collections.addAll(table, names);
        return table;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Email: ").append(email).append("\n")
                .append("Head: ").append(head).append("\n")
                .append("Year: ").append(year).append("\n");
        for(String name: names) {
            sb.append("Name: ").append(name).append("\n");
        }
        return sb.toString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String[] getNames() {
        return names;
    }

    public Stream<String> getNamesStream() {
        List<String> names = new ArrayList<>();
        Collections.addAll(names, this.names);
        names.add(head);
        return names.stream().map(name -> name.toLowerCase().replace(" ", ""));
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
