package br.ufrn.tads.repository.imp;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ufrn.tads.model.Question;
import br.ufrn.tads.model.User;
import br.ufrn.tads.repository.*;

public class QuestionsDao implements InterfaceDao<Question>{

    @Override
    public Question findByName(String name) {
        return null;
        // TODO Auto-generated method stub
        
    }

    @Override
    public Question findById(Long id) {
        Question question = null;
        String sql = "SELECT q.id, q.index_number, q.discipline,q.Language,q.correct_alternative,q.year,q.title,q.files,q.context,array_agg(DISTINCT (a.letter || ' - ' || a.text)) AS alternativas,array_agg(DISTINCT t.topic_text) AS topicos FROM questions q LEFT JOIN alternatives a ON a.question_id = q.id LEFT JOIN topics t ON t.question_id = q.id WHERE q.index_number = ? GROUP BY q.id;";
        Connection conn = null;
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conn = DbConnection.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            //verificando Entrada no banco
            //System.out.println("conecção: "+preparedStatement);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                question = new Question();
                question.setTitle(resultSet.getString("title"));
                question.setIndex(resultSet.getInt("index_number"));
                question.setDiscipline(resultSet.getString("discipline"));
                question.setLanguage(resultSet.getString("language"));
                question.setYear(resultSet.getInt("year"));
                question.setContext(resultSet.getString("context"));
                question.setCorrectAlternative(resultSet.getString("correct_alternative"));

                // files tablea
                Array array = resultSet.getArray("files");
                if (array != null) {
                    String[] filesArray = (String[]) array.getArray();  // converte para string[]
                    List<String> filesList = Arrays.asList(filesArray); // converte para List
                    question.setFiles(filesList);
                }


                //files
                // Array sqlArray1 = resultSet.getArray("topicos");
                // String[] filesArray = (String[]) sqlArray1.getArray();
                // List<String> files = Arrays.asList(filesArray);
                // question.setFiles(files);

                //topicos
                Array sqlArray2 = resultSet.getArray("topicos");
                String[] topicsArray = (String[]) sqlArray2.getArray();
                List<String> topics = Arrays.asList(topicsArray);
                question.setTopicos(topics);
                

                //Alternativas
                Array sqlArray3 = resultSet.getArray("alternativas");
                String[] alternativasArray = (String[]) sqlArray3.getArray();
                List<String> alternativas = Arrays.asList(alternativasArray);
                question.setAlternativesDoBd(alternativas);
                
                System.out.println("output de valor das questoes:"+question.getYear());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(preparedStatement != null) preparedStatement.close();
                if(conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return question;
    }

    public Question findByTema(String tema,Long id) {
        Question question = null;
        String sql = "SELECT q.id, q.index_number, q.discipline,q.Language,q.correct_alternative,q.year,q.title,q.files,q.context,array_agg(DISTINCT (a.letter || ' - ' || a.text)) AS alternativas,array_agg(DISTINCT t.topic_text) AS topicos FROM questions q LEFT JOIN alternatives a ON a.question_id = q.id LEFT JOIN topics t ON t.question_id = q.id WHERE discipline = ? AND index_number = ? GROUP BY q.id;";
        Connection conn = null;
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        System.out.println("tema :" + tema + id+ " :id");
        try {
            conn = DbConnection.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, tema);
            preparedStatement.setLong(2, id);
            //verificando Entrada no banco
            //System.out.println("conecção: "+preparedStatement);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                question = new Question();
                question.setTitle(resultSet.getString("title"));
                question.setIndex(resultSet.getInt("index_number"));
                question.setDiscipline(resultSet.getString("discipline"));
                question.setLanguage(resultSet.getString("language"));
                question.setYear(resultSet.getInt("year"));
                question.setContext(resultSet.getString("context"));
                question.setCorrectAlternative(resultSet.getString("correct_alternative"));

                Array filesArray = resultSet.getArray("files");
                if (filesArray != null) {
                try {
                    String[] filesArrayData = (String[]) filesArray.getArray();
                    if (filesArrayData != null && filesArrayData.length > 0) {
                        question.setFiles(Arrays.asList(filesArrayData));
                    } else {
                        question.setFiles(new ArrayList<>());
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao processar array de files: " + e.getMessage());
                    question.setFiles(new ArrayList<>());
                }
            } else {
                question.setFiles(new ArrayList<>());
            }

            // topicos
            Array sqlArray2 = resultSet.getArray("topicos");
            if (sqlArray2 != null) {
                try {
                    String[] topicsArray = (String[]) sqlArray2.getArray();
                    if (topicsArray != null && topicsArray.length > 0) {
                        List<String> topics = Arrays.asList(topicsArray);
                        question.setTopicos(topics);
                    } else {
                        question.setTopicos(new ArrayList<>());
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao processar array de tópicos: " + e.getMessage());
                    question.setTopicos(new ArrayList<>());
                }
            } else {
                question.setTopicos(new ArrayList<>());
            }

            // alternativ
            Array sqlArray3 = resultSet.getArray("alternativas");
            if (sqlArray3 != null) {
                try {
                    String[] alternativasArray = (String[]) sqlArray3.getArray();
                    if (alternativasArray != null && alternativasArray.length > 0) {
                        List<String> alternativas = Arrays.asList(alternativasArray);
                        question.setAlternativesDoBd(alternativas);
                    } else {
                        question.setAlternativesDoBd(new ArrayList<>());
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao processar array de alternativas: " + e.getMessage());
                    question.setAlternativesDoBd(new ArrayList<>());
                }
            } else {
                question.setAlternativesDoBd(new ArrayList<>());
            }
                
                System.out.println("output de valor das questoes:"+question.getYear());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(preparedStatement != null) preparedStatement.close();
                if(conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return question;
    }

    @Override
    public List<Question> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public boolean save(Question t) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean update(Question t, String[] params) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
