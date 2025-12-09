package br.ufrn.tads.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.text.Text;

import br.ufrn.tads.App;
// import br.ufrn.tads.model.Login;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProvaController {

    // menu lateral
    @FXML
    private Button btn_menu;

    @FXML
    private Button btn_provas;

    @FXML
    private Button btn_questoes;

    @FXML
    private Button btn_calendario;

    @FXML
    private Button btn_ajuda;

    @FXML
    private Text nomeUser;

    // método principal para abrir provas
    @FXML
    void abrirProva(ActionEvent event) {
        // identifica o ano pelo texto do botão clicado (ex: "2025")
        Button botaoClicado = (Button) event.getSource();
        String ano = botaoClicado.getText(); // para puxar a string que é o titulo do botão clicado. por isso o titulo
                                             // tem que ser o ano.

        // pergunta se é dia 1 ou dia 2
        String dia = perguntarUsuario("Qual o dia da prova?", "Dia 1", "Dia 2");
        if (dia == null)
            return; // Se o usuário cancelar, para aqui.

        String numeroDia = dia.replace("Dia ", ""); // transforma "Dia 1" em "1"

        // pergunta a cor do caderno
        String cor = perguntarUsuario("Qual a cor do caderno?",
                "Azul", "Amarelo", "Verde", "Laranja", "Roxo");
        if (cor == null)
            return;

        // busca o Link na lista de links usando switch
        String urlParaAbrir = obterUrlDoInep(ano, numeroDia, cor.toLowerCase());

        // se achou o link, pede confirmação e abre
        if (urlParaAbrir != null) {

            Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacao.setTitle("Redirecionamento Externo");
            confirmacao.setHeaderText("Você será levado para o site do INEP");
            confirmacao.setContentText("Deseja abrir a prova " + cor + " (" + ano + ") no seu navegador agora?");

            // botões
            ButtonType btnSim = new ButtonType("Sim, abrir prova");
            ButtonType btnNao = new ButtonType("Não, voltar", ButtonBar.ButtonData.CANCEL_CLOSE);

            confirmacao.getButtonTypes().setAll(btnSim, btnNao); // substitui os botões padrão para os botoes ficar com
                                                                 // o titulo acima

            Optional<ButtonType> resultado = confirmacao.showAndWait();

            if (resultado.isPresent() && resultado.get() == btnSim) {
                abrirNavegador(urlParaAbrir);
            }

        } else {
            mostrarErro("Desculpe, ainda não cadastramos o link para:\n" +
                    "Ano: " + ano + ", Dia: " + numeroDia + ", Cor: " + cor);
        }
    }

    // BASE DE DADOS dos links das provas do INEP
    private String obterUrlDoInep(String ano, String dia, String cor) {

        if (ano.equals("2025")) {
            // links do DIA 1 (2025)
            if (dia.equals("1")) {
                switch (cor) {
                    case "azul":
                        return "https://download.inep.gov.br/enem/provas_e_gabaritos/2025_PV_impresso_D1_CD1.pdf";
                    case "amarelo":
                        return "https://download.inep.gov.br/enem/provas_e_gabaritos/2025_PV_impresso_D1_CD2.pdf";
                    case "verde":
                        return "https://download.inep.gov.br/enem/provas_e_gabaritos/2025_PV_impresso_D1_CD4.pdf";
                    case "laranja":
                        return "https://download.inep.gov.br/enem/provas_e_gabaritos/2025_PV_impresso_D1_CD9.pdf";
                    case "roxo":
                        return "https://download.inep.gov.br/enem/provas_e_gabaritos/2025_PV_impresso_D1_CD10.pdf";
                }
            }
            // links do DIA 2 (2025)
            else if (dia.equals("2")) {
                switch (cor) {
                    case "amarelo":
                        return "https://download.inep.gov.br/enem/provas_e_gabaritos/2025_PV_impresso_D2_CD5.pdf";
                    case "azul":
                        return "https://download.inep.gov.br/enem/provas_e_gabaritos/2025_PV_impresso_D2_CD7.pdf";
                    case "verde":
                        return "https://download.inep.gov.br/enem/provas_e_gabaritos/2025_PV_impresso_D2_CD8.pdf";
                    case "laranja":
                        return "https://download.inep.gov.br/enem/provas_e_gabaritos/2025_PV_impresso_D2_CD11.pdf";
                    case "roxo":
                        return "https://download.inep.gov.br/enem/provas_e_gabaritos/2025_PV_impresso_D2_CD12.pdf";
                }
            }
        }
        return null; // retorna null se não encontrar combinação
    }

    // alguns metódos auxiliares para abrir navegador, perguntar usuário e mostrar
    // erro

    private void abrirNavegador(String url) {
        try {
            // verifica se o sistema suporta abrir navegador
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                mostrarErro("Seu sistema operacional não permite abrir o navegador automaticamente.");
            }
        } catch (Exception e) {
            mostrarErro("Erro ao tentar abrir o link: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String perguntarUsuario(String titulo, String... opcoes) { // método genérico para perguntar ao usuário (...
                                                                       // para receber várias opções) Varargs
        List<String> listaOpcoes = new ArrayList<>();
        for (String op : opcoes)
            listaOpcoes.add(op);

        ChoiceDialog<String> dialog = new ChoiceDialog<>(listaOpcoes.get(0), listaOpcoes); // caixa de diálogo com
                                                                                           // opções recebidas
        dialog.setTitle("Seleção de Prova");
        dialog.setHeaderText(titulo);// cabeçalho pode perguntar qual o dia ou qual a cor
        dialog.setContentText("Escolha uma opção:");

        Optional<String> result = dialog.showAndWait(); // espera a resposta do usuário
        return result.orElse(null);
    }

    private void mostrarErro(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ops!");
        alert.setHeaderText(null);
        alert.setContentText(msg); // mensagem de erro personalizada de acordo com quem chama (reutilizavel)
        alert.showAndWait();
    }

    @FXML
    public void initialize() {

    }

    // navegação entre telas

    @FXML
    void menu_screen(ActionEvent event) throws IOException {
        App.setRoot("menuScreen");
    }

    @FXML
    void questoes_screen(ActionEvent event) throws IOException {
        App.setRoot("questoesScreen");
    }

    @FXML
    void calendario__screen(ActionEvent event) throws IOException {
        App.setRoot("calendarioScreen");
    }

    @FXML
    void ajuda_screen(ActionEvent event) throws IOException{
        App.setRoot("ajuda_configScreen");
    }
}