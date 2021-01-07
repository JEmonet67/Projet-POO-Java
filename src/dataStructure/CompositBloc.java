package projet.dataStructure;

import java.util.List;

public class CompositBloc extends AbstractBloc {
    public CompositBloc(String name, String code, List<Cours> coursList) {
        super(name, code, coursList);
    }

    public boolean isOptional(){return false;}

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
}
