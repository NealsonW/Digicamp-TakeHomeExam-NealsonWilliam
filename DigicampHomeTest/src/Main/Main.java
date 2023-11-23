/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controller.ControllerTask;
import Model.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Nealson William
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int num = 0;
        Scanner in = new Scanner(System.in);
        
        while(num != 6){
            mainMenu();
            num = in.nextInt();
            switch(num){
                case 1:
                    ArrayList<Task> arrTask = ControllerTask.getAllTask();
                    for(int i=0; i<arrTask.size();i++){
                        System.out.println(arrTask.get(i).toString());
                    }
                    break;
                case 2:
                    Task newTask = new Task();
                    boolean insertTask;
                    String title;
                    String desc;
                    int lastId;
                    String id;
                    Scanner inputInsert = new Scanner(System.in);
                    System.out.print("Masukkan Judul: ");
                    title = inputInsert.nextLine();  
                    if(title.equals("")){
                        System.out.println("Judul Tidak Boleh Kosong!");
                        break;
                    }
                    System.out.print("Masukkan Deskripsi: ");
                    desc = inputInsert.nextLine();   
                    if(desc.equals("")){
                        System.out.println("Deskripsi Tidak Boleh Kosong!");
                        break;
                    }
                    arrTask = ControllerTask.getAllTask();

                    if(arrTask.isEmpty()){
                        id = "1";
                    }
                    lastId = Integer.parseInt(arrTask.get(arrTask.size()-1).getId());
                    lastId+=1;
                    id = Integer.toString(lastId);                    
                    
                    
                    newTask.setId(id);
                    newTask.setTitle(title);
                    newTask.setDescription(desc);
                    newTask.setStatus("To Do");
                    insertTask = ControllerTask.insertTask(newTask);
                    if(insertTask){
                        System.out.println("Task baru berhasil dimasukkan!");
                        break;
                    } 
                    System.out.println("Terjadi Kesalahan");
                    break;
                case 3:
                    Scanner inputUpdt = new Scanner(System.in);
                    boolean foundUpdt = false;
                    System.out.print("Silahkan masukkan id task: ");
                    String inId = inputUpdt.nextLine();
                    
                    arrTask = ControllerTask.getAllTask();
                    for(int i=0; i<arrTask.size();i++){
                        if(inId.equals(arrTask.get(i).getId())){
                            System.out.println(arrTask.get(i).toString());
                            foundUpdt = true;
                        }
                    }
                    if(!foundUpdt){
                        System.out.println("ID tidak ditemukan!");
                        break;
                    }                    
                    
                    System.out.println("Silahkan pilih status: ");
                    System.out.println("1. To Do");
                    System.out.println("2. In Progress");
                    System.out.println("3. Done");
                    System.out.print("Status: ");
                    String inStatus = inputUpdt.nextLine();
                    String newStatus = "";
                    if(inStatus.equals("1")){
                        newStatus = "To Do";
                    } else if(inStatus.equals("2")){
                        newStatus = "In Progress";
                    } else if(inStatus.equals("3")){
                        newStatus = "Done";
                    } 
                    boolean updateStatus;
                    updateStatus = ControllerTask.updateStatus(inId, newStatus);
                    if(updateStatus){
                        System.out.println("Task baru berhasil diperbaharui!");   
                        break;
                    }
                    System.out.println("Terjadi Kesalahan");
                    break;
                case 4:
                    Scanner inputDel = new Scanner(System.in);
                    boolean found = false;
                    System.out.print("Masukkan ID task yang ingin dihapus: ");
                    String idDel = inputDel.nextLine();
                    arrTask = ControllerTask.getAllTask();
                    for(int i=0; i<arrTask.size();i++){
                        if(idDel.equals(arrTask.get(i).getId())){
                            found = true;
                        }
                    }
                    if(!found){
                        System.out.println("ID tidak ditemukan!");
                        break;
                    }                    
                    boolean deleteTask;
                    deleteTask = ControllerTask.deleteTask(idDel);
                    if(deleteTask){
                        System.out.println("Task berhasil dihapus!");
                        break;
                    }
                    System.out.println("Terjadi Kesalahan");
                    break;
                case 5:
                    break;
            }
        }
    }
    
    public static void mainMenu(){
        System.out.println("Pick a Query:");
        System.out.println("1.Get all tasks");
        System.out.println("2.Insert a new task");
        System.out.println("3.Update a task");
        System.out.println("4.Delete a task");
        System.out.println("5.Back");
        System.out.println("6.Exit");
        System.out.print("Number: ");
    }
    
}
