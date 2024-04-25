package Del_2.sample;

import Del_2.MemberArchive.*;
import Del_2.Logger.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;

public class Main extends Application {
    MemberArchive ma = new MemberArchive();
    TableView table = new TableView();
    MyLogger myLogger = new MyLogger();

    public Main() throws IOException {
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        memberList();

        table.setColumnResizePolicy(table.CONSTRAINED_RESIZE_POLICY);
        table.setPrefHeight(500);
        table.setPrefWidth(500);
        System.out.println(ma.getMembers().get(0).getPersonals());

        TableColumn<Integer, BonusMember> memberNOColumn = new TableColumn<>("Member No");
        memberNOColumn.setCellValueFactory(new PropertyValueFactory<>("MemberNO"));

        TableColumn<Personals, BonusMember> personalsColumn = new TableColumn<>("Name");
        personalsColumn.setCellValueFactory(new PropertyValueFactory<>("Personals"));

        TableColumn<LocalDate, BonusMember> LocaldateColumn = new TableColumn<>("Enrolled date");
        LocaldateColumn.setCellValueFactory(new PropertyValueFactory<>("EnrolledDate"));

        TableColumn<Integer, BonusMember> pointsColumn = new TableColumn<>("Points");
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("Points"));

        table.getColumns().addAll(memberNOColumn,personalsColumn,LocaldateColumn,pointsColumn);
        updateTableView();

        GridPane buttonpane = new GridPane();
        Button addMemberbtn = new Button("Add member");
        Button  deleteMemberbtn = new Button("Delete member ");
        Button  updateMemberbtn = new Button("Update member");
        Button  showdetailsbtn = new Button("Show details");

        addMemberbtn.setOnAction(s ->addMember());
        deleteMemberbtn.setOnAction(s->deleteMember());
        updateMemberbtn.setOnAction(s-> {
           ma.checkMember(LocalDate.now());
        });
        showdetailsbtn.setOnAction(s-> {
            int index = ma.getMembers().indexOf(table.getSelectionModel().getSelectedItem());
            BonusMember member = ma.getMembers().get(index);
            showDetails(member);
        });

        buttonpane.add(addMemberbtn,1, 1);
        buttonpane.add(deleteMemberbtn,3,1);
        buttonpane.add(updateMemberbtn, 5,1);
        buttonpane.add(showdetailsbtn,7,1);

        GridPane gridPane = new GridPane();
        gridPane.add(table, 1, 1);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gridPane);
        borderPane.setBottom(buttonpane);
        borderPane.setPadding(new Insets(10,10,10,10));
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void addMember(){
        Stage stage = new Stage();
        TextField firstname = new TextField();
        firstname.setPromptText("First name");

        TextField surnamefield = new TextField();
        surnamefield.setPromptText("Surname");

        TextField EmailField = new TextField();
        EmailField.setPromptText("Email");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button comfirmBtn = new Button("Comfirm");

        GridPane gridPane = new GridPane();
        gridPane.add(firstname,3,1);
        gridPane.add(surnamefield,3,2);
        gridPane.add(EmailField,3,3);
        gridPane.add(passwordField,3,4);
        gridPane.add(comfirmBtn,3,5);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gridPane);
        borderPane.setPadding(new Insets(10,10,10,10));

        comfirmBtn.setOnAction(s ->{
            try {
                Personals personals = new Personals(firstname.getText(), surnamefield.getText(), EmailField.getText(), passwordField.getText());
                ma.newMember(personals, LocalDate.now());
                stage.close();
                updateTableView();
            }catch (Exception e){
                myLogger.getLogger().log(Level.FINE,e.getMessage());
            }
        });
        stage.setScene(new Scene(borderPane, 100,200));
        stage.show();

    }
    public void deleteMember(){
        ma.getMembers().remove(table.getSelectionModel().getSelectedItem());
        updateTableView();
    }

    public void showDetails(BonusMember member){
        Label label = new Label(member.toString());
        VBox vbox = new VBox(label);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vbox);
        Button checkMemberBtn = new Button("Check member");
        Button registerpointsBtn = new Button("Register points");
        checkMemberBtn.setOnAction(s -> checkMember(member));
        registerpointsBtn.setOnAction(s ->{
            registerPoints(member);
            label.setText(member.toString());
        });
        GridPane gridPane = new GridPane();
        gridPane.add(checkMemberBtn, 1,1);
        gridPane.add(registerpointsBtn,2,1);
        borderPane.setBottom(gridPane);
        borderPane.setPadding(new Insets(10,10,10,10));
        Stage stage = new Stage();
        stage.setScene(new Scene(borderPane,250,150));
        stage.show();
    }
    public void registerPoints(BonusMember member){
        Stage stage = new Stage();
        TextField points = new TextField();
        points.setPromptText("Points");
        Button register = new Button("Comfirm");
        register.setOnAction(s -> {
            try {
                member.registerPoints(Integer.parseInt(points.getText()));
                updateTableView();
                stage.close();
            }catch (Exception e){
                myLogger.getLogger().log(Level.FINE,e.getMessage());
            }
        });
        GridPane gridPane = new GridPane();
        gridPane.add(points, 1,1);
        gridPane.add(register, 1,2);
        stage.setScene(new Scene(gridPane,50,50));
        stage.show();

    }

    public void checkMember(BonusMember member){
        member.findQualificationPoints(LocalDate.now());
        String message;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        System.out.println(member.findQualificationPoints(LocalDate.now()));
        if(member.findQualificationPoints(LocalDate.now()) != -1) {
            message = "This member can upgrade";
        }else{
            message= "This member can't upgrage";
            alert.setAlertType(Alert.AlertType.WARNING);
        }

        alert.setContentText(message);

        alert.showAndWait();
    }

    public void memberList(){
        Personals ole = new Personals("Olsen", "Ole", "ole.olsen@dot.com", "ole");
        Personals tove = new Personals("Hansen", "Tove", "tove.hansen@dot.com", "tove");
        ma.newMember( ole, LocalDate.of(2006, 2, 15));
        ma.newMember( tove, LocalDate.of(2007, 3, 5));

    }
    public void updateTableView(){
        table.getItems().clear();
        for (BonusMember member:ma.getMembers()) {
            table.getItems().add(member);
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
