package projet;

import java.util.HashSet;
import java.util.List;

public abstract class AbsractBloc implements Bloc {
    private String name;
    private String code;
    private int credits;
    private HashSet<String> coursIds;
    private List<Cours> coursList;

    public AbsractBloc(String name, String code, List<Cours> coursList) {
        this.name = name;
        this.code = code;
        if (this.isOptional()){
            this.credits = calculOptionalCredits(coursList);
        } else {
            this.credits = calculUnoptionalCredits(coursList);
        }
        this.coursIds = getCoursIds(coursList);
        this.coursList=coursList;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public int getCredits() {
        return credits;
    }

    @Override
    public HashSet<String> getCoursIds() {
        return coursIds;
    }

    public List<Cours> getCoursList() {
        return coursList;
    }

    private HashSet<String> getCoursIds(List<Cours> coursList) {
        HashSet<String> coursIds = new HashSet<>();
        for(Cours cours : coursList){
            coursIds.add(cours.getCode());
        }
        return coursIds;
    }

    private static int calculUnoptionalCredits(List<Cours> coursList) {
        int defaultCredits = 0;
        for (Cours cours : coursList) {
            defaultCredits += cours.getCredits();
        }
        return defaultCredits;
    }

    private static int calculOptionalCredits(List<Cours> coursList) {
        int defaultCredits = coursList.get(0).getCredits();
        for (int i = 1; i < coursList.size(); i++) {
            if (coursList.get(i).getCredits() != defaultCredits) {
                throw new IllegalArgumentException("les credits des cours composant l'option ne sont pas égaux.");
            }
        }
        return defaultCredits;
    }



    @Override
    public String toString() {
        return code + " - " + name ;
    }
}
