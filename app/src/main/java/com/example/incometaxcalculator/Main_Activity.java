package com.example.incometaxcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Main_Activity extends AppCompatActivity {
    Button TaxDetailsButton, startButton, restartButton;
    TextInputEditText EnterIncomeTextInputEditT;
    TextView resultTV;
    String Income = null;
    Float Income_Double = 0.0f, Tax = 0.0f;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*-----------------Hooks-----------------*/

        TaxDetailsButton = findViewById(R.id.Tax_Details_Button);
        EnterIncomeTextInputEditT = findViewById(R.id.Enter_Income_Text_Input_EditT);
        resultTV = findViewById(R.id.result_TV);
        startButton = findViewById(R.id.start_Button);
        restartButton = findViewById(R.id.restart_Button);

        /*-----------------Set On Click Listener on Start button-----------------*/

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!EnterIncomeTextInputEditT.getText().toString().isEmpty()) {
                    Income = EnterIncomeTextInputEditT.getText().toString();
                    Income_Double = Float.parseFloat(Income);
                }
                if (Income != null) {
                    if (Income_Double <= 250000) {
                        Tax = Tax + 0.0f;
                    } else if (Income_Double > 250000 && Income_Double <= 500000) {
                        Tax = Tax + 0.05f * (Income_Double - 250000f);
                    } else if (Income_Double > 500000 && Income_Double <= 1000000) {
                        Tax = Tax + 0.05f * (500000f - 250000f);
                        Tax = Tax + 0.2f * (Income_Double - 500000f);
                    } else if (Income_Double > 1000000) {
                        Tax = Tax + 0.05f * (500000f - 250000f);
                        Tax = Tax + 0.2f * (1000000f - 500000f);
                        Tax = Tax + 0.3f * (Income_Double - 1000000f);
                    }
                    resultTV.setText(Tax + "");
                    Tax = 0.0f;
                    Income_Double = 0.0f;
                } else {
                    Toast.makeText(getApplicationContext(), "Please Enter Income", Toast.LENGTH_SHORT).show();
                }

            }
        });

        /*-----------------Set On Click Listener on Restart button-----------------*/

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTV.setText(null);
                Tax = 0.0f;
                Income_Double = 0.0f;
                EnterIncomeTextInputEditT.setText(null);
                Income = null;

            }
        });
    }
}