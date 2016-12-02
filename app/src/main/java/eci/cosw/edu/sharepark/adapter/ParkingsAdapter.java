package eci.cosw.edu.sharepark.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import eci.cosw.edu.sharepark.entities.Parking;

/**
 * Created by 2098099 on 11/23/16.
 */

public class ParkingsAdapter extends RecyclerView.Adapter<ParkingsAdapter.ViewHolder>{

    private final List<Parking> parkings;
    private Context context;

    public ParkingsAdapter(List<Parking> parkings) {
        this.parkings = parkings;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView shortName;
        ImageView logo;
        public ViewHolder(View itemView) {
            super(itemView);
            //name=(TextView)itemView.findViewById(R.id.name);
            //shortName=(TextView)itemView.findViewById(R.id.shortName);
            //logo=(ImageView)itemView.findViewById(R.id.logo);
        }
    }

    @Override
    public ParkingsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        //View view= LayoutInflater.from(context).inflate(R.layout.team_row, parent, false);
        //return new ViewHolder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(ParkingsAdapter.ViewHolder holder, int position) {
        Parking parking= parkings.get(position);
        holder.name.setText(parking.getAddress());
        holder.shortName.setText(parking.getOwner_id());
    }

    @Override
    public int getItemCount() {
        return parkings.size();
    }

    public List<Parking> getParkings(){
        return parkings;
    }
}
