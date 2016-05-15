package nanodegree.android.udacity.myappportfolio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void showToastMessage(View view) {

    if (view.getId() == R.id.popular_movies_button_view) {
      Intent popularMoviesIntent = new Intent(this, PopularMovies.class);
      //popularMoviesIntent.setData(Uri.parse(fileUrl));
      startActivity(popularMoviesIntent);
    } else {
      Context context = getApplicationContext();
      CharSequence text = "This button will launch my " + ((Button) view).getText() + " app";
      int duration = Toast.LENGTH_SHORT;

      Toast toast = Toast.makeText(context, text, duration);
      toast.show();
    }
  }
}
