package cocktail.mixthis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * This class represent the Main activity and supports new AppCompat
 * activity and fragment updates.
 */
public class MainActivity extends AppCompatActivity {
    Button mixDrink;


/**
  *    On Creation of main activity, we set the content view to the appropriate layout.
  */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mixDrink = (Button) findViewById(R.id.MixDrink);

        /**
         * Button will navigate us to the correct activity, SearchDrinks class.
         */
        mixDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchDrinks.class);
                startActivity(intent);
            }
        });
    }
}