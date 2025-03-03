package projet.dataStructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OptionBloc extends AbstractBloc {
    public OptionBloc(String name, String code, List<Cours> coursList) {
        super(name, code, coursList);
    }

    public boolean isOptional(){return true;}

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
}
