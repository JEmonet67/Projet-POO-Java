package projet;

import projet.graphic.GUI;
import java.awt.*;

public class Main{

    public static void main(String[] args) {
        //parsing des données
        String datapath = System.getProperty("user.dir") +"/src/projet/data.xml";
        XmlReader reader = new XmlReader(datapath);
        var programMap = reader.getProgramMap();
        var studentMap=reader.getStudentMap();
        var coursMap=reader.getCoursMap();

        //lancement de l'interface graphique
        GUI gui = new GUI(programMap,studentMap,coursMap);
    }
}




