import java.awt.*;
import java.awt.event.*;
public class EXC1 extends Frame implements ActionListener{
    TextField tf1,tf2,tf3,tf4;
    Button b1;
    EXC1(){
        tf1=new TextField("Input Edge of a Cube");
        tf1.setBounds(50,50,150,20);
        tf2=new TextField("Cube Surface Area");
        tf2.setBounds(50,100,150,20);
        tf2.setEditable(false);
        tf3=new TextField("One Side Area of the Cube");
        tf3.setBounds(50,150,150,20);
        tf3.setEditable(false);
        tf4=new TextField("Cube Volume");
        tf4.setBounds(50,200,150,20);
        tf4.setEditable(false);
        b1=new Button("Calculate");
        b1.setBounds(50,250,150,50);
        b1.addActionListener(this);
        add(tf1);add(tf2);add(tf3);add(tf4);add(b1);
        setSize(500,500);
        setLayout(null);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        String s1=tf1.getText();
        int a=Integer.parseInt(s1);
        String cSA_str=String.valueOf(6*a*a);
        String osSA_str=String.valueOf(a*a);
        String cV_str=String.valueOf(a*a*a);
        tf2.setText(cSA_str);
        tf3.setText(osSA_str);
        tf4.setText(cV_str);
    }
public static void main(String[] args) {
    new EXC1();
}
}
