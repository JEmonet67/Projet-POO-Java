package projet.graphic;

import projet.dataStructure.Cours;
import projet.dataStructure.Program;
import projet.dataStructure.Student;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.HashMap;

public class GUI extends JFrame {
    private Collection<Program> programs;
    private HashMap<String, Student> studentMap;
    private HashMap<String, Cours> coursMap;

    public GUI(Collection<Program> programs,HashMap<String,Student> studentMap,HashMap<String, Cours> coursMap) throws HeadlessException {
        super();
        this.programs=programs;
        this.studentMap=studentMap;
        this.coursMap=coursMap;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("projet");
        setPreferredSize(new Dimension(1200, 600));
        //setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        CentralPanel centralpanel= new CentralPanel(programs);
        centralpanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,0));
        add(centralpanel,BorderLayout.CENTER);

        JPanel savePvPanel = new SavePvPanel(centralpanel,this);
        add(savePvPanel,BorderLayout.SOUTH);

        JPanel searchStudent = new SearchByStudentPanel(studentMap,coursMap);
        add(searchStudent,BorderLayout.NORTH);

    }
}
