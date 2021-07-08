#### With the passing of Stéfan Sinclair, this repository has been archived. ####
#### Please see [voyanttools/VoyantServer](https://github.com/voyanttools/VoyantServer) for future development. ####

## Voyant Server ##

Voyant Server is a web application launcher for Voyant Tools – it makes it easy to run a stand-alone instance of Voyant Tools on your local machine, which has several potential advantages (performance, security, privacy, reliability, etc.) compared to the [hosted version](http://voyant-tools.org).

## Installation ##

See the [latest release](https://github.com/sgsinclair/VoyantServer/releases/latest) and download the VoyantServer zip file. Unzip the file (be sure to actually extract the contents into a real directory) and double-click on the VoyantServer.jar file (on Macs you may need to Ctrl-click on the VoyantServer.jar, select open and confirm the opening – this is because of security precautions in OS X).

Once you open VoyantServer.jar a controller application will appear (that allows you stop stop the server, see error messages, change settings, etc.) and a new browser will also appear with Voyant Tools. That's it!

If you receive an error message on macOS:
> *The Java JAR file “VoyantServer.jar” could not be launched. Check the Console for possible error messages.*

you may open VoyantServer via the terminal: `java -jar VoyantServer.jar` as a workaround.

## License ##
Voyant Server is released under the same license as JettyDesktop, the GNU General Public License v3.0 (see license-GPL3.txt in this directory).
