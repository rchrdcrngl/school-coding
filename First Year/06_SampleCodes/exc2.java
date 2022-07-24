/*
FIX STRS!
*/

import java.awt.*;
import java.awt.event.*;






public class exc2 extends Frame implements ActionListener{
    TextField tf1,tf2,tf3,tf4;
    Button b1;
    exc2(){
        tf1=new TextField("Gross Income");
        tf1.setBounds(50,50,150,20);
        tf2=new TextField("Deductions");
        tf2.setBounds(50,100,150,20);
        tf3=new TextField("Net Income");
        tf3.setBounds(50,150,150,20);
        tf3.setEditable(false);
        tf4=new TextField("Net Income");
        tf4.setBounds(50,200,150,20);
        tf4.setEditable(false);
        b1=new Button("Calculate");
        b1.setBounds(50,250,150,50);
        b1.addActionListener(this);
        add(tf1);add(tf2);add(tf3);add(tf4);add(b1);
        setSize(500,500);
        setLayout(null);
        setVisible(true);
        tf1.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            tf1.setText("");
          }
        });
        tf2.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            tf2.setText("");
          }
        });

    }
    public void actionPerformed(ActionEvent e) {
        String s1=tf1.getText();
        int a=Integer.parseInt(s1);
        String s2=tf2.getText();
        int b=Integer.parseInt(s2);
        String netIncome=String.valueOf(a - b);
        int tax=0;
        int netIncome_int=Integer.parseInt(netIncome);
        if (netIncome_int <=8000){
          String strs=String.valueOf(netIncome_int * 0.03);
          tf4.setText(strs);
        }
        else
        {
          if (netIncome_int >8000 && netIncome_int <=15000){
            String strs=String.valueOf(netIncome_int * 0.05);
            tf4.setText(strs);
          }
          else
          {
            if (netIncome_int >15000){
              String strs=String.valueOf(netIncome_int * 0.08);
              tf4.setText(strs);
            };
          };
        };
        tf3.setText(netIncome);
    }


public static void main(String[] args) {
    new exc2();


}
}
