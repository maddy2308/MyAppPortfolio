package nanodegree.android.udacity.myappportfolio;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nanodegree.android.udacity.myappportfolio.Adapter.MoviePosterAdapter;
import nanodegree.android.udacity.myappportfolio.Model.Movie;
import nanodegree.android.udacity.myappportfolio.Model.ResultPage;
import nanodegree.android.udacity.myappportfolio.Services.TheMovieDbService;

public class MovieListActivity extends AppCompatActivity {

  private List<Movie> mMovies = new ArrayList<>();
  private ArrayAdapter<Movie> mMovieAdapter = null;
  private final String LOG_TAG = getClass().getSimpleName();
  private String lastPreference = "";
  /**
   * ATTENTION: This was auto-generated to implement the App Indexing API.
   * See https://g.co/AppIndexing/AndroidStudio for more information.
   */
  private GoogleApiClient client;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list_movie);
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
    // ATTENTION: This was auto-generated to implement the App Indexing API.
    // See https://g.co/AppIndexing/AndroidStudio for more information.
    client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
  }

  @Override
  public void onStart() {
    super.onStart();
    // ATTENTION: This was auto-generated to implement the App Indexing API.
    // See https://g.co/AppIndexing/AndroidStudio for more information.
    client.connect();
    updateMovieList();
    // ATTENTION: This was auto-generated to implement the App Indexing API.
    // See https://g.co/AppIndexing/AndroidStudio for more information.
    Action viewAction = Action.newAction(
        Action.TYPE_VIEW, // TODO: choose an action type.
        "MovieList Page", // TODO: Define a title for the content shown.
        // TODO: If you have web page content that matches this app activity's content,
        // make sure this auto-generated web page URL is correct.
        // Otherwise, set the URL to null.
        Uri.parse("http://host/path"),
        // TODO: Make sure this auto-generated app URL is correct.
        Uri.parse("android-app://nanodegree.android.udacity.myappportfolio/http/host/path")
    );
    AppIndex.AppIndexApi.start(client, viewAction);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.movie_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      startActivity(new Intent(this, SettingsActivity.class));
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private void updateMovieList() {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    String movieFetchPreference = prefs.getString(getString(R.string.pref_movie_fetch_key),
        getString(R.string.pref_movie_popularity));
    if (!(lastPreference.equals(movieFetchPreference))) {
      lastPreference = movieFetchPreference;
      if (isConnectedToInternet(getApplicationContext())) {
        new MovieAsyncTask().execute(movieFetchPreference);
      } else {
        Toast.makeText(this, "Please check your Network Connections, and try back again", Toast.LENGTH_SHORT).show();
      }
    }
  }

  @Override
  public void onStop() {
    super.onStop();

    // ATTENTION: This was auto-generated to implement the App Indexing API.
    // See https://g.co/AppIndexing/AndroidStudio for more information.
    Action viewAction = Action.newAction(
        Action.TYPE_VIEW, // TODO: choose an action type.
        "MovieList Page", // TODO: Define a title for the content shown.
        // TODO: If you have web page content that matches this app activity's content,
        // make sure this auto-generated web page URL is correct.
        // Otherwise, set the URL to null.
        Uri.parse("http://host/path"),
        // TODO: Make sure this auto-generated app URL is correct.
        Uri.parse("android-app://nanodegree.android.udacity.myappportfolio/http/host/path")
    );
    AppIndex.AppIndexApi.end(client, viewAction);
    client.disconnect();
  }


  private class MovieAsyncTask extends AsyncTask<String, Void, List<Movie>> {

    @Override
    protected List<Movie> doInBackground(String... params) {
      try {
        ResultPage resultPage = new TheMovieDbService().getMovies(params[0]);
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

  public Boolean isConnectedToInternet(Context mContext) {

    NetworkInfo info = ((ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE))
        .getActiveNetworkInfo();
    if (info == null || !info.isConnected())
    {
      return false;
    }
    if (info.isRoaming()) {
      // here is the roaming option you can change it if you want to
      // disable internet while roaming, just return false
      return true;
    }

    return true;

  }
}
