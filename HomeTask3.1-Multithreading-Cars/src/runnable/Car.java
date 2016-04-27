package runnable;
import org.apache.log4j.Logger;

public class Car implements Runnable {
        private static final long MAX_DISTANCE = 10000;
       // Logger log = Logger.getLogger(getClass());
        private long speed; 
        private long distance; 
        private String name;
		private static boolean imWinner = false;
        
        private static long TIME = 100;
        
        public Car(String name, long speed) {
            this.name = name;
            this.speed = speed;
            
        }
        @Override
        public void run() {
            try {
            	
            	 
                while (distance < MAX_DISTANCE) {
                    Thread.sleep(TIME);
                  //probability the car breaks down
                    //!!the higher the speed , the more probability the car breaks down!!
               	 boolean isDisqualify = Math.random() <= (speed*0.0001);
                    System.out.println(name + " " + distance);
                    distance += speed*TIME*0.01;
                   
                    if (isDisqualify) { 
                        Thread.currentThread().interrupt(); 
                    }
                }
                synchronized (Car.class) {
                    if (!imWinner ) {
                        imWinner = true; 

                        System.out.println("Winner is " + name);
                    }
                }
            } catch (InterruptedException e) {
            	System.out.println(name +" break :((( ");
            }
         
        }
    }
