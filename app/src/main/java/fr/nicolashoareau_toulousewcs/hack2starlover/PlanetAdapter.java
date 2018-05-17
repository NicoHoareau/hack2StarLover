package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlanetAdapter extends ArrayAdapter<PlanetModel>{

    public PlanetAdapter(Context context, ArrayList<PlanetModel> planetList) {
        super(context, 0, planetList);
    }

    @Override
    public View getView (int position, View convertView, final ViewGroup parent) {
        PlanetModel planetModel = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_grid_planet, parent, false);
        }

        TextView namePlanet = (TextView) convertView.findViewById(R.id.tv_name_planet);

        namePlanet.setText(planetModel.getNamePlanete());

        ImageView imgPlanet = (ImageView) convertView.findViewById(R.id.iv_picture_planet);
        imgPlanet.setImageResource(planetModel.getPlanetPicture());
        /*imgPlanet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                LayoutInflater li = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View mView = li.inflate(R.layout.validate_planet_dialog, null);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                //contain :
                TextView tvNamePlanet = mView.findViewById(R.id.tv_name_planet_dialog);
                ImageView imgPlanetSelected = mView.findViewById(R.id.iv_planet_select);
                Button btnValidatePlanet = mView.findViewById(R.id.btn_validate_planet);
                btnValidatePlanet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                dialog.show();

            }
        });*/


        return convertView;

    }
}
