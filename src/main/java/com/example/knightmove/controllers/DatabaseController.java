package com.example.knightmove.controllers;
import com.example.knightmove.HelloApplication;
import com.example.knightmove.Model.Consts;
import com.example.knightmove.Model.GameHistory;
import com.example.knightmove.Model.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class DatabaseController implements Initializable {


    private Stage stage;
    private Scene scene;
    private Parent root;
    public void returnToAppIntroPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("AppIntroPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //check if the user is in the database
    public boolean isUserInDatabase(String nickname) throws SQLException {
        try{
            //establish connection
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://src//Knight_Move_DB.accdb");
            //create the statement
            PreparedStatement stmt = con.prepareStatement(Consts.ADD_NEW_PLAYER);
            stmt.setString(1, nickname);
            //execute sql query
            stmt.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    //for player info

    @FXML
    private TableView<Player> playerTableView;
    @FXML
    private TableColumn<Player,String> nickname;

    public ArrayList<Player> getAllPlayers() {
        ArrayList<Player> playerArrayList= new ArrayList<>();
        try (
                //establish connection
                Connection con = DriverManager.getConnection("jdbc:ucanaccess://src//Knight_Move_DB.accdb");
                //create the statement
                Statement stmt = con.createStatement();
                //execute sql query
                ResultSet rs = stmt.executeQuery("SELECT * FROM Players_tbl")
        ){
            while (rs.next()){
                playerArrayList.add(new Player(rs.getString("nickname")));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return playerArrayList;
    }

    private ObservableList<Player> getPlayersToTable(){
        ObservableList<Player> players = FXCollections.observableArrayList();
        ArrayList<Player> query;
        try {
            query = new ArrayList<>(getAllPlayers());
            players.addAll(query);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(players+" players");
        return players;
    }

    public void playersFunctionsInitialized(){
        nickname.setCellValueFactory(new PropertyValueFactory<>("Nickname"));
        playerTableView.setItems(getPlayersToTable());
    }
    //for score board information
    @FXML
    private TableView<GameHistory> scoreTableView;
    @FXML
    private TableColumn<GameHistory,Integer> gameHistoryIDColumn;
    @FXML
    private TableColumn<GameHistory,String> nicknameForGameHistoryColumn;
    @FXML
    private TableColumn<GameHistory,Integer> scoreColumn;


    public ArrayList<GameHistory> getGameHistory() {
        ArrayList<GameHistory> GameHistoryList= new ArrayList<>();
        try (
                //establish connection
                Connection con = DriverManager.getConnection("jdbc:ucanaccess://src//Knight_Move_DB.accdb");
                //create the statement
                Statement stmt = con.createStatement();
                //execute sql query
                ResultSet rs = stmt.executeQuery("SELECT * FROM Game_History_tbl")
        ){
            while (rs.next()){
                GameHistoryList.add(new GameHistory(rs.getInt(1),rs.getString(2),rs.getInt(3)));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return GameHistoryList;
    }

    private ObservableList<GameHistory> getGameHistoryToTable(){
        ObservableList<GameHistory> GameHistory = FXCollections.observableArrayList();
        ArrayList<GameHistory> query;
        try {
            query = new ArrayList<>(getGameHistory());
            GameHistory.addAll(query);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(GameHistory+" GameHistory");
        return GameHistory;
    }

    public void GameHistoryFunctionsInitialized(){
        gameHistoryIDColumn.setCellValueFactory(new PropertyValueFactory<>("gameID"));
        nicknameForGameHistoryColumn.setCellValueFactory(new PropertyValueFactory<>("nicknameForGameHistory"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("Score"));
        scoreTableView.setItems(getGameHistoryToTable());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //playersTbl
        playersFunctionsInitialized();
        //scoreboardTbl
        GameHistoryFunctionsInitialized();

    }

}