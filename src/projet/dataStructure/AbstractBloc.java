package projet.dataStructure;

import java.util.HashSet;
import java.util.List;

public abstract class AbstractBloc extends AbstractUnit implements Bloc {
    //Classe abstraite servant de classe mère aux blocs

    private final int credits;
    private HashSet<String> coursIds;
    private List<Cours> coursList;

    public AbstractBloc(String name, String code, List<Cours> coursList) {
        super(code,name);
        this.coursList=coursList;
        setCoursIds();
        this.credits=calculCredits();
    }

    public boolean isCours(){ return false; }

    public int getCredits() { return credits; }

    public HashSet<String> getCoursIds() { return coursIds; }

    public Unit[] getChildren() {
        return coursList.toArray(new Unit[coursList.size()]);
    }


    private void setCoursIds() {
        HashSet<String> coursIds = new HashSet<>();
        for(Cours cours : coursList){
            coursIds.add(cours.getCode());
        }
        this.coursIds=coursIds;
    }


}
