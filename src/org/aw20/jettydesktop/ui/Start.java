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
package org.aw20.jettydesktop.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import org.aw20.jettydesktop.ui.awt.ServerConfig;
import org.aw20.jettydesktop.ui.awt.ServerTab;

public class Start implements ConfigActionInterface {
	private static String VERSION = "2.0.8";
	
	private List<ServerConfigMap>	serverConfigList;
	
	private JFrame frame;
	private JMenu mnServer;
	private JMenuItem mAddServerMenuItem;
	private JTabbedPane tabbedPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Start window = new Start();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Start() {
		loadSettings();
		initialize();
	}

	private void saveSettings(){
		ObjectOutputStream OS;
		try {
			FileOutputStream out	= new FileOutputStream( new File("jettydesktop.settings") ); 
			OS = new ObjectOutputStream( out );
	    OS.writeObject( serverConfigList );
	    out.flush();
	    out.close();
		} catch (IOException e) {}
	}
	

	private void loadSettings(){
		ObjectInputStream ois;
		try {
			FileInputStream in	= new FileInputStream( new File("jettydesktop.settings") ); 
			ois = new ObjectInputStream(in);
			serverConfigList = (java.util.List<ServerConfigMap>)ois.readObject();
      in.close();
		} catch (Exception e) {
			serverConfigList = new ArrayList<ServerConfigMap>();
		}
	}	
	

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("JettyDesktop v" + VERSION + " by aw2.0 Ltd");
		frame.setBounds(100, 100, 610, 414);
		frame.setMinimumSize( new Dimension(610, 414) );
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/org/aw20/logo/aw20.jpg")));
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				terminateApp();
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				terminateApp();
			}
		});
		
		mnServer = new JMenu("Web App");
		menuBar.add(mnServer);
		
		mAddServerMenuItem = new JMenuItem("< add webapp >");
		mAddServerMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openConfig( ServerConfigMap.getDefault() );
			}
		});
		rebuildServerMenu();
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmSupportSite = new JMenuItem("Visit Home Page");
		mntmSupportSite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://github.com/aw20/jettydesktop/"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnHelp.add(mntmSupportSite);
		
		JMenuItem mntmBugSite = new JMenuItem("Report Bug/Feature");
		mntmBugSite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://github.com/aw20/jettydesktop/issues"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnHelp.add(mntmBugSite);

		JMenuItem mntmAWSite = new JMenuItem("Visit aw2.0 Ltd");
		mntmAWSite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					java.awt.Desktop.getDesktop().browse(java.net.URI.create("http://aw20.is/"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnHelp.add(mntmAWSite);
		mnHelp.addSeparator();
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog (null, "Designed/Developed by aw2.0 Ltd\r\nhttp://aw20.is/\r\n\r\nGPLv3.0 License\r\nv" + VERSION + " September 2013", "About JettyDesktop", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		
		mnHelp.add(mntmAbout);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
	}

	protected boolean terminateApp() {
		int totalRunning = 0;
		for ( int x=0; x < tabbedPane.getComponentCount(); x++ ){
			ServerTab sT	= (ServerTab)tabbedPane.getComponent(x);
			if ( sT.isServerRunning() )
				totalRunning++;
		}
		
		if ( totalRunning == 0 )
			System.exit(1);
		
		int result = JOptionPane.showConfirmDialog( this.frame, "You have " + totalRunning + " server(s) still running.\r\n\r\nDo you still wish to terminate?\r\n");
		if ( result == JOptionPane.OK_OPTION ){
			for ( int x=0; x < tabbedPane.getComponentCount(); x++ ){
				ServerTab sT	= (ServerTab)tabbedPane.getComponent(x);
				if ( sT.isServerRunning() )
					sT.stopServer();
			}
			System.exit(1);
		}
		
		return false;
	}

	private void rebuildServerMenu(){
		mnServer.removeAll();
		
		mnServer.add( mAddServerMenuItem );
		
		if ( serverConfigList.isEmpty() )
			return;
		
		mnServer.addSeparator();
		
		// Get the names
		String[]	names	= new String[serverConfigList.size()];
		for ( int x=0; x < names.length; x++ )
			names[x] = serverConfigList.get(x).getName();

		Arrays.sort( names );
		
		for ( int x=0; x < names.length; x++ ){
			
			JMenuItem menu = new JMenuItem(names[x]);
			menu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					openServer( ((JMenuItem)e.getSource()).getText() );
				}
			});
			
			mnServer.add( menu );
		}
	}
	
	
	protected void openServer(String servername) {

		// See if that one is already open in the tabs
		for ( int x=0; x < tabbedPane.getComponentCount(); x++ ){
			ServerTab sT	= (ServerTab)tabbedPane.getComponent(x);
			if ( sT.getConfig().getName().equals(servername) ){
				tabbedPane.setSelectedIndex(x);
				return;
			}
		}
				
		// We need to add a new tab
		ServerTab	sT	= new ServerTab( get(servername), this );
		tabbedPane.add(sT, servername);
		tabbedPane.setSelectedComponent(sT);
	}
	
	
	private ServerConfigMap get(String name){
		for ( int x=0; x < serverConfigList.size(); x++ ){
			if ( serverConfigList.get(x).getName().equals( name ) ){
				return serverConfigList.get(x);
			}
		}
		return null;
	}
	

	private void openConfig( ServerConfigMap serverConfigMap ){
		ServerConfig	popup	= new ServerConfig( serverConfigMap );
		popup.setVisible(true);
		if ( popup.getConfig() == null )
			return;
		
		ServerConfigMap	scm	= popup.getConfig();

		// We need to run through the list see if its already there
		boolean bFound = false;
		for ( int x=0; x < serverConfigList.size(); x++ ){
			if ( serverConfigList.get(x).getName().equals( scm.getName() ) ){
				serverConfigList.set(x, scm);
				bFound = true;
				break;
			}
		}

		if ( !bFound )
			serverConfigList.add(scm);
		
		saveSettings();
		rebuildServerMenu();
		
		// Update the reference if it is used in an active TAB
		for ( int x=0; x < tabbedPane.getComponentCount(); x++ ){
			ServerTab sT	= (ServerTab)tabbedPane.getComponent(x);
			if ( sT.getConfig().getName().equals(scm.getName()) ){
				sT.setConfig(scm);
				break;
			}
		}
	}

	@Override
	public void onEdit(ServerConfigMap scm) {
		openConfig(scm);
	}

	@Override
	public void onDelete(ServerConfigMap scm) {
		
		// Remove from the tabs
		onClose(scm);
		
		// Remove from the list
		for ( int x=0; x < serverConfigList.size(); x++ ){
			if ( serverConfigList.get(x).getName().equals( scm.getName() ) ){
				serverConfigList.remove(x);
				break;
			}
		}
		
		saveSettings();
		rebuildServerMenu();
	}

	@Override
	public void onClose(ServerConfigMap scm) {
		
		// Remove from the tabs
		for ( int x=0; x < tabbedPane.getComponentCount(); x++ ){
			ServerTab sT	= (ServerTab)tabbedPane.getComponent(x);
			if ( sT.getConfig().getName().equals(scm.getName()) ){
				tabbedPane.remove(x);
				break;
			}
		}
	}
	
}
