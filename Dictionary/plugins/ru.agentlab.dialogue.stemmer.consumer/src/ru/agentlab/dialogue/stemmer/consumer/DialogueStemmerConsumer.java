/**
 *
 */
package ru.agentlab.dialogue.stemmer.consumer;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.IndexWordSet;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.PointerUtils;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.data.list.PointerTargetNodeList;
import net.sf.extjwnl.dictionary.Dictionary;
import ru.agentlab.dialogue.stemmer.service.IDialogueStemmerService;
import ru.agentlab.mystemwrapper.GrammemeType;
import ru.agentlab.mystemwrapper.Word;

/**
 * @author admin
 *
 */
public class DialogueStemmerConsumer {

    private IDialogueStemmerService dialog;
    static Util util = Util.getInstance();
    private Dictionary dictionary;

    public synchronized void bindDialog(IDialogueStemmerService service) {
        System.err.println("Service dialog was set.");

        this.dialog = service;

        List<Word> words =
            dialog.getSemantic("Пользователь Марина опубликовал запись в блоге");

        StringBuilder semantic = new StringBuilder();

        for (Word word : words)
        {
            semantic.append(word.getValue());
            Set<Word.Lexema> lexemas = word.getLexemas();
            for (Word.Lexema lexema : lexemas)
            {
                Iterator<GrammemeType> iter = lexema.getGrammemes().iterator();

                semantic.append("\n\t[ ");

                while (iter.hasNext())
                {
                    semantic.append(util.getGrammemeTypeToString().get(iter.next()) + ", ");

                }

                if (semantic.charAt(semantic.length() - 2) == ',')
                {
                    semantic.delete(semantic.length() - 2, semantic.length() - 1);
                }

                semantic.append("]\n");
            }

        }

        System.out.println(semantic);

        String example = "Post this article in your blog";
        String[] exampleWords = example.split(" ");
        // Синонимы
        try
        {
            // Подключаем словарь
            setUpDictionary();

            for (int i = 0; i < exampleWords.length; i++)
            {
                IndexWord currentWord = getWord(exampleWords[i]);
                if (currentWord != null)
                {
                    printSynonyms(currentWord);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

    public void setUpDictionary() throws FileNotFoundException, JWNLException, CloneNotSupportedException {
        Dictionary dictionary = null;
        dictionary = Dictionary.getDefaultResourceInstance();
        if (null != dictionary)
        {
            this.dictionary = dictionary;
        }
    }

    private void printSynonyms(IndexWord word) throws JWNLException {
        System.out.println("Синонимы " + word.getLemma() + ":");
        // Для глаголов и существительных используются гиперонимы, для прилагательных синонимы. Так устроен extjwnl,
        // см. PointerUtils.getSynonyms
        List<Synset> listSs = word.getSenses();
        if (listSs != null && listSs.get(0) != null)
        {
            if (word.getPOS() == POS.NOUN || word.getPOS() == POS.VERB)
            {
                PointerTargetNodeList hypernyms = PointerUtils.getDirectHypernyms(listSs.get(0));
                hypernyms.print();
            }
            else
            {
                PointerTargetNodeList synonyms = PointerUtils.getSynonyms(listSs.get(0));
                synonyms.print();
            }
        }
    }

    public IndexWord getWord(String word) throws JWNLException {
        IndexWordSet wordSet1 = dictionary.lookupAllIndexWords(word);
        IndexWord[] wordArray = wordSet1.getIndexWordArray();
        if (wordArray.length > 0)
        {
            return wordArray[0];
        }
        return null;
    }

    public synchronized void unbindDialog(IDialogueStemmerService service) {
        this.dialog = null;
    }
}