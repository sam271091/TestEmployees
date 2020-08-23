package demo.com.testemployees.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import demo.com.testemployees.pojo.Employee;

@Database(entities = Employee.class,version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase database;
    private static final String DB_NAME = "employees.db";
    private static final Object LOCK = new Object();

    public static AppDatabase getInstance(Context context){
       synchronized (LOCK){
           if (database == null){
               database = Room.databaseBuilder(context,AppDatabase.class,DB_NAME).fallbackToDestructiveMigration().build();
           }

        return database;
       }
    }


    public abstract EmployeeDao employeeDao();

}
