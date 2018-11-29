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

    public void setOperationLabel(String textOpC,String...colname){
        String text = new String("OOOOO");
        System.out.println(name);
        try{
            if (name.equals("Min")){
                text= dataFrame.iloc(0,200).groupBy(colname).min().toString();
                operationText.setText("Min "+textOpC);
            }
            else if (name.equals("Max")){
                text= dataFrame.groupBy(colname).max().toString();
                operationText.setText("Max "+textOpC);
            }
            else if (name.equals("Sum")){
                text= dataFrame.groupBy(colname).sum().toString();
                operationText.setText("Sum "+textOpC);
            }
            else if (name.equals("Std")){
                text= dataFrame.groupBy(colname).std().toString();
                operationText.setText("Std "+textOpC);
            }
            else if (name.equals("Mean")){
                text= dataFrame.groupBy(colname).mean().toString();
                operationText.setText("Mean "+textOpC);
            }
            else if (name.equals("Var")){
                text= dataFrame.groupBy(colname).var().toString();
                operationText.setText("Var "+textOpC);
            }
            //System.out.println(text);
            operationLabel.setText(text);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
