package com.dannyroa.espresso_samples.recyclerview;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {

  private final TeamAdapter teamAdapter;
  TextView tvName;
  Button btnFollow;

  public ItemViewHolder(TeamAdapter teamAdapter, View itemView) {
    super(itemView);
    this.teamAdapter = teamAdapter;
    tvName = (TextView) itemView.findViewById(R.id.name);
    btnFollow = (Button) itemView.findViewById(R.id.follow_button);
  }

  public void setTeam(final Team team) {

    tvName.setText(team.getName());

    if (team.isFollowing()) {
      btnFollow.setText(teamAdapter.context.getString(R.string.following));
    } else {
      btnFollow.setText(teamAdapter.context.getString(R.string.follow));
    }

    btnFollow.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            team.setIsFollowing(!team.isFollowing());
            if (team.isFollowing()) {
              btnFollow.setText(teamAdapter.context.getString(R.string.following));
            } else {
              btnFollow.setText(teamAdapter.context.getString(R.string.follow));
            }
          }
        });

    itemView.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {

            ViewTeamActivity.launch((Activity) teamAdapter.context, team);
          }
        });
  }
}
