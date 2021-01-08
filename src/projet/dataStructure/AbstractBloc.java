package projet.dataStructure;

import java.util.HashSet;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public abstract class AbstractBloc extends AbstractUnit implements Bloc {
    //Classe abstraite servant de classe mère aux blocs

    private final int credits;
    private HashSet<String> coursIds;
    private List<Cours> coursList;
    private static HashMap<String, Bloc> blocsMap = new HashMap<>();

    public AbstractBloc(String name, String code, List<Cours> coursList) {
        super(code,name);
        this.coursList=coursList;
        setCoursIds();
        this.credits=calculCredits();
        blocsMap.put(this.toString(),this);
    }

    public boolean isCours(){ return false; }

    public int getCredits() { return credits; }

    protected abstract void checkCoursAddition(Cours cours);

    public void addCours(Cours cours){
        checkCoursAddition(cours);
        this.coursList.add(cours); }

    public HashSet<String> getCoursIds() { return coursIds; }

    public Unit[] getChildren() {
        return coursList.toArray(new Unit[coursList.size()]);
    }

    public static HashMap<String,Bloc> getBlocsMap() {
        return blocsMap;
    }

    private void setCoursIds() {
        HashSet<String> coursIds = new HashSet<>();
        for(Cours cours : coursList){
            coursIds.add(cours.getCode());
        }
        this.coursIds=coursIds;
    }


}
