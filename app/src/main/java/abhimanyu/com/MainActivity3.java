package abhimanyu.com;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity3 extends AppCompatActivity {
    private DatePicker datePicker;
    private TextView ageTextView, currentDateTextView, birthdayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        datePicker = findViewById(R.id.datePicker);
        ageTextView = findViewById(R.id.ageTextView);
        currentDateTextView = findViewById(R.id.currentDateTextView);
        birthdayText = findViewById(R.id.jumpingText);

        ageTextView.addTextChangedListener(textWatcher);

        Date currentDate = Calendar.getInstance().getTime();
        DateFormat dateFormat = DateFormat.getDateInstance();
        String formattedDate = dateFormat.format(currentDate);
        currentDateTextView.setText(formattedDate);

    }

    public void calculateAge(View view) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth(); // Calendar uses 0-indexed months
        int year = datePicker.getYear();

        Calendar dob = Calendar.getInstance();
        dob.set(year, month, day);

        Calendar today = Calendar.getInstance();

        int years = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        int months = today.get(Calendar.MONTH) - dob.get(Calendar.MONTH);
        int days = today.get(Calendar.DAY_OF_MONTH) - dob.get(Calendar.DAY_OF_MONTH);

        if (months < 0 || (months == 0 && days < 0)) {
            years--;
            months += 12;
            if (days < 0) {
                months--;
                days += today.getActualMaximum(Calendar.DAY_OF_MONTH);
            }
        }
        String ageString = years + " Years, " + months + " Months, " + days + " Days";
        ageTextView.setText(ageString);

    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth(); // Calendar uses 0-indexed months
            int year = datePicker.getYear();

            Calendar dob = Calendar.getInstance();
            dob.set(year, month, day);

            Calendar today = Calendar.getInstance();

            int years = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
            int months = today.get(Calendar.MONTH) - dob.get(Calendar.MONTH);
            int days = today.get(Calendar.DAY_OF_MONTH) - dob.get(Calendar.DAY_OF_MONTH);

            String text = ageTextView.getText().toString();

            if (!text.isEmpty()) {
                if (years > 0) {
                    if (months == 0 && days == 0) {

                        birthdayText.setVisibility(View.VISIBLE);
                        jump(birthdayText);
                    } else {
                        birthdayText.setVisibility(View.INVISIBLE);

                    }
                } else {
                    birthdayText.setVisibility(View.INVISIBLE);
                }
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private void jump(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", 0f, -50f, 0f);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(500);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }
}
