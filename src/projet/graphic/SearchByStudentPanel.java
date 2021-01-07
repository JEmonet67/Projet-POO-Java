package projet.graphic;

import projet.dataStructure.Cours;
import projet.dataStructure.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class SearchByStudentPanel extends JPanel {
    //Classe permetant la création du panel permettant la recherche des notes par numero étudiant

    private HashMap<String, Student> studentMap;
    private HashMap<String, Cours> coursMap;

    public SearchByStudentPanel(HashMap<String, Student> studentMap,HashMap<String, Cours> coursMap) {
        super();
        this.studentMap=studentMap;
        this.coursMap=coursMap;

        JTextField tf = new JTextField("Numéro étudiant",10);

        add(new JLabel("Afficher les notes d'un étudiant:"));
        add(tf);
        add(createRecherchButton(tf));
        add(createModificationButton());
    }

    private JButton createModificationButton(){
        JButton modifButton = new JButton("Modifier la base de données");
        modifButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame modificationFrame = new JFrame();
                modificationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                modificationFrame.setTitle("Modification de la base de données");
                modificationFrame.setLayout(new BorderLayout());
                modificationFrame.setSize(1000,600);

                ModifPanel modifPanel = new ModifPanel();
                modificationFrame.add(modifPanel,BorderLayout.CENTER);
                JPanel choicesModifPanel = new ChoicesModifPanel(modifPanel);
                modificationFrame.add(choicesModifPanel,BorderLayout.NORTH);
                JPanel saveModifPanel = new SaveModifPanel();
                modificationFrame.add(saveModifPanel, BorderLayout.SOUTH);

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
                if (studentMap.containsKey(studentId)){//verifier si le numéro étudiant entré est valide
                    Student student = studentMap.get(studentId);
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
            String label = coursMap.get(grade).toString();
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
