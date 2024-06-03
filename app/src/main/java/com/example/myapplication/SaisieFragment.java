package com.example.myapplication;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SaisieFragment extends Fragment {

    private EditText etNom, etPrenom, etDateNaissance, etTelephone, etEmail;
    private CheckBox cbSport, cbMusique, cbLecture;
    private RadioButton rbSynchroniserOui, rbSynchroniserNon;
    private Button btnSoumettre;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saisie, container, false);

        etNom = view.findViewById(R.id.etNom);
        etPrenom = view.findViewById(R.id.etPrenom);
        etDateNaissance = view.findViewById(R.id.etDateNaissance);
        etTelephone = view.findViewById(R.id.etTelephone);
        etEmail = view.findViewById(R.id.etEmail);
        cbSport = view.findViewById(R.id.cbSport);
        cbMusique = view.findViewById(R.id.cbMusique);
        cbLecture = view.findViewById(R.id.cbLecture);
        rbSynchroniserOui = view.findViewById(R.id.rbSynchroniserOui);
        rbSynchroniserNon = view.findViewById(R.id.rbSynchroniserNon);
        btnSoumettre = view.findViewById(R.id.btnSoumettre);

        etDateNaissance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        btnSoumettre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("nom", etNom.getText().toString());
                    bundle.putString("prenom", etPrenom.getText().toString());
                    bundle.putString("dateNaissance", etDateNaissance.getText().toString());
                    bundle.putString("telephone", etTelephone.getText().toString());
                    bundle.putString("email", etEmail.getText().toString());
                    bundle.putBoolean("sport", cbSport.isChecked());
                    bundle.putBoolean("musique", cbMusique.isChecked());
                    bundle.putBoolean("lecture", cbLecture.isChecked());
                    bundle.putBoolean("synchroniser", rbSynchroniserOui.isChecked());

                    Navigation.findNavController(v).navigate(R.id.action_saisieFragment_to_confirmationFragment, bundle);
                } else {
                    Toast.makeText(getActivity(), "Veuillez remplir tous les champs correctement", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, month, dayOfMonth);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        etDateNaissance.setText(dateFormat.format(selectedDate.getTime()));
                    }
                },
                year, month, day);

        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private boolean validateInputs() {
        boolean isValid = true;

        if (TextUtils.isEmpty(etNom.getText())) {
            etNom.setError("Nom requis");
            isValid = false;
        }

        if (TextUtils.isEmpty(etPrenom.getText())) {
            etPrenom.setError("Prénom requis");
            isValid = false;
        }

        if (TextUtils.isEmpty(etDateNaissance.getText())) {
            etDateNaissance.setError("Date de naissance requise");
            isValid = false;
        }

        if (TextUtils.isEmpty(etTelephone.getText())) {
            etTelephone.setError("Numéro de téléphone requis");
            isValid = false;
        }

        if (TextUtils.isEmpty(etEmail.getText()) || !isValidEmail(etEmail.getText().toString())) {
            etEmail.setError("Adresse mail invalide");
            isValid = false;
        }

        return isValid;
    }

    private boolean isValidEmail(CharSequence email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
