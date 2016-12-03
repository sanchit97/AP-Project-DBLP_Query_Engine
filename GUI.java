import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

public class GUI 
{
	int choice =2;
	int choice1;
	int i;
	private JFrame mainFrame;
	private static int DEFAULT_HEIGHT = 700;
	private static int DEFAULT_WIDTH = 1000;
	private static String Title = "DBLP Search Engine";
	JTextPane inputNameTag,inputk,author1input,author2input,author3input,author4input,author5input,yearinput,a,b,x;
	String since,from,to;
	UserHandler1 userhandler1=new UserHandler1();
	UserHandler2 userhandler2=new UserHandler2();
	UserHandler3 userhandler3=new UserHandler3();
	UserHandler4 userhandler4=new UserHandler4();
	ArrayList<Publication> artlist=new ArrayList<Publication>();
	Vector<Vector> rowData =new Vector<Vector>();
	ArrayList<String> names;
	JPanel result;
	JTable table;
	int number=0;
	public String tag="";
	public GUI() 
	{
		mainFrame = new JFrame();
		mainFrame.setBounds(180, 35, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setTitle(Title);
		mainFrame.setLayout(null);
	}
	public void sortbyyear()
	{
		Collections.sort(artlist, new Comparator<Publication>() 
		{
	        @Override
	        public int compare(Publication pub1, Publication pub2)
	        {

	            return  -1*pub1.year.compareTo(pub2.year);
	        }
    	});
    	for(Publication p: artlist)
    	{
    		System.out.println(p.year);
    	}
	}
	public void sortbyrelevance()
	{
		Collections.sort(artlist, new Comparator<Publication>() 
		{
	        @Override
	        public int compare(Publication pub1, Publication pub2)
	        {

	            return Integer.compare(pub1.relevance, pub2.relevance);
	        }
    	});
	}
 	private JPanel titleSpaceDefault() 
 	{
		JPanel title = new JPanel();
		title.setSize(new Dimension(DEFAULT_WIDTH, 120));
		// title.setBackground(Color.CYAN);		
		
		JLabel text = new JLabel("DBLP Query Engine");
		text.setFont(new Font("Arial", Font.BOLD, 60));
		text.setLocation(10, 10);
		text.setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT/5));
		
		title.add(text);
		title.setVisible(true);
		return title;
	}
 	JPanel querySpaceDefault() 
 	{
 		JPanel query = new JPanel(new GridBagLayout());
 		GridBagConstraints c = new GridBagConstraints();
 		query.setBounds(0, 120, DEFAULT_WIDTH/3, 557);
 		//query.setBackground(Color.RED);
 		
 		String[] queryStrings = { "Queries", "Query1", "Query2", "Query3" };
 		JComboBox<String> queryList = new JComboBox<String>(queryStrings);
 		c.ipadx = 150;
 		c.ipady = 17;
 		c.gridx = 1;
 		c.gridy = 1;
 		c.insets = new Insets(40,0,0,0);
 		c.weighty = 2;
 		c.weightx = 2;
 		c.anchor = GridBagConstraints.PAGE_START;
 		query.add(queryList, c);
 		queryList.addItemListener(new ItemListener() 
 		{
 			public void itemStateChanged(ItemEvent e) 
 			{
 				if(e.getStateChange() == ItemEvent.SELECTED) 
 				{
 					String item = (String)e.getItem();
 					if(item.equals("Query1")) 
 					{
 						querySpace("Query1", query,queryList);
 						//add/repaint this JPanel
 					}
 					else if (item.equals("Query2")) 
 					{
 						querySpace("Query2", query,queryList);
 						//add/repaint this JPanel
 					}
 					else if(item.equals("Query3")) 
 					{
 						querySpace("Query3", query,queryList);
 						//add/repaint this JPanel
 					}
 					else if(item.equals("Queries")) 
 					{
 						showComboBox();
 					}
 				}
 			}
 		});

 		query.setVisible(true);
 		return query;
 	}
 	void querySpace(String type, JPanel querySpace, JComboBox<String> queryList) 
 	{
 		if(type.equals("Query1")) 
 		{
 			GridBagConstraints c = new GridBagConstraints();
 			querySpace.removeAll();
 			c.ipadx = 150;
	 		c.ipady = 17;
	 		c.gridx = 1;
	 		c.gridy = 1;
	 		c.insets = new Insets(40,0,0,0);
	 		c.weighty = 2;
	 		c.weightx = 2;
	 		c.anchor = GridBagConstraints.PAGE_START;

	 		querySpace.add(queryList, c);

	 		querySpace.repaint();
	 		querySpace.revalidate();


 			//querySpace.setBackground(Color.RED);
 			String[] searchStrings = { "Search By", "Search By Author", "Search By Publications" };
 			JComboBox<String> searchList = new JComboBox<String>(searchStrings);
 			searchList.addItemListener(new ItemListener()
 			 {
 				public void itemStateChanged(ItemEvent e) 
 				{
 					if(e.getStateChange() == ItemEvent.SELECTED) 
 					{
 						String item = (String)e.getItem();
 	 					if(item.equals("Search By Author")) 
 	 					{
					 	 	choice=0;
 	 					}
 	 					else if(item.equals("Search By Publications")) 
 	 					{
 	 						choice=1;
 	 					}
 	 					else if(item.equals("Search By")) 
 	 					{
 	 						choice=2;
 	 					}
 					}
 				}
 			});

			c.ipadx = 15;
	 		c.ipady = 17;
	 		c.gridx = 1;
	 		c.gridy = 1;
	 		c.insets = new Insets(100,47,0,0);
	 		c.weighty = 2;
	 		c.weightx = 2;
	 		c.anchor = GridBagConstraints.FIRST_LINE_START;
	 		querySpace.add(searchList, c);

			String[] sortStrings = { "Sort By", "Year", "Relevance", "Since", "Between"};
			JComboBox<String> sortList = new JComboBox<String>(sortStrings);
			sortList.addItemListener(new ItemListener()
			 {
				public void itemStateChanged(ItemEvent e1) 
				{
					if(e1.getStateChange() == ItemEvent.SELECTED) 
					{
						String item = (String)e1.getItem();
	 					if(item.equals("Sort By")) 
	 					{
				 	 	choice1=0;
	 					}
	 					else if(item.equals("Year")) 
	 					{
	 						choice1=1;
	 					}
	 					else if(item.equals("Relevance")) 
	 					{
	 						choice1=2;
	 					}
	 					else if(item.equals("Since")) 
	 					{
 	 					choice1=3;
	 						//querySpace.removeAll();
	 					}
	 					else if(item.equals("Between")) 
	 					{
	 						choice1=4;
	 					}
					}
				}
			});
			c.ipadx = 15;
	 		c.ipady = 17;
	 		c.gridx = 1;
	 		c.gridy = 1;
	 		c.insets = new Insets(150,47,0,0);
	 		c.weighty = 2;
	 		c.weightx = 2;
	 		c.anchor = GridBagConstraints.FIRST_LINE_START;
	 		querySpace.add(sortList, c);
			
			JLabel nameLabel = new JLabel("Name/Title Tags");
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.insets = new Insets(200,20,0,0);
			querySpace.add(nameLabel, c);
			
			inputNameTag = new JTextPane();
			//System.out.println("i am here");
			inputNameTag.setBorder(BorderFactory.createLineBorder(Color.black, 2, false));
			inputNameTag.setEditable(true);
			inputNameTag.setFont(new Font("Arial", Font.BOLD, 12));
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.ipadx = 80;
			c.ipady = 10;
			c.insets = new Insets(200,150,0,0);
			querySpace.add(inputNameTag, c);

			JLabel k0 = new JLabel("Since");
			c.ipadx = 15;
	 		c.ipady = 17;
	 		c.gridx = 1;
	 		c.gridy = 1;
	 		c.insets = new Insets(250,47,0,0);
	 		c.weighty = 2;
	 		c.weightx = 2;
	 		c.anchor = GridBagConstraints.FIRST_LINE_START;
	 		querySpace.add(k0, c);
		querySpace.repaint();
 		querySpace.revalidate();

 		x = new JTextPane();
		x.setBorder(BorderFactory.createLineBorder(Color.black, 2, false));
		x.setEditable(true);
		x.setFont(new Font("Arial", Font.BOLD, 12));
		c.ipadx = 80;
		c.ipady = 10;
		c.insets = new Insets(250,100,0,0);
		querySpace.add(x, c);
 
			JLabel k1 = new JLabel("From");
			c.ipadx = 15;
	 		c.ipady = 17;
	 		c.gridx = 1;
	 		c.gridy = 1;
	 		c.insets = new Insets(300,47,0,0);
	 		c.weighty = 2;
	 		c.weightx = 2;
	 		c.anchor = GridBagConstraints.FIRST_LINE_START;
	 		querySpace.revalidate();
	 		querySpace.add(k1, c);

	 		a = new JTextPane();
		a.setBorder(BorderFactory.createLineBorder(Color.black, 2, false));
		a.setEditable(true);
		a.setFont(new Font("Arial", Font.BOLD, 12));
		c.ipadx = 80;
		c.ipady = 10;
		c.insets = new Insets(300,100,0,0);
		querySpace.add(a, c);

	 		JLabel k2 = new JLabel("To");
			c.ipadx = 15;
	 		c.ipady = 17;
	 		c.gridx = 1;
	 		c.gridy = 1;
	 		c.insets = new Insets(350,47,0,0);
	 		c.weighty = 2;
	 		c.weightx = 2;
	 		c.anchor = GridBagConstraints.FIRST_LINE_START;
	 		querySpace.add(k2, c);
	 		querySpace.repaint();
 		querySpace.revalidate();

 		b = new JTextPane();
		b.setBorder(BorderFactory.createLineBorder(Color.black, 2, false));
		b.setEditable(true);
		b.setFont(new Font("Arial", Font.BOLD, 12));
		c.ipadx = 80;
		c.ipady = 10;
		c.insets = new Insets(350,100,0,0);
		querySpace.add(b, c);


		JButton newbut = new JButton("Reset");
		newbut.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
		c.ipadx = 50;
 		c.ipady = 10;
 		c.insets = new Insets(0,0,80,0);
		c.anchor = GridBagConstraints.PAGE_END;
		querySpace.add(newbut, c);
		querySpace.repaint();
		newbut.setVisible(true);
		newbut.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent w)
			{
				mainFrame.dispose();
				GUI newobj=new GUI();
				newobj.showComboBox();
			}
		});

		JButton but = new JButton("Search");
 		//GridBagConstraints c = new GridBagConstraints();
		but.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 15;
		c.ipadx = 30;
		c.insets = new Insets(0,0,20,0);
		c.anchor = GridBagConstraints.PAGE_END;
		querySpace.add(but, c);
		but.setVisible(true);
		but.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent w)
			{
				if(choice==0)
				{
					try
					{	
	 					// Vector<String> columnNames = new Vector<String>();
	 					// GridBagConstraints c = new GridBagConstraints();
	 					// table = new JTable(rowData, columnNames);
						//JScrollPane spTable = new JScrollPane(table);
						// c.anchor = GridBagConstraints.CENTER;
						// c.weighty = 1;
						// resultSpaceDefault().add(table, c);
	 					if(choice1==0)
	 					{	
							File inputFile = new File("dblp.xml");
							SAXParserFactory factory = SAXParserFactory.newInstance();
							SAXParser saxParser = factory.newSAXParser();
		 					userhandler1=new UserHandler1();
		 					userhandler1.tag=inputNameTag.getText();
		 					if((userhandler1.tag).equals(""))
		 					JOptionPane.showMessageDialog(null,"Cannot be Empty");
		 					// entityresolve();
		 					System.out.println(inputNameTag.getText());
		 					WWWHandler w1=new WWWHandler();
		 					saxParser.parse(inputFile,w1);	
		 					userhandler1.alist=w1.getMyData();
		 					// System.out.println(userhandler1.alist);
		 					saxParser.parse(inputFile, userhandler1);
		 					if(table!=null)
			 					resultSpaceDefault().remove(table);
		 					System.out.println("yo");
		 					result=null;
		 					rowData=new Vector<Vector>();
		 					artlist=userhandler1.getList();
	 						if(artlist.size()==0)
	 						{
	 							JOptionPane.showMessageDialog(null,"No Results Found!");
	 						}
	 						i=0;
	 						int sno=1;
	 						//JPanel result = new JPanel(new GridBagLayout());
	 						while(i<artlist.size() && i<20)
	 						{

	 							Publication p=new Publication();
	 							p=artlist.get(i);
		 						Vector<String> rows=new Vector<String>();
	 							rows.addElement(Integer.toString(sno++));
	 							String auth="";
	 							for(String test : p.authors) 
	 							{
	 								System.out.println(p.authors);
	 								auth=test+auth;
	 							}
	 							rows.addElement(auth);
	 							rows.addElement(p.title);
	 							rows.addElement(p.pages);
	 							rows.addElement(p.year);
	 							rows.addElement(p.volume);
	 							rows.addElement(p.journal);
	 							rows.addElement(p.url);
								rowData.addElement(rows);
								number++;
								// System.out.println("ll");
								// mainFrame.remove(resultSpaceDefault());
	 							mainFrame.getContentPane().add(resultSpaceDefault());
	 							i++;
	 						}
	 						number=0;
	 						System.out.println(artlist.size());
 							//resultSpaceDefault().revalidate();
	 					}
	 					else if(choice1==1)
	 					{
	 						if(artlist.size()==0)
	 						{
	 							JOptionPane.showMessageDialog(null,"No Results Found!");
	 						}
	 						sortbyyear();
	 						i=0;
	 						int sno=1;
	 						while(i<artlist.size() && i<20)
	 						{
	 							Publication p=new Publication();
	 							p=artlist.get(i);
	 							// Vector<String> row=new Vector<String>();
		 						Vector<String> rows=new Vector<String>();
	 							rows.addElement(Integer.toString(sno++));
	 							String auth="";
	 							for(String test : p.authors) 
	 								auth=test+auth;
	 							rows.addElement(auth);
	 							rows.addElement(p.title);
	 							rows.addElement(p.pages);
	 							rows.addElement(p.year);
	 							rows.addElement(p.volume);
	 							rows.addElement(p.journal);
	 							rows.addElement(p.url);
								rowData.addElement(rows);
								number++;
								//mainFrame.remove(resultSpaceDefault());
	 							mainFrame.getContentPane().add(resultSpaceDefault());
	 							i++;
	 						}
	 						System.out.println(number);
	 						number=0;
	 						//sort by year
	 						//load arraylist into jtable
	 					} 
	 					else if(choice1==2)
	 					{

							File inputFile = new File("dblp.xml");
							SAXParserFactory factory = SAXParserFactory.newInstance();
							SAXParser saxParser = factory.newSAXParser();
		 					userhandler1=new UserHandler1();
		 					userhandler1.tag=inputNameTag.getText();
		 					if((userhandler1.tag).equals(""))
		 					JOptionPane.showMessageDialog(null,"Cannot be Empty");
		 					// entityresolve();
		 					System.out.println(inputNameTag.getText());
		 					WWWHandler w1=new WWWHandler();
		 					saxParser.parse(inputFile,w1);	
		 					userhandler1.alist=w1.getMyData();
		 					// System.out.println(userhandler1.alist);
		 					saxParser.parse(inputFile, userhandler1);
		 					if(table!=null)
			 					resultSpaceDefault().remove(table);
		 					System.out.println("yo");
		 					result=null;
		 					rowData=new Vector<Vector>();
		 					artlist=userhandler1.getList();
	 						if(artlist.size()==0)
	 						{
	 							JOptionPane.showMessageDialog(null,"No Results Found!");
	 						}
	 						sortbyrelevance();
	 						i=0;
	 						int sno=1;
	 						while(i<artlist.size() && i<20)
	 						{
	 							Publication p=new Publication();
	 							p=artlist.get(i);
	 							// Vector<String> row=new Vector<String>();
		 						Vector<String> rows=new Vector<String>();
	 							rows.addElement(Integer.toString(sno++));
	 							String auth="";
	 							for(String test : p.authors) 
	 								auth=test+auth;
	 							rows.addElement(auth);
	 							rows.addElement(p.title);
	 							rows.addElement(p.pages);
	 							rows.addElement(p.year);
	 							rows.addElement(p.volume);
	 							rows.addElement(p.journal);
	 							rows.addElement(p.url);
								rowData.addElement(rows);
								//mainFrame.remove(resultSpaceDefault());
	 							mainFrame.getContentPane().add(resultSpaceDefault());
	 							i++;
	 						}
	 						//load arraylist into jtable
	 					}
	 					else if(choice1==3)
	 					{
	 						since=x.getText();
	 						while(since.length()!=4 && since.contains("[a-zA-Z]+") == false)
	 						{
	 							System.out.println(since.length());
	 							since = JOptionPane.showInputDialog("Invalid entry ! Please enter Since Year Again");
	 							System.out.println(since);
	 							x.setText(since);
	 						}

							File inputFile = new File("dblp.xml");
							SAXParserFactory factory = SAXParserFactory.newInstance();
							SAXParser saxParser = factory.newSAXParser();
		 					userhandler1=new UserHandler1();
		 					userhandler1.tag=inputNameTag.getText();
		 					if((userhandler1.tag).equals(""))
		 					JOptionPane.showMessageDialog(null,"Cannot be Empty");
		 					// entityresolve();
		 					System.out.println(inputNameTag.getText());
		 					WWWHandler w1=new WWWHandler();
		 					saxParser.parse(inputFile,w1);	
		 					userhandler1.alist=w1.getMyData();
		 					// System.out.println(userhandler1.alist);
		 					saxParser.parse(inputFile, userhandler1);
		 					if(table!=null)
			 					resultSpaceDefault().remove(table);
		 					System.out.println("yo");
		 					result=null;
		 					rowData=new Vector<Vector>();
		 					artlist=userhandler1.getList();
	 						if(artlist.size()==0)
	 						{
	 							JOptionPane.showMessageDialog(null,"No Results Found!");
	 						}
	 						sortbyyear();
	 						
	 						i=0;
	 						int sno=1;
	 						while(i<artlist.size() && i<20)
	 						{
	 							Publication p=new Publication();
	 							p=artlist.get(i);
	 							if(p.year.compareTo(since)>=0)
	 							{
	 							// Vector<String> row=new Vector<String>();
			 						Vector<String> rows=new Vector<String>();
		 							rows.addElement(Integer.toString(sno++));
		 							String auth="";
		 							for(String test : p.authors) 
		 								auth=test+auth;
		 							rows.addElement(auth);
		 							rows.addElement(p.title);
		 							rows.addElement(p.pages);
		 							rows.addElement(p.year);
		 							rows.addElement(p.volume);
		 							rows.addElement(p.journal);
		 							rows.addElement(p.url);
									rowData.addElement(rows);
									System.out.println(rowData.size());
									//mainFrame.remove(resultSpaceDefault());
		 							mainFrame.getContentPane().add(resultSpaceDefault());
		 						}
	 							i++;
	 						}
	 						//sort by year after
	 						//load arraylist into jtable
	 					}
	 					else if(choice1==4)
	 					{
	 						to=b.getText();
	 						from=a.getText();
	 						while(from.length()!=4 && from.contains("[a-zA-Z]+") == false)
	 						{
	 							System.out.println(from.length());
	 							from = JOptionPane.showInputDialog("Invalid entry ! Please enter 'From' Year Again");
	 							a.setText(from);
	 						}
	 						while(to.length()!=4 && to.contains("[a-zA-Z]+") == false)
	 						{
	 							
	 							to = JOptionPane.showInputDialog("Invalid entry ! Please enter 'To' Year Again");
	 							System.out.println(to);
	 							b.setText(to);
	 						}

							File inputFile = new File("dblp.xml");
							SAXParserFactory factory = SAXParserFactory.newInstance();
							SAXParser saxParser = factory.newSAXParser();
		 					userhandler1=new UserHandler1();
		 					userhandler1.tag=inputNameTag.getText();
		 					if((userhandler1.tag).equals(""))
		 					JOptionPane.showMessageDialog(null,"Cannot be Empty");
		 					// entityresolve();
		 					System.out.println(inputNameTag.getText());
		 					WWWHandler w1=new WWWHandler();
		 					saxParser.parse(inputFile,w1);	
		 					userhandler1.alist=w1.getMyData();
		 					// System.out.println(userhandler1.alist);
		 					saxParser.parse(inputFile, userhandler1);
		 					if(table!=null)
			 					resultSpaceDefault().remove(table);
		 					System.out.println("yo");
		 					result=null;
		 					rowData=new Vector<Vector>();
		 					artlist=userhandler1.getList();
	 						if(artlist.size()==0)
	 						{
	 							JOptionPane.showMessageDialog(null,"No Results Found!");
	 						}
							sortbyyear();
	 						since=x.getText();
	 						System.out.println(since);
	 						i=0;
	 						int sno=1;
	 						while(i<artlist.size() && i<20)
	 						{

	 							Publication p=new Publication();
	 							p=artlist.get(i);
	 							if(p.year.compareTo(from)>=0 && p.year.compareTo(to)<0)
	 							{
	 							// Vector<String> row=new Vector<String>();
			 						Vector<String> rows=new Vector<String>();
		 							rows.addElement(Integer.toString(sno++));
		 							String auth="";
		 							for(String test : p.authors) 
		 								auth=test+auth;
		 							rows.addElement(auth);
		 							rows.addElement(p.title);
		 							rows.addElement(p.pages);
		 							rows.addElement(p.year);
		 							rows.addElement(p.volume);
		 							rows.addElement(p.journal);
		 							rows.addElement(p.url);
									rowData.addElement(rows);
									System.out.println(rowData.size());
									//mainFrame.remove(resultSpaceDefault());
		 							mainFrame.getContentPane().add(resultSpaceDefault());
		 						}
	 							i++;
	 						}
	 						//sort by since
	 						//load arraylist into jtable
	 					}
					}
					catch (Exception e1)
					{
						e1.printStackTrace();
					}
				}
				else if (choice ==1)
				{
					try
					{	
						
						Vector<String> columnNames = new Vector<String>();
	 					GridBagConstraints c = new GridBagConstraints();
	 					JTable table = new JTable(rowData, columnNames);
						JScrollPane spTable = new JScrollPane(table);
						c.anchor = GridBagConstraints.CENTER;
						c.weighty = 1;
						resultSpaceDefault().add(spTable, c);
						columnNames.addElement("S.No");
						columnNames.addElement("Authors");
						columnNames.addElement("Title");
						columnNames.addElement("Pages");
						columnNames.addElement("Year");
						columnNames.addElement("Volume");
						columnNames.addElement("Journal");
						columnNames.addElement("URL");
						mainFrame.getContentPane().add(resultSpaceDefault());

	 					if(choice1==0)
	 					{
	 						File inputFile = new File("dblp.xml");
						SAXParserFactory factory = SAXParserFactory.newInstance();
						SAXParser saxParser = factory.newSAXParser();
	 					userhandler2=new UserHandler2();
	 					userhandler2.tag=inputNameTag.getText();
	 					if(userhandler2.tag.length()==0)
	 					{
	 						JOptionPane.showMessageDialog(null,"Cannot be Empty");
	 					}
	 					saxParser.parse(inputFile, userhandler2); 
	 					artlist=userhandler2.getList();
	 						i=0;
							int sno=1;
	 						while(i<artlist.size() && i<20)
	 						{

	 							Publication p=new Publication();
	 							p=artlist.get(i);
	 							// Vector<String> row=new Vector<String>();
		 						Vector<String> rows=new Vector<String>();
	 							rows.addElement(Integer.toString(sno++));
	 							String auth="";
	 							for(String test : p.authors) 
	 								auth=test+auth;
	 							rows.addElement(auth);
	 							rows.addElement(p.title);
	 							rows.addElement(p.pages);
	 							rows.addElement(p.year);
	 							rows.addElement(p.volume);
	 							rows.addElement(p.journal);
	 							rows.addElement(p.url);
								rowData.addElement(rows);
								System.out.println(rowData.size());
								//mainFrame.remove(resultSpaceDefault());
	 							mainFrame.getContentPane().add(resultSpaceDefault());
	 							i++;
	 						}
 							//resultSpaceDefault().revalidate();
	 					}
	 					else if(choice1==1)
	 					{
	 						File inputFile = new File("dblp.xml");
						SAXParserFactory factory = SAXParserFactory.newInstance();
						SAXParser saxParser = factory.newSAXParser();
	 					userhandler2=new UserHandler2();
	 					userhandler2.tag=inputNameTag.getText();
	 					if(userhandler2.tag.length()==0)
	 					{
	 						JOptionPane.showMessageDialog(null,"Cannot be Empty");
	 					}
	 					saxParser.parse(inputFile, userhandler2); 
	 					artlist=userhandler2.getList();
	 						sortbyyear();
	 						i=0;
	 						int sno=1;
	 						while(i<artlist.size() && i<20)
	 						{
	 							Publication p=new Publication();
	 							p=artlist.get(i);
	 							// Vector<String> row=new Vector<String>();
		 						Vector<String> rows=new Vector<String>();
	 							rows.addElement(Integer.toString(sno++));
	 							String auth="";
	 							for(String test : p.authors) 
	 								auth=test+auth;
	 							rows.addElement(auth);
	 							rows.addElement(p.title);
	 							rows.addElement(p.pages);
	 							rows.addElement(p.year);
	 							rows.addElement(p.volume);
	 							rows.addElement(p.journal);
	 							rows.addElement(p.url);
								rowData.addElement(rows);
								//mainFrame.remove(resultSpaceDefault());
	 							mainFrame.getContentPane().add(resultSpaceDefault());
	 							i++;
	 						}
	 						//sort by year
	 						//load arraylist into jtable
	 						sno=1;
	 					} 
	 					else if(choice1==2)
	 					{
	 						File inputFile = new File("dblp.xml");
						SAXParserFactory factory = SAXParserFactory.newInstance();
						SAXParser saxParser = factory.newSAXParser();
	 					userhandler2=new UserHandler2();
	 					userhandler2.tag=inputNameTag.getText();
	 					if(userhandler2.tag.length()==0)
	 					{
	 						JOptionPane.showMessageDialog(null,"Cannot be Empty");
	 					}
	 					saxParser.parse(inputFile, userhandler2); 
	 					artlist=userhandler2.getList();
	 						sortbyrelevance();
	 						i=0;
	 						int sno=1;
	 						while(i<artlist.size() && i<20)
	 						{
	 							Publication p=new Publication();
	 							p=artlist.get(i);
	 							// Vector<String> row=new Vector<String>();
		 						Vector<String> rows=new Vector<String>();
	 							rows.addElement(Integer.toString(sno++));
	 							String auth="";
	 							for(String test : p.authors) 
	 								auth=test+auth;
	 							rows.addElement(auth);
	 							rows.addElement(p.title);
	 							rows.addElement(p.pages);
	 							rows.addElement(p.year);
	 							rows.addElement(p.volume);
	 							rows.addElement(p.journal);
	 							rows.addElement(p.url);
								rowData.addElement(rows);
								//mainFrame.remove(resultSpaceDefault());
	 							mainFrame.getContentPane().add(resultSpaceDefault());
	 							i++;
	 						}
	 						//load arraylist into jtable
	 						sno=1;
	 					}
	 					else if(choice1==3)
	 					{
	 						since=x.getText();
	 						System.out.println(since.length());
	 						while(since.length()!=4 && since.contains("[a-zA-Z]+") == false)
	 						{
	 							System.out.println(since.length());
	 							since = JOptionPane.showInputDialog("Invalid entry ! Please enter Since Year Again");
	 							System.out.println(since);
	 							x.setText(since);
	 						}
	 						
	 						System.out.println(since);
	 						File inputFile = new File("dblp.xml");
						SAXParserFactory factory = SAXParserFactory.newInstance();
						SAXParser saxParser = factory.newSAXParser();
	 					userhandler2=new UserHandler2();
	 					userhandler2.tag=inputNameTag.getText();
	 					if(userhandler2.tag.length()==0)
	 					{
	 						JOptionPane.showMessageDialog(null,"Cannot be Empty");
	 					}
	 					saxParser.parse(inputFile, userhandler2); 
	 					artlist=userhandler2.getList();
	 						sortbyyear();
	 						i=0;
	 						int sno=1;
	 						while(i<artlist.size() && i<20)
	 						{
	 							Publication p=new Publication();
	 							p=artlist.get(i);
	 							if(p.year.compareTo(since)>=0)
	 							{
	 							// Vector<String> row=new Vector<String>();
			 						Vector<String> rows=new Vector<String>();
		 							rows.addElement(Integer.toString(sno++));
		 							String auth="";
		 							for(String test : p.authors) 
		 								auth=test+auth;
		 							rows.addElement(auth);
		 							rows.addElement(p.title);
		 							rows.addElement(p.pages);
		 							rows.addElement(p.year);
		 							rows.addElement(p.volume);
		 							rows.addElement(p.journal);
		 							rows.addElement(p.url);
									rowData.addElement(rows);
									System.out.println(rowData.size());
									//mainFrame.remove(resultSpaceDefault());
		 							mainFrame.getContentPane().add(resultSpaceDefault());
		 						}
	 							i++;
	 							sno=1;
	 						}
	 						//sort by year after
	 						//load arraylist into jtable
	 					}
	 					else if(choice1==4)
	 					{
	 						File inputFile = new File("dblp.xml");
						SAXParserFactory factory = SAXParserFactory.newInstance();
						SAXParser saxParser = factory.newSAXParser();
	 					userhandler2=new UserHandler2();
	 					userhandler2.tag=inputNameTag.getText();
	 					if(userhandler2.tag.length()==0)
	 					{
	 						JOptionPane.showMessageDialog(null,"Cannot be Empty");
	 					}
	 					saxParser.parse(inputFile, userhandler2); 
	 					artlist=userhandler2.getList();
	 						from=a.getText();
	 						while(from.length()!=4 && from.contains("[a-zA-Z]+") == false)
	 						{
	 							System.out.println(from.length());
	 							from = JOptionPane.showInputDialog("Invalid entry ! Please enter 'From' Year Again");
	 							System.out.println(from);
	 							a.setText(from);
	 						}
	 						to=b.getText();
	 						while(to.length()!=4 && to.contains("[a-zA-Z]+") == false)
	 						{
	 							System.out.println(to.length());
	 							to = JOptionPane.showInputDialog("Invalid entry ! Please enter 'To' Year Again");
	 							System.out.println(to);
	 							b.setText(to);
	 						}
							sortbyyear();
	 						//System.out.println(since);
	 						i=0;
	 						int sno=1;
	 						while(i<artlist.size() && i<20)
	 						{
	 							Publication p=new Publication();
	 							p=artlist.get(i);
	 							if(p.year.compareTo(from)>=0 && p.year.compareTo(to)<0)
	 							{
			 						Vector<String> rows=new Vector<String>();
		 							rows.addElement(Integer.toString(sno++));
		 							String auth="";
		 							for(String test : p.authors) 
		 								auth=test+auth;
		 							rows.addElement(auth);
		 							rows.addElement(p.title);
		 							rows.addElement(p.pages);
		 							rows.addElement(p.year);
		 							rows.addElement(p.volume);
		 							rows.addElement(p.journal);
		 							rows.addElement(p.url);
									rowData.addElement(rows);
									System.out.println(rowData.size());
									//mainFrame.remove(resultSpaceDefault());
		 							mainFrame.getContentPane().add(resultSpaceDefault());
		 						}
	 							i++;
	 						}
	 						//sort by since
	 						//load arraylist into jtable
	 						sno=1;
	 					}
					}
					catch (Exception e1)
					{
						e1.printStackTrace();
					}
				}
				else if(choice==2)
				{
					JOptionPane.showMessageDialog(null,"Please select a choice first");
				}
			}
		});
 			
 		}
 		else if(type.equals("Query2")) 
 		{

 			GridBagConstraints c = new GridBagConstraints();
 			querySpace.removeAll();
 			c.ipadx = 150;
	 		c.ipady = 17;
	 		c.gridx = 1;
	 		c.gridy = 1;
	 		c.insets = new Insets(40,0,0,0);
	 		c.weighty = 2;
	 		c.weightx = 2;
	 		c.anchor = GridBagConstraints.PAGE_START;
	 		querySpace.add(queryList, c);

	 		querySpace.repaint();
	 		querySpace.revalidate();

 			JLabel k = new JLabel("No. of Publications");
 			c.ipadx = 15;
 	 		c.ipady = 17;
 	 		c.gridx = 1;
 	 		c.gridy = 1;
 	 		c.insets = new Insets(100,47,0,0);
 	 		c.weighty = 2;
 	 		c.weightx = 2;
 	 		c.anchor = GridBagConstraints.FIRST_LINE_START;
 	 		querySpace.add(k, c);

 	 		inputk = new JTextPane();
 			inputk.setBorder(BorderFactory.createLineBorder(Color.black, 2, false));
 			inputk.setEditable(true);
 			inputk.setFont(new Font("Arial", Font.BOLD, 12));
 			c.ipadx = 80;
 			c.ipady = 10;
 			c.insets = new Insets(100,200,0,0);
 			querySpace.add(inputk, c);

 			JButton search = new JButton("Search");
 			c.ipadx = 50;
 			c.ipady = 10;
 			c.insets = new Insets(200,100,0,0);
 			querySpace.add(search, c);
 			search.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent w)
				{
					String kstring=inputk.getText();
					int k=Integer.parseInt(kstring);
					
					try
					{	
						mainFrame.remove(resultSpaceDefault());
						mainFrame.revalidate();
	 					mainFrame.getContentPane().add(resultSpaceDefault1());
						File inputFile = new File("dblp.xml");
						SAXParserFactory factory = SAXParserFactory.newInstance();
						SAXParser saxParser = factory.newSAXParser();
	 					userhandler3=new UserHandler3();
	 					saxParser.parse(inputFile,userhandler3);	
	 					HashMap<String,Integer> map=new HashMap<String,Integer>();
	 					map=userhandler3.getHash();
	 					userhandler4=new UserHandler4();
	 					factory = SAXParserFactory.newInstance();
	 					saxParser = factory.newSAXParser();
	 					userhandler4.getMap(map);
	 					saxParser.parse(inputFile,userhandler4);
	 					map=userhandler4.getHash();
	 					for (String name : map.keySet())
	 					{
				            String key = name.toString();
				            String value = map.get(name).toString();  
				            if(map.get(name)>k)
				            {
					            System.out.println(key + " " + value);
					            Vector<String> rows=new Vector<String>();
								rows.addElement(name);
	 							rows.addElement(map.get(name).toString());
								rowData.addElement(rows); 
				            }
				            mainFrame.getContentPane().add(resultSpaceDefault());
	 					}
	 				}
					catch (Exception e1)
					{
						e1.printStackTrace();
					}
				}
			});		

 			//querySpace.setBackground(Color.GREEN);
 			
 		}
 		else if(type.equals("Query3")) 
 		{
 			//querySpace.setBackground(Color.MAGENTA);
 			GridBagConstraints c = new GridBagConstraints();
 			querySpace.removeAll();

 			c.ipadx = 150;
	 		c.ipady = 17;
	 		c.gridx = 1;
	 		c.gridy = 1;
	 		c.insets = new Insets(40,0,0,0);
	 		c.weighty = 2;
	 		c.weightx = 2;
	 		c.anchor = GridBagConstraints.PAGE_START;
	 		querySpace.add(queryList, c);

	 		querySpace.repaint();
	 		querySpace.revalidate();

	 		JLabel author1 = new JLabel("Author 1");
 			c.ipadx = 15;
 	 		c.ipady = 17;
 	 		c.gridx = 1;
 	 		c.gridy = 1;
 	 		c.insets = new Insets(100,47,0,0);
 	 		c.weighty = 2;
 	 		c.weightx = 2;
 	 		c.anchor = GridBagConstraints.FIRST_LINE_START;
 	 		querySpace.add(author1, c);

 	 		author1input = new JTextPane();
 			author1input.setBorder(BorderFactory.createLineBorder(Color.black, 2, false));
 			author1input.setEditable(true);
 			author1input.setFont(new Font("Arial", Font.BOLD, 12));
 			c.ipadx = 80;
 			c.ipady = 10;
 			c.insets = new Insets(100,150,0,0);
 			querySpace.add(author1input, c);

 			JLabel author2 = new JLabel("Author 2");
 			c.ipadx = 15;
 	 		c.ipady = 17;
 	 		c.gridx = 1;
 	 		c.gridy = 1;
 	 		c.insets = new Insets(150,47,0,0);
 	 		c.weighty = 2;
 	 		c.weightx = 2;
 	 		c.anchor = GridBagConstraints.FIRST_LINE_START;
 	 		querySpace.add(author2, c);

 	 		author2input = new JTextPane();
 			author2input.setBorder(BorderFactory.createLineBorder(Color.black, 2, false));
 			author2input.setEditable(true);
 			author2input.setFont(new Font("Arial", Font.BOLD, 12));
 			c.ipadx = 80;
 			c.ipady = 10;
 			c.insets = new Insets(150,150,0,0);
 			querySpace.add(author2input, c);

 			JLabel author3 = new JLabel("Author 3");
 			c.ipadx = 15;
 	 		c.ipady = 17;
 	 		c.gridx = 1;
 	 		c.gridy = 1;
 	 		c.insets = new Insets(200,47,0,0);
 	 		c.weighty = 2;
 	 		c.weightx = 2;
 	 		c.anchor = GridBagConstraints.FIRST_LINE_START;
 	 		querySpace.add(author3, c);

 	 		author3input = new JTextPane();
 			author3input.setBorder(BorderFactory.createLineBorder(Color.black, 2, false));
 			author3input.setEditable(true);
 			author3input.setFont(new Font("Arial", Font.BOLD, 12));
 			c.ipadx = 80;
 			c.ipady = 10;
 			c.insets = new Insets(200,150,0,0);
 			querySpace.add(author3input, c);

 			JLabel author4 = new JLabel("Author 4");
 			c.ipadx = 15;
 	 		c.ipady = 17;
 	 		c.gridx = 1;
 	 		c.gridy = 1;
 	 		c.insets = new Insets(250,47,0,0);
 	 		c.weighty = 2;
 	 		c.weightx = 2;
 	 		c.anchor = GridBagConstraints.FIRST_LINE_START;
 	 		querySpace.add(author4, c);

 	 		author4input = new JTextPane();
 			author4input.setBorder(BorderFactory.createLineBorder(Color.black, 2, false));
 			author4input.setEditable(true);
 			author4input.setFont(new Font("Arial", Font.BOLD, 12));
 			c.ipadx = 80;
 			c.ipady = 10;
 			c.insets = new Insets(250,150,0,0);
 			querySpace.add(author4input, c);

 			JLabel author5 = new JLabel("Author 5");
 			c.ipadx = 15;
 	 		c.ipady = 17;
 	 		c.gridx = 1;
 	 		c.gridy = 1;
 	 		c.insets = new Insets(300,47,0,0);
 	 		c.weighty = 2;
 	 		c.weightx = 2;
 	 		c.anchor = GridBagConstraints.FIRST_LINE_START;
 	 		querySpace.add(author5, c);

 	 		author5input = new JTextPane();
 			author5input.setBorder(BorderFactory.createLineBorder(Color.black, 2, false));
 			author5input.setEditable(true);
 			author5input.setFont(new Font("Arial", Font.BOLD, 12));
 			c.ipadx = 80;
 			c.ipady = 10;
 			c.insets = new Insets(300,150,0,0);
 			querySpace.add(author5input, c);

 			JLabel year = new JLabel("Year");
 			c.ipadx = 15;
 	 		c.ipady = 17;
 	 		c.gridx = 1;
 	 		c.gridy = 1;
 	 		c.insets = new Insets(350,47,0,0);
 	 		c.weighty = 2;
 	 		c.weightx = 2;
 	 		c.anchor = GridBagConstraints.FIRST_LINE_START;
 	 		querySpace.add(year, c);

 	 		yearinput = new JTextPane();
 			yearinput.setBorder(BorderFactory.createLineBorder(Color.black, 2, false));
 			yearinput.setEditable(true);
 			yearinput.setFont(new Font("Arial", Font.BOLD, 12));
 			c.ipadx = 80;
 			c.ipady = 10;
 			c.insets = new Insets(350,150,0,0);
 			querySpace.add(yearinput, c);

 			//Search and Reset
 			JButton search = new JButton("Search");
 			c.ipadx = 50;
 			c.ipady = 10;
 			c.insets = new Insets(400,50,0,0);
 			querySpace.add(search, c);

 			// JButton reset = new JButton("Reset");
 			// c.ipadx = 30;
 			// c.ipady = 10;
 			// c.insets = new Insets(400,200,0,0);
 			// querySpace.add(reset, c);

 			//querySpace.setBackground(Color.GREEN);
 		}
		// but.addActionListener(new ActionListener()
		// {
 	}
 	private JPanel resultSpaceDefault1() 
	{
		JPanel result = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		result.removeAll();

	 	result.repaint();
	 	result.revalidate();
		
		result.setBounds(DEFAULT_WIDTH/3, 120, 2*DEFAULT_WIDTH/3, 557);
		// result.setBackground(Color.YELLOW);
		
		// JLabel label = new JLabel("Result Count : "+ userhandler1.getTotal());
		// label.setFont(new Font("Arial", Font.BOLD, 30));
		// c.gridx = 0;
		// c.gridy = 0;
		// c.weighty = 1;
		// c.insets = new Insets(10,0,0,0);
		// c.anchor = GridBagConstraints.FIRST_LINE_START;
		// result.add(label, c);
		// result.repaint();
		
		
		JButton but = new JButton("Next");
		but.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 15;
		c.ipadx = 30;
		c.insets = new Insets(0,0,20,0);
		c.anchor = GridBagConstraints.PAGE_END;
		result.add(but, c);
		result.repaint();
		but.setVisible(true);
		but.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent w)
			{
				int r=i;
				while(i<=artlist.size() && i<r+20 )
				{
					Publication p=new Publication();
					p=artlist.get(i);
					// Vector<String> row=new Vector<String>();
					Vector<String> rows=new Vector<String>();
					rows.addElement(Integer.toString(i+1));
					String auth="";
					for(String test : p.authors) 
					auth=test+auth;
					rows.addElement(auth);
					rows.addElement(p.title);
					rows.addElement(p.pages);
					rows.addElement(p.year);
					rows.addElement(p.volume);
					rows.addElement(p.journal);
					rows.addElement(p.url);
					rowData.addElement(rows);
					System.out.println(rowData.size());
					//mainFrame.remove(resultSpaceDefault());
					mainFrame.getContentPane().add(resultSpaceDefault());
					i++;
				}
			}
		});	
		Vector<String> columnNames = new Vector<String>();
		columnNames.addElement("S.No");
		columnNames.addElement("Authors");
		columnNames.addElement("Total");
		JTable table = new JTable(rowData, columnNames);

		JScrollPane spTable = new JScrollPane(table);
		c.anchor = GridBagConstraints.CENTER;
		c.weighty = 1;
		result.add(spTable, c);
		result.repaint();
		mainFrame.repaint();
		mainFrame.revalidate();
		result.setVisible(true);
		return result;
	}
	private JPanel resultSpaceDefault() 
	{
		JPanel result = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		result.removeAll();

	 	result.repaint();
	 	result.revalidate();
		
		result.setBounds(DEFAULT_WIDTH/3, 120, 2*DEFAULT_WIDTH/3, 557);
		// result.setBackground(Color.YELLOW);
		
		// JLabel label = new JLabel("Result Count : "+ number);
		// label.setFont(new Font("Arial", Font.BOLD, 30));
		// c.gridx = 0;
		// c.gridy = 0;
		// c.weighty = 1;
		// c.insets = new Insets(10,0,0,0);
		// c.anchor = GridBagConstraints.FIRST_LINE_START;
		// result.add(label, c);
		// result.repaint();
		
		
		JButton but = new JButton("Next");
		but.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 15;
		c.ipadx = 30;
		c.insets = new Insets(0,0,20,0);
		c.anchor = GridBagConstraints.PAGE_END;
		result.add(but, c);
		result.repaint();
		but.setVisible(true);

		Vector<String> columnNames = new Vector<String>();
		columnNames.addElement("S.No");
		columnNames.addElement("Authors");
		columnNames.addElement("Title");
		columnNames.addElement("Pages");
		columnNames.addElement("Year");
		columnNames.addElement("Volume");
		columnNames.addElement("Journal");
		columnNames.addElement("URL");
		table = new JTable(rowData, columnNames);

		JScrollPane spTable = new JScrollPane(table);
		c.anchor = GridBagConstraints.CENTER;
		c.weighty = 1;
		result.add(spTable, c);
		// result.repaint();
		// mainFrame.repaint();
		// mainFrame.revalidate();
		// result.setVisible(true);
		but.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent w)
			{
				int r=i;
				while(i<artlist.size() && i<r+20)
				{
					Publication p=new Publication();
					p=artlist.get(i);
					// Vector<String> row=new Vector<String>();
					Vector<String> rows=new Vector<String>();
					rows.addElement(Integer.toString(i+1));
					String auth="";
					for(String test : p.authors) 
					auth=test+auth;
					rows.addElement(auth);
					rows.addElement(p.title);
					rows.addElement(p.pages);
					rows.addElement(p.year);
					rows.addElement(p.volume);
					rows.addElement(p.journal);
					rows.addElement(p.url);
					rowData.addElement(rows);
					System.out.println(rowData.size());
					//mainFrame.remove(resultSpaceDefault());
					mainFrame.getContentPane().add(resultSpaceDefault());
					i++;
				}
			}
		});	
		result.repaint();
		mainFrame.repaint();
		mainFrame.revalidate();
		result.setVisible(true);
		return result;
	}
	public JFrame showComboBox()
	{
		mainFrame.getContentPane().add(titleSpaceDefault());
		//mainFrame.getContentPane().add(resultSpaceDefault());
		mainFrame.getContentPane().add(querySpaceDefault());
		mainFrame.repaint();
		mainFrame.revalidate();
		mainFrame.setVisible(true);
		return mainFrame;
	}
}