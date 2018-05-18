package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class LoverGridAdapter extends ArrayAdapter<LoverModel>{
    public LoverGridAdapter  (Context context, ArrayList<LoverModel> userGrid) {
        super(context, 0, userGrid);
    }

        @Override
        public View getView (int position, View convertView, final ViewGroup parent) {
            LoverModel userGrid = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_grid_lover, parent, false);

            }

            TextView nameLover = (TextView) convertView.findViewById(R.id.tv_name_lover);
            nameLover.setText(userGrid.getPseudo());

            final ImageView imgLover = (ImageView) convertView.findViewById(R.id.img_lover_grid);
            Glide.with(getContext()).load(userGrid.getProfilPic()).apply(RequestOptions.circleCropTransform()).into(imgLover);


            return convertView;


    }

}

