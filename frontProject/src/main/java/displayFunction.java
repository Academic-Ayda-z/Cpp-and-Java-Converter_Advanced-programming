import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class displayFunction {
    //public static cppGenerator en=new cppGenerator();
    static VBox lastBox;
   // static VBox box=new VBox(20);
    public static void display(storeData.Class Main,FlowPane mainLayout){
        Stage funcWindow=new Stage();
        cppGenerator en=new cppGenerator();
        funcWindow.initModality(Modality.APPLICATION_MODAL);
        funcWindow.setTitle("function setting");

        JFXTextField returnType = new JFXTextField();
        returnType.setPromptText("return Object Type");
        returnType.setAlignment(Pos.TOP_LEFT);
        //Name.setLayoutX(15);
        returnType.setLabelFloat(true);

        JFXTextField returnValue = new JFXTextField();
        returnValue.setPromptText("return Object Value");
        returnValue.setAlignment(Pos.TOP_LEFT);
        //Name.setLayoutX(15);
        returnValue.setLabelFloat(true);

        JFXTextField Name = new JFXTextField();
        Name.setPromptText("Name");
        Name.setLabelFloat(true);


        final ToggleGroup groupAccses = new ToggleGroup();

        JFXRadioButton Public=new JFXRadioButton("Public");
        Public.setToggleGroup(groupAccses);
        Public.setUserData("3");

        JFXRadioButton Protected=new JFXRadioButton("Protected");
        Protected.setToggleGroup(groupAccses);
        Protected.setUserData("2");

        JFXRadioButton Private=new JFXRadioButton("Private");
        Private.setToggleGroup(groupAccses);
        Private.setUserData("1");

        final String[] accessStr = new String[1];

        groupAccses.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (groupAccses.getSelectedToggle() != null) {
                    accessStr[0] =groupAccses.getSelectedToggle().getUserData().toString();
                    // System.out.println(groupAccses.getSelectedToggle().getUserData().toString());
                }
            }
        });

        JFXRadioButton Static=new JFXRadioButton("Static");
        //  Static.setAlignment(Pos.TOP_LEFT);

        JFXTextField inputs = new JFXTextField();
        inputs.setPromptText("function inputs");
        inputs.setLabelFloat(true);

        JFXRadioButton Abstract=new JFXRadioButton("Abstract");

        boolean isStatic= false;
         boolean isAbstract= false;

        AtomicInteger access = new AtomicInteger();
        int finalAccess = access.get();
        Button closeButton=new Button("Ok");

        closeButton.setOnAction(e->{
            VBox box=new VBox(20);
            box.setStyle("    -fx-background-color:#fcd2bd ;\n");
            if(accessStr[0]=="1")
                access.set(1);
            else if(accessStr[0]=="2")
                access.set(2);
            else if(accessStr[0]=="3")
                access.set(3);

            storeData.Function innerFunc=new storeData.Function(returnType.getText(),inputs.getText(),returnValue.getText(),Name.getText(),Static.isSelected(),access.get(),Abstract.isSelected());
            Main.addFunction(innerFunc);

            funcWindow.close();

            box.setAlignment(Pos.CENTER);
            en.functionG(innerFunc);
            box.getChildren().addAll(new Label(en.engineCode));

            mainLayout.getChildren().add(box);


          //  int size=mainLayout.getChildren().size();
        //    if(mainLayout.getChildren().contains(lastBox))
          //      mainLayout.getChildren().remove(lastBox);
          //  mainLayout.getChildren().add(box);
         //   lastBox=box;



        });
        closeButton.setStyle("    -jfx-button-type: RAISED;\n" +
                "    -fx-background-color:#d6f6f0 ;\n" +
                "    -fx-text-fill: #929191;");

        FlowPane layout2 = new FlowPane(Orientation.HORIZONTAL,10,10);
        //  layout2.setSpacing(8);
        layout2.setPadding(new Insets(10,10,10,10));
        layout2.getChildren().addAll(Name,returnType,returnValue,Public,Private,Protected,Static,Abstract,inputs,closeButton);

        Scene funcScene= new Scene(layout2,400,400);
        funcWindow.setScene(funcScene);

        funcWindow.showAndWait();

    }
    public static void change(){
        Stage classWindow=new Stage();
        classWindow.initModality(Modality.APPLICATION_MODAL);
        classWindow.setTitle("Class setting");

        Button addAttrButton=new Button("add Attribute");
        addAttrButton.setOnAction(e->classWindow.close());
        addAttrButton.setStyle("    -jfx-button-type: RAISED;\n" +
                "    -fx-background-color:#d6f6f0 ;\n" +
                "    -fx-text-fill: #929191;");


        Button closeButton=new Button("Ok");
        closeButton.setOnAction(e->classWindow.close());
        closeButton.setStyle("    -jfx-button-type: RAISED;\n" +
                "    -fx-background-color:#d6f6f0 ;\n" +
                "    -fx-text-fill: #929191;");

        FlowPane layout2 = new FlowPane(Orientation.HORIZONTAL,10,10);
        layout2.setPadding(new Insets(15));
        layout2.getChildren().addAll(addAttrButton,closeButton);

        Scene classScene= new Scene(layout2,400,400);
        classWindow.setScene(classScene);
        classWindow.showAndWait();
    }

}
