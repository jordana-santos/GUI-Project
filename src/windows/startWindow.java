package windows;
import java.awt.HeadlessException;
public class startWindow {
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Simple Windows Sample Running ...");
        try {
            (new mainWindow()).start();
        } catch (HeadlessException headlessException) {
            System.out.println("Operating System Graphic Environment (window system) not detected.");
        } catch (Exception exceptionValue) {
            System.out.println("An unexpected exception has been launched.");
            System.out.println("Message: " + exceptionValue.getMessage() + "\n\n");
            exceptionValue.printStackTrace();
        }
        System.out.println("Simple Windows Sample closed!");
    }
}
