package edu.ewubd.cse2020160119;


        import android.annotation.SuppressLint;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;
        import androidx.annotation.NonNull;
        import java.util.ArrayList;

public class CustomEventAdapter extends ArrayAdapter<Event> {

    private final Context context;
    private final ArrayList<Event> values;


    public CustomEventAdapter(@NonNull Context context, @NonNull ArrayList<Event> objects) {
        super(context, -1, objects);
        this.context = context;
        this.values = objects;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.layout_event_row, parent, false);

        TextView eventName = rowView.findViewById(R.id.tvEventName);
        TextView eventPlaceName = rowView.findViewById(R.id.tvEventPlaceName);
        //TextView eventCapacity = rowView.findViewById(R.id.tvEventcapacity);
      //  TextView eventBudget = rowView.findViewById(R.id.tvEventBudget);
        TextView eventemail= rowView.findViewById(R.id.tvEventEmail);
        TextView eventPhone = rowView.findViewById(R.id.tvEventPhone);
       // TextView eventDescription = rowView.findViewById(R.id.tvEventDescription);
        TextView eventDateTime = rowView.findViewById(R.id.tvEventDateTime);
        TextView eventCType = rowView.findViewById(R.id.tvEventtype);



        eventName.setText(values.get(position).name);
        eventPlaceName.setText(values.get(position).place);
        //eventCapacity.setText(values.get(position).capacity);
        //eventBudget.setText(values.get(position).budget);
        //eventemail.setText(values.get(position).email);
        //eventPhone.setText(values.get(position).phone);
      //  eventDescription.setText(values.get(position).description);
        eventDateTime.setText(values.get(position).datetime);
        //eventCType.setText(values.get(position).eventType);

        return rowView;
    }
}
