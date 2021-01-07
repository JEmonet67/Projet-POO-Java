package projet.graphic;

import javax.swing.*;

public class ModifCoursePanel extends JPanel {

    public ModifCoursePanel() {
        JTextField tfCode = new JTextField("Code UE",10);
        JTextField tfName = new JTextField("Nom",10);
        JTextField tfCredits = new JTextField("Crédits",10);
        add(new JLabel("Entrer les informations sur le cours à ajouter :"));
        add(tfCode);
        add(tfName);
        add(tfCredits);


    }

}
