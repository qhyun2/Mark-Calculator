import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import org.eclipse.wb.swing.FocusTraversalOnArray;

public class MarkCalculator{

	private JFrame frmMarkCalculator;
	private JTextField term;
	private JTextField exam;
	private JTextField course;
	private JSpinner spinner;
	private JLabel label;
	private int value = 30;
	private String defLabel = "Enter any 2 below";

	protected void calculate(){

		label.setText(defLabel);

		String termText = term.getText();
		String examText = exam.getText();
		String courseText = course.getText();
		value = (int)spinner.getValue();

		double termNum = 0, examNum = 0, courseNum = 0;
		int emptyCount = 0;
		int type = 0;

		if(term.getText().isEmpty()){
			emptyCount++;	
			type = 1;
		}
		else{
			try{
				termNum = Double.parseDouble(termText);
			}catch (Exception e){
				invalidInput(0);
				emptyCount++;
			}
		}


		if(exam.getText().isEmpty()){
			emptyCount++;
			type = 2;
		}
		else{
			try{
				examNum = Double.parseDouble(examText);
			}catch (Exception e){
				invalidInput(1);
				emptyCount++;
			}
		}


		if(course.getText().isEmpty()){
			emptyCount++;
			type = 3;
		}
		else{
			try{
				courseNum = Double.parseDouble(courseText);
			}catch (Exception e){
				invalidInput(2);
				emptyCount++;
			}
		}

		if(emptyCount != 1){
			label.setText("Invalid Number of Inputs");
		}
		else{
			double examValue = value / 100.0;
			
			//equations come from rearranged: term * (1 - exam weight) + exam * exam weight = final


			switch (type) {
				case 1:
					double termCal = (courseNum - (examNum * examValue)) / (1.0 - examValue);
					String outputTerm = String.format("Your course mark is: %.2f", termCal);
					label.setText(outputTerm);
					break;
				case 2:
					double examCal = (termNum * (1.0 - examValue) - courseNum) / (-examValue);
					String outputExam = String.format("Your exam mark is: %.2f", examCal);
					label.setText(outputExam);
					break;
				case 3:
					double courseCal = termNum * (1.0 - examValue) + examNum * examValue;
					String outputCourse = String.format("Your final mark is: %.2f", courseCal);
					label.setText(outputCourse);
					break;

				default:
					break;
			}
		}
	}

	public void invalidInput(int location){

		switch (location) {
			case 0:
				term.setText("Invalid Input");
				break;
			case 1:
				exam.setText("Invalid Input");
				break;
			case 2:
				course.setText("Invalid Input");
				break;
			default:
				break;
		}
	}

	public static void main(String[] args){
		
		try {
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (Exception e) {
	    }


		EventQueue.invokeLater(new Runnable(){

			public void run(){

				try{
					MarkCalculator window = new MarkCalculator();
					window.frmMarkCalculator.setVisible(true);
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
	}

	public MarkCalculator(){

		initialize();
	}

	private void initialize(){

		frmMarkCalculator = new JFrame();
		frmMarkCalculator.setType(Type.UTILITY);
		frmMarkCalculator.setTitle("Mark Calculator");
		frmMarkCalculator.setBounds(100, 100, 480, 284);
		frmMarkCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMarkCalculator.setResizable(false);
		frmMarkCalculator.getContentPane().setLayout(new BoxLayout(frmMarkCalculator.getContentPane(), BoxLayout.Y_AXIS));

		JPanel examValue = new JPanel();
		examValue.setBorder(new EmptyBorder(20, 0, 20, 0));
		frmMarkCalculator.getContentPane().add(examValue);
		examValue.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel valueDisplay = new JLabel("Exam Value:");
		valueDisplay.setFont(new Font("Arial", Font.PLAIN, 13));
		valueDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		examValue.add(valueDisplay);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(30, 0, 100, 1));
		examValue.add(spinner);
		
		JLabel percent = new JLabel("%");
		percent.setHorizontalAlignment(SwingConstants.CENTER);
		percent.setFont(new Font("Arial", Font.PLAIN, 13));
		examValue.add(percent);
		
		JLabel label_1 = new JLabel("");
		frmMarkCalculator.getContentPane().add(label_1);

		JPanel input = new JPanel();
		input.setBorder(new EmptyBorder(10, 5, 10, 5));
		frmMarkCalculator.getContentPane().add(input);
		input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));

		label = new JLabel(defLabel);
		label.setFont(new Font("Arial", Font.PLAIN, 13));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setBorder(new EmptyBorder(0, 0, 20, 0));
		input.add(label);
		label.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel markBox = new JPanel();
		markBox.setBorder(new EmptyBorder(0, 20, 0, 20));
		input.add(markBox);
		markBox.setLayout(new GridLayout(0, 3, 20, 15));

		term = new JTextField();
		term.setFont(new Font("Arial", Font.PLAIN, 13));
		term.setHorizontalAlignment(SwingConstants.CENTER);
		markBox.add(term);
		term.setColumns(10);

		exam = new JTextField();
		exam.setFont(new Font("Arial", Font.PLAIN, 13));
		exam.setHorizontalAlignment(SwingConstants.CENTER);
		markBox.add(exam);
		exam.setColumns(10);

		course = new JTextField();
		course.setFont(new Font("Arial", Font.PLAIN, 13));
		course.setHorizontalAlignment(SwingConstants.CENTER);
		markBox.add(course);
		course.setColumns(10);

		JLabel termLabel = new JLabel("Term Mark");
		termLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		termLabel.setHorizontalAlignment(SwingConstants.CENTER);
		markBox.add(termLabel);

		JLabel examLabel = new JLabel("Exam Mark");
		examLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		examLabel.setHorizontalAlignment(SwingConstants.CENTER);
		markBox.add(examLabel);

		JLabel finalLabel = new JLabel("Final Mark");
		finalLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		finalLabel.setHorizontalAlignment(SwingConstants.CENTER);
		markBox.add(finalLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(20, 0, 0, 0));
		input.add(panel);

		JButton calculate = new JButton("Calculate");
		calculate.setFont(new Font("Arial", Font.PLAIN, 13));
		panel.add(calculate);

		JButton reset = new JButton("   Clear   ");
		reset.setFont(new Font("Arial", Font.PLAIN, 13));
		reset.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0){

				term.setText("");
				exam.setText("");
				course.setText("");
				label.setText(defLabel);
			}
		});
		panel.add(reset);
		frmMarkCalculator.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{spinner, term, exam, course, calculate, reset}));
		calculate.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e){

				calculate();
			}
		});
	}
}
