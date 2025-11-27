package br.ufrn.tads.model;

import java.util.List;

public class Question {
    private String title;
    private int index;
    private String discipline;
    private String language;
    private int year;
    private String context;
    private List<String> files;
    private String correctAlternative;
    private List<Alternative> alternatives;
    private List<String> alternativesDoBd;
    private List<String> topicos;
    private String alternativesIntroduction;
    
    //acertos e erros
    private boolean acertouOuNao;

    public Question(){
        
    }
    //esses dados são incluidos somente para a limpeza do json
    public int similarity_score;
    public int matched_question_number;
    public boolean is_manual_addition;

    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public String getDiscipline() {
        return discipline;
    }
    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getContext() {
        return context;
    }
    public void setContext(String context) {
        this.context = context;
    }
    public List<String> getFiles() {
        return files;
    }
    public void setFiles(List<String> files) {
        this.files = files;
    }
    public String getCorrectAlternative() {
        return correctAlternative;
    }
    public void setCorrectAlternative(String correctAlternative) {
        this.correctAlternative = correctAlternative;
    }
    public List<Alternative> getAlternatives() {
        return alternatives;
    }
    public void setAlternatives(List<Alternative> alternatives) {
        this.alternatives = alternatives;
    }
    public List<String> getTopicos() {
        return topicos;
    }
    public void setTopicos(List<String> topicos) {
        this.topicos = topicos;
    }
    public String getAlternativesIntroduction() {
        return alternativesIntroduction;
    }
    public void setAlternativesIntroduction(String alternativesIntroduction) {
        this.alternativesIntroduction = alternativesIntroduction;
    }
    public int getSimilarity_score() {
        return similarity_score;
    }
    public void setSimilarity_score(int similarity_score) {
        this.similarity_score = similarity_score;
    }
    public int getMatched_question_number() {
        return matched_question_number;
    }
    public void setMatched_question_number(int matched_question_number) {
        this.matched_question_number = matched_question_number;
    }
    public boolean isIs_manual_addition() {
        return is_manual_addition;
    }
    public void setIs_manual_addition(boolean is_manual_addition) {
        this.is_manual_addition = is_manual_addition;
    }
    public List<String> getAlternativesDoBd() {
        return alternativesDoBd;
    }
    public void setAlternativesDoBd(List<String> alternativesDoBd) {
        this.alternativesDoBd = alternativesDoBd;
    }
    public boolean isAcertouOuNao() {
        return acertouOuNao;
    }
    public void setAcertouOuNao(boolean acertouOuNao) {
        this.acertouOuNao = acertouOuNao;
    }
    
}


