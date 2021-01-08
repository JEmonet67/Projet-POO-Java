package projet.graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputIDFrame extends InputFrame {
    public InputIDFrame(String titre, ModifPanel outputPanel) {
        super(titre, outputPanel);

        JPanel panelNumber = new JPanel();
        JTextField tfIdNumber = new JTextField("ID", 10);
        panelNumber.add(tfIdNumber);
        panelNumber.add(getValidSuppr(tfIdNumber));
        inputFrame.add(panelNumber, BorderLayout.CENTER);

        inputFrame.setVisible(true);
    }

    public JButton getValidSuppr(JTextField tf) {
        JButton getAddNumberButton = new JButton("Supprimer");
        getAddNumberButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer.parseInt(tf.getText().strip());
                } catch (Exception notNumber) {
                    JOptionPane.showMessageDialog(null, "La valeur entrée n'est pas un nombre.");
                }
                inputFrame.dispose();

            }
        });
        return getAddNumberButton;
    }
}
