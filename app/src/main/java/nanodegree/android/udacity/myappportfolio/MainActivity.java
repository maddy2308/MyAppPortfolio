package nanodegree.android.udacity.myappportfolio;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    Button button = (Button)findViewById(view.getId());

    Context context = getApplicationContext();
    assert button != null;
    CharSequence text = "This button will launch my " + button.getText() + " app";
    int duration = Toast.LENGTH_SHORT;

    Toast toast = Toast.makeText(context, text, duration);
    toast.show();
  }
}
