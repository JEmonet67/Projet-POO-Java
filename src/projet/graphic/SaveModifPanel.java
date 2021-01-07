package projet.graphic;

import javax.swing.*;

public class SaveModifPanel extends JPanel {

    public SaveModifPanel() {
        add(getSaveModifButton());
    }

    private JButton getSaveModifButton() {
        JButton saveModifButton = new JButton("Enregistrer les modifications");

        return saveModifButton;
    }
}
