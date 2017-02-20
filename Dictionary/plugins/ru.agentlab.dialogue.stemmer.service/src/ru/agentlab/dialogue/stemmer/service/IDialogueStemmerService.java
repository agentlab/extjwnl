/**
 *
 */
package ru.agentlab.dialogue.stemmer.service;

import java.util.List;

import ru.agentlab.mystemwrapper.Word;

/**
 * @author admin
 *
 */
public interface IDialogueStemmerService {
    public List<Word> getSemantic(String text);
}
