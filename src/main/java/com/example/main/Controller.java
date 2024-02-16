package com.example.main;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Controller {

    ArrayList[] gameMatrix = {new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList()};

    ArrayList[] previousMatrix = {new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList()};

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button tryAgainButton;

    @FXML
    private Button backButton;

    @FXML
    private Button backToMainButton;

    @FXML
    private Button settingButton;

    @FXML
    private Text bestText;

    @FXML
    private AnchorPane gameOverPane;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private AnchorPane gamePane;

    @FXML
    private AnchorPane settingPane;

    @FXML
    private Text scoreText;

    @FXML
    private Button newGameButton;

    @FXML
    private Text slot1;

    @FXML
    private Text slot2;

    @FXML
    private Text slot3;

    @FXML
    private Text slot4;

    @FXML
    private Text slot5;

    @FXML
    private Text slot6;

    @FXML
    private Text slot7;

    @FXML
    private Text slot8;

    @FXML
    private Text slot9;

    @FXML
    private Text slot10;

    @FXML
    private Text slot11;

    @FXML
    private Text slot12;

    @FXML
    private Text slot13;

    @FXML
    private Text slot14;

    @FXML
    private Text slot15;

    @FXML
    private Text slot16;

    @FXML
    private Rectangle box1;

    @FXML
    private Rectangle box2;

    @FXML
    private Rectangle box3;

    @FXML
    private Rectangle box4;

    @FXML
    private Rectangle box5;

    @FXML
    private Rectangle box6;

    @FXML
    private Rectangle box7;

    @FXML
    private Rectangle box8;

    @FXML
    private Rectangle box9;

    @FXML
    private Rectangle box10;

    @FXML
    private Rectangle box11;

    @FXML
    private Rectangle box12;

    @FXML
    private Rectangle box13;

    @FXML
    private Rectangle box14;

    @FXML
    private Rectangle box15;

    @FXML
    private Rectangle box16;

    @FXML
    private Button startButton;

    @FXML
    private Button undoButton;


    @FXML
    void initialize() {
        Text[][] slots = {
                {slot1, slot2, slot3, slot4},
                {slot5, slot6, slot7, slot8},
                {slot9, slot10, slot11, slot12},
                {slot13, slot14, slot15, slot16}};

        Rectangle[][] boxes = {
                {box1, box2, box3, box4},
                {box5, box6, box7, box8},
                {box9, box10, box11, box12},
                {box13, box14, box15, box16}};

        gameMatrix[0].add(0);
        gameMatrix[0].add(0);
        gameMatrix[0].add(0);
        gameMatrix[0].add(0);

        gameMatrix[1].add(0);
        gameMatrix[1].add(0);
        gameMatrix[1].add(0);
        gameMatrix[1].add(0);

        gameMatrix[2].add(0);
        gameMatrix[2].add(0);
        gameMatrix[2].add(0);
        gameMatrix[2].add(0);

        gameMatrix[3].add(0);
        gameMatrix[3].add(0);
        gameMatrix[3].add(0);
        gameMatrix[3].add(0);

        previousMatrix = Logic.copy(gameMatrix);

        int[] score = {0};

        Logic.prettyPrint(gameMatrix);

        int[] previousBestResult = {0};

        File file = new File("bestResult.txt");
        if(!file.exists()) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("bestResult.txt"));
                writer.write("0");
                writer.close();
                previousBestResult[0] = 0;
                bestText.setText(String.valueOf(previousBestResult[0]));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("bestResult.txt"));
            previousBestResult[0] = Integer.parseInt(reader.readLine());
            bestText.setText(String.valueOf(previousBestResult[0]));
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        startButton.setOnAction(actionEvent -> {
            gamePane.setVisible(true);
            mainPane.setVisible(false);
        });

        mainPane.setOnKeyPressed(keyEvent -> {
            switch(keyEvent.getCode()) {
                case ENTER:
                    gamePane.setVisible(false);
                    mainPane.setVisible(true);
            }
        });

        newGameButton.setOnAction(actionEvent -> {
            gameOverPane.setVisible(false);
            newGame(slots, boxes, gameMatrix, previousMatrix, score);
        });

        backToMainButton.setOnAction(actionEvent -> {
            gamePane.setVisible(false);
            mainPane.setVisible(true);
            newGame(slots, boxes, gameMatrix, previousMatrix, score);
        });

        tryAgainButton.setOnAction(actionEvent -> {
            gameOverPane.setVisible(false);
            newGame(slots, boxes, gameMatrix, previousMatrix, score);
        });

        gamePane.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case D:
                    previousMatrix = Logic.copy(gameMatrix);
                    if(Logic.moveRight(gameMatrix, score)) {
                        ArrayList emptySlots = Logic.getEmptyList(gameMatrix);
                        int randomSlot = Logic.getRandomSlot(emptySlots);
                        int[] indices = Logic.getIndexFromNumber(randomSlot);
                        Logic.insert_2_or_4(gameMatrix, indices[0], indices[1]);
                    }
                    Logic.prettyPrint(gameMatrix);
                    break;
                case A:
                    previousMatrix = Logic.copy(gameMatrix);
                    if(Logic.moveLeft(gameMatrix, score)) {
                        ArrayList emptySlots1 = Logic.getEmptyList(gameMatrix);
                        int randomSlot1 = Logic.getRandomSlot(emptySlots1);
                        int[] indices1 = Logic.getIndexFromNumber(randomSlot1);
                        Logic.insert_2_or_4(gameMatrix, indices1[0], indices1[1]);
                    }
                    Logic.prettyPrint(gameMatrix);
                    break;
                case W:
                    previousMatrix = Logic.copy(gameMatrix);
                    if(Logic.moveUp(gameMatrix, score)) {
                        ArrayList emptySlots2 = Logic.getEmptyList(gameMatrix);
                        int randomSlot2 = Logic.getRandomSlot(emptySlots2);
                        int[] indices2 = Logic.getIndexFromNumber(randomSlot2);
                        Logic.insert_2_or_4(gameMatrix, indices2[0], indices2[1]);
                    }
                    Logic.prettyPrint(gameMatrix);
                    break;
                case S:
                    previousMatrix = Logic.copy(gameMatrix);
                    if(Logic.moveDown(gameMatrix, score)) {
                        ArrayList emptySlots3 = Logic.getEmptyList(gameMatrix);
                        int randomSlot3 = Logic.getRandomSlot(emptySlots3);
                        int[] indices3 = Logic.getIndexFromNumber(randomSlot3);
                        Logic.insert_2_or_4(gameMatrix, indices3[0], indices3[1]);
                    }
                    Logic.prettyPrint(gameMatrix);
                    break;
            }

            if(Logic.checkMatrix(gameMatrix)) {
                System.out.println("GAME OVER!!!");
                previousMatrix = Logic.copy(gameMatrix);
                gameOverPane.setVisible(true);
            }

            scoreText.setText(String.valueOf(score[0]));

            try {
                if(previousBestResult[0] < score[0]) {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("bestResult.txt"));
                    writer.write(String.valueOf(score[0]));
                    writer.close();
                    bestText.setText(String.valueOf(score[0]));
                    previousBestResult[0] = score[0];
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            updateMatrix(slots, boxes, gameMatrix);
        });

        undoButton.setOnAction(actionEvent -> {
            gameMatrix = Logic.copy(previousMatrix);
            updateMatrix(slots, boxes, gameMatrix);
        });

        settingButton.setOnAction(actionEvent -> {
            mainPane.setVisible(false);
            settingPane.setVisible(true);
        });

        backButton.setOnAction(actionEvent -> {
            settingPane.setVisible(false);
            mainPane.setVisible(true);
        });
    }

    private void newGame(Text[][] slots, Rectangle[][] boxes, ArrayList[] gameMatrix, ArrayList[] previousMatrix, int[] score) {
        for(int i = 0;i<4;i++) {
            for(int j = 0;j<4;j++) {
                slots[i][j].setVisible(false);
                boxes[i][j].setFill(Paint.valueOf("#def6fe"));
                gameMatrix[i].set(j, 0);
                previousMatrix[i].set(j, 0);
            }
        }
        scoreText.setText("0");
        score[0] = 0;
    }

    private void updateMatrix(Text[][] slots, Rectangle[][] boxes, ArrayList[] gameMatrix) {
        for(int i = 0;i<4;i++) {
            for(int j = 0;j<4;j++) {
                slots[i][j].setText(gameMatrix[i].get(j).toString());
                if (gameMatrix[i].get(j).equals(0)) {
                    slots[i][j].setVisible(false);
                    boxes[i][j].setFill(Paint.valueOf("#def6fe"));
                } else slots[i][j].setVisible(true);

                switch((int)gameMatrix[i].get(j)) {
                    case 2:
                        boxes[i][j].setFill(Paint.valueOf("#00fff6"));
                        break;
                    case 4:
                        boxes[i][j].setFill(Paint.valueOf("#C1FF72"));
                        break;
                    case 8:
                        boxes[i][j].setFill(Paint.valueOf("#00BF63"));
                        break;
                    case 16:
                        boxes[i][j].setFill(Paint.valueOf("#FFDE59"));
                        break;
                    case 32:
                        boxes[i][j].setFill(Paint.valueOf("#FF914D"));
                        break;
                    case 64:
                        boxes[i][j].setFill(Paint.valueOf("#ea00ff"));
                        break;
                    case 128:
                        boxes[i][j].setFill(Paint.valueOf("#CB6CE6"));
                        break;
                    case 256:
                        boxes[i][j].setFill(Paint.valueOf("#0900ff"));

                        break;
                    case 512:
                        boxes[i][j].setFill(Paint.valueOf("#FFDE59"));

                        break;
                    case 1024:
                        boxes[i][j].setFill(Paint.valueOf("#5E17EB"));
                        break;
                    case 2048:
                        boxes[i][j].setFill(Paint.valueOf("#FF3131"));
                        break;
                    case 4096:
                        boxes[i][j].setFill(Paint.valueOf("#000000"));
                        break;
                }
            }
        }
    }
}