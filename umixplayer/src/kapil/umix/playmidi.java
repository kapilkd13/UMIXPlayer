package kapil.umix; 

import java.io.*;



import javax.sound.midi.*;

public class playmidi  //extends player
{
	
	
	public void play(String fileaddress,playerchooser pc)
	{try{Sequencer sequencer= MidiSystem.getSequencer();
	sequencer.addMetaEventListener(new MetaEventListener(){
		public void meta(MetaMessage msg)
		{
			//if((msg.getType()==47))
			//pc.next();
		}
		
	});
	System.out.println("sequencer found");
	sequencer.open();
	  InputStream is = new BufferedInputStream(new FileInputStream(new File(fileaddress)));

	 sequencer.setSequence(is); 
	 sequencer.start();


	}
	catch(MidiUnavailableException ex)
	{System.out.println("midi non available ");}
	catch(Exception ex)
	{System.out.println("these issues will be resolved");}}

	
	
      }


