package windows;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
public class AnimatedShapePanel extends JPanel {
    private int x = 0, y = 0;
    private int xSpeed = 5, ySpeed = 5;
    private int width = 20, height = 20;
    private Timer timer;
    private Color shapeColor = Color.RED;
    private String movementPattern = "Straight";
    private int speedLevel = 1;
    public AnimatedShapePanel() {
        timer = new Timer(getDelayForSpeed(speedLevel), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateShapePosition();
            }
        });
        timer.start();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(shapeColor);
        g2d.fillOval(x, y, width, height);
    }
    private void updateShapePosition() {
        switch (movementPattern) {
            case "Diagonal":
                updateDiagonalMovement();
                break;
            case "Straight":
            default:
                updateStraightMovement();
                break;
        }
        repaint();
    }
    private void updateStraightMovement() {
        if (x + width >= getWidth() || x < 0) {
            xSpeed = -xSpeed;
        }
        x += xSpeed;
    }
    private void updateDiagonalMovement() {
        if (x + width >= getWidth() || x < 0) {
            xSpeed = -xSpeed;
        }
        if (y + height >= getHeight() || y < 0) {
            ySpeed = -ySpeed;
        }
        x += xSpeed;
        y += ySpeed;
    }
    public void setSpeedLevel(int level) {
        this.speedLevel = level;
        if (level == 0) {
            timer.stop();
        } else {
            timer.setDelay(getDelayForSpeed(level));
            if (!timer.isRunning()) {
                timer.start();
            }
        }
    }
    public int getSpeedLevel() {
        return speedLevel;
    }
    private int getDelayForSpeed(int level) {
        switch (level) {
            case 2:
                return 250;
            case 3:
                return 125;
            case 1:
            default:
                return 500;
        }
    }
    public void setShapeColor(Color newColor) {
        this.shapeColor = newColor;
        repaint();
    }
    public Color getShapeColor() {
        return shapeColor;
    }
    public void setMovementPattern(String pattern) {
        this.movementPattern = pattern;
    }
}
