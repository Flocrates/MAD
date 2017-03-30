package floris_van_lent_500717249.mad_assignment_1;

/**
 * Created by Floris on 30-03-2017.
 */

public class Serie {
    private String title;
    private String year;

    public Serie() {
    }

    public Serie(String title, String year) {
        this.title = title;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
