package com.example.davidsanta.mirestaurante;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.davidsanta.mirestaurante.BD.ControladoraBD;

import java.util.Calendar;

public class ReservaActivity extends AppCompatActivity {


    private Calendar calendar;
    private TextView dateView, timeView, doc;
    private int year, month, day, hour, minute;
    private EditText editCantidad;
    private String documento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);


        //capturamos el doc del cliente
        documento = getIntent().getStringExtra("documento");
        doc=(TextView) findViewById(R.id.textView6);
        doc.setText(documento);

        dateView = (TextView) findViewById(R.id.textFecha);
        timeView = (TextView) findViewById(R.id.textHora);
        editCantidad = (EditText) findViewById(R.id.editCantidad);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
        showDate(year, month + 1, day);
        showTime(hour, minute);
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    @SuppressWarnings("deprecation")
    public void setTime(View view) {
        showDialog(998);
    }

    @Override
    @SuppressWarnings("deprecation")
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }else if(id == 998){
            return new TimePickerDialog(this, myTimeListener, hour, minute, true);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };

    private TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
            // arg1 = hour
            // arg2 = minute
            showTime(arg1, arg2);
        }
    };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("-")
                .append(month).append("-").append(year));
    }

    private void showTime(int hour, int minute) {
        timeView.setText(new StringBuilder().append(hour).append(":").append(minute));
    }

    public void agregarReserva(View view){
        String dateTime = dateView.getText() + " " + timeView.getText();
        int personas = Integer.parseInt(editCantidad.getText().toString());
        int docCliente = Integer.parseInt(documento);
        Reserva r = new Reserva(docCliente, personas, dateTime);

        ControladoraBD bd = new ControladoraBD(this);
        bd.openForWrite();
        bd.insertReserva(r);
        bd.close();

        //mostar un alert o toast
        Context context = getApplicationContext();
        CharSequence text = "Reserva Realizada";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();



        Intent intento = new Intent(getApplicationContext(),MainActivity.class );
        startActivity(intento);
    }
}
