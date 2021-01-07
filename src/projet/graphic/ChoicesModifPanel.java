package graphic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class ChoicesModifPanel extends JPanel {

    public ChoicesModifPanel() {
        super();
        add(getAddStudentButton());
        add(getSupprStudentButton());
        add(getAddBlocButton());
        add(getAddProgramButton());
    }


    private JButton getAddStudentButton(){
        JButton getAddStudentButton = new JButton("Ajouter Etudiant");

        return getAddStudentButton;
    }

    private JButton getSupprStudentButton() {
        JButton getSupprStudentButton = new JButton("Supprimer Etudiant");

        return getSupprStudentButton;
    }

    private JButton getAddBlocButton() {
        JButton getAddBlocButton = new JButton("Ajouter un bloc");

        return getAddBlocButton;
    }

    private JButton getAddProgramButton() {
        JButton getAddProgramButton = new JButton("Ajouter Programme");

        return getAddProgramButton;
    }
}
