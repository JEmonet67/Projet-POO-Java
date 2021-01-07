package projet.graphic;

import projet.dataStructure.Unit;

import javax.swing.tree.DefaultMutableTreeNode;

public class UnitNode extends DefaultMutableTreeNode {
    private Unit unit;

    public UnitNode(Unit unit) {
        super(unit);
        this.unit = unit;
    }

    public Unit getUnit() {
        return unit;
    }
}
