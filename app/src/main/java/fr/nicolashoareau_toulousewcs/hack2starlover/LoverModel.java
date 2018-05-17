package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.widget.ImageView;

public class LoverModel {

    private int imageLover;
    private String nameLover;

    public LoverModel() {
    }

    public LoverModel(int imageLover, String nameLover) {
        this.imageLover = imageLover;
        this.nameLover = nameLover;
    }


    public int getImageLover() {
        return imageLover;
    }

    public void setImageLover(int imageLover) {
        this.imageLover = imageLover;
    }

    public String getNameLover() {
        return nameLover;
    }

    public void setNameLover(String nameLover) {
        this.nameLover = nameLover;
    }
}

