package projet.dataStructure;

import java.util.List;

public class CompositBloc extends AbstractBloc {
    //Classe représentant un bloc composite (et les blocs simples qui sont modelisés comme des blocs composites composés d'un unique cours)

    public CompositBloc(String name, String code, List<Cours> coursList) {
        super(name, code, coursList);
    }

    public int calculCredits() {
        int defaultCredits = 0;
        for (Unit cours : getChildren()) {
            defaultCredits += cours.getCredits();
        }
        return defaultCredits;
    }

    //public boolean isOptional(){return false;}

    public double getMoyenne(Student student){
        double note;
        if (getChildren().length < 2){
            note = student.getGrades().get(getChildren()[0].getCode());
        }else{
            double sommeNote=0;
            double sommeCoef=0;
            for (Unit cours : getChildren()){
                double value = student.getGrades().get(cours.getCode());
                if (value >=0 ){
                    sommeNote+=value*cours.getCredits();
                    }
                sommeCoef+=cours.getCredits();
            }
            note = sommeNote/sommeCoef;
        }
    return note;
    }

    public void checkCompatibility(Student student){
        //verifie qu'un étudiant rempli les conditions pour être inscrit au bloc
        if (! student.getGrades().keySet().containsAll(getCoursIds())){
            throw new IllegalArgumentException("L'étudiant "+student.toString()+" ne remplie pas les conditions d'inscription a son programme.");
        }
    }
}
