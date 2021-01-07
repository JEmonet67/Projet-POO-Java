package projet.graphic;

import projet.dataStructure.Program;
import projet.dataStructure.Unit;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.Collection;

public class TreePanel {
    private JPanel jPanel = new JPanel();
    private JTree myTree;
    private Collection<Program> programs;

    public TreePanel(Collection<Program> programs) {
        this.programs=programs;
        this.myTree = createTree();
        jPanel.setLayout(new GridLayout(1,1));
        jPanel.setSize(800,600);
    }

    private JTree createTree(){
        DefaultMutableTreeNode racine = new DefaultMutableTreeNode();

        for (Program program : programs){
            UnitNode programNode = new UnitNode(program);
            racine.add(programNode);

            for (Unit bloc : program.getChildren()) {
                UnitNode blocNode = new UnitNode(bloc);
                programNode.add(blocNode);
                if (bloc.getChildren().length > 1) {
                    for (Unit cours : bloc.getChildren()) {
                        UnitNode coursNode = new UnitNode(cours);
                        blocNode.add(coursNode);
                    }
                }
            }
        }
        JTree tree= new JTree(racine);;
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
        JScrollPane jps = new JScrollPane(tree);
        jps.setSize(800,600);
        jPanel.add(jps);
        return tree;
    }

    public JPanel getJPanel(){
        return jPanel;
    }

    public JTree getMyTree() {
        return myTree;
    }
}
