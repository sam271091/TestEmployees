package demo.com.testemployees.screens.employees;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import demo.com.testemployees.R;
import demo.com.testemployees.adapters.EmployeeAdapter;
import demo.com.testemployees.api.ApiFactory;
import demo.com.testemployees.api.ApiService;
import demo.com.testemployees.pojo.Employee;
import demo.com.testemployees.pojo.EmployeeResponse;
import demo.com.testemployees.pojo.Speciality;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EmployeeListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewEmployees;
    private EmployeeAdapter adapter;
    private EmployeeViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewEmployees = findViewById(R.id.recyclerViewEmployees);
        adapter = new EmployeeAdapter();

        adapter.setEmployees(new ArrayList<Employee>());

        recyclerViewEmployees.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEmployees.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        viewModel.getEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(@Nullable List<Employee> employees) {
                adapter.setEmployees(employees);
                if (employees != null){
                    for (Employee employee : employees){
                        List<Speciality> specialities = employee.getSpecialty();
                        for (Speciality speciality : specialities){
                            Log.i("Speciality",speciality.getName());
                        }

                    }
                }

            }
        });

        viewModel.getErrors().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(@Nullable Throwable throwable) {
               if (throwable != null){
                   Toast.makeText(EmployeeListActivity.this, "Error", Toast.LENGTH_SHORT).show();
                   viewModel.clearErrors();
               }
            }
        });
        viewModel.loaddata();





    }


}

