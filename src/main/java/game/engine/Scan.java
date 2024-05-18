package game.engine;
import java.util.Scanner;
public class Scan {
    Scanner sc;
    public Scan() {
        sc = new Scanner(System.in);
    }
    public String string(){
        return sc.nextLine();
    }
    public int num(){
        return sc.nextInt();
    }

}
