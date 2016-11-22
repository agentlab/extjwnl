/**
 *
 */
package extjwnl.component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;

import extjwnl.service.IExtjwnlService;
import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.dictionary.Dictionary;
import net.sf.extjwnl.utilities.Examples;

/**
 * @author admin
 *
 */
public class ExtjwnlComponent
    implements IExtjwnlService {

    private Dictionary dictionary;

    public ExtjwnlComponent() throws IOException {
        int a = 0;
        a = 9;
        try
        {
            URL url =
                new URL("platform:/plugin/extjwnl.component/src/extjwnl/component/extjwnl_resource_properties.xml");
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

    @Override
    public Dictionary getDictionary() {
        return dictionary;
    }

    @Activate
    public void activate(ComponentContext context) throws IOException {

        try
        {
            int a;
            a = 9;
            FileInputStream inputStream = new FileInputStream("./extjwnl_resource_properties.xml"); //$NON-NLS-1$
            System.err.println("/ExtjwnlComponent.java"); //$NON-NLS-1$
//            System.out.println(Dictionary.CHECK_LEX_IDS_KEY);
            dictionary = Dictionary.getInstance(inputStream);
            // this.dictionary = Dictionary.getDefaultResourceInstance();
        }
        catch (JWNLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (null != dictionary) {
            try
            {
                new Examples(dictionary).go();
            }
            catch (JWNLException | CloneNotSupportedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.println("ExjwnlComponent stated"); //$NON-NLS-1$

    }

}
