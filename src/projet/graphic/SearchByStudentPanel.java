package projet.graphic;

import projet.dataStructure.Cours;
import projet.dataStructure.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class SearchByStudentPanel extends JPanel {
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

            JPanel choicesModifPanel = new ChoicesModifPanel();
            modificationFrame.add(choicesModifPanel,BorderLayout.NORTH);
            JPanel modifPanel = new ModifPanel();
            modificationFrame.add(modifPanel,BorderLayout.CENTER);

            modificationFrame.setVisible(true);
        }
        });
        return modifButton;
    }

    private JButton createRecherchButton(JTextField tf){
        JButton button = new JButton("Recherhcher");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId=tf.getText();
                if (studentMap.containsKey(studentId)){
                    Student student = studentMap.get(studentId);

                    JFrame studentFrame=new JFrame();
                    studentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    studentFrame.setTitle(student.getName()+' '+student.getSurname()+" - "+studentId);
                    studentFrame.setLayout(new GridLayout(student.getGrades().size(),1));
                    studentFrame.setSize(300,600);
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
                    studentFrame.setVisible(true);
                    studentFrame.pack();
                }else {
                    JOptionPane.showMessageDialog(null,"Entrez un numéro étudiant valide");
                }
            }
        });
        return button;
    }
}
