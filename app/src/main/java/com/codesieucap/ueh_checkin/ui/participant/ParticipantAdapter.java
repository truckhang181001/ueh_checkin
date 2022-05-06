package com.codesieucap.ueh_checkin.ui.participant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codesieucap.ueh_checkin.R;
import com.codesieucap.ueh_checkin.models.JoinerModel;

import java.util.List;

public class ParticipantAdapter extends RecyclerView.Adapter<ParticipantAdapter.ParticipantViewHolder> {

    private Context context;
    private List<JoinerModel> participantList;

    public ParticipantAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<JoinerModel> list){
        this.participantList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ParticipantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_participantlayout, parent, false);
        return new ParticipantViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ParticipantViewHolder holder, int position) {
        JoinerModel participant = participantList.get(position);
        if (participant == null){
            return;
        }
        holder.tv_oder.setText(participant.getIdCode());
        holder.tv_name.setText(participant.getJoinerName());
        holder.tv_class.setText(participant.getClassName());
        holder.tv_status.setText(participant.getStatus());
//        if (participant.getStatus() == "0") {
//            holder.tv_status.setBackgroundColor(R.color.yellow);
//        }
    }

    @Override
    public int getItemCount() {
        if (participantList != null){
            return participantList.size();
        }
        return 0;
    }

    public class ParticipantViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_oder, tv_name, tv_class, tv_status;

        public ParticipantViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_oder = itemView.findViewById(R.id.participant_oder);
            tv_name = itemView.findViewById(R.id.participant_name);
            tv_class = itemView.findViewById(R.id.participant_class);
            tv_status = itemView.findViewById(R.id.participant_status);
        }
    }
}
