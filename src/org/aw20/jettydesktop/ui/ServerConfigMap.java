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

import java.io.Serializable;
import java.util.HashMap;

public class ServerConfigMap extends HashMap<String, String> implements Serializable {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("serial")
	public static ServerConfigMap getDefault(){
		return new ServerConfigMap(){{
			setName("");
			setIP("127.0.0.1");
			setPort("80");
			setWebFolder("");
			setCurrentJVM();
			setMemoryJVM("64");
		}};
	}
	
	public void setName(String name){
		put("SERVER_NAME", name);
	}
	
	public String getName(){
		return get("SERVER_NAME");
	}
	
	public void setIP(String ip){
		put("SERVER_IP", ip);
	}
	
	public String getIP(){
		return get("SERVER_IP");
	}
	
	public void setPort(String port){
		put("SERVER_PORT", port);
	}
	
	public String getPort(){
		return get("SERVER_PORT");
	}
	
	public void setWebFolder(String webfolder){
		put("WEBFOLDER", webfolder);
	}
	
	public String getWebFolder(){
		return get("WEBFOLDER");
	}
	
	public void setCustomJVM(String customFolder){
		put("CUSTOMJVM", customFolder);
		remove("CURRENTJVM");
	}
	
	public String getCustomJVM(){
		return get("CUSTOMJVM");
	}
	
	public void setCurrentJVM(){
		put("CURRENTJVM", "1");
		remove("CUSTOMJVM");
	}
	
	public String getCurrentJVM(){
		return get("CURRENTJVM");
	}
	
	public void setMemoryJVM(String memory){
		put("MEMORYJVM", memory);
	}
	
	public String getMemoryJVM(){
		return get("MEMORYJVM");
	}

	public String getDefaultJVMArgs() {
		return get("DEFAULTJVM");
	}

	public String getDefaultWebUri() {
		return get("DEFAULTURI");
	}

	public void setDefaultJVMArgs(String args) {
		put("DEFAULTJVM", args);
	}

	public void setDefaultWebUri(String args) {
		put("DEFAULTURI", args.trim() );
	}
}