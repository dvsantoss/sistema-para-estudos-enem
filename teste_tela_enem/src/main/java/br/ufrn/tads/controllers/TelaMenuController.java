package br.ufrn.tads.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

import br.ufrn.tads.App;
import br.ufrn.tads.model.User;
import br.ufrn.tads.model.UserDailyStats;
import br.ufrn.tads.servicy.imp.*;
public class TelaMenuController {

    QuestoesServicy qServicy = new QuestoesServicy();
    @FXML
    private Text nomeUser;

    @FXML
    public void initialize() {
        nomeUser.setText(Login.getUserAtual().getName());
        carregarGrafico(Login.getUserAtual());
    }

    @FXML
    private Button btn_ajuda;

    @FXML
    private Button btn_calendario;

    @FXML
    private Button btn_menu;

    @FXML
    private LineChart<String, Number> chartId;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;


    @FXML
    private Button btn_provas;

    @FXML
    private Button btn_questoes;

    @FXML
    void ajuda_screen(ActionEvent event) {

    }

    @FXML
    void calendario__screen(ActionEvent event) {

    }

    @FXML
    void menu_screen(ActionEvent event) {

    }

    @FXML
    void prova_screen(ActionEvent event) throws IOException{
        App.setRoot("provaScreen");
    }

    @FXML
    void questoes_screen(ActionEvent event) throws IOException{
        App.setRoot("questoesScreen");
    }


    public void carregarGrafico(User t) {

    List<UserDailyStats> lista = qServicy.getInformacoesDeAcertoseErrosDiarios(t);

    XYChart.Series<String, Number> serieCertas = new XYChart.Series<>();
    serieCertas.setName("Acertos");

    XYChart.Series<String, Number> serieErradas = new XYChart.Series<>();
    serieErradas.setName("Erros");

    for (UserDailyStats stats : lista) {
        String dataFormatada = stats.getData().toString(); // 2025-11-27
        serieCertas.getData().add(new XYChart.Data<>(dataFormatada, stats.getQuest_certas()));
        serieErradas.getData().add(new XYChart.Data<>(dataFormatada, stats.getQuest_erradas()));
    }

    chartId.getData().clear();
    chartId.getData().addAll(serieCertas, serieErradas);
}

}
