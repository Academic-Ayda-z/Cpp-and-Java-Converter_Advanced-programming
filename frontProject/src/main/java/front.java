import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.print.PrinterJob;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.shape.Box;

import javax.xml.soap.Node;
import javax.xml.soap.Text;
import java.util.ArrayList;

public class front extends Application {
    public Scene mainScene;
    cppGenerator cppEngine=new cppGenerator();
    javaGenerator javaEngine=new javaGenerator();
    public static void main(String[] args) {
        launch(args);


    }
    public void start(Stage primaryStage) throws Exception {
        //(String Name,boolean isS,int access ,String ExtendedClass,boolean isAbs)
        storeData.Class Main= new storeData.Class("Main",false,3,"",false);


        Stage window=primaryStage;
        primaryStage.setTitle("my uml");

        //      if(javaEngine.isPossible!=0)
        //         javaEngine.classG(Main);
        //     cppEngine.classG(Main);

      //  cppCode.setTranslateX(150);
     //   cppCode.setTranslateY(100);

    //    javaCode.setTranslateX(150);
   //     javaCode.setTranslateY(100);
          VBox javaConverterLayout =new VBox(20);
          VBox cppConverterLayout =new VBox(20);

        Button cppConverter = new Button("Cpp Code");

        cppConverter.setStyle("    -jfx-button-type: RAISED;\n" +
                "    -fx-background-color:#fccb97 ;\n" +
                "    -fx-text-fill: #929191;");

        VBox box2= new VBox(20);
        box2.setAlignment(Pos.CENTER);

        //cppConverter.setAlignment(Pos.BASELINE_RIGHT);
        VBox box= new VBox(20);
        box.setAlignment(Pos.CENTER);
        Button javaConverterButton = new Button("Java Code");

        javaConverterButton.setAlignment(Pos.BASELINE_RIGHT);
        javaConverterButton.setStyle("    -jfx-button-type: RAISED;\n" +
                "    -fx-background-color: #aaf8f3 ;\n" +
                "    -fx-text-fill: #929191;");

        FlowPane mainLayout = new FlowPane(Orientation.HORIZONTAL,10,10);
        mainLayout.setPadding(new Insets(15));

        mainScene = new Scene(mainLayout, 700, 400);


        Button okButton4 = new Button("Done");
        okButton4.setId("ok");
        okButton4.setStyle("    -jfx-button-type: RAISED;\n" +
                "    -fx-background-color:#d6f6f0 ;\n" +
                "    -fx-text-fill: #929191;");

        Button okButton5 = new Button("Done");
        okButton5.setId("ok");
        okButton5.setStyle("    -jfx-button-type: RAISED;\n" +
                "    -fx-background-color:#cef5ed ;\n" +
                "    -fx-text-fill: #929191;");

        Button funcButton = new Button("Function");
        funcButton.setOnAction(e->displayFunction.display(Main,mainLayout));
        funcButton.setStyle("    -jfx-button-type: RAISED;\n" +
                "    -fx-background-color:#fcd2bd ;\n" +
                "    -fx-text-fill: #929191;");

        Button attrButton = new Button("Attribute");
        attrButton.setOnAction(e->displayAttr.display(Main,mainLayout) );
        attrButton.setStyle("    -jfx-button-type: RAISED;\n" +
                "    -fx-background-color:#fde3d5 ;\n" +
                "    -fx-text-fill: #929191;");


        Button classButton = new Button("Class");
        classButton.setOnAction(e->displayClass.display(Main,mainLayout));
        classButton.setStyle("    -jfx-button-type: RAISED;\n" +
                "    -fx-background-color:#fcc2a5 ;\n" +
                "    -fx-text-fill: #929191;");

      //  VBox javaConverterLayout =new VBox(20);
        VBox test=new VBox(20);
        test.getChildren().add(new Label("test"));

        javaConverterLayout.getChildren().addAll(box2,okButton4);////
        javaConverterLayout.setAlignment(Pos.TOP_CENTER);

    //    VBox cppConverterLayout =new VBox(20);
        cppConverterLayout.getChildren().addAll(okButton5);////
        cppConverterLayout.setAlignment(Pos.TOP_CENTER);

        mainLayout.getChildren().addAll(classButton, funcButton, attrButton,cppConverter,javaConverterButton);
        //mainScene.getStylesheets().add("style.css");

        Scene cppCodeScene = new Scene(cppConverterLayout, 700, 400);
        Scene javaCodeScene = new Scene(javaConverterLayout, 700, 400);

        okButton4.setOnAction(e->window.setScene(mainScene));
        okButton5.setOnAction(e->window.setScene(mainScene));
        javaConverterButton.setOnAction(e->{
            if(javaEngine.isPossible==1) {
                javaEngine.classG(Main);
                box2.getChildren().clear();
                box.getChildren().add(new Label(javaEngine.engineCode));
            }
            else{
                box.getChildren().add(new Label( "can not convert this code to java!"));
            }
            javaConverterLayout.getChildren().add(box);

            window.setScene(javaCodeScene);
        });

        cppConverter.setOnAction(e->{
            cppEngine.classG(Main);
          //  box2.getChildren().clear();
            box2.getChildren().add(new Label(cppEngine.engineCode));
            cppConverterLayout.getChildren().add(box2);
            window.setScene(cppCodeScene);
        });

        primaryStage.setScene(mainScene);

        primaryStage.show();


    }


}