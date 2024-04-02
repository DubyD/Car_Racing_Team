
//@author Gus Warmington (AW)
//art by Gus Warmington (AW)
//This class creates and displays the main menu, allowing
//for game customization and initiation. It also is used
//to display results after a race is finished.

import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class MenuGui extends JPanel{
    private JLabel instructions;
    private JButton startButton;
    private JComboBox<String> sizeSelection;
    private JComboBox<String> racerSelection;
    private int windowHeight;
    private int windowWidth;
    private JFrame window;
    private String[] results;
    public MenuGui(){
        this.instructions = new JLabel();
        
        this.windowHeight = 600;
        this.windowWidth = 800;
        this.window = new JFrame();
        this.startButton = new JButton("Start");
        this.startButton.setBounds((windowWidth/2)-200, 3*windowHeight/4, 150, 30);
    }
    /**
 * Constructor
 * @param frame root JFrame (window element)
    */
    public MenuGui(JFrame frame){
        this.instructions = new JLabel();
        
        this.windowHeight = frame.getHeight();
        this.windowWidth = frame.getWidth();
        this.window = frame;
        this.startButton = new JButton("Start");
        this.startButton.setBounds((windowWidth/2)-200, 3*windowHeight/4, 150, 30);
    }
    /**
     * +titleScreen: void
     * This method initializes the initial title screen, 
     * creates and displays elements 
     */
    public void titleScreen(){
        //Exit button simply exits the game
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(event -> {
            System.exit(0);
        });
        //Set to be on bottom left side of screen
        exitButton.setBounds(50+(windowWidth/2), 3*windowHeight/4, 100, 30);
        
        //instructions text, set to be on right side of frame
        //HTML formatting to create automatic linebreaks
        instructions.setBounds(520, 0, 300, 500);
        instructions.setText("<html>Welcome to the Racing Game!<br/>Choose the size of the square racing grid"+""
                + "<br/> and the number of racers. <br/>"
                + "By Will Duby, Victor Serra, Eric Lim, and <br/>Gus Warmington</html>");
        this.add(instructions);
        
        //Load all car images:
        //car1
        URL car1URL = getClass().getResource("images/carEnlarged1.png"); //File location
        Icon car1Icon = new ImageIcon(car1URL); //New icon using this url
        JLabel car1Label = new JLabel(car1Icon); //New label using this icon
        car1Label.setBounds((windowWidth/4), 2*(windowHeight/5), 150, 90); //setting position
        //car2
        URL car2URL = getClass().getResource("images/carEnlarged2.png");
        Icon car2Icon = new ImageIcon(car2URL);
        JLabel car2Label = new JLabel(car2Icon);
        car2Label.setBounds((windowWidth/4)+70, 2*(windowHeight/5), 150, 90);
        //car3
        URL car3URL = getClass().getResource("images/carEnlarged3.png");
         Icon car3Icon = new ImageIcon(car3URL);
        JLabel car3Label = new JLabel(car3Icon);
        car3Label.setBounds((windowWidth/4)+140, 2*(windowHeight/5), 150, 90);
        //car4
        URL car4URL = getClass().getResource("images/carEnlarged4.png");
         Icon car4Icon = new ImageIcon(car4URL);
        JLabel car4Label = new JLabel(car4Icon);
        car4Label.setBounds((windowWidth/4)+210, 2*(windowHeight/5), 150, 90);
        
        //Add to MenuGui JPanel
        this.add(car1Label);
        this.add(car2Label);
        this.add(car3Label);
        this.add(car4Label);
        //Set visibility of only 1 car to true, as default is one racer
        car1Label.setVisible(true);
        car2Label.setVisible(false);
        car3Label.setVisible(false);
        car4Label.setVisible(false);
        
        //Header banner initialization
        URL bannerURL = getClass().getResource("images/carRacingTitle.png");
        Icon bannerImage = new ImageIcon(bannerURL);
        JLabel bannerLabel = new JLabel(bannerImage);
        bannerLabel.setBounds(110, windowHeight/10, 600, 180);
        
        //animated gif of flag, set beside the banner
        URL flagURL = getClass().getResource("images/flagLarge.gif");
        Icon flagImage = new ImageIcon(flagURL);
        JLabel wavingFlag = new JLabel(flagImage);
        wavingFlag.setBounds(690, windowHeight/12, 100, 180);
        
        //Labeling the JComboBox
        JLabel sizeText = new JLabel("Size");
        sizeText.setBounds((windowWidth/2)-230, (3*windowHeight/5), 150, 30);
        //Initialize drop down with 4 options
        this.sizeSelection = new JComboBox<>();
        sizeSelection.addItem("4");
        sizeSelection.addItem("5");
        sizeSelection.addItem("6");
        sizeSelection.addItem("7");
        //set position under text
        sizeSelection.setBounds((windowWidth/2)-200, 3*windowHeight/5, 150, 30);
        
        //Labeling the JComboBox
        JLabel racerText = new JLabel("Racers");
        racerText.setBounds((windowWidth/2), (3*windowHeight/5), 100, 30);
        //Initialize drop down with 4 options
        this.racerSelection = new JComboBox<>();
        racerSelection.addItem("1");
        racerSelection.addItem("2");
        racerSelection.addItem("3");
        racerSelection.addItem("4");
        //Every time this drop down is interacted with, visibility on car graphics is updated
        racerSelection.addActionListener((ActionEvent e)->{
            switch(racerSelection.getSelectedIndex()){
                case 0:
                        car1Label.setVisible(true);
                        car2Label.setVisible(false);
                        car3Label.setVisible(false);
                        car4Label.setVisible(false);
                        repaint();
                        break;
                case 1:
                        car1Label.setVisible(true);
                        car2Label.setVisible(true);
                        car3Label.setVisible(false);
                        car4Label.setVisible(false);
                        repaint();
                        break;
                case 2:
                        car1Label.setVisible(true);
                        car2Label.setVisible(true);
                        car3Label.setVisible(true);
                        car4Label.setVisible(false);
                        repaint();
                        break;
                case 3:
                        car1Label.setVisible(true);
                        car2Label.setVisible(true);
                        car3Label.setVisible(true);
                        car4Label.setVisible(true);
                        repaint();
                        break;
                default://by default all invisible, shouldn't occur
                        car1Label.setVisible(false);
                        car2Label.setVisible(false);
                        car3Label.setVisible(false);
                        car4Label.setVisible(false);
                        repaint();
                        break;
        }
        });
        
        //set position under text
        racerSelection.setBounds(50+(windowWidth/2), 3*windowHeight/5, 100, 30);
        
        //Allows manual positioning
        this.setLayout(null);
        
        //Adding elements to MenuGUI JPanel
        this.add(startButton);
        this.add(exitButton);
        this.add(bannerLabel);
        this.add(wavingFlag);
        this.add(sizeSelection);
        this.add(racerSelection);
        this.add(racerText);
        this.add(sizeText);
        
        
        //Making sure JPanel is visible
        this.setVisible(true);
        window.setVisible(true);
        
        window.repaint();
    }
    
    /**
     * +displayResults: void
     * This method is used to display the contents of results[]
     */
    
        public void displayResults(){
            //Start by clearing window of all elements
            window.getContentPane().removeAll();
            //null layout for manual positioning
            this.setLayout(null);
            //Array of JLabels for variable size of racers
            JLabel[] resultsLabel = new JLabel[results.length];
            for (int i = 0; i < results.length; i++) {
                //create new seperate JLabels from results array
                resultsLabel[i] = new JLabel(results[i]);
                resultsLabel[i].setBounds(300, 50 + (50 *i), 300, 100);
                this.add(resultsLabel[i]);
             }
            
            //OK button returns to menu
            JButton OKButton = new JButton("Menu");
            this.add(OKButton);
            OKButton.setBounds((windowWidth/2)-100, 3*windowHeight/4, 150, 30);
            OKButton.addActionListener((ActionEvent e)->{  
            window.getContentPane().removeAll();    
            titleScreen();
        });
            
        
        //ensure both frame and panel are visible
        this.setVisible(true);
        window.setVisible(true);
        
        window.repaint();
        }
        //return selected item in sizeSelection
    public String getSelectedSizeItem(){
        return (String) this.sizeSelection.getSelectedItem();
    }
        //return selected item in racerSelection
    public String getSelectedRacingItem(){
        return (String) this.racerSelection.getSelectedItem();
    }
        //set results array
    /**
     * +setResults: void
     * @param results holds the results of the race in a String[]
     * sets local results to hold external data
     */
    public void setResults(String[] results){
            this.results = results;
        }
        //public access so SceneSwitcher can set functionality of the button

    public JButton getStartButton(){
            return this.startButton;
    }

    @Override
    public String toString(){
        return "This class creates and displays the main menu, allowing"
                + "for game customization and initiation. It also is used"
                + "to display results after a race is finished.";
    }
}
