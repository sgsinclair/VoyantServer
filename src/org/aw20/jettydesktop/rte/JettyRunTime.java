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
package org.aw20.jettydesktop.rte;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyRunTime extends Object {

	public static String JETTYSTARTED = "... Jetty has started.";
	
	public static void main(String[] args) {
		System.out.println( "Jetty Version: " + Server.getVersion() );
		
		if ( args.length == 3 )
			System.out.println( "http://*:" + args[0] );
		else
			System.out.println( "http://" + args[0] + ":" + args[1] );

		try {
			if ( args.length == 3 )
				new JettyRunTime( null, args[0], args[1], args[2] );
			else
				new JettyRunTime( args[0], args[1], args[2], args[3] );
		} catch (Exception e) {
			System.out.println( e.getMessage() );
		}
	}

	private Server server;
	
	public JettyRunTime( String ip, String port, String webapp, String adminport ) throws Exception{
		System.out.println( "Jetty starting up ... please wait" );
		
		if ( ip == null )
			server = new Server( Integer.valueOf(port) );
		else
			server = new Server( InetSocketAddress.createUnresolved(ip, Integer.valueOf(port)) );
		
		int aport	= Integer.valueOf(adminport);
		if ( aport > 0 ){
			new adminPort(aport);
		}
		
		WebAppContext context = new WebAppContext();
		
		context.setDescriptor( webapp + "/WEB-INF/web.xml");
		context.setResourceBase( webapp );
		context.setContextPath("/");
		context.setParentLoaderPriority(true);
		context.setDefaultsDescriptor("org/aw20/jettydesktop/rte/webdefault.xml");
		
		server.setHandler(context);
		server.start();
		
		System.out.println( JETTYSTARTED );
	}
	
	
	class adminPort extends Thread {
		
		ServerSocket	ss;
		Socket s;
		int port;
		PrintWriter	pw;
		
		public adminPort( int port ){
			this.port = port;
			setDaemon(true);
			start();
		}
		
		public void run(){
			try {
				ss	= new ServerSocket(port);
				s		=	ss.accept();
				pw	= new	PrintWriter( new OutputStreamWriter( s.getOutputStream() ) ); 
				
				for(;;){
					Thread.sleep(2000);

	      	long freeMem 	= (Runtime.getRuntime().freeMemory() / 1024000);
	      	long totalMem	= (Runtime.getRuntime().totalMemory() / 1024000);
	      	long usedMem	= totalMem - freeMem;
	      	pw.println("Memory Usage: " + usedMem + "MB of " + totalMem + "MB  ");
	      	pw.flush();

				}
				
			} catch (Exception e) {
				System.out.println(e);
			}
			
		}
		
	}
	
}