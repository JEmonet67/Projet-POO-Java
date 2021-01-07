package projet.PvEditor;

import java.util.List;

public interface Stat {
    //Interface représantant les objets statistiques utilisés pour la création des dernières lignes des procès verbaux
    String getLineName();
    double calcul(List<Double> list);
    default double roundValue(List<Double> list){
        return Math.round(calcul(list)*1000.0)/1000.0;
    }
}
