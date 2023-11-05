package com.example.ecole;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

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
import com.example.ecole.Affichage.AffichageRoles;

import org.json.JSONException;
import org.json.JSONObject;

public class RoleActivity  extends AppCompatActivity implements View.OnClickListener{
    private EditText roleName;
    private Button sendRole,redirectRole;
    RequestQueue requestQueue;
    String insertUrl = "http://10.0.2.2:8082/api/roles";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);
        roleName=findViewById(R.id.role);

        sendRole=findViewById(R.id.submitRole);
        sendRole.setOnClickListener(this);


        redirectRole=findViewById(R.id.AffichageRoles);
        redirectRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoleActivity.this, AffichageRoles.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View view) {


        Log.d("Role", "onClick method called");

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("name", roleName.getText().toString() );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("Role", "JSON Data: " + jsonBody.toString()); // Log the JSON data

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                insertUrl, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Role", "Response: " + response.toString());
                // Effacer les champs après un envoi réussi
                roleName.setText("");

                // Afficher une boîte de dialogue (popup) pour indiquer le succès
                AlertDialog.Builder builder = new AlertDialog.Builder(RoleActivity.this);
                builder.setTitle("Succès");
                builder.setMessage("Role ajoutée avec succès");
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
                Log.e("Role", "Error: " + error.toString()); // Log any errors
            }
        });
        requestQueue.add(request);

    }
}