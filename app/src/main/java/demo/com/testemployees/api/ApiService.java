package demo.com.testemployees.api;

import demo.com.testemployees.pojo.EmployeeResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET ("testTask.json")
    Observable<EmployeeResponse> getEmployees();
}
