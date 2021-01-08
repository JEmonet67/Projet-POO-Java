package projet.dataStructure;

import java.util.*;

public class Program extends AbstractUnit implements Unit {
    //Classe représentant un programme

    private List<Bloc> blocs;
    private int credits;

    public Program(String code, String name, List<Bloc> blocs) {
        super(code, name);
        this.blocs = blocs;
        this.credits=0;
        for (Bloc bloc : blocs){
            this.credits+=bloc.getCredits();
        }
    }

    public boolean isCours(){ return false; }

    public Unit[] getChildren() {
        return blocs.toArray(new Unit[blocs.size()]);
    }

    public int getCredits() {
        return credits;
    }

    public void addBloc(Bloc bloc){
        this.blocs.add(bloc);
        this.credits += bloc.getCredits();
    }

    public void addCours(Cours cours){
        List<Cours> coursList= new ArrayList<>();
        coursList.add(cours);
        this.blocs.add(new CompositBloc(cours.getName(), cours.getCode(),coursList));
        this.credits+=cours.getCredits();
    }

    public double getMoyenne(Student student){
        double sommeNote=0;
        double sommeCoef=0;
        for (Unit bloc : getChildren()){
            double note = bloc.getMoyenne(student);
            if (note >=0 ){
                sommeNote+=note*bloc.getCredits();
            }
            sommeCoef+=bloc.getCredits();
        }
        return sommeNote/sommeCoef;
    }

}
