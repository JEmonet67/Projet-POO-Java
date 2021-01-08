package projet.graphic;

import javax.swing.*;
import java.awt.*;
import projet.graphic.ModifPanel;

public abstract class InputFrame extends JFrame {
    protected JFrame inputFrame;
    protected ModifPanel outputPanel;
    protected JPanel panelNumber;

    public InputFrame(String titre, ModifPanel outputPanel) {
        this.inputFrame = new JFrame();
        this.outputPanel = outputPanel;
        this.panelNumber = new JPanel();
        inputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inputFrame.setLayout(new BorderLayout());
        inputFrame.setTitle(titre);
        inputFrame.setSize(400, 95);
    }


}
