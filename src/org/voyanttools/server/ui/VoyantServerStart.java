/**
 * 
 */
package org.voyanttools.server.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import org.aw20.jettydesktop.ui.ServerConfigMap;
import org.aw20.jettydesktop.ui.Start;
import org.aw20.jettydesktop.ui.awt.ServerTab;
import org.voyanttools.server.ui.awt.VoyantServerTab;

/**
 * @author sgs
 *
 */
public class VoyantServerStart extends Start {

	private static final String VERSION = "1.0";
	
	private JFrame frame;
	
	private JTabbedPane tabbedPane;
	
	protected List<ServerConfigMap>	serverConfigList;


	/**
	 * @throws IOException 
	 * 
	 */
	public VoyantServerStart() throws IOException {
//		loadSettings();
		initialize();
	}


	public static void main(String[] args) {
		for (String string : args) {
			if (string.contains("headless=true")) {
				try {
					VoyantServerStartHeadless voyantServerStartHeadless = new VoyantServerStartHeadless();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return; // don't create gui
			}
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					VoyantServerStart window = new VoyantServerStart();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setTitle("Voyant Server");
		frame.setBounds(100, 100, 610, 414);
		frame.setMinimumSize( new Dimension(610, 414) );
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/org/voyanttools/resources/voyant_small.png")));
		
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
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmSupportSite = new JMenuItem("Visit Voyant Server Help Page");
		mntmSupportSite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					java.awt.Desktop.getDesktop().browse(java.net.URI.create("http://docs.voyant-tools.org/voyant-server/"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnHelp.add(mntmSupportSite);
		
		// We need to add a new tab
		VoyantServerTab	sT	= new VoyantServerTab(this );
		
		frame.getContentPane().add(sT, BorderLayout.CENTER);
	}
	
	protected boolean terminateApp() {
		VoyantServerTab sT	= (VoyantServerTab)frame.getContentPane().getComponent(0);
		if ( sT.isServerRunning() ) {
			sT.stopServer();
		}
		System.exit(1);
		return true;
	}
}
