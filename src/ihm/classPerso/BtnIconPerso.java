package ihm.classPerso;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import ihm.Frame;

public class BtnIconPerso extends BtnPerso
{

	private final ImageIcon imgHover;
	private final ImageIcon imgSelect;
	private final ImageIcon imgNonSelect;
	private String pan;
	private Frame mere;

	public BtnIconPerso(String imgHover, String imgSelect, String imgNonSelect, Frame mere, String pan)
	{
		super();

		this.pan  = pan;
		this.mere = mere;
		
		ImageIcon tmpImgHover     = new ImageIcon(getClass().getResource(imgHover));
		ImageIcon tmpImgSelect    = new ImageIcon(getClass().getResource(imgSelect));
		ImageIcon tmpImgNonSelect = new ImageIcon(getClass().getResource(imgNonSelect));

		Image imageResizeHover     = tmpImgHover.getImage();
		Image imageResizeSelect    = tmpImgSelect.getImage();
		Image imageResizeNonSelect = tmpImgNonSelect.getImage();

		imageResizeHover     = imageResizeHover     .getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		imageResizeSelect    = imageResizeSelect    .getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		imageResizeNonSelect = imageResizeNonSelect .getScaledInstance(30, 30, Image.SCALE_SMOOTH);

		this.imgHover     = new ImageIcon(imageResizeHover, "imgHover"         );
		this.imgSelect    = new ImageIcon(imageResizeSelect, "imgSelect"       );
		this.imgNonSelect = new ImageIcon(imageResizeNonSelect, "imgNonSelect" );

		this.setBackground(new Color(31, 31, 31));

		// Désactiver le texte
		setHorizontalTextPosition(JButton.CENTER);
		setVerticalTextPosition(JButton.CENTER);

		// Désactiver la bordure
		setBorderPainted(false);
		setFocusPainted(false);

		this.setIcon(this.imgNonSelect);

		this.addMouseListener(this);
	}

	public void miseAJourStyle()
	{
		if(this.isSelected())
		{
			this.mere.chargerPanel(pan);
			setIcon(imgSelect);
		}
		else
		{
			setIcon(imgNonSelect);
		}
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e)
	{
		// System.out.println("DEBUG : ENTRE");
		this.setIcon(imgHover);
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// System.out.println("DEBUG : SORTI");
		if (isSelected())
		{
			setIcon(imgSelect);
		}
		else
		{
			setIcon(imgNonSelect);
		}
	}
	
}
