package demo.com.testemployees.converters;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import demo.com.testemployees.pojo.Speciality;

public class Converter {

    @TypeConverter
    public String ListSpecialityToString(List<Speciality> specialities){

        return new Gson().toJson(specialities);

    }

    @TypeConverter
    public List<Speciality> StringToListSpeciality(String specialityAsString){
        Gson gson = new Gson();

        ArrayList objects = gson.fromJson(specialityAsString,ArrayList.class);

        ArrayList<Speciality> specialities = new ArrayList<>();

        for (Object o : objects){
            specialities.add(gson.fromJson(o.toString(),Speciality.class));
        }

        return specialities;

    }
}
