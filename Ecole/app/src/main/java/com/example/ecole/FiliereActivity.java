package com.example.ecole;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.Response;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ecole.Affichage.AffichageFilieres;
import com.example.ecole.Affichage.AffichageRoles;

import org.json.JSONException;
import org.json.JSONObject;

public class FiliereActivity extends AppCompatActivity implements View.OnClickListener  {

    private EditText code,nom;
    private Button sendFiliere,redirectFiliere;
    RequestQueue requestQueue;
    String insertUrl = "http://10.0.2.2:8082/api/filieres";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filiere);

        code=findViewById(R.id.code);
        nom=findViewById(R.id.mdp);


        sendFiliere=findViewById(R.id.submitFiliere);
        sendFiliere.setOnClickListener(this);

        redirectFiliere=findViewById(R.id.AffichageFilieres);
        redirectFiliere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FiliereActivity.this, AffichageFilieres.class);
                startActivity(intent);
            }
        });



    }
    @Override
    public void onClick(View view) {


        Log.d("Filiere", "onClick method called");

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("code", code.getText().toString() );
            jsonBody.put("name", nom.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("Filiere", "JSON Data: " + jsonBody.toString()); // Log the JSON data

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                insertUrl, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Filiere", "Response: " + response.toString());
                // Effacer les champs après un envoi réussi
                code.setText("");
                nom.setText("");

                // Afficher une boîte de dialogue (popup) pour indiquer le succès
                AlertDialog.Builder builder = new AlertDialog.Builder(FiliereActivity.this);
                builder.setTitle("Succès");
                builder.setMessage("Filière ajoutée avec succès");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Vous pouvez ajouter un code supplémentaire ici si nécessaire
                    }
                });
                builder.show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Filiere", "Error: " + error.toString()); // Log any errors
            }
        });
        requestQueue.add(request);

    }

}