package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import main.java.lab1.dataFrame.DataFrame;

public class OperationController {
    public DataFrame dataFrame;
    private String name;
    @FXML private Text operationText;
    @FXML private Label operationLabel;
    public OperationController(){
        dataFrame=null;
        name="";
    }

    public OperationController(DataFrame dataFrame,String name){
        this.dataFrame=dataFrame;
        this.name=name;
    }
    public void setName (String name){
        this.name=name;
    }
    public String getName (){
        return name;
    }
    public void setDataFrame(DataFrame dataFrame){
        this.dataFrame=dataFrame;
    }

    public void setOperationLabel(String...colname){
        String text = new String("OOOOO");
        System.out.println(name);
        try{
            if (name.equals("Min")){
                text= dataFrame.groupBy(colname).min().toString();
                operationText.setText("Min");
            }
            else if (name.equals("Max")){
                text= dataFrame.groupBy(colname).max().toString();
                operationText.setText("Max");
            }
            else if (name.equals("Sum")){
                text= dataFrame.groupBy(colname).sum().toString();
                operationText.setText("Sum");
            }
            else if (name.equals("Std")){
                text= dataFrame.groupBy(colname).std().toString();
                operationText.setText("Std");
            }
            else if (name.equals("Mean")){
                text= dataFrame.groupBy(colname).mean().toString();
                operationText.setText("Mean");
            }
            else if (name.equals("Var")){
                text= dataFrame.groupBy(colname).var().toString();
                operationText.setText("Var");
            }
            //System.out.println(text);
            operationLabel.setText(text+text+text+text);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
