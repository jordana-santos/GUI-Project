package windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnimatedShapePanel extends JPanel{
    //initial position and speed
    private int x = 0, y = 0;
    private int xSpeed = 5, ySpeed = 5;
    private int width = 20, height = 20;
    private Timer timer;
    private Color shapeColor = Color.RED;

    public AnimatedShapePanel(){
        //setting up the timer to update the animation every 50ms
        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateShapePosition();
            }
        });
        timer.start(); //start the animation
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(shapeColor);
        g2d.fillOval(x, y, width, height);
    }

    private void updateShapePosition(){
        if (x+width >= getWidth() || x < 0){
            xSpeed = -xSpeed; //reverse direction when hitting the edges
        }
        if (y+height >= getHeight() || y < 0){
            ySpeed = -ySpeed; //reverse vertically
        }
        //update x and y coordinates
        x += xSpeed;
        y += ySpeed;

        repaint();
    }

    //getters
    public Color getShapeColor(){ return shapeColor; }
    public int getxSpeed(){ return xSpeed; }
    public int getySpeed(){ return ySpeed; }

    //setter
    public void setShapeColor(Color newColor){
        this.shapeColor = newColor;
        repaint();
    }
    public void setxSpeed(int newxSpeed){ this.xSpeed = newxSpeed; }
    public void setySpeed(int newySpeed){ this.ySpeed = newySpeed; }

}
