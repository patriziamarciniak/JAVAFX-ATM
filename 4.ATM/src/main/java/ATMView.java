
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;


public class ATMView extends Application {


    private ATMPresenter presenter;
    private Pane root, root1, root2, root3;
    Stage theStage;
    Scene scene, scene1, scene2, scene3, scene4;
    MenuBar menuBar;


    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            root = FXMLLoader.load(getClass()
                    .getResource("/atmStartWindow.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        menuBar = (MenuBar) (root.lookup("#menu"));
        menuBar.getMenus().get(1).getItems().get(0).setOnAction(event -> setSceneAbout());
        menuBar.getMenus().get(0).getItems().get(0).setOnAction(event -> setSceneClientsFile() );
        menuBar.getMenus().get(0).getItems().get(1).setOnAction(event -> setSceneNoteFile() );


        root.lookup("#buttonLogin")
                .setOnMouseClicked(event -> presenter.buttonLoginClicked());

        root.lookup("#buttonLogOut")
                .setOnMouseClicked(event -> presenter.buttonLogOutClicked());


        root.lookup("#buttonPay").setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((Pane) (root.lookup("#panePay"))).setVisible(true);
                ((Pane) (root.lookup("#paneTransaction"))).setVisible(false);
                ((TextField) (root.lookup("#textKeyboardInput"))).setDisable(true);
                ((Button) (root.lookup("#buttonClear"))).setDisable(true);
            }
        });

        root.lookup("#buttonWithdraw")
                .setOnMouseClicked(event -> presenter.buttonWithdrawClicked());

        root.lookup("#buttonChooseNotes")
                .setOnMouseClicked(event -> presenter.buttonChooseNotesClicked());

        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            root.lookup("#buttonNum" + finalI).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ((TextField) (root.lookup("#textKeyboardInput"))).appendText(Integer.toString(finalI));
                }
            });
        }

        root.lookup("#buttonClear").setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((TextField) (root.lookup("#textKeyboardInput"))).clear();
            }
        });

        root.lookup("#buttonCancel").setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
goBackFromPaying();
            }
        });

        root.lookup("#buttonChangePIN").setOnMouseClicked(event -> setChangePINScene());


        this.presenter = new ATMPresenter(this);
        this.theStage = primaryStage;

        this.scene = new Scene(root, 600, 550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

public void goBackFromPaying(){
    ((Pane) (root.lookup("#paneTransaction"))).setVisible(true);
    ((Pane) (root.lookup("#panePay"))).setVisible(false);
    ((TextField) (root.lookup("#textKeyboardInput"))).setDisable(false);
    ((Button) (root.lookup("#buttonClear"))).setDisable(false);
}

    public String getPIN() {
        if (((TextField) (root.lookup("#textPIN"))).getText().trim().isEmpty()) {
            return "empty";
        } else {
            return ((TextField) (root.lookup("#textPIN"))).getText();
        }
    }

    public String getCardNumber() {
        if (((TextField) (root.lookup("#textCardNr"))).getText().trim().isEmpty()) {
            return "empty";
        } else {
            return ((TextField) (root.lookup("#textCardNr"))).getText();
        }
    }

    public String getInputValue() {
        if (((TextField) (root.lookup("#textKeyboardInput"))).getText().trim().isEmpty()) {
            return "empty";
        } else {
            return ((TextField) (root.lookup("#textKeyboardInput"))).getText();
        }
    }

    public String getFileName() {
        if (((TextField) (root1.lookup("#textFileName"))).getText().trim().isEmpty()) {
            return "empty";
        } else {
            return ((TextField) (root1.lookup("#textFileName"))).getText();
        }
    }


    public int getNote(int i) {
        if (((TextField) (root.lookup("#text" + i))).getText().trim().isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(((TextField) (root.lookup("#text" + i))).getText());
        }
    }

    public int getPINtoChange(int i) {
        if (((TextField) (root2.lookup("#textpin" + i))).getText().trim().isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(((TextField) (root2.lookup("#textpin" + i ))).getText());
        }
    }

    public void setUserLogged() {
        menuBar.getMenus().get(0).setDisable(true);
        ((Pane) (root.lookup("#paneLogin"))).setVisible(false);
        ((Pane) (root.lookup("#paneTransaction"))).setVisible(true);
        ((Button) (root.lookup("#buttonLogin"))).setVisible(false);
        ((Button) (root.lookup("#buttonLogOut"))).setVisible(true);
        ((Button) (root.lookup("#buttonChangePIN"))).setVisible(true);
        ((Label) (root.lookup("#labelSaldo"))).setVisible(true);
        ((Label) (root.lookup("#labelSaldoState"))).setVisible(true);
        ((Label) (root.lookup("#labelAccount"))).setVisible(true);
        ((Label) (root.lookup("#labelAccountNr"))).setVisible(true);
        ((Label) (root.lookup("#labelSaldo"))).textProperty().bind(new SimpleIntegerProperty(presenter.atm.user.saldo).asString());
        ((Label) (root.lookup("#labelAccount"))).textProperty().bind(new SimpleLongProperty(presenter.atm.user.accountNumber).asString());
    }

    public void setUserLogOut() {
        menuBar.getMenus().get(0).setDisable(false);
        ((Pane) (root.lookup("#paneLogin"))).setVisible(true);
        ((Pane) (root.lookup("#paneTransaction"))).setVisible(false);
        ((Pane) (root.lookup("#panePay"))).setVisible(false);
        ((Button) (root.lookup("#buttonLogin"))).setVisible(true);
        ((Button) (root.lookup("#buttonLogOut"))).setVisible(false);
        ((Button) (root.lookup("#buttonChangePIN"))).setVisible(false);
        ((TextField) (root.lookup("#textCardNr"))).clear();
        ((TextField) (root.lookup("#textPIN"))).clear();
        ((Label) (root.lookup("#labelSaldo"))).setVisible(false);
        ((Label) (root.lookup("#labelSaldoState"))).setVisible(false);
        ((Label) (root.lookup("#labelAccount"))).setVisible(false);
        ((Label) (root.lookup("#labelAccountNr"))).setVisible(false);
    }

    public void showAlert(String text) {

        Pane rootWindow = new Pane();

        try {
            rootWindow = FXMLLoader.load(getClass()
                    .getResource("/atmWrongNumber.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        ((Label) (rootWindow.lookup("#labelErrorMessage"))).setText(text);
        scene3 = new Scene(rootWindow, 250, 125);
        theStage.setScene(scene3);


        ((Label) (rootWindow.lookup("#labelErrorMessage"))).setText(text);
        rootWindow.lookup("#buttonBack")
                .setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {

                        setStartScene();
                        ((Label) (root.lookup("#labelSaldo"))).textProperty().bind(new SimpleIntegerProperty(presenter.atm.user.saldo).asString());

                    }
                });
        ((TextField) (root.lookup("#textCardNr"))).clear();
        ((TextField) (root.lookup("#textPIN"))).clear();
        ((TextField) (root.lookup("#textKeyboardInput"))).clear();
    }


    public void setStartScene() {

        theStage.setScene(scene);
        theStage.show();

    }

    public void setSceneAbout() {
        Pane root2 = new Pane();

        try {
            root2 = FXMLLoader.load(getClass()
                    .getResource("/atmAbout.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        scene2 = new Scene(root2, 520, 350);
        theStage.setScene(scene2);

        root2.lookup("#buttonBackAbout").setOnMouseClicked(event -> setStartScene());

    }

    public void setSceneClientsFile() {
       root1 = new Pane();

        try {
            root1 = FXMLLoader.load(getClass()
                    .getResource("/atmFileManager.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        scene1 = new Scene(root1, 350, 200);
        theStage.setScene(scene1);

        root1.lookup("#buttonFileCancel").setOnMouseClicked(event -> setStartScene());
        root1.lookup("#buttonFileOk").setOnMouseClicked(event -> presenter.buttonFileOkClicked());
    }

    public void setChangePINScene() {

        root2 = new Pane();

        try {
            root2 = FXMLLoader.load(getClass()
                    .getResource("/atmChangePIN.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        scene4 = new Scene(root2, 280, 240);
        theStage.setScene(scene4);

        root2.lookup("#buttonCancelPIN").setOnMouseClicked(event -> setStartScene());
        root2.lookup("#buttonChangePIN").setOnMouseClicked(event -> presenter.buttonChangePINClicked());
    }

    public void setSceneNoteFile(){
        root3 = new Pane();

        try {
            root3 = FXMLLoader.load(getClass()
                    .getResource("/atmFileManager.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        scene1 = new Scene(root3, 350, 200);
        theStage.setScene(scene1);

        root3.lookup("#buttonFileCancel").setOnMouseClicked(event -> setStartScene());
        root3.lookup("#buttonFileOk").setOnMouseClicked(event -> presenter.buttonNoteFileOkClicked());
    }
}

