package fr.nicolashoareau_toulousewcs.hack2starlover;

public class CharacterModel {
    private String image;
    private String desc;

    public CharacterModel() {}

    public CharacterModel(String image) {
        this.image = image;
    }

    public CharacterModel(String image, String desc) {
        this.image = image;
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public CharacterModel setImage(String image) {
        this.image = image;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public CharacterModel setDesc(String desc) {
        this.desc = desc;
        return this;
    }
}

