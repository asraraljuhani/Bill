/*
 * Created By: Asrar Aljuhani
 * some of codes from https://github.com/buckyroberts 
 * 
 */
package bill;

import javafx.scene.text.Font;
import com.itextpdf.text.BadElementException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.*;
import javafx.collections.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import java.awt.FlowLayout;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.FontWeight;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author asrar
 */
public class Bill extends Application {

    Stage window;
    TableView<Thing> table;
    String nameOfCompany;
    ArrayList<Thing> list = new ArrayList<>();
    private static com.itextpdf.text.Font catFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 36, com.itextpdf.text.Font.BOLD);

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
        //add icon to the window
        Image imageWindow;
        imageWindow = new Image(new FileInputStream("client-icon.png"));
        window.getIcons().add(imageWindow);
        //creating the table for scene2 
        //Name column
        TableColumn<Thing, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Price column
        TableColumn<Thing, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Quantity column
        TableColumn<Thing, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        //Total column
        TableColumn<Thing, Double> totalColumn = new TableColumn<>("Total");
        totalColumn.setMinWidth(100);
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

        //vBox
        VBox vBox1 = new VBox();

        VBox vBox2 = new VBox();

        VBox vBox3 = new VBox();

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
        //client-icon
        Image imageMain;
        imageMain = new Image(new FileInputStream("client-icon (1).png"));
        //Setting the image view 
        ImageView viewimageMain = new ImageView(imageMain);
        HBox hBox0 = new HBox();

        hBox0.getChildren().add(viewimageMain);
        hBox0.setAlignment(Pos.BASELINE_CENTER);

        HBox hBox00 = new HBox();
        Text Welcome = new Text(" Welcome to Bill app");

        Welcome.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        Welcome.setStyle("-rtfx-background-color: blue;");
        Welcome.setFill(Color.BLUEVIOLET);
        hBox00.getChildren().add(Welcome);
        hBox00.setAlignment(Pos.CENTER);
      

        Label CompanyName = new Label("Enter Company Name");

        TextField CompanyNameInput = new TextField();
        CompanyNameInput.getText();
        CompanyNameInput.setPromptText("Company Name");
        CompanyNameInput.setMinWidth(200);

        HBox hBox1 = new HBox();
        hBox1.setPadding(new Insets(10, 10, 10, 10));
        hBox1.setSpacing(10);
        Button next1 = new Button("Next");
        Button close = new Button("Close");
        Button p1 = new Button("Previous");

        Image image;
        image = new Image(new FileInputStream("review-icon.png"));

        //Setting the image view 
        ImageView imageView = new ImageView(image);

        Button readFile = new Button("read from File", imageView);

        next1.setAlignment(Pos.CENTER);
        close.setAlignment(Pos.CENTER);
        p1.setAlignment(Pos.CENTER);
        readFile.setAlignment(Pos.CENTER);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(CompanyName, CompanyNameInput, next1, readFile);
        readFile.setOnAction(e -> {
            readFile();
        });
        vBox1.setAlignment(Pos.CENTER);

        vBox1.getChildren().addAll(hBox0, hBox00, hBox1, close);
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
       
        Text enterinfo = new Text("Press on the button to create pdf file of your bill:)");

        enterinfo.setFont(Font.font("Verdana", FontWeight.LIGHT, 18));
        enterinfo.setFill(Color.RED);
        
        HBox hBox3 = new HBox();
        hBox3.setPadding(new Insets(10, 10, 10, 10));
        hBox3.setSpacing(10);
        //cat-paper-icon
        Image imageCreate;
        imageCreate = new Image(new FileInputStream("cat-paper-icon.png"));
        //Setting the image view 
        ImageView viewimageCreate = new ImageView(imageCreate);

        Button create = new Button("create", viewimageCreate);

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
            this.nameOfCompany = CompanyNameInput.getText().toUpperCase();
        });

        next2.setOnAction(e -> window.setScene(scene3));
        close.setOnAction(e -> closeprogram());
        p1.setOnAction(e -> window.setScene(scene1));
        p2.setOnAction(e -> window.setScene(scene2));

        create.setOnAction(e -> {
            try {

                pdf(table, this.nameOfCompany);
                Alert alert = new Alert(AlertType.CONFIRMATION);

                alert.setTitle("Success");
                alert.setHeaderText("Your Bill have successfully create it:) ");
                alert.setContentText("If you want to read the file press Read The File\nIf you want to close the program click on OK\nIf you won`t click on CANCEL");
                ButtonType readTheFile = new ButtonType("Read The File");

                alert.getButtonTypes().add(readTheFile);
                alert.setGraphic(imageView);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    // ... user chose OK
                    alert.close();
                    window.close();
                } else if (result.get() == ButtonType.CANCEL) {
                    // ... user chose CANCEL or closed the dialog
                    alert.close();
                } else {
                    // ... user chose readTheFile button
                    readFile();
                }

            } catch (FileNotFoundException | DocumentException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
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
        do {
            try {
                Thing product = new Thing();
                if (nameInput.getText().length() == 0) {
                    Alert alert = new Alert(AlertType.ERROR, "You did not enter a name for the product, please be careful", ButtonType.CLOSE);
                    alert.showAndWait();
                    return;

                }

                product.setName(nameInput.getText());
                product.setPrice(Double.parseDouble(priceInput.getText()));
                product.setQuantity(Integer.parseInt(quantityInput.getText()));

                table.getItems().add(product);
                nameInput.clear();
                priceInput.clear();
                quantityInput.clear();
                break;
            } catch (Exception e) {
                nameInput.clear();
                priceInput.clear();
                quantityInput.clear();

                Alert alert = new Alert(AlertType.ERROR, "You enter wrong value in the table, please be careful", ButtonType.CLOSE);
                alert.showAndWait();
                return;

            }
        } while (true);

    }

    //Delete button clicked
    public void deleteButtonClicked() {
        ObservableList<Thing> productSelected, allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();
        productSelected.forEach((item) -> allProducts.remove(item));//lambda expression
    }

    //Get all of the products
    /**
     *
     * @return products
     */
    public ObservableList<Thing> getProduct() {
        ObservableList<Thing> products = FXCollections.observableArrayList();

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

    public void pdf(TableView<Thing> table1, String name) throws FileNotFoundException, DocumentException, BadElementException, IOException {
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

        com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance("invoice.png");
        img.scaleAbsolute(100f, 100f);

        img.setAbsolutePosition((float) (PageSize.A4.getWidth() - 350.0), (float) (PageSize.A4.getHeight() - 120.0));

        document.open();

        Paragraph paragraph = new Paragraph();

        document.add(Chunk.TABBING);
        img.setAlignment(Element.ALIGN_CENTER);

        document.add(img);
        document.add(new Paragraph(""));

        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);

        document.add(p1);
        document.add(new Paragraph("\n"));

        document.add(table);
        document.close();
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    public void readFile() {
        PdfReader reader = null;

        try {

            reader = new PdfReader("Bill.pdf");
            int pages = reader.getNumberOfPages();

            //Iterate the pdf through pages.
            for (int i = 1; i <= pages; i++) {
                // pageNumber = 1 start from one. 
                String textFromPage = PdfTextExtractor.getTextFromPage(reader, i);
                textFromPage = textFromPage.substring(8);
                String companyName = "";
                if (textFromPage.indexOf("\n") == -1) {
                    companyName = "There no Company Name";
                } else {
                    companyName = textFromPage.substring(0, textFromPage.indexOf("\n"));
                }
                String[] msg = printMsg(textFromPage);

                readWin(msg, companyName);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
    }

    public String[] printMsg(String text) {

        text = text.substring(text.indexOf("\n"));

        if (text.indexOf("N") == -1) {
            return new String[]{"There no products to show."};
        }
        text = text.substring(text.indexOf("N"));
        text = text.substring(text.lastIndexOf("Total") + 6);
        String ar[] = text.split("\\s");

        return ar;

    }

    public void readWin(String[] msg, String companyName) {

        JFrame frame = new JFrame();

        JTextArea textArea = new JTextArea();

        JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setSize(500, 500);

        // Add the scroll pane into the content pane
        frame.add(scroll);

        frame.setSize(600, 600);
        textArea.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 1));
        String dots = " ";
        for (int i = 0; i <= companyName.length() + 2; i++) {
            dots += "-";
        }
        textArea.setText(dots + "\n " + companyName + "\n" + dots + "\n");

        textArea.setText(textArea.getText() + " ---------------------------The Products-----------------------\n"
                + " -------------------------------------------------------------\n");
        if (!msg[0].equalsIgnoreCase("There no products to show.")) {
            for (int i = 0; i < msg.length - 3; i += 4) {

                textArea.setText(textArea.getText() + " Product Name: " + msg[i] + "\n Price: " + msg[i + 1] + "\n Quantity: " + msg[i + 2] + "\n Total: " + msg[i + 3] + "\n--------------------------------------------------------------\n");

            }
        } else {
            textArea.setText(textArea.getText() + " There no products to show.");
            textArea.setText(textArea.getText() + "\n--------------------------------------------------------------\n");

        }

        frame.setTitle("The Bill");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

}
