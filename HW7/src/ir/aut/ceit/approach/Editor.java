package ir.aut.ceit.approach;

/**
 * Created by Admin on 5/14/2017.
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;

////start the ImgArea class
//The ImgArea class acts as a drawing area of the Image Editor program

class ImgArea extends Canvas {

    Image orImg;
    BufferedImage orBufferedImage;
    BufferedImage bimg;
    BufferedImage bimg1;
    float e;
    float radian;
    Dimension dimension;
    int mX;
    int mY;
    int x;
    int y;
    static boolean imageLoaded;
    boolean actionSlided;
    boolean actionResized;
    boolean actionRotated;
    boolean actionDraw;
    boolean actionCropped;
    boolean actionFiltered;
    boolean actionColored;
    boolean drawn;
    MediaTracker mt;
    static Color c;
    Color colorTextDraw;
    Robot rb;
    boolean dirHor;
    String imgFileName;
    String fontName;
    int fontSize;
    String textToDraw;

    public ImgArea() {

        addMouseListener(new Mousexy()); //hanlding mouse event of Canvas class
        addKeyListener(new KList()); //handling key event of the Canvas
        try {
            rb = new Robot(); //create Robot object
        } catch (AWTException e) {
        }

        dimension = getToolkit().getScreenSize(); //get the screen size
        mX = (int) dimension.getWidth() / 2; //half of the screen width
        mY = (int) dimension.getHeight() / 2;//half of the screen height

    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; //create Graphics2D object
        if (imageLoaded) {

            //draw the update image
            if (actionSlided || actionResized || actionRotated || drawn) {
                x = mX - bimg.getWidth() / 2;
                y = mY - bimg.getHeight() / 2;
                g2d.translate(x, y); //move to coordinate (x,y)
                g2d.drawImage(bimg, 0, 0, null); //draw the iamge

            } else { //draw the original image
                x = mX - orBufferedImage.getWidth() / 2;
                y = mY - orBufferedImage.getHeight() / 2;
                g2d.translate(x, y); //move to  coordinate (x,y)
                g2d.drawImage(orBufferedImage, 0, 0, null); //draw image
            }
        }
        g2d.dispose(); //clean the Graphic2D object

    }


    /**
     *
     */
    class Mousexy extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            Color color = rb.getPixelColor(e.getX(), e.getY()); //get the color at the clicked point
            try {
                setColor(color); //take the color at the clicked point for later use
                if (actionDraw) { //add text to the update image
                    if (actionSlided || actionResized || actionRotated || drawn)
                        addTextToImage(e.getX() - x, e.getY() - y, bimg);
                    else  //add text to the original image
                        addTextToImage(e.getX() - x, e.getY() - y, orBufferedImage);


                }

            } catch (Exception ie) {
            }


        }


    }

    //The KList class extends the KeyAdpater class to implement the keyPressed method
    //to handle the key event of the Canvas

    /**
     *
     */
    class KList extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == 27) { //ESC is pressed to stop drawing the text on the image
                actionDraw = false;
                textToDraw = "";
                fontName = "";
                fontSize = 0;
            }
        }
    }

    /**
     * The addTextToImage method adds the text specified by the user to the image
     *
     * @param x
     * @param y
     * @param img
     */
    public void addTextToImage(int x, int y, BufferedImage img) {
        //create a blanck buffered image
        BufferedImage bi = (BufferedImage) createImage(img.getWidth(), img.getHeight());
        //create the Graphics2D object from the buffered image
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        //Set the font of drawing pen
        g2d.setFont(new Font(fontName, Font.BOLD, fontSize));
        //Set the pen color
        g2d.setPaint(colorTextDraw);
        //Draw the image on the blank buffered image
        g2d.drawImage(img, 0, 0, null);
        //Draw the text on the buffered image
        g2d.drawString(textToDraw, x, y);
        //update the image
        bimg = bi;
        //there is a text drawing on the image
        drawn = true;
        //clean the Graphics2D object
        g2d.dispose();
        //redisplay the update image so the text is displayed on the image now
        repaint();
    }

    //set the selected color to the c variable
    public void setColor(Color color) {
        c = color;
    }

    //set the image filename to the imgFileName variable
    public void setImgFileName(String fname) {
        imgFileName = fname;
    }

    //initialize variables
    public void initialize() {
        imageLoaded = false;
        actionSlided = false;
        actionResized = false;
        actionRotated = false;
        boolean actionCropped;
        boolean actionFiltered;
        boolean actionColored;
        actionDraw = false;
        drawn = false;
        dirHor = false;
        c = null;
        radian = 0.0f;
        e = 0.0f;
    }

    //cancel the image editing so we reset the drawing area
    public void reset() {
        if (imageLoaded) {
            prepareImage(imgFileName);
            repaint();
        }

    }

    //Rotate the image shown on the program interface
    public void makeImageRotate(BufferedImage image, int w, int h) {

        BufferedImage bi = (BufferedImage) createImage(w, h);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        radian = (float) Math.PI / 2; //angle
        g2d.translate(w / 2, h / 2);
        //  move to coordinate(w / 2, h / 2)
        g2d.rotate(radian); //rotate the image
        g2d.translate(-h / 2, -w / 2); //move the coordinate back
        g2d.drawImage(image, 0, 0, null); //draw the rotated image
        bimg = bi; //update the image so now you see the rotated image
        g2d.dispose();


    }

    //The rotateImage invokes the makeImageRotate method to rotate the image
    public void rotateImage() {
        BufferedImage bi;
        //rotate update image
        if (actionSlided || actionResized || actionRotated || drawn) {
            bi = bimg;
        }
        //rotate the original image
        else {
            bi = orBufferedImage;
        }

        makeImageRotate(bi, bi.getHeight(), bi.getWidth());

        actionRotated = true; //set the actionRotated to true to indicate that
        //the image is rotated
        repaint(); //repaint the update image

    }


    //The resizeImage method has code to resize the image
    //This method is invoked when the user clicks OK button on the image resize window
    //The image resize window is displayed when you select the Image resize sub-menu item
    public void resizeImage(int w, int h) {
        BufferedImage bi = (BufferedImage) createImage(w, h);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        //resize the update image

        if (actionSlided || actionRotated || drawn)
            g2d.drawImage(bimg, 0, 0, w, h, null);
            //resize the original image
        else
            g2d.drawImage(orImg, 0, 0, w, h, null);
        bimg = bi;
        g2d.dispose();

    }

    //Prepare the image so it is ready to display and editable
    public void prepareImage(String filename) {
        initialize();
        try {
            //track the image loading
            mt = new MediaTracker(this);
            orImg = Toolkit.getDefaultToolkit().getImage(filename);
            mt.addImage(orImg, 0);
            mt.waitForID(0);
            //get the image width and height
            int width = orImg.getWidth(null);
            int height = orImg.getHeight(null);
            //create buffered image from the image so any change to the image can be made
            orBufferedImage = createBufferedImageFromImage(orImg, width, height, false);
            //create the blank buffered image
            //the update image data is stored in the buffered image
            bimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            imageLoaded = true; //now the image is loaded
        } catch (Exception e) {
            System.exit(-1);
        }
    }

    //The filterImage method applies brightness to the image when the knob of the image slider is
    //making changed.
    //When the value of the image slider changes it affects the e variable
    //so the image is brighter or darker
    public void filterImage() {
        float[] elements = {0.0f, 1.0f, 0.0f, -1.0f, e, 1.0f, 0.0f, 0.0f, 0.0f};
        Kernel kernel = new Kernel(3, 3, elements);  //create keynel object to encapsulate the elements array
        ConvolveOp cop = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null); //create ConvolveOp to encapsulate
        //the kernel
        bimg = new BufferedImage(orBufferedImage.getWidth(), orBufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        cop.filter(orBufferedImage, bimg); //start filtering the image
        //the filtered image is stored in the bimg buffered image
        //now the image increases or decreases its brightness


    }

    //set a value to e variable
    //this method is invoked when the user makes change to the  image slider
    public void setValue(float value) {
        e = value;
    }

    //Set a boolean value the actionSlided variable
    public void setActionSlided(boolean value) {
        actionSlided = value;
    }

    //Set a boolean value the actionResized variable
    public void setActionResized(boolean value) {
        actionResized = value;
    }


    //Set a boolean value the actionDraw variable
    public void setActionDraw(boolean value) {
        actionDraw = value;

    }

    public void setActionCropped(boolean value) {
        actionCropped = value;
    }

    public void setActionRotated(boolean value) {
        actionRotated = value;
    }

    public void setActionFiltered(boolean value) {
        actionFiltered = value;
    }

    public void setActionColored(boolean value) {
        actionColored = value;
    }

    /**
     * The createBufferedImageFromImage method can  generate a buffered image from an input image
     *
     * @param image
     * @param width
     * @param height
     * @param tran
     * @return
     */
    public BufferedImage createBufferedImageFromImage(Image image, int width, int height, boolean tran) {
        BufferedImage dest;
        if (tran)
            dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        else
            dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = dest.createGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return dest;
    }


    /**
     * Save the image file
     *
     * @param filename
     */
    public void saveToFile(String filename) {
        String ftype = filename.substring(filename.lastIndexOf('.') + 1);
        try {

            //save the update image
            if (actionSlided || actionColored || actionFiltered || actionCropped || actionResized || actionRotated || drawn)
                ImageIO.write(bimg, ftype, new File(filename));
        } catch (IOException e) {
            System.out.println("Error in saving the file");
        }
    }

    //Assign values to the variables used in drawing text on the image
    public void setText(String text, String fName, int fSize, Color color) {
        textToDraw = text;
        fontName = fName;
        fontSize = fSize;
        if (color == null)
            colorTextDraw = new Color(100, 0, 200);
        else
            colorTextDraw = color;
    }
}

////end of the ImgArea class
