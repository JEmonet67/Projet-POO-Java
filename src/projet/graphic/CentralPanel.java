package projet.graphic;

import projet.dataStructure.Program;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.List;

public class CentralPanel extends JPanel {
    //Classe permettant la création du panel central contenant l'arborescence des unités d'enseignement et la table du pv

    private Collection<Program> programs;
    private TablePanel table = null;

    public CentralPanel(Collection<Program> programs) {
        //initialisation du panel
        super(new BorderLayout());
        this.programs = programs;
        setSize(1500,600);
        setLayout(new BorderLayout());
        //ajout de composant JSplitPane
        add(getSplitPane(),"Center");
    }

    private JSplitPane getSplitPane(){
        //Création du JSplitPane contenant deux panels : un créé par la classe TreePanel et un créé par la classe TablePanel

        //Création des objets TreePanel et TablePanel
        TreePanel tree =  new TreePanel(programs);
        TablePanel table= new TablePanel();
        this.table=table;

        //Etablissement de la connexion : table écoute les changements de tree
        tree.getMyTree().addTreeSelectionListener(table);

        //Création du JSplitPane et ajout des panels
        JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitpane.setResizeWeight(0.4);
        splitpane.add(tree.getJPanel());
        splitpane.add(table.getJPanel());

        return splitpane;
    }

    public List<String[]> getPv() {
        if (table == null){
            return null;
        }else{
            return table.getPv();
        }
    }
}
