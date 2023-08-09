package trif.trifrnd.apicallnew.Classes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import trif.trifrnd.apicallnew.API_Services.EmployeeApi;
import trif.trifrnd.apicallnew.Fragement_Activity.HomeFragement;
import trif.trifrnd.apicallnew.Fragement_Activity.ProfileFragment;
import trif.trifrnd.apicallnew.Models.Employee;
import trif.trifrnd.apicallnew.R;

public class DashboardActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    String mobile = MyApplication.mobile;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setTitle("Dashboard");

        fetchUserName();

        Toolbar toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragement()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_home){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragement()).commit();
        }
        else if (itemId == R.id.nav_profile) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
        } else if (itemId == R.id.nav_logout) {
            Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }




    private void fetchUserName() {
        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apip.trifrnd.com/portal/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create a Retrofit service for the API interface
//        AttendanceApi attendanceService = retrofit.create(AttendanceApi.class);
        EmployeeApi employeeApi = retrofit.create(EmployeeApi.class);

        // Make the API call
        Call<List<Employee>> call = employeeApi.getAllEmployees();
        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (response.isSuccessful()){
                    List<Employee> employees = response.body();
                    // Find the Employee with the matching mobile number
                    Employee targetEmployee = null;
                    for (Employee employee : employees) {
                        if (employee.getMobile().equals(mobile)) {
                            targetEmployee = employee;
                            break;
                        }
                    }
                    TextView nameTextView = findViewById(R.id.username);
                    nameTextView.setText(targetEmployee.getFname());



                }

            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {

            }
        });



    }


}