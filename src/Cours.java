package projet;

public class Cours {

    private final String code;
    private final String name;
    private final int credits;

    public Cours(String code, String name, int credits) {
        this.code = code;
        this.name = name;
        this.credits = credits;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public String toString() {
        return code + " - " + name ;
    }
}
