//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WordCountService {
	public long countt = 0;
	private long time1, time2;
	private charTreeNode root;
	// = new ArrayList<WordCount>();
	public void initCharTree(){                    //构造函数
//	static charTreeNode initCharTree(){                    //构造函数
//		charTreeNode root = new charTreeNode();
		root = new charTreeNode();
//		return root;
	}
	
	public void geneCharTree(String text)
	{
		time1 = System.currentTimeMillis();
		geneCharTree_inner(text);
		time2 = System.currentTimeMillis();
		countt += time2-time1;
	}
	
    private charTreeNode geneCharTree_inner(String text){ //生成或是增加树，关键看输入的节点参数
//    static charTreeNode geneCharTree_inner(charTreeNode root,String text){ //生成或是增加树，关键看输入的节点参数
        charTreeNode p = root;
        char c = ' ';
        for(int i = 0; i < text.length(); i ++){
            c = text.charAt(i);
            if(c >= 'A' && c <= 'Z')
                 c = (char)(c + 32);
            if(c >= 'a' && c <= 'z'){
                 if(p.children[c-'a'] == null)
                    p.children[c-'a'] = new charTreeNode();
                 p = p.children[c-'a'];
             }else if(c >= '0' && c <= '9'){
            		if(p.children[26 + c - '0'] == null)
            			p.children[26 + c - '0'] = new charTreeNode();
            		p = p.children[26 + c - '0'];
            }
    		else if(c == '_'){
    				if(p.children[36] == null)
    					p.children[36] = new charTreeNode();
    				p = p.children[36];
    		}
			else{
				p.cnt ++;
				p = root;
			}
        }
         if(c >= 'a' && c <= 'z' || c >= '0' && c <= '9')  //文件末尾的那个。
             p.cnt ++;
         return root;
     }
    
    //深度优先搜索
    
    public void getwordCountFromcharTree(List<WordCount> result, charTreeNode p, char[] buffer, int length){
    	for(int i = 0; i < 37; i ++){
    		if(p.children[i] != null){
    			if(i >= 0 && i < 26)
    				buffer[length] = (char)(i + 'a');
    			else if(i >= 26 && i <= 35)
    					buffer[length] = (char)(i + '0');
    			else buffer[length] = '_';
    			
    			if(p.children[i].cnt > 0){
    				WordCount wc = new WordCount();
    				wc.setCount(p.children[i].cnt);
    				wc.setWord(String.valueOf(buffer, 0, length + 1));
    				result.add(wc);
    			}
    			getwordCountFromcharTree(result, p.children[i], buffer, length + 1);
    		}
    	}
    }
    private void getwordCountFromCharTree(List<WordCount> result, charTreeNode p){
    	getwordCountFromcharTree(result, p, new char[100], 0);
    }
    
  //排序结果
    
    public List<WordCount> getwordCount(){
 //      public static List<WordCount> getwordCount(charTreeNode root){
    	List<WordCount> result = new ArrayList<WordCount>();
    	getwordCountFromCharTree(result, root);
    	Collections.sort(result, new Comparator<WordCount>(){    		
    		public int compare(WordCount wc1, WordCount wc2){
    			return wc2.getCount() - wc1.getCount();
    		}
    	});
    	return result;
    }
}