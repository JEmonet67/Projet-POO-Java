package projet.graphic;

import projet.dataStructure.Unit;

import javax.swing.tree.DefaultMutableTreeNode;

public class UnitNode extends DefaultMutableTreeNode {
    //Classe definissant les noeuds de l'arborescence de l'arbre de la classe TreePanel

    private Unit unit;

    public UnitNode(Unit unit) {
        super(unit);
        this.unit = unit;
    }

    public Unit getUnit() {
        return unit;
    }
}
