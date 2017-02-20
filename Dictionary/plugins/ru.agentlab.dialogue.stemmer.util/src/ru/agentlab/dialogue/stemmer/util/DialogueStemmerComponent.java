/**
 *
 */
package ru.agentlab.dialogue.stemmer.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.parser.ParseException;

import net.sf.extjwnl.dictionary.Dictionary;
import net.sf.extjwnl.service.IExtjwnlService;
import ru.agentlab.dialogue.stemmer.service.IDialogueStemmerService;
import ru.agentlab.mystemwrapper.StemmerWrapper;
import ru.agentlab.mystemwrapper.Word;
import ru.agentlab.mystemwrapper.service.IMyStemWrapperService;

/**
 * @author admin
 *
 */
public class DialogueStemmerComponent
    implements IDialogueStemmerService {

    private IExtjwnlService extjwnlService;
    private List<Dictionary> dictionary = new LinkedList<>();

    private StemmerWrapper stemmerWrapper;
    private IMyStemWrapperService stemmerWrapperService;

    public synchronized void setDictionary(IExtjwnlService service) {

        this.extjwnlService = service;
        this.dictionary = this.extjwnlService.getDictionary();
    }

    public synchronized void unsetDictionary(IExtjwnlService service) {
        if (this.dictionary != null)
        {
            this.dictionary = null;
        }
        if (this.extjwnlService == service)
        {
            this.extjwnlService = null;
        }
    }

    public synchronized void setStemWrapper(IMyStemWrapperService service) {

        this.stemmerWrapperService = service;
        this.stemmerWrapper = this.stemmerWrapperService.getStemmerWrapper();

    }

    public synchronized void unsetStemWrapper(IMyStemWrapperService service) {
        if (this.stemmerWrapper != null)
        {
            this.stemmerWrapper = null;
        }
        if (this.stemmerWrapperService == service)
        {
            this.stemmerWrapperService = null;
        }
    }

    @Override
    public List<Word> getSemantic(String text) {

        List<String> line = new ArrayList<>();
        line.add(text);

        try
        {
            return stemmerWrapper.analysis(line);
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

}
