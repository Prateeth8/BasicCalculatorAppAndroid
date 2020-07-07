package com.example.calculator; // this is a package or project named as calculator used for creating a particular app
//----------------------------------------------------------------------------------------------------------------------------------------------------------
import androidx.annotation.NonNull;//parameter which returns a value that does not contain the value as null, it is annotation with no attributes
import androidx.annotation.Nullable;// this annotation helps the parameter to return any value including null value
import androidx.appcompat.app.AppCompatActivity;//this is appcompatActivity library or class contains functions compatible with the app
//----------------------------------------------------------------------------------------------------------------------------------------------------------
import android.os.Bundle;// Bundle is to map string keys to parcelable values
import android.os.PersistableBundle;//this is mapping from string keys to values of various types which is restricted only to simple objects
import android.view.View;//basic building block for user interface components responsible for drawing and event handling
import android.widget.Button;// this is widget in android studio like a real button
import android.widget.EditText;// this is a space that displays numbers for calculator app
import android.widget.TextView;// this is a space that displays characters for calculator app
//----------------------------------------------------------------------------------------------------------------------------------------------------------
public class MainActivity extends AppCompatActivity {// AppcompatActivity helps the android studio to create a new activity
                                                     // extends tells the java that mainactivity is a part and example of AppcompatActivity
    private EditText result; // the first edit text is given a variable to store values
    private EditText newNumber; // the second edit text is given a variable to store values
    private TextView displayOperation; // the characters or operands to perform operation are stored here
//*********************************************************************************************************************************************
    // Variables to hold the operands and type of calculations
    private Double operand1 = null; //
    private String pendingOperation = "="; // this used as a main symbol for both calculation and also changing to new values
//*********************************************************************************************************************************************
    private static  final String STATE_PENDING_OPERATION="PendingOperation";//STATE PENDING OPERATION is a variable to store pending operation, this value does not change and remains const
    private static  final String STATE_OPERAND="Operand1";//STATE OPERAND is a variable to store operand 1, this value does not change and remains const
//*********************************************************************************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {//oncreate is called to create activity and it is the best place to put initialization code
        super.onCreate(savedInstanceState);// to tell Dalvik VM to run the code with the existing code in the parent class
                                           // this handles - UI drawing, house cleaning, maintaining the activity and application life cycles
        setContentView(R.layout.activity_main);//this is a layout resource which defines the architecture of UI in an activity

        result = (EditText) findViewById(R.id.result);//finds the view called as edit text from the resource file attached to the current activity
        newNumber = (EditText) findViewById(R.id.newNumber);//finds the view called as edit text from the resource file attached to the current activity
        displayOperation = (TextView) findViewById(R.id.operation);//finds the view called as text view from the resource file attached to the current activity

        Button button0 = (Button) findViewById(R.id.button0);// button 0 in the resource xml file
        Button button1 = (Button) findViewById(R.id.button1);// button 1 in the resource xml file
        Button button2 = (Button) findViewById(R.id.button2);// button 2 in the resource xml file
        Button button3 = (Button) findViewById(R.id.button3);// button 3 in the resource xml file
        Button button4 = (Button) findViewById(R.id.button4);// button 4 in the resource xml file
        Button button5 = (Button) findViewById(R.id.button5);// button 5 in the resource xml file
        Button button6 = (Button) findViewById(R.id.button6);// button 6 in the resource xml file
        Button button7 = (Button) findViewById(R.id.button7);// button 7 in the resource xml file
        Button button8 = (Button) findViewById(R.id.button8);// button 8 in the resource xml file
        Button button9 = (Button) findViewById(R.id.button9);// button 9 in the resource xml file

        Button buttonDot = (Button) findViewById(R.id.buttonDot);// button dot in the resource xml file
        Button buttonEquals = (Button) findViewById(R.id.buttonEquals);// button equal in the resource xml file
        Button buttonDivide = (Button) findViewById(R.id.buttonDivide);// button divide in the resource xml file
        Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);// button multiply in the resource xml file
        Button buttonMinus = (Button) findViewById(R.id.buttonMinus);// button minus in the resource xml file
        Button buttonPlus = (Button) findViewById(R.id.buttonPlus);// button plus in the resource xml file
//*********************************************************************************************************************************************
        View.OnClickListener listener = new View.OnClickListener() { // not called by the user but creates an instance for it
            @Override
            public void onClick(View v) { // this is called when a view is clicked
                Button b = (Button) v;// the function is called when the button is clicked
                newNumber.append(b.getText().toString());// the number is converted and accepted as text and then is returned to the widget variable

            }
        };
 //********************************************************************************************************************************************
        button0.setOnClickListener(listener);// initiated when button 0 is clicked
        button1.setOnClickListener(listener);// initiated when button 1 is clicked
        button2.setOnClickListener(listener);// initiated when button 2 is clicked
        button3.setOnClickListener(listener);// initiated when button 3 is clicked
        button4.setOnClickListener(listener);// initiated when button 4 is clicked
        button5.setOnClickListener(listener);// initiated when button 5 is clicked
        button6.setOnClickListener(listener);// initiated when button 6 is clicked
        button7.setOnClickListener(listener);// initiated when button 7 is clicked
        button8.setOnClickListener(listener);// initiated when button 8 is clicked
        button9.setOnClickListener(listener);// initiated when button 9 is clicked
        buttonDot.setOnClickListener(listener);// initiated when button dot is clicked
//**********************************************************************************************************************************************
        View.OnClickListener opListener = new View.OnClickListener() {// not called by the user but creates an instance for it
            @Override
            public void onClick(View v) {// this is called when a view is clicked
                Button b = (Button) v;// calls the function when the buttton is clicked
                String op = b.getText().toString();
                String value = newNumber.getText().toString();// the character is converted and accepted as text and then is returned to the widget variable
                try {
                    Double doubleValue = Double.valueOf(value);
                    performOperation(doubleValue, op);
                } catch (NumberFormatException e) {
                    newNumber.setText("");// if a decimal . is entered it is an exception hence no number is added to the original number in calculator
                }
                pendingOperation = op;// to assign pending operation as op and op is through onclick
                displayOperation.setText(pendingOperation);// setting a character operand on to the text widget
            }
        };
//************************************************************************************************************************************************
        buttonEquals.setOnClickListener(opListener);// initiated when button equal is clicked
        buttonDivide.setOnClickListener(opListener);// initiated when button divide is clicked
        buttonMultiply.setOnClickListener(opListener);// initiated when button multiply is clicked
        buttonMinus.setOnClickListener(opListener);// initiated when button minus is clicked
        buttonPlus.setOnClickListener(opListener);// initiated when button plus is clicked
//************************************************************************************************************************************************
        Button buttonNeg = (Button) findViewById(R.id.buttonNeg);

        buttonNeg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String value= newNumber.getText().toString();
                if(value.length()==0){
                    newNumber.setText("-");
                }else{
                    try{
                        Double doubleValue =Double.valueOf(value);
                        doubleValue *= -1;
                        newNumber.setText(doubleValue.toString());
                    }catch(NumberFormatException e){
                        newNumber.setText("");
                    }
                }
            }
        });
        Button buttonClear= (Button) findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double val=0.0;
                result.getText().clear();
                result.setText(val.toString());
                operand1=val;
                newNumber.getText().clear();
                displayOperation.setText("");
            }
        });
    }

 //***********************************************************************************************************************************************
        @Override
        protected void onSaveInstanceState(@NonNull Bundle outState) {
            outState.putString(STATE_PENDING_OPERATION,pendingOperation);
            if(operand1 !=null)
            {
                outState.putDouble(STATE_OPERAND,operand1);
            }
            super.onSaveInstanceState(outState);
    }
 //************************************************************************************************************************************************

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        pendingOperation=savedInstanceState.getString(STATE_PENDING_OPERATION);
        operand1=savedInstanceState.getDouble(STATE_OPERAND);
        displayOperation.setText(pendingOperation);
    }
//*************************************************************************************************************************************************
    private void performOperation(Double value, String operation) {

        if (null == operand1) {
            operand1 = value;
        } else {

            if (pendingOperation.equals("=")) {
                pendingOperation = operation;
            }
            switch (pendingOperation) {
                case "=":
                    operand1 = value;
                    break;
                case "/":
                    if (value == 0) {
                        operand1 = 0.0;
                    } else {
                        operand1 /= value;
                    }
                    break;
                case "*":
                    operand1 *= value;
                    break;
                case "-":
                    operand1 -= value;
                    break;
                case "+":
                    operand1 += value;
                    break;
            }
        }
 //****************************************************************************************************************************************************
        result.setText(operand1.toString());
        newNumber.setText("");
    }
}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
