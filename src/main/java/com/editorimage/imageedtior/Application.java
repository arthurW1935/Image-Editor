package com.editorimage.imageedtior;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Application extends javafx.application.Application {

    private ImageView editImage;

    public Image setImage(File thisImage){
        Image image = new Image(thisImage.toURI().toString());
        double height = image.getHeight();
        double width = image.getWidth();
        if(height > 700 || width > 1000){
            double ratio = height/width;
            if(height > 700){
                height = 700;
                width = height/ratio;
            }
            if(width > 1000){
                width = 1000;
                height = width*ratio;
            }
            image = new Image(thisImage.toURI().toString(), width, height, false, false);
        }
        return image;
    }

    public void showImage(){
        File file = new File("src\\main\\resources\\com\\editorimage\\imageedtior\\output.jpg");
        Image image = setImage(file);
        editImage.setImage(image);
    }
    public void openFile(Stage stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
//            save the selected file as output.jpg in the resources folder
            try{
                BufferedImage bufferedImage = ImageIO.read(selectedFile);
                File output = new File("src\\main\\resources\\com\\editorimage\\imageedtior\\output.jpg");
                ImageIO.write(bufferedImage, "jpg", output);
            }
            catch (IOException e){
                System.out.println("Image is not opening");
            }

            showImage();
        }
    }

    public void saveFile(Stage stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showSaveDialog(stage);

        if (selectedFile != null) {
//            save the selected file as output.jpg in the resources folder
            try{
                File outputImg = new File("src\\main\\resources\\com\\editorimage\\imageedtior\\output.jpg");
                BufferedImage bufferedImage = ImageIO.read(outputImg);
                ImageIO.write(bufferedImage, "jpg", selectedFile);
            }
            catch (IOException e){
                System.out.println("Image is not opening");
            }
        }
    }

    public void grayscale(){
        try {
            ImageEditor imageEditor = new ImageEditor();
            File outputImg = new File("src\\main\\resources\\com\\editorimage\\imageedtior\\output.jpg");
            BufferedImage outputBufferedImage = ImageIO.read(outputImg);
            BufferedImage processedImage = imageEditor.grayscale(outputBufferedImage);
            ImageIO.write(processedImage, "jpg", outputImg);
            showImage();
        }
        catch (IOException e){
            System.out.println("Image is not opening");
        }
    }

    public void horizontalFLip(){
        try {
            ImageEditor imageEditor = new ImageEditor();
            File outputImg = new File("src\\main\\resources\\com\\editorimage\\imageedtior\\output.jpg");
            BufferedImage outputBufferedImage = ImageIO.read(outputImg);
            BufferedImage processedImage = imageEditor.flipHorizontally(outputBufferedImage);
            ImageIO.write(processedImage, "jpg", outputImg);
            showImage();
        }
        catch (IOException e){
            System.out.println("Image is not opening");
        }
    }

    public void verticalFlip(){
        try {
            ImageEditor imageEditor = new ImageEditor();
            File outputImg = new File("src\\main\\resources\\com\\editorimage\\imageedtior\\output.jpg");
            BufferedImage outputBufferedImage = ImageIO.read(outputImg);
            BufferedImage processedImage = imageEditor.flipVertically(outputBufferedImage);
            ImageIO.write(processedImage, "jpg", outputImg);
            showImage();
        }
        catch (IOException e){
            System.out.println("Image is not opening");
        }
    }

    public void rotateClockwise(){
        try {
            ImageEditor imageEditor = new ImageEditor();
            File outputImg = new File("src\\main\\resources\\com\\editorimage\\imageedtior\\output.jpg");
            BufferedImage outputBufferedImage = ImageIO.read(outputImg);
            BufferedImage processedImage = imageEditor.rotateClockwise(outputBufferedImage);
            ImageIO.write(processedImage, "jpg", outputImg);
            showImage();
        }
        catch (IOException e){
            System.out.println("Image is not opening");
        }
    }

    public void rotateAnticlockwise(){
        try {
            ImageEditor imageEditor = new ImageEditor();
            File outputImg = new File("src\\main\\resources\\com\\editorimage\\imageedtior\\output.jpg");
            BufferedImage outputBufferedImage = ImageIO.read(outputImg);
            BufferedImage processedImage = imageEditor.rotateCounterClockwise(outputBufferedImage);
            ImageIO.write(processedImage, "jpg", outputImg);
            showImage();
        }
        catch (IOException e){
            System.out.println("Image is not opening");
        }
    }

    public void brightness(double value){
        try {
            ImageEditor imageEditor = new ImageEditor();
            File outputImg = new File("src\\main\\resources\\com\\editorimage\\imageedtior\\output.jpg");
            BufferedImage outputBufferedImage = ImageIO.read(outputImg);
            BufferedImage processedImage = imageEditor.brightness(outputBufferedImage, (int) value);
            ImageIO.write(processedImage, "jpg", outputImg);
            showImage();
        }
        catch (IOException e){
            System.out.println("Image is not opening");
        }
    }

    public void pixelBlur(double value){
        try {
            ImageEditor imageEditor = new ImageEditor();
            File outputImg = new File("src\\main\\resources\\com\\editorimage\\imageedtior\\output.jpg");
            BufferedImage outputBufferedImage = ImageIO.read(outputImg);
            BufferedImage processedImage = imageEditor.blurr(outputBufferedImage, (int) value);
            ImageIO.write(processedImage, "jpg", outputImg);
            showImage();
        }
        catch (IOException e){
            System.out.println("Image is not opening");
        }
    }

    EventHandler<WindowEvent> closeWindowEvent = event -> {
        File file = new File("src\\main\\resources\\com\\editorimage\\imageedtior\\output.jpg");
        file.delete();
    };


    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();

        editImage = new ImageView();
        editImage.setId("editImage");

        MenuBar menubar = new MenuBar();
        menubar.setId("menubar");
        Menu file = new Menu("File");
        file.setId("file");
        MenuItem open = new MenuItem("Open");
        open.setOnAction(event -> openFile(stage));
        MenuItem save = new MenuItem("Save");
        save.setOnAction(event -> saveFile(stage));
        file.getItems().add(open);
        file.getItems().add(save);
        menubar.getMenus().add(file);
        root.setTop(menubar);

        VBox toolbar = new VBox();
        toolbar.setId("toolbar");

        Button grayScale = new Button("GrayScale");
        grayScale.setMaxWidth(Double.MAX_VALUE);
        grayScale.setOnAction(event -> grayscale());

        Button flipHorizontal = new Button("Horizontal Flip");
        flipHorizontal.setMaxWidth(Double.MAX_VALUE);
        flipHorizontal.setOnAction(event -> horizontalFLip());

        Button flipVertical = new Button("Vertical Flip");
        flipVertical.setMaxWidth(Double.MAX_VALUE);
        flipVertical.setOnAction(event -> verticalFlip());

        Button rotateClock = new Button("Rotate Clockwise");
        rotateClock.setMaxWidth(Double.MAX_VALUE);
        rotateClock.setOnAction(event -> rotateClockwise());

        Button rotateAnticlock = new Button("Rotate Anticlockwise");
        rotateAnticlock.setMaxWidth(Double.MAX_VALUE);
        rotateAnticlock.setOnAction(event -> rotateAnticlockwise());

        Button brightness = new Button("Brightness");
        brightness.setMaxWidth(Double.MAX_VALUE);
        brightness.setId("brightness");

        Slider sliderBrightness = new Slider(-100,100,2);
        Button applyBrightness = new Button("Apply Brightness");
        applyBrightness.setMaxWidth(Double.MAX_VALUE);
        applyBrightness.setOnAction(event -> brightness(sliderBrightness.getValue()));

        Button pixel_blur = new Button("Pixel Blurr");
        pixel_blur.setId("pixel_blur");
        pixel_blur.setMaxWidth(Double.MAX_VALUE);

        Slider sliderPixelBlur = new Slider(1,100,1);
        Button applyPixelBlur = new Button("Apply Pixel Blurr");
        applyPixelBlur.setMaxWidth(Double.MAX_VALUE);
        applyPixelBlur.setOnAction(event -> pixelBlur(sliderPixelBlur.getValue()));

        toolbar.getChildren().addAll(grayScale, flipHorizontal, flipVertical, rotateClock, rotateAnticlock, pixel_blur, sliderPixelBlur, applyPixelBlur, brightness, sliderBrightness, applyBrightness);
        root.setLeft(toolbar);

        root.setCenter(editImage);
        Scene scene = new Scene(root);

        URL urlCSS = new File("src\\main\\resources\\com\\editorimage\\imageedtior\\style.css").toURI().toURL();
        scene.getStylesheets().add(String.valueOf(urlCSS));

        stage.getIcons().add(new Image("file:src\\main\\resources\\com\\editorimage\\imageedtior\\icon.png"));
        stage.setTitle("Photo Editor");
        stage.setScene(scene);

        stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, closeWindowEvent);


        stage.setMaximized(true);
        stage.setMinHeight(800);
        stage.setMinWidth(1400);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
