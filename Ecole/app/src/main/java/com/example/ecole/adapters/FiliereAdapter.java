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

public class FiliereAdapter extends BaseAdapter {
    private Context context;
    private JSONArray data;

    public FiliereAdapter(Context context, JSONArray data) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.filierelayout, parent, false);
        }

        TextView textViewId = convertView.findViewById(R.id.textViewId);
        TextView textViewCode = convertView.findViewById(R.id.textViewCode);
        TextView textViewName = convertView.findViewById(R.id.textViewName);

        try {
            JSONObject item = data.getJSONObject(position);
            textViewId.setText("ID: " + item.getString("id"));
            textViewCode.setText("Code: " + item.getString("code"));
            textViewName.setText("Name: " + item.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
