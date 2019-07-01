package sg.edu.rp.c346.demomyprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
EditText etName,etGPA;
RadioGroup rgGender;
Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.editText);
        etGPA = findViewById(R.id.editText2);
        rgGender = findViewById(R.id.radiogroup);
        bt = findViewById(R.id.button);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = etName.getText().toString();
                Float gpaNum = Float.parseFloat(etGPA.getText().toString());
                int id = rgGender.getCheckedRadioButtonId();
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putFloat("gpa",gpaNum);
                prefEdit.putString("name",strName);
                prefEdit.putInt("gender",id);
                prefEdit.commit();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String msg = prefs.getString("name","No name!");
        Float gpa = prefs.getFloat("gpa",0);
        int gender = prefs.getInt("gender",0);
        etName.setText(msg);
        etGPA.setText(""+gpa);
        rgGender.check(gender);
    }

    @Override
    protected void onPause() {
        super.onPause();

        String strName = etName.getText().toString();
        Float gpaNum = Float.parseFloat(etGPA.getText().toString());
        int id = rgGender.getCheckedRadioButtonId();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor prefEdit = prefs.edit();
    prefEdit.putFloat("gpa",gpaNum);
        prefEdit.putString("name",strName);
        prefEdit.putInt("gender",id);
        prefEdit.commit();
    }
}
