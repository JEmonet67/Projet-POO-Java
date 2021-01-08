package projet.graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import projet.dataStructure.*;

public class SaveProgramPanel extends JPanel {
    private String codeProgram;
    private String nameProgram;
    private List<JComboBox> listCbBlocs;
    private GUI gui;

    public SaveProgramPanel(JTextField tfCodeProgram, JTextField tfNameProgram,
                            List<JComboBox> listCbBlocs, GUI gui) {
        this.listCbBlocs = listCbBlocs;
        this.gui = gui;
        add(getSaveProgramButton(tfCodeProgram, tfNameProgram));

    }

    private JButton getSaveProgramButton(JTextField tfCodeProgram, JTextField tfNameProgram) {
        JButton saveProgramButton = new JButton("Enregistrer les modifications");
        saveProgramButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameProgram = tfNameProgram.getText().strip();
                codeProgram = tfCodeProgram.getText().strip();
                List<Bloc> blocsList = makeBlocsList(listCbBlocs);
                addProgramAtMap(blocsList);
                CentralPanel centralPanel = new CentralPanel(gui.getProgramsMap().values());
                centralPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
//                gui.add(centralPanel, BorderLayout.CENTER);
//                gui.revalidate();
//                gui.repaint();
                gui.dispose();
                GUI newGUI = new GUI(gui.getProgramsMap(),gui.getStudentMap(),gui.getCoursMap());
            }
        });

        return saveProgramButton;
    }

    private List<Bloc> makeBlocsList(List<JComboBox> listCbBlocs) {
        List<Bloc> blocsList = new ArrayList();
        for (JComboBox cb : listCbBlocs) {
            String inputNameBloc = (String) cb.getSelectedItem();
            if (inputNameBloc != "") {
                try {
                    Bloc inputBloc = AbstractBloc.getBlocsMap().get(inputNameBloc);
                    if (!blocsList.contains(inputBloc)) {
                        blocsList.add(inputBloc);
                    }
                } catch (Exception err) {
                    System.out.println("Bloc absent de la map des blocs");
                }
            }
        }
        return blocsList;
    }

    private void addProgramAtMap(List<Bloc> blocsList) {
        Program newProgram = new Program(codeProgram, nameProgram, blocsList);
        if (codeProgram != "" & codeProgram != "Code Programme"
                & nameProgram != "" & nameProgram != "Nom")
            if (!gui.getProgramsMap().containsKey(codeProgram)
                    & !gui.getProgramsMap().containsValue(newProgram)) {
                HashMap<String, Program> newProgramsMap = gui.getProgramsMap();
                newProgramsMap.put(codeProgram, newProgram);
                gui.setProgramsMap(newProgramsMap);
            }
    }
}

