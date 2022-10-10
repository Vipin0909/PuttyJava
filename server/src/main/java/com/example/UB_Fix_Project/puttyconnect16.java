// program written by Vipin -16 Aug
// this program will connect to unix server and extract the fix message based on compliance id. Output of this file will be stored in one text file
package puttyJava;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


public class puttyconnect16 {

	/**
	 * JSch Example Tutorial
	 * Java SSH Connection Program
	 */
	public static void main(String[] args) {
	    System.out.println("in main ");
		String host="3.144.74.169";
	    String user="ec2-user";
	    String password="sshpwd";
	    String command1="ls";
	    String complianceID="ABCD1";
	    String privateKeyPath = "C:\\DevOps\\Jenkins_Server_2022.pem";
	    try{
	    	
	    	
	    	java.util.Properties config = new java.util.Properties(); 
	    	config.put("StrictHostKeyChecking", "no");
	    	JSch jsch = new JSch();
	    	jsch.addIdentity(privateKeyPath);
	    	Session session=jsch.getSession(user, host, 22);
	    	session.setPassword(password);
	    	session.setConfig(config);
	    	session.connect();
	    	System.out.println("Connected to server ...");
	    	
	    	Channel channel = session.openChannel("shell");
		    OutputStream ops = channel.getOutputStream();
		    PrintStream ps = new PrintStream(ops, true);

		    channel.connect();
		    InputStream input = channel.getInputStream();
		    ps.println("sudo su -");
		    //ps.println("cd..");
		    ps.println("cd /var/lib/jenkins/workspace/PuttyJavaProject");
		    //ps.println("grep -ai "+complianceID+" FixFile.txt | sed G");
		    
		    ps.println("grep -ai ABCD1 FixFile.txt | sed G");
		    ps.println("grep -ai ABCD2 FixFile.txt | sed G");
		    ps.println("grep -ai ABCD3 FixFile.txt | sed G");
		    //ps.println("grep -l -r \"+complianceID+\" --exclude=*.csv* | xargs cp -t ~/PuttyConnect_Fix_Protocol_Test/webapp/ ");
		    
		    ps.close();
	    	
//	    	Channel channel=session.openChannel("exec");
//	        ((ChannelExec)channel).setCommand(command1);
//	        channel.setInputStream(null);
//	        ((ChannelExec)channel).setErrStream(System.err);
//	        
	        InputStream in=channel.getInputStream();
	        try {
	        	FileWriter file = new FileWriter("Putty\\logs.json");
		        file.flush();
			} catch (Exception e) {
				// TODO: handle exception
			}
	            
		    
//	        channel.connect();
	        byte[] tmp=new byte[1024];
	        while(true){
	          while(in.available()>0){
	            int i=in.read(tmp, 0, 1024);
	            if(i<0)break;
	            System.out.print(new String(tmp, 0, i));
	          }
	          if(channel.isClosed()){
	            System.out.println("exit-status: "+channel.getExitStatus());
	            break;
	          }
	          try{Thread.sleep(1000);}catch(Exception ee){}
	        }
	        channel.disconnect();
	        session.disconnect();
	        //System.out.println("DONE");
	    }catch(Exception e){
	    	e.printStackTrace();
	    }

	}

}