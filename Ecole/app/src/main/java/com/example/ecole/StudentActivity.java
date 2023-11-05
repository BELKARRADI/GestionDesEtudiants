
package com.example.ecole;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ecole.Affichage.AffichageRoles;
import com.example.ecole.Affichage.AffichageStudents;
import com.example.ecole.adapters.Option;
import com.example.ecole.adapters.Option2;

import android.os.Bundle;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StudentActivity  extends AppCompatActivity implements View.OnClickListener {

    private EditText userName,password,nom,prenom,telephone;
    private Button sendStudent,redirectStudents;
    private int selectedOptionId,selectedOptionId2;
    RequestQueue requestQueue;
    String insertUrl = "http://10.0.2.2:8082/api/Student";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        nom=findViewById(R.id.nomUser);
        prenom=findViewById(R.id.prenomUser);
        userName=findViewById(R.id.userName);
        password=findViewById(R.id.mdp);
        telephone=findViewById(R.id.telephoneUser);


        Spinner spinner = findViewById(R.id.spinner); // Référencez le Spinner depuis le layout

// Créez une liste d'options (comme mentionné dans la réponse précédente)
        List<Option> options = new ArrayList<>();
        options.add(new Option(1, "2ITE"));
        options.add(new Option(2, "ISIC"));
        options.add(new Option(3, "G2E"));
        options.add(new Option(4, "GI"));
        options.add(new Option(5, "GC"));
        options.add(new Option(6, "CCN"));

// Créez un ArrayAdapter pour lier les options au Spinner
        ArrayAdapter<Option> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);

// Spécifiez le modèle d'affichage du Spinner (par exemple, simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Associez l'adaptateur au Spinner
        spinner.setAdapter(adapter);




        Spinner spinner2 = findViewById(R.id.spinner2); // Référencez le Spinner depuis le layout

// Créez une liste d'options (comme mentionné dans la réponse précédente)
        List<Option2> options2 = new ArrayList<>();
        options2.add(new Option2(1, "ADMIN"));
        options2.add(new Option2(2, "ETUDIANT"));
        options2.add(new Option2(3, "PROFESSEUR"));

// Créez un ArrayAdapter pour lier les options au Spinner
        ArrayAdapter<Option2> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options2);

// Spécifiez le modèle d'affichage du Spinner (par exemple, simple_spinner_item)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Associez l'adaptateur au Spinner
        spinner2.setAdapter(adapter2);


        sendStudent=findViewById(R.id.submitStudent);
        sendStudent.setOnClickListener(this);


        redirectStudents=findViewById(R.id.AffichageStudents);
        redirectStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentActivity.this, AffichageStudents.class);
                startActivity(intent);
            }
        });





    }
    @Override
    public void onClick(View view) {
        Spinner spinner = findViewById(R.id.spinner);
        Option selectedOption = (Option) spinner.getSelectedItem();
        selectedOptionId = selectedOption.getId();


        Spinner spinner2 = findViewById(R.id.spinner2);
        Option2 selectedOption2 = (Option2) spinner2.getSelectedItem();
        selectedOptionId2 = selectedOption2.getId();

        Log.d("Student", "onClick method called");

        JSONObject jsonBody = new JSONObject();
        JSONObject filiereObj = new JSONObject();
        JSONObject roleObj = new JSONObject();

        try {
            filiereObj.put("id", selectedOptionId);


        }catch (JSONException e){
            e.printStackTrace();

        }
        try {
            roleObj.put("id", selectedOptionId2);


        }catch (JSONException e){
            e.printStackTrace();

        }
        try {
            jsonBody.put("nom", nom.getText().toString() );
            jsonBody.put("prenom", prenom.getText().toString() );
            jsonBody.put("code", telephone.getText().toString() );
            jsonBody.put("userName", userName.getText().toString() );
            jsonBody.put("password", password.getText().toString() );
            jsonBody.put("telephone", telephone.getText().toString() );
            //selectedOptionId
            jsonBody.put("filiere", filiereObj);
            jsonBody.put("roles", new JSONArray().put(roleObj));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("Etudiant", "JSON Data: " + jsonBody.toString()); // Log the JSON data

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                insertUrl, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Filiere", "Response: " + response.toString());
                // Effacer les champs après un envoi réussi
                nom.setText("");
                prenom.setText("");
                telephone.setText("");
                userName.setText("");
                password.setText("");
                telephone.setText("");


                // Afficher une boîte de dialogue (popup) pour indiquer le succès
                AlertDialog.Builder builder = new AlertDialog.Builder(StudentActivity.this);
                builder.setTitle("Succès");
                builder.setMessage("Etudiant ajouté avec succès");
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
                Log.e("Student", "Error: " + error.toString()); // Log any errors
            }
        });
        requestQueue.add(request);

    }
}