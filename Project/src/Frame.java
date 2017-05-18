import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

@SuppressWarnings("serial")
public class Frame extends javax.swing.JFrame {  //myframe�� jframe�� ����
 private JLabel label =new JLabel();
 private JLabel label2 =new JLabel();
 private JTextField tf = new JTextField(5); //���� ¥�� �Է�â
 private MyThread th;
 private MyThread th2;
 
 public Frame(){
  setTitle("���� ������");
  Container c = getContentPane(); //JLabel�� ȭ�鿡 ��Ÿ������
  c.setLayout(new FlowLayout());
  
  c.add(tf);
  tf.setFont(new Font("Gothic", Font.ITALIC,60));
  tf.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e){
    JTextField t = (JTextField)e.getSource();
    String s =t.getText();
    int n=0;
    try {
     n=Integer.parseInt(s);
    }
    catch(NumberFormatException ex){
     t.setText("���ڸ� �Ĥ���������������!");
     return;
    }
    th.setNumber(n);
    
   }
   
  });
  
  label.setFont(new Font("Gothic", Font.ITALIC,60));
  label.setHorizontalAlignment(JLabel.CENTER);
  label.setBackground(Color.YELLOW);
  label.setOpaque(true);
  c.add(label); //contentpane�� ���̺��� ����
  
  label2.setFont(new Font("Gothic", Font.ITALIC,60));
  label2.setHorizontalAlignment(JLabel.CENTER);
  label2.setBackground(Color.GREEN);
  label2.setOpaque(true);
  c.add(label2);
  
  setSize(300,300);
  setVisible(true); //�̰� ����� ������ �ʴ´�
  
  c.addMouseListener(new MouseAdapter(){
   public void mousePressed(MouseEvent e){
    th.interrupt();
   }
  });
  
     th = new MyThread(label,100,th2); //label�� �Ѱ��ش� 100 ->���� �ϳ��� 0.1��
     th.start();
  th2 = new MyThread(label2,200,null); 
     th2.start();
     
     Thread th3= new Thread(new ColorThread(label));
     th3.start();
     
 }
 class ColorThread implements Runnable{
  private JLabel label;
  public ColorThread(JLabel label){
   this.label =label;
  }
  public void run(){
   while(true){
    int r=(int)(Math.random()*255);
    int g=(int)(Math.random()*255);
    int b=(int)(Math.random()*255);
    Color c=new Color(r,g,b);
    label.setForeground(c);
    try {
     Thread.sleep(100);
    } catch (InterruptedException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
    }
    
   }
   
  }
 }
 class MyThread extends Thread{
  private JLabel label; //label ������ ���
  private long delay;
  private Thread next;
  private int n;
  
  public MyThread(JLabel label, long delay, Thread next){ //�ӵ� ������
   this.delay=delay;
   this.label=label;
   this.next=next;
   setNumber(0);
  }
  public void setNumber(int n){
   this.n=n;
  }
  public void run() {
   //���� ����� ���� �۾��� �����
   
   while(true){ //run�� ������ �ʵ��� ���ѷ����� ���� �Ѵ�
      label.setText(Integer.toString(n));
      if(n==100)  //���ڰ� 100�� �����ϸ� ����
       break;
      try {
    sleep(delay);
    n++;
   } catch (InterruptedException e) {  
    label.setText(label.getText()+"�� Kiiled");
    if(next !=null){
     next.interrupt();
    }
    break;
    //e.printStackTrace();
   }
   }
  }
 }
public static void main(String[] args){
 //���� ����ǰ� �ִ� 
    Thread m = Thread.currentThread(); //���� Ÿ���� ������ / ���� ���� �����尡 �������ΰ�?
    int n = Thread.activeCount();
    System.out.println(n); //ctrl+shift+b :�극��ũ ����
    System.out.println(m.getName());
    System.out.println(m.getId());
    System.out.println(m.getPriority());
    System.out.println(m.getState());
    
 Frame f= new Frame();
 n = Thread.activeCount();
 
}
}