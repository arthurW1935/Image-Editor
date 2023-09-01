package com.editorimage.imageedtior;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Controller {
    @FXML
    ImageView myImageView;
    private Stage editorStage;

    public void setStage(Stage editorStage) {
        this.editorStage = editorStage;
    }

    @FXML
    public void open(ActionEvent e){
        System.out.println("Open");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(editorStage);

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            myImageView.setImage(image);
        }
    }

    @FXML
    public void save(ActionEvent e){
        System.out.println("Save");
    }
    @FXML
    public void grayScale(ActionEvent e){
        System.out.println("GrayScale");
    }

    @FXML
    public void brightness(ActionEvent e){
        System.out.println("Brightness");
    }

    @FXML
    public void flipHorizontal(ActionEvent e){
        System.out.println("Horizontal Flip");
    }

    @FXML
    public void flipVertical(ActionEvent e){
        System.out.println("Vertical Flip");
    }

    @FXML
    public void pixel_blur(ActionEvent e){
        System.out.println("Pixel Blurr");
    }
}