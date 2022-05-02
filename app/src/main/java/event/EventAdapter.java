package event;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codesieucap.ueh_checkin.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder>{

    private Context mContext;
    private List<Event> eventList;

    public EventAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void SetData(List<Event> list){
        this.eventList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);
        if (event == null){
            return;
        }
        holder.imgEvent.setImageResource(event.getEventId());
        holder.tvName.setText(event.getName());
        holder.tvAddress.setText(event.getAddress());
    }

    @Override
    public int getItemCount() {
        if (eventList != null ) {
            return eventList.size();
        }
        return 0;
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgEvent;
        private TextView tvName, tvAddress;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            imgEvent = itemView.findViewById(R.id.img_event);
            tvName = itemView.findViewById(R.id.name_event);
            tvAddress = itemView.findViewById(R.id.address_event);
        }
    }
}
