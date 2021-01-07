package projet.PvEditor;

import projet.dataStructure.Unit;
import projet.dataStructure.Student;

import java.util.*;

public class ProcesVerbal {
    private Unit unit;

    public ProcesVerbal(Unit unit) {
        this.unit = unit;
    }

    private String[] getFirstLine(HashMap<String,List<Double>> unitsNotes){
        List<String> line = new ArrayList(50);
        line.add("N°Etudiant");
        line.add("Nom");
        line.add("Prénom");
        line.add(unit.toString());
        unitsNotes.put(unit.getCode(), new ArrayList<>());

        if (unit.getChildren().length>1){
            for (Unit child : unit.getChildren()){
                line.add(child.toString());
                unitsNotes.put(child.getCode(), new ArrayList<>());
                if (child.getChildren().length>1){
                    for (Unit cours: child.getChildren()){
                        line.add(cours.toString());
                        unitsNotes.put(cours.getCode(), new ArrayList<>());
                    }
                }
            }
        }
        return line.toArray(new String[line.size()]);
    }

    private String[] studentLine(Student student, HashMap<String,List<Double>> unitsNotes){
        List<String> line = new ArrayList(50);
        line.add(student.getId());
        line.add(student.getSurname());
        line.add(student.getName());
        double moyenne=unit.getMoyenne(student);
        if (moyenne < 0) {
            line.add("ABI");
        } else {
            line.add(Double.toString(Math.round(moyenne * 1000.0) / 1000.0));
            unitsNotes.get(unit.getCode()).add(moyenne);
        }

        if (unit.getChildren().length>1) {
            for (Unit child : unit.getChildren()) {
                if (child.isCours()) {
                    Unit cours = child;
                    if (student.getGrades().containsKey(cours.getCode())) {
                        double coursGrade = student.getGrades().get(cours.getCode());
                        if (coursGrade < 0) {
                            line.add("ABI");
                        } else {
                            line.add(Double.toString(Math.round(coursGrade * 1000.0) / 1000.0));
                            unitsNotes.get(cours.getCode()).add(coursGrade);
                        }

                    } else {
                        line.add(" ");
                    }
                } else {
                    Unit bloc = child;
                    double blocGrade = bloc.getMoyenne(student);
                    if (blocGrade < 0) {
                        line.add("ABI");
                    } else {
                        line.add(Double.toString(Math.round(blocGrade * 1000.0) / 1000.0));
                        unitsNotes.get(bloc.getCode()).add(blocGrade);
                    }
                    if (bloc.getChildren().length > 1) {
                        for (Unit cours : bloc.getChildren()) {
                            if (student.getGrades().containsKey(cours.getCode())) {
                                double coursGrade = student.getGrades().get(cours.getCode());
                                if (coursGrade < 0) {
                                    line.add("ABI");
                                } else {
                                    line.add(Double.toString(Math.round(coursGrade * 1000.0) / 1000.0));
                                    unitsNotes.get(cours.getCode()).add(coursGrade);
                                }

                            } else {
                                line.add(" ");
                            }
                        }
                    }
                }
            }
        }
        return line.toArray(new String[line.size()]);
    }

    private String[] statLine(HashMap<String,List<Double>> unitsNotes, Stat stat){
        List<String> line = new ArrayList(50);
        line.add(stat.getLineName());
        line.add(" ");
        line.add(" ");

        double moyenneValue = stat.roundValue(unitsNotes.get(unit.getCode()));
        line.add(Double.toString(moyenneValue));

        if (unit.getChildren().length>1){
            for (Unit child : unit.getChildren()){
                double blocValue = stat.roundValue(unitsNotes.get(child.getCode()));
                line.add(Double.toString(blocValue));

                if (child.getChildren().length>1){
                    for (Unit cours : child.getChildren()){
                        double coursValue= stat.roundValue(unitsNotes.get(cours.getCode()));
                        line.add(Double.toString(coursValue));

                    }
                }
            }
        }

        return line.toArray(new String[line.size()]);
    }




    public List<String[]> createPV(){
        List<Student> students = unit.getStudents();
        System.out.println("ok");
        HashMap<String,List<Double>> unitsNotes = new HashMap<>();

        List<String[]> data = new ArrayList<>(150);

        data.add(getFirstLine(unitsNotes));


        Collections.sort(students);
        for (Student student : students){
            data.add(studentLine(student,unitsNotes));
        }
        data.add(statLine(unitsNotes,new Max()));
        data.add(statLine(unitsNotes,new Min()));
        data.add(statLine(unitsNotes,new Mean()));
        data.add(statLine(unitsNotes,new StandardDev()));

        return data;
    }
}
