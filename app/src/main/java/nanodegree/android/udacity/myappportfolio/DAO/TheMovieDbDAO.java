package nanodegree.android.udacity.myappportfolio.DAO;




import nanodegree.android.udacity.myappportfolio.Model.ResultPage;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheMovieDbDAO {

  @GET("discover/movie")
  Call<ResultPage> discoverMovies(@Query("api_key") String apiKey, @Query("sort_by") String sortBy);
}
