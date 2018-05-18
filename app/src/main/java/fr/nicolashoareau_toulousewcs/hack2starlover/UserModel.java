package fr.nicolashoareau_toulousewcs.hack2starlover;

public class UserModel {
    private String pseudo;

    public UserModel(String pseudo, String profilPic, String genre) {
        this.pseudo = pseudo;
        this.profilPic = profilPic;
        this.genre = genre;
    }

    private String profilPic;
    private String genre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public UserModel() {}


    public UserModel(String pseudo, String profilPic) {
        this.pseudo = pseudo;
        this.profilPic = profilPic;
    }

    public UserModel (String profilPic){
        this.profilPic = profilPic;
    }



    public String getPseudo() {
        return pseudo;
    }

    public UserModel setPseudo(String pseudo) {
        this.pseudo = pseudo;
        return this;
    }

    public String getProfilPic() {
        return profilPic;
    }

    public UserModel setProfilPic(String profilPic) {
        this.profilPic = profilPic;
        return this;
    }
}