package br.ufrn.tads.servicy.imp;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.tads.model.Question;
import br.ufrn.tads.repository.imp.QuestionsDao;

public class QuestoesServicy {
    private List<Question> questoes= new ArrayList<>();
    private QuestionsDao questionsDao = new QuestionsDao();
    private Question question = null;

    public QuestoesServicy(){
    }

    public List<Question> catchQuestoes(){
        try {
            for(int i =1; i<10;i++){
                question = questionsDao.findById((long)i);
                System.out.println(question);
                questoes.add(question);  
            }   
            return questoes;

        } catch (NullPointerException e) {
            System.out.println(e);
            // TODO: handle exception
        }
        return null;
    }
    
}
