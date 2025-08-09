import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textfield;

    JButton button1 = new JButton(" % ");
    JButton button2 = new JButton("AC");
    JButton button3 = new JButton("7");
    JButton button4 = new JButton("8");
    JButton button5 = new JButton("9");
    JButton button6 = new JButton(" / ");
    JButton button7 = new JButton("4");
    JButton button8 = new JButton("5");
    JButton button9 = new JButton("6");
    JButton button10 = new JButton(" * ");
    JButton button11 = new JButton("1");
    JButton button12 = new JButton("2");
    JButton button13 = new JButton("3");
    JButton button14 = new JButton(" - ");
    JButton button15 = new JButton("0");
    JButton button16 = new JButton(".");
    JButton button17 = new JButton("=");
    JButton button18 = new JButton(" + ");
    JButton[] buttons = {button1, button2, button3, 
                        button4, button5, button6, button7, button8, 
                        button9, button10, button11, button12, button13,
                        button14, button15, button16, button17, button18};

    
    Calculator(){

        // making frame
    frame = new JFrame("calc");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // EXITS IN CLOSE
    frame.setSize(500,600);
    frame.setLayout(null);
    
    textfield = new JTextField();
    textfield.setBounds(50,25,380,50);
    textfield.setEditable(false);
    frame.add(textfield);

    JPanel panel = new JPanel();
    panel.setBounds(50,100,380,400);
    panel.setLayout(new GridBagLayout());
    Color color = new Color(137,219,236);
    panel.setBackground(color);

    GridBagConstraints gridbc = new GridBagConstraints();
    gridbc.fill = GridBagConstraints.BOTH;
    gridbc.insets = new Insets(5,5,5,5);
    gridbc.weightx = 1;
    gridbc.weighty = 1;


   int[][] positions = {
    {0, 0}, {2, 0}, // % and AC
    {0, 1}, {1, 1}, {2, 1}, {3, 1}, // 7 8 9 /
    {0, 2}, {1, 2}, {2, 2}, {3, 2}, // 4 5 6 *
    {0, 3}, {1, 3}, {2, 3}, {3, 3}, // 1 2 3 -
    {0, 4}, {1, 4}, {2, 4}, {3, 4}  // 0 . = +
};

for (int i = 0; i < buttons.length; i++) {
    JButton btn = buttons[i];
    btn.addActionListener(this);
    btn.setFocusable(false);
    Color bcolor = new Color(0,144,158);
    btn.setBackground(bcolor);

    gridbc.gridx = positions[i][0];
    gridbc.gridy = positions[i][1];
    gridbc.gridwidth = 1;
    if (i == 0 | i ==1){
        gridbc.gridwidth = 2;
    }

    panel.add(btn, gridbc);
}

    frame.add(panel);

    frame.setVisible(true);
    panel.setVisible(true);
}
    public static void main(String[] args){
        Calculator calc = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String operator = e.getActionCommand();

        for(int i=0; i<buttons.length;i++){
            if(e.getSource() == buttons[i]){
                String buttonsText = buttons[i].getText();
                textfield.setText(textfield.getText().concat(buttonsText));
                if (e.getSource() == buttons[1]){
                    textfield.setText("");
                }
                if(e.getSource()==buttons[16]) {
                    String equation = textfield.getText();
                    String expression = equation.replaceAll("=","");
                    String[] parts = expression.split(" ");
                    double result = Double.parseDouble(parts[0]);
                    DecimalFormat dec = new DecimalFormat("0.########");

                    if (textfield.getText().contains("%")){
                        result *= .01;
                        textfield.setText(String.valueOf(dec.format(result)));
                        if (parts.length > 2){
                            textfield.setText("Error");
                        }
                        break;
                    }

                    textfield.setText("");

                    

                    for(int x = 1; x < parts.length - 1; x += 2){
                        String command = parts[x];
                        double number = Double.parseDouble(parts[x+1]);

                        switch (command) {
                            case "+":
                                result += number;
                                break;
                            case "-":
                                result -= number;
                                break;
                            case "*":
                                result *= number;
                                break;
                            case "/":
                                result /= number;
                                if (number == '0'){
                                    textfield.setText("Error");
                                }
                                break;
                            case "%":
                                result *= .01;
                                break;
                            case "AC":
                                textfield.setText("");
                                break;
                            default:
                                textfield.setText("Invalid Operator");
                                break;
                        }
                    }
                    textfield.setText(String.valueOf(dec.format(result)));
                }
            }
        }
    }
}