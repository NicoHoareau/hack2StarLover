package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class LoverGridAdapter extends ArrayAdapter<LoverModel>{
    public LoverGridAdapter  (Context context, ArrayList<LoverModel> userGrid) {
        super(context, 0, userGrid);
    }

        @Override
        public View getView (int position, View convertView, ViewGroup parent) {
            LoverModel userGrid = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_grid_lover, parent, false);

                ImageView img = (ImageView) convertView.findViewById(R.id.img_lover_grid);

            }

            return convertView;


    }

}

