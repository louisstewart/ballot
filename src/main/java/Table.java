/**
 * Table pojo for json deserialiser
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
