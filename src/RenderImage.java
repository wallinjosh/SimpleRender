/**
 * Created by Josh on 1/25/17.
 */
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Color;

public class RenderImage {

    private BufferedImage image;


    public RenderImage(int width, int height){
        image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = image.getGraphics();
        g.setColor(new Color(0x000000));
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
    }

    public int getPixel(int x, int y){
        return image.getRGB(x, y);
    }

    public void setPixel(int x, int y, int rgb){
        image.setRGB(x,y,rgb);
    }

    public int getWidth(){
        return image.getWidth();
    }

    public int getHeight(){
        return image.getHeight();
    }

    public void setPixel(int x, int y, int red, int green, int blue){
        int rgb = red;
        rgb = (rgb << 8) + green;
        rgb = (rgb << 8) + blue;
        image.setRGB(x, y, rgb);
    }

    public void outputRenderImage(String filename){
        File output = new File(filename);
        try {
            ImageIO.write(image, "jpg", output);
        }catch(Exception e){
            e.printStackTrace();
        }
    }



}
