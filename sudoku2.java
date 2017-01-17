import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import java.lang.reflect.InvocationTargetException;

/*
<object code= "sudoku2" width=20 height=20>
</object>
*/

public class sudoku2 extends JApplet implements ActionListener{

JTextArea f[][][][]= new JTextArea[3][3][3][3];
int arow[][][][]= new int[3][3][3][3];
JFrame f1= new JFrame("SUDOKU");
JPanel p[][]= new JPanel[3][3];
JPanel pan= new JPanel(new GridLayout(3,3));	
JMenuBar b= new JMenuBar();
JMenu m1= new JMenu("Options");
JMenuItem i1= new JMenuItem("New Game");
JMenuItem i2= new JMenuItem("Finish");
JMenuItem i3= new JMenuItem("Get Solution");
JLabel lab=new JLabel("");
int flag=0;

public static void main (String[] args){
	sudoku2 sudoku = new sudoku2();
	try{
	sudoku.init();
	} catch(Exception ex){
		System.out.println(ex.getCause());
	}
}

public void init(){
			try{
				SwingUtilities.invokeAndWait(new Runnable(){
										public void run()
										{
											makeGui();	
										}
								});
				}
			catch(Exception e)
			{
				System.out.println("Exception thrown "+e+ e.getCause());
			}
}

private void makeGui()
{
	f1.setVisible(true);
	f1.setSize(450,450);
	Border b1= BorderFactory.createLineBorder(Color.black);
	f1.setLayout(new BorderLayout());	
	f1.add(BorderLayout.NORTH,b);
	m1.add(i1);
	m1.add(i2);
	m1.add(i3);
	b.add(m1);
	i1.addActionListener(this);
	i2.addActionListener(this);
	i3.addActionListener(this);
	int i,j,k,l,r;
	for(i=0;i>3;i++)
		for(j=0;j<3;j++)
			{p[i][j]=new JPanel();
				p[i][j].setLayout(new GridLayout(3,3));
				pan.add(p[i][j]);
			}
	f1.add(BorderLayout.CENTER, pan);
	for(j=0;j<3;j++)
		for(k=0;k<3;k++)
			{
				for(i=0;i<3;i++)
				for(l=0;l<3;l++)
					{
						f[i][j][k][l]= new JTextArea(50,50);
						arow[i][j][k][l]=0;
						f[i][j][k][l].setBorder(b1);
						p[i][j].add(f[i][j][k][l]);
						if((i==0||i==2)&& j==1)
							{f[i][j][k][l].setBackground(Color.yellow);
							}
						else if(i==1 && (j==0||j==2))
							{f[i][j][k][l].setBackground(Color.yellow);
							}
						else f[i][j][k][l].setBackground(Color.lightGray);
						f[i][j][k][l].setForeground(Color.red);
						f[i][j][k][l].setFont(new Font("Serif",Font.ITALIC,20));
					}			
				}				
/*for(j=0;j<3;j++)
	{	for(k=0;k<3;k++)
			{for(i=0;i<3;i++)
				for(l=0;l<3;l++)
					System.out.print(arow[i][j][k][l]+" "+i+""+j+""+k+""+l+" ");
		
		System.out.println();
	}}
				//make();
				//set();
			System.out.println("Board set");*/
}

private void make()
{
	System.out.println("In make");
	int i=0,j=0,k,l,r;
	Random ran = new Random();
		for(i=0;i<3;i++)
			for(j=0;j<3;j++)
				for(k=0;k<3;k++)
					for(l=0;l<3;)
						{
							r=ran.nextInt(9);
							System.out.println("r is: "+r);
							boolean bool=check(i,j,k,l,++r);
							if(bool)
							{	arow[i][j][k][l]=r; l++;
								System.out.println("l is "+l);
							}
							
						}
	for(i=0;i<3;i++)
	{
		for(j=0;j<3;j++)
			{for(k=0;k<3;k++)
				for(l=0;l<3;l++)
					System.out.print(arow[i][j][k][l]+" "); //+i+""+j+""+k+""+l+" ");
		
		System.out.println();
	}
}
}

private boolean check(int i,int j,int k,int l,int r)
{
	boolean bool=true;
	System.out.println("In check");
	for(int a=0; a<3;a++)
		for(int b=0;b<3;b++)
				{
					if(arow[a][j][b][l]==r ||arow[i][a][k][b]==r || arow[i][j][a][b]==r )
						{bool=false; break;}
				}
System.out.println("bool is: "+bool);
return bool;

}
private void set()
{
	int i,j,k,l,r;
	Random ran =new Random();
	r= ran.nextInt(15);
	r+=5;
	while(r>0)
	{
		i=ran.nextInt(3);
		j=ran.nextInt(3);
		k=ran.nextInt(3);
		l=ran.nextInt(3);
		if(f[i][j][k][l].getText().equals(""))
			{
				
				f[i][j][k][l].setText(""+arow[i][j][k][l]);
				f[i][j][k][l].setEditable(false);
				f[i][j][k][l].setForeground(Color.black);
				r--;
			}
	}
}

public void actionPerformed(ActionEvent e)
{
	int fl=0;
	JMenuItem i= (JMenuItem)e.getSource();
	if(i.getText().equalsIgnoreCase("New game"))
		newf();
	else if(i.getText().equalsIgnoreCase("Finish"))
		{
			lab.setFont(new Font("Serif",Font.ITALIC,30));
			lab.setBackground(Color.red);
			f1.add(BorderLayout.SOUTH,lab);
			for(int a=0;a<3;a++)
				for(int b=0;b<3;b++)
					for(int c=0;c<3;c++)
						for(int d=0;d<3;d++)
						{
							if(f[a][b][c][d].getText().equals(""))
								{fl=1;break;}
							int u=Integer.parseInt(f[a][b][c][d].getText());
							if(arow[a][b][c][d]!=u)
							{
								fl=1;
								break;
							}
						}
	
			if(fl==0)
				lab.setText("You Win");
			
			else
				lab.setText("Better luck next time");
			
	}
	else
		getsol();
}

private void getsol()
{
	int i,j,k,l;
	for(i=0;i<3;i++)
		for(j=0;j<3;j++)
			for(k=0;k<3;k++)
				for(l=0;l<3;l++)
					if(f[i][j][k][l].isEditable())
						f[i][j][k][l].setText(""+arow[i][j][k][l]);
}	

private void newf()
{
	int i,j,k,l;
	for(i=0;i<3;i++)
		for(j=0;j<3;j++)
			for(k=0;k<3;k++)
				for(l=0;l<3;l++)
				{
					arow[i][j][k][l]=0;
					if(!f[i][j][k][l].isEditable())
						f[i][j][k][l].setEditable(true);
					f[i][j][k][l].setForeground(Color.red);
					f[i][j][k][l].setText("");

}
	if(flag==1)
		{
			f1.remove(lab); 
			flag=0;
		}
	make();
}
}