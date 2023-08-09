package trif.trifrnd.apicallnew.API_Services;

//import com.example.trifrnd_employee_attendance.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginService {
    @FormUrlEncoded
    @POST("data.php?apicall=login")
    Call<String> login(@Field("mobile") String mobile, @Field("password") String password);

}
