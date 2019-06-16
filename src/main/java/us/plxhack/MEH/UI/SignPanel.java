package us.plxhack.MEH.UI;

import org.zzl.minegaming.GBAUtils.BitConverter;
import us.plxhack.MEH.IO.MapIO;
import us.plxhack.MEH.MapElements.SpritesSignManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignPanel extends JPanel {
	private JTextField textField;
	int myIndex;
    void Load(SpritesSignManager mgr, int index){
    	textField.setText(BitConverter.toHexString((int) mgr.mapSigns.get(index).pScript));
    }
    void Save(SpritesSignManager mgr){
    	mgr.mapSigns.get(myIndex).pScript = Integer.parseInt(textField.getText(), 16);
    }
	/**
	 * Create the panel.
	 */
	public SignPanel(SpritesSignManager mgr, int index) {
		myIndex=index;
		setBorder(new TitledBorder(null, "Sign", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		
		JLabel lblScriptPointer = new JLabel("<html>Script Pointer:   <B style=\"color: green\">$</B><html>");
		lblScriptPointer.setBounds(12, 27, 127, 15);
		add(lblScriptPointer);
		
		textField = new JTextField();
		textField.setBounds(128, 23, 83, 26);
		add(textField);
		textField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(12, 119, 68, 25);
		btnSave.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Save(MapIO.loadedMap.mapSignManager);
			}
		});
		add(btnSave);
		
		JButton btnOpenScript = new JButton("Open Script");
		btnOpenScript.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				MapIO.openScript(Integer.parseInt(textField.getText(), 16));
			}
		});
		btnOpenScript.setBounds(56, 54, 142, 25);
		add(btnOpenScript);
		Load(mgr, index);

	}
}
