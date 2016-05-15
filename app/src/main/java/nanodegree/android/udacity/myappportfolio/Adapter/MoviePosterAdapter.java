package nanodegree.android.udacity.myappportfolio.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import nanodegree.android.udacity.myappportfolio.BuildConfig;
import nanodegree.android.udacity.myappportfolio.Model.Movie;
import nanodegree.android.udacity.myappportfolio.MovieDetail;
import nanodegree.android.udacity.myappportfolio.R;

public class MoviePosterAdapter extends ArrayAdapter<Movie> {

  private final String MOVIE_TITLE_CLICKED = "MOVIE_TITLE_CLICKED";

  public MoviePosterAdapter(Context context, List<Movie> posters) {
    super(context, 0, posters);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    final Movie movie = getItem(position);
    if (convertView == null) {
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_poster, parent,
          false);
    }

    ImageView imageView = (ImageView) convertView.findViewById(R.id.movie_thumbnail_image_view);
//    TextView textView = (TextView) convertView.findViewById(R.id.movie_title_text_view);

    Picasso.with(getContext())
        .load("http://image.tmdb.org/t/p/w342/" + movie.getPosterPath() + "?api_key=" +
            BuildConfig.MY_MOVIE_DB_API_KEY)
            //.error(R.drawable.user_placeholder_error)
            //.resize(50, 50)
            //.centerCrop()
        .into(imageView);

//    textView.setText(movie.getTitle());

    convertView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent movieDetailIntent = new Intent(getContext(), MovieDetail.class);
        movieDetailIntent.putExtra(MOVIE_TITLE_CLICKED, (Serializable) movie);
        getContext().startActivity(movieDetailIntent);
      }
    });

    return convertView;
  }
}