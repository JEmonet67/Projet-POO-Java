package projet.graphic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class SavePvPanel extends JPanel{
    //Classe permettant la création du panel contenant le bouton "Enregistrer le procès verbal"

    private CentralPanel centralPanel;
    private JFrame fenetrePrincipale;

    public SavePvPanel(CentralPanel centralPanel, JFrame frame) {
        super();
        this.centralPanel = centralPanel;
        this.fenetrePrincipale = frame;

        //ajout du bouton au panel (c'est le seul élément du panel)
        add(getJButton());
    }

    private JButton getJButton(){
        //Création du bouton
        JButton savePvButton = new JButton("Enregistrer le procès verbal");
        savePvButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //actions effectuées lorsqu'on appuie sur le bouton
                if (centralPanel.getPv()==null){
                    JOptionPane.showMessageDialog(null,"Veuillez sélectionner une unité d'enseignement.");
                }else{
                    //Afficher un JFileChooser et enregistrer le pv a l'adresse sélectionée
                    JFileChooser selector = new JFileChooser();
                    int res = selector.showDialog(fenetrePrincipale,"Enregistrement du procès verbal");
                    if (res==JFileChooser.APPROVE_OPTION){
                        exportPvToCSV(selector.getSelectedFile(),';');
                    }
                }
            }
        });
        return savePvButton;
    }

    private boolean exportPvToCSV(File filePath,char sep) {
        //enregistrement du pv a l'adresse file path
        try {
            FileWriter fw = new FileWriter(filePath,false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw =new PrintWriter(bw);

            List<String[]> pv = centralPanel.getPv();

            for (String[] line : pv){
                for (String elem : line ){
                    pw.print(elem+sep);
                }
                pw.println();
            }

            pw.flush();
            pw.close();

            JOptionPane.showMessageDialog(null,"Le procès verbal a bien été enregistré.");
            return true;

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Une erreur est survenue. Le procès verbal n'a pas pue être enregistré");
        }
        return false;
    }
}
