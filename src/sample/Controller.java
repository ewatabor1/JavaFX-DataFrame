package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import main.java.lab1.dataFrame.*;
import main.java.lab1.myExceptions.WrongTypeInColumn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    ObservableList<String> allTypesList = FXCollections.observableArrayList(
            "DateTimeValue", "DoubleValue", "FloatValue","IntegerValue","StringValue");
    @FXML private VBox boxLoadFile;
    @FXML private TextField textFFA;
    @FXML private TextField widthTF;
    @FXML private TextField addColNameTF;
    @FXML private TextArea colInfoText;
    @FXML private TextArea typesInfoText;
    @FXML private Text typesMessage;
    @FXML private Text groupbyMessage;
    @FXML private Text widthInfo;
    @FXML private CheckBox checkFN;
    @FXML private CheckBox showLoadFile;
    @FXML private ChoiceBox typeCBox;
    @FXML private Button addTypeButton;
    @FXML private Button setWidthButton;
    @FXML private Button clearTypesButton;

    Class<? extends Value>[] types;
    String[] names;
    DataFrame dataF;
    DataFrame.DataFrameGroupBy dataFrameGroupBy;
    File file;
    int width=0;



    @FXML private void initialize() {
        typeCBox.setItems(allTypesList);
        showLoadFile.setSelected(true);

    }
    @FXML private void setWidth (){
        if (Integer.valueOf(widthTF.getText())>=0){
            int a = Integer.valueOf(widthTF.getText())-1;
            if (a<0) a=0;
            if (width<Integer.valueOf(widthTF.getText()) || types[a]==null) {
                width = Integer.valueOf(widthTF.getText());
                types = new Class[width];
                names=new String[width];
                typesMessage.setText("Width set.");
                widthInfo.setText("Width: "+width);
            }
            else{
                typesMessage.setText("Width not set.");
            }
        }
        else typesMessage.setText("Width must be a nonnegative number!");
        typesMessage.setVisible(true);
    }
    @FXML private void addType (){
        boolean flag=false;
        for (int i=0;i<width;i++){
            if(types[i]==null) {
                flag=true;
                if (typeCBox.getValue()=="DateTimeValue") types[i]=DateTimeValue.class;
                if (typeCBox.getValue()=="DoubleValue") types[i]=DoubleValue.class;
                if (typeCBox.getValue()=="FloatValue") types[i]=FloatValue.class;
                if (typeCBox.getValue()=="IntegerValue") types[i]=IntegerValue.class;
                if (typeCBox.getValue()=="StringValue") types[i]=StringValue.class;
                typesMessage.setText("Type added");
                typesMessage.setVisible(true);
                break;
            }
        }
        if (!flag) {
            if(width==0) typesMessage.setText("Set width first!");
            else typesMessage.setText("All types are already added!");
            typesMessage.setVisible(true);
        }
        else {
            typesInfoText.setText(typesInfoText.getText()+typeCBox.getValue()+" ");
        }
    }
    @FXML private void addName(){
        boolean flag=false;
        for (int i=0;i<width;i++){
            if(names[i]==null) {
                flag=true;
                names[i]=addColNameTF.getText();
                typesMessage.setText("Column name added.");
                typesMessage.setVisible(true);
                break;
            }
        }
        if(!flag){
            if(names[width-1]!=null) typesMessage.setText("All names are already set!");
            else typesMessage.setText("Column name not added.");
            typesMessage.setVisible(true);
        }
        else {
            colInfoText.setText(colInfoText.getText()+" \""+addColNameTF.getText()+"\"");
        }
    }
    @FXML private void clearTypes (ActionEvent event){
        if (types[0]==null) typesMessage.setText("Types already cleared.");
        else{
            for (int i=0;i<width;i++){
                if (types[i]==null) break;
                types[i]=null;
            }
            typesMessage.setText("Types cleared.");
        }
        typesInfoText.setText("Types given: ");
    }
    @FXML private void clearNames (ActionEvent event){
        if (checkFN.isSelected()){
                for (int i=0;i<width;i++){
                    if (names[i]==null) break;
                    names[i]=null;
                }
                typesMessage.setText("Types cleared.");
            colInfoText.setText("Names in file.");
        }
        else colInfoText.setText("Names given: ");
    }

    @FXML private void handleButton1Action (ActionEvent event) {
        try{
            boolean flag=true;
            for (int a=0;a<width;a++){
                if (types[a]==null) flag=false;
            }
            if (checkFN.isSelected()) {
                if (flag){
                    dataF = new DataFrame(file.getAbsolutePath(), types);
                    typesMessage.setText("File loaded successfully");
                    typesMessage.setVisible(true);
                }
                else{
                    typesMessage.setText("Wrong number of type arguments. File not loaded.");
                    typesMessage.setVisible(true);
                }
            }
            else if(!checkFN.isSelected()){
                for (int i=0;i<width;i++) if (names[i]==null || types[i]==null) flag=false;
                if (flag){
                    dataF = new DataFrame(file.getAbsolutePath(),
                            types,names);
                    typesMessage.setText("File loaded.");
                    typesMessage.setVisible(true);
                }
                else {
                    typesMessage.setText("Wrong number of arguments. File not loaded.");
                    typesMessage.setVisible(true);
                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
            typesMessage.setText("File not loaded. Try again.");
            typesMessage.setVisible(true);
        }

    }
    @FXML private void getMinButtonAction (ActionEvent event){
        //FXMLLoader loader = new FXMLLoader()
    }
    @FXML private void showLoadFile (ActionEvent event){
        boolean set=false;
        if (showLoadFile.isSelected()) set=true;
        boxLoadFile.setVisible(set);
        widthInfo.setVisible(set);
        typesInfoText.setVisible(set);
        colInfoText.setVisible(set);
    }
    @FXML protected void locateFile(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        Node node = (Node) event.getSource();
        file =  chooser.showOpenDialog(node.getScene().getWindow());

    }

}
