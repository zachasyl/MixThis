package cocktail.mixthis;

import android.content.Intent;
import android.graphics.BitmapFactory;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.net.URL;
import java.io.IOException;



import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;


/**
 * This class represent the Main activity and supports new AppCompat
 * activity and fragment updates.
 */
public class SearchDrinks extends AppCompatActivity {
    //  Declaring the user text entry EditText, which will be used for searches
    private EditText user_Drink;
    private String actual_url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=";
    private String string_format;
    private Handler textHandler = new Handler();
    Button search;

    /**
     *    On Creation of SearchDrinks activity,
     *    we set the content view to the appropriate layout.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_drinks);
        user_Drink = findViewById(R.id.drinksearchbar);
//        search = (findViewById(R.id.drinkhandler));
//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                string_format = actual_url + user_Drink.getText().toString();
//                runOnRunnableThread(view);
//            }
//        });
    }


    public void runOnRunnableThread(View view){
        runnableThread runnableThread = new runnableThread();
        new Thread(runnableThread).start();
    }
    class runnableThread implements Runnable {
        String one_ingredient = "";
        String all_ingreditents = "";


        @Override
        public void run() {
            JSONObject jObject;

            try {
                URL url = new URL(string_format);
                String resp = NetworkUti.httpResponse(url);

                jObject = new JSONObject(resp);

                Integer i = 1;
                while (true) {
                    if ((jObject.getJSONArray("drinks").getJSONObject(0).get("strDrink")).toString().equalsIgnoreCase(user_Drink.getText().toString())) {

                        String ingredient = "strIngredient" + i;
                        one_ingredient = jObject.getJSONArray("drinks").getJSONObject(0).get(ingredient).toString();

                        all_ingreditents += one_ingredient + "\n";

                        i += 1;
                    } else {
                        break;
                    }
                }
            }catch (IOException | JSONException e) {
            }
                textHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        TextView result_view = (TextView) findViewById(R.id.result_textview);

                        if (all_ingreditents.length() > 0) {
                            result_view.setText((CharSequence) all_ingreditents);
                        } else {
                            // Either there were multiple drinks but the first was
                            // not an exact match, or there was no match.
                            result_view.setText("No Drink Found.");
                        }
                    }

                });
            }
        }


    //used in place of onclick for now, onclick set in search_dirnks.xml
    public void handler(View view)   {
        string_format = actual_url + user_Drink.getText().toString();
        runOnRunnableThread(view);

    }

}
