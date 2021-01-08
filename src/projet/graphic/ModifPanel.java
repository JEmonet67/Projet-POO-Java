package projet.graphic;

import javax.swing.*;
import java.awt.*;
import projet.dataStructure.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;



public class ModifPanel extends JPanel {
    private JFrame frame;
    private GUI gui;

    public ModifPanel(JFrame frame, GUI gui) {
        this.frame = frame;
        this.gui = gui;
    }

    public void modifCourseDisplay(int number) {
        removeAll();
        repaint();
        revalidate();

        for(int i=0;i<number;i++) {
            JTextField tfCode = new JTextField("Code UE", 10);
            JTextField tfName = new JTextField("Nom", 10);
            JTextField tfCredits = new JTextField("Crédits", 10);
            add(new JLabel("Entrer les informations sur le cours à ajouter :"));
            add(tfCode);
            add(tfName);
            add(tfCredits);
        }
        setBorder(BorderFactory.createEmptyBorder(10, 250, 10, 250));
        setLayout(new GridLayout(15+number, 1));
        revalidate();
        repaint();
    }

    public void modifBlocDisplay(int number) {
        removeAll();
        repaint();
        revalidate();

        java.util.List<JComboBox> listCb = new ArrayList<>();

        JCheckBox cbOption = new JCheckBox("Bloc d'option");
        JTextField tfCode = new JTextField("Code UE", 10);
        JTextField tfName = new JTextField("Nom", 10);
        add(new JLabel("Entrer les informations sur le cours à ajouter :"));
        add(cbOption);
        add(tfCode);
        add(tfName);

        for(int i=0;i<number;i++) {
            JComboBox<String> cb = getCourseComboBox(gui.getCoursMap());
            listCb.add(cb);
            add(cb);
        }

        setBorder(BorderFactory.createEmptyBorder(10, 250, 10, 250));
        setLayout(new GridLayout(10+number, 1));
        revalidate();
        repaint();
    }

    public void modifStudentDisplay() {
        removeAll();
        repaint();
        revalidate();

        JTextField tfId = new JTextField("Numéro", 10);
        JTextField tfName = new JTextField("Nom", 10);
        JTextField tfSurname = new JTextField("Prénom", 10);
        JTextField tfProgram = new JTextField("ID Programme", 10);
        add(new JLabel("Entrer les informations sur le cours à ajouter :"));
        add(tfId);
        add(tfName);
        add(tfSurname);
        add(tfProgram);

        setBorder(BorderFactory.createEmptyBorder(10, 250, 10, 250));
        setLayout(new GridLayout(5, 1));
        revalidate();
        repaint();
    }

    public void modifProgrammDisplay(int number) {
        removeAll();
        repaint();
        revalidate();

        JTextField tfCode = new JTextField("Code Programme", 10);
        JTextField tfName = new JTextField("Nom", 10);
        add(new JLabel("Entrer les informations sur le cours à ajouter :"));
        add(tfCode);
        add(tfName);

        java.util.List<JComboBox> listCb = new ArrayList<>();
        for(int i=0;i<number;i++) {
            JComboBox<String> cb = getBlocComboBox(AbstractBloc.getBlocsMap());
            listCb.add(cb);
            add(cb);
        }

        JPanel saveProgramPanel = new SaveProgramPanel(tfCode, tfName,
                listCb,gui);
        frame.add(saveProgramPanel, BorderLayout.SOUTH);


        setBorder(BorderFactory.createEmptyBorder(10, 250, 10, 250));
        setLayout(new GridLayout(10+number, 1));
        revalidate();
        repaint();
    }

    private JComboBox<String> getBlocComboBox(HashMap<String,Bloc> unitsMap) {
        java.util.List<String> list = new ArrayList<>();
        list.add("");
        for(Bloc bloc:unitsMap.values()){
            list.add(bloc.toString());
        }
        int size = list.size();
        String[] unitsChoices = list.toArray(new String[size]);
        final JComboBox<String> cb = new JComboBox<String>(unitsChoices);

        return cb;
    }

    private JComboBox<String> getCourseComboBox(HashMap<String,Cours> unitsMap) {
        java.util.List<String> list = new ArrayList<>();
        list.add("");
        for(Cours cours:unitsMap.values()){
            list.add(cours.toString());
        }
        int size = list.size();
        String[] unitsChoices = list.toArray(new String[size]);
        final JComboBox<String> cb = new JComboBox<String>(unitsChoices);

        return cb;
    }
}

