
import java.awt.HeadlessException;
import javax.swing.JFrame;


public class GameFrame extends JFrame{

     GameFrame(){
     GamePanel d = new GamePanel();
    
        this.add(new GamePanel());  // Add Panel !!?
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit when close the form 
        this.setResizable(false);   //Resizeble
        
        
        this.pack(); // ????????????? 1
        
        
        this.setVisible(true);   
        this.setLocationRelativeTo(null);       //Location
        
    }
   
    
}
