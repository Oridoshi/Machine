import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoutonPersonnaliseApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Boutons Personnalisés");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Créez un bouton personnalisé
        CustomButton button = new CustomButton("Bouton personnalisé", "test (1).PNG", "test (2).PNG", "test (3).PNG");

        // Ajoutez un écouteur d'événements pour le bouton
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button.setSelected(!button.isSelected());
            }
        });

        frame.add(button);
        frame.pack();
        frame.setVisible(true);
    }
}
