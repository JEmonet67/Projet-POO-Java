package projet;

import java.util.Collections;
import java.util.List;

public class Min implements Stat {

    public Min() {
    }

    public String getLineName(){
        return "Note min";
    }

    public double calcul(List<Double> list){
        return Collections.min(list);
    }
}
