package projet.graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifPanel extends JPanel {

    public ModifPanel() {
        JPanel topPanel = new JPanel();
        JPanel numberModifPanel = new NumberModifPanel();
        JPanel botPanel = new JPanel();

        topPanel.add(new JLabel("Choisissez ce que vous voulez ajouter et son nombre"));
        JTextField tfSupprEtudiant = new JTextField("Identifiant");
        botPanel.add(new JLabel("Choisissez un étudiant à supprimer"));
        botPanel.add(Box.createHorizontalStrut(50));
        botPanel.add(tfSupprEtudiant);
        botPanel.add(getSupprStudentButton());
        botPanel.setLayout(new GridLayout(2,2));

        add(topPanel, BorderLayout.NORTH);
        add(numberModifPanel, BorderLayout.CENTER);
        add(botPanel, BorderLayout.SOUTH);
    }

    private JButton getSupprStudentButton() {
        JButton getSupprStudentButton = new JButton("Supprimer");

        return getSupprStudentButton;

    }
}

