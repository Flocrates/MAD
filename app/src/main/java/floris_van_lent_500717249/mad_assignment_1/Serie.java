package floris_van_lent_500717249.mad_assignment_1;

/**
 * Created by Floris on 30-03-2017.
 */

public class Serie {
    private String title;
    private boolean seen;

    public Serie() {
    }

    public Serie(String title) {
        this(title, false);
    }

    public Serie(String title, boolean seen) {
        this.title = title;
        this.seen = seen;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}
