import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

class CustomButton extends JToggleButton implements MouseListener
{
	private ImageIcon normalIcon;
	private ImageIcon hoverIcon;
	private ImageIcon selectedIcon;
	private float iconOpacity = 1.0f;
	private JLabel infoLabel;

	public CustomButton(String text, String normalIconPath, String hoverIconPath, String selectedIconPath)
	{
		super(text);
		normalIcon   = new ImageIcon(normalIconPath)  ;
		hoverIcon    = new ImageIcon(hoverIconPath)   ;
		selectedIcon = new ImageIcon(selectedIconPath);

		normalIcon   = resizeIcon(normalIcon, 32, 32)  ;
		hoverIcon    = resizeIcon(hoverIcon, 32, 32)   ;
		selectedIcon = resizeIcon(selectedIcon, 32, 32);

		setIcon(normalIcon);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(true);
		setBackground(Color.BLUE);
		setForeground(Color.WHITE);

		infoLabel = new JLabel("Information sur le bouton");
		infoLabel.setVisible(false); // Initialement invisible
		infoLabel.setForeground(Color.BLACK);

		this.addMouseListener(this);

		Timer timer = new Timer(20, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});
		timer.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setComposite(AlphaComposite.SrcOver.derive(iconOpacity));

		if (isSelected()) {
			setBackground(Color.GREEN);
			setIcon(selectedIcon);
		} else if (getModel().isRollover()) {
			setBackground(Color.RED);
			setIcon(hoverIcon);
		} else {
			setBackground(Color.BLUE);
			setIcon(normalIcon);
		}

		super.paintComponent(g2d);
		g2d.dispose();
	}

	@Override
	public void setSelected(boolean selected) {
		super.setSelected(selected);
		iconOpacity = selected ? 1.0f : 0.5f;
	}

	private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
		Image img = icon.getImage();
		Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(newImg);
	}
}
