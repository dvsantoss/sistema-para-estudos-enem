package br.ufrn.tads.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.tads.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;
import br.ufrn.tads.model.*;
import br.ufrn.tads.repository.imp.QuestionsDao;
import br.ufrn.tads.servicy.imp.*;

public class ResponderController {

    private List<Question> questoes = new ArrayList<>();
    private QuestoesServicy qs = new QuestoesServicy();
    private int indexAtual = 0;

    @FXML
    private CheckBox alternativaA;
    @FXML
    private CheckBox alternativaB;
    @FXML
    private CheckBox alternativaC;
    @FXML
    private CheckBox alternativaD;
    @FXML
    private CheckBox alternativaE;
    @FXML
    private Button btn_menu;
    @FXML
    private Button btn_proxima;
    @FXML
    private Button btn_anterior;
    @FXML
    private Text contexto;
    @FXML
    private Text conteudo;
    @FXML
    private Text index_question;

    @FXML 
    public void initialize() {
        try {
            questoes = qs.catchQuestoes();
            System.out.println(questoes);
            if (questoes == null || questoes.isEmpty()) {
                System.out.println("Nenhuma questão encontrada!");
                contexto.setText("Nenhuma questão disponível.");
                return;
            }
            
            mostrarQuestao(0);
        } catch (Exception e) {
            System.err.println("Erro ao inicializar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void mostrarQuestao(int idx) {
        if (questoes == null || idx < 0 || idx >= questoes.size()) {
            return;
        }

        Question q = questoes.get(idx);
        
        contexto.setText(q.getTitle());
        conteudo.setText(q.getContext());
        List<String> altAtual = q.getAlternativesDoBd();
        
        if (altAtual != null && altAtual.size() >= 5) {
            alternativaA.setText(altAtual.get(0));
            alternativaB.setText(altAtual.get(1));
            alternativaC.setText(altAtual.get(2));
            alternativaD.setText(altAtual.get(3));
            alternativaE.setText(altAtual.get(4));
        }
        
        atualizarIndice();
        atualizarBotoesNavegacao();
    }

    private void atualizarIndice() {
        index_question.setText((indexAtual + 1) + "/" + (questoes != null ? questoes.size() : 0));
    }

    private void atualizarBotoesNavegacao() {
        btn_anterior.setDisable(indexAtual == 0);
        btn_proxima.setDisable(indexAtual == (questoes.size() - 1));
    }

    @FXML
    void proximaQuestao(ActionEvent event) {
        if (indexAtual < questoes.size() - 1) {
            indexAtual++;
            mostrarQuestao(indexAtual);
        }
    }

    @FXML
    void questaoAnterior(ActionEvent event) {
        if (indexAtual > 0) {
            indexAtual--;
            mostrarQuestao(indexAtual);
        }
    }

    @FXML
    void menu_screen(ActionEvent event) throws IOException {
        App.setRoot("menuScreen");
    }
}