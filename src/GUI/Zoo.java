package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

import Transactions.ManagerTransactions;
import Transactions.VisitorTransactions;
import DatabaseConnection.DatabaseConnection;


public class Zoo extends JDialog {
	
	public String querydescription = "<html><p><b>Welcome to the Zoo</b></p></html>";
	public String alertdescription = "<html><p color='green'>There are no problems</p></html>";
	public String currentbutton = "None";
	public JLabel descriptionlabel = new JLabel(querydescription);
	public JLabel alertlabel = new JLabel(alertdescription);
	public JTextArea field1;
	public JTextArea field2;
	public JTextArea field3;
	public DatabaseConnection dc;
	public ManagerTransactions mt; 
	public VisitorTransactions vt;
	
	public Zoo() {
		
		dc = new DatabaseConnection();
		dc.connectToDB();
		Connection con = dc.getConnection();
		mt = new ManagerTransactions(con);
		vt = new VisitorTransactions(con, 1);
		
		initUI();
		
	}
	
	private void initUI() {
		
		JPanel outerbox = new JPanel();
        outerbox.setLayout(new BoxLayout(outerbox, BoxLayout.Y_AXIS));
        add(outerbox);
        
        	JPanel topbox = new JPanel();
        	topbox.setLayout(new BoxLayout(topbox, BoxLayout.X_AXIS));
        	
        		JPanel inputbox = new JPanel();
        		inputbox.setLayout(new BoxLayout(inputbox, BoxLayout.Y_AXIS));
        		
        			JPanel f1l = new JPanel();        		
        				String l1 = "Field 1";      			
        				JLabel label1 = new JLabel(l1);
        				f1l.add(label1);
        			inputbox.add(f1l);
        			      			
        			JPanel f1t = new JPanel();
	        			JTextArea text1 = new JTextArea();
	        			field1 = text1;
	        			text1.setPreferredSize(new Dimension(100, 16));
	        			f1t.add(text1);
	        		inputbox.add(f1t);
	        		
        			JPanel f2l = new JPanel();        		
    					String l2 = "Field 2";      			
    					JLabel label2 = new JLabel(l2);
    					f2l.add(label2);
    				inputbox.add(f2l);
    				
        			JPanel f2t = new JPanel();
	        			JTextArea text2 = new JTextArea();
	        			field2 = text2;
	        			text2.setPreferredSize(new Dimension(100, 16));
	        			f2t.add(text2);
        			inputbox.add(f2t);
        			
        			JPanel f3l = new JPanel();        		
    					String l3 = "Field 3";      			
    					JLabel label3 = new JLabel(l3);
    					f3l.add(label3);
    				inputbox.add(f3l);
    				
        			JPanel f3t = new JPanel();
	        			JTextArea text3 = new JTextArea();
	        			field3 = text3;
	        			text3.setPreferredSize(new Dimension(100, 16));
	        			f3t.add(text3);
        			inputbox.add(f3t);
        			
        		topbox.add(inputbox);
        		
        		JPanel displaybox = new JPanel();
        		displaybox.setLayout(new BoxLayout(displaybox, BoxLayout.Y_AXIS));
	        			
	    			JPanel description = new JPanel();        		
						String desc = querydescription;      			
						JLabel desclabel = new JLabel(desc);
						descriptionlabel = desclabel;
						description.add(desclabel);
					displaybox.add(description);
					
	    			JPanel alert = new JPanel();        		
						String al = alertdescription;      			
						JLabel allabel = new JLabel(al);
						alertlabel = allabel;
						alert.add(allabel);
					displaybox.add(alert);
        		
        		topbox.add(displaybox);
        	
        	outerbox.add(topbox);
        	
        	JPanel gridbox = new JPanel();
        	gridbox.setLayout(new BoxLayout(gridbox, BoxLayout.X_AXIS));
		
        		JPanel visgrid = new JPanel();
        		visgrid.setLayout(new GridLayout(3, 3));       		
        			
        			JButton visdesc = new JButton("VISITOR FUNCTIONS");
        			visdesc.setForeground(new Color(0, 0, 255).brighter());
        			visgrid.add(visdesc);
        			
        			JButton vis1 = new JButton("Buy Souvenir");
        			vis1.setForeground(new Color(0, 0, 255).brighter());
        			vis1.addActionListener(new ActionListener() {       				
    	                @Override
    	                public void actionPerformed(ActionEvent event) {    	                	
    	                	String d ="<html><p><b>Welcome to the Zoo</b></p><br><br><br><p>Enter the name of the souvenir you'd like to buy from the zoo's souvenir shop in <b>Field 1</b>.</p>";
    	                    describe(d, "vis1");
    	                }       				
        			});       			
        			visgrid.add(vis1);
        			
        			JButton vis2 = new JButton("Take Tour");
        			vis2.setForeground(new Color(0, 0, 255).brighter());
        			vis2.addActionListener(new ActionListener() {       				
    	                @Override
    	                public void actionPerformed(ActionEvent event) {
    	                	String d ="<html><p><b>Welcome to the Zoo</b></p><br><br><br><p>Go ahead and take a tour! No field entry required..</p>";
    	                    describe(d, "vis2");
    	                }       				
        			});       			
        			visgrid.add(vis2);
        			
        			JButton vis3 = new JButton("Buy Food");
        			vis3.setForeground(new Color(0, 0, 255).brighter());
        			vis3.addActionListener(new ActionListener() {       				
    	                @Override
    	                public void actionPerformed(ActionEvent event) {
    	                	String d ="<html><p><b>Welcome to the Zoo</b></p><br><br><br><p>Enter the name of the food you'd like to buy from the zoo's cafeteria in <b>Field 1</b>.</p>";
    	                    describe(d, "vis3");
    	                }       				
        			});       			
        			visgrid.add(vis3);
        			
        			JButton vis4 = new JButton("Search For Animal");
        			vis4.setForeground(new Color(0, 0, 255).brighter());
        			vis4.addActionListener(new ActionListener() {       				
    	                @Override
    	                public void actionPerformed(ActionEvent event) {
    	                	String d ="<html><p><b>Welcome to the Zoo</b></p><br><br><br><p>Enter the type of animal you'd like to locate in <b>Field 1</b>.<br><br>Enter the name of the animal you'd like to locate in <b>Field 2</b></p>";
    	                    describe(d, "vis4");
    	                }       				
        			});       			
        			visgrid.add(vis4);
        			
        			JButton vis5 = new JButton("Search For Section");
        			vis5.setForeground(new Color(0, 0, 255).brighter());
        			vis5.addActionListener(new ActionListener() {       				
    	                @Override
    	                public void actionPerformed(ActionEvent event) {
    	                	String d ="<html><p><b>Welcome to the Zoo</b></p><br><br><br><p>Enter the name of the section you'd like to locate in <b>Field 1</b>.</p>";
    	                    describe(d, "vis5");
    	                }       				
        			});       			
        			visgrid.add(vis5);
        			
        		gridbox.add(visgrid);
        		
        		JPanel mangrid = new JPanel();
        		mangrid.setLayout(new GridLayout(3, 4));
        		
	        		JButton mandesc = new JButton("MANAGER FUNCTIONS");
	        		mandesc.setForeground(new Color(255, 0, 0).brighter());
	    			mangrid.add(mandesc);
	    			
	    			JButton man1 = new JButton("Check Animal");
	    			man1.setForeground(new Color(255, 0, 0).brighter());
	    			man1.addActionListener(new ActionListener() {       				
		                @Override
		                public void actionPerformed(ActionEvent event) {
    	                	String d ="<html><p><b>Welcome to the Zoo</b></p><br><br><br><p>Enter the type of animal you'd like to give a checkup in <b>Field 1</b>.<br><br>Enter the name of the animal you'd like to give a checkup in <b>Field 2</b><br><br>Enter the date to schedule the vet checkup in <b>Field 3</b></p>";
    	                    describe(d, "man1");
		                }       				
	    			});       			
	    			mangrid.add(man1);
	    			
	    			JButton man2 = new JButton("Build Section");
	    			man2.setForeground(new Color(255, 0, 0).brighter());
	    			man2.addActionListener(new ActionListener() {       				
		                @Override
		                public void actionPerformed(ActionEvent event) {
		                	String d ="<html><p><b>Welcome to the Zoo</b></p><br><br><br><p>Enter the name of the section theme you'd like to build in<b>Field 1</b>.<br><br>Enter the number of the new section in <b>Field 2</b></p>";
    	                    describe(d, "man2");
		                }       				
	    			});       			
	    			mangrid.add(man2);
	    			
	    			JButton man3 = new JButton("Build Enclosure");
	    			man3.setForeground(new Color(255, 0, 0).brighter());
	    			man3.addActionListener(new ActionListener() {       				
		                @Override
		                public void actionPerformed(ActionEvent event) {
		                	String d ="<html><p><b>Welcome to the Zoo</b></p><br><br><br><p>Enter the name of the section theme you'd like to build the enclosure in <b>Field 1</b>.<br><br>Enter the type of animal to be held in <b>Field 2</b></p>";
    	                    describe(d, "man3");
		                }       				
	    			});       			
	    			mangrid.add(man3);
	    			
	    			JButton man4 = new JButton("Buy Animal");
	    			man4.setForeground(new Color(255, 0, 0).brighter());
	    			man4.addActionListener(new ActionListener() {       				
		                @Override
		                public void actionPerformed(ActionEvent event) {
    	                	String d ="<html><p><b>Welcome to the Zoo</b></p><br><br><br><p>Enter the name of the animal you'd like to buy in <b>Field 1</b>.</p>";
    	                    describe(d, "man4");
    	                    // DOES THIS NEED 2 FIELDS FOR MONEY?
		                }       				
	    			});       			
	    			mangrid.add(man4);
	    			
	    			JButton man5 = new JButton("Feed Animal");
	    			man5.setForeground(new Color(255, 0, 0).brighter());
	    			man5.addActionListener(new ActionListener() {       				
		                @Override
		                public void actionPerformed(ActionEvent event) {
    	                	String d ="<html><p><b>Welcome to the Zoo</b></p><br><br><br><p>Enter the type of animal you'd like to feed in <b>Field 1</b>.<br><br>Enter the name of the animal you'd like to feed in <b>Field 2</b></p>";
    	                    describe(d, "man5");
		                }       				
	    			});       			
	    			mangrid.add(man5);
	    			
	    			JButton man6 = new JButton("Buy Food Supplies");
	    			man6.setForeground(new Color(255, 0, 0).brighter());
	    			man6.addActionListener(new ActionListener() {       				
		                @Override
		                public void actionPerformed(ActionEvent event) {
    	                	String d ="<html><p><b>Welcome to the Zoo</b></p><br><br><br><p>Enter the type of food you'd like to buy in <b>Field 1</b>.<br><br>Enter the amount you'd like to buy in <b>Field 2</b></p>";
    	                    describe(d, "man6");
		                }       				
	    			});       			
	    			mangrid.add(man6);
	    			
	    			JButton man7 = new JButton("Buy Souvenir Supplies");
	    			man7.setForeground(new Color(255, 0, 0).brighter());
	    			man7.addActionListener(new ActionListener() {       				
		                @Override
		                public void actionPerformed(ActionEvent event) {
    	                	String d ="<html><p><b>Welcome to the Zoo</b></p><br><br><br><p>Enter the type of souvenirs you'd like to buy in <b>Field 1</b>.<br><br>Enter the amount you'd like to buy in <b>Field 2</b></p>";
    	                    describe(d, "man6");
		                }       				
	    			});       			
	    			mangrid.add(man7);
	    			
	    			JButton man8 = new JButton("End Day");
	    			man8.setForeground(new Color(255, 0, 0).brighter());
	    			man8.addActionListener(new ActionListener() {       				
		                @Override
		                public void actionPerformed(ActionEvent event) {
    	                	String d ="<html><p><b>Welcome to the Zoo</b></p><br><br><br><p>End a long hard day, resetting animal feeding and tour guide availability.</p>";
    	                    describe(d, "man8");
		                }       				
	    			});       			
	    			mangrid.add(man8);
        		
        		gridbox.add(mangrid);
        		
        	outerbox.add(gridbox);
        	
        	JPanel optionspanel = new JPanel(new FlowLayout());
        	
            	JButton runbutton = new JButton("Run Query");

            	runbutton.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent event) {
	                	runQuery();
	                }
	            });
            	
	            
	            JButton quitbutton = new JButton("Quit");
	
	            quitbutton.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent event) {
	                    System.exit(0);
	                }
	            });
            
            optionspanel.add(runbutton);
            optionspanel.add(quitbutton);
        	
        	outerbox.add(optionspanel);
        	
        setTitle("304 Zoo");
        setSize(new Dimension(900, 600));
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
	}
	
	private void runQuery() {
		String q1;
    	String q2;
    	String q3;
        String cb = currentbutton;
        switch (cb) {
        	case "vis1":
        		q1 = field1.getText().toString();
        		if (q1 == null || !isAlpha(q1)){
        			alert("<html><p color='red'> You must enter a name for your souvenir in field 1 using valid letters!</p></html>");
        		} else {
        			q2 = vt.purchaseItem(q1);
        			if (q2 == "Item purchased") {
        				alert("<html><p color='green'> You bought one "+ q1 +"</p></html>");
        			} else {
        				alert("<html><p color='red'> We are all out of that souvenir!");
        			}
        		}
        		break;
        	case "vis2":
        		// Run Query, implement if statement to check if there is available guide
    			if (true) {
    				alert("<html><p color='green'>Enjoy your tour!</p></html>");
    			} else {
    				alert("<html><p color='red'> No tour guides are available!");
    			}
        		break;
        	case "vis3":
        		q1 = field1.getText();
        		if (q1 == null || !isAlpha(q1)){
        			alert("<html><p color='red'> You must enter the type of food you'd like to order in field 1 using valid letters!</p></html>");
        		} else {
        			// Run Query, check if there is food to buy
        			if (true) {
        				alert("<html><p color='green'> You bought one "+ q1 +"</p></html>");
        			} else {
        				alert("<html><p color='red'> We are all out of that type of food!");
        			}
        		}
            	break;
        	case "vis4":
        		q1 = field1.getText();
        		q2 = field2.getText();
        		if (q1 == null || !isAlpha(q1)){
        			alert("<html><p color='red'> You must enter the type of animal you'd like to find in field 1 using valid letters!</p></html>");
        		} else if (q2 == null || !isAlpha(q2)) {
					alert("<html><p color='red'> You must enter the name of the animal you'd like to find in field 2 using valid letters!</p></html>");
        		} else {
        			// Run Query, check if the animal exists
        			q1 = "Arctic";
        			q2 = "12";
        			if (true) {
        				alert("<html><p color='green'> The animal lives in the "+ q1 +" section in enclosure " + q2 +"</p></html>");
        			} else {
        				alert("<html><p color='red'> The animal does not live at this zoo</p></html>");
        			}
        		}
        		break;
        	case "vis5":
        		q1 = field1.getText();
        		if (q1 == null || !isAlpha(q1)){
        			alert("<html><p color='red'> You must enter the name of that section you'd like to find in field 1 using valid letters!</p></html>");
        		} else {
        			// Run Query, check if there is food to buy
        			if (true) {
        				alert("<html><p color='green'> The section id of that section is "+ q1 +"</p></html>");
        			} else {
        				alert("<html><p color='red'> That is not a section at this zoo!");
        			}
        		}
        		break;
        	case "man1":
        		q1 = field1.getText();
        		q2 = field2.getText();
        		q3 = field3.getText();
        		if (q1 == null || !isAlpha(q1)){
        			alert("<html><p color='red'> You must enter the type of animal you'd like to checkup in field 1 using valid letters!</p></html>");
        		} else if (q2 == null || !isAlpha(q2)) {
        			alert("<html><p color='red'> You must enter the name of the animal you'd like to checkup in field 2 using valid letters!</p></html>");
        		} else if (q3 == null || !isAlphaNumeric(q3)) {
        			alert("<html><p color='red'> You must enter the date of the intended checkup in field 1 using valid letters!</p></html>");
        		} else {
        			// Run Query
        			q1 = "Tiger";
        			q2 = "Ben";
        			q3 = "March 11";
        			if (false) {
        				// no animal of that type
        			} else if (false) {
        				// no animal of that name and type
        			} else if (false) {
        				// animal had checkup on that date
        			} else {
        				alert("<html><p color='green'>The "+ q1 +" " + q2 + " got a checkup on " + q3 +"</p></html>");
        			}                  			
        		}
        		break;
        	case "man2":
        		q1 = field1.getText();
        		q2 = field2.getText();
        		if (q1 == null || !isAlpha(q1)){
        			alert("<html><p color='red'> You must enter the name of the section theme you'd like to build!</p></html>");
        		} else if (q2 == null || !isNumber(q2)) {
					alert("<html><p color='red'> You must enter the section number of the section you'd like to build!</p></html>");
        		} else {
        			// Run Query, check if the animal exists
        			q1 = "Tropical";
        			q2 = "6";
        			if (true) {
        				alert("<html><p color='green'> You built section "+ q1 +" with section id " + q2 +"</p></html>");
        			} else if (false){
        				alert("<html><p color='red'> That section id already exists</p></html>");
        			} else {
        				alert("<html><p color='red'> That section theme already exists</p></html>");
        			}
        		}
        		break;
        	case "man3":
        		q1 = field1.getText();
        		q2 = field2.getText();
        		if (q1 == null || !isAlpha(q1)){
        			alert("<html><p color='red'> You must enter the name of the section theme you'd like to build your enclosure in!</p></html>");
        		} else if (q2 == null || !isAlpha(q2)) {
					alert("<html><p color='red'> You must enter the type of animal you want to keep!</p></html>");
        		} else {
        			// Run Query, check if the animal exists
        			q1 = "Arctic";
        			q2 = "12";
        			if (true) {
        				alert("<html><p color='green'> You built an enclosure in section "+ q1 +" that will hold animals of type " + q2 +"</p></html>");
        			} else if (false){
        				alert("<html><p color='red'> That theme does not exist</p></html>");
        			} else {
        				alert("<html><p color='red'> That enclosure already exists</p></html>");
        			}
        		}
        		break;
        	case "man4":
        		q1 = field1.getText();
        		q2 = field2.getText();
        		if (q1 == null || !isAlpha(q1)){
        			alert("<html><p color='red'> You must enter the type of animal you'd like to buy!</p></html>");
        		} else if (q2 == null || !isAlpha(q2)) {
					alert("<html><p color='red'> You must enter the name of the animal you'd like to buy!</p></html>");
        		} else {
        			// Run Query, check if the animal exists
        			q1 = "Penguin";
        			q2 = "Dave";
        			q3 = "17";
        			String q4 = "Arctic";
        			if (true) {
        				alert("<html><p color='green'> You bought a "+ q1 +" named " + q2 +" that lives in enclosure " + q3 +" in the " + q4 +" section</p></html>");
        			} else if (false){
        				alert("<html><p color='green'> You bought a "+ q1 +" named " + q2 +" that lives in enclosure " + q3 +" in the " + q4 +" section</p></html>");
        			} else {
        				alert("<html><p color='red'> There is no enclosure for this animal!</p></html>");
        			}
        		}
        		break;
        	case "man5":
        		q1 = field1.getText();
        		q2 = field2.getText();
        		if (q1 == null || !isAlpha(q1)){
        			alert("<html><p color='red'> You must enter the type of animal you'd like to feed!</p></html>");
        		} else if (q2 == null || !isAlpha(q2)) {
					alert("<html><p color='red'> You must enter the name of the animal you'd like to feed!</p></html>");
        		} else {
        			// Run Query, check if the animal exists
        			q1 = "Penguin";
        			q2 = "Dave";
        			if (true) {
        				alert("<html><p color='green'> You have fed the "+ q1 +" " + q2 + "</p></html>");
        			} else if (false){
        				alert("<html><p color='red'> That animal does not exist!</p></html>");
        			} else {
        				alert("<html><p color='red'> The zoo needs to buy food before it can feed this animal!</p></html>");
        			}
        		}
        		break;
        	case "man6":
        		q1 = field1.getText();
        		q2 = field2.getText();
        		if (q1 == null || !isAlpha(q1)){
        			alert("<html><p color='red'> You must enter the food you'd like to buy!</p></html>");
        		} else if (q2 == null || !isNumber(q2)) {
					alert("<html><p color='red'> You must enter the amount of food you'd like to buy!</p></html>");
        		} else {
        			// Run Query, check if the animal exists
        			q1 = "Tacos";
        			q2 = "30";
        			if (true) {
        				alert("<html><p color='green'> You have bought "+ q2 +" " + q1 + "</p></html>");
        			} else {
        				alert("<html><p color='red'> The zoo cannot afford that!</p></html>");
        			}
        		}
        		break;
        	case "man7":
        		q1 = field1.getText();
        		q2 = field2.getText();
        		if (q1 == null || !isAlpha(q1)){
        			alert("<html><p color='red'> You must enter the souvenir you'd like to buy!</p></html>");
        		} else if (q2 == null || !isNumber(q2)) {
					alert("<html><p color='red'> You must enter the amount of souvenirs you'd like to buy!</p></html>");
        		} else {
        			// Run Query, check if the animal exists
        			q1 = "Turtle Hat";
        			q2 = "15";
        			if (true) {
        				alert("<html><p color='green'> You have bought "+ q2 +" " + q1 + "</p></html>");
        			} else {
        				alert("<html><p color='red'> The zoo cannot afford that!</p></html>");
        			}
        		}
        		break;
        	case "man8":
        		// Run Query
        		alert("<html><p green='green'All tour guides and animal hunger levels have been refreshed!");
        		break;
        	case "mandesc":
        		break;
        	case "visdesc":
        		break;
        	default:
        		alert("<html><p color='red'>Please choose a query to run..</p></html>");	                    	
        }
        field1.setText("");
        field2.setText("");
        field3.setText("");
	}
	
	private void describe(String description, String button) {
		
		currentbutton = button;
		descriptionlabel.setText(description);
		
	}
	
	private void alert(String alert) {
		
		alertlabel.setText(alert);
		
	}
	
	private boolean isAlpha (String toCheck){
		return toCheck.matches("[a-zA-Z\\s]+");
	}
	
	private boolean isNumber (String toCheck) {
		return toCheck.matches("[0-9]+");
	}
	
	private boolean isAlphaNumeric (String toCheck){
		return toCheck.matches("[a-zA-Z0-9\\s]+");
	}
	
	public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
        
            @Override
            public void run() {
                Zoo zoo = new Zoo();
                zoo.setVisible(true);
            }
        });
    }
}
