package nl.twapenaar.poulesimulation.services;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;

public class Utility {
    /**
     * makes a console log with the same tag
     */
    public static void Log(String logMsg){
        Log.d("PouleSimulation", logMsg);
    }

    /**
     * return a random value between 0 and your range
     */
    public static int RandomInt(int highRange){
        return RandomInt(0, highRange);
    }

    /**
     * return a random value between given low value and given high value
     */
    public static int RandomInt(int lowRange, int highRange){
        return ThreadLocalRandom.current().nextInt(lowRange, highRange+1);
    }


    /**
     * generates a generic TextView
     */
    public static TextView genericTextView(int id, String text, Context context){
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        TextView textview = new TextView(context);
        textview.setId(id);
        textview.setLayoutParams(layoutParams);
        textview.setTextColor(Color.BLACK);
        textview.setTextSize(14);
        textview.setText(text);

        return textview;
    }
    /**
     * generates a generic TextView with a background
     */
    public static TextView genericTextView(int id, String text, Context context, int backgroundColor){
        TextView textView = genericTextView(id, text, context);
        textView.setBackgroundColor(backgroundColor);
        return textView;
    }
}
