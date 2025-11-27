package br.ufrn.tads.repository.imp;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufrn.tads.model.Alternative;
import br.ufrn.tads.model.Question;
import br.ufrn.tads.model.Root;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class Importer {
    


    public static void main(String[] args) {
        DbConnection dbConnection = new DbConnection();
        Connection  conn = null;
        try {
            //Ler 
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("varAmbiente/prova_2022_com_topicos.json");

            Root root = mapper.readValue(file, Root.class);

            conn = dbConnection.getConnection();
            conn.setAutoCommit(false); 

            for (Question q : root.questions) {

                // inserir perguntas
                String sqlQ = "INSERT INTO questions (index_number, title, discipline, language, year, context, correct_alternative,files) VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";

                PreparedStatement psQ = conn.prepareStatement(sqlQ);
                psQ.setInt(1, q.getIndex());
                psQ.setString(2, q.getTitle());
                psQ.setString(3, q.getDiscipline());
                psQ.setString(4, q.getLanguage());
                psQ.setInt(5, q.getYear());
                psQ.setString(6, q.getContext());
                psQ.setString(7, q.getCorrectAlternative());
                psQ.setString(8, String.join(",", q.getFiles()));


                ResultSet rs = psQ.executeQuery();
                rs.next();
                int questionId = rs.getInt("id");

                // Inserir alternativas
                for (Alternative alt : q.getAlternatives()) {
                    String sqlA = "INSERT INTO alternatives (question_id, letter, text, is_correct) VALUES (?, ?, ?, ?)";

                    PreparedStatement psA = conn.prepareStatement(sqlA);
                    psA.setInt(1, questionId);
                    psA.setString(2, alt.letter);
                    psA.setString(3, alt.text);
                    psA.setBoolean(4, alt.isCorrect);
                    psA.executeUpdate();
                }

                // 5. Inserir tópicos (se existirem)
                if (q.getTopicos() != null) {
                    for (String topic : q.getTopicos()) {
                        String sqlT = "INSERT INTO topics (question_id, topic_text) VALUES (?, ?)";

                        PreparedStatement psT = conn.prepareStatement(sqlT);
                        psT.setInt(1, questionId);
                        psT.setString(2, topic);
                        psT.executeUpdate();
                    }
                }
            }

            // salvar 
            conn.commit();
            conn.close();

            System.out.println("Importação conc");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

