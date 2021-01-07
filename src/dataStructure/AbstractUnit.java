package projet.dataStructure;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractUnit implements Unit {
    private final String code;
    private final String name;
    private List<Student> students;

    public AbstractUnit(String code, String name) {
        this.code = code;
        this.name = name;
        this.students = new ArrayList<>(150);
    }




    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void addStudent(Student student){
        students.add(student);
        if (getChildren().length>0){
            for (Unit child : getChildren()){
                if (! child.isCours() || student.getGrades().keySet().contains(child.getCode())){
                    child.addStudent(student);
                }
            }
        }
    }

    public List<Student> getStudents(){
        return students;
    }

    public String toString() {
        return code + " - " + name ;
    }
}
