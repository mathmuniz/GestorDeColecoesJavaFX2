/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.Skin;
import model.dao.SkinDaoJdbc;
import model.dao.DaoFactory;
import start.App;

/**
 *
 * @author mmuni
 */
public class CadastroController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtColecao;
    @FXML
    private TextField txtPreco;
    @FXML
    private TextField txtStatus;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnGravar;

    private static Skin skinSelecionada;
    @FXML
    private ImageView imgFoto;
    private String caminhoFoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (skinSelecionada != null) {
            txtNome.setText(skinSelecionada.getNome());
            txtColecao.setText(skinSelecionada.getColecao());
            txtPreco.setText(skinSelecionada.getPreco());
            txtStatus.setText(skinSelecionada.getStatus());
            String path = null;
            try {
                path = new File(".").getCanonicalPath();
                String caminho = path + skinSelecionada.getFoto();
                imgFoto.setImage(new Image("file:///" + caminho));
                System.out.println("=============> " + path);

            } catch (Exception e) {
                System.out.println("=============> " + e.getMessage());
            }

        }
    }

    @FXML
    private void btnCancelarOnAction(ActionEvent event) throws IOException {
        skinSelecionada = null;
        App.setRoot("Principal");

    }

    @FXML
    private void btnGravarOnAction(ActionEvent event) throws IOException, Exception {
        Skin skin = new Skin();
        skin.setNome(txtNome.getText());
        skin.setColecao(txtColecao.getText());
        skin.setPreco(txtPreco.getText());
        skin.setStatus(txtStatus.getText());
        skin.setFoto(caminhoFoto);
        System.out.println("---------------> " + caminhoFoto);

        SkinDaoJdbc dao = DaoFactory.novaSkinDao();
        if (skinSelecionada == null) {
            dao.incluir(skin);
        } else {
            skin.setId(skinSelecionada.getId());
            dao.editar(skin);
            skinSelecionada = null;
        }
        App.setRoot("Principal");
    }

    public static void setSkinSelecionada(Skin skinSelecionada) {
        CadastroController.skinSelecionada = skinSelecionada;
    }

    public void selecionaFoto() {
        FileChooser f = new FileChooser();
        f.getExtensionFilters().add(new ExtensionFilter("Imagens", "*.jpg", "*.jpeg", "*.png"));
        File file = f.showOpenDialog(new Stage());
        if (file != null) {
            imgFoto.setImage(new Image("file:///" + file.getAbsolutePath()));  //talvez modificar
            caminhoFoto = "/imagens/" + file.getName();
            
        }
    }

    @FXML
    private void imgFotoOnMouseClicked(MouseEvent event) {
        selecionaFoto();

    }

}
