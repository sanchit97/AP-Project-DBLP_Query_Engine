import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

public class Parse
{
	public static void main(String [] args)
	{
		GUI box = new GUI();
		box.showComboBox();
		// try
		// {	
		// 	File inputFile = new File("dblp.xml");
		// 	SAXParserFactory factory = SAXParserFactory.newInstance();
		// 	SAXParser saxParser = factory.newSAXParser();
		// }
		// catch (Exception e)
		// {
		// 	e.printStackTrace();
		// }
	}
}••••••••

class Publication
{
	ArrayList<String> authors;
	String key;
	String mdate;
	String title;
	String pages;
	String year;
	String volume;
	String journal;
	String url;
	String ee;
	int relevance;

	public Publication()
	{
		authors=new ArrayList<String>();
		key="";
		mdate="";
		title="";
		pages="";
		year="";
		volume="";
		journal="";
		url="";
		ee="";
		relevance=0;
	}
}
class UserHandler1 extends DefaultHandler
{
	ArrayList<Publication> artlist=new ArrayList<Publication>();
	ArrayList<Author> alist=new ArrayList<Author>();
	Publication a;
	String tag="wa";
	boolean bAuthor=false;
	boolean bTitle=false;
	boolean bPages=false;
	boolean bYear=false;
	boolean bVolume=false;
	boolean bJournal=false;
	boolean bURL=false;
	boolean bEE=false;
	String nameofauthor;
	int found=0;
	int number=0;
	ArrayList<String> searchinthis=new ArrayList<String>();
	public ArrayList<Publication> getList()
	{
		return artlist;
	}
	public int getTotal()
	{
		return number;
	}
	@Override
	public void startDocument() throws SAXException
	{
		nameofauthor=tag;
		for(Author q: alist)
		{
			if(q.alias.contains(nameofauthor))
			{
				for(String t: q.alias)
				{
					searchinthis.add(t);
					System.out.println(t);
				}
				break;
			}
		}
		System.out.println(searchinthis);
		//search for nameofauthor in alist and store results in an arraylist
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{

		if(qName.equalsIgnoreCase("article"))
		{
			String key=attributes.getValue("key");
			String date=attributes.getValue("mdate");
		}
		else if(qName.equalsIgnoreCase("author")) 
		{
			bAuthor=true;
		}
		else if(qName.equalsIgnoreCase("title")) 
		{
			bTitle=true;
		}
		else if(qName.equalsIgnoreCase("pages")) 
		{
			bPages=true;
		}
		else if(qName.equalsIgnoreCase("year")) 
		{
			bYear=true;
		}
		else if(qName.equalsIgnoreCase("volume")) 
		{
			bVolume=true;
		}
		else if(qName.equalsIgnoreCase("journal")) 
		{
			bJournal=true;
		}
		else if(qName.equalsIgnoreCase("url")) 
		{
			bURL=true;
		}
		else if(qName.equalsIgnoreCase("ee")) 
		{
			bEE=true;
		}

		if(qName.equalsIgnoreCase("proceedings"))
		{
			String key=attributes.getValue("key");
			String date=attributes.getValue("mdate");
		}
		if(qName.equalsIgnoreCase("inproceedings"))
		{
			String key=attributes.getValue("key");
			String date=attributes.getValue("mdate");
		}
		if(qName.equalsIgnoreCase("book"))
		{
			String key=attributes.getValue("key");
			String date=attributes.getValue("mdate");
		}
		if(qName.equalsIgnoreCase("incollection"))
		{
			String key=attributes.getValue("key");
			String date=attributes.getValue("mdate");
		}
		if(qName.equalsIgnoreCase("phdthesis"))
		{
			String key=attributes.getValue("key");
			String date=attributes.getValue("mdate");
		}
		if(qName.equalsIgnoreCase("masterthesis"))
		{
			String key=attributes.getValue("key");
			String date=attributes.getValue("mdate");
		}
	}

	public void endElement(String uri,String localName, String qName) throws SAXException
	{
		if (qName.equalsIgnoreCase("article"))
		{
		}
		else if (qName.equalsIgnoreCase("title"))
		{
			bTitle=false;
		}
		else if (qName.equalsIgnoreCase("author"))
		{
			bAuthor=false;
		}
		else if (qName.equalsIgnoreCase("proceedings"))
		{
		}
		else if (qName.equalsIgnoreCase("inproceedings"))
		{
		}

		else if (qName.equalsIgnoreCase("book"))
		{
		}
		else if (qName.equalsIgnoreCase("incollection"))
		{
		}
		else if (qName.equalsIgnoreCase("phdthesis"))
		{
		}
		else if (qName.equalsIgnoreCase("masterthesis"))
		{
		}
	}

	public void characters(char ch[],int start, int length) throws SAXException
	{
		if(bAuthor) 
		{
			String w;
			w=new String(ch,start,length);
			int tempvar=0;
			if(searchinthis.contains(w)) //check if w is contained in arraylist
			{		
				String []arr=w.split(" ");
				for ( String ss : arr) 
				{
			    	if(ss.equals(nameofauthor))
			    		tempvar+=1;
				}
				// System.out.println("Found");
				number+=1;
				found=1;
				a=new Publication();
				a.authors.add(w);
				a.relevance=tempvar;
				artlist.add(a);
			}
			else
			{
				found=0;
			}
		}
		else if(bTitle) 
		{
			if(found==1)
			a.title=new String(ch,start,length);
		}
		else if(bPages) 
		{
			bPages=false;
			if(found==1)
			a.pages=new String(ch,start,length);
		}
		else if(bYear) 
		{
			bYear=false;
			if(found==1)
			a.year=new String(ch,start,length);
		}
		else if(bVolume) 
		{
			bVolume=false;
		
			if(found==1)
			a.volume=new String(ch,start,length);
		}
		else if(bJournal) 
		{
			bJournal=false;
			if(found==1)
			a.journal=new String(ch,start,length);
		}
		else if(bURL) 
		{
			bURL=false;
			if(found==1)
			a.url=new String(ch,start,length);
		}
		else if(bEE) 
		{
			bEE=false;
			if(found==1)
			a.ee=new String(ch,start,length);
		}
	}
}
class UserHandler2 extends DefaultHandler
{
	ArrayList<Publication> artlist=new ArrayList<Publication>();
	Publication a;
	String tag="pol";
	boolean bAuthor=false;
	boolean bTitle=false;
	boolean bPages=false;
	boolean bYear=false;
	boolean bVolume=false;
	boolean bJournal=false;
	boolean bURL=false;
	boolean bEE=false;
	String nameoftitle;
	int found=0;
	int number=0;
	public ArrayList<Publication> getList()
	{
		return artlist;
	}
	public int getTotal()
	{
		return number;
	}
	@Override
	public void startDocument() throws SAXException
	{
		nameoftitle=tag;
	}
	public void endDocument() throws SAXException
	{
		for(Publication m: artlist)
		{
			System.out.println(m.authors);
			System.out.println(m.title);
			System.out.println(m.pages);
			System.out.println(m.year);
			System.out.println(m.volume);
			System.out.println(m.journal);
			System.out.println(m.url);
			System.out.println(m.ee);
			System.out.println(number);
		} 
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{

		if(qName.equalsIgnoreCase("article"))
		{
			String key=attributes.getValue("key");
			String date=attributes.getValue("mdate");
		}
		else if(qName.equalsIgnoreCase("author")) 
		{
			bAuthor=true;
		}
		else if(qName.equalsIgnoreCase("title")) 
		{
			bTitle=true;
		}
		else if(qName.equalsIgnoreCase("pages")) 
		{
			bPages=true;
		}
		else if(qName.equalsIgnoreCase("year")) 
		{
			bYear=true;
		}
		else if(qName.equalsIgnoreCase("volume")) 
		{
			bVolume=true;
		}
		else if(qName.equalsIgnoreCase("journal")) 
		{
			bJournal=true;
		}
		else if(qName.equalsIgnoreCase("url")) 
		{
			bURL=true;
		}
		else if(qName.equalsIgnoreCase("ee")) 
		{
			bEE=true;
		}

		if(qName.equalsIgnoreCase("proceedings"))
		{
			String key=attributes.getValue("key");
			String date=attributes.getValue("mdate");
		}
		if(qName.equalsIgnoreCase("inproceedings"))
		{
			String key=attributes.getValue("key");
			String date=attributes.getValue("mdate");
		}
		if(qName.equalsIgnoreCase("book"))
		{
			String key=attributes.getValue("key");
			String date=attributes.getValue("mdate");
		}
		if(qName.equalsIgnoreCase("incollection"))
		{
			String key=attributes.getValue("key");
			String date=attributes.getValue("mdate");
		}
		if(qName.equalsIgnoreCase("phdthesis"))
		{
			String key=attributes.getValue("key");
			String date=attributes.getValue("mdate");
		}
		if(qName.equalsIgnoreCase("masterthesis"))
		{
			String key=attributes.getValue("key");
			String date=attributes.getValue("mdate");
		}
		if(qName.equalsIgnoreCase("www"))
		{
			String key=attributes.getValue("key");
			String date=attributes.getValue("mdate");
		}
	}

	public void endElement(String uri,String localName, String qName) throws SAXException
	{
		if (qName.equalsIgnoreCase("article"))
		{
		}
		else if (qName.equalsIgnoreCase("title"))
		{
			bTitle=false;
		}
		else if (qName.equalsIgnoreCase("author"))
		{
			bAuthor=false;
		}
		else if (qName.equalsIgnoreCase("proceedings"))
		{
		}
		else if (qName.equalsIgnoreCase("inproceedings"))
		{
		}

		else if (qName.equalsIgnoreCase("book"))
		{
		}
		else if (qName.equalsIgnoreCase("incollection"))
		{
		}
		else if (qName.equalsIgnoreCase("phdthesis"))
		{
		}
		else if (qName.equalsIgnoreCase("masterthesis"))
		{
		}
		else if (qName.equalsIgnoreCase("www"))
		{
		}
	}

	public void characters(char ch[],int start, int length) throws SAXException
	{
		if(bAuthor) 
		{
			
			if(found==1)
			a.authors.add(new String(ch,start,length));
		}
		else if(bTitle) 
		{
			String w;
			w=new String(ch,start,length);
			int tempvar=0;
			if(w.contains(nameoftitle))
			{		
				String []arr=w.split(" ");
				for ( String ss : arr) 
				{
			    	if(ss.equals(nameoftitle))
			    		tempvar+=1;
				}
				number+=1;
				found=1;
				a=new Publication();
				a.title=w;
				a.relevance=tempvar;
				artlist.add(a);
			}
			else
			{
				found=0;
			}
		}
		else if(bPages) 
		{
			bPages=false;
			if(found==1)
			a.pages=new String(ch,start,length);
		}
		else if(bYear) 
		{
			bYear=false;
			if(found==1)
			a.year=new String(ch,start,length);
		}
		else if(bVolume) 
		{
			bVolume=false;
		
			if(found==1)
			a.volume=new String(ch,start,length);
		}
		else if(bJournal) 
		{
			bJournal=false;
			if(found==1)
			a.journal=new String(ch,start,length);
		}
		else if(bURL) 
		{
			bURL=false;
			if(found==1)
			a.url=new String(ch,start,length);
		}
		else if(bEE) 
		{
			bEE=false;
			if(found==1)
			a.ee=new String(ch,start,length);
		}
	}

}
class UserHandler3 extends DefaultHandler
{
	boolean bAuthor=false;
	int number=0;
	HashMap<String,Integer> map=new HashMap<String,Integer>();
	ArrayList<String> nauth;
	public HashMap<String,Integer> getHash()
	{
		return map;
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{

		if(qName.equalsIgnoreCase("author")) 
		{
			bAuthor=true;
			nauth=new ArrayList<String>();
		}
		
	}

	public void endElement(String uri,String localName, String qName) throws SAXException
	{
		
		if (qName.equalsIgnoreCase("author"))
		{
			bAuthor=false;
		}
	}

	public void characters(char ch[],int start, int length) throws SAXException
	{
		if(bAuthor) 
		{
			//nauth.add(new String(ch,start,length));
			map.put(new String(ch,start,length),0);
		}
	}
}
class UserHandler4 extends DefaultHandler
{
	boolean bAuthor=false;
	HashMap<String,Integer> map=new HashMap<String,Integer>();
	public HashMap<String,Integer> getHash()
	{
		return map;
	}
	public void getMap(HashMap<String,Integer> m)
	{
		map=m;
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		if(qName.equalsIgnoreCase("author")) 
		{
			bAuthor=true;
		}
	}

	public void endElement(String uri,String localName, String qName) throws SAXException
	{
		
		if (qName.equalsIgnoreCase("author"))
		{
			bAuthor=false;
		}
	}

	public void characters(char ch[],int start, int length) throws SAXException
	{
		if(bAuthor)
		{
			String authorfinal=new String(ch,start,length);
			map.put(authorfinal,(map.get(authorfinal))+1);
		}
	}
}
class Author
{
	ArrayList<String> alias;
	// ArrayList<Publication> publications;
	public Author()
	{
		alias=new ArrayList<String>();
	}
}
class WWWHandler extends DefaultHandler
{
	Author author;
	ArrayList<Author> authors_list = new ArrayList<Author>();
	boolean bAuthor = false;
	boolean bWWW = false;

	public ArrayList<Author> getMyData()
	{
		return authors_list;
	}

	public void endDocument() throws SAXException
	{
		// for(Author z: authors_list)
		// {
		// 	System.out.println(z.alias);
		// } 
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		if ( qName.equalsIgnoreCase("www") )
		{
			bWWW = true;
			author = new Author();
		} 
		else if (qName.equalsIgnoreCase("author") && bWWW)
		{
			bAuthor = true;
		} 
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException{
		if ( qName.equalsIgnoreCase("www") )
		{
			bWWW=false;
			authors_list.add(author);
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException{
		if(bAuthor)
		{
			author.alias.add(new String(ch, start, length));
			bAuthor = false;
		}
	}


}