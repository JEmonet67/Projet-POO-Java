package projet;

import java.util.List;

public class Mean implements Stat{

    public Mean() {
    }

    public String getLineName() {
        return "Note moyenne";
    }

    public double calcul(List<Double> list){
        double somme =0;
        for (double note : list){
            somme += note;
        }
        return somme/ list.size();
    }
}
