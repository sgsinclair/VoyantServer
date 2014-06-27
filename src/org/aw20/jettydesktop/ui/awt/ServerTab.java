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
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import org.aw20.jettydesktop.rte.JettyRunTime;
import org.aw20.jettydesktop.ui.ConfigActionInterface;
import org.aw20.jettydesktop.ui.Executor;
import org.aw20.jettydesktop.ui.ExecutorInterface;
import org.aw20.jettydesktop.ui.ServerConfigMap;
import org.aw20.util.DateUtil;

public class ServerTab extends JPanel implements ExecutorInterface{
	private static final long serialVersionUID = 1L;

	private final ServerConfigMap serverConfigMap;
	private final ConfigActionInterface configActionI;
	private JButton startstopButton;
	private JButton editConfigButton;
	private JButton deleteButton;
	private JButton gotoButton;
	
	private Executor	executor = null;
	private JTextArea textArea;
	private JLabel		labelStatus;
	private JButton btnClose;
	private JPanel panel;
	private JLabel RemoteMemoryLabel;
	
	public ServerTab(ServerConfigMap _serverConfigMap, ConfigActionInterface _configActionI ) {
		this.serverConfigMap 	= _serverConfigMap;
		this.configActionI		= _configActionI;

		setLayout(new BorderLayout(0, 0));
		
		JScrollPane jConsoleScrollPane = new JScrollPane();
		jConsoleScrollPane.setMinimumSize( new Dimension(400, 500) );
		
		textArea = new JTextArea();
		textArea.setTabSize(2);
		textArea.setFont(new Font("Courier New", Font.PLAIN, 12));
		textArea.setEditable(false);

		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		jConsoleScrollPane.setViewportView(textArea);
		add(jConsoleScrollPane, BorderLayout.CENTER);
		
		
		Panel panel_1 = new Panel();
		add(panel_1, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		editConfigButton = new JButton("Edit");
		editConfigButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				configActionI.onEdit(serverConfigMap);
			}
		});
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				configActionI.onClose(serverConfigMap);
			}
		});
		
		
		GridBagConstraints gbc_btnClose = new GridBagConstraints();
		gbc_btnClose.insets = new Insets(5,5,5,5);
		gbc_btnClose.gridx = 0;
		gbc_btnClose.gridy = 0;
		panel_1.add(btnClose, gbc_btnClose);
		GridBagConstraints gbc_button_3 = new GridBagConstraints();
		gbc_button_3.insets = new Insets(5, 5, 5, 5);
		gbc_button_3.gridx = 1;
		gbc_button_3.gridy = 0;
		panel_1.add(editConfigButton, gbc_button_3);
		
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog( getParent(), "Do you wish to delete this server?");
				if ( result == JOptionPane.OK_OPTION ){
					configActionI.onDelete(serverConfigMap);
				}
			}
		});
		GridBagConstraints gbc_button_4 = new GridBagConstraints();
		gbc_button_4.insets = new Insets(5, 5, 5, 5);
		gbc_button_4.gridx = 2;
		gbc_button_4.gridy = 0;
		panel_1.add(deleteButton, gbc_button_4);

		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.insets = new Insets(5, 5, 5, 5);
		gbc_btnClear.gridx = 3;
		gbc_btnClear.gridy = 0;
		panel_1.add(clearButton, gbc_btnClear);
		
		startstopButton = new JButton("Start Server");
		startstopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( executor == null ){
					startServer();
				}else{
					stopServer();
				}
			}
		});
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.anchor = GridBagConstraints.EAST;
		gbc_button_1.insets = new Insets(5, 5,5, 5);
		gbc_button_1.gridx = 5;
		gbc_button_1.gridy = 0;
		panel_1.add(startstopButton, gbc_button_1);
		
		gotoButton = new JButton("Open WebApp");
		gotoButton.setEnabled(false);
		gotoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( !executor.isWebAppRunning() )
					return;
				
				try {
					String host	= serverConfigMap.getIP();
					if ( host == null || host.length() == 0 )
						host	= "127.0.0.1";
					
					String defaultUri	= serverConfigMap.getDefaultWebUri();
					if ( defaultUri == null )
						defaultUri = "/";
					
					java.awt.Desktop.getDesktop().browse(java.net.URI.create("http://" + host + ":" + serverConfigMap.getPort() + defaultUri ));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.anchor = GridBagConstraints.EAST;
		gbc_button_2.insets = new Insets(5, 5, 5, 5);
		gbc_button_2.gridx = 6;
		gbc_button_2.gridy = 0;
		panel_1.add(gotoButton, gbc_button_2);
		
		panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		
		labelStatus	= new JLabel("Last Updated");
		panel.add(labelStatus, BorderLayout.WEST);
		labelStatus.setBorder( BorderFactory.createEmptyBorder(3, 3, 3, 3) );
		
		RemoteMemoryLabel = new JLabel("Memory Usage: 0 / 0 MB  ");
		panel.add(RemoteMemoryLabel, BorderLayout.EAST);
	}
	
	public boolean isServerRunning(){
		return (executor != null);
	}
	
	public void stopServer(){
		startstopButton.setEnabled(false);
		startstopButton.setText( "Stopping..." );
		executor.exit();
	}
	
	private void startServer(){
		startstopButton.setEnabled(false);
		startstopButton.setText( "Starting..." );
		try {
			executor	= new Executor(serverConfigMap, this );
		} catch (IOException e) {
			onConsole( e.getMessage() );
			startstopButton.setEnabled(true);
			startstopButton.setText( "Start Server" );
		}
	}
	
	public ServerConfigMap getConfig(){
		return serverConfigMap;
	}
	
	public void setConfig(ServerConfigMap scm){
		serverConfigMap.clear();
		serverConfigMap.putAll( scm );
	}

	public void onMemory( String m ){
		RemoteMemoryLabel.setText(m);
	}
	
	@Override
	public synchronized void onConsole(String message) {
		textArea.append(message);
		textArea.append("\r\n");
		labelStatus.setText( "Console updated: " + DateUtil.getHttpDate(System.currentTimeMillis()) );
		
		if ( message.indexOf( JettyRunTime.JETTYSTARTED ) != -1 )
			gotoButton.setEnabled(true);
	}

	@Override
	public void onServerStart() {
		onConsole( "Server Starting" );
		gotoButton.setEnabled(false);
		editConfigButton.setEnabled(false);
		deleteButton.setEnabled(false);
		btnClose.setEnabled(false);
		startstopButton.setEnabled(true);
		startstopButton.setText("Stop Server");
	}

	@Override
	public synchronized void onServerExit() {
		if ( executor == null )
			return;
		
		onConsole("Server terminated.");
		gotoButton.setEnabled(false);
		editConfigButton.setEnabled(true);
		deleteButton.setEnabled(true);
		startstopButton.setEnabled(true);
		btnClose.setEnabled(true);
		startstopButton.setText("Start Server");
		
		executor	= null;
	}
}
