/**
*
*/
package ru.agentlab.dialogue.stemmer.consumer;

import java.util.EnumMap;
import java.util.Map;

import ru.agentlab.mystemwrapper.GrammemeType;

/**
* @author dellpc
*
*/
public class Util {
    private Map<GrammemeType, String> grammemeTypeToString;
    private static Util INSTANCE;

    public static Util getInstance() {
        return create();
    }


    public Map<GrammemeType, String> getGrammemeTypeToString() {
        return this.grammemeTypeToString;
    }

    private Util() {
        this.grammemeTypeToString = new EnumMap<>(GrammemeType.class);
        this.fillGrammemeTypeToString();
    }

    private static synchronized Util create() {
        if (INSTANCE == null)
        {
            INSTANCE = new Util();
        }

        return INSTANCE;
    }

    private void fillGrammemeTypeToString() {

//        GrammemeType gt = new GrammemeType();

        grammemeTypeToString.put(GrammemeType.A, "прилагательное");
        grammemeTypeToString.put(GrammemeType.ADV, "наречие");
        grammemeTypeToString.put(GrammemeType.ADVPRO, "местоименное наречие");
        grammemeTypeToString.put(GrammemeType.ANUM, "числительное-приглагательное");
        grammemeTypeToString.put(GrammemeType.APRO, "местоимение-прилагательное");
        grammemeTypeToString.put(GrammemeType.COM, "часть композита - сложного слова");
        grammemeTypeToString.put(GrammemeType.CONJ, "союз");
        grammemeTypeToString.put(GrammemeType.INTJ, "междометие");
        grammemeTypeToString.put(GrammemeType.NUM, "числительное");
        grammemeTypeToString.put(GrammemeType.PART, "частица");
        grammemeTypeToString.put(GrammemeType.PR, "предлог");
        grammemeTypeToString.put(GrammemeType.S, "сущестивельное");
        grammemeTypeToString.put(GrammemeType.SPRO, "местоимение-существительное");
        grammemeTypeToString.put(GrammemeType.V, "глагол");

        grammemeTypeToString.put(GrammemeType.praes, "настоящее");
        grammemeTypeToString.put(GrammemeType.inpraes, "непрошедшее");
        grammemeTypeToString.put(GrammemeType.praet, "прошедшее");

        grammemeTypeToString.put(GrammemeType.nom, "именительный");
        grammemeTypeToString.put(GrammemeType.gen, "родительный");
        grammemeTypeToString.put(GrammemeType.dat, "дательный");
        grammemeTypeToString.put(GrammemeType.acc, "винительный");
        grammemeTypeToString.put(GrammemeType.ins, "творительный");
        grammemeTypeToString.put(GrammemeType.abl, "предложный");
        grammemeTypeToString.put(GrammemeType.part, "партитив (второй родительный)");
        grammemeTypeToString.put(GrammemeType.loc, "местный (второй предложный)");
        grammemeTypeToString.put(GrammemeType.voc, "звательный");

        grammemeTypeToString.put(GrammemeType.sg, "единственное число");
        grammemeTypeToString.put(GrammemeType.pl, "множественное число");

        grammemeTypeToString.put(GrammemeType.ger, "деепричастие");
        grammemeTypeToString.put(GrammemeType.inf, "инфинитив");
        grammemeTypeToString.put(GrammemeType.partcp, "причастие");
        grammemeTypeToString.put(GrammemeType.indic, "изъявительное наклонение");
        grammemeTypeToString.put(GrammemeType.imper, "повелительное наклонение");

        grammemeTypeToString.put(GrammemeType.brev, "краткая форма");
        grammemeTypeToString.put(GrammemeType.plen, "полная форма");
        grammemeTypeToString.put(GrammemeType.poss, "притяжательное прилагательное");

        grammemeTypeToString.put(GrammemeType.supr, "превосходная");
        grammemeTypeToString.put(GrammemeType.comp, "сравнительная");

        grammemeTypeToString.put(GrammemeType._1p, "1-е лицо");
        grammemeTypeToString.put(GrammemeType._2p, "2-е лицо");
        grammemeTypeToString.put(GrammemeType._3p, "3-е лицо");

        grammemeTypeToString.put(GrammemeType.m, "мужской род");
        grammemeTypeToString.put(GrammemeType.f, "женский род");
        grammemeTypeToString.put(GrammemeType.n, "средний род");

        grammemeTypeToString.put(GrammemeType.ipf, "несовершенный");
        grammemeTypeToString.put(GrammemeType.pf, "совершенный");

        grammemeTypeToString.put(GrammemeType.act, "действительный залог");
        grammemeTypeToString.put(GrammemeType.pass, "страдательный залог");

        grammemeTypeToString.put(GrammemeType.anim, "одушевленное");
        grammemeTypeToString.put(GrammemeType.inan, "неодушевленное");

        grammemeTypeToString.put(GrammemeType.tran, "переходный глагол");
        grammemeTypeToString.put(GrammemeType.intr, "непереходный глагол");

        grammemeTypeToString.put(GrammemeType.parenth, "вводное слово");
        grammemeTypeToString.put(GrammemeType.geo, "географическое название");
        grammemeTypeToString.put(GrammemeType.awkw, "образование формы затруднено");
        grammemeTypeToString.put(GrammemeType.persn, "имя собственное");
        grammemeTypeToString.put(GrammemeType.dist, "искаженная форма");
        grammemeTypeToString.put(GrammemeType.mf, "общая форма мужского и женского рода");
        grammemeTypeToString.put(GrammemeType.obsc, "обсценная лексика");
        grammemeTypeToString.put(GrammemeType.patrn, "отчество");
        grammemeTypeToString.put(GrammemeType.praed, "предикатив");
        grammemeTypeToString.put(GrammemeType.inform, "разговорная форма");
        grammemeTypeToString.put(GrammemeType.rare, "редко встречающееся слово");
        grammemeTypeToString.put(GrammemeType.abbr, "сокращение");
        grammemeTypeToString.put(GrammemeType.obsol, "устаревшая форма");
        grammemeTypeToString.put(GrammemeType.famn, "фамилия");

    }

}