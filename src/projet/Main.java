package projet;

import projet.*;
import projet.dataStructure.Program;
import projet.graphic.GUI;

import java.awt.*;
import java.util.Collection;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //String f = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
                String datapath = "/mnt/Shared Data/Etudes/M2_BIM/Programmation_objet/Projet-POO-Java/data/data.xml";

                XmlReader reader = new XmlReader(datapath);
                var programMap = reader.getProgramMap();
                var studentMap=reader.getStudentMap();
                var coursMap=reader.getCoursMap();

                Collection<Program> programs = programMap.values();

                GUI gui = new GUI(programs,studentMap,coursMap);
                gui.setVisible(true);
                gui.pack();
            }
        });
    }
}




