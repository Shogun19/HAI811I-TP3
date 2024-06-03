package com.example.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

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

        btnSoumettre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    // Collect the data and pass it to the next fragment
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
                    Toast.makeText(getActivity(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private boolean validateInputs() {
        return !TextUtils.isEmpty(etNom.getText()) &&
                !TextUtils.isEmpty(etPrenom.getText()) &&
                !TextUtils.isEmpty(etDateNaissance.getText()) &&
                !TextUtils.isEmpty(etTelephone.getText()) &&
                !TextUtils.isEmpty(etEmail.getText());
    }
}