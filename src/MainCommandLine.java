package projet;

import java.util.HashMap;

public class MainCommandLine {
    public static void main(String[] args) {
        String datapath = "C:\\Users\\layag\\OneDrive\\Documents\\M2\\progra_objet\\projet\\data.xml";
        String savepath = "C:\\Users\\layag\\OneDrive\\Documents\\M2\\progra_objet\\projet\\pv_test.csv";
        String programId = "SLIMI3 180";

        var mapList =XmlReader.parseXML(datapath);

        HashMap<String,Cours> coursMap= (HashMap<String,Cours> ) mapList.get(0);
        HashMap<String,Program> programMap= (HashMap<String,Program>) mapList.get(1);
        HashMap<String,Student> studentMap= (HashMap<String,Student>) mapList.get(2);

        Program p = programMap.get(programId);
        ProcesVerbal pv = new ProcesVerbal(p,coursMap);
        pv.saveProcesVerbal(savepath);
    }
}
