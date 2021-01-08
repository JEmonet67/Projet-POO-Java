package projet.graphic;

public class ProgramModifDisplay extends InputNumberFrame {
    public ProgramModifDisplay(String titre, ModifPanel outputPanel) {
        super(titre,outputPanel);
    }

    public void modifDisplay(int number) {
        outputPanel.modifProgrammDisplay(number);
    }
}
