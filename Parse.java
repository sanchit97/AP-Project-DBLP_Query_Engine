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
			UserHandler userhandler = new UserHandler();
			saxParser.parse(inputFile, userhandler);     
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

class Article
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

	public Article()
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
	}
	public Article(String a)
	{
		authors=new ArrayList<String>();
		authors.add(a);
		key=" ";
		mdate=" ";
		title=" ";
		pages=" ";
		year=" ";
		volume=" ";
		journal=" ";
		url=" ";
		ee=" ";
	}
}
class UserHandler extends DefaultHandler
{
	ArrayList<Article> articlelist=new ArrayList<Article>();
	int writing=0;

	boolean bAuthor=false;
	boolean bTitle=false;
	boolean bPages=false;
	boolean bYear=false;
	boolean bVolume=false;
	boolean bJournal=false;
	boolean bURL=false;
	boolean bEE=false;

	int counter_article=0;
	int counter_www=0;
	int counter_proceedings=0;
	int counter_inproceedings=0;
	int counter_book=0;
	int counter_incollection=0;
	int counter_phdthesis=0;
	int counter_masterthesis=0;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		
		if(qName.equalsIgnoreCase("article"))
		{
			counter_article++;
			String key=attributes.getValue("key");
			String date=attributes.getValue("mdate");
			System.out.println("Key:"+ key);
			System.out.println("MDate:"+date);
			writing=1;
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
			counter_proceedings++;
			String key=attributes.getValue("key");
			String date=attributes.getValue("mdate");
			System.out.println("Key:"+ key);
			System.out.println("MDate:"+date);
		}
		if(qName.equalsIgnoreCase("inproceedings"))
		{
			counter_inproceedings++;
			String key=attributes.getValue("key");
			String date=attributes.getValue("mdate");
			System.out.println("Key:"+ key);
			System.out.println("MDate:"+date);
		}
		if(qName.equalsIgnoreCase("book"))
		{
			counter_book++;
			String key=attributes.getValue("key");
			String date=attributes.getValue("mdate");
			System.out.println("Key:"+ key);
			System.out.println("MDate:"+date);
		}
		if(qName.equalsIgnoreCase("incollection"))
		{
			counter_incollection++;
			String key=attributes.getValue("key");
			String date=attributes.getValue("mdate");
			System.out.println("Key:"+ key);
			System.out.println("MDate:"+date);
		}
		if(qName.equalsIgnoreCase("phdthesis"))
		{
			counter_phdthesis++;
			String key=attributes.getValue("key");
			String date=attributes.getValue("mdate");
			System.out.println("Key:"+ key);
			System.out.println("MDate:"+date);
		}
		if(qName.equalsIgnoreCase("masterthesis"))
		{
			counter_masterthesis++;
			String key=attributes.getValue("key");
			String date=attributes.getValue("mdate");
			System.out.println("Key:"+ key);
			System.out.println("MDate:"+date);
		}
		if(qName.equalsIgnoreCase("www"))
		{
			counter_www++;
			String key=attributes.getValue("key");
			String date=attributes.getValue("mdate");
			System.out.println("Key:"+ key);
			System.out.println("MDate:"+date);
		}
	}

	public void endElement(String uri,String localName, String qName) throws SAXException
	{
		if (qName.equalsIgnoreCase("article"))
		{
			System.out.println("End Element :" + qName);
			writing=0;
		}
		else if (qName.equalsIgnoreCase("proceedings"))
		{
			System.out.println("End Element :" + qName);
		}
		else if (qName.equalsIgnoreCase("inproceedings"))
		{
			System.out.println("End Element :" + qName);
		}

		else if (qName.equalsIgnoreCase("book"))
		{
			System.out.println("End Element :" + qName);
		}
		else if (qName.equalsIgnoreCase("incollection"))
		{
			System.out.println("End Element :" + qName);
		}
		else if (qName.equalsIgnoreCase("phdthesis"))
		{
			System.out.println("End Element :" + qName);
		}
		else if (qName.equalsIgnoreCase("masterthesis"))
		{
			System.out.println("End Element :" + qName);
		}
		else if (qName.equalsIgnoreCase("www"))
		{
			System.out.println("End Element :" + qName);
		}
	}

	public void characters(char ch[],int start, int length) throws SAXException
	{
		if(bAuthor) 
		{
			System.out.println("Author:"+new String(ch,start,length));
			bAuthor=false;
			Article a=new Article(new String(ch,start,length));
			articlelist.add(a);
		}
		else if(bTitle) 
		{
			System.out.println("Title:"+new String(ch,start,length));
			bTitle=false;
		}
		else if(bPages) 
		{
			System.out.println("Pages:"+new String(ch,start,length));
			bPages=false;
		}
		else if(bYear) 
		{
			System.out.println("Year:"+new String(ch,start,length));
			bYear=false;
		}
		else if(bVolume) 
		{
			System.out.println("Volume:"+new String(ch,start,length));
			bVolume=false;
		}
		else if(bJournal) 
		{
			System.out.println("Journal:"+new String(ch,start,length));
			bJournal=false;
		}
		else if(bURL) 
		{
			System.out.println("URL:"+new String(ch,start,length));
			bURL=false;
		}
		else if(bEE) 
		{
			System.out.println("EE:"+new String(ch,start,length));
			bEE=false;
		}
		System.out.println("www"+counter_www);
		System.out.println("article"+counter_article);
		System.out.println("book"+counter_book);
		System.out.println("masterthes"+counter_masterthesis);
		System.out.println("phdthesis"+counter_phdthesis);
		System.out.println("incollection"+counter_incollection);
		System.out.println("inproceedings"+counter_inproceedings);
		System.out.println("proceedings"+counter_proceedings);
		System.out.println("size"+articlelist.size());
	}

}