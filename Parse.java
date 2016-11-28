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
}

class Publication
{
	String authors;
	String key;
	String mdate;
	String title;
	String pages;
	String year;
	String volume;
	String journal;
	String url;
	String ee;

	public Publication()
	{
		authors="";
		key="";
		mdate="";
		title="";
		pages="";
		year="";
		volume="";
		journal="";
		url="";
		ee="";
	}
}
class UserHandler1 extends DefaultHandler
{
	ArrayList<Publication> artlist=new ArrayList<Publication>();
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
	public int getTotal()
	{
		return number;
	}
	@Override
	public void startDocument() throws SAXException
	{
		nameofauthor=tag;
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
			String w;
			w=new String(ch,start,length);
			if(w.contains(nameofauthor))
			{		
				System.out.println("Found");
				number+=1;
				found=1;
				a=new Publication();
				a.authors=w;
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
			a.authors=new String(ch,start,length);
		}
		else if(bTitle) 
		{
			String w;
			w=new String(ch,start,length);
			if(w.contains(nameoftitle))
			{		
				System.out.println("Found");
				number+=1;
				found=1;
				a=new Publication();
				a.title=w;
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