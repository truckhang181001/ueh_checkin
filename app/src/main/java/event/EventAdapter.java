package event;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codesieucap.ueh_checkin.models.EventModel;
import com.codesieucap.ueh_checkin.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder>{

    public interface OnItemClickListener{
        void onItemClick(EventModel item);
    }

    private Context mContext;
    private List<EventModel> eventList;
    private OnItemClickListener listener;

    public EventAdapter(Context mContext, List<EventModel> eventList, OnItemClickListener listener) {
        this.eventList = eventList;
        this.mContext = mContext;
        this.listener = listener;
    }


    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        EventModel event = eventList.get(position);
        if (event == null){
            return;
        }
        holder.bind(event,listener);
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

        public void bind(final EventModel item, final OnItemClickListener listener) {
            Picasso.get().load(item.getAvatarImgUri()).into(imgEvent);
            tvName.setText(item.getEventName());
            tvAddress.setText(item.getAddress());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
