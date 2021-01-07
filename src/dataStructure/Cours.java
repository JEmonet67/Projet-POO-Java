package projet.dataStructure;

public class Cours extends AbstractUnit implements Unit {

    private final int credits;

    public Cours(String code, String name, int credits) {
        super(code,name);
        this.credits = credits;
    }

    public boolean isCours(){return true;}

    public Unit[] getChildren(){
        //return new Unit[]{this};
        return new Unit[]{};
    }


    public int getCredits() {
        return credits;
    }

    public double getMoyenne(Student student){
        if (student.getGrades().containsKey(getCode())){
            double note = student.getGrades().get(getCode());
            return note;
        }else {
            return -10;
        }

    }

}
