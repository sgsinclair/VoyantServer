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
			setIP("127.0.0.1");
			setPort("8888");
			setWebFolder(System.getProperty("user.dir") + File.separator + "_app");
			setCurrentJVM();
			setMemoryJVM("1024");
		}};
		Properties properties = new Properties();
		File file = new File("server-settings.txt");
		FileInputStream fis = new FileInputStream(file);
		properties.load(fis);
		fis.close();
		for (String name : properties.stringPropertyNames()) {
			String val = properties.getProperty(name);
			if (val.trim().isEmpty()) { continue;}
			if (name.equals("port")) {
				serverConfigMap.setPort(val);
			}
			else if (name.equals("memory")) {
				serverConfigMap.setMemoryJVM(val);
			}
			else if (name.equals("data_directory")) {
				serverConfigMap.setDefaultJVMArgs("-Djava.io.tmpdir="+val);
			}
			else {
				serverConfigMap.put(name, properties.getProperty(name));
			}
		}
		return serverConfigMap;
	}
	
	public static void main(String[] args) throws IOException {
		ServerConfig.getStoredServerConfig();
	}
}
