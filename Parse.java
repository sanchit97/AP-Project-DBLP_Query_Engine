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
		try
		{	
			File inputFile = new File("dblp.xml");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			UserHandler1 userhandler1 = new UserHandler1();
			System.out.println("Enter Query:");
			System.out.println("Query 1..........1");
			System.out.println("Query 2..........2");
			int ch;
			Scanner sc=new Scanner(System.in);
			ch=sc.nextInt();
			switch(ch)
			{
				case 1:
				System.out.println("Enter name of author(1)/title(2)");
				int c;
				c=sc.nextInt();
				if(c==1)
				{
					// System.out.println("Enter name of Author");
					// AUTHOR=sc.next();
					saxParser.parse(inputFile, userhandler1);    
				}
				// if(c==2)
				// {
				// 	System.out.println("Enter title");
				// 	saxParser.parse(inputFile, userhandler2);
				// }
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
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
	@Override
	public void startDocument() throws SAXException
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter name of Author");
		nameofauthor=sc.nextLine();
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
			//System.out.println(w);
			//bAuthor=false;
			//System.out.println(nameofauthor);
			if(w.equals(nameofauthor))
			{		
				System.out.println("Found");
				found=1;
				a=new Article();
				a.authors=w;
				artlist.add(a);
				//System.out.println(nameofauthor);
			}
			else
			{
				found=0;
			}
		}
		else if(bTitle) 
		{
			//System.out.println("Title:"+new String(ch,start,length));
			//bTitle=false;
			if(found==1)
			//System.out.println(new String(ch,start,length));
			a.title=new String(ch,start,length);
		}
		else if(bPages) 
		{
			//System.out.println("Pages:"+new String(ch,start,length));
			bPages=false;
			if(found==1)
			// System.out.println(new String(ch,start,length));
			a.pages=new String(ch,start,length);
			//System.out.println(new String(ch,start,length));
		}
		else if(bYear) 
		{
			//System.out.println("Year:"+new String(ch,start,length));
			bYear=false;
			if(found==1)
			// System.out.println(new String(ch,start,length));
			//System.out.println(new String(ch,start,length));
			a.year=new String(ch,start,length);
		}
		else if(bVolume) 
		{
			//System.out.println("Volume:"+new String(ch,start,length));
			bVolume=false;
		
			if(found==1)
			a.volume=new String(ch,start,length);
			// System.out.println(new String(ch,start,length));
			//System.out.println(new String(ch,start,length));
		}
		else if(bJournal) 
		{
			//System.out.println("Journal:"+new String(ch,start,length));
			bJournal=false;
			if(found==1)
			a.journal=new String(ch,start,length);
			// System.out.println(new String(ch,start,length));
			//System.out.println(new String(ch,start,length));
		}
		else if(bURL) 
		{
			//System.out.println("URL:"+new String(ch,start,length));
			bURL=false;
			if(found==1)
			a.url=new String(ch,start,length);
			// System.out.println(new String(ch,start,length));
			//System.out.println(new String(ch,start,length));
		}
		else if(bEE) 
		{
			//System.out.println("EE:"+new String(ch,start,length));
			bEE=false;
			if(found==1)
			a.ee=new String(ch,start,length);
			// System.out.println(new String(ch,start,length));
			//System.out.println(new String(ch,start,length));
		}
	}

}