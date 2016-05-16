package nanodegree.android.udacity.myappportfolio.Services;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import nanodegree.android.udacity.myappportfolio.BuildConfig;
import nanodegree.android.udacity.myappportfolio.DAO.TheMovieDbDAO;
import nanodegree.android.udacity.myappportfolio.Model.ResultPage;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TheMovieDbService {


  private final String BASE_URL = "http://api.themoviedb.org/3/";
  private final String LOG_TAG = getClass().getSimpleName();

  private final Gson gson = new GsonBuilder()
      .setDateFormat("yyyy-MM-dd")
      .excludeFieldsWithoutExposeAnnotation()
      .create();

  private Retrofit retrofit = new Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create(gson))
      .build();

  private TheMovieDbDAO theMovieDbDAO = retrofit.create(TheMovieDbDAO.class);

  public ResultPage getMovies(String preference) throws IOException {

    // vote_average.desc
    // popularity.desc
    Call<ResultPage> call = theMovieDbDAO.discoverMovies(BuildConfig.MY_MOVIE_DB_API_KEY, preference);
    Response<ResultPage> response = call.execute();
    Log.d(LOG_TAG, String.valueOf(response.body()));
    return response.body();
  }

//  public static void main(String[] args) throws IOException {
//    new TheMovieDbService().getMovies();
//  }
}
