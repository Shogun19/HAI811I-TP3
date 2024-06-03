package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ConfirmationFragment extends Fragment {

    private TextView tvConfirmation;
    private Button btnValider;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirmation, container, false);

        tvConfirmation = view.findViewById(R.id.tvConfirmation);
        btnValider = view.findViewById(R.id.btnValider);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String nom = bundle.getString("nom");
            String prenom = bundle.getString("prenom");
            String dateNaissance = bundle.getString("dateNaissance");
            String telephone = bundle.getString("telephone");
            String email = bundle.getString("email");
            boolean sport = bundle.getBoolean("sport");
            boolean musique = bundle.getBoolean("musique");
            boolean lecture = bundle.getBoolean("lecture");
            boolean synchroniser = bundle.getBoolean("synchroniser");

            String confirmationText = "Nom: " + nom + "\n" +
                    "Prénom: " + prenom + "\n" +
                    "Date de naissance: " + dateNaissance + "\n" +
                    "Numéro de téléphone: " + telephone + "\n" +
                    "Adresse mail: " + email + "\n" +
                    "Centres d’intérêt: " +
                    (sport ? "Sport " : "") +
                    (musique ? "Musique " : "") +
                    (lecture ? "Lecture " : "") + "\n" +
                    "Synchronisation: " + (synchroniser ? "Oui" : "Non");

            tvConfirmation.setText(confirmationText);
        }

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Données validées", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
