package producer_and_customer;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.AncestorListener;
public class ProducerAndConsumer {	
	static int i=0;	
	public static void main(String[] args) throws Exception {									
		JFrame jf = new JFrame("生产者与消费者");		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置窗口大小
		jf.setSize(800,400);
		//设置窗口居中
		jf.setLocationRelativeTo(null);
		Container con=jf.getContentPane();
		
		JPanel pan=new JPanel();
		pan.setLayout(null);
		jf.add(pan);
		
		JLabel title=new JLabel("生产者与消费者案例演示");
		title.setBounds(250,10,300,50);
		title.setFont(new Font("宋体", Font.BOLD, 24));
		pan.add(title);
		
		
		JLabel hc=new JLabel("缓冲队列：");
		hc.setBounds(90,90,110,20);
		pan.add(hc);
		JTextField jt[]=new JTextField[6];
		for (int i = 0; i < jt.length; i++) {
			jt[i]=new JTextField(6);
			pan.add(jt[i]);
		}
		jt[0].setBounds(160,90,60,20);
		jt[1].setBounds(230,90,60,20); 
		jt[2].setBounds(300,90,60,20);
		jt[3].setBounds(370,90,60,20);
		jt[4].setBounds(440,90,60,20);
		jt[5].setBounds(510,90,60,20);
		
		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		
		JLabel jl1=new JLabel("生产阻塞队列：");
		jl1.setBounds(70,140,110,20);
		pan.add(jl1);
		JTextField jt_zuse1[]=new JTextField[8];
		for (int i =6; i < jt_zuse1.length; i++) {			
			jt_zuse1[i]=new JTextField(4);			
			pan.add(jt_zuse1[i]);
		}
		
		jt_zuse1[6].setBounds(160,140,60,20);
		jt_zuse1[7].setBounds(230,140,60,20);
		
		JLabel jl2=new JLabel("消费阻塞队列：");
		jl2.setBounds(370,140,110,20);
		pan.add(jl2);
		JTextField jt_zuse2[]=new JTextField[2];
		for (int i = 0; i < jt_zuse2.length; i++) {			
			jt_zuse2[i]=new JTextField(4);            //输入框4个长度			
			pan.add(jt_zuse2[i]);			
		}
		jt_zuse2[0].setBounds(460,140,60,20);
		jt_zuse2[1].setBounds(530,140,60,20);

		
		JLabel jl3=new JLabel("产品名称：");
		jl3.setBounds(90,190,70,20);
		pan.add(jl3);
		JTextField nameField = new JTextField(4);    //4个可视范围，产品输入框
		nameField.setBounds(160,190,130,20);
		pan.add(nameField);	
		
		JButton jb1=new JButton("生产者1");
		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		jb1.setBorder(BorderFactory.createRaisedBevelBorder()); 
		jb1.setContentAreaFilled(false); 
		jb1.setBounds(110, 250,80 , 30);
		pan.add(jb1);
		
		JButton jb2=new JButton("生产者2");
		jb2.setBorder(BorderFactory.createRaisedBevelBorder()); 
		jb2.setContentAreaFilled(false);
		jb2.setBounds(250, 250,80, 30);
		//jb2.setBorder(BorderFactory.createLoweredBevelBorder());
		pan.add(jb2);
		
		JButton jb3=new JButton("消费者1");
		jb3.setBorder(BorderFactory.createRaisedBevelBorder()); 
		jb3.setContentAreaFilled(false);
		jb3.setBounds(390,250,80 ,30);
		//jb3.setBorder(BorderFactory.createLoweredBevelBorder());
		pan.add(jb3);
		
		JButton jb4=new JButton("消费者2");
		jb4.setBorder(BorderFactory.createRaisedBevelBorder()); 
		jb4.setContentAreaFilled(false);
		jb4.setBounds(530,250,80, 30);
		//jb4.setBorder(BorderFactory.createLoweredBevelBorder());
		pan.add(jb4);
		
		JLabel author=new JLabel("By:White");
		author.setBounds(50,310,110,20);
		pan.add(author);
		
		/**
		 * 监听jb1按钮			
		 */
		jb1.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				if(i<6&&i>=0) {
					//获取产品名称
					String name = nameField.getText();
					//缓冲队列++
					jt[i].setText(name);
					i++;								
				}
				else if(i<8&&i>=6){
					jt_zuse1[i].setText("生产者1");
					jb1.setEnabled(false);
					i++;
				}
				else if(i==9){
					jb1.setEnabled(false);
					jb2.setEnabled(false);
				}else if(i==-1) {
					if(jt_zuse2[-i-1].getText().equals("消费者1")) {
						jb3.setEnabled(true);
					}else if(jt_zuse2[-i-1].getText().equals("消费者2")) {
						jb4.setEnabled(true);
					}								
					jt_zuse2[-i-1].setText(null);
					i++;	
				}
				
				//当消费者阻塞时，这时候点击生产者的变化
				else if(i==-2) {															
					if(jt_zuse2[0].getText().equals("消费者1")) {
						//这时候jb3就可使
						jb3.setEnabled(true);           
						jt_zuse2[0].setText("消费者2");
					}else if(jt_zuse2[0].getText().equals("消费者2")) {
						jb4.setEnabled(true);
						jt_zuse2[0].setText("消费者1");
					}
					jt_zuse2[1].setText(null);
					i++;	
//					if(jt_zuse2[1].getText().equals("消费者1")) {
//						jt_zuse2[0].setText("消费者1");
//					}else if(jt_zuse2[1].getText().equals("消费者2")) {
//						jt_zuse2[0].setText("消费者2");
//					}
				}
			}																	
		});
		
		/**
		 * 监听jb2按钮		
		 * i是作为标志位	
		 */
		
		jb2.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				if(i<6&&i>=0) {								
					String name = nameField.getText();						
					jt[i].setText(name);
					i++;								
				}
				else if(i<8&&i>=6){
					jt_zuse1[i].setText("生产者2");
					jb2.setEnabled(false);
					i++;
				} 
				else if(i==9){
					jb2.setEnabled(false);
					jb1.setEnabled(false);
				}else if(i==-1) {																
					if(jt_zuse2[-i-1].getText().equals("消费者1")) {
						jb3.setEnabled(true);
					}else if(jt_zuse2[-i-1].getText().equals("消费者2")) {
						jb4.setEnabled(true);
					}								
					jt_zuse2[-i-1].setText(null);
					i++;	
				}
				else if(i==-2) {															
					if(jt_zuse2[0].getText().equals("消费者1")) {
						jb3.setEnabled(true);
					}else if(jt_zuse2[0].getText().equals("消费者2")) {
						jb4.setEnabled(true);
					}
					if(jt_zuse2[1].getText().equals("消费者1")) {
						jt_zuse2[0].setText("消费者1");
					}else if(jt_zuse2[1].getText().equals("消费者2")) {
						jt_zuse2[0].setText("消费者2");
					}
					jt_zuse2[1].setText(null);
					i++;	
				}

			}																	
		});	
		
		/**
		 * 监听jb3按钮			
		 */
		
		jb3.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				if(i<=6&&i>=1) {													
					jt[i-1].setText(null);	
					i--;
					jb2.setEnabled(true);
					jb1.setEnabled(true);					
				} 
				else if(i==7){    //当点击jb3按钮时，这时候标志位i==7说明这时候生产阻塞队列中有一个，先判断是谁然后再清空，恢复生产，然后置空
					if(jt_zuse1[i-1].getText().equals("生产者1")) {
						jb1.setEnabled(true); 
					}else if(jt_zuse1[i-1].getText().equals("生产者2")) {
						jb2.setEnabled(true);
					}
					jt_zuse1[i-1].setText(null);	
					i--;
				}
				else if(i==8){
					if(jt_zuse1[6].getText().equals("生产者1")) {
						jb1.setEnabled(true);
					}else if(jt_zuse1[6].getText().equals("生产者2")) {
						jb2.setEnabled(true);
					}
					if(jt_zuse1[7].getText().equals("生产者1")) {
						jt_zuse1[6].setText("生产者1");
					}else if(jt_zuse1[7].getText().equals("生产者2")) {
						jt_zuse1[6].setText("生产者2");
					}
					i--;
					jt_zuse1[7].setText(null);
				}
				else if(i<=0&&i>=-1){
					jt_zuse2[-i].setText("消费者1");
					i--;
					jb3.setEnabled(false);								
				}						
			}																	
		});
		
		/**
		 * 监听jb4按钮			
		 */
		
		jb4.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				if(i<=6&&i>=1) {													
					jt[i-1].setText(null);	
					i--;
					jb2.setEnabled(true);
					jb1.setEnabled(true);					
				} 
				else if(i==7){
					if(jt_zuse1[i-1].getText().equals("生产者1")) {
						jb1.setEnabled(true);
					}else if(jt_zuse1[i-1].getText().equals("生产者2")) {
						jb2.setEnabled(true);
					}
					jt_zuse1[i-1].setText(null);	
					i--;
				}
				else if(i==8){
					if(jt_zuse1[6].getText().equals("生产者1")) {
						jb1.setEnabled(true);
					}else if(jt_zuse1[6].getText().equals("生产者2")) {
						jb2.setEnabled(true);
					}
					if(jt_zuse1[7].getText().equals("生产者1")) {
						jt_zuse1[6].setText("生产者1");
					}else if(jt_zuse1[7].getText().equals("生产者2")) {
						jt_zuse1[6].setText("生产者2");
					}
					i--;
					jt_zuse1[7].setText(null);
				}

				else if(i<=0&&i>=-1){
					jt_zuse2[-i].setText("消费者2");
					i--;
					jb4.setEnabled(false);								
				}												
			}																	
		});	
		
		con.add(pan);
		jf.setVisible(true);//设置窗口可见性			
	}		
}
