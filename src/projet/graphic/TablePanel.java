package projet.graphic;

import projet.PvEditor.ProcesVerbal;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TablePanel implements TreeSelectionListener {
    //Classe permettant la création du panel contenant le proces verbal de l'unité selectionée

    private JPanel panel;
    private List<String[]> pv;

    public TablePanel() {
        super();
        this.panel = new JPanel();

        this.pv=null; //avant qu'une unité ait été sélectionnée, aucun pv n'est créé
        //initialisation du panel sans pv
        JLabel initialText = new JLabel("Sélectionner une unité d'enseignement");
        panel.add(initialText);

    }

    public JPanel getJPanel() { return panel; }

    public List<String[]> getPv() { return pv; }

    public void valueChanged(TreeSelectionEvent tsl) {
        //Ecoute l'arbre dans Tree panel et adapte le pv a l'unité d'enseignement sélectionée dans celui-ci

        if(tsl.getNewLeadSelectionPath() != null){//si une unité a été selectionnée, on change le pv affiché

            //recupération de l'unité correspondant au noeud sélectioné
            UnitNode node = (UnitNode) tsl.getNewLeadSelectionPath().getLastPathComponent();

            //création du nouveau pv
            ProcesVerbal pvCreator = new ProcesVerbal(node.getUnit());
            List<String[]> pv = pvCreator.createPV();
            this.pv = pv;

            //Convertion du pv en JTable puis JScrollPane pour permettre son affichage dans l'interface graphique
            JTable jt=createJTable(pv);
            JScrollPane jps = new JScrollPane(jt);

            //Nettoyer le panel des anciens éléments et y ajouter le nouveau JScrollPane contenant le pv
            panel.removeAll();
            panel.repaint();
            panel.revalidate();
            panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            panel.setLayout(new GridLayout(0,1));
            panel.add(jps);
            panel.revalidate();
            panel.repaint();
        }
    }



    private static JTable createJTable(List<String[]> pv){
        //Convertit le procès verbal en JTable (format affichable dans l'interface graphique)

        String[] colNames = pv.get(0);
        String[][] lines = new String[pv.size()][pv.get(0).length];

        for (int i=1; i<pv.size(); i++){
            lines[i]=pv.get(i);
        }

        DefaultTableModel tableModel = new DefaultTableModel(lines, colNames) {
            public boolean isCellEditable(int row, int column) {
                //rendre les cellules non modifiables
                return false;
            }
        };

        JTable jt = new JTable(lines,colNames);
        jt.setModel(tableModel);
        jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        return jt;
    }
}
