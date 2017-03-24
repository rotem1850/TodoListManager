package info.androidhive.cardview;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;


/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Album> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, count, id;
        public RelativeLayout container;
        public ImageView thumbnail, overflow;
        public  View v;
        public MyViewHolder(View view) {
            super(view);
            this.v = view;
            id = (TextView) view.findViewById(R.id.album_card_id);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            container = (RelativeLayout) view.findViewById(R.id.card_container);
            //thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
        }

    }


    public AlbumsAdapter(Context mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.id.setText(Integer.toString(album.getId()));
        holder.count.setText(album.getDate());
        if(album.getId() % 2== 0){
            holder.container.setBackgroundResource(R.color.red_card);
        }
        else{
            holder.container.setBackgroundResource(R.color.blue_card);
        }
        // loading album cover using Glide library
        //Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);



        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow, holder);
            }
        });


        holder.v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ((MainActivity)mContext).showEditMyDialog(v);
                return true;
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view, final MyViewHolder holder) {
        // inflate menu

        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();

        if(holder.title.getText().toString().trim().startsWith("call") || holder.title.getText().toString().trim().startsWith("להתקשר")){
            inflater.inflate(R.menu.menu_album, popup.getMenu());
            popup.setOnMenuItemClickListener(new MyMenuItemClickListener(holder.title.getText().toString()));
            popup.show();
        }

    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        private String title;
        public MyMenuItemClickListener(String title) {
             this.title = title;
        }

        private void dialContactPhone(final String phoneNumber) {
            mContext.startActivity(new Intent(Intent.ACTION_DIAL , Uri.fromParts("tel", phoneNumber, null)));

        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String phone = title.split("call")[1];
            phone = phone.trim().split("  ")[0].trim();
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, phone, Toast.LENGTH_SHORT).show();
                    dialContactPhone(phone);
                    return true;
                /*case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;*/
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
