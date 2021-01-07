package projet.PvEditor;

import java.util.List;

public interface Stat {
    String getLineName();
    double calcul(List<Double> list);
    default double roundValue(List<Double> list){
        return Math.round(calcul(list)*1000.0)/1000.0;
    }
}
