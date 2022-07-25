import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;

import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;


import javax.swing.event.ChangeEvent;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
public class displayAttr {

 //   public static cppGenerator en=new cppGenerator();///uml generator
    static VBox lastBox;
  //  static VBox box=new VBox(20);
    public static void display(storeData.Class Main,FlowPane mainLayout) {
        Stage attrWindow=new Stage();
        cppGenerator en=new cppGenerator();
        attrWindow.initModality(Modality.APPLICATION_MODAL);
        attrWindow.setTitle("attribute setting");


        JFXTextField Name = new JFXTextField();
        Name.setPromptText("Name");
        Name.setLabelFloat(true);

        JFXTextField type = new JFXTextField();
        type.setPromptText("Type");
        type.setLabelFloat(true);

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

        JFXTextField valueStatic = new JFXTextField();
        valueStatic.setPromptText("static value");
        valueStatic.setLabelFloat(true);

        AtomicInteger access = new AtomicInteger();
        int finalAccess = access.get();

        Button closeButton=new Button("Ok");
        closeButton.setOnAction(e->{
            VBox box=new VBox(20);
            box.setStyle( "    -fx-background-color:#fde3d5 ;\n" );
            if(accessStr[0]=="1")
                access.set(1);
            else if(accessStr[0]=="2")
                access.set(2);
            else if(accessStr[0]=="3")
                access.set(3);

            storeData.Attribute attr;//=new storeData.Attribute(type.getText(),Name.getText(),true, access.get(),valueStatic.getText();

            if(Static.isSelected()==false) {
                attr=new storeData.Attribute(type.getText(),Name.getText(),false , access.get());
            }
            else {
                attr=new storeData.Attribute(type.getText(),Name.getText(),true, access.get(),valueStatic.getText());
            }
            attrWindow.close();
            Main.addAttribute(attr);

            box.setAlignment(Pos.CENTER);

            en.attributeG(attr);
            box.getChildren().addAll(new Label(en.engineCode));
            mainLayout.getChildren().add(box);

        });
        closeButton.setStyle("    -jfx-button-type: RAISED;\n" +
                "    -fx-background-color:#d6f6f0 ;\n" +
                "    -fx-text-fill: #929191;");

        FlowPane layout2 = new FlowPane(Orientation.HORIZONTAL,10,10);
      //  layout2.setSpacing(8);
        layout2.setPadding(new Insets(10,10,10,10));
        layout2.getChildren().addAll(Name,type,Public,Private,Protected,Static,valueStatic,closeButton);

        Scene attrScene= new Scene(layout2,400,400);
        attrWindow.setScene(attrScene);

        attrWindow.showAndWait();

        }
    }
