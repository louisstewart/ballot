import java.util.List;

public class Ballot {
    private List<Table> tables;

    public Ballot(List<Table> tables) {
        this.tables = tables;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}
