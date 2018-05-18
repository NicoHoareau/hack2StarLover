package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.widget.ImageView;

public class LoverModel {

    private String profilPic;
    private String pseudo;

    public LoverModel() {
    }

    public LoverModel(String profilPic, String pseudo) {
        this.profilPic = profilPic;
        this.pseudo = pseudo;
    }

    public String getProfilPic() {
        return profilPic;
    }

    public LoverModel setProfilPic(String profilPic) {
        this.profilPic = profilPic;
        return this;
    }

    public String getPseudo() {
        return pseudo;
    }

    public LoverModel setPseudo(String pseudo) {
        this.pseudo = pseudo;
        return this;
    }
}

