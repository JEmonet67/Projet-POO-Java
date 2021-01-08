package projet.graphic;

public class CourseModifDisplay extends InputNumberFrame {
    public CourseModifDisplay(String titre, ModifPanel outputPanel) {
        super(titre,outputPanel);
    }

    public void modifDisplay(int number) {
        outputPanel.modifCourseDisplay(number);
    }
}