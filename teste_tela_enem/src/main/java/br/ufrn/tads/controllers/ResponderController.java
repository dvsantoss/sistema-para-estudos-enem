package br.ufrn.tads.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.tads.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import br.ufrn.tads.model.*;
import br.ufrn.tads.servicy.imp.*;

public class ResponderController {

    private List<Question> questoes = new ArrayList<>();
    private QuestoesServicy qs = new QuestoesServicy();
    private Question q;
    private int indexAtual = 0;
    private static boolean provaGeral = true;
    private static String temaAtual;

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
    private ImageView imageDisplay;
    @FXML
    private VBox areaQuestao;
    
    @FXML 
    public void initialize() {

        alternativaA.setOnAction(e -> desmarcarExceto(alternativaA));
        alternativaB.setOnAction(e -> desmarcarExceto(alternativaB));
        alternativaC.setOnAction(e -> desmarcarExceto(alternativaC));
        alternativaD.setOnAction(e -> desmarcarExceto(alternativaD));
        alternativaE.setOnAction(e -> desmarcarExceto(alternativaE));
        if(provaGeral)
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

        else{
            try {

                questoes = qs.catchQuestoesPortema(getTemaAtual());
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
    }


        private void desmarcarExceto(CheckBox selecionada) {
        if (selecionada != alternativaA) alternativaA.setSelected(false);
        if (selecionada != alternativaB) alternativaB.setSelected(false);
        if (selecionada != alternativaC) alternativaC.setSelected(false);
        if (selecionada != alternativaD) alternativaD.setSelected(false);
        if (selecionada != alternativaE) alternativaE.setSelected(false);
    }

    private void mostrarQuestao(int idx) {
        alternativaA.setSelected(false);
        alternativaB.setSelected(false);
        alternativaC.setSelected(false);
        alternativaD.setSelected(false);
        alternativaE.setSelected(false);
        if (questoes == null || idx < 0 || idx >= questoes.size()) {
            return;
        }

        q = questoes.get(idx);
        System.out.println(q.getFiles());
        System.out.println(q.getCorrectAlternative());
        List<String> altAtual = q.getAlternativesDoBd();


        areaQuestao.getChildren().clear();

        contexto.setText(q.getTitle());
        Text texto = new Text(q.getContext());
        texto.setWrappingWidth(480);
        areaQuestao.getChildren().add(texto);

        if (q.getFiles() != null && !q.getFiles().isEmpty()) {
            for (String filePath : q.getFiles()) {
                ImageView img = new ImageView(new Image(filePath));
                img.setFitWidth(400);
                img.setPreserveRatio(true);
                areaQuestao.getChildren().add(img);
            }
        }
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
        System.out.println("acertou? "+acertouOuNao());
        q.setAcertouOuNao(acertouOuNao());
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
        qs.contagemDequestoes(questoes);
    }

    public static boolean isProvaGeral() {
        return provaGeral;
    }

    public static void setProvaGeral(boolean provaGeral) {
        ResponderController.provaGeral = provaGeral;
    }

    public static String getTemaAtual() {
        return temaAtual;
    }

    public static void setTemaAtual(String temaAtual) {
        ResponderController.temaAtual = temaAtual;
    }

    public boolean acertouOuNao(){
        if(alternativaA.isSelected() && "A".equals(q.getCorrectAlternative())){
            return true;
        }
        else if(alternativaB.isSelected() && "B".equals(q.getCorrectAlternative())){
            return true;
        }
        else if(alternativaC.isSelected() && "C".equals(q.getCorrectAlternative())){
            return true;
        }
        else if(alternativaD.isSelected() && "D".equals(q.getCorrectAlternative())){
            return true;
        }
        else if(alternativaE.isSelected() && "E".equals(q.getCorrectAlternative())){
            return true;
        }

        return false;
    }
}