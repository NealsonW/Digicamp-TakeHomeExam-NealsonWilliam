/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Task;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Nealson William
 */
public class ControllerTask {
    private Connection dbCon;
    static DatabaseHandler conn = new DatabaseHandler(); 
    
    public ControllerTask(Connection c)
    {
        dbCon = c;
    }
    
    public static ArrayList<Task> getAllTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM tasks";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getString("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setStatus(rs.getString("status_task"));
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (tasks);
    }
    
    public static boolean insertTask(Task task) {
        conn.connect();
        String query_InsertToPerson = "INSERT INTO tasks VALUES (?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query_InsertToPerson);
            stmt.setString(1, task.getId());
            stmt.setString(2, task.getTitle());
            stmt.setString(3, task.getDescription());
            stmt.setString(4, task.getStatus());
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (false);
    }
    
    public static boolean updateStatus(String id, String newStatus) {
        conn.connect();
        String query = "UPDATE tasks SET status_task = '" + newStatus
                + "' WHERE id = '" + id + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    
    public static boolean deleteTask(String id) {
        conn.connect();
        String query = "DELETE FROM tasks WHERE id='" + id + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
}
