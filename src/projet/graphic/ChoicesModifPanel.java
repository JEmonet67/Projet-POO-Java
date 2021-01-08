package projet.graphic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.awt.*;
import projet.dataStructure.Student;

public class ChoicesModifPanel extends JPanel {
    ModifPanel modifPanel;
    HashMap<String,Student> studentMap;

    public ChoicesModifPanel(ModifPanel modifPanel,
                             GUI gui) {
        this.modifPanel = modifPanel;
        this.studentMap = gui.getStudentMap();
        add(getAddStudentButton());
        add(getSupprStudentButton());
        add(getAddCourseButton());
        add(getAddBlocButton());
        add(getAddProgramButton());
    }


    private JButton getAddStudentButton() {
        JButton addStudentButton = new JButton("Ajouter un étudiant");
        addStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        return addStudentButton;
    }

    private JButton getSupprStudentButton() {
        JButton supprStudentButton = new JButton("Supprimer un étudiant");
        supprStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new InputIDFrame("Suppression étudiant",modifPanel);
            }
        });

        return supprStudentButton;
    }

    private JButton getAddCourseButton() {
        JButton addCourseButton = new JButton("Ajouter un cours");
        addCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CourseModifDisplay("Nombre de cours à ajouter", modifPanel);
            }
        });

        return addCourseButton;
    }

    private JButton getAddBlocButton() {
        JButton addBlocButton = new JButton("Ajouter un bloc");
        addBlocButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BlocModifDisplay("Nombre de cours du bloc", modifPanel);
            }
            });

        return addBlocButton;
    }

    private JButton getAddProgramButton() {
        JButton addProgramButton = new JButton("Ajouter un programme");
        addProgramButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ProgramModifDisplay("Nombre de blocs du programme",modifPanel);
            }
        });

        return addProgramButton;
    }
}
