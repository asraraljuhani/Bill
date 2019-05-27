/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bill;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author asrar
 */
public class close {
    
    public static void display(String title,String message){
    
    Stage window=new Stage();
    
    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle(title);
    window.setMaxWidth(250);
    Label l=new Label();
    l.setText(message);
    Button yes=new Button("yes");
    Button no=new Button("no");
    /////////
    VBox v=new VBox(10);
    v.getChildren().addAll(l,yes,no);
    v.setAlignment(Pos.CENTER);
    Scene s=new Scene(v);
    window.setScene(s);
    window.showAndWait();
    
    }
}
