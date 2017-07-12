package dafla.dafla;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private Button bbirth;
    private EditText tbirth;
    private TextView login;
    private int day, month, year, rightNow;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        login = (TextView) findViewById(R.id.cancel_button);
        bbirth = (Button) findViewById(R.id.bbirth);
        tbirth = (EditText) findViewById(R.id.tbirth);
        bbirth.setOnClickListener(this);
        login.setOnClickListener(this);

        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


/*
        final ActionBar abar = getSupportActionBar();
        View viewActionBar = getLayoutInflater().inflate(R.layout.app_bar, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(//Center the textview in the ActionBar !
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        TextView textviewTitle = (TextView) viewActionBar.findViewById(R.id.actionbar_textview);
        textviewTitle.setText(getText(R.string.title_activity_signup));
        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(true);
        abar.setDisplayHomeAsUpEnabled(true);
        abar.setIcon(R.color.white);*/
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bbirth:
                final Calendar c= Calendar.getInstance();
                day=c.get(Calendar.DAY_OF_MONTH);
                month=c.get(Calendar.MONTH);
                year=c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tbirth.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                    }
                }
                        ,day,month,year);
                datePickerDialog.show();
                break;
            case R.id.cancel_button:
                startActivity(new Intent(SignUpActivity.this, LogInActivity.class));
                finish();
                break;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(new Intent(SignUpActivity.this, LogInActivity.class));
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}