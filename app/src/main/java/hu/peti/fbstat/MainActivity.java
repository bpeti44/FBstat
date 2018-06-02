package hu.peti.fbstat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mNameText;
    private ImageView mImageView;
    private RatingBar mRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameText = findViewById(R.id.nameText);
        mImageView = findViewById(R.id.imageView);
        mRatingBar = findViewById(R.id.ratingBar);



        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        HTTPCalls.getID();
        Log.d("MSG", "Main token: " + HTTPCalls.token);


    }
}
