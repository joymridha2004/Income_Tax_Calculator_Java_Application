package com.example.incometaxcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Main_Activity extends AppCompatActivity {
    Button TaxDetailsButton, startButton, restartButton;
    TextInputEditText EnterIncomeTextInputEditT;
    TextView resultTV;
    String Income = null;
    Float Tax = 0.0f, Income_Float = 0.0f;
    Float tax1 = 0.0f, Num = 0.0f;

    /*-----------------Details DialogBox-----------------*/

    ImageButton DetailCloseIV;
    TextView DetailsRow1DialogBoxTV, DetailsRow2DialogBoxTV, DetailsRow3DialogBoxTV;
    Button BackButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*-----------------Dialog-----------------*/

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.details_dialog_box);

        /*-----------------Hooks-----------------*/

        TaxDetailsButton = findViewById(R.id.Tax_Details_Button);
        EnterIncomeTextInputEditT = findViewById(R.id.Enter_Income_Text_Input_EditT);
        resultTV = findViewById(R.id.result_TV);
        startButton = findViewById(R.id.start_Button);
        restartButton = findViewById(R.id.restart_Button);

        /*-----------------Details Dialog-----------------*/

        DetailCloseIV = dialog.findViewById(R.id.Detail_Close_IV);
        DetailsRow1DialogBoxTV = dialog.findViewById(R.id.Details_Row1_DialogBox_TV);
        DetailsRow2DialogBoxTV = dialog.findViewById(R.id.Details_Row2_DialogBox_TV);
        DetailsRow3DialogBoxTV = dialog.findViewById(R.id.Details_Row3_DialogBox_TV);
        BackButton = dialog.findViewById(R.id.Back_Button);

        /*-----------------Set On Click Listener on Start button-----------------*/

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Tax = 0.0f;
                Income_Float = 0.0f;

                if (!EnterIncomeTextInputEditT.getText().toString().isEmpty()) {
                    Income = EnterIncomeTextInputEditT.getText().toString();
                    Income_Float = Float.parseFloat(Income);
                }
                if (Income != null) {
                    if (Income_Float <= 250000) {
                        Tax = Tax + 0.0f;
                    } else if (Income_Float > 250000 && Income_Float <= 500000) {
                        Tax = Tax + 0.05f * (Income_Float - 250000f);
                    } else if (Income_Float > 500000 && Income_Float <= 1000000) {
                        Tax = Tax + 0.05f * (500000f - 250000f);
                        Tax = Tax + 0.2f * (Income_Float - 500000f);
                    } else if (Income_Float > 1000000) {
                        Tax = Tax + 0.05f * (500000f - 250000f);
                        Tax = Tax + 0.2f * (1000000f - 500000f);
                        Tax = Tax + 0.3f * (Income_Float - 1000000f);
                    }
                    resultTV.setText(Tax + "");
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
                Income_Float = 0.0f;
                EnterIncomeTextInputEditT.setText(null);
                Income = null;

            }
        });

        /*-----------------Set On Click Listener on Details -----------------*/

        TaxDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();
                Tax = 0.0f;
                tax1 = 0.0f;
                Num = 0.0f;
                if (EnterIncomeTextInputEditT.getText().toString().isEmpty()) {
                    DetailsRow1DialogBoxTV.setText("Sorry Bro");
                    DetailsRow2DialogBoxTV.setText("No Income");
                    DetailsRow3DialogBoxTV.setText("No Tax");
                } else {
                    if (Income_Float <= 250000) {
                        Tax = Tax + 0.0f;
                        DetailsRow1DialogBoxTV.setText("Your Income");
                        DetailsRow2DialogBoxTV.setText("Under of");
                        DetailsRow3DialogBoxTV.setText("No Tax Line");
                    } else if (Income_Float > 250000 && Income_Float <= 500000) {
                        Tax = Tax + 0.05f * (Income_Float - 250000f);
                        DetailsRow1DialogBoxTV.setText("Your Income");
                        DetailsRow2DialogBoxTV.setText("Tax is");
                        DetailsRow3DialogBoxTV.setText(Tax + "");
                    } else if (Income_Float > 500000 && Income_Float <= 1000000) {
                        Tax = Tax + 0.05f * (500000f - 250000f);
                        DetailsRow1DialogBoxTV.setText("500000-250000 Rs on Tax " + Tax);
                        tax1 = (Tax + 0.2f * (Income_Float - 500000f)) - Tax;
                        Tax = Tax + 0.2f * (Income_Float - 500000f);
                        Num = (Income_Float - 500000);
                        DetailsRow2DialogBoxTV.setText(Num + " Rs on Tax " + tax1);
                        DetailsRow3DialogBoxTV.setText(null);
                    } else if (Income_Float > 1000000) {
                        Tax = Tax + 0.05f * (500000f - 250000f);
                        DetailsRow1DialogBoxTV.setText("500000-250000 Rs on Tax " + Tax);
                        Tax = Tax + 0.2f * (1000000f - 500000f);
                        DetailsRow2DialogBoxTV.setText("1000000-500000 Rs on Tax 100000");
                        tax1 = (Tax + 0.3f * (Income_Float - 1000000f)) - Tax;
                        Tax = Tax + 0.3f * (Income_Float - 1000000f);
                        Num = (Income_Float - 1000000);
                        DetailsRow3DialogBoxTV.setText(Num + " Rs on Tax is " + tax1);

                    }

                }

            }
        });

        /*-----------------Set On Click Listener on Details Close Button -----------------*/

        DetailCloseIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        /*-----------------Set On Click Listener on Details Back Button -----------------*/

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}