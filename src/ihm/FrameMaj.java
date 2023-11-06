package ihm;

import javax.swing.*;
import java.awt.*;

public class FrameMaj extends JFrame
{
	private JProgressBar progressBar;

	public FrameMaj()
	{
		// Créez une fenêtre
		this.setTitle("Téléchargement de la mise à jour");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 100);

		// Créez une barre de chargement
		this.progressBar = new JProgressBar(0, 100);
		this.progressBar.setValue(0);
		this.progressBar.setStringPainted(true);

		this.setLayout(new FlowLayout());
		this.add(new JLabel("Téléchargement de la mise à jour : "));
		this.add(progressBar);
		this.setVisible(true);

		// Simulez le chargement en utilisant un thread
		SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>()
		{
			@Override
			protected Void doInBackground() throws Exception
			{
				for (int progress = 0; progress <= 100; progress = progressBar.getValue() + 1)
				{
					publish(progress);
					Thread.sleep(90);
				}
				return null;
			}

			@Override
			protected void process(java.util.List<Integer> chunks)
			{
				for (int value : chunks)
				{
					if(progressBar.getValue() < 100)
						progressBar.setValue(value);
				}
			}

			@Override
			protected void done()
			{
				JOptionPane.showMessageDialog(null, "Téléchargement terminé", "Information", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		};
		worker.execute();
	}

	public void updateFini()
	{
		this.progressBar.setValue(100);
	}
}