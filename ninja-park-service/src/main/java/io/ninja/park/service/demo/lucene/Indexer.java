/**
 * 
 */
package io.ninja.park.service.demo.lucene;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * @author romgzy
 *
 */
public class Indexer {
	private final static String indexDir ="D:\\export\\lucene";
	private final static String dataDir ="D:\\export\\data";
	public static void main(String[] arg) throws Exception{
		long start = System.currentTimeMillis();
		Indexer indexer = new Indexer();
		indexer.init();
		
	}

    /** 
     * 初始添加文档 
     * @throws Exception 
     */  
    public void init() throws Exception {  
        String pathFile="D://export/lucene";  
        dir= null; // FSDirectory.open(new File(pathFile));  
        IndexWriter writer=getWriter();  
        for(int i=0; i < ids.length; i++) {  
            Document doc=new Document();  
            doc.add(new StringField("id", ids[i], Store.YES));  
            doc.add(new TextField("content", content[i], Store.YES));  
            doc.add(new StringField("city", city[i], Store.YES));  
            writer.addDocument(doc);  
        }  
        System.out.println("init ok?");  
        writer.close();  
    }  
  
    /** 
     * 获得IndexWriter对象 
     * @return 
     * @throws Exception 
     */  
    public IndexWriter getWriter() throws Exception {  
        Analyzer analyzer=new StandardAnalyzer();  
        IndexWriterConfig iwc=new IndexWriterConfig( analyzer);  
        return new IndexWriter(dir, iwc);  
    }  

    protected String[] ids={"1", "2"};  
  
    protected String[] content={"Amsterdam has lost of add  cancals", "i love  add this girl"};  
  
    protected String[] city={"Amsterdam", "Venice"};  
  
    private Directory dir;  
}
