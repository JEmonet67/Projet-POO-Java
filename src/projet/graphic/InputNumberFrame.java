package projet.graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class InputNumberFrame extends InputFrame implements ModifDisplay {

    public InputNumberFrame(String titre, ModifPanel outputPanel){
        super(titre,outputPanel);
        panelNumber.add(new JLabel("Quantité"));
        JTextField tfNumber = new JTextField("1", 5);
        panelNumber.add(tfNumber);
        panelNumber.add(getValidButton(tfNumber));
        inputFrame.add(panelNumber, BorderLayout.CENTER);

        inputFrame.setVisible(true);
    }

    private JButton getValidButton(JTextField tf) {
        JButton getAddNumberButton = new JButton("Valider");
        getAddNumberButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer number = Integer.parseInt(tf.getText().strip());
                    modifDisplay(number);
                } catch (Exception notNumber) {
                    JOptionPane.showMessageDialog(null, "La valeur entrée n'est pas un nombre.");
                }
                inputFrame.dispose();

            }
        });
        return getAddNumberButton;
    }

    abstract protected void modifDisplay(int number);
}