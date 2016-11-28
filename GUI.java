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

public class GUI 
{
	int choice =2;
	private JFrame mainFrame;
	private static int DEFAULT_HEIGHT = 700;
	private static int DEFAULT_WIDTH = 1000;
	private static String Title = "DBLP Search Engine";
	UserHandler1 userhandler1=new UserHandler1();
	UserHandler2 userhandler2=new UserHandler2();
	//UserHandler2 userhandler2;
	public GUI() 
	{
		mainFrame = new JFrame();
		mainFrame.setBounds(180, 35, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setTitle(Title);
		mainFrame.setLayout(null);
	}
 	private JPanel titleSpaceDefault() 
 	{
		JPanel title = new JPanel();
		title.setSize(new Dimension(DEFAULT_WIDTH, 120));
		title.setBackground(Color.CYAN);		
		
		JLabel text = new JLabel("DBLP QUERY ENGINE");
		text.setFont(new Font("Arial", Font.BOLD, 60));
		text.setLocation(10, 10);
		text.setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT/5));
		
		title.add(text);
		title.setVisible(true);
		return title;
	}
 	private JPanel querySpaceDefault() 
 	{
 		final JPanel query = new JPanel(new GridBagLayout());
 		final GridBagConstraints c = new GridBagConstraints();
 		query.setBounds(0, 120, DEFAULT_WIDTH/3, 557);
 		query.setBackground(Color.RED);
 		
 		String[] queryStrings = { "Querries", "Query1", "Query2", "Query3" };
 		final JComboBox<String> queryList = new JComboBox<String>(queryStrings);
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
 						querySpace("Query1", query);
 						//add/repaint this JPanel
 					}
 					else if (item.equals("Query2")) 
 					{
 						querySpace("Query2", query);
 						//add/repaint this JPanel
 					}
 					else if(item.equals("Query3")) 
 					{
 						querySpace("Query3", query);
 						//add/repaint this JPanel
 					}
 					else if(item.equals("Querries")) 
 					{
 						query.removeAll();
 						query.add(queryList, c);
 						query.repaint();
 						query.revalidate();
 					}
 				}
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
		query.add(but, c);
		query.repaint();
		but.setVisible(true);
		but.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent w)
			{
				if(choice==0)
				{
					try
					{	
						File inputFile = new File("dblp.xml");
						SAXParserFactory factory = SAXParserFactory.newInstance();
						SAXParser saxParser = factory.newSAXParser();
	 					userhandler1=new UserHandler1();
	 					saxParser.parse(inputFile, userhandler1); 
						mainFrame.repaint();
						mainFrame.revalidate();
						mainFrame.setVisible(false);
						mainFrame.setVisible(true);
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
						File inputFile = new File("dblp.xml");
						SAXParserFactory factory = SAXParserFactory.newInstance();
						SAXParser saxParser = factory.newSAXParser();
	 					userhandler2=new UserHandler2();
	 					saxParser.parse(inputFile, userhandler2); 
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
 		query.setVisible(true);
 		return query;
 	}
 	private void querySpace(String type, JPanel querySpace) 
 	{
 		if(type.equals("Query1")) 
 		{
 			GridBagConstraints c = new GridBagConstraints();
 			querySpace.setBackground(Color.RED);
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
 	 		querySpace.revalidate();
 			querySpace.repaint();
 			
 			JLabel nameLabel = new JLabel("Name/Title Tags");
 			c.anchor = GridBagConstraints.FIRST_LINE_START;
 			c.insets = new Insets(150,20,0,0);
 			querySpace.add(nameLabel, c);
 			
 			JTextPane inputNameTag = new JTextPane();
 			inputNameTag.setBorder(BorderFactory.createLineBorder(Color.black, 2, false));
 			inputNameTag.setEditable(true);
 			inputNameTag.setFont(new Font("Arial", Font.BOLD, 12));
 			c.anchor = GridBagConstraints.FIRST_LINE_START;
 			c.ipadx = 80;
 			c.ipady = 10;
 			c.insets = new Insets(150,150,0,0);
 			querySpace.add(inputNameTag, c);
 			
 		}
 		else if(type.equals("Query2")) 
 		{
 			querySpace.setBackground(Color.GREEN);
 			querySpace.repaint();
 		}
 		else if(type.equals("Query3")) 
 		{
 			querySpace.setBackground(Color.MAGENTA);
 			querySpace.repaint();
 		}
 	}
	private JPanel resultSpaceDefault() 
	{
		JPanel result = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		result.setBounds(DEFAULT_WIDTH/3, 120, 2*DEFAULT_WIDTH/3, 557);
		result.setBackground(Color.YELLOW);
		
		JLabel label = new JLabel("Result Count : "+ userhandler1.getTotal());
		System.out.println("incfdin"+userhandler1.getTotal());
		label.setFont(new Font("Arial", Font.BOLD, 30));
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 1;
		c.insets = new Insets(10,0,0,0);
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		result.add(label, c);
		result.repaint();
		
		
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
		
		
		Object rowData[][] = { };
		Object columnNames[] = { };
		
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
	public JFrame showComboBox()
	{
		mainFrame.getContentPane().add(titleSpaceDefault());
		mainFrame.getContentPane().add(resultSpaceDefault());
		mainFrame.getContentPane().add(querySpaceDefault());
		mainFrame.repaint();
		mainFrame.revalidate();
		mainFrame.setVisible(true);
		return mainFrame;
	}
	// public static void main(String args[]) {
	// 	GUI box = new GUI();
	// 	box.showComboBox();
	// }
}