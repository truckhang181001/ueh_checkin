package event;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codesieucap.ueh_checkin.R;

import java.util.List;

public class SearchEventAdapter extends RecyclerView.Adapter<SearchEventAdapter.SearchEventViewHolder>{

    private List<Event> list;

    public void setData(List<Event> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event_search, parent, false);
        return new SearchEventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchEventViewHolder holder, int position) {
        Event event = list.get(position);
        if (event == null) {
            return;
        }
        holder.imgEvent.setImageResource(event.getEventId());
        holder.tvName.setText(event.getName());
        holder.tvDesc.setText(event.getDesc());
        holder.tvAddress.setText(event.getAddress());
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class SearchEventViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgEvent;
        private TextView tvName, tvDesc, tvAddress;

        public SearchEventViewHolder(@NonNull View itemView) {
            super(itemView);
            imgEvent = itemView.findViewById(R.id.img_event_search);
            tvName = itemView.findViewById(R.id.name_event_search);
            tvDesc = itemView.findViewById(R.id.desc_event_search);
            tvAddress = itemView.findViewById(R.id.address_event_search);
        }
    }
}
