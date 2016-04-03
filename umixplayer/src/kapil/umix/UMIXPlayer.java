package kapil.umix;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
//parsing need to be done as windows system of segregation file path is different thAN LINUX AND not allowed in java
//dont know if a class already exist for parsing if better method available then replace it i used this because of laziness to look up
	//could have been done in using // but we used //// both are similiar but later is not found usually and less chance of error

public class UMIXPlayer {
	public final String delimiter=",";
	public static final  String audioFileListName="songlistfile.csv";
	public static boolean audioFileListExist=false;
	private	BufferedWriter audiofilewriter;//filew riter
	private BufferedReader audiofilereader;//file reader
	static int noofsongs=0,songno=0;//may be used in future for separating file 
	public String songname="null", filecontentline,songAddress;//current song address
	   public static	ArrayList<String> songarraylist;
	   public static ArrayList<String>songAddressArrayList;
    public ArrayList<String> dirList;
    public ArrayList<String> songNameList;
	//public static Object player;
	
	public static boolean playingSong=false;
	
	
	
	
	public static void main(String[] args){
		
		
	UMIXPlayer start= new UMIXPlayer();
	start.checkAudioListFileExistence(audioFileListName);
		if(audioFileListExist==true)
		{
			start.opensonglist();
		GuiHandling guihandler=new GuiHandling(start.getSongNameList());
		
		}
		else
	start.createAudioFile();
		
	//listing main directories like C,D,E
		
	//	createGUI firstInterface=new createGUI("audiofile.csv");
//	
	// put it on refresh button
	//start.refreshSongList();
	}
	
	private void checkAudioListFileExistence(String fname)
	{
		if( new File(fname).exists())
		audioFileListExist=true;
	}
	//check if the audio file exist
	





private void createAudioFile()
{
try{audiofilewriter= new BufferedWriter(new FileWriter(audioFileListName));
	
	
	}
	catch(Exception ex)
		{
		ex.printStackTrace();
		
		
		}

	 dirList=listingMainDirectory();//like c,d,e drive
	//intoEachDirectory(dirList);
	getfile("/media/kapil/f8409f55-2df4-4c9c-a51b-5f38d0261638/kapil/songs");
	
	try{
		audiofilewriter.close();
		}
		catch(Exception ex)
		{System.out.println("problem in closing file");
			}

}

//listing main directory
private ArrayList<String> listingMainDirectory()
{
	ArrayList<String> dirList=new ArrayList<String>();
	File[] dir=File.listRoots();
for(int i=0;i<dir.length;i++)
{//System.out.println(dir[i].getAbsolutePath());
String a=dir[i].getAbsolutePath();
a=getParsedAddress(a);
//System.out.println(a);
dirList.add(a);
}
return dirList;
	}



private  void intoEachDirectory(ArrayList<String> dirList)
{
for(String dirname:dirList)
{System.out.println(dirname);
	getfile(dirname);
	}
try{
audiofilewriter.close();
}
catch(Exception ex)
{System.out.println("problem in closing file");
	}
}

private void getfile(String path)//receiving main directory path
{
File fileContainer= new File(path);		
System.out.println(path);

		File[] flist=fileContainer.listFiles();
		if(flist!=null)
	{for(File anyfile:flist)
	{//	System.out.println(n.getName());
	if(anyfile.isFile())
		//send file for comparison o know if its a audio file
		//get name separates name from path of a file
	{if(compareext(anyfile.getName()))
		{//System.out.println(n.getName());
		//onlyfilepath.add(n.getAbsolutePath().replaceAll("\\\\","/"));
		String pathtofile=getParsedAddress(anyfile.getAbsolutePath());
		writeSongPathToFile(pathtofile);
	//if path contains comma replace it with othercharacter than again replace it to origunal after abstraction
		}
	}//file if
		
	else if(anyfile.isDirectory())
	{//if given address has a directory again send it to get file (recursion)
		getfile(getParsedAddress(anyfile.getAbsolutePath()));
		
		
	//}
	}//file dir
	
	}//for loop
	}
	
	
		//System.out.println(folder.getParent());
}

private void writeSongPathToFile(String path)
{
	try{	
		System.out.println("writting");
		//System.out.println(anyfile.getAbsolutePath().replaceAll("\\\\","/"));
		
			audiofilewriter.write(++noofsongs+delimiter+path +delimiter+getextension(path));
			System.out.println("written");
			
			audiofilewriter.newLine();
		}catch(Exception ex)
		{
			System.out.println("problem in creating and writing to file");
			
		}
	
}
//to get parsed address public as it may be used in song clasd
public String getParsedAddress(String address)
{
	return(address.replaceAll("\\\\","/"));
	
}
public  void refreshSongList()
{
	if(new File(audioFileListName).exists())
	{new File(audioFileListName).delete();//future log file insert
}
createAudioFile();

}
private String extractSongName(String songAddress)
{
	int lastpoint=songAddress.lastIndexOf("/");
	String songName=songAddress.substring(lastpoint+1);
	return songName;
}



public void opensonglist()
{
/*for(String fname:onlyfilepath)
{	System.out.println(fname);

new playerchooser(fname,checkextension(fname));
}*/try{songAddressArrayList=new ArrayList<String>();
	songarraylist= new ArrayList<String>();
	songNameList= new ArrayList<String>();
	audiofilereader=new BufferedReader(new FileReader(audioFileListName));
	//songno=audiofilereader.read();
	//System.out.println("vfdbfgbfgbs");
	while((filecontentline=audiofilereader.readLine())!=null)
	{ //System.out.println("vfdbfgbfgbs");
	String songinfo[]=filecontentline.split(",");
	  songAddress=songinfo[1];
	//work to be done here after gui setup
	  ///media/kapil/f8409f55-2df4-4c9c-a51b-5f38d0261638/kapil/songs/Sachin-Jigar/Happy Ending (Original Motion Picture Soundtrack)/Haseena Tu Kameena Mein.mp3
	//  new playmp3("/media/kapil/f8409f55-2df4-4c9c-a51b-5f38d0261638/kapil/songs/Sachin-Jigar/Happy Ending (Original Motion Picture Soundtrack)/01 Tere Hoke Rehengay.mp3");
	  //new playmp3(songAddress);
	songname=extractSongName(songAddress);//System.out.println("vfdbfgbfgbs");
	addToSongNameList(songname);
	
	songarraylist.add(songname);
	songAddressArrayList.add(songAddress);
	System.out.println(songname);	
//have to change this method once gui get implemented
	//work to be done here after gui setup
	//new playerchooser(songAddress,getextension(songname),songAddressArrayList);
	}
}catch(Exception ex)
{
System.out.println("prob in opening and reading file");	
}
System.out.println(songno+" "+songname);	

}


private String getextension(String filename)
{
int lastpoint= filename.lastIndexOf(".");
String ext=filename.substring(lastpoint+1);
//System.out.println(ext);

return ext;
}

private boolean compareext(String filename)
{//
String extension=getextension(filename);
//System.out.println(extension+"asd");

 if(//extension.equals("wav")||
	//	 extension.equals("aac")||
		// extension.equals("amr")||
	//	 extension.equals("au")||
		// extension.equals("awb")||
		 extension.equals("mp3")||
		// extension.equals("mpc")||
		// extension.equals("ogg")||
		// extension.equals("oga")||
		 extension.equals("wma")||
		// extension.equals("wv") ||
		// extension.equals("webm")||
		 extension.equals("mid")   //||
		// extension.equals("ra")||
		// extension.equals("ram")||
		// extension.equals("rm")
		 )
	 return true;
else
	return false;

}

	
	//to write in file
	
public  ArrayList<String> getSongNameList()
{
	return(songNameList);}

private  void addToSongNameList(String songName)
{songNameList.add(songName);
	}

public ArrayList<String> getSongAddressArrayList()
{return(songAddressArrayList);}

}//class ends