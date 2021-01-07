package projet.graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberModifPanel extends JPanel {

    public NumberModifPanel() {
        setLayout(new GridLayout(4,2));
        setBorder(BorderFactory.createEmptyBorder(10,250,10,250));

        JTextField tfNumberEtudiant = new JTextField("1");
        JTextField tfNumberCourse = new JTextField("1");
        JTextField tfNumberBloc = new JTextField("1");
        JTextField tfNumberProgramme = new JTextField("1");

        add(tfNumberEtudiant);
        add(getAddStudentButton());
        add(tfNumberCourse);
        add(getAddCourseButton());
        add(tfNumberBloc);
        add(getAddBlocButton());
        add(tfNumberProgramme);
        add(getAddProgramButton());
    }

    private JButton getAddStudentButton(){
        JButton getAddStudentButton = new JButton("Ajouter des étudiants");

        return getAddStudentButton;
    }

    private JButton getAddCourseButton() {
        JButton getAddCourseButton = new JButton("Ajouter des cours");
        getAddCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ModifCoursePanel modifCoursePanel = new ModifCoursePanel();
                removeAll();
                repaint();
                revalidate();
                add(modifCoursePanel);
                //setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
                //setLayout(new GridLayout(0,1));
                revalidate();
                repaint();
            }
        });

        return getAddCourseButton;
    }

    private JButton getAddBlocButton() {
        JButton getAddBlocButton = new JButton("Ajouter des blocs");

        return getAddBlocButton;
    }

    private JButton getAddProgramButton() {
        JButton getAddProgramButton = new JButton("Ajouter des programmes");

        return getAddProgramButton;
    }
}
