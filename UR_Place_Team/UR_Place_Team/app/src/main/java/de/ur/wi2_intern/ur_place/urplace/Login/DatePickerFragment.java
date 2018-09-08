package de.ur.wi2_intern.ur_place.urplace.Login;

import java.util.Calendar;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;


@SuppressLint("ValidFragment")
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    EditText textBirthdate;
    public DatePickerFragment(View view){
        textBirthdate = (EditText)view;
    }
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current date as the default date in the dialog
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);

    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        //show to the selected date in the text box
        String date, monthS, dayS;
        if((month+1) < 10){
            monthS = "0"+(month+1);
        }else{
            monthS = ""+(month+1);
        }
        if(day < 10){
            dayS = "0"+day;
        }else{
            dayS = ""+day;
        }
        date = year+"-"+monthS+"-"+dayS;

        textBirthdate.setText(date);
    }



}
