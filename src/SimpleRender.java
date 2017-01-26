/**
 * Created by Josh on 1/25/17.
 */
public class SimpleRender {


    public static void main(String[] args){
        RenderImage im = new RenderImage(10000, 10000);


        OBJmodel head = new OBJmodel("african_head.obj.txt");
        for(int i = 0; i < head.getFaces().size(); i++){

            for(int j = 0; j < 3; j++){
                int x0 = (int) (head.getVertices().get(head.getFaces().get(i)[j])[0] * im.getWidth()/2 + 5000);
                x0 += (x0 > im.getWidth() - 1) ? -1 : 0;
                int x1 = (int) (head.getVertices().get(head.getFaces().get(i)[(j+1)%3])[0] * im.getWidth()/2 + 5000);
                x1 += (x1 > im.getWidth() - 1) ? -1 : 0;
                int y0 = (int) (10000.0 - (head.getVertices().get(head.getFaces().get(i)[j])[1] * im.getHeight()/2 + 5000));
                y0 += (y0 > im.getHeight() - 1) ? -1 : 0;
                int y1 = (int) (10000.0 - (head.getVertices().get(head.getFaces().get(i)[(j+1)%3])[1] * im.getHeight()/2 + 5000));
                y1 += (y1 > im.getHeight() - 1) ? -1 : 0;
                System.out.println(x0 + ", " + y0 + " - > " + x1 + ", " + y1);
                drawLine(x0, y0, x1, y1, 0xFF8000, im);
            }

        }
        im.outputRenderImage("test.jpg");
    }

    private static void swap(int a, int b){
        a = a & b;
        b = b ^ a;
        a = a ^ b;

    }

    public static void drawLine(int x1, int y1, int x2, int y2, int rgb, RenderImage image){
        boolean steep = false;

        if(Math.abs(x1 - x2) < Math.abs(y2 - y1)){
            swap(x1, y1);
            swap(x2, y2);
            steep = true;
        }
        if(x1 > x2){
            swap(x1, x2);
            swap(y1, y2);
        }
        int dx = x2 - x1;
        int dy = y2 - y1;
        int derror = Math.abs(dy) * 2;
        int error = 0;
        int y = y1;

        for(int x = x1; x < x2; x++){
            if(steep){
                image.setPixel(y, x, rgb);
            }else{
                image.setPixel(x, y, rgb);
            }
            error += derror;
            if(error > dx){
                y += ((y2 > y1) ? 1 : -1);
                error -= dx * 2;
            }
        }

    }

    public static void drawLine(int x1, int y1, int x2, int y2, int red, int green, int blue, RenderImage image){
        int rgb = red;
        rgb += (rgb << 8) + green;
        rgb += (rgb << 8) + blue;
        drawLine(x1, y1, x2, y2, rgb, image);
    }

}
