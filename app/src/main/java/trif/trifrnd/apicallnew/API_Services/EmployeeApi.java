package trif.trifrnd.apicallnew.API_Services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import trif.trifrnd.apicallnew.Models.Employee;

public interface EmployeeApi {

    @GET("data.php?apicall=readall")
    Call<List<Employee>> getAllEmployees();

//    @FormUrlEncoded
//    @POST("data.php?apicall=read")
//    Call<List<Employee>> getUsername(@Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("data.php?apicall=read")
    Call<String> getUsername(@Field("mobile") String mobile);


}
