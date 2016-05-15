package nanodegree.android.udacity.myappportfolio;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nanodegree.android.udacity.myappportfolio.Adapter.MoviePosterAdapter;
import nanodegree.android.udacity.myappportfolio.Model.Movie;
import nanodegree.android.udacity.myappportfolio.Model.ResultPage;
import nanodegree.android.udacity.myappportfolio.Services.TheMovieDbService;

public class PopularMovies extends AppCompatActivity {

  private List<Movie> mMovies = new ArrayList<>();
  private ArrayAdapter<Movie> mMovieAdapter = null;
  private final String LOG_TAG = getClass().getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_popular_movies);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

//    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//    fab.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View view) {
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//            .setAction("Action", null).show();
//      }
//    });
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    mMovieAdapter = new MoviePosterAdapter(this, mMovies);

    GridView gridview = (GridView) findViewById(R.id.movie_poster_grid_view);
    gridview.setAdapter(mMovieAdapter);

    MovieAsyncTask task = new MovieAsyncTask();
    task.execute();
  }

  class MovieAsyncTask extends AsyncTask<Void, Void, List<Movie>> {

    @Override
    protected List<Movie> doInBackground(Void... params) {
      try {
        ResultPage resultPage = new TheMovieDbService().getMovies();
        return resultPage.getResults();
      } catch (IOException e) {
        Log.e(LOG_TAG, e.getLocalizedMessage());
        return null;
      }
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
      mMovies.clear();
      mMovies.addAll(movies);
      mMovieAdapter.notifyDataSetChanged();
      //mMovieAdapter.setNotifyOnChange(true);
    }
  }

}
