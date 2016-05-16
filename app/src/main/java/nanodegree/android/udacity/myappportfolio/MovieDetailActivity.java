package nanodegree.android.udacity.myappportfolio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nanodegree.android.udacity.myappportfolio.Model.Movie;

public class MovieDetailActivity extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail_movie);
//    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
//    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    Intent i = this.getIntent();

    String MOVIE_CLICKED = "MOVIE_TITLE_CLICKED";
    Movie movie = (Movie) i.getExtras().get(MOVIE_CLICKED);
    setMovieDetailOnView(movie);
  }

  private void setMovieDetailOnView(Movie movie) {


    ImageView imageView = (ImageView) findViewById(R.id.movie_poster_image_view);
    Picasso.with(this)
        .load("http://image.tmdb.org/t/p/w500/" + movie.getPosterPath() + "?api_key=" +
            BuildConfig.MY_MOVIE_DB_API_KEY)
        .into(imageView);

    TextView movieTitleView = (TextView)findViewById(R.id.movie_name_text_view);
    assert movieTitleView != null;
    movieTitleView.setText(createBoldHtmlForString("Title : ", movie.getTitle()));

    TextView userRatingTextView = (TextView)findViewById(R.id.user_rating_text_view);
    assert userRatingTextView != null;
    userRatingTextView.setText(createBoldHtmlForString("Rating : ", movie.getVoteAverage() + ""));

    TextView releaseDateTextView = (TextView)findViewById(R.id.release_date_text_view);
    assert releaseDateTextView != null;
    releaseDateTextView.setText(createBoldHtmlForString("Released On : ", movie.getReleaseDate()));

    TextView overviewTextView = (TextView)findViewById(R.id.overview_text_view);
    assert overviewTextView != null;
    overviewTextView.setText(createBoldHtmlForString("Synopsis : ", movie.getOverview()));
  }

  private Spanned createBoldHtmlForString(String subHeading, String data) {
    return Html.fromHtml("<b>" + subHeading + "</b> " + data);
  }
}
