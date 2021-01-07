package projet.graphic;

import projet.PvEditor.ProcesVerbal;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TablePanel implements TreeSelectionListener {

    private JPanel panel = new JPanel();
    private List<String[]> pv;

    public TablePanel() {
        super();
        initialise();
    }

    private void initialise() {
        pv=null;
        JLabel initialText = new JLabel("Sélectionnez une unitée d'enseignement");
        panel.add(initialText);
    }

    public JPanel getJPanel() {
        return panel;
    }

    public void valueChanged(TreeSelectionEvent tsl) {
        if(tsl.getNewLeadSelectionPath() != null){
            UnitNode node = (UnitNode) tsl.getNewLeadSelectionPath().getLastPathComponent();
            ProcesVerbal pvFactory = new ProcesVerbal(node.getUnit());
            var pv = pvFactory.createPV();
            this.pv = pv;
            JTable jt=createJTable(pv);
            //pv.setModel(new DefaultTableModel());
            //pv.repaint(); //??
            //panel.add(pv);
            //
            JScrollPane jps = new JScrollPane(jt);

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

        String[] colNames = pv.get(0);
        String[][] lines = new String[pv.size()][pv.get(0).length];

        for (int i=1; i<pv.size(); i++){
            lines[i]=pv.get(i);
        }

        DefaultTableModel tableModel = new DefaultTableModel(lines, colNames) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        JTable jt = new JTable(lines,colNames);
        jt.setModel(tableModel);
        //jt.setPreferredScrollableViewportSize(new Dimension(450,63));
        //jt.setFillsViewportHeight(true);
        jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        return jt;
    }

    public List<String[]> getPv() {
        return pv;
    }
}
