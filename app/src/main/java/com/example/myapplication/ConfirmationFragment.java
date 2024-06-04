package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ConfirmationFragment extends Fragment {

    private TextView tvConfirmation;
    private Button btnValider;

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

            btnValider.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        JSONObject json = new JSONObject();
                        json.put("nom", nom);
                        json.put("prenom", prenom);
                        json.put("dateNaissance", dateNaissance);
                        json.put("telephone", telephone);
                        json.put("email", email);
                        json.put("sport", sport);
                        json.put("musique", musique);
                        json.put("lecture", lecture);
                        json.put("synchroniser", synchroniser);

                        saveToFile(json.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        return view;
    }

    private void saveToFile(String data) {
        String fileName = "user_data.json";
        FileOutputStream fos = null;
        try {
            File file = new File(getActivity().getFilesDir(), fileName);
            fos = new FileOutputStream(file);
            fos.write(data.getBytes());
            Toast.makeText(getActivity(), "Données sauvegardées", Toast.LENGTH_LONG).show();
            Log.d("ConfirmationFragment", "File saved at: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Erreur lors de la sauvegarde des données", Toast.LENGTH_SHORT).show();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
