package kangarooSimulator;

import java.lang.reflect.InvocationTargetException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import static kangarooSimulator.JumpyGroof.nodeInput;

public class tableNoHeight {
    
    //for table-NodeHeight from Each Node Detail
    static TableView<NodeHeight> table;
    static TextField nodeToInput,heightInput;
 
    public static void display(String title, String message, String message2){
        Stage window = new Stage();
        window.setTitle(title);
        window.initModality(Modality.APPLICATION_MODAL);
        
        //nodeTo column
        TableColumn<NodeHeight,Integer> nodeToCol = new TableColumn<>(message);
        nodeToCol.setMinWidth(200);
        nodeToCol.setCellValueFactory(new PropertyValueFactory<>("nodeToID"));
        
        //height column
        TableColumn<NodeHeight,Integer> heightCol = new TableColumn<>(message2);
        heightCol.setMinWidth(200);
        heightCol.setCellValueFactory(new PropertyValueFactory<>("heightID"));
        
        //TextField for nodeTo
        nodeToInput = new TextField();
        nodeToInput.setMaxSize(100, 100);
        nodeToInput.setPromptText("Node ID");
        
        //TextField for height
        heightInput = new TextField();
        heightInput.setMaxSize(100, 100);
        heightInput.setPromptText("Height");
        
        //add button
        Button add = new Button("ADD");
        add.setOnAction(e ->{
            addItem();
        });
        
        
        //exit button
        Button exit = new Button("EXIT");
        exit.setOnAction(e -> {
            window.close();
        });
        
        //HBox
        HBox boxTable2 = new HBox();
        boxTable2.setPadding(new Insets(10,10,10,10));
        boxTable2.setSpacing(10);
        boxTable2.getChildren().addAll(nodeToInput,heightInput,add,exit);

        TableView table = new TableView<>();
        table.setItems(getNodeHeightDetail());
        table.getColumns().addAll(nodeToCol,heightCol);

        VBox layoutTable = new VBox(20);
        layoutTable.getChildren().addAll(table,boxTable2);
        
        Scene sceneTable = new Scene(layoutTable,400,400);
        
        window.setScene(sceneTable);
        window.showAndWait();
        
        
    }
    
    public static ObservableList<NodeHeight> getNodeHeightDetail(){
        ObservableList<NodeHeight> products2 = FXCollections.observableArrayList();
        
        return products2;
    }
    
    public static void addItem(){
        NodeHeight x = new NodeHeight();
        x.setNodeToID(Integer.parseInt(nodeToInput.getText()));
        x.setHeightID(Integer.parseInt(heightInput.getText()));
        table.getItems().add(x);
        nodeToInput.clear();
        heightInput.clear();
    }
    
    
}
