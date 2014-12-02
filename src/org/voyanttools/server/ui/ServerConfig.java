package org.voyanttools.server.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.aw20.jettydesktop.ui.ServerConfigMap;

public class ServerConfig {

	public static ServerConfigMap getStoredServerConfig() throws IOException {
		ServerConfigMap serverConfigMap =  new ServerConfigMap(){{
			setName("Voyant");
			setIP("");
			setPort("8888");
			setWebFolder(System.getProperty("user.dir") + File.separator + "_app");
			setCurrentJVM();
			setMemoryJVM("1024");
		}};
		Properties properties = new Properties();
		File file = getFile();
		serverConfigMap.put("lastModified", String.valueOf(file.lastModified()));
		FileInputStream fis = new FileInputStream(file);
		properties.load(fis);
		fis.close();
		for (String name : properties.stringPropertyNames()) {
			String val = properties.getProperty(name).trim();
			if (val.isEmpty()) { continue;}
			if (name.equals("port")) {
				serverConfigMap.setPort(val);
			}
			else if (name.equals("memory")) {
				serverConfigMap.setMemoryJVM(val);
			}
			else if (name.equals("data_directory")) {
				serverConfigMap.setDefaultJVMArgs((serverConfigMap.getDefaultJVMArgs()!=null ? serverConfigMap.getDefaultJVMArgs()+" " : "") +"-Djava.io.tmpdir="+val);
			}
			else if (name.equals("uri_path")) {
				serverConfigMap.setDefaultWebUri(val);
			}
			else if (name.equals("host")) {
				serverConfigMap.setIP(val);
			}
			else {
				serverConfigMap.put(name, properties.getProperty(name));
			}
		}
		File f = new File("data");
		
//		serverConfigMap.setDefaultJVMArgs((serverConfigMap.getDefaultJVMArgs()!=null ? serverConfigMap.getDefaultJVMArgs()+" " : "") +"OPTIONS=Server,jsp");
		if (new File("data").exists() && (!serverConfigMap.containsKey("data_directory") || serverConfigMap.get("data_directory").isEmpty())) {
			serverConfigMap.setDefaultJVMArgs((serverConfigMap.getDefaultJVMArgs()!=null ? serverConfigMap.getDefaultJVMArgs()+" " : "") +"-Djava.io.tmpdir="+new File(System.getProperty("user.dir"),"data") );
		}
		return serverConfigMap;
	}
	
	public static boolean isModified(ServerConfigMap serverConfigMap) {
		File file = getFile();
		return file.lastModified() > Long.valueOf(serverConfigMap.get("lastModified"));
	}
	
	private static File getFile() {
		return new File("server-settings.txt");
	}
	
	public static void main(String[] args) throws IOException {
		ServerConfig.getStoredServerConfig();
	}
}
