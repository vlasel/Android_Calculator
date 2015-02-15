package calc.vlas.htp.by.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by _guest on 04.02.2015.
 */
public class MainActivity extends Activity {

    @InjectView(R.id.operand1)
    EditText mOperand1;

    @InjectView(R.id.operand2)
    EditText mOperand2;

    @InjectView(R.id.result)
    TextView mResult;

    @InjectView(R.id.rb_group)
    RadioGroup mOperation;

    @InjectView(R.id.bt_calc)
    Button mCalculate;

    private final String STATE_RESULT_KEY = "result_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mResult.setText("");
    }

    @OnClick(R.id.bt_calc)
    void btnCalcAction() {

        if (TextUtils.isEmpty(mOperand1.getText()) || TextUtils.isEmpty(mOperand2.getText())) {
            Toast.makeText(this, getString(R.string.error_empty_argument), Toast.LENGTH_SHORT).show();
            return;
        }

        String operand1 = mOperand1.getText().toString();
        String operand2 = mOperand2.getText().toString();

        double op1 = Double.parseDouble(operand1);
        double op2 = Double.parseDouble(operand2);

        mResult.setText(getCalculatedResult(mOperation, op1, op2));
    }

    private String getCalculatedResult(RadioGroup pOperation, double op1, double op2) {
        String result;
        switch (pOperation.getCheckedRadioButtonId()) {
            case R.id.rb_add: {
                result = String.valueOf(op1 + op2);
                break;
            }
            case R.id.rb_sub: {
                result = String.valueOf(op1 - op2);
                break;
            }
            case R.id.rb_div: {
                if (op2 == 0) {
                    result = "";
                    Toast.makeText(this, getString(R.string.error_divide_to_zero), Toast.LENGTH_SHORT).show();
                } else {
                    result = String.valueOf(op1 / op2);
                }
                break;
            }
            case R.id.rb_mult: {
                result = String.valueOf(op1 * op2);
                break;
            }
            default: {
                result = "";
                break;
            }
        }
        return result;
    }

    //----------------------- Save-Restore -------------------------------------------
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(STATE_RESULT_KEY, mResult.getText());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mResult.setText(savedInstanceState.getCharSequence(STATE_RESULT_KEY));
    }
    //-----------------------/ Save-Restore -------------------------------------------

}





