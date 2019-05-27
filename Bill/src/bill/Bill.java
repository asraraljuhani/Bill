/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bill;
//package com.concretepage;

import com.itextpdf.text.Document;

import com.itextpdf.text.Image;
import com.itextpdf.text.*;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.*;
import javafx.collections.*;
import javafx.event.EventType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Scale;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.itextpdf.text.Font;
import com.sun.scenario.effect.ImageData;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import static javafx.print.Paper.A4;

/**
 *
 * @author asrar
 */
public class Bill extends Application {

    Stage window;
    TableView<thing> table;
    String NameOfCompany;
    ArrayList<thing> list = new ArrayList<>();
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 36,
            Font.BOLD);
    //Document d=new Document();

    TextField nameInput, priceInput, quantityInput;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception, FileNotFoundException, DocumentException {
        window = primaryStage;
        window.setTitle("The Bill ");

        //Name column
        TableColumn<thing, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Price column
        TableColumn<thing, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Quantity column
        TableColumn<thing, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        //Total column
        TableColumn<thing, Double> totalColumn = new TableColumn<>("Total");
        totalColumn.setMinWidth(100);
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

        //vBox
        VBox vBox1 = new VBox();

        VBox vBox2 = new VBox();

        VBox vBox3 = new VBox();

        /////////////////////////////////////////////////
        //Name input
        nameInput = new TextField();
        nameInput.setPromptText("Name");
        nameInput.setMinWidth(100);

        //Price input
        priceInput = new TextField();
        priceInput.setPromptText("Price");

        //Quantity input
        quantityInput = new TextField();
        quantityInput.setPromptText("Quantity");

        //Buttons
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addButtonClicked());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteButtonClicked());

        window.setOnCloseRequest(e -> {
            e.consume();
            closeprogram();
        });

        //scene1
        Label CompanyName = new Label("Enter Company Name");

        TextField CompanyNameInput = new TextField();
        CompanyNameInput.getText();
        CompanyNameInput.setPromptText("Company Name");
        CompanyNameInput.setMinWidth(200);

        System.out.println(CompanyNameInput.getText());
        HBox hBox1 = new HBox();
        hBox1.setPadding(new Insets(10, 10, 10, 10));
        hBox1.setSpacing(10);
        Button next1 = new Button("Next");
        Button close = new Button("Close");
        Button p1 = new Button("Previous");
        next1.setAlignment(Pos.CENTER);
        close.setAlignment(Pos.CENTER);
        p1.setAlignment(Pos.CENTER);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(CompanyName, CompanyNameInput, next1);

        vBox1.setAlignment(Pos.CENTER);

        vBox1.getChildren().addAll(hBox1, close);
        Scene scene1 = new Scene(vBox1);
        Scene scene2 = new Scene(vBox2);
        Scene scene3 = new Scene(vBox3);

        scene1.getStylesheets().add("stylesheet.css");

        //scene2
        HBox hBox2 = new HBox();
        hBox2.setPadding(new Insets(10, 10, 10, 10));
        hBox2.setSpacing(10);
        Button next2 = new Button("Next");
        Button p2 = new Button("Previous");
        next2.setAlignment(Pos.CENTER);
        p2.setAlignment(Pos.CENTER);
        hBox2.getChildren().addAll(nameInput, priceInput, quantityInput, addButton, deleteButton);

        HBox v = new HBox();
        v.setPadding(new Insets(10, 10, 10, 10));
        v.setSpacing(10);
        v.setAlignment(Pos.CENTER);
        v.getChildren().addAll(p1, next2, close);

        table = new TableView<>();
        table.setItems(getProduct());
        table.getColumns().addAll(nameColumn, priceColumn, quantityColumn, totalColumn);

        vBox2.getChildren().addAll(table, hBox2, v);

        //scene3
        Label enterinfo = new Label("press on the button to create pdf file of your bill:)");

        HBox hBox3 = new HBox();
        hBox3.setPadding(new Insets(10, 10, 10, 10));
        hBox3.setSpacing(10);

        Button create = new Button("create");

        HBox hBox4 = new HBox();
        hBox4.setPadding(new Insets(10, 10, 10, 10));
        hBox4.setSpacing(10);

        close.setAlignment(Pos.CENTER);
        p2.setAlignment(Pos.CENTER);
        create.setAlignment(Pos.CENTER);
        hBox3.setAlignment(Pos.CENTER);
        hBox4.setAlignment(Pos.CENTER);
        hBox3.getChildren().addAll(enterinfo, create);
        hBox4.getChildren().addAll(p2, close);
        vBox3.setAlignment(Pos.CENTER);
        vBox3.getChildren().addAll(hBox3, hBox4);

        //next Button
        next1.setOnAction(e -> {
            window.setScene(scene2);
            this.NameOfCompany = CompanyNameInput.getText().toUpperCase();
        });

        next2.setOnAction(e -> window.setScene(scene3));
        close.setOnAction(e -> closeprogram());
        p1.setOnAction(e -> window.setScene(scene1));
        p2.setOnAction(e -> window.setScene(scene2));

        create.setOnAction(e -> {
            try {

                pdf(table, this.NameOfCompany);
                Alert alert = new Alert(AlertType.CONFIRMATION);

                alert.setTitle("Success");
                alert.setHeaderText("Your Bill have successfully create it:) ");
                alert.setContentText("if you want to close the program click on OK..if you won`t click on CANCEL");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    // ... user chose OK
                    alert.close();
                    window.close();
                } else {
                    // ... user chose CANCEL or closed the dialog
                    alert.close();

                }

            } catch (FileNotFoundException | DocumentException ex) {
                Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        window.setHeight(500);

        window.setScene(scene1);
        window.show();

    }

    //Add button clicked
    /**
     *
     */
    public void addButtonClicked() {
        // Boolean i=false;
        thing product = new thing();
        product.setName(nameInput.getText());

        product.setPrice(Double.parseDouble(priceInput.getText()));

        product.setQuantity(Integer.parseInt(quantityInput.getText()));
//
//        while (i == false) {
//
//            try {
//
//                product.setPrice(Double.parseDouble(priceInput.getText()));
//                product.setQuantity(Integer.parseInt(quantityInput.getText()));
//
//                i = true;
//            } catch (Exception e) {
//
//                // check();
//                Alert alert = new Alert(AlertType.ERROR);
//                alert.setTitle("Alert!");
//                alert.setHeaderText("You Entered Wrong value");
//                alert.setContentText("please check the value that you entered is numbers not letters.");
//                alert.close();
//
//                priceInput.clear();
//                quantityInput.clear();
//                priceInput.clear();
//                quantityInput.clear();
//            }
//        }

         
        
        table.getItems().add(product);
        nameInput.clear();
        priceInput.clear();
        quantityInput.clear();

    }

    //Delete button clicked
    public void deleteButtonClicked() {
        ObservableList<thing> productSelected, allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();
        productSelected.forEach(allProducts::remove);
    }

    //Get all of the products
    /**
     *
     * @return
     */
    public ObservableList<thing> getProduct() {
        ObservableList<thing> products = FXCollections.observableArrayList();

        return products;
    }

    private void closeprogram() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Alert!");
        alert.setHeaderText("Are you sure you want to exit?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            alert.close();
            window.close();
        } else {
            // ... user chose CANCEL or closed the dialog
            alert.close();
        }
    }

    private void check() {

        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Alert!");
        alert.setHeaderText("You Entered Wrong value");
        alert.setContentText("please check the value that you entered is numbers not letters.");
    }

    public void pdf(TableView<thing> table1, String name) throws FileNotFoundException, DocumentException, BadElementException, IOException {
        Document document = new Document(PageSize.A4);
        Rectangle one = new Rectangle(100, 100);
        document.addTitle(name);
        PdfPTable table = new PdfPTable(4);
        PdfPCell cell = null;
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell("Name");
        table.addCell("Price");
        table.addCell("Quantity");
        table.addCell("Total");
        table.setHeaderRows(1);

        PdfPCell[] cells = table.getRow(0).getCells();
        for (int j = 0; j < cells.length; j++) {
            cells[j].setBackgroundColor(BaseColor.GRAY);
        }
        for (int i = 0; i < table1.getItems().size(); i++) {
            table.addCell(table1.getItems().get(i).getName());
            table.addCell(Double.toString(table1.getItems().get(i).getPrice()));
            table.addCell(Double.toString(table1.getItems().get(i).getQuantity()));
            table.addCell(Double.toString(table1.getItems().get(i).getTotal()));
        }
        name = ("Company Name: " + name);
        Paragraph p1 = new Paragraph(name);
        p1.setAlignment(Element.ALIGN_CENTER);
        p1.setFont(catFont);
        PdfWriter.getInstance(document, new FileOutputStream("Bill.pdf"));

        Image img = Image.getInstance("invoice.png");
        img.scaleAbsolute(100f, 100f);

        img.setAbsolutePosition((float) (PageSize.A4.getWidth() - 350.0), (float) (PageSize.A4.getHeight() - 120.0));

        document.open();

        Paragraph paragraph = new Paragraph();
        //addEmptyLine(paragraph, 100);
        // document.add(Chunk.NEWLINE);

        document.add(Chunk.TABBING);
        img.setAlignment(Element.ALIGN_CENTER);
        // img.setAbsolutePosition(10, (float) (PageSize.A4.getHeight() - 20.0));
        document.add(img);
        document.add(new Paragraph(""));
        // document.add(Chunk.TABBING);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);

        document.add(p1);
        document.add(new Paragraph("\n"));

        // document.add(Chunk.TABBING);
        document.add(table);
        document.close();
    }

    public ArrayList conv(TableView<thing> table1, ArrayList<thing> list) {
        for (int i = 0; i < 5; i++) {
        }

        return list;
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

}
