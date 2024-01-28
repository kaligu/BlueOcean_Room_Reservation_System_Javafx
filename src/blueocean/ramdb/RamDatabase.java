package blueocean.ramdb;

import blueocean.model.Customer;
import blueocean.model.Meal;
import blueocean.model.Room;

import java.util.ArrayList;

public class RamDatabase {
    public static ArrayList<Customer> customerdb = new ArrayList();
    public static ArrayList<Room> roomdb =new ArrayList();
    public static ArrayList<Meal> mealdb =new ArrayList();

    static{
        customerdb.add(new Customer("Kaligu","2001337027832","0767095344","Kaligu@gmail.com","Horana"));
        customerdb.add(new Customer("Tharidu","2055637027832","07675865344","Tharidu@gmail.com","Padukka"));
        customerdb.add(new Customer("Nimal","2078337027832","0777095344","Nimal@gmail.com","Colombo"));
    }
    static{
        roomdb.add(new Room("101","s","Single Room","1-bathroom,1-kitchen","","true"));
        roomdb.add(new Room("102","d","Double Room","2-bathroom,1-kitchen","Kamal","false"));
        roomdb.add(new Room("103","t","Triple Room","3-bathroom,2-kitchen","","true"));
        roomdb.add(new Room("104","s","Single Room","1-bathroom,1-kitchen","","true"));
        roomdb.add(new Room("105","s","Single Room","1-bathroom,1-kitchen","","maintain"));
        roomdb.add(new Room("106","s","Single Room","1-bathroom,1-kitchen","","true"));
        roomdb.add(new Room("107","s","Single Room","1-bathroom,1-kitchen","","true"));
        roomdb.add(new Room("108","s","Single Room","1-bathroom,1-kitchen","","true"));
        roomdb.add(new Room("109","s","Single Room","1-bathroom,1-kitchen","","true"));
        roomdb.add(new Room("110","s","Single Room","1-bathroom,1-kitchen","","true"));
        roomdb.add(new Room("111","s","Single Room","1-bathroom,1-kitchen","","maintain"));
        roomdb.add(new Room("112","s","Single Room","1-bathroom,1-kitchen","Nimal","false"));
        roomdb.add(new Room("113","s","Single Room","1-bathroom,1-kitchen","","true"));
        roomdb.add(new Room("114","s","Single Room","1-bathroom,1-kitchen","","true"));
        roomdb.add(new Room("115","s","Single Room","1-bathroom,1-kitchen","","true"));
        roomdb.add(new Room("116","s","Single Room","1-bathroom,1-kitchen","","true"));
        roomdb.add(new Room("117","s","Single Room","1-bathroom,1-kitchen","","true"));
        roomdb.add(new Room("118","s","Single Room","1-bathroom,1-kitchen","","true"));
        roomdb.add(new Room("119","s","Single Room","1-bathroom,1-kitchen","","true"));
        roomdb.add(new Room("120","s","Single Room","1-bathroom,1-kitchen","","true"));
        roomdb.add(new Room("121","s","Single Room","1-bathroom,1-kitchen","","true"));
        roomdb.add(new Room("122","s","Single Room","1-bathroom,1-kitchen","","true"));
        roomdb.add(new Room("123","s","Single Room","1-bathroom,1-kitchen","","true"));
        roomdb.add(new Room("124","s","Single Room","1-bathroom,1-kitchen","","true"));
        roomdb.add(new Room("125","q","Quad Room","4-bathroom,2-kitchen","Srimal","false"));

    }

    static{
        mealdb.add(new Meal("mixrice","m1","850","mix","large"));
        mealdb.add(new Meal("mixrice","m2","450","mix","Medium"));
        mealdb.add(new Meal("mixrice","m2","250","mix","Small"));
    }
}
