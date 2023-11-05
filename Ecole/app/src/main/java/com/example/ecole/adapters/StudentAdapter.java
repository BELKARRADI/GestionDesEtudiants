package com.example.ecole.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ecole.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StudentAdapter extends BaseAdapter {
    private Context context;
    private JSONArray data;

    public StudentAdapter(Context context, JSONArray data) {
        this.context = context;
        this.data = data;
    }

    public void setData(JSONArray data) {
        this.data = data;
        notifyDataSetChanged(); // Notifiez l'adaptateur que les données ont changé
    }

    @Override
    public int getCount() {
        return data.length();
    }

    @Override
    public JSONObject getItem(int position) {
        try {
            return data.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.studentlayout, parent, false);
        }

        TextView textViewId = convertView.findViewById(R.id.textViewId);
        TextView textViewNom = convertView.findViewById(R.id.textViewNom);
        TextView textViewPrenom = convertView.findViewById(R.id.textViewPrenom);
        TextView textViewUserName = convertView.findViewById(R.id.textViewUserName);
        TextView textViewTelephone = convertView.findViewById(R.id.textViewTelephone);


        try {
            JSONObject item = data.getJSONObject(position);
            textViewId.setText("ID: " + item.getString("id"));

            textViewNom.setText("Nom: " + item.getString("nom"));
            textViewPrenom.setText("Prenom: " + item.getString("prenom"));
            textViewUserName.setText("UserName: " + item.getString("userName"));
            textViewTelephone.setText("Telephone: " + item.getString("telephone"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
