package app.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ActorFile {
 
	protected File file;
	protected PrintWriter writer;
	protected BufferedReader reader;
	protected int lastRead = 0;
	
	 
	public ActorFile(){
		this.file = new File("davscore.dat");
	}
	 
	public String[][] readFile() {
		// TODO Auto-generated method stub
		String[][] ret = new String[2][10];
		if(this.file.exists() && !this.file.isDirectory() && this.file.length()>0){
			String line = "";
			try {
				this.reader = new BufferedReader(new FileReader(this.file));
				while((line = this.reader.readLine()) != null){
					StringTokenizer s = new StringTokenizer(line, ":");
					String pName = s.nextToken();
					String pScore = s.nextToken();
					ret[0][this.lastRead] = pName;
					ret[1][this.lastRead] = pScore;
					this.lastRead++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;
	}

	public boolean saveScore(String[][] scores, int size) {
		try {
			this.writer = new PrintWriter(new FileWriter(this.file, false));
			for(int i = 0; i< size; i++)
				this.writer.println(scores[0][i]+":"+new Integer(scores[1][i]).toString());
			this.writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
		
	}

	public int getLastRead() {
		return this.lastRead;
	}
	 
}
 
