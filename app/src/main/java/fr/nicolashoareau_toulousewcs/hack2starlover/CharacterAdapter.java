package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class CharacterAdapter extends ArrayAdapter<CharacterModel> {

    public CharacterAdapter (Context context, ArrayList<CharacterModel> trips) {
        super(context, 0, trips);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CharacterModel character = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_character, parent, false);
        }

        ImageView characterPic = (ImageView) convertView.findViewById(R.id.iv_character);

        Glide.with(getContext()).load(character.getImage()).apply(RequestOptions.circleCropTransform()).into(characterPic);

        return convertView;
    }
}
