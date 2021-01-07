package projet.PvEditor;

import projet.dataStructure.Unit;
import projet.dataStructure.Student;

import java.util.*;

public class ProcesVerbal {
    //Classe permettant le calcul du proces verbal d'une unitée d'enseignement

    private Unit unit;
    private HashMap<String,List<Double>> unitsNotes; //stocke toute les notes de chaque unitée d'enseignement (pour permettre les calculs statistiques)

    public ProcesVerbal(Unit unit) {
        this.unit = unit;
    }

    private String[] getFirstLine(){
        //Crée la première ligne d'un procès verbal (nom des colonnes)
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

    private void addNote(double note,List<String>line,Unit unit){
        //ajoute une note a une ligne donnée en argument
        //ajoute aussi cette note au champ unitNote pour l'unitée d'enseignemnt donnée en paramètre

        if (note < 0) {
            line.add("ABI");
        } else {
            line.add(Double.toString(Math.round(note * 1000.0) / 1000.0));
            unitsNotes.get(unit.getCode()).add(note);
        }
    }

    private String[] studentLine(Student student){
        //Crée la ligne du procès verbal qui correspond a un étudiant

        //initialisation
        List<String> line = new ArrayList(50);

        //informations de l'étudiant
        line.add(student.getId());
        line.add(student.getSurname());
        line.add(student.getName());

        //moyenne de l'étudiant a l'unitée d'enseignement dont le procès verbal est créé
        double moyenne=unit.getMoyenne(student);
        addNote(moyenne,line,unit);

        //notes de l'étudiant dans les unitées d'enseignement filles de cette dont le procès verbal est édité
        if (unit.getChildren().length>1) {
            for (Unit child : unit.getChildren()) {
                if (child.isCours()) {
                    Unit cours = child;
                    if (student.getGrades().containsKey(cours.getCode())) {
                        double coursGrade = student.getGrades().get(cours.getCode());
                        addNote(coursGrade,line,cours);
                    } else {
                        line.add(" ");
                    }
                } else {

                    Unit bloc = child;
                    double blocGrade = bloc.getMoyenne(student);
                    addNote(blocGrade,line,bloc);

                    if (bloc.getChildren().length > 1) {
                        for (Unit cours : bloc.getChildren()) {
                            if (student.getGrades().containsKey(cours.getCode())) {
                                double coursGrade = student.getGrades().get(cours.getCode());
                                addNote(coursGrade,line,cours);
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

    private String[] statLine(Stat stat){
        //Crée une linge statitique du procès verbal

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
        //initialisation de unitsNotes vide
        this.unitsNotes = new HashMap<>();

        //initialisation de la variable data qui servira a stocker tout les éléments du "tableau" procès verbal
        List<String[]> data = new ArrayList<>(150);

        //ajout de la ligne d'entête (nom de colones) a data
        data.add(getFirstLine());

        //ajout des lignes correspondants aux étudiants
        List<Student> students = unit.getStudents();
        Collections.sort(students); //tri des étudiants par ordre alphabetique
        for (Student student : students){
            data.add(studentLine(student));
        }

        //ajout des lignes statistiques
        data.add(statLine(new Max()));
        data.add(statLine(new Min()));
        data.add(statLine(new Mean()));
        data.add(statLine(new StandardDev()));

        return data;
    }
}
