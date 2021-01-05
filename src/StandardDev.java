package projet;

import java.util.List;

public class StandardDev implements Stat {

    public StandardDev() {
    }

    public String getLineName(){
        return "Ecart type";
    }

    public double calcul(List<Double> list){
        double somme = 0;
        Stat meanCalculator = new Mean();
        double moyenne = meanCalculator.calcul(list);

        for (double note : list){
            somme += (note - moyenne) * (note - moyenne);
        }

        return Math.sqrt(somme/ list.size());
    }
}
