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

import java.util.concurrent.atomic.AtomicInteger;

public class displayClass {

    static Button closeButton=new Button("Ok");
    static VBox lastBox;
    public static void clickBox(){
//        lastBox.setOnDragDropped(e->display());
    }
    public static void display(storeData.Class Main,FlowPane mainLayout ){
        Stage classWindow=new Stage();
        cppGenerator en=new cppGenerator();
        classWindow.initModality(Modality.APPLICATION_MODAL);
        classWindow.setTitle("Class setting");

        JFXTextField Name = new JFXTextField();
        Name.setPromptText("Name");
        Name.setLabelFloat(true);


        JFXTextField extenedC = new JFXTextField();
        extenedC.setPromptText("extended class/classes");
        extenedC.setLabelFloat(true);


        AtomicInteger access = new AtomicInteger();
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



        JFXRadioButton Abstract=new JFXRadioButton("Abstract");

        JFXRadioButton Static=new JFXRadioButton("Static");
        //  Static.setAlignment(Pos.TOP_LEFT);



        closeButton.setStyle("    -jfx-button-type: RAISED;\n" +
                "    -fx-background-color:#d6f6f0 ;\n" +
                "    -fx-text-fill: #929191;");

        int finalAccess = access.get();
        closeButton.setOnAction(e-> {
            VBox box = new VBox(20);
            box.setStyle("    -fx-background-color:#fcc2a5 ;\n");
            if (accessStr[0] == "1")
                access.set(1);
            else if (accessStr[0] == "2")
                access.set(2);
            else if (accessStr[0] == "3")
                access.set(3);

            classWindow.close();
            storeData.Class innerClass=new storeData.Class(Name.getText(), Static.isSelected(), access.get(), extenedC.getText(), Abstract.isSelected());

            box.setAlignment(Pos.CENTER);
            en.classG(innerClass);
            box.getChildren().addAll(new Label(en.engineCode));

            mainLayout.getChildren().add(box);
            Main.addInnerClass(innerClass);
            //lastBox = box;

        });

        FlowPane layout2 = new FlowPane(Orientation.HORIZONTAL,10,10);
        //  layout2.setSpacing(8);
        layout2.setPadding(new Insets(10,10,10,10));
        layout2.getChildren().addAll(Name,extenedC,Public,Private,Protected,Static,Abstract,closeButton);

        Scene classScene= new Scene(layout2,400,400);
        classWindow.setScene(classScene);


        classWindow.showAndWait();
    //    Main.addInnerClass(new storeData.Class(Name.getText(),Static.isSelected(),access ,extenedC.getText(),Abstract.isSelected()));

      //  javaClasses.add(new storeData.Class(Name.getText(),Static.isSelected(),access ,extenedC.getText(),Abstract.isSelected()));

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

        Button addFuncButton=new Button("add Function");
        addFuncButton.setOnAction(e->classWindow.close());
        addFuncButton.setStyle("    -jfx-button-type: RAISED;\n" +
                "    -fx-background-color:#d6f6f0 ;\n" +
                "    -fx-text-fill: #929191;");

        Button addClassButton=new Button("add Class");
        addClassButton.setOnAction(e->classWindow.close());
        addClassButton.setStyle("    -jfx-button-type: RAISED;\n" +
                "    -fx-background-color:#d6f6f0 ;\n" +
                "    -fx-text-fill: #929191;");

        Button closeButton=new Button("Ok");
        closeButton.setOnAction(e->classWindow.close());
        closeButton.setStyle("    -jfx-button-type: RAISED;\n" +
                "    -fx-background-color:#d6f6f0 ;\n" +
                "    -fx-text-fill: #929191;");

        FlowPane layout2 = new FlowPane(Orientation.HORIZONTAL,10,10);
        layout2.setPadding(new Insets(15));
        layout2.getChildren().addAll(addClassButton,addFuncButton,addAttrButton,closeButton);

        Scene classScene= new Scene(layout2,400,400);
        classWindow.setScene(classScene);
        classWindow.showAndWait();
    }

}
