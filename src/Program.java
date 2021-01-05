package projet;

import java.util.ArrayList;
import java.util.List;

public class Program {
    private String code;
    private String name;
    private List<Bloc> blocs;
    private int credits;
    private List<Student> students;

    public Program(String code, String name, List<Bloc> blocs) {
        this.code = code;
        this.name = name;
        this.blocs = blocs;
        this.credits=0;
        for (Bloc bloc : blocs){
            this.credits+=bloc.getCredits();
        }
        this.students = new ArrayList<>(70);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public List<Bloc> getBlocs() {
        return blocs;
    }

    public int getCredits() {
        return credits;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public double getMoyenne(Student student){
        double sommeNote=0;
        double sommeCoef=0;
        for (Bloc bloc : getBlocs()){
            double note = bloc.getMoyenne(student);
            if (note >=0 ){
                sommeNote+=note*bloc.getCredits();
                //sommeCoef+=bloc.getCredits();
            }
            sommeCoef+=bloc.getCredits();
        }
        return sommeNote/sommeCoef;
    }


    @Override
    public String toString() {
        return code + " - " + name ;
    }
}
