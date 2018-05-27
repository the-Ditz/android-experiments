package ditzlern.diceeremade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button rollButton = findViewById(R.id.rollButton);

        final ImageView leftDice = findViewById(R.id.left_dice);

        final ImageView rightDice = findViewById(R.id.right_dice);

        final int[] diceArray = {
                R.drawable.dice1,
                R.drawable.dice2,
                R.drawable.dice3,
                R.drawable.dice4,
                R.drawable.dice5,
                R.drawable.dice6
        };

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Dicee", "Ze button has been pressed");

                Random randomNumberGenerator = new Random();

                leftDice.setImageResource(randomNumberGenerator.nextInt(6));
                rightDice.setImageResource(randomNumberGenerator.nextInt(6));
            }
        });
    }
}
