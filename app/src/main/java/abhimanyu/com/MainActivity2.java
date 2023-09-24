package abhimanyu.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private EditText editBaseAmount, editCGSTRate, editSGSTRate;
    private TextView textTotalTax, textCGST, textSGST;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editBaseAmount = findViewById(R.id.editBaseAmount);
        editCGSTRate = findViewById(R.id.editCGSTRate);
        editSGSTRate = findViewById(R.id.editSGSTRate);

        textTotalTax = findViewById(R.id.textTotalTax);
        textCGST = findViewById(R.id.textCGST);
        textSGST = findViewById(R.id.textSGST);

        Button calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateGST();
            }
        });
    }

    private void calculateGST() {
        double baseAmount = Double.parseDouble(editBaseAmount.getText().toString());
        double cgstRate = Double.parseDouble(editCGSTRate.getText().toString());
        double sgstRate = Double.parseDouble(editSGSTRate.getText().toString());

        double cgstAmount = (baseAmount * cgstRate) / 100;
        double sgstAmount = (baseAmount * sgstRate) / 100;
        double totalTax = cgstAmount + sgstAmount;

        textTotalTax.setText("Total Tax: " + totalTax);
        textCGST.setText("CGST: " + cgstAmount);
        textSGST.setText("SGST: " + sgstAmount);
    }
}