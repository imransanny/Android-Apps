package edu.ewubd.storeandretrievefirebase;

public class Upload {
    private String imageName;
    private String imageUrl;
    private String imagedes;

    public Upload(){

    }

    public Upload (String imageName, String imageUrl,String imagedes){
        this.imageName = imageName;
        this.imageUrl = imageUrl;
        this.imagedes = imagedes;

    }

    public  String getImageName(){
        return imageName;
    }

    public void setImageName(String imageName){
        this.imageName = imageName;
    }

    public String getImageUrl(){
        return imageUrl;
    }
    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public String getImagedes(){
        return imagedes;
    }
    public void setImagedes(String imagedes){
        this.imagedes = imagedes;
    }



}
