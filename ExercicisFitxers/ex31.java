package ExercicisFitxers;


import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class ex31 {
    public static void main(String[] args){

        int codi = ThreadLocalRandom.current().nextInt(1, 4);
        String file = "arxiu.txt";
         try {

        File f = new File(file);
        DataOutputStream out = new DataOutputStream(new FileOutputStream(f));
        
        for (int i = 0; i < 10; i++){
            String secret = "";
            for (int l = 0; l < 3; l++) {
                char lletra = (char) ThreadLocalRandom.current().nextInt('a', 'z' + 1);
                secret += lletra;
            }
            System.out.println(codi + " " + secret);
            codi += ThreadLocalRandom.current().nextInt(1, 4);
            


           
                out.writeInt(codi);
                out.writeChars(secret);
    
    

        }

        out.close();

    
    } catch (IOException e) {
        e.printStackTrace();
    }


        


    }

    
}
