package projet.graphic;

import projet.dataStructure.Cours;
import projet.dataStructure.Program;
import projet.dataStructure.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class SearchByStudentPanel extends JPanel {
    //Classe permetant la création du panel permettant la recherche des notes par numero étudiant

//    private HashMap<String, Student> studentMap;
//    private HashMap<String, Cours> coursMap;
//    private HashMap<String, Program> programsMap;
    private GUI gui;

    public SearchByStudentPanel(GUI gui) {
        super();
//        this.studentMap=studentMap;
//        this.coursMap=coursMap;
//        this.programsMap = programsMap;
        this.gui = gui;

        JTextField tf = new JTextField("Numéro étudiant",10);

        add(new JLabel("Afficher ou modifier les notes d'un étudiant:"));
        add(tf);
        add(createRecherchButton(tf));
        add(createModifStudentButton(tf));
        add(Box.createHorizontalStrut(150));
        add(createModifDbButton());
    }

    private JButton createModifStudentButton(JTextField tf) {
        JButton modifStudentbutton = new JButton("Modifier");

        return modifStudentbutton;
    }

    private JButton createModifDbButton(){
        JButton modifButton = new JButton("Modifier la base de données");
        modifButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame modificationFrame = new JFrame();
                modificationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                modificationFrame.setTitle("Modification de la base de données");
                modificationFrame.setLayout(new BorderLayout());
                modificationFrame.setSize(1000,600);

                ModifPanel modifPanel = new ModifPanel(modificationFrame,gui);
                modificationFrame.add(modifPanel,BorderLayout.CENTER);
                JPanel choicesModifPanel = new ChoicesModifPanel(modifPanel,gui);
                modificationFrame.add(choicesModifPanel,BorderLayout.NORTH);

                modificationFrame.setVisible(true);
            }
        });
        return modifButton;
    }

    private JButton createRecherchButton(JTextField tf){
        //Création du bouton
        JButton button = new JButton("Rechercher");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //actions effectuées lorsqu'on appuie sur le bouton
                String studentId=tf.getText().strip();
                if (gui.getStudentMap().containsKey(studentId)){//verifier si le numéro étudiant entré est valide
                    Student student = gui.getStudentMap().get(studentId);
                    displayStudentFrame(student);
                }else {
                    JOptionPane.showMessageDialog(null,"Entrez un numéro étudiant valide");
                }
            }
        });
        return button;
    }

    private void displayStudentFrame(Student student){
        //Affiche une fenetre contenant les notes de l'étudiant donné en paramètre

        //Initialisation de la fenêtre
        JFrame studentFrame=new JFrame();
        studentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        studentFrame.setTitle(student.toString());
        studentFrame.setLayout(new GridLayout(student.getGrades().size(),1));
        studentFrame.setSize(300,600);

        //Ajout des notes
        for (String grade : student.getGrades().keySet()){
            String label = gui.getCoursMap().get(grade).toString();
            String note;
            if (student.getGrades().get(grade)<0){
                note="ABI";
            }else {
                note=student.getGrades().get(grade).toString();
            }
            studentFrame.add(new JLabel(label+" : "+note));
        }

        //Affichage de la fenêtre
        studentFrame.setVisible(true);
        studentFrame.pack();
    }
}
