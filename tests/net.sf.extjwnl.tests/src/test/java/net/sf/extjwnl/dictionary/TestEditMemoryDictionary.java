package net.sf.extjwnl.dictionary;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author <a href="http://autayeu.com/">Aliaksandr Autayeu</a>
 */
public class TestEditMemoryDictionary extends DictionaryEditTester {

    @Override
    protected InputStream getProperties() {

        URL url;

        try
        {
            url = new URL("platform:/plugin/net.sf.extjwnl/net/sf/extjwnl/dictionary/mem_properties.xml");
            return url.openStream();
        }
        catch (MalformedURLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        return Dictionary.class.getResourceAsStream("/net/sf/extjwnl/dictionary/mem_properties.xml");
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
