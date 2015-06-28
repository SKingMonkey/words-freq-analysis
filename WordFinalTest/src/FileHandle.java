import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
//import java.io.FileReader;
//import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


public class FileHandle {
	public static void fileWrite(List<WordCount> resultFinal) throws IOException{
		File fileOut = new File("/home/roc/Java/filename.txt");
		   if (!fileOut.exists()) {
		    fileOut.createNewFile();
		   }
		   FileWriter fw = new FileWriter(fileOut.getAbsoluteFile());
		   System.out.println("写入文件到硬盘中。");
		   BufferedWriter bw = new BufferedWriter(fw);
		   Iterator<WordCount> it1 = resultFinal.iterator();
		   int wordNumber = 0;
		   while(it1.hasNext()){
			     WordCount temp11 = new WordCount();
			     temp11 = it1.next();
				 bw.write(temp11.getWord());
				 bw.write(" ");
				 bw.write(String.valueOf(temp11.getCount()));
				 bw.write("\n");
				 wordNumber ++;
			 }
		  System.out.println("文件中的总的词汇数:" + wordNumber);
		   bw.close();
		   System.out.println("Done");
	}
	
	public static String fileRead() throws IOException{
		 BufferedReader br = new BufferedReader(new FileReader("test1M.txt")); 
		 StringBuffer sb = new StringBuffer();   //块处理的时候才使用
		 String temp = br.readLine();
		 while (temp !=  null) { 
			temp = br.readLine();
			sb.append(temp + " ");
		 }
		 br.close();
		 return sb.toString();
	}
}
