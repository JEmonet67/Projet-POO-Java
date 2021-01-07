package projet.graphic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class SavePvPanel extends JPanel{
    private CentralPanel centralPanel;
    private JFrame frame;

    public SavePvPanel(CentralPanel centralPanel, JFrame frame) {
        this.centralPanel = centralPanel;
        this.frame = frame;
        add(getJButton());
    }

    private JButton getJButton(){
        JButton savePvButton = new JButton("Enregistrer le procès verbal");
        savePvButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser selector = new JFileChooser();
                int res = selector.showDialog(frame,"Enregistrement du procès verbal");
                if (res==JFileChooser.APPROVE_OPTION){
                    if (centralPanel.getPv()==null){
                        JOptionPane.showMessageDialog(null,"Veuillez sélectionner une unitée d'enseignement.");
                    }else{
                        exportPvToCSV(selector.getSelectedFile(),';');
                    }

                }
            }
        });
        return savePvButton;
    }

    private boolean exportPvToCSV(File filePath,char sep) {
        try {

            FileWriter fw = new FileWriter(filePath,false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw =new PrintWriter(bw);

            List<String[]> data = centralPanel.getPv();

            for (String[] line : data){
                for (String elem : line ){
                    pw.print(elem+sep);
                }
                pw.println();
            }


            pw.flush();
            pw.close();

            JOptionPane.showMessageDialog(null,"PV created");
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"PV not created");
        }
        return false;
    }
}
