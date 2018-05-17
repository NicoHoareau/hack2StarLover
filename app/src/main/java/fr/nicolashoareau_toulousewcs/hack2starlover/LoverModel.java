package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.widget.ImageView;

public class LoverModel {

    public LoverModel(ImageView image) {
        this.image = image;
    }

    public LoverModel() {
    }

    public ImageView getImage() {
        return image;

    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    ImageView image;
}

