import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

@SuppressWarnings("serial")
public class Frame extends javax.swing.JFrame {  //myframe精 jframe引 旭陥
 private JLabel label =new JLabel();
 private JLabel label2 =new JLabel();
 private JTextField tf = new JTextField(5); //廃匝 促軒 脊径但
 private MyThread th;
 private MyThread th2;
 
 public Frame(){
  setTitle("蟹税 覗傾績");
  Container c = getContentPane(); //JLabel戚 鉢檎拭 蟹展蟹亀系
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
     t.setText("収切幻 団せせせせせせせせ!");
     return;
    }
    th.setNumber(n);
    
   }
   
  });
  
  label.setFont(new Font("Gothic", Font.ITALIC,60));
  label.setHorizontalAlignment(JLabel.CENTER);
  label.setBackground(Color.YELLOW);
  label.setOpaque(true);
  c.add(label); //contentpane拭 傾戚鷺聖 細績
  
  label2.setFont(new Font("Gothic", Font.ITALIC,60));
  label2.setHorizontalAlignment(JLabel.CENTER);
  label2.setBackground(Color.GREEN);
  label2.setOpaque(true);
  c.add(label2);
  
  setSize(300,300);
  setVisible(true); //戚杏 走酔檎 左戚走 省澗陥
  
  c.addMouseListener(new MouseAdapter(){
   public void mousePressed(MouseEvent e){
    th.interrupt();
   }
  });
  
     th = new MyThread(label,100,th2); //label聖 角移層陥 100 ->収切 馬蟹亜 0.1段
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
  private JLabel label; //label 鎧遂聖 奄常
  private long delay;
  private Thread next;
  private int n;
  
  public MyThread(JLabel label, long delay, Thread next){ //紗亀 匙牽惟
   this.delay=delay;
   this.label=label;
   this.next=next;
   setNumber(0);
  }
  public void setNumber(int n){
   this.n=n;
  }
  public void run() {
   //鎧亜 幻級壱 粛精 拙穣聖 幻窮岩
   
   while(true){ //run戚 魁蟹走 省亀系 巷廃欠覗研 宜惟 廃陥
      label.setText(Integer.toString(n));
      if(n==100)  //収切亜 100拭 亀含馬檎 誇茶
       break;
      try {
    sleep(delay);
    n++;
   } catch (InterruptedException e) {  
    label.setText(label.getText()+"板 Kiiled");
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
 //薄仙 叔楳鞠壱 赤澗 
    Thread m = Thread.currentThread(); //軒渡 展脊戚 什傾球 / 走榎 巷充 什傾球亜 遭楳掻昔亜?
    int n = Thread.activeCount();
    System.out.println(n); //ctrl+shift+b :崎傾戚滴 竺舛
    System.out.println(m.getName());
    System.out.println(m.getId());
    System.out.println(m.getPriority());
    System.out.println(m.getState());
    
 Frame f= new Frame();
 n = Thread.activeCount();
 
}
}