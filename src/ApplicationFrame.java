import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import javax.swing.plaf.FileChooserUI;

public class ApplicationFrame extends JFrame implements ActionListener {
	
	public ApplicationFrame() {
		this(400,400);
	}
	
	private JEditorPane text;
	private JButton backgroundColor;
	private JButton save;
	private JButton open;
	
	public ApplicationFrame(int width, int height) {
		// flow all elements
		this.setLayout( new FlowLayout() );
		// close applications when closed
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.text = new JEditorPane();
		this.text.setSize( width - 50 , 300);
		this.add( this.text );
		
		this.backgroundColor = new JButton("Valitse väri");
		this.add( this.backgroundColor );
		this.backgroundColor.addActionListener(this);
		
		this.open = new JButton("Avaa tiedosto");
		this.add( this.open );
		this.open.addActionListener(this);
		
		this.save = new JButton("Tallenna tiedosto");
		this.add( this.save );
		this.save.addActionListener(this);
		
		// Settings for display
		this.setSize(width, height);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {
		if( event.getSource() == this.backgroundColor ) {
			Color newColor = JColorChooser.showDialog( ApplicationFrame.this, "Valitse taustan väri", this.getBackground());
			this.setBackground(newColor);
		}
		if( event.getSource() == this.open ) {
			// read content and put it in the editor
			JFileChooser fc = new JFileChooser();
			int select = fc.showOpenDialog(this);
			if( select == JFileChooser.APPROVE_OPTION ) {
				File file = fc.getSelectedFile();
				try {
					String text = FileIO.open(file);
					this.text.setText(text);
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(this, "Something bad happened", "Can not do!", JOptionPane.ERROR_MESSAGE );
				}
			}
		}
		if( event.getSource() == this.save ) {
			JFileChooser fc = new JFileChooser();
			int select = fc.showSaveDialog(this);
			if( select == JFileChooser.APPROVE_OPTION ) {
				try {
					File file = fc.getSelectedFile();
					FileIO.save(file, this.text.getText() );
				} catch (IOException e) {
					JOptionPane.showMessageDialog(this, "Something bad happened", "Can not do!", JOptionPane.ERROR_MESSAGE );
				}
			}
		}
		
	}

}
