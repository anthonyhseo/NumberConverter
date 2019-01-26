/*
 * BaseCalculator.java
 * 
 * DESCRIPTION: This is the main gui program for the number converter.
 * 
 * To compile: Ensure both BaseCalculator.java and BaseConverter.java in the same directory. Then run 'javac *.java' from the command line. 
 * To run: After compiling, run 'java BaseCalculator' from the command line. 
 * 
 * AUTHOR: Anthony Seo
 */


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.*;


public class BaseCalculator {
    private JFrame frm;
    private JPanel pnl;

    private JLabel decimalLabel;
    private JTextField decimalText;
    private JLabel binaryLabel;
    private JTextField binaryText;
    private JLabel octalLabel;
    private JTextField octalText;
    private JLabel hexLabel;
    private JTextField hexText;
    private JLabel characterLabel;
    private JTextField characterText;
    private JLabel colorLabel;
    private JTextField colorText;
    private JLabel floatLabel;
    private JTextField floatText;

    private JButton chooseColorBtn;
    private JButton convertBtn;
    private JButton clearBtn;

    private boolean decimalChanged;
    private boolean binaryChanged;
    private boolean octalChanged;
    private boolean hexChanged;
    private boolean characterChanged;
    private boolean colorChanged;
    private boolean floatChanged;
    


    public BaseCalculator() {
        init();

        /*
         * DESCRIPTION: This is the choose color action listener. When clicked,
         * the gui will open the JcolorChooser and the user will select a color.
         * Once selected and closed, the program will convert to different bases. 
         */
        chooseColorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color currentColor = colorText.getBackground();
                Color newColor = JColorChooser.showDialog(frm, "Choose Color", currentColor);
                colorText.setBackground(newColor);
                resetTextChangedStatus();
                colorChanged = true;
                decimalText.setText("" + BaseConverter.colorToInt(newColor));
                binaryText.setText(BaseConverter.colorToBinary(newColor));
                octalText.setText(BaseConverter.colorToOctal(newColor));
                hexText.setText(BaseConverter.colorToHex(newColor));
                characterText.setText(BaseConverter.colorToString(newColor));
                floatText.setText("" + BaseConverter.colorToFloat(newColor));
            }
        });
        /*
         * DESCRIPTION: Allows the gui to update the textfield values with 
         * the respective base values. It converts based on the last edited
         * text field. 
         */
        convertBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (decimalChanged) {
                    int decimal = Integer.parseInt(decimalText.getText());
                    binaryText.setText(BaseConverter.intToBinary(decimal));
                    octalText.setText(BaseConverter.intToOctal(decimal));
                    hexText.setText(BaseConverter.intToHex(decimal));
                    characterText.setText(BaseConverter.intToString(decimal));
                    colorText.setBackground(BaseConverter.intToColor(decimal));
                    floatText.setText("" + BaseConverter.intToFloat(decimal));
                } else if (binaryChanged) {
                    String binary = binaryText.getText();
                    decimalText.setText("" + BaseConverter.binaryToInt(binary));
                    octalText.setText(BaseConverter.binaryToOctal(binary));
                    hexText.setText(BaseConverter.binaryToHex(binary));
                    characterText.setText(BaseConverter.binaryToString(binary));
                    colorText.setBackground(BaseConverter.binaryToColor(binary));
                    floatText.setText("" + BaseConverter.binaryToFloat(binary));
                } else if (octalChanged) {
                    String octal = octalText.getText();
                    decimalText.setText("" + BaseConverter.octalToInt(octal));
                    binaryText.setText(BaseConverter.octalToBinary(octal));
                    hexText.setText(BaseConverter.octalToHex(octal));
                    characterText.setText(BaseConverter.octalToString(octal));
                    colorText.setBackground(BaseConverter.octalToColor(octal));
                    floatText.setText("" + BaseConverter.octalToFloat(octal));
                } else if (hexChanged) {
                    String hex = hexText.getText();
                    decimalText.setText("" + BaseConverter.hexToInt(hex));
                    binaryText.setText(BaseConverter.hexToBinary(hex));
                    octalText.setText(BaseConverter.hexToOctal(hex));
                    characterText.setText(BaseConverter.hexToString(hex));
                    colorText.setBackground(BaseConverter.hexToColor(hex));
                    floatText.setText("" + BaseConverter.hexToFloat(hex));
                } else if (characterChanged) {
                    String character = characterText.getText();
                    decimalText.setText("" + BaseConverter.stringToInt(character));
                    binaryText.setText(BaseConverter.stringToBinary(character));
                    octalText.setText(BaseConverter.stringToOctal(character));
                    hexText.setText(BaseConverter.stringToHex(character));
                    colorText.setBackground(BaseConverter.stringToColor(character));
                    floatText.setText("" + BaseConverter.stringToFloat(character));
                }  else if (floatChanged) {
                    Float floatVal = Float.parseFloat(floatText.getText());
                    decimalText.setText("" + BaseConverter.floatToInt(floatVal));
                    binaryText.setText(BaseConverter.floatToBinary(floatVal));
                    octalText.setText(BaseConverter.floatToOctal(floatVal));
                    hexText.setText(BaseConverter.floatToHex(floatVal));
                    characterText.setText("" + BaseConverter.floatToString(floatVal));
                    colorText.setBackground(BaseConverter.floatToColor(floatVal)); 
                }
            }
        });

        /*
         * DESCRIPTION: Enables clear function. When the clear button is pressed,
         * all the text fields are reset. 
         */
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decimalText.setText("");
                binaryText.setText("");
                octalText.setText("");
                hexText.setText("");
                characterText.setText("");
                colorText.setText("");
                colorText.setBackground(new Color(0xFF, 0xFF, 0xFF));
                floatText.setText("");
                resetTextChangedStatus();
            }
        });

        /*
         * DESCRIPTION: The next 6 listeners will detect if the text
         * within each respective textbox have been edited. The flags are 
         * set for the convert button to use. 
         */

        decimalText.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                resetTextChangedStatus();
                decimalChanged = true;
            }
        });

        binaryText.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                resetTextChangedStatus();
                binaryChanged = true;
            }
        });

        octalText.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                resetTextChangedStatus();
                octalChanged = true;
            }
        });

        hexText.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                resetTextChangedStatus();
                hexChanged = true;
            }
        });

        characterText.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                resetTextChangedStatus();
                characterChanged = true;
            }
        });

        floatText.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                resetTextChangedStatus();
                floatChanged = true;
            }
        });
 
    }

    /*
     * DESCRIPTION: This initializes the gui variables. 
     */
    private void init() {
        frm = new JFrame("Number Converter");
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pnl = new JPanel();
        pnl.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        decimalLabel = new JLabel("Decimal");
        pnl.add(decimalLabel, constraints);

        constraints.gridx = 1;
        decimalText = new JTextField(32);
        pnl.add(decimalText, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        binaryLabel = new JLabel("Binary");
        pnl.add(binaryLabel, constraints);
        
        constraints.gridx = 1;
        binaryText = new JTextField(32);
        pnl.add(binaryText, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        octalLabel = new JLabel("Octal");
        pnl.add(octalLabel, constraints);

        constraints.gridx = 1;
        octalText = new JTextField(32);
        pnl.add(octalText, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        hexLabel = new JLabel("Hexadecimal");
        pnl.add(hexLabel, constraints);

        constraints.gridx = 1;
        hexText = new JTextField(32);
        pnl.add(hexText, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        characterLabel = new JLabel("Character(s)");
        pnl.add(characterLabel, constraints);

        constraints.gridx = 1;
        characterText = new JTextField(32);
        pnl.add(characterText, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        colorLabel = new JLabel("Color");
        pnl.add(colorLabel, constraints);

        constraints.gridx = 1;
        colorText = new JTextField(32);
        pnl.add(colorText, constraints);

        constraints.gridx = 2;
        chooseColorBtn = new JButton("Choose Color");
        pnl.add(chooseColorBtn, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        floatLabel = new JLabel("Float");
        pnl.add(floatLabel, constraints);

        constraints.gridx = 1;
        floatText = new JTextField(32);
        pnl.add(floatText, constraints);

        constraints.gridx = 1;
        constraints.gridy = 7;
        convertBtn = new JButton("Convert");
        pnl.add(convertBtn, constraints);

        constraints.gridx = 2;
        clearBtn = new JButton("Clear");
        pnl.add(clearBtn, constraints);

        frm.add(pnl);
        frm.setPreferredSize(new Dimension(700, 500));

        frm.setVisible(true);
        frm.pack();
        resetTextChangedStatus();
    }

    /*
     * DESCRIPTION: This resets all the textfield changed flags.
     */
    public void resetTextChangedStatus() {
        decimalChanged = false;
        binaryChanged = false;
        octalChanged = false;
        hexChanged = false;
        characterChanged = false;
        colorChanged = false;
        floatChanged = false;
    }

    public static void main(String[] args) {
        BaseCalculator app = new BaseCalculator();
    }
}