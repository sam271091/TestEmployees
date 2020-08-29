package demo.com.testemployees.api;

import demo.com.testemployees.pojo.EmployeeResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET ("testTask.json")
    Observable<EmployeeResponse> getEmployees();

//    @GET ("testTask.json/{id}/video")
//    Observable<EmployeeResponse> getEmployees(@Path("id") int id, @Query("api_key") int api_key, @Query("lang") int lang);
}
