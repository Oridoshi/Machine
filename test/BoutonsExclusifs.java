import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class BoutonsExclusifs {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Boutons Exclusifs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();

        // Créez un groupe de boutons
        ButtonGroup groupeBoutons = new ButtonGroup();

        // Créez des boutons radio
        JButton bouton1 = new JButton("Option 1");
        JButton bouton2 = new JButton("Option 2");
        JButton bouton3 = new JButton("Option 3");

        // Ajoutez les boutons au groupe
        groupeBoutons.add(bouton1);
        groupeBoutons.add(bouton2);
        groupeBoutons.add(bouton3);

        // Ajoutez les boutons au panneau
        panel.add(bouton1);
        panel.add(bouton2);
        panel.add(bouton3);

        frame.add(panel);
        frame.setVisible(true);
    }
}