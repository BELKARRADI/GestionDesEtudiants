package com.example.ecole.Affichage;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ecole.R;
import com.example.ecole.adapters.FiliereAdapter;
import com.example.ecole.adapters.RolesAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AffichageFilieres extends AppCompatActivity {

    String insertUrl = "http://10.0.2.2:8082/api/filieres";
    private ListView listView;
    private FiliereAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_filieres);

        listView = findViewById(R.id.ListFilieres);
        adapter = new FiliereAdapter(this, new JSONArray());

        listView.setAdapter(adapter);

        // GET DATA
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                insertUrl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String id = jsonObject.getString("id");
                                String name = jsonObject.getString("name");
                                Log.d("Donnees", "Donnees recues par le GET - ID: " + id + " Name: " + name);
                            }
                            adapter.setData(response);

                            // Écouteur de clic pour la ListView
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    JSONObject selectedFiliere = adapter.getItem(position);
                                    if (selectedFiliere != null) {
                                        try {
                                            String idSelected = selectedFiliere.getString("id");
                                            showAlertDialog(idSelected);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("MyTag", "Erreur de requête GET: " + error.getMessage());
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);
    }

    private void showAlertDialog(final String filiereId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert Dialog");
        builder.setMessage("Vous avez sélectionné la filiere : " + filiereId);

        builder.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Afficher une nouvelle alerte pour demander confirmation
                showConfirmationDialog(filiereId);
            }
        });

        builder.setNegativeButton("Modifier", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Gérer le clic sur le bouton "Modifier" si nécessaire
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showConfirmationDialog(final String filiereId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation de suppression");
        builder.setMessage("Êtes-vous sûr de supprimer la filiere  : " + filiereId);

        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteFiliere(filiereId);

            }
        });

        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Gérer le clic sur le bouton "Non" si nécessaire
            }
        });

        AlertDialog confirmationDialog = builder.create();
        confirmationDialog.show();
    }
    private void deleteFiliere(String filiereId) {

        String deleteUrl = "http://10.0.2.2:8082/api/filieres/"+filiereId;

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(
                Request.Method.DELETE,
                deleteUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Gérer la réponse du serveur en cas de succès
                        // Vous pouvez rafraîchir la liste des rôles après la suppression
                        // par exemple, en refaisant la requête GET
                        refreshFiliereList();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("MyTag", "Erreur de requête DELETE : " + error.getMessage());
                        // Gérer les erreurs ici
                    }
                }
        );

        requestQueue.add(request);
    }

    private void refreshFiliereList() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                insertUrl, // URL de la liste des rôles
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            adapter.setData(response); // Mettre à jour l'adaptateur avec les nouvelles données
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("MyTag", "Erreur de requête GET: " + error.getMessage());
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);
    }



}
