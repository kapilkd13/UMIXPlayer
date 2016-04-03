package kapil.umix;
import java.util.ArrayList;
public class playerchooser implements Runnable {
public static ArrayList<String> songAddressArrayList=null;
//ArrayList<String>addlist=songAddressArrayList;
public static String currentPlayer=null;
public static String lastPlayer=null;
public static int currentSongName;//to check if the current song is only paused or forwarded and reworsed ie options other than p;ay should work only if the current song is same as the next song
public static String songAddress;
public static Object Player;
private int songNo;
public playerchooser()
{
if(songAddressArrayList==null)
	songAddressArrayList=(new UMIXPlayer()).getSongAddressArrayList();

}



public playerchooser(int song)
{
if(songAddressArrayList==null)
	songAddressArrayList=(new UMIXPlayer()).getSongAddressArrayList();

songNo=song;
}




public playerchooser(String fileadd,String fileext,ArrayList<String> songAddressArrayList)
{playerchooser.songAddressArrayList=songAddressArrayList;
	/*if(fileext.equals("mid")||fileext.equals("midi"))
	{	playmidi obj=new playmidi();
	obj.play(fileadd,this);
	try{Thread.sleep(10000);}
	catch(Exception ex)
	{}
	 
	}  */
	
	if(fileext.equals("mp3"))
	{
	
	try{Thread.sleep(10000);}
	catch(Exception ex)
	{System.out.println("problem while making a thread sleep");}
	 
	}
	
	
}

public void run()
{
	play(songNo);

	
}


	public void play(int songNo)
	{System.out.println("ddddd");
		doChanges(songNo);
		//ore if statements if new player is added
		if(currentPlayer=="playmp3")
		{System.out.println(songAddress);
			 playmp3 player=new playmp3(songAddress);
			// player.play();
			 Player=player;
			 System.out.println("dddd888d");
		}
	}
	
	
	private void doChanges(int songNo)
	{if(currentPlayer!=null)
		lastPlayer=currentPlayer;
		songAddress=songAddressArrayList.get(songNo);
	String extension=getextension(songAddress);
	setCurrentPlayer(getPlayerName(extension));
	}
	

public static void  setCurrentPlayer(String playername)
{
	
currentPlayer=playername;
}
private String getPlayerName(String extension)
{String player="playmp3";// setting playmp3 as default if nothing works
if(extension=="midi"||extension=="mid")
{player="playmidi";
	}
else if(extension=="mp3")
	player= "playmp3";
return player;
}
	
private String getextension(String filename)
{
int lastpoint= filename.lastIndexOf(".");
String ext=filename.substring(lastpoint+1);
//System.out.println(ext);

return ext;
}
	



 	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
