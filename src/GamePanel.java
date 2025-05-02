
//import java.awt.Graphics;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Random;






public class GamePanel extends JPanel implements ActionListener {

//-------------------------- Size --------------------------------//  
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25; // حجم الخلية الواحد في الشاشة
   static final int GAME_UNIT = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
 
     final int x[] = new int [GAME_UNIT]; // مصفوفه جسم الثبان
     final int y[] = new int [GAME_UNIT];  // مصفوفه جسم الثبان
    
    int bodyParts = 5;  // the lanth of snake 
    
//----------------------------------------------------------------//
    static final int DELAY = 300;  // speed
    
    int applesEaten  ;
    int appleX;
    int appleY;
    char direction = 'R';  // المسار
    boolean running = false;
    Timer timer;
    Random random;
    //JButton b ;
    
    GamePanel(){
        random = new Random();
       
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));  // size of panel
        this.setBackground(Color.black);   //background of panel
        
        this.setFocusable(true);  // the focus in panel
        this.addKeyListener(new MyKeyAdapter());  // the key listener
        
       startGame();
    //System.out.println(GAME_UNIT);
        
    }
    
    ///////


////////////////////////////////////////////////////////////////////////
    
    public void startGame(){
        newApple();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }
    
    //------------------------------------------------------------------//
    
    /////////////////
    ////////////////???????????????????????????????????? 
    //              < Graphics >
    
    public void paintComponent(Graphics g){ // fun 1  تلوين
        super.paintComponent(g);
        draw(g);
    }
    
    
    public void draw(Graphics g){  // fun 2
        if(running){
            // كود تخطيط الواجهه
             for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++){
                 g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                 g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
             }
             //كود رسم التفاحة 
             g.setColor(Color.red);
             g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

             // كود رسم الثعبان
             for(int i = 0 ; i < bodyParts ; i++){
                 if(i == 0) {//رأس الثعبان
                     g.setColor(Color.green);
                     g.fillOval(x[i], y[i], UNIT_SIZE , UNIT_SIZE);
                 }
                 else {//جسم الثعبان
                     g.setColor(new Color(45, 180, 0));
                     g.setColor(new Color(random.nextInt(255), random.nextInt(255) , random.nextInt(255) ) );
                     g.fillOval(x[i], y[i], UNIT_SIZE , UNIT_SIZE);
                 }
             }
             //Score text  الدرجات
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free" , Font.BOLD , 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: " +applesEaten , (SCREEN_WIDTH - metrics.stringWidth("Score: " +applesEaten))/2 , g.getFont().getSize());
        }
        else {
            gameOver(g);
            
        }
    }
    
    //                       < /Graphcs > 
    ////////////////////////////////////////////////////////////
      //             
    public void newApple(){ //fun3
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }
    
    public void move(){  // // fun 4
        for(int i = bodyParts ; i>0;i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        
        switch(direction) {
            case'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
                
        }
    }
    public void checkApple(){  // // fun 5
        //كود اكل التفاحة
        if((x[0] == appleX) && (y[0] == appleY) ) {
            bodyParts++;
            applesEaten++;
            newApple();
            
        }
        
    }
    public void checkCollisions(){  // fun 6   // دالة الخسارة
        // checks if head collides whith body !!?
        for(int i = bodyParts; i>0;i--) {
            if((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        // check if head touches left border
        if(x[0] < 0){
            running = false;
        }
        // check if head touches right border
        if(x[0] > SCREEN_WIDTH){
            running = false;
        }
        ///////////////////////////////////////
        /////////////////////////////??????????
        // check if head touches top border
        if(y[0] < 0){
            running = false;
        }
        // check if head touches botton border
        if(y[0] > SCREEN_HEIGHT){
            running = false;
        }
        ////////////////////////////////////////
        ////////////////////////////////????????????????????
        if(!running) {
            timer.stop();
        }
    }
    
    public void gameOver(Graphics g){  // fun 7 

      
        

        //Score text 
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free" , Font.BOLD , 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " +applesEaten , (SCREEN_WIDTH - metrics1.stringWidth("Score: " +applesEaten))/2 , g.getFont().getSize());
        
        //Game Over text 
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free" , Font.BOLD , 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over!!?" , (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/3 , SCREEN_HEIGHT/2);
        
        
        
        //button
//        b= new JButton("play agen");
//        this.add(b); 
   
   
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of generated methods, choose Tools | Templates.
        if(running){
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }
    
    public class MyKeyAdapter extends KeyAdapter{
        // كود تحريك الثعبان
        
        @Override
        public void keyPressed(KeyEvent e){
            
            switch(e.getKeyCode()) {
                
                case KeyEvent.VK_LEFT:
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L'){
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    break;
            }
            
            /*
              ): كود اعادة اللعبة لا يعمل  
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                if(!running){
                    
                    //bodyParts = 0;
                    //applesEaten = 0;
                    //running = true;
                    startGame();
                    
                    GamePanel g = new GamePanel();
                    
//                    g.x[0] =10;
//                    g.y[0] = 10;
                     //move();
                     //timer.start();
                   // checkApple();
                 //checkCollisions();
                 
                    repaint();
                }
                
            }*/
            
        }
    }
}
