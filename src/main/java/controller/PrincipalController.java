/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Skin;
import model.dao.SkinDaoJdbc;
import model.dao.DaoFactory;
import start.App;

/**
 *
 * @author mmuni
 */
public class PrincipalController implements Initializable {

    @FXML
    private Button btnIncluir;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnExcluir;
    @FXML
    private TextField txtFiltro;
    @FXML
    private Button btnFiltrar;
    @FXML
    private Button btnLimpar;
    @FXML
    private TableView<Skin> tblSkin;
    @FXML
    private TableColumn<Skin, String> tblColNome;
    @FXML
    private TableColumn<Skin, String> tblColColecao;
    @FXML
    private TableColumn<Skin, String> tblColPreco;
    @FXML
    private TableColumn<Skin, String> tblColStatus;
   

    private List<Skin> listaSkins;
    private ObservableList<Skin> observableListSkins;

    @FXML
    private TableColumn<Skin, String> tblColFoto;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarSkins("");
    }

    @FXML
    private void btnIncluirOnAction(ActionEvent event) throws IOException {
        App.setRoot("Cadastro");
    }

    @FXML
    private void btnEditarOnAction(ActionEvent event) throws IOException {
        Skin skinSelecionada = tblSkin.selectionModelProperty().getValue().getSelectedItem();

        if (skinSelecionada != null) {
            CadastroController.setSkinSelecionada(skinSelecionada);
            App.setRoot("Cadastro");
        }
    }

    @FXML
    private void btnExcluirOnAction(ActionEvent event) throws Exception {
         Skin skinSelecionada = tblSkin.selectionModelProperty().getValue().getSelectedItem();
         SkinDaoJdbc dao = DaoFactory.novaSkinDao();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setContentText("Confirma exclusão de " + skinSelecionada.getNome() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                dao.excluir(skinSelecionada);
            } catch (Exception e) {
                Alert alertErro = new Alert(Alert.AlertType.INFORMATION);
                alertErro.setTitle("Aviso");
                alertErro.setContentText("Ocorreu um erro: " + e.getMessage());
                alertErro.showAndWait();
            }
        }
        carregarSkins("");
    }

    @FXML
    private void btnFiltrarOnAction(ActionEvent event) {
        carregarSkins(txtFiltro.getText());
    }

    @FXML
    private void btnLimparOnAction(ActionEvent event) {
        carregarSkins("");
    }

    private void carregarSkins(String param) {

        tblColNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tblColColecao.setCellValueFactory(new PropertyValueFactory<>("Colecao"));
        tblColPreco.setCellValueFactory(new PropertyValueFactory<>("Preco"));
        tblColStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        tblColFoto.setCellValueFactory(new PropertyValueFactory<>("Foto"));
        try {
            SkinDaoJdbc dao = DaoFactory.novaSkinDao();
            listaSkins = dao.listar(param);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        observableListSkins = FXCollections.observableArrayList(listaSkins);
        tblSkin.setItems(observableListSkins);
    }

}
