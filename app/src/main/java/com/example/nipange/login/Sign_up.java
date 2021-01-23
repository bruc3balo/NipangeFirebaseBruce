package com.example.nipange.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.nipange.R;
import com.example.nipange.models.User;
import com.example.nipange.landlord.dashboard_land;
import com.example.nipange.landlord.new_property_launch;
import com.google.android.material.snackbar.Snackbar;
import com.rilixtech.widget.countrycodepicker.Country;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;
import com.treebo.internetavailabilitychecker.InternetAvailabilityChecker;
import com.treebo.internetavailabilitychecker.InternetConnectivityListener;
import com.whiteelephant.monthpicker.MonthPickerDialog;
//import com.mukesh.countrypicker.Country;
//import com.mukesh.countrypicker.CountryPicker;
//import com.mukesh.countrypicker.CountryPickerListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import SharedPrefManager.SharedPrefManager;
import Volley.URLS;

public class Sign_up extends AppCompatActivity implements InternetConnectivityListener {
    Snackbar snackbar;
    EditText name;
    EditText password;
    EditText email;
    EditText date_of_birth;
    Spinner country;
    EditText mobile_number;
    //EditText phone_number;
    EditText id_Number;
    //EditText website;
    RadioButton genderMale;
    RadioButton genderFemale;
    RadioGroup gender;
    EditText address;
    Button button2;
    CheckBox checkBoxTc;
    CountryCodePicker ccp;
    InternetAvailabilityChecker mInternetAvailabilityChecker;
    Boolean isConnected;
    EditText date_of_birth_1;
    ArrayList <String> countries;
    TextView  Log_In;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


//        getCountries(this);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        name = findViewById(R.id.editText3);
        password = findViewById(R.id.editText6);
        email = findViewById(R.id.editText5);
        //country = findViewById(R.id.editText9);
        genderMale = findViewById(R.id.radioButton);
        genderFemale = findViewById(R.id.radioButton2);
        mobile_number = findViewById(R.id.editText11);
        //phone_number= findViewById(R.id.editText12);
        id_Number = findViewById(R.id.editText13);
        //website = findViewById(R.id.editText14);
        date_of_birth = findViewById(R.id.editText7);
        date_of_birth_1 = findViewById(R.id.editText0);
        button2 = findViewById(R.id.button2);
        address = findViewById(R.id.editText15);
        gender = findViewById(R.id.radiogender);
        checkBoxTc = findViewById(R.id.checkBox);
        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        Log_In = findViewById(R.id.textViewL);

        Log_In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Log_in.class);
                startActivity(intent);
            }
        });


        mInternetAvailabilityChecker = InternetAvailabilityChecker.init(this);



        mInternetAvailabilityChecker = InternetAvailabilityChecker.getInstance();
        mInternetAvailabilityChecker.addInternetConnectivityListener(this);



        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected(Country selectedCountry) {
                Toast.makeText(Sign_up.this, "Updated " + selectedCountry.getName(), Toast.LENGTH_SHORT).show();

            }
        });



        mobile_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mobile_number.getText().toString().startsWith("0")) {
                    mobile_number.setText("");
                }
            }
        });

        awesomeValidation.addValidation(this, R.id.editText3, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();
            }
        });



        date_of_birth_1.setTransformationMethod(null);
        date_of_birth_1.setGravity(Gravity.CENTER_HORIZONTAL);











        date_of_birth_1.setEnabled(true);


        date_of_birth.setGravity(Gravity.CENTER_HORIZONTAL);




//     date_of_birth_1.setOnClickListener(new View.OnClickListener() {

//        @Override
//        public CharSequence getTransformation(CharSequence source, View view) {
//            return source;
//        }
//    });
//
//            @Override
//            public void onClick(View v) {
//                MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(
//                        Sign_up.this,
//                        new MonthPickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(int selectedMonth, int selectedYear) {
//                                String selectedDate = ++selectedMonth + "/" + selectedYear;
//                                date_of_birth.setText(selectedDate);
//                            }
//                        },
//                        Calendar.getInstance().get(Calendar.YEAR),
//                        Calendar.getInstance().get(Calendar.MONTH)
//                );
//
//                builder.setActivatedMonth(Calendar.getInstance().get(Calendar.MONTH))
//                        .setOnMonthChangedListener(new MonthPickerDialog.OnMonthChangedListener() {
//                            @Override
//                            public void onMonthChanged(int selectedMonth) {
//                                selectedMonth++;
//                                date_of_birth.setText(String.valueOf(selectedMonth));
//                            }
//                        })
//                        .setOnYearChangedListener(new MonthPickerDialog.OnYearChangedListener() {
//                            @Override
//                            public void onYearChanged(int year) {
//                                String text = date_of_birth.getText().toString();
//                                if(!date_of_birth.getText().toString().trim().contains("/")) {
//                                    text = text + "/" + String.valueOf(year);
//                                } else {
//                                    text = text.substring(0, text.indexOf("/")) + "/" + String.valueOf(year);
//                                }
//                                date_of_birth.setText(text);
//                            }
//                        })
//                        .build()
//                        .show();
//            }
//        });






















        date_of_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(
                        Sign_up.this,
                        new MonthPickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(int selectedMonth, int selectedYear) {
                                String selectedDate = ++selectedMonth + "/" + selectedYear;
                                date_of_birth.setText(selectedDate);
                            }
                        },
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH)
                );

                builder.setActivatedMonth(Calendar.getInstance().get(Calendar.MONTH))
                        .setOnMonthChangedListener(new MonthPickerDialog.OnMonthChangedListener() {
                            @Override
                            public void onMonthChanged(int selectedMonth) {
                                selectedMonth++;
                                date_of_birth.setText(String.valueOf(selectedMonth));
                            }
                        })
                        .setOnYearChangedListener(new MonthPickerDialog.OnYearChangedListener() {
                            @Override
                            public void onYearChanged(int year) {
                                String text = date_of_birth.getText().toString();
                                if(!date_of_birth.getText().toString().trim().contains("/")) {
                                    text = text + "/" + String.valueOf(year);
                                } else {
                                    text = text.substring(0, text.indexOf("/")) + "/" + String.valueOf(year);
                                }
                                date_of_birth.setText(text);
                            }
                        })
                        .build()
                        .show();
            }

//                Calendar calendar = Calendar.getInstance();
//                int year = calendar.get(Calendar.YEAR);
//                int month = calendar.get(Calendar.MONTH);
//                int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog dt = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener(){
//
//                    @Override
//public void onDateSet(DatePicker datePicker, int i,int i1, int i2){
//
//                        i1+=1;
//                        date_of_birth.setText(String.valueOf(i)+ "/" + String.valueOf(i1)  + "/" +  String.valueOf(i2));
//                    }
//
//                },
//                year,
//                month,
//                day);
//        dt.getDatePicker().setMaxDate(calendar.getTimeInMillis());
//        dt.show();
//
//            }
        });


        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (((CheckBox) v).isChecked())
                    Toast.makeText(getBaseContext(), "Accepted T/Cs and Privacy Policy", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getBaseContext(), "Declined T/Cs and Privacy Policy", Toast.LENGTH_SHORT).show();

            }
        });



//        CountryPicker picker = CountryPicker.newInstance("Select Country");
//        picker.show(getSupportFragmentManager(), "COUNTRY_PICKER");
//        picker.setListener(new CountryPickerListener() {
//            @Override
//            public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
//                // Implement your code here
//            }
//        });

//        CountryPicker picker = CountryPicker.newInstance("Select Country");  // dialog title
//        picker.setListener(new CountryPickerListener() {
//            @Override
//            public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
//                // Implement your code here
//            }
//        });
//        picker.show(getSupportFragmentManager(), "COUNTRY_PICKER");
//
//        List<Country> countries = Country.getAllCountries(); //List of all countries
//        Country[] countries1 = Country.COUNTRIES; //Array of all countries sorted by ISO code
//       // Country country = Country.getCountryFromSIM(context); //Get user country based on SIM card
        //Country country = Country.getCountryByLocale(locale); //Get country based on Locale
        //Country country = Country.getCountryByName(countryName); //Get country by its name


        //String name = country.getName();
       // String code = country.getCode();
        //int flag = country.getFlag();  // returns android resource id of flag or -1, if none is associated
        //String dialCode = country.getDialCode();

        //country.loadFlagByCode();  // attempts to associate flag to country based on its ISO code. Used if you create your own instance of Country.class




        snackbar = Snackbar.make(name, "Please connect to the Internet", Snackbar.LENGTH_INDEFINITE);
    }

    void getCountries(final Context context) {
        countries = new ArrayList<>();
        StringRequest countriesStringRequest = new StringRequest(
                Request.Method.GET,
                "https://restcountries.eu/rest/v2/all",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                countries.add(array.getJSONObject(i).getString("name"));
                            }

//                            ArrayAdapter countryAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, countries);
//                            countryAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//                            country.setAdapter(countryAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );

        Volley.newRequestQueue(context).add(countriesStringRequest);
    }

    Boolean isEmail(EditText emailEditText) {
        String emailText = emailEditText.getText().toString();
        return (!TextUtils.isEmpty(emailText) && Patterns.EMAIL_ADDRESS.matcher(emailText).matches());
    }

    Boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public String getGender() {
        if(gender.getCheckedRadioButtonId() == R.id.radioButton) {
            return "Male";
        }
        return "Female";
    }

    void checkDataEntered() {
        if(!isConnected) {
            Toast t = Toast.makeText(this, "Connect to the INTERNET!!!!!", Toast.LENGTH_SHORT);
            t.show();
            return;
        }

        if (isEmpty(name)) {
            Toast t = Toast.makeText(this, "You must enter Full Names to register!", Toast.LENGTH_SHORT);
            t.show();
            return;
        }
// else if(!name.matches("[a-zA-Z"))

        if (isEmail(email) == false) {
            email.setError("Enter valid email!");
            email.requestFocus();
            return;
        }

        if (isEmpty(password)) {
            password.setError("Input Password to register!");
            password.requestFocus();
            return;
        }

        if (isEmpty(date_of_birth)) {
            date_of_birth.setError("Input Month and Year of Birth to register!");
            date_of_birth.requestFocus();
            return;
        }

        if(isEmpty(date_of_birth_1)){
            date_of_birth_1.setError("Input the Date of Birth to Register!");
            date_of_birth_1.requestFocus();
            return;
        }

        if(Integer.parseInt(date_of_birth_1.getText().toString()) > 31 || Integer.parseInt(date_of_birth_1.getText().toString()) < 1) {
            date_of_birth_1.setError("The date should be between 1 and 31");
            date_of_birth_1.requestFocus();
            return;
        }



        if (isEmpty(mobile_number)) {
            mobile_number.setError("Input Mobile Number to register!");
            mobile_number.requestFocus();
            return;
        }

        if (isEmpty(id_Number)) {
            id_Number.setError("Input ID Number to register!");
            id_Number.requestFocus();
            return;


        }

//        if (isEmpty(address)) {
//            address.setError("Input Address to register!");
//            address.requestFocus();
//            return;
//
//
//        }



        if(!checkBoxTc.isChecked()){
            Toast.makeText(getBaseContext(), "Kindly Accept the T/Cs", Toast.LENGTH_SHORT).show();
            checkBoxTc.requestFocus();
            return;
        }



//        checkBoxTc.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//            public void onCheckedChanged(Boolean isChecked) {
//                if ( isChecked ) {
//                    checkBoxTc.setError(null);
//                } else {
//                    checkBoxTc.setError("BLEH BLEH BLEH");
//                }
//            }
//        });



        if (awesomeValidation.validate()) {
            Toast.makeText(this, "Validation Successful", Toast.LENGTH_LONG).show();
            signUP(this);
            //process the data further
        }


    }

    public void signUP(final Context v) {
        final String id_NumberText = id_Number.getText().toString();
        final String emailText = email.getText().toString();
        final String passwordText = password.getText().toString();
        final String nameText = name.getText().toString();
        final String date_of_birthText = date_of_birth.getText().toString();
//        final String countryText = country.getSelectedItem().toString();
        final String mobile_numberText = mobile_number.getText().toString();
        //final String phone_numberText = phone_number.getText().toString();
        //final String websiteText = website.getText().toString();
        final String addressText = address.getText().toString();
        final String genderText = getGender();
        final String date_of_birth_1Text = date_of_birth_1.getText().toString();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URLS.SIGNUP_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("response: ", response);
                            JSONObject object = new JSONObject(response);
                            String message = object.getString("message");
                            Boolean error = object.getBoolean("error");
                            if (!error) {
                                User user = new User(
                                        object.getInt("id_Number"),
                                        object.getString("name"),
                                        object.getString("email"),
                                        object.getString("country"),
                                        object.getString("gender"),
                                        //object.getString("phone_number"),
                                        object.getString("mobile_number"),
                                        object.getString("date_of_birth"),
                                        //object.getString("date_of_birth_1")
                                        //object.getString("website"),
                                        object.getString("address_line")

                                );

                                SharedPrefManager.getInstance(getApplicationContext()).userLogIn(user);

//                                    progressBar.setVisibility(View.GONE);
//                                    Intent intent = new Intent(getApplicationContext(), My_Wallet_07.class);
                                    Toast.makeText(getBaseContext(), "Successfully Signed Up", Toast.LENGTH_SHORT).show();
//                                    startActivity(intent);
//                                    finish();

                                AlertDialog dialog = new AlertDialog.Builder(v)
                                        .setTitle("Confirmation")
                                        .setMessage("Would you like to add property now ?")
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent = new Intent(Sign_up.this, new_property_launch.class);
                                                startActivity(intent);
                                            }
                                        })
                                        .setNegativeButton("Later", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent = new Intent(Sign_up.this, dashboard_land.class );
                                                startActivity(intent);
                                            }
                                        })
                                        .show();

                                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
//                                positiveButton.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        // Toast.makeText(MainActivity.this, "Not closing", Toast.LENGTH_SHORT).show();
//                                        //dialog.dismiss();
//
//
//
//                                    }
//                                });



                            } else {
                                    email.requestFocus();
                                    email.setError("Email already exists");
                                    return;
                            }

                        } catch (JSONException exception) {
//                            progressBar.setVisibility(View.GONE);
                            exception.printStackTrace();
                            Log.e("JSONException", exception.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("NetworkError: ", error.toString());
//                        progressBar.setVisibility(View.GONE);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_Number", id_NumberText);
                params.put("email", emailText);
                params.put("password", passwordText);
                params.put("name", nameText);
                params.put("date_of_birth", date_of_birthText);
//                params.put("country", countryText);
                params.put("mobile_number", mobile_numberText);
                //params.put("phone_number", phone_numberText);
params.put("date_of_birth_1",date_of_birth_1Text);
                //params.put("website", websiteText);
                params.put("address_line", addressText);
                params.put("gender", genderText);




                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }


    @Override
    public void onInternetConnectivityChanged(boolean isConnected) {
        this.isConnected = isConnected;
        setViewsEnabled(isConnected);

        if(!isConnected) {
            snackbar.show();
        } else {
            snackbar.dismiss();
        }
    }

    @Override
    protected void onDestroy() {

        mInternetAvailabilityChecker.removeInternetConnectivityChangeListener(this);
        super.onDestroy();

    }

    void setViewsEnabled(Boolean enabled) {
        name.setEnabled(enabled);
        password.setEnabled(enabled);
        email.setEnabled(enabled);
        date_of_birth.setEnabled(enabled);
        mobile_number.setEnabled(enabled);
        id_Number.setEnabled(enabled);
        genderMale.setEnabled(enabled);
        genderFemale.setEnabled(enabled);
        gender.setEnabled(enabled);
        address.setEnabled(enabled);
        checkBoxTc.setEnabled(enabled);
        ccp.setEnabled(enabled);
        date_of_birth_1.setEnabled(enabled);

    }

    private DatePickerDialog createDialogWithoutDateField() {
        DatePickerDialog dpd = new DatePickerDialog(this, null, 2014, 1, 24);
        try {
            java.lang.reflect.Field[] datePickerDialogFields = dpd.getClass().getDeclaredFields();
            for (java.lang.reflect.Field datePickerDialogField : datePickerDialogFields) {
                if (datePickerDialogField.getName().equals("mDatePicker")) {
                    datePickerDialogField.setAccessible(true);
                    DatePicker datePicker = (DatePicker) datePickerDialogField.get(dpd);
                    java.lang.reflect.Field[] datePickerFields = datePickerDialogField.getType().getDeclaredFields();
                    for (java.lang.reflect.Field datePickerField : datePickerFields) {
                        Log.i("test", datePickerField.getName());
                        if ("mDaySpinner".equals(datePickerField.getName())) {
                            datePickerField.setAccessible(true);
                            Object dayPicker = datePickerField.get(datePicker);
                            ((View) dayPicker).setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
        }
        return dpd;
    }

}
