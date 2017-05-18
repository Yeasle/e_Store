import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

@SuppressWarnings("serial")
public class Frame extends javax.swing.JFrame {  //myframe은 jframe과 같다
 private JLabel label =new JLabel();
 private JLabel label2 =new JLabel();
 private JTextField tf = new JTextField(5); //한줄 짜리 입력창
 private MyThread th;
 private MyThread th2;
 
 public Frame(){
  setTitle("나의 프레임");
  Container c = getContentPane(); //JLabel이 화면에 나타나도록
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
     t.setText("숫자만 쳐ㅋㅋㅋㅋㅋㅋㅋㅋ!");
     return;
    }
    th.setNumber(n);
    
   }
   
  });
  
  label.setFont(new Font("Gothic", Font.ITALIC,60));
  label.setHorizontalAlignment(JLabel.CENTER);
  label.setBackground(Color.YELLOW);
  label.setOpaque(true);
  c.add(label); //contentpane에 레이블을 붙임
  
  label2.setFont(new Font("Gothic", Font.ITALIC,60));
  label2.setHorizontalAlignment(JLabel.CENTER);
  label2.setBackground(Color.GREEN);
  label2.setOpaque(true);
  c.add(label2);
  
  setSize(300,300);
  setVisible(true); //이걸 지우면 보이지 않는다
  
  c.addMouseListener(new MouseAdapter(){
   public void mousePressed(MouseEvent e){
    th.interrupt();
   }
  });
  
     th = new MyThread(label,100,th2); //label을 넘겨준다 100 ->숫자 하나가 0.1초
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
  private JLabel label; //label 내용을 기억
  private long delay;
  private Thread next;
  private int n;
  
  public MyThread(JLabel label, long delay, Thread next){ //속도 빠르게
   this.delay=delay;
   this.label=label;
   this.next=next;
   setNumber(0);
  }
  public void setNumber(int n){
   this.n=n;
  }
  public void run() {
   //내가 만들고 싶은 작업을 만든답
   
   while(true){ //run이 끝나지 않도록 무한루프를 돌게 한다
      label.setText(Integer.toString(n));
      if(n==100)  //숫자가 100에 도달하면 멈춤
       break;
      try {
    sleep(delay);
    n++;
   } catch (InterruptedException e) {  
    label.setText(label.getText()+"후 Kiiled");
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
 //현재 실행되고 있는 
    Thread m = Thread.currentThread(); //리턴 타입이 스레드 / 지금 무슨 스레드가 진행중인가?
    int n = Thread.activeCount();
    System.out.println(n); //ctrl+shift+b :브레이크 설정
    System.out.println(m.getName());
    System.out.println(m.getId());
    System.out.println(m.getPriority());
    System.out.println(m.getState());
    
 Frame f= new Frame();
 n = Thread.activeCount();
 
}
}