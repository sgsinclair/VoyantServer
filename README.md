## Voyant Server ##

Voyant Server is a web application launcher for Voyant Tools – it makes it easy to run a stand-alone instance of Voyant Tools on your local machine, rather than A desktop launcher to control one or more Jetty instances.   Make developing web-apps very easy by running up apps quickly and easily.

Voyant Server (not Voyant Tools, the web application) is an extensive adaption of JettyDesktop, see https://github.com/aw20/jettydesktop.

## Installation ##
For more details on installation, configuration and running, see http://docs.voyant-tools.org/run-your-own/voyant-server/

Jetty Desktop requires Java6+ to be installed, but after that, it ships with everything you need to start running your web applications in Jetty (v8.1).

## Known Issues ##
* VoyantServer has to be run from a folder that doesn't have a space (all the way up to the root)
* several tools and skins from Voyant Tools 1.0 still not available
* no backwards compatibility for Voyant Tools 1.0 yet

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
* version 2.0 M2 (Dec 16, 2014)
	* fix for file uploading
	* new UI elements in tool headers: help, options, switch tools, and export
* version 2.0 M4 (May 28, 2015)
	* shifted to using tabs in for tools (shows more tools available)
	* fixed bug for ingesting HTML (entire file was used before because of regression bug)
	* clicking on items in trends now triggers events properly
	* much improved loading of framework assets (much faster to load remotely)
	* more context feature has returned to KWICs
	* global stopword lists
	* and [much more](https://github.com/sgsinclair/Voyant/issues?q=milestone%3A%222.0+M4%22)
* version 2.0 M5 (Jule 23, 2015)
	* new Phrases tool (for query and queryless n-gram analysis)
	* restored input format options during corpus creation
	* improved search box with better help
	* and [more](https://github.com/sgsinclair/Voyant/issues?q=milestone%3A%222.0+M5%22)
* version 2.0 M6 (October 19, 2015)
	* export URL of default skin now remembers which panels are active
	* easier to have no stopwords selected
	* introdcutory text and documentation link under box
	* improved HTML5 Cirrus
	* preliminary RezoViz
	* reader enhancements
	* and [more](https://github.com/sgsinclair/Voyant/issues?q=milestone%3A%222.0+M6%22)
* version 2.0 M7 (December 24, 2015)
 	* built-in documentation
 	* modify (add, remove, reorder) documents in a corpus
 	* improved support for some Asian languages
 	* improved support for touch devices
 	* Knots tool reimplemented
 	* fixed bug for applying stopwords globally
 	* new corpus collocates visualization
	* export URL of default skin now remembers which panels are active
	* easier to have no stopwords selected
	* introdcutory text and documentation link under box
	* improved HTML5 Cirrus
	* preliminary RezoViz
	* reader enhancements
	* and [more](https://github.com/sgsinclair/Voyant/issues?q=milestone%3A%222.0+M7%22)

## License ##
Voyant Server is released under the same license as JettyDesktop, the GNU General Public License v3.0 (see license-GPL3.txt in this directory).
