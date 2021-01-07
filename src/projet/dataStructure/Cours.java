package projet.dataStructure;

public class Cours extends AbstractUnit implements Unit {
    //Classe representant un cours

    private final int credits;

    public Cours(String code, String name, int credits) {
        super(code,name);
        this.credits = credits;
    }

    public boolean isCours(){return true;}

    public Unit[] getChildren(){
        //Un cours n'a pas d'unité d'enseignement fille
        return new Unit[]{};
    }


    public int getCredits() {
        return credits;
    }

    public double getMoyenne(Student student){
        //La moyenne d'un étudiant a un cours est sa note a ce cours
        if (student.getGrades().containsKey(getCode())){
            double note = student.getGrades().get(getCode());
            return note;
        }else {
            return -10;
        }

    }

}
