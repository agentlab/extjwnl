/**
 *
 */
package net.sf.extjwnl.component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

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

    private List<Dictionary> dictionary = new LinkedList<>();

    @Override
    public List<Dictionary> getDictionary() {
        return dictionary;
    }

    @Activate
    public void activate(ComponentContext context) throws IOException {

        try
        {
            URL url = new URL("platform:/plugin/net.sf.extjwnl.data.wn31/extjwnl_resource_properties.xml");
//            URL url = new URL("platform:/plugin/net.sf.extjwnl.data.wn31");
//            final File folder = new File(url.getContent());
//            FileUtils;

            InputStream inputStream = url.openConnection().getInputStream();
            dictionary.add(Dictionary.getInstance(inputStream));
        }
        catch (JWNLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.err.println("Start !!!!"); //$NON-NLS-1$

    }

}
