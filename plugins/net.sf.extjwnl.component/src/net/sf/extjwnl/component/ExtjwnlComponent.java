/**
 *
 */
package net.sf.extjwnl.component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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

    private Dictionary dictionary;

    @Override
    public Dictionary getDictionary() {
        return dictionary;
    }

    @Activate
    public void activate(ComponentContext context) throws IOException {

        try
        {
            URL url =
                new URL("platform:/plugin/net.sf.extjwnl.component/src/extjwnl/component/extjwnl_resource_properties.xml");
            InputStream inputStream = url.openConnection().getInputStream();
            dictionary = Dictionary.getInstance(inputStream);
        }
        catch (FileNotFoundException | JWNLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.err.println("Start !!!!"); //$NON-NLS-1$

    }

}
