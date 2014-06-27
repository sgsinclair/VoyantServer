/* 
 *  JettyDesktop is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  Free Software Foundation,version 3.
 *  
 *  JettyDesktop is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  If not, see http://www.gnu.org/licenses/
 *  
 *  Additional permission under GNU GPL version 3 section 7
 *  
 *  If you modify this Program, or any covered work, by linking or combining 
 *  it with any of the JARS listed in the README.txt (or a modified version of 
 *  (that library), containing parts covered by the terms of that JAR, the 
 *  licensors of this Program grant you additional permission to convey the 
 *  resulting work. 
 *  
 *  https://github.com/aw20/jettydesktop
 *  
 *  May 2013
 */
package org.aw20.jettydesktop.ui.awt;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.aw20.jettydesktop.ui.ServerConfigMap;

public class ServerConfig extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldServerName;
	private JTextField textFieldCustomJVM;
	private JTextField textFieldMemoryJVM;
	private JTextField textFieldWebFolder;
	private JTextField textServerPort;
	private JTextField textServerIP;
	private JRadioButton rdbtnCurrentJVM, rdbtnCustomJVM;

	private ServerConfigMap	currentConfigMap = null;
	private JTextField textJVMArgs;
	private JTextField textDefaultURI;

	public ServerConfig( ServerConfigMap serverConfigMap ) {
		setModal(true);
		setResizable(false);
		setTitle("Web Configuration");
		setBounds(100, 100, 450, 398);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Server Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.NORTH);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblName = new JLabel("Name:");
				GridBagConstraints gbc_lblName = new GridBagConstraints();
				gbc_lblName.insets = new Insets(5, 5, 5, 5);
				gbc_lblName.anchor = GridBagConstraints.EAST;
				gbc_lblName.gridx = 0;
				gbc_lblName.gridy = 0;
				panel.add(lblName, gbc_lblName);
			}
			{
				textFieldServerName = new JTextField();
				GridBagConstraints gbc_textFieldServerName = new GridBagConstraints();
				gbc_textFieldServerName.gridwidth = 2;
				gbc_textFieldServerName.insets = new Insets(5, 5, 5, 5);
				gbc_textFieldServerName.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldServerName.gridx = 1;
				gbc_textFieldServerName.gridy = 0;
				panel.add(textFieldServerName, gbc_textFieldServerName);
				textFieldServerName.setColumns(10);
			}
			{
				JLabel lblIpAddress = new JLabel("IP Address:");
				GridBagConstraints gbc_lblIpAddress = new GridBagConstraints();
				gbc_lblIpAddress.anchor = GridBagConstraints.EAST;
				gbc_lblIpAddress.insets = new Insets(5, 5, 5, 5);
				gbc_lblIpAddress.gridx = 0;
				gbc_lblIpAddress.gridy = 1;
				panel.add(lblIpAddress, gbc_lblIpAddress);
			}
			{
				textServerIP = new JTextField();
				GridBagConstraints gbc_textServerIP = new GridBagConstraints();
				gbc_textServerIP.insets = new Insets(5, 5, 5, 5);
				gbc_textServerIP.fill = GridBagConstraints.HORIZONTAL;
				gbc_textServerIP.gridx = 1;
				gbc_textServerIP.gridy = 1;
				panel.add(textServerIP, gbc_textServerIP);
				textServerIP.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Port:");
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel_1.insets = new Insets(5, 5, 5, 5);
				gbc_lblNewLabel_1.gridx = 2;
				gbc_lblNewLabel_1.gridy = 1;
				panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			}
			{
				textServerPort = new JTextField();
				GridBagConstraints gbc_textServerPort = new GridBagConstraints();
				gbc_textServerPort.fill = GridBagConstraints.HORIZONTAL;
				gbc_textServerPort.gridx = 3;
				gbc_textServerPort.gridy = 1;
				panel.add(textServerPort, gbc_textServerPort);
				textServerPort.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Web Application", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblNewLabel = new JLabel("Web Folder:");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.insets = new Insets(5, 5, 5, 5);
				gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = 0;
				panel.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				textFieldWebFolder = new JTextField();
				GridBagConstraints gbc_textFieldWebFolder = new GridBagConstraints();
				gbc_textFieldWebFolder.insets = new Insets(5, 5, 5, 5);
				gbc_textFieldWebFolder.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldWebFolder.gridx = 1;
				gbc_textFieldWebFolder.gridy = 0;
				panel.add(textFieldWebFolder, gbc_textFieldWebFolder);
				textFieldWebFolder.setColumns(10);
			}
			{
				JButton btnWebAppBrowse = new JButton("browse");
				btnWebAppBrowse.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						JFileChooser chooser = new JFileChooser();
						chooser.setCurrentDirectory(new java.io.File("."));
						chooser.setDialogTitle("Choose Web App Folder");
						chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						chooser.setAcceptAllFileFilterUsed(false);

						if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
							File webApp = chooser.getSelectedFile();
							textFieldWebFolder.setText(webApp.getAbsolutePath());
						}
						
					}
				});
				GridBagConstraints gbc_btnWebAppBrowse = new GridBagConstraints();
				gbc_btnWebAppBrowse.insets = new Insets(0, 0, 5, 0);
				gbc_btnWebAppBrowse.gridx = 2;
				gbc_btnWebAppBrowse.gridy = 0;
				panel.add(btnWebAppBrowse, gbc_btnWebAppBrowse);
			}
			{
				JLabel lblDefaultUri = new JLabel("Default URI:");
				GridBagConstraints gbc_lblDefaultUri = new GridBagConstraints();
				gbc_lblDefaultUri.anchor = GridBagConstraints.EAST;
				gbc_lblDefaultUri.insets = new Insets(5, 5, 5, 5);
				gbc_lblDefaultUri.gridx = 0;
				gbc_lblDefaultUri.gridy = 1;
				panel.add(lblDefaultUri, gbc_lblDefaultUri);
			}
			{
				textDefaultURI = new JTextField();
				GridBagConstraints gbc_textDefaultURI = new GridBagConstraints();
				gbc_textDefaultURI.insets = new Insets(5, 5, 5, 5);
				gbc_textDefaultURI.fill = GridBagConstraints.HORIZONTAL;
				gbc_textDefaultURI.gridx = 1;
				gbc_textDefaultURI.gridy = 1;
				panel.add(textDefaultURI, gbc_textDefaultURI);
				textDefaultURI.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Java Virtual Machine", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.SOUTH);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblRuntime = new JLabel("Runtime:");
				GridBagConstraints gbc_lblRuntime = new GridBagConstraints();
				gbc_lblRuntime.insets = new Insets(5, 5, 5, 5);
				gbc_lblRuntime.gridx = 0;
				gbc_lblRuntime.gridy = 0;
				panel.add(lblRuntime, gbc_lblRuntime);
			}
			{
				rdbtnCurrentJVM = new JRadioButton( System.getProperty("java.vm.name") + " " + System.getProperty("java.version") + " " + System.getProperty("java.vm.version") );
				rdbtnCurrentJVM.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						rdbtnCustomJVM.setSelected(false);
					}
				});
				GridBagConstraints gbc_rdbtnCurrentJVM = new GridBagConstraints();
				gbc_rdbtnCurrentJVM.gridwidth = 2;
				gbc_rdbtnCurrentJVM.anchor = GridBagConstraints.WEST;
				gbc_rdbtnCurrentJVM.insets = new Insets(5, 5, 5, 5);
				gbc_rdbtnCurrentJVM.gridx = 1;
				gbc_rdbtnCurrentJVM.gridy = 0;
				panel.add(rdbtnCurrentJVM, gbc_rdbtnCurrentJVM);
			}
			{
				rdbtnCustomJVM = new JRadioButton("Custom JVM");
				rdbtnCustomJVM.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						rdbtnCurrentJVM.setSelected(false);
					}
				});
				GridBagConstraints gbc_rdbtnCustomJVM = new GridBagConstraints();
				gbc_rdbtnCustomJVM.anchor = GridBagConstraints.WEST;
				gbc_rdbtnCustomJVM.insets = new Insets(5, 5, 5, 5);
				gbc_rdbtnCustomJVM.gridx = 1;
				gbc_rdbtnCustomJVM.gridy = 1;
				panel.add(rdbtnCustomJVM, gbc_rdbtnCustomJVM);
			}
			{
				textFieldCustomJVM = new JTextField();
				GridBagConstraints gbc_textFieldCustomJVM = new GridBagConstraints();
				gbc_textFieldCustomJVM.insets = new Insets(5, 5, 5, 5);
				gbc_textFieldCustomJVM.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldCustomJVM.gridx = 2;
				gbc_textFieldCustomJVM.gridy = 1;
				panel.add(textFieldCustomJVM, gbc_textFieldCustomJVM);
				textFieldCustomJVM.setColumns(10);
			}
			{
				JButton btnBrowseCustomJVM = new JButton("browse");
				btnBrowseCustomJVM.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						JFileChooser chooser = new JFileChooser();
						chooser.setCurrentDirectory(new java.io.File("."));
						chooser.setDialogTitle("Choose Java Runtime Folder");
						chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						chooser.setAcceptAllFileFilterUsed(false);

						if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
							File webApp = chooser.getSelectedFile();
							textFieldCustomJVM.setText(webApp.getAbsolutePath());
						}
						
					}
				});
				GridBagConstraints gbc_btnBrowseCustomJVM = new GridBagConstraints();
				gbc_btnBrowseCustomJVM.insets = new Insets(5, 0, 5, 0);
				gbc_btnBrowseCustomJVM.gridx = 3;
				gbc_btnBrowseCustomJVM.gridy = 1;
				panel.add(btnBrowseCustomJVM, gbc_btnBrowseCustomJVM);
			}
			{
				JLabel lblJvmArguments = new JLabel("JVM Arguments");
				GridBagConstraints gbc_lblJvmArguments = new GridBagConstraints();
				gbc_lblJvmArguments.anchor = GridBagConstraints.EAST;
				gbc_lblJvmArguments.insets = new Insets(5, 25, 5, 5);
				gbc_lblJvmArguments.gridx = 1;
				gbc_lblJvmArguments.gridy = 2;
				panel.add(lblJvmArguments, gbc_lblJvmArguments);
			}
			{
				textJVMArgs = new JTextField();
				GridBagConstraints gbc_textJVMArgs = new GridBagConstraints();
				gbc_textJVMArgs.insets = new Insets(5, 5, 5, 5);
				gbc_textJVMArgs.fill = GridBagConstraints.HORIZONTAL;
				gbc_textJVMArgs.gridx = 2;
				gbc_textJVMArgs.gridy = 2;
				panel.add(textJVMArgs, gbc_textJVMArgs);
				textJVMArgs.setColumns(10);
			}
			{
				JLabel lblMemory = new JLabel("Memory:");
				GridBagConstraints gbc_lblMemory = new GridBagConstraints();
				gbc_lblMemory.anchor = GridBagConstraints.EAST;
				gbc_lblMemory.insets = new Insets(5, 5, 0, 5);
				gbc_lblMemory.gridx = 0;
				gbc_lblMemory.gridy = 3;
				panel.add(lblMemory, gbc_lblMemory);
			}
			{
				textFieldMemoryJVM = new JTextField();
				GridBagConstraints gbc_textFieldMemoryJVM = new GridBagConstraints();
				gbc_textFieldMemoryJVM.anchor = GridBagConstraints.WEST;
				gbc_textFieldMemoryJVM.insets = new Insets(5, 5, 0, 5);
				gbc_textFieldMemoryJVM.gridx = 1;
				gbc_textFieldMemoryJVM.gridy = 3;
				textFieldMemoryJVM.setMinimumSize( new Dimension(100, textFieldMemoryJVM.getHeight()) );
				panel.add(textFieldMemoryJVM, gbc_textFieldMemoryJVM);
				textFieldMemoryJVM.setColumns(10);
			}
			{
				JLabel lblMb = new JLabel("MB");
				GridBagConstraints gbc_lblMb = new GridBagConstraints();
				gbc_lblMb.anchor = GridBagConstraints.WEST;
				gbc_lblMb.insets = new Insets(5, 5, 0, 5);
				gbc_lblMb.gridx = 2;
				gbc_lblMb.gridy = 3;
				panel.add(lblMb, gbc_lblMb);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if ( validateSettings() )
							dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		setValues(serverConfigMap);
	}

	public ServerConfigMap	getConfig(){
		return currentConfigMap;
	}
	
	private boolean validateSettings(){
		String tmp;
		ServerConfigMap	config	= new ServerConfigMap();
		
		// Server Name
		tmp	= textFieldServerName.getText().trim();
		if ( tmp.length() == 0 ){
			JOptionPane.showMessageDialog(this, "Invalid server name");
			return false;
		}else
			config.setName(tmp);
		
		// Server IP
		tmp = textServerIP.getText().trim();
		if ( tmp.length() != 0 && !isValidIP(tmp) ){
			JOptionPane.showMessageDialog(this, "Invalid IP address");
			return false;
		}else
			config.setIP(tmp);
		
		// Server Port
		tmp = textServerPort.getText().trim();
		if ( !isValidNumber(tmp) ){
			JOptionPane.showMessageDialog(this, "Invalid server port");
			return false;
		}else
			config.setPort(tmp);
		
		// WebApp folder
		tmp	= textFieldWebFolder.getText().trim();
		if ( tmp.length() == 0 && !new File(tmp + File.separator + "WEB-INF", "web.xml").isFile() ){
			JOptionPane.showMessageDialog(this, "Invalid web directory");
			return false;
		}else
			config.setWebFolder(tmp);
		
		tmp	= textDefaultURI.getText();
		if ( tmp.length() > 0 )
			config.setDefaultWebUri( tmp );
		
		
		// JVM
		if ( rdbtnCurrentJVM.isSelected() ){
			config.setCurrentJVM();
		}else{
			tmp	= textFieldCustomJVM.getText().trim();
			if ( tmp.length() == 0 && !new File(tmp,"bin").isDirectory() ){
				JOptionPane.showMessageDialog(this, "Invalid Java directory");
				return false;
			}else
				config.setCustomJVM(tmp);
		}
		
		
		// Default JVM ARGs
		tmp	= textJVMArgs.getText();
		if ( tmp.length() > 0 )
			config.setDefaultJVMArgs( tmp );
		

		// Memory
		tmp = textFieldMemoryJVM.getText().trim();
		if ( !isValidNumber(tmp) ){
			JOptionPane.showMessageDialog(this, "Invalid memory value");
			return false;
		}else
			config.setMemoryJVM(tmp);
		
		// All is good
		currentConfigMap	= config;
		return true;
	}
	
	private boolean isValidNumber(String t){
		try{
			Integer.valueOf(t);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	private boolean isValidIP(String t){
		if ( t.length() == 0 )
			return false;
		
		try {
			Inet4Address.getByName(t);
			return true;
		} catch (UnknownHostException e) {
			return false;
		}
	}
	
	private void setValues( ServerConfigMap serverConfigMap ){
		textFieldServerName.setText( serverConfigMap.getName() );
		textServerPort.setText( serverConfigMap.getPort() );
		textServerIP.setText( serverConfigMap.getIP() );
		
		textFieldWebFolder.setText( serverConfigMap.getWebFolder() );
		
		textFieldMemoryJVM.setText( serverConfigMap.getMemoryJVM() );
		
		if ( serverConfigMap.getCurrentJVM() != null ){
			textFieldCustomJVM.setText( serverConfigMap.getCustomJVM() );
			rdbtnCurrentJVM.setSelected(true);
			rdbtnCustomJVM.setSelected(false);
		}else{
			rdbtnCurrentJVM.setSelected(false);
			rdbtnCustomJVM.setSelected(true);
		}
		
		String defaultJVMArgs	= serverConfigMap.getDefaultJVMArgs();
		if ( defaultJVMArgs == null ){
			textJVMArgs.setText("");
		}else{
			textJVMArgs.setText(defaultJVMArgs);
		}
		
		String defaultWebUri	= serverConfigMap.getDefaultWebUri();
		if ( defaultWebUri == null ){
			textDefaultURI.setText("/");
		}else{
			textDefaultURI.setText(defaultWebUri);
		}
	}
}
