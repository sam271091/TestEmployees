package demo.com.testemployees.screens.employees;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import demo.com.testemployees.R;
import demo.com.testemployees.adapters.EmployeeAdapter;
import demo.com.testemployees.api.ApiFactory;
import demo.com.testemployees.api.ApiService;
import demo.com.testemployees.pojo.Employee;
import demo.com.testemployees.pojo.EmployeeResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EmployeeListActivity extends AppCompatActivity implements EmployeesListView{

    private RecyclerView recyclerViewEmployees;
    private EmployeeAdapter adapter;
    private EmployeeListPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewEmployees = findViewById(R.id.recyclerViewEmployees);
        adapter = new EmployeeAdapter();

        adapter.setEmployees(new ArrayList<Employee>());

        recyclerViewEmployees.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEmployees.setAdapter(adapter);

        presenter = new EmployeeListPresenter(this);
        presenter.loaddata();




    }

    @Override
    public void showData(List<Employee> employees) {
        adapter.setEmployees(employees);
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    //    public void showData(List<Employee> employees){
//        adapter.setEmployees(employees);
//    }
//
//    public void showError(){
//        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
//    }
    
    @Override
    protected void onDestroy() {
        presenter.disposeDisposable();
        super.onDestroy();
    }
}

