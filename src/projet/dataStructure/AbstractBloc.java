package projet.dataStructure;

import java.util.HashSet;
import java.util.List;

public abstract class AbstractBloc extends AbstractUnit implements Bloc {

    private final int credits;
    private HashSet<String> coursIds;
    private List<Cours> coursList;

    public AbstractBloc(String name, String code, List<Cours> coursList) {
        super(code,name);
        if (this.isOptional()){
            this.credits = calculOptionalCredits(coursList);
        } else {
            this.credits = calculUnoptionalCredits(coursList);
        }
        this.coursIds = getCoursIds(coursList);
        this.coursList=coursList;
    }

    public boolean isCours(){return false;}

        public int getCredits() { return credits; }

    @Override
    public HashSet<String> getCoursIds() { return coursIds;  }

    public Unit[] getChildren() {
        return coursList.toArray(new Unit[coursList.size()]);
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

}
