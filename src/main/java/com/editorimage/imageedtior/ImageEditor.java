package com.editorimage.imageedtior;

import java.io.File;
import java.io.IOException;  
import javax.imageio.ImageIO;  
import java.util.Scanner;
import java.awt.image.BufferedImage;  
import java.awt.Color;
import java.lang.Math;

public class ImageEditor {
    public static BufferedImage grayscale(BufferedImage image){
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                Color pixel = new Color (image.getRGB(j,i));
                int avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;
                Color newPixel = new Color(avg, avg, avg);
                newImage.setRGB(j, i, newPixel.getRGB());
            }
        }
        return newImage;
    }
    

    public static BufferedImage brightness(BufferedImage image, int value){

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                Color pixel = new Color (image.getRGB(j,i));
                int red = Math.min(255, (int)(pixel.getRed()*(1 + value/100d)));
                int green = Math.min(255, (int)(pixel.getGreen()*(1 + value/100d)));
                int blue = Math.min(255, (int)(pixel.getBlue()*(1 + value/100d)));
                Color newPixel = new Color(red, green, blue);
                newImage.setRGB(j, i, newPixel.getRGB());
            }
        }
        
        return newImage;
    }

    public static BufferedImage rotateClockwise(BufferedImage image){
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                newImage.setRGB(i, j, image.getRGB(j, height-i-1));
            }
        }
        return newImage;
    }

    public static BufferedImage rotateCounterClockwise(BufferedImage image){
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                newImage.setRGB(i, j, image.getRGB(width-j-1, i));
            }
        }
        return newImage;
    }

    public static BufferedImage flipHorizontally(BufferedImage image){
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                newImage.setRGB(j, i, image.getRGB(width-j-1, i));
            }
        }
        return newImage;
    }

    public static BufferedImage flipVertically(BufferedImage image){
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                newImage.setRGB(j, i, image.getRGB(j, height-i-1));
            }
        }
        return newImage;
    }

    // function to blur the image
    public static BufferedImage blur(BufferedImage image,int value){
        int width = image.getWidth();
        int height = image.getHeight();

        // creating an array of averages of each pool
        int avg_array[][] = new int[height+1][width+1];

        // averaging values for the size (height-height%value)*(width-width%value) 
        for(int row=0; row<height-(height%value); row+=value){
            for(int column=0; column<width-(width%value); column+=value){
                float[] color_avg = new float[3];

                // implementing average pooling for each pool
                for(int row_pool=row; row_pool<row+value; row_pool++){
                    for(int col_pool=column; col_pool<column+value; col_pool++){
                        Color pixel = new Color (image.getRGB(col_pool,row_pool));
                        color_avg[0] += pixel.getRed()/(float)(value*value);
                        color_avg[1] += pixel.getGreen()/(float)(value*value);
                        color_avg[2] += pixel.getBlue()/(float)(value*value);
                    }
                }
                Color newPixel = new Color((int)color_avg[0], (int)color_avg[1], (int)color_avg[2]);
                avg_array[(row/value)][(column/value)] = newPixel.getRGB();
            }
        }

        // averaging values for the remaining bottom part of the image
        if(height%value != 0){
            for(int column=0; column<width-(width%value); column+=value){

                // implementing average pooling for each pool
                float color_avg [] = new float[3];
                for(int row_pool=height-(value); row_pool<height; row_pool++){
                    for(int col_pool=column; col_pool<column+value; col_pool++){

                        Color pixel = new Color (image.getRGB(col_pool,row_pool));
                        color_avg[0] += pixel.getRed()/(float)(value*value);
                        color_avg[1] += pixel.getGreen()/(float)(value*value);
                        color_avg[2] += pixel.getBlue()/(float)(value*value);
                    }
                }
                Color newPixel = new Color((int)color_avg[0], (int)color_avg[1], (int)color_avg[2]);
                avg_array[(height/value)][(column/value)] = newPixel.getRGB();
            }
        }

        // averaging values for the remaining right part of the image
        if(width%value!=0){
            for(int row=0; row<height-(height%value); row+=value){

                // implementing average pooling for each pool
                float color_avg [] = new float[3];
                for(int row_pool=row; row_pool<row+value; row_pool++){
                    for(int col_pool=width-(value); col_pool<width; col_pool++){

                        Color pixel = new Color (image.getRGB(col_pool,row_pool));
                        color_avg[0] += pixel.getRed()/(float)(value*value);
                        color_avg[1] += pixel.getGreen()/(float)(value*value);
                        color_avg[2] += pixel.getBlue()/(float)(value*value);
                    }
                }
                Color newPixel = new Color((int)color_avg[0], (int)color_avg[1], (int)color_avg[2]);
                avg_array[(row/value)][(width/value)] = newPixel.getRGB();
            }
        }

        // averaging values for the remaining bottom right part of the image
        if(height%value!=0 && width%value!=0){

            // implementing average pooling for each pool
            float color_avg [] = new float[3];
            for(int row_pool = height-value; row_pool<height; row_pool++){
                for(int col_pool = width-value; col_pool<width; col_pool++){
                    Color pixel = new Color (image.getRGB(col_pool,row_pool));
                    color_avg[0] += pixel.getRed()/(float)((value)*(value));
                    color_avg[1] += pixel.getGreen()/(float)((value)*(value));
                    color_avg[2] += pixel.getBlue()/(float)((value)*(value));
                }
            }
            Color newPixel = new Color((int)color_avg[0], (int)color_avg[1], (int)color_avg[2]);
            avg_array[(height/value)][(width/value)] = newPixel.getRGB();
        }

        // creating a new image with the averaged values to blur the image
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for(int row=0; row<height; row++){
            for(int column=0; column<width; column++){
                newImage.setRGB(column, row, avg_array[row/value][column/value]);
            }
        }

        return newImage;
    }
}



