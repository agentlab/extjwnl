/**
 *
 */
package net.sf.extjwnl.component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.dictionary.Dictionary;
import net.sf.extjwnl.service.IExtjwnlService;

/**
 * @author admin
 *
 */
public class ExtjwnlComponent
    implements IExtjwnlService {

    private List<Dictionary> dictionaries = new LinkedList<>();
    private final String pluginInstallDir = getPluginDir("net.sf.extjwnl.data.wn31");
    private final String pathPattern = "_resource_properties.xml";

    @Override
    public List<Dictionary> getDictionary() {
        return dictionaries;
    }

    @Activate
    public void activate(ComponentContext context) {
        buildDictionaries();
    }

    private synchronized void buildDictionaries() {
        try
        {
            final File folder = new File(pluginInstallDir);

            for (final File fileEntry : folder.listFiles())
            {
                if (fileEntry.isFile() && fileEntry.toString().contains(pathPattern))
                {
                    InputStream inputStream = fileEntry.toURI().toURL().openStream();
                    dictionaries.add(Dictionary.getInstance(inputStream));
                }
            }

        }
        catch (JWNLException | IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static String getPluginDir(String pluginId) {
        /* get bundle with the specified id */
        // pluginId = pluginId.
        Bundle bundle = Platform.getBundle(pluginId);
        if (bundle == null)
        {
            throw new RuntimeException("Could not resolve plugin: " + pluginId + "\r\n"
                + "Probably the plugin has not been correctly installed.\r\n"
                + "Running eclipse from shell with -clean option may rectify installation.");
        }

        /* resolve Bundle::getEntry to local URL */
        URL pluginURL = null;
        try
        {
            pluginURL = Platform.resolve(bundle.getEntry("/"));
        }
        catch (IOException e)
        {
            throw new RuntimeException("Could not get installation directory of the plugin: " + pluginId);
        }
        String pluginInstallDir = pluginURL.getPath().trim();
        if (pluginInstallDir.length() == 0)
        {
            throw new RuntimeException("Could not get installation directory of the plugin: " + pluginId);
        }

        /* since path returned by URL::getPath starts with a forward slash, that
         * is not suitable to run commandlines on Windows-OS, but for Unix-based
         * OSes it is needed. So strip one character for windows. There seems
         * to be no other clean way of doing this. */
        /* if (Platform.getOS().compareTo(Platform.OS_MACOSX) == 0)
        {
            pluginInstallDir = pluginInstallDir.substring(1);
        }*/

        return pluginInstallDir;
    }

}
