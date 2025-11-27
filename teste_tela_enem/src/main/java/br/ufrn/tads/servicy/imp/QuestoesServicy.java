package br.ufrn.tads.servicy.imp;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.tads.model.Question;
import br.ufrn.tads.model.User;
import br.ufrn.tads.model.UserDailyStats;
import br.ufrn.tads.repository.imp.QuestionsDao;
import br.ufrn.tads.repository.imp.UserDao;

public class QuestoesServicy {
    private List<Question> questoes= new ArrayList<>();
    private User user;
    private UserDao userdao = new UserDao();
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

    public List<Question> catchQuestoesPortema(String temaAtual){
        try {
            if(temaAtual.equals("linguagens")){
                for(int i =1; i<46;i++){
                                question = questionsDao.findByTema(temaAtual,(long)i);
                                System.out.println(question);
                                if(question!= null){
                                    questoes.add(question);  
                                }
                                
                            }
            }
            else if(temaAtual.equals("ciencias-humanas")){
                for(int i =45; i<91;i++){
                                question = questionsDao.findByTema(temaAtual,(long)i);
                                System.out.println(question);
                                if(question!= null){
                                    questoes.add(question);  
                                }
                                
                            }
            }
            else if(temaAtual.equals("ciencias-natureza")){
                for(int i =90; i<136;i++){
                                question = questionsDao.findByTema(temaAtual,(long)i);
                                System.out.println(question);
                                if(question!= null){
                                    questoes.add(question);  
                                }
                                
                            }
            }
            else if(temaAtual.equals("matematica")){
                for(int i =135; i<181;i++){
                                question = questionsDao.findByTema(temaAtual,(long)i);
                                System.out.println(question);
                                if(question!= null){
                                    questoes.add(question);  
                                }
                                
                            }
            }               
            return questoes;

        } catch (NullPointerException e) {
            System.out.println(e);
            // TODO: handle exception
        }
        return null;
    }

    public void contagemDequestoes(List<Question> questoes){
        int i = 0;
        for(Question q:questoes){
            if(q.isAcertouOuNao()){
                i++;
            }
        }
        user = Login.getUserAtual();
        user.setQuest_certas(i);
        user.setQuest_erradas(questoes.size()-i);
        user.setQuest_feitas(questoes.size());
        userdao.update(user, null);
        userdao.salvarEstatisticaDiaria(user);
        System.out.println("Acertos = "+i);
        System.out.println("Questoes erradas = "+ (questoes.size()-i));
        System.out.println("Questoes feitas total = "+(questoes.size()));
    }
    

    public List<UserDailyStats> getInformacoesDeAcertoseErrosDiarios(User t){
        return userdao.getEstatisticaDiaria(t.getId());
    }
}
