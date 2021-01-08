package projet.graphic;

public class BlocModifDisplay extends InputNumberFrame {
    public BlocModifDisplay(String titre, ModifPanel outputPanel) {
        super(titre,outputPanel);
    }

    public void modifDisplay(int number) {
        outputPanel.modifBlocDisplay(number);
    }
}