import java.io.IOException;
import java.util.*; 
public class WordTest {
	public static void main(String[] args) throws IOException {
		long time1, time2;
		long readt = 0;
		 WordCountService ws = new WordCountService();
		 ws.initCharTree();
		 List<WordCount> resultFinal = new ArrayList<WordCount>();
		 time1 = System.currentTimeMillis();
		 String tempRead = FileHandle.fileRead();
		 time2 = System.currentTimeMillis();
		 readt = time2-time1;
		 ws.geneCharTree(tempRead);
		 time1 = System.nanoTime(); 
		 resultFinal = ws.getwordCount();
		 time2 = System.nanoTime();
		 long sortt = time2 - time1;
		 System.out.println("排序生成时间：" + (sortt/1000000));		
		 time1 = System.nanoTime(); 
		 FileHandle.fileWrite(resultFinal);
		 time2 = System.nanoTime();
		 long outputt = time2 - time1;
		 System.out.println("输出时间:" + ((time2 - time1)/1000000));
		 System.out.println("读入时间:" + readt);
		 System.out.println("统计时间:" + ws.countt);
		 System.out.println("总时间:" + (ws.countt + readt + (sortt + outputt)/1000000));
	} 
}
