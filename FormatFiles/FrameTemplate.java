package FormatFiles;

import java.awt.*;
import javax.swing.*;


/**
 *  This is a child class of JFrame which has its own format according
 *      to the author's liking.
 * 
 *  @author HEPHZI TOLENTINO
 *  @author FRANCINE SANTOS
 */
public class FrameTemplate extends JFrame{
    /**
     *  Constructs a new frame with the properties set according to
     *      authors' liking.
     * 
     *  @param name The name of the frame.
     *  @param width The width of the frame.
     *  @param height The height of the frame.
     */
    public FrameTemplate(String name, int width, int height) {
        super(name);

        this.setSize(width, height);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        initialize();
    }


    
    /**
     *  Overriding of initialize() method.
     */
    protected void initialize() {
    }
}