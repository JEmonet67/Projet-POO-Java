package projet.dataStructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OptionBloc extends AbstractBloc {
    //Classe représentant un bloc option

    public OptionBloc(String name, String code, List<Cours> coursList) {
        super(name, code, coursList);
    }

    //public boolean isOptional(){return true;}

    public int calculCredits() {
        int defaultCredits = getChildren()[0].getCredits();
        for (int i = 1; i < getChildren().length; i++) {
            if (getChildren()[i].getCredits() != defaultCredits) {
                throw new IllegalArgumentException("Les credits des cours composants l'option "+this.toString()+" ne sont pas égaux.");
            }
        }
        return defaultCredits;
    }

    public double getMoyenne(Student student){
        List<Double> notes = new ArrayList<>(4);
        notes.add(-10.0);
        for (Unit cours : getChildren()){
            if (student.getGrades().containsKey(cours.getCode())){
                double note = student.getGrades().get(cours.getCode());
                if (note >=0) {
                    notes.add(note);
                }
            }
        }
        return Collections.max(notes);
    }

    public void checkCompatibility(Student student){
        //verifie qu'un étudiant rempli les conditions pour être inscrit au bloc
        int compt = 0;
        for (String id: getCoursIds()){
            if (student.getGrades().keySet().contains(id)){
                compt +=1 ;
            }
        }
        if (compt < 1){
            throw new IllegalArgumentException("L'étudiant "+student.toString()+" ne rempli pas les conditions d'inscription a son programme.");
        }
    }
}
