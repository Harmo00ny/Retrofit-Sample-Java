package com.marysugar.retrofit_sample_java;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class CustomCountryListAdapter extends BaseAdapter {
    private final Activity context;
    List<Country> countries;

    public CustomCountryListAdapter(Activity context, List<Country> countries) {
        this.context = context;
        this.countries = countries;
    }

    public static class ViewHolder {
        TextView textViewId;
        TextView textViewCountry;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            row = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.textViewId = row.findViewById(R.id.tv_id);
            viewHolder.textViewCountry = row.findViewById(R.id.tv_country);
            row.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.textViewId.setText(""+countries.get(position).getId());
        viewHolder.textViewCountry.setText(countries.get(position).getCountryName());

        return row;
    }

    public long getItemId(int position) {
        return position;
    }

    public Object getItem(int position) {
        return position;
    }

    public int getCount() {
        if (countries.size() <= 0) return 1;
        return countries.size();
    }
}
