package kapil.umix;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
public class SongTiles extends JPanel {
 
	//objects are serializable and to avoid classexerror error in eclipse 
	private static final long serialVersionUID=1l;
	private static final String delimiter=",";
	private boolean songPlaying=false;
	private String playingSongName;
	private int currentSongId;
	private boolean songPaused;
	private String pausedSongName;
	private JLabel songNameLabel;

	ArrayList<String> buttonIdList;
	//constructor
	public SongTiles(String songName,int id)
	{System.out.println("sd");
	setLayout(new BorderLayout());
	
	setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		setBackground(new Color(200,100,100));
		setBorder(BorderFactory.createLineBorder(Color.white,2));
		this.setsongNameLabel(songName);
	
	
	//	this.add(this.songName);//add above jlabel to your jpanel
		
		//this.add(this.play);
		this.add(songNameLabel);
	//	this.add(this.songName);
		
	}
	
	
	
	
	private void setsongNameLabel(String songName)
	{
		songNameLabel = new JLabel("Time Starts now", JLabel.CENTER);
		songNameLabel.setFont(new Font("SERIF", Font.BOLD, 25));
		songNameLabel.setText(songName);
		
		//this.songName=new JLabel(songName);//give songname to jlabel
		
	}
	
	
	
	//adding components to panel
	
	//background of selected song tile
	public void setSongPlayingBackground()
	{   
		this.setBackground( new Color(0,150,0));
		this.setBorder(BorderFactory.createLineBorder(Color.blue,3));
		}
	
	public void setSongNotPlayingBackground()
	{
		this.setBackground( new Color(100,100,100));
		this.setBorder(BorderFactory.createLineBorder(Color.white,2));
		
		
		
	}
	
	
}
