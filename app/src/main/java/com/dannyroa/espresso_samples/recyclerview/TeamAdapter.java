package com.dannyroa.espresso_samples.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/**
 * Created by dannyroa on 5/8/15.
 */
public class TeamAdapter extends RecyclerView.Adapter<ItemViewHolder> {

  List<Team> items;
  Context context;

  public TeamAdapter(Context context, List<Team> items) {
    this.items = items;
    this.context = context;
  }

  @NonNull
  @Override
  public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    return new ItemViewHolder(this,
        LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_team, parent, false));
  }

  @Override
  public void onBindViewHolder(ItemViewHolder holder, int position) {
    Team team = items.get(position);
    holder.setTeam(team);
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

}
