## Voyant Server ##
Voyant Server is a web application launcher for Voyant Tools – it makes it easy to run a stand-alone instance of Voyant Tools on your local machine, rather than A desktop launcher to control one or more Jetty instances.   Make developing web-apps very easy by running up apps quickly and easily.

Voyant Server (not Voyant Tools, the web application) is an extensive adaption of JettyDesktop, see https://github.com/aw20/jettydesktop.

## Installation ##
For more details on installation, configuration and running, see http://docs.voyant-tools.org/run-your-own/voyant-server/

Jetty Desktop requires Java6+ to be installed, but after that, it ships with everything you need to start running your web applications in Jetty (v8.1).

## License ##
Voyant Server is released under the same license as JettyDesktop, the GNU General Public License v3.0 (see license-GPL3.txt in this directory).

## Release Notes ##
* version 1.0 (June 26th, 2014)
	* Initial release
* version 1.1 (July 5th, 2014)
	* FIX: server is terminated properly when quitting application on Mac
	* NEW: open menu of Voyant Tools home page now shows local corpora
	* NEW: now possible to specify open menu labels in Voyant Tools (see settings file)
	* NEW: now possible to make server open (beyond local address – see settings)
* version 2.0 M1 (Dec. 2, 2014)
	* new release to coincide with Voyant Tools 2.0 M1: https://github.com/sgsinclair/Voyant/tree/v2.0.0-M1