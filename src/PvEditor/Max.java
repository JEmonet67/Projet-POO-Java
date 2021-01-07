package projet.PvEditor;

import java.util.Collections;
import java.util.List;

public class Max implements Stat{

    public Max() { }

    public String getLineName(){
        return "Note max";
    }

    public double calcul(List<Double> list){
        return Collections.max(list);
    }
}
