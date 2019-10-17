import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * The View was set up using WindowBuilder,
 * and contains all logic regarding the creation
 * and maintenance of the frame and its nested PuzzlePanel
 * @author zacharyaamold
 *
 */
public class View extends JFrame {

	// Declaring global variables
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private Controller myController;
	
	/**
	 * Constructor creates the frame, initializes
	 * the PuzzlePanel that displays the game, and
	 * creates and sets up all buttons
	 * @param c The Controller
	 * @param width The width of the PuzzlePanel
	 * @param height The height of the PuzzlePanel
	 */
	public View(Controller c, int width, int height) {
		myController = c;
		
		// Frame Setup
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 375);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0};
		gbl_mainPanel.rowHeights = new int[]{300, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		// PuzzlePanel creation and setup
		PuzzlePanel pPanel = new PuzzlePanel(myController, width, height);
		GridBagConstraints gbc_pPanel = new GridBagConstraints();
		gbc_pPanel.insets = new Insets(0, 0, 5, 0);
		gbc_pPanel.fill = GridBagConstraints.BOTH;
		gbc_pPanel.gridx = 0;
		gbc_pPanel.gridy = 0;
		mainPanel.add(pPanel, gbc_pPanel);
		
		// Button Panel setup
		JPanel buttonPanel = new JPanel();
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridx = 0;
		gbc_buttonPanel.gridy = 1;
		mainPanel.add(buttonPanel, gbc_buttonPanel);
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_buttonPanel.rowHeights = new int[]{0, 0};
		gbl_buttonPanel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_buttonPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		buttonPanel.setLayout(gbl_buttonPanel);
		
		// Randomize Button randomizes the tile locations
		JButton btnRandomize = new JButton("Randomize");
		btnRandomize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myController.randomize();
				mainPanel.repaint();
			}
		});
		GridBagConstraints gbc_btnRandomize = new GridBagConstraints();
		gbc_btnRandomize.fill = GridBagConstraints.BOTH;
		gbc_btnRandomize.insets = new Insets(0, 0, 0, 5);
		gbc_btnRandomize.gridx = 0;
		gbc_btnRandomize.gridy = 0;
		buttonPanel.add(btnRandomize, gbc_btnRandomize);
		
		// Exit button closes the program
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		
		// Reset button moves all Pieces back to default positions
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myController.reset();
			}
		});
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.fill = GridBagConstraints.BOTH;
		gbc_btnReset.insets = new Insets(0, 0, 0, 5);
		gbc_btnReset.gridx = 1;
		gbc_btnReset.gridy = 0;
		buttonPanel.add(btnReset, gbc_btnReset);
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.fill = GridBagConstraints.BOTH;
		gbc_btnExit.gridx = 2;
		gbc_btnExit.gridy = 0;
		buttonPanel.add(btnExit, gbc_btnExit);
		
		// Final frame setup
		this.pack();
		this.setVisible(true);
	}

}
