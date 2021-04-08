package com.vullnetlimani.myapplication.activites;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.udojava.evalex.Expression;
import com.vullnetlimani.myapplication.R;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Calculator extends AppCompatActivity {

    private EditText inputBox;
    private TextView resultTextView;
    private String rezultati_string = "";
    private String display = "";
    private ImageView btn_clear_step;
    private List<Character> validOperators = Arrays.asList('+', '-', '\u00F7', 'x', '%');
    private boolean isDotAdded = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);

        inputBox = findViewById(R.id.inputBox);
        resultTextView = findViewById(R.id.resultTextView);
        btn_clear_step = findViewById(R.id.btn_clear_step);


        btn_clear_step.setColorFilter(Color.parseColor("#1976D2"));
        btn_clear_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteNumber();
            }
        });


    }


    public void onClickNumber(View view) {

        Button button = (Button) view;

        String btn_next = button.getText().toString();

        ConsoleMessage("onNumberLog", "Button with number - " + btn_next + " is clicked.");

        if (!rezultati_string.equals("")) {
            resultTextView.setText("");
            inputBox.setText("");
            rezultati_string = "";
        }

        if (btn_next.contains(".") && isDotAdded) {
            ConsoleMessage("onNumberLog", "Dot is Added");
        } else {
            display = display + button.getText();

            if (btn_next.contains(".")) {

                isDotAdded = true;

                if (endsWithOperator() || getInput().isEmpty())
                    display = "0" + display;

            }

        }

        appendToLast(display);
        display = "";

    }

    public void onOperatorClick(View view) {
        Button button = (Button) view;

        char operatoriFundit = button.getText().charAt(button.getText().length() - 1);

        if (!rezultati_string.equals("")) {

            if (validOperators.contains(operatoriFundit)) {
                resultTextView.setText("");
                inputBox.setText("");
                display = "";
                display = rezultati_string + button.getText();
                appendToLast(display);
                display = "";
                rezultati_string = "";
            } else {
                display += button.getText();
                appendToLast(display);
                display = "";
            }

        } else {

            display += button.getText();

            if (endsWithOperator()) {
                replace(display);
            } else {

                appendToLast(display);
            }

            display = "";
        }

        if (isDotAdded)
            isDotAdded = false;
    }

    private void replace(String str) {
        inputBox.getText().replace(getInput().length() - 1, getInput().length(), str);
    }

    private boolean endsWithOperator() {
        return getInput().endsWith("+") || getInput().endsWith("-") || getInput().endsWith("/") || getInput().endsWith("x") || getInput().endsWith("%");
    }

    public void onEqualClick(View view) {
        String input = getInput();

        ConsoleMessage("onClickOperatorLog", "input = " + input);

        if (!endsWithOperator() && input.length() > 0) {

            if (input.contains("x")) {
                input = input.replaceAll("x", "*");
            }

            if (input.contains("\u00F7")) {
                input = input.replaceAll("\u00F7", "/");
            }

            Expression expression = new Expression(input);

            BigDecimal bigDecimal = expression.eval();

            double rezultati = bigDecimal.doubleValue();

            ConsoleMessage("onClickOperatorLog", "rezultati = " + rezultati);

            if (isNumberRounded(rezultati)) {
                int roundedValue = (int) Math.round(rezultati);
                rezultati_string = String.valueOf(roundedValue);
            } else {
                rezultati_string = String.valueOf(rezultati);
            }

            resultTextView.setText(rezultati_string);


            // Manuall Code
            //  ConsoleMessage("onClickOperatorLog", "input = " + input);
//
//            try {
//
//                String extractOperator = input.replaceAll("[\\d.]", "");
//                extractOperator = String.valueOf(extractOperator.charAt(extractOperator.length() - 1));
//
//                String[] Numbers = input.split("[\\-/+*%]");
//
//                double rezultati;
//
//                if (input.startsWith("-")) {
//
//                    Numbers[1] = "-" + Numbers[1];
//
//                    ConsoleMessage("onClickOperatorLog", "Numbers[0] = " + Numbers[1]);
//                    ConsoleMessage("onClickOperatorLog", "extractOperator = " + extractOperator);
//                    ConsoleMessage("onClickOperatorLog", "Numbers[1] = " + Numbers[2]);
//
//                    rezultati = operate(Numbers[1], Numbers[2], extractOperator);
//
//                } else {
//                    ConsoleMessage("onClickOperatorLog", "Numbers[0] = " + Numbers[0]);
//                    ConsoleMessage("onClickOperatorLog", "extractOperator = " + extractOperator);
//                    ConsoleMessage("onClickOperatorLog", "Numbers[1] = " + Numbers[1]);
//
//                    rezultati = operate(Numbers[0], Numbers[1], extractOperator);
//                }
//
//                ConsoleMessage("onClickOperatorLog", "rezultati = " + rezultati);
//
//                if (isNumberRounded(rezultati)) {
//                    int roundedValue = (int) Math.round(rezultati);
//                    rezultati_string = String.valueOf(roundedValue);
//                } else {
//                    rezultati_string = String.valueOf(rezultati);
//                }
//
//                if (rezultati_string.startsWith("-0.0"))
//                    rezultati_string = rezultati_string.replaceAll("-", "");
//
//                resultTextView.setText(rezultati_string);
//            } catch (Exception e) {
//                resultTextView.setText("");
//            }

        } else resultTextView.setText("");

        System.out.println(rezultati_string);
    }

    private double operate(String a, String b, String cp) {
        switch (cp) {
            case "+":
                return Double.parseDouble(a) + Double.parseDouble(b);
            case "-":
                return Double.parseDouble(a) - Double.parseDouble(b);
            case "*":
                return Double.parseDouble(a) * Double.parseDouble(b);
            case "/":
                return Double.parseDouble(a) / Double.parseDouble(b);
            case "%":
                return (Double.parseDouble(a) / 100) * Double.parseDouble(b);
            default:
                return -1;
        }
    }

    private boolean isNumberRounded(double number) {
        return number % 1 == 0;
    }

    private String getInput() {
        return inputBox.getText().toString();
    }

    public void onClearClick(View view) {
        inputBox.getText().clear();
        resultTextView.setText("");

        if (isDotAdded)
            isDotAdded = false;
    }

    private void deleteNumber() {
        if (getInput().length() > 0) {

            if (inputBox.getText().toString().endsWith(".") && isDotAdded)
                isDotAdded = false;

            inputBox.getText().delete(getInput().length() - 1, getInput().length());

        }

    }


    public void onNegativeClick(View view) {
    }


    private void ConsoleMessage(String TAG, String message) {
        Log.d(TAG, message);
    }

    private void appendToLast(String string) {
        this.inputBox.getText().append(string);
    }

}
