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

import java.util.ArrayList;
import java.util.Map;

public class ParticipantAdapter extends RecyclerView.Adapter<ParticipantAdapter.ParticipantViewHolder> {

    private Context context;
    private ArrayList participantList;
    private OnClickItemListener onClickItemListener;

    public interface OnClickItemListener{
        public void onLickItemListener(JoinerModel joinerItem);
    }

    public ParticipantAdapter(Context context, Map<String, JoinerModel> list, OnClickItemListener onClickItemListener)
    {
        this.context = context;
        this.participantList = new ArrayList<>();
        this.participantList.addAll(list.entrySet());
        this.onClickItemListener = onClickItemListener;
    }

    public void setData(Map<String, JoinerModel> list){
        this.participantList = new ArrayList<>();
        this.participantList.addAll(list.entrySet());
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
        Map.Entry<String,JoinerModel> participant = (Map.Entry<String, JoinerModel>) participantList.get(position);
        if (participant == null){
            return;
        }
        holder.binding(participant.getValue(),onClickItemListener);
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

        @SuppressLint("ResourceAsColor")
        private void binding(JoinerModel participant, OnClickItemListener listener){
            if(participant.getStatus().equals(JoinerModel.STATUS_CHECKED))
                tv_status.setBackgroundResource(R.color.green);
            if(participant.getStatus().equals(JoinerModel.STATUS_SENT))
                tv_status.setBackgroundResource(R.color.main_blue);
            if(participant.getStatus().equals(JoinerModel.STATUS_PENDING))
                tv_status.setBackgroundResource(R.color.grey);
            if(participant.getStatus().equals(JoinerModel.STATUS_FAIL))
                tv_status.setBackgroundResource(R.color.red);

            tv_oder.setText(participant.getIdCode());
            tv_name.setText(participant.getJoinerName());
            tv_class.setText(participant.getClassName());
            tv_status.setText(participant.getStatus());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onLickItemListener(participant);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
