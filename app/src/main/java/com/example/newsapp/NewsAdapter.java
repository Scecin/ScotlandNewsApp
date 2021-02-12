package com.example.newsapp;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NewsAdapter extends ArrayAdapter<News> {

    private static final String LOG_TAG = NewsAdapter.class.getSimpleName();

    public NewsAdapter(@NonNull Activity context, @NonNull ArrayList<News> news) {
        // we initialize the ArrayAdapter's internal storage for the context and the list.
        super(context, 0, news);
    }

    // Provides a view for an AdapterView.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        // Get the {@link News} object located at this position in the list
        News currentNews = getItem(position);

        // Find the TextViews in the list_item.xml
        TextView pillarNameTextView = listItemView.findViewById(R.id.pillar_name);
       pillarNameTextView.setText(currentNews.getPillarName());

        TextView nameSectionTextView = listItemView.findViewById(R.id.section_name);
        nameSectionTextView.setText(currentNews.getSectionName());

        TextView titleTextView = listItemView.findViewById(R.id.title);
        titleTextView.setText(currentNews.getWebTitle());

        String originalDate = currentNews.getDate();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        Date date = null;
        try {
            date = format.parse(originalDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        String formattedDate = formatDate(date);
        dateView.setText(formattedDate);

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(date);
        timeView.setText(formattedTime);

        LinearLayout generalSection = listItemView.findViewById(R.id.general_section);

        // Set the proper background color on the PillarNsme circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable pillarRectangle = (GradientDrawable) generalSection.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int rectangleColor = getRectangleColor(currentNews.getPillarName());

        // Set the color on the magnitude circle
        pillarRectangle.setColor(rectangleColor);

        // Return the whole list item layout
        return listItemView;
    }

    private int getRectangleColor(String generalSector) {
        int pillarColorResourceId;
        switch (generalSector) {
            case "Sport":
                pillarColorResourceId = R.color.sport;
                break;
            case "News":
                pillarColorResourceId = R.color.news;
                break;
            case "Opinion":
                pillarColorResourceId = R.color.opinion;
                break;
            case "Arts":
                pillarColorResourceId = R.color.art;
                break;
            case "Culture":
                pillarColorResourceId = R.color.culture;
                break;
            case "Lifestyle":
                pillarColorResourceId = R.color.lifeStyle;
                break;
            default:
                pillarColorResourceId = R.color.default_color;
                break;
        }
        return ContextCompat.getColor(getContext(), pillarColorResourceId);
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(date);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date date) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(date);
    }
}
