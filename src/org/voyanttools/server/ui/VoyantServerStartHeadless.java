/**
 * 
 */
package org.voyanttools.server.ui;

import java.io.IOException;

import org.aw20.jettydesktop.rte.JettyRunTime;
import org.aw20.jettydesktop.ui.ConfigActionInterface;
import org.aw20.jettydesktop.ui.Executor;
import org.aw20.jettydesktop.ui.ExecutorInterface;
import org.aw20.jettydesktop.ui.ServerConfigMap;

/**
 * @author sgs
 *
 */
public class VoyantServerStartHeadless implements ConfigActionInterface, ExecutorInterface {

	/**
	 * @throws IOException 
	 * 
	 */
	public VoyantServerStartHeadless(ServerConfigMap serverConfigMap) throws IOException {
		Executor executor	= new Executor(serverConfigMap, this);
	}

	/* (non-Javadoc)
	 * @see org.aw20.jettydesktop.ui.ConfigActionInterface#onEdit(org.aw20.jettydesktop.ui.ServerConfigMap)
	 */
	@Override
	public void onEdit(ServerConfigMap scm) {
		onConsole("edit");
	}

	/* (non-Javadoc)
	 * @see org.aw20.jettydesktop.ui.ConfigActionInterface#onDelete(org.aw20.jettydesktop.ui.ServerConfigMap)
	 */
	@Override
	public void onDelete(ServerConfigMap scm) {
		onConsole("delete");
	}

	/* (non-Javadoc)
	 * @see org.aw20.jettydesktop.ui.ConfigActionInterface#onClose(org.aw20.jettydesktop.ui.ServerConfigMap)
	 */
	@Override
	public void onClose(ServerConfigMap scm) {
		onConsole("close");
	}

	@Override
	public void onServerStart() {
		onConsole("server start");
	}

	@Override
	public void onServerExit() {
		onConsole("server exit");
	}

	@Override
	public void onConsole(String message) {
		System.out.println(message);
		if ( message.indexOf( JettyRunTime.JETTYSTARTED ) != -1 ) {
			System.out.println("\n\nPlease note that you will need to kill the Jetty process yourself since it's running separately.");
		}
	}

	@Override
	public void onMemory(String memory) {
		onConsole("memory");
	}

}
