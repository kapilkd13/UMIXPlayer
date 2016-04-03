/*tasks
 * panel with circular edges
 * change color of panels
 * see for playing next song automatically with shuffle off and on
 * finding alternative to destroy thread or a way to pause song
 * impleenting hibernate here
 */

package kapil.umix;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;





public class GuiHandling {
	  
	private static final String delimiter=",";
	   public static ArrayList<String> songNameArrayList;
	   public static ArrayList<SongTiles> songList=new ArrayList<SongTiles>();
	   public static  SongTiles prevSong=null;
	   private boolean prevCommand=false;
	   private boolean nextCommand=false;
	   private boolean playCommand=false;
	   private boolean pauseCommand=false;
	   private Thread lastThread;
	   private Thread nextThread;
	   public String threadName="threadName";//if later want to name the thread also thats why public
	public int songUnderFocus=-1; 
	public int prevselectedsongno=-1;
	//framing vars
	JFrame frame = new JFrame();
	JPanel mainFrame=new JPanel();	
	JPanel header=new JPanel();	
	JPanel optionBar=new JPanel();
	JPanel buttonBar=new JPanel();	
	
	JLabel musicPlayername;
	JLabel prevButtonLabel;
	JLabel nextButtonLabel;
	JLabel playButtonLabel;
	JLabel pauseButtonLabel;
	JLabel playList;
		JButton prev=new JButton();
		JButton next=new JButton();
		JButton play=new JButton();
	 	JButton pause=new JButton();
	
	 	
	
	   public GuiHandling( ArrayList<String> songName)
	{songNameArrayList=songName;
		makeList();
	
	}
	
	public GuiHandling( )
	{//empty constructor
		
	} 
	
	
	//constructor invoked by songtiles on button press
public void  sendUserClickedSongInfo(String buttonid)
{
	if(prevSong!=null)
		prevSong.setSongNotPlayingBackground();

System.out.println(buttonid);
String command[]=buttonid.split(",");
	extractInput(command[1]);
actOnInput(command[0]);
}

private void extractInput(String buttoncode)
{
	System.out.println(buttoncode);

if(buttoncode.equals("pre"))
	prevCommand=true;
else if(buttoncode.equals("next"))
	nextCommand=true;
else if(buttoncode.equals("play"))
	{playCommand=true;
	System.out.println(buttoncode);

	
	}
else if(buttoncode.equals("pause"))
	pauseCommand=true;

}

@SuppressWarnings("deprecation")//stoping a thread will throw threaddeath exception
public void actOnInput(String songNo)
{

	if(playCommand==true)
	{lastThread=nextThread;
	if(lastThread!=null)
	{	//lastThread.destroy();
		try{lastThread.wait(1000);}
		catch(Exception e)
		{}}
		nextThread=new Thread(new playerchooser((Integer.parseInt(songNo))),threadName);
		nextThread.start();
		
		//new playerchooser().play((Integer.parseInt(songNo)-1));
	System.out.println(songNo);
	}
	//else if(pa)
	 prevCommand=false;
	 nextCommand=false;
	 playCommand=false;
	 pauseCommand=false;
}
	
private void makeList()
{
int i;
//String song;


musicPlayername = new JLabel("Time Starts now", JLabel.CENTER);
musicPlayername.setFont(new Font("SERIF", Font.BOLD, 30));
musicPlayername.setText("UMIXPlayer");

prevButtonLabel = new JLabel("Time Starts now", JLabel.CENTER);
prevButtonLabel.setFont(new Font("SERIF", Font.BOLD, 30));
prevButtonLabel.setText("prev");

nextButtonLabel = new JLabel("Time Starts now", JLabel.CENTER);
nextButtonLabel.setFont(new Font("SERIF", Font.BOLD, 30));
nextButtonLabel.setText("next");

playButtonLabel = new JLabel("Time Starts now", JLabel.CENTER);
playButtonLabel.setFont(new Font("SERIF", Font.BOLD, 30));
playButtonLabel.setText("play");

pauseButtonLabel = new JLabel("Time Starts now", JLabel.CENTER);
pauseButtonLabel.setFont(new Font("SERIF", Font.BOLD, 30));
pauseButtonLabel.setText("pause");

playList = new JLabel("Time Starts now", JLabel.CENTER);
playList.setFont(new Font("SERIF", Font.BOLD, 30));
playList.setText("Your Playlist");

header.add(musicPlayername);
prev.add(prevButtonLabel);
next.add(nextButtonLabel);
play.add(playButtonLabel);
pause.add(pauseButtonLabel);

//addi nng buttons on bar
buttonBar.add(prev);
buttonBar.add(play);
buttonBar.add(pause);
buttonBar.add(next);

prev.addActionListener(new buttonclass(1));
play.addActionListener(new buttonclass(2));
pause.addActionListener(new buttonclass(3));
next.addActionListener(new buttonclass(4));

//adding oplaylist option bar
optionBar.add(playList);
optionBar.setPreferredSize(new Dimension(250,100));

buttonBar.setLayout(new GridLayout(0,4));
header.setBorder(BorderFactory.createLoweredBevelBorder());
mainFrame.setLayout(new BoxLayout(mainFrame,BoxLayout.Y_AXIS));
optionBar.setLayout(new BoxLayout(optionBar,BoxLayout.Y_AXIS));

for(i=0;i<songNameArrayList.size();i++)
 {//
	 SongTiles s=new SongTiles(songNameArrayList.get(i),i);
	// s.setPreferredSize(new Dimension(500,80));
	 s.setMaximumSize(new Dimension(1000,200));
	 s.setMinimumSize(new Dimension(1000,200));
	 s.addMouseListener(new listener(i));
	 songList.add(s);
	 mainFrame.add(s);
	 
 }System.out.println("sd");
//scroller.add(mainFrame);
JScrollPane scroller=new JScrollPane(mainFrame);
scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
scroller.setPreferredSize(new Dimension(1000,1000));
frame.getContentPane().add(BorderLayout.NORTH,header);
frame.getContentPane().add(BorderLayout.WEST,optionBar);
frame.getContentPane().add(BorderLayout.SOUTH,buttonBar);
frame.getContentPane().add(BorderLayout.CENTER,scroller);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//frame.setSize(600,600);
frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
frame.setVisible(true);

}

	
	//first we need to extend component class to create a list bar which contains name of song play pause next pre button
	//in case of of action event we need to extend it so that it send button code information so that we know which button was pressed
	//by receiving a button code from which we can retrieve other information
//button =new Jbutton();
	//string id; we can create a create id method which take play pause next pre and create string id
	//button.putClientProperty("buttonid",id);
	// button.addActionListner(.....);
	//actionperformed(ActionEvent e)
	// string id=((JButton)e.getSource()).getClientProperty("buttonid");
	//now separate and perform any function

class listener extends MouseAdapter
{int selectedsongno;
	public listener(int selectedsongno)
	{
	
	this.selectedsongno=selectedsongno;
	
	}
	public listener()
	{super();}

	private void showselected()
	{SongTiles p;
	
		p=(SongTiles)songList.get(selectedsongno);
		p.setBorder(BorderFactory.createLineBorder(Color.blue,4));
		//System.out.println("kkasasasas"+prevselectedsongno);		
}
	private void deselected()
	{SongTiles p;
		if(prevselectedsongno!=-1)
	{p=(SongTiles)songList.get(prevselectedsongno);
	//System.out.println("asas12asas"+prevselectedsongno);
	p.setBorder(BorderFactory.createLineBorder(Color.black,4));
	//prevselectedsongno=selectedsongno;
	//System.out.println("asas34asas"+prevselectedsongno);
	//System.out.println(selectedsongno);
				}
	}
	
	public void mouseClicked(MouseEvent e)
	{songUnderFocus=selectedsongno;
		deselected();
		showselected();
		
		prevselectedsongno=selectedsongno;
	
	mainFrame.repaint();
	
	}
	
}


//buttonclass //inner class buttonclass implementation
class buttonclass implements ActionListener
{int buttonid;
String choosedSongData;
public buttonclass(int buttonid)
{this.buttonid=buttonid;}


	
	
	
	public void actionPerformed(ActionEvent event)
	{//to obtain buttonid on nutton click
	//	SongTiles.this.setSongPlayingBackground();
	
		if(songUnderFocus!=-1)
		{
			if(buttonid==1)
		{//start war
			choosedSongData=songUnderFocus+delimiter+"prev";
			sendUserClickedSongInfo(choosedSongData);
		
		
				mainFrame.repaint();
		
	
		}
		
		else if(buttonid==2)
		{//right
			

			choosedSongData=songUnderFocus+delimiter+"play";
			sendUserClickedSongInfo(choosedSongData);
		
		
				mainFrame.repaint();
		}
		else if(buttonid==3)
		{
			choosedSongData=songUnderFocus+delimiter+"pause";
			sendUserClickedSongInfo(choosedSongData);
		
		
				mainFrame.repaint();
		}
		else if(buttonid==4)		
			///	String buttonid=(String)((JButton)event.getSource()).getClientProperty("buttonId");
			
		{
			choosedSongData=songUnderFocus+delimiter+"next";
			sendUserClickedSongInfo(choosedSongData);
		
		
				mainFrame.repaint();
		}
		}
		mainFrame.repaint();
	
	
	}}
}