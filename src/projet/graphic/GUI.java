package projet.graphic;

import projet.dataStructure.Cours;
import projet.dataStructure.Program;
import projet.dataStructure.Student;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.HashMap;

public class GUI extends JFrame {
    //Classe permettant la création et le lancement de la fenêtre principale de l'interface graphique

    private HashMap<String, Program> programsMap;
    private HashMap<String, Student> studentMap;
    private HashMap<String, Cours> coursMap;
    private CentralPanel centralpanel;

    public GUI(HashMap<String, Program> programsMap,HashMap<String,Student> studentMap,HashMap<String, Cours> coursMap) throws HeadlessException {
        super();

        //initialisation des champs
        this.programsMap=programsMap;
        this.studentMap=studentMap;
        this.coursMap=coursMap;

        //definition des caractéristiques de la fenêtre
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("projet");
        setPreferredSize(new Dimension(1200, 600));
        setLayout(new BorderLayout());

        //Ajout des panels
        addCentralPanel();
        addSavePvPanel();
        addSearchStudentPanel();

        //Rendre la fenêtre visible
        setVisible(true);
        pack();
    }

    public void addCentralPanel(){
        Collection<Program> programs = programsMap.values();
        CentralPanel centralpanel= new CentralPanel(programs);
        centralpanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,0));
        this.centralpanel=centralpanel;
        add(centralpanel,BorderLayout.CENTER);
    }

    public void addSavePvPanel(){
        JPanel savePvPanel = new SavePvPanel(centralpanel,this);
        add(savePvPanel,BorderLayout.SOUTH);
    }

    public void addSearchStudentPanel(){
        JPanel searchStudent = new SearchByStudentPanel(this);
        add(searchStudent,BorderLayout.NORTH);
    }

    public HashMap<String, Program> getProgramsMap() {
        return programsMap;
    }

    public HashMap<String, Student> getStudentMap() {
        return studentMap;
    }

    public HashMap<String, Cours> getCoursMap() {
        return coursMap;
    }

    public void setProgramsMap(HashMap<String, Program> programsMap) {
        this.programsMap = programsMap;
    }

    public void setStudentMap(HashMap<String, Student> studentMap) {
        this.studentMap = studentMap;
    }

    public void setCoursMap(HashMap<String, Cours> coursMap) {
        this.coursMap = coursMap;
    }
}
