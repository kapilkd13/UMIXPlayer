package kapil.umix;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.player.advanced.*;

public class playmp3 //extends Player 
{
	private AdvancedPlayer mp3player;
	private String fileaddress;
	
public playmp3(String fileaddress)
{this.fileaddress=fileaddress;
play();
}
public void play()
{ System.out.println(fileaddress);
try{FileInputStream  f=new FileInputStream(fileaddress);
 mp3player= new AdvancedPlayer(f);
 mp3player.play();
 System.out.println("mp3 found0000");

}
catch(FileNotFoundException ex)
{System.out.println("file not found");}
catch(Exception e)
{System.out.println("something else went wrong");}

try{Thread.sleep(10);}
catch(Exception ex)
{}
 


}

	
	
}



