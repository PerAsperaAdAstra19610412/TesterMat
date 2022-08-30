package ynv.nigmus.testermat;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.preference.EditTextPreference;

public class EditNumPreference extends EditTextPreference {

    // Add some preferences, which can be used later for checking
    //private Integer mPasswordMinSize = 6;
    private static final int MAXSIZE = 5;
    private static final int MAXIMUM_INT_DEF = 65536;
    private static final int MINIMUM_INT_DEF = 2;
    private static final String MAXIMUM_TEX_DEF = "65536";
    private static final String MINIMUM_TEX_DEF = "2";
    private  Integer x = 0;


    public EditNumPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialization();
    }

    public EditNumPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialization();
    }

    public EditNumPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialization();
    }

    public EditNumPreference(Context context) {
        super(context);
        initialization();
    }

    private void initialization(){
        // Set Dialog button text
        // this.setNegativeButtonText("RETURN");
        // this.setPositiveButtonText("CHECK");

        this.setOnBindEditTextListener(new OnBindEditTextListener() {
            @Override
            public void onBindEditText(@NonNull EditText editText) {
                // Put field parameters here
                //editText.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            }
        });
    }


    @Override
    public CharSequence getSummary() {
        super.getSummary();
        return this.getText();
    }

    @Override
    public void setText(String text) {

        if ( text.length() > MAXSIZE ) { text = MAXIMUM_TEX_DEF; // Чтобы небло переполнения int
        }
        else {
                int intParseIntText = Integer.parseInt(text);

                if ( (text.isEmpty()) || (intParseIntText < MINIMUM_INT_DEF) ){
                    text = MINIMUM_TEX_DEF;
                }

                if ( intParseIntText > MAXIMUM_INT_DEF) {
                        text = MAXIMUM_TEX_DEF;
                }

        }
        super.setText(text);
    }

}