import java.awt.image.BufferedImage;

public class GameObject {

    private BufferedImage image;

    private Positioned parent;


    GameObject(BufferedImage image, Positioned parent) {
        this.image = image;
        this.parent = parent;
    }
    public double getX(){
        return parent.getX();
    }
    public double getY(){
        return parent.getY();
    }

    public BufferedImage getImage(){
        return image;
    }

    public int getWidth(){
        return image.getWidth();
    }

    public int getHeight(){
        return image.getHeight();
    }

    public Positioned getParent(){
        return parent;
    }


}
