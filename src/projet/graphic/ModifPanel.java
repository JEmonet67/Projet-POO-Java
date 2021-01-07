package projet.graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifPanel extends JPanel {

    public void modifCourseDisplay() {
        removeAll();
        repaint();
        revalidate();

        JTextField tfCode = new JTextField("Code UE",10);
        JTextField tfName = new JTextField("Nom",10);
        JTextField tfCredits = new JTextField("Crédits",10);
        add(new JLabel("Entrer les informations sur le cours à ajouter :"));
        add(tfCode);
        add(tfName);
        add(tfCredits);

        setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        setLayout(new GridLayout(5, 1));
        revalidate();
        repaint();
    }

    public void modifBlocDisplay() {

    }

    public void modifStudentDisplay() {

    }

    public void modifProgrammDisplay() {

    }
}

