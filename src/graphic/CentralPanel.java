package projet.graphic;

import projet.dataStructure.Program;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.List;

public class CentralPanel extends JPanel {
    private Collection<Program> programs;
    private TablePanel tablePanel = null;

    public CentralPanel(Collection<Program> programs) {
        super(new BorderLayout());
        this.programs = programs;
        setSize(1500,600);
        setLayout(new BorderLayout());
        add(getSplitPane(),"Center");
    }

    private JSplitPane getSplitPane(){

        TreePanel leftpanel = getJTreeContainer();
        TablePanel rightpanel= getRightTableContainer();
        this.tablePanel=rightpanel;

        leftpanel.getMyTree().addTreeSelectionListener(rightpanel);

        JSplitPane splitpanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitpanel.setResizeWeight(0.4);
        splitpanel.add(leftpanel.getJPanel());
        splitpanel.add(rightpanel.getJPanel());


        return splitpanel;
    }

    private TablePanel getRightTableContainer(){
        TablePanel tablePanel=new TablePanel();
        return tablePanel;
    }

    private TreePanel getJTreeContainer(){
        TreePanel myTree = new TreePanel(programs);
        return myTree;
    }

    public List<String[]> getPv() {
        if (tablePanel == null){
            return null;
        }else{
            return tablePanel.getPv();
        }
    }
}
