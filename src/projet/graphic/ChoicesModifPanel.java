package projet.graphic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;
import java.awt.*;

public class ChoicesModifPanel extends JPanel {
    ModifPanel modifPanel;

    public ChoicesModifPanel(ModifPanel modifPanel) {
        this.modifPanel = modifPanel;
        add(getAddStudentButton());
        add(getSupprStudentButton());
        add(getAddCourseButton());
        add(getAddBlocButton());
        add(getAddProgramButton());
    }


    private JButton getAddStudentButton() {
        JButton getAddStudentButton = new JButton("Ajouter un étudiant");
        getAddStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getNumberFrame("cours");
            }
        });

        return getAddStudentButton;
    }

    private JButton getSupprStudentButton() {
        JButton getSupprStudentButton = new JButton("Supprimer un étudiant");
        getSupprStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        return getSupprStudentButton;
    }

    private JButton getAddCourseButton() {
        JButton getAddCourseButton = new JButton("Ajouter un cours");
        getAddCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getNumberFrame("cours");
            }
        });

        return getAddCourseButton;
    }

    private JButton getAddBlocButton() {
        JButton getAddBlocButton = new JButton("Ajouter un bloc");
        getAddBlocButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getNumberFrame("blocs");
            }
        });

        return getAddBlocButton;
    }

    private JButton getAddProgramButton() {
        JButton getAddProgramButton = new JButton("Ajouter un programme");
        getAddProgramButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getNumberFrame("programme");
            }
        });
        return getAddProgramButton;
    }

    private JFrame getNumberFrame(String type) {
        JFrame numberFrame = new JFrame();
        numberFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        numberFrame.setLayout(new BorderLayout());
        numberFrame.setTitle("Ajout de " + type);
        numberFrame.setSize(300, 75);

        JPanel panelNumber = new JPanel();
        panelNumber.add(new JLabel("Nombre à ajouter"));
        JTextField tfNumber = new JTextField("1", 5);
        panelNumber.add(tfNumber);
        panelNumber.add(getAddNumberButton(tfNumber, type, numberFrame));
        numberFrame.add(panelNumber, BorderLayout.CENTER);

        numberFrame.setVisible(true);

        return numberFrame;
    }

    private JButton getAddNumberButton(JTextField tf,String type, JFrame frame) { ;
        Integer number = Integer.parseInt(tf.getText().strip());

        JButton getAddNumberButton = new JButton("Valider");
        getAddNumberButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (type == "bloc") {
                    //modifPanel.modifBlocDisplay();
                }
                else if (type == "cours") {
                    modifPanel.modifCourseDisplay();
                    frame.dispose();
                }
                else if (type == "programme") {
                    //modifPanel.modifProgrammDisplay();
                }
                else if (type == "étudiant") {
                    //modifPanel.modifStudentDisplay();
                }
            }
        });

        return getAddNumberButton;
    }
}
