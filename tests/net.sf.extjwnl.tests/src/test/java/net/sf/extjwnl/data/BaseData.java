package net.sf.extjwnl.data;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.junit.Before;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.dictionary.Dictionary;

/**
 * Base class for tests.
 *
 * @author <a href="http://autayeu.com/">Aliaksandr Autayeu</a>
 */
public abstract class BaseData {

    protected Dictionary dictionary;
    protected Dictionary mapDictionary;

    protected InputStream getProperties() throws IOException {
        URL url = new URL("platform:/plugin/net.sf.extjwnl/net/sf/extjwnl/dictionary/mem_properties.xml");
        InputStream inputStream = url.openConnection().getInputStream();
        return inputStream;
//        return Dictionary.class.getResourceAsStream("/net/sf/extjwnl/dictionary/mem_properties.xml");
    }

    @Before
    public void setUp() throws JWNLException, IOException {
        dictionary = Dictionary.getInstance(getProperties());
        mapDictionary = Dictionary.getInstance(getProperties());
    }
}