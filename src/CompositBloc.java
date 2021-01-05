package projet;

import projet.AbsractBloc;

import java.util.List;

public class CompositBloc extends AbsractBloc {
    public CompositBloc(String name, String code, List<Cours> coursList) {
        super(name, code, coursList);
    }

    public boolean isOptional(){return false;}

    public double getMoyenne(Student student){
        double note;
        if (getCoursList().size()<2){
            note = student.getGrades().get(getCoursList().get(0).getCode());
        }else{
            double sommeNote=0;
            double sommeCoef=0;
            for (Cours cours : getCoursList()){
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
