package cocktail.mixthis;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


/**
 * This class represent the Main activity and supports new AppCompat
 * activity and fragment updates.
 */
public class SearchDrinks extends AppCompatActivity {
    //  Declaring the user text entry EditText, which will be used for searches
    private EditText user_Drink;

    /**
     *    On Creation of SearchDrinks activity,
     *    we set the content view to the appropriate layout.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_drinks);
        user_Drink = findViewById(R.id.drinksearchbar);
    }
}
