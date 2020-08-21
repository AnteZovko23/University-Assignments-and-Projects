import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Image;

/**
 * Ante Zovko
 * 
 * Simulates a flocking algorithm
 * 
 * Version: May 21, 2020
 * 
 */
public class Sim extends JPanel implements ActionListener{

    public static final int WIDTH = 900;
    public static final int HEIGHT = 900;

    public static Random rand = new Random();

    private static final long serialVersionUID = 1L;
    Timer redrawTimer = new Timer(33, this);

    ArrayList<Boid> boids = new ArrayList<>();

    Image bufferImage;
    Graphics bufferGraphics;

    public static void main(String[] args) {
        
        Sim s = new Sim();
        JFrame f = new JFrame();

        f.setTitle("Boid Simulation");
        f.setSize(WIDTH, HEIGHT);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add canvas to window
        f.add(s);
        s.setBackground(Color.white);

        s.createFlock();

    }

    public void createFlock(){
        
        int numBoids = 100;

        for(int i = 0; i < numBoids; i++){
            Boid b = new Boid();
            boids.add(b);
        }

       
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.setColor(Color.BLACK);
        
        for (Boid b:boids){
            g.drawLine((int) b.px, (int) b.py, (int)(b.px + b.vx), (int)(b.py + b.vy));
        }

        redrawTimer.start();

    }

    @Override
    public void update(Graphics g){

        if(bufferImage == null){
            bufferImage = createImage(WIDTH, HEIGHT);
            bufferGraphics = bufferImage.getGraphics();
        }

        bufferGraphics.setColor(getBackground());
        bufferGraphics.fillRect(0, 0, WIDTH, HEIGHT);
        bufferGraphics.setColor(getForeground());
        paint(bufferGraphics);

        g.drawImage(bufferImage, 0, 0, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(Boid b:boids){
            b.move(boids);
        }
        repaint();

    }

    class Boid {
        //each boid has a position and a velocity
        double px, py;
        double vx, vy;
        double maxVel = 5;
        double ax,ay;

        public Boid(){

            px = Sim.rand.nextInt(Sim.WIDTH);
            py = Sim.rand.nextInt(Sim.HEIGHT);

            vx = Sim.rand.nextInt(2*(int)maxVel) - maxVel;
            vy = Sim.rand.nextInt(2*(int)maxVel) - maxVel;

        }
        public void move(List<Boid> boids){

            px += vx;
            py += vy;

            steer(boids);

            vx += ax;
            vy += ay;

            double magnitude = Math.sqrt(vx*vx+vy*vy);
            if(magnitude > maxVel){
                vx = (vx/magnitude) * maxVel;
                vy = (vy/magnitude) * maxVel;
            }

            if(px > Sim.WIDTH){
                px = 0;            
            }
            else if(px < 0){
                px = Sim.WIDTH;
            }

            if(py > Sim.HEIGHT){
                py = 0;
            }
            else if(py < 0){
                py = Sim.HEIGHT;
            }

            ax = 0;
            ay = 0;


        }

        public void steer(List<Boid> boids){
            double sep[] = separate(boids);
            double aln[] = align(boids);
            double coh[] = cohere(boids);

            ax = sep[0] + aln[0] + coh[0];
            ay = sep[1]+ aln[1] + coh[1];
        }

        double[] separate(List<Boid> boids){

            double targetDistance = 100;

            double sumX = 0;
            double sumY = 0;

            int count = 0;

            for(Boid b: boids){
                double dist = Math.sqrt((b.px - px)*(b.px - px) + (b.py-py)*(b.py - py));
                if((dist > 0) && (dist < targetDistance)){
                    sumX  += (px - b.px)/dist;
                    sumY  += (py - b.py)/dist;

                    count++;

                }
            }
            
            double sep[] = new double[2];
            sep[0] = 0;
            sep[1] = 1;
            if(count > 0){
                
                sep[0] = sumX/count;
                sep[1] = sumY/count;

            }
            
            return sep;
        }

        double[] align(List<Boid> boids){
            double targetDistance = 50;
            double sumX = 0;
            double sumY = 0;

            int count = 0;

            for(Boid b: boids){
                double dist = Math.sqrt((b.px - px)*(b.px - px) + (b.py-py)*(b.py - py));
                if((dist > 0) && (dist < targetDistance)){
                    sumX = b.vx;
                    sumY = b.vy;
                    count++;
                }
            }

            double[] align = new double[2];

            align[0] = 0;
            align[1] = 0;

            if(count > 0){
                align[0] = sumX/count;
                align[1] = sumY/count;
            }

            return align;

        }

        double[] cohere(List<Boid> boids){
            double targetDistance = 200;

            double sumX = 0;
            double sumY = 0;

            int count = 0;

            for(Boid b:boids){
                double dist = Math.sqrt((b.px - px)*(b.px - px) + (b.py-py)*(b.py - py));
                
                if((dist > 0) && (dist < targetDistance)){
                    sumX += b.px;
                    sumY += b.py;
                    count++;
                }
            }

            double coh[] = new double[2];

            coh[0] = 0;
            coh[1] = 0;

            double cmX = 0;
            double cmY = 0;

            if(count > 0){
                cmX = sumX/count;
                cmY = sumY/count;

                double dist = Math.sqrt((cmX - px)*(cmX - px) + (cmY-py)*(cmY - py));
                coh[0] = (cmX-px)/dist;
                coh[1] = (cmY-py)/dist;
            }

            return coh;

        }
        
        
    }

}