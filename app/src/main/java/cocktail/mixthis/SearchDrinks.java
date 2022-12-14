package cocktail.mixthis;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
    private Handler textHandler = new Handler();
    private String actual_url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=";

    private EditText user_Drink;
    private String string_format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.search_drinks);
        user_Drink = (EditText)findViewById(R.id.drinksearchbar);
    }

    /**
     * This thread is called by the callWebserviceButtonHandler on click.
     *
     */
    public void runOnRunnableThread(View view){
        runnableThread runnableThread = new runnableThread();
        new Thread(runnableThread).start();
    }

    class runnableThread implements Runnable{
        String one_ingredient = "";
        String all_ingreditents = "";
        private Bitmap bitmap = null;
        private ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        TextView result_view = (TextView)findViewById(R.id.result_textview);

        @Override
        public void run(){
            // Reset the text view to empty upon new search.
            result_view.setText("");
            // Progress visibility should happen separately and before/while loading.
            // Thread handler will first set progressBar to visible.
            textHandler.post(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.VISIBLE);
                }
            });

            JSONObject jObject;
            try {
                URL url = new URL(string_format);
                String resp = NetworkUti.httpResponse(url);
                jObject = new JSONObject(resp);
                Integer i = 1;

                // So long as the first drink (the most closely related drink) the API
                // returns from its search is IDENTICAL to what we are looking for....
                // The idea here is that if we search margarita and the API returns "margarita"
                // and also "lime margarita," we would look at the first object and see if it
                // matches our search
                // for "margarita. If you type "lime margarita," the API's first return would be
                // "lime margarita"  This is really similar as while (true) the loop will only end
                // after we break off when there are no more ingredients.
                while ((jObject.getJSONArray("drinks").getJSONObject(0).get("strDrink")).toString().equalsIgnoreCase(user_Drink.getText().toString())){
                    String ingredient = "strIngredient" + i;
                    // we are getting the ingredient i
                    one_ingredient = jObject.getJSONArray("drinks").getJSONObject(0).get(ingredient).toString();
                    // When there is no longer an ingredient we have finished.
                    if (one_ingredient.equals("null")) {
                        break;
                    }
                    // newline after each ingredient
                    all_ingreditents += one_ingredient + "\n";

                    //parsing JSON for image of found drink
                    bitmap = BitmapFactory.decodeStream((InputStream) new URL(
                            jObject.getJSONArray("drinks").getJSONObject(0).get("strDrinkThumb").toString()
                    ).getContent());

                    i += 1;

                }
            } catch (IOException | JSONException e) {

                e.printStackTrace();
            }
            //Thread handler will now set image and text views.
            textHandler.post(new Runnable() {
                @Override
                public void run() {

                    final ImageView drinkImage = (ImageView)findViewById(R.id.imageView);

                    if (all_ingreditents.length() >0 ) {
                        result_view.setText((CharSequence) all_ingreditents);
                        drinkImage.setImageBitmap(bitmap);

                    }
                    else{
                        // Either there were multiple drinks but the first was
                        // not an exact match,  the first drink was not a match, or there were no
                        // drinks.
                        result_view.setText("No Drink Found.");
                        drinkImage.setImageBitmap(null);

                    }
                    // Image isn't found so let user know nothing to load.
                    progressBar.setVisibility(View.INVISIBLE);


                }
            });
        }
    }
    // we run the thread after this button click. For now this onclick is specified in search_drinks.xml
    public void callWebserviceButtonHandler(View view)   {

        string_format = actual_url + user_Drink.getText().toString();
        runOnRunnableThread(view);
    }
}
