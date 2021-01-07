package projet.dataStructure;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractUnit implements Unit {
    //Classe abstraite servant de classe mère a toutes les unités d'enseignement (cours, bloc ou programme)

    private final String code;
    private final String name;
    private List<Student> students;

    public AbstractUnit(String code, String name) {
        this.code = code;
        this.name = name;
        //La liste des étudiants inscrits à l'unité d'enseignement est initialisée vide.
        //Les étudiants y sont ajoutés lors de leur création.
        this.students = new ArrayList<>(150);
    }


    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void addStudent(Student student){
        //Methode pour ajouter un étudiant a l'unité d'enseignement.
        //Si cette unité d'enseigneemnt a des unités filles, l'étudiant y est aussi ajouté s'il y est inscrit.
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
