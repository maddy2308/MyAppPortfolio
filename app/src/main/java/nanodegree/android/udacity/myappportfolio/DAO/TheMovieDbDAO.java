package nanodegree.android.udacity.myappportfolio.DAO;




import nanodegree.android.udacity.myappportfolio.Model.ResultPage;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheMovieDbDAO {

  @GET("movie/popular")
  Call<ResultPage> discoverPopularMovies(@Query("api_key") String apiKey);

  @GET("movie/top_rated")
  Call<ResultPage> discoverTopRatedMovies(@Query("api_key") String apiKey);
}
