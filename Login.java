import java.util.*;
import java.sql.*;
import java.io.*;


//*****************************Admin Module************************************

class Admin
{
    String name,id;
    Admin(String eid,String n)
    {
        name=n;
        id=eid;
    
    }
    static Scanner sc = new Scanner(System.in); 
    static Connection con = null ;
    static      
    {
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root",""); 
            if(con!=null)
                System.out.println("Connection Established...");
            else
                System.out.println("Connection Failed");

        }
        catch(Exception e)
        {
            System.out.println("Cannot Load Driver");
        }
    }

    public static void createUserAccount() throws Exception
    {
        Admin.ClearScreen();
        Scanner sc=new Scanner(System.in);
        System.out.println("Press 1 To Create Monitor Account :");
        System.out.println("Press 2 To Create Admin Account :");
        System.out.println("Press  to Exit :");
        int c=sc.nextInt();
        switch(c)
        {
            case 1:
            Admin.ClearScreen();
            PreparedStatement pst=con.prepareStatement("insert into prospect values(?,?,?,?,?,?,?,?)");
            System.out.println("Enter Prospect ID : " );
            pst.setInt(1,sc.nextInt());
            sc.nextLine();
            System.out.println("Enter Prospect Name : " );
            pst.setString(2,sc.nextLine());
            System.out.println("Enter Prospect Phone Number : " );
            pst.setString(3,sc.nextLine());
            System.out.println("Enter Prospect Address : " );
            pst.setString(4,sc.nextLine());
            System.out.println("Enter Interested Model : " );
            pst.setString(5,sc.nextLine());
            System.out.println("Enter Interested Color: " );
            pst.setString(6,sc.nextLine());
            System.out.println("Enter the Date of Visit : " );
            pst.setString(7,sc.nextLine());
            System.out.println("Enter the Category Of HOTNESS : " );
            pst.setString(8,sc.nextLine());
            int res = pst.executeUpdate();
            if(res>0)
                System.out.println("Record Inserted....");
            else
                System.out.println("Error in Inserting the Record");
            case 2:
            Admin.ClearScreen();
            PreparedStatement pst1=con.prepareStatement("insert into employee values(?,?,?,?,?,?,?)");
            System.out.print(" Username :");
            pst1.setString(1,sc.nextLine());
            System.out.print(" User Password:");
            pst1.setString(2,sc.nextLine());
            System.out.print(" User Type:");
            pst1.setString(3,sc.nextLine());
            System.out.print(" Full Name:");
            pst1.setString(4,sc.nextLine());
            System.out.print(" Phone :");
            pst1.setString(5,sc.nextLine());
            System.out.print(" Email :");
            pst1.setString(6,sc.nextLine());
            System.out.print(" Status :");
            pst1.setString(7,sc.nextLine());
            int res1=pst1.executeUpdate();
            if(res1>0)
            {
                System.out.println("Record inserted");
            }
            else
            {   
                System.out.println("Error");
            }
        }
    }

    public static void viewAllUsers() throws Exception
    {
        Admin.ClearScreen();
        PreparedStatement pst = con.prepareStatement("select * from employee    ");
        ResultSet res = pst.executeQuery();
        if(res.next()==false)
            System.out.println("No Records Available");
        else
        {   ResultSet t_res = pst.executeQuery();
            ResultSetMetaData rsmd = t_res.getMetaData();
            int n=1;
            while(n<8)
            {
                System.out.print(rsmd.getColumnName(n)+"\t\t");
                n++;
            }
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            while(t_res.next())
            {
                String s = String.format("%s \t\t %14s \t %14s \t %14s \t %14s \t %14s \t\t %14s \t",t_res.getString(1),t_res.getString(2),t_res.getString(3),t_res.getString(4),t_res.getString(5),t_res.getString(6),t_res.getString(7));
                System.out.println(s);
            }
        }
        sc.nextLine();
    }

    public static void viewProspect() throws Exception
    {
        Admin.ClearScreen();
        PreparedStatement pst = con.prepareStatement("select * from prospect");
        ResultSet res = pst.executeQuery();
        if(res.next()==false)
            System.out.println("No Records Available");
        else
        {   ResultSet t_res = pst.executeQuery();
            ResultSetMetaData rsmd = t_res.getMetaData();
            int n=1;
            while(n<9)
            {
                System.out.print(rsmd.getColumnName(n)+"\t\t");
                n++;
            }
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            while(t_res.next())
            {
                if(t_res.getString(4).length()<10)
                    System.out.print(t_res.getInt(1)+"\t\t"+t_res.getString(2)+"\t\t"+t_res.getString(3)+"\t\t"+t_res.getString(4)+"\t\t\t"+t_res.getString(5)+"\t\t"+t_res.getString(6)+"\t"+t_res.getString(7)+"\t"+t_res.getString(8)+"\n");
                else if(t_res.getString(4).length()>14)
                    System.out.print(t_res.getInt(1)+"\t\t"+t_res.getString(2)+"\t\t"+t_res.getString(3)+"\t\t"+t_res.getString(4)+"\t"+t_res.getString(5)+"\t\t"+t_res.getString(6)+"\t"+t_res.getString(7)+"\t"+t_res.getString(8)+"\n");

                else
                    System.out.print(t_res.getInt(1)+"\t\t"+t_res.getString(2)+"\t\t"+t_res.getString(3)+"\t\t"+t_res.getString(4)+"\t\t"+t_res.getString(5)+"\t\t"+t_res.getString(6)+"\t"+t_res.getString(7)+"\t"+t_res.getString(8)+"\n");
            }
        }
    }

    public static void changePwd() throws Exception
    {
        Admin.ClearScreen();
        Scanner sc=new Scanner(System.in);
        System.out.println("1.To Change Self Password \n2. To Change Others Password \n3.Exit \nEnter Choice :");
        int ch=sc.nextInt();
        switch(ch)
        {
            case 1 :
            PreparedStatement pst1 = con.prepareStatement("update employee set userPass = ? where userName = ?");
            System.out.println("Enter Username: ");
            sc.nextLine();
            pst1.setString(2,sc.nextLine());
            System.out.println("Enter new Password : ");
            pst1.setString(1,sc.nextLine());
            int res1 = pst1.executeUpdate();
            if(res1>0)
                System.out.println("Successfully Updated....");
            else
                System.out.println("Updation Failed");  
            break;
            case 2 :
            PreparedStatement pst2 = con.prepareStatement("select * from employee where userName=?");
            System.out.println("Enter Employee ID :");
            sc.nextLine();
            pst2.setString(1,sc.nextLine());
            ResultSet res2=pst2.executeQuery();
            if(res2.next())
            {
                pst2 = con.prepareStatement("update employee set userPass = ? where userName = ?");
                System.out.println("Enter new Password : ");
                sc.nextLine();
                pst2.setString(1,sc.nextLine());
                pst2.setString(2,sc.nextLine());
                int res3 = pst2.executeUpdate();
                if(res3>0)
                    System.out.println("Successfully Updated....");
                else
                    System.out.println("Updation Failed");  
                break;  
            }
        }
    }

    public static void search() throws Exception
    {
        Admin.ClearScreen();
        Scanner sc=new Scanner(System.in);
        System.out.println("Press 1 To Search By Hotness :");
        System.out.println("Press 2 To Search By Prospect ID :");
        System.out.println("press 3 Exit");
        int ch=sc.nextInt();
        sc.nextLine();
        switch(ch)
        {
            case 1 :
            PreparedStatement pst = con.prepareStatement("select * from prospect where hotness=?");
            System.out.println("Enter the Degree of Hotness(Hot/Warm/Cold)");
            pst.setString(1,sc.nextLine());
            ResultSet res=pst.executeQuery();
            while(res.next())
            {
                String s = String.format("%14d \t %14s \t %14s \t %14s \t %14s \t",res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8));
                System.out.println(s);
            }
            break;
            case 2 :
            PreparedStatement pst1 = con.prepareStatement("select * from prospect where prosID=?");
            System.out.println("Enter the Degree of Prospect ID :");
            pst1.setInt(1,sc.nextInt());
            ResultSet res_t=pst1.executeQuery();
            while(res_t.next())
            {
                String s = String.format("%14d \t %14s \t %14s \t %14s \t %14s \t",res_t.getInt(1),res_t.getString(2),res_t.getString(3),res_t.getString(4),res_t.getString(5),res_t.getString(6),res_t.getString(7),res_t.getString(8));
                System.out.println(s);
            }
            break;
            case 3 :

            break;
            default : System.out.println("Invalid Choice ...");
        }
    }

    public static void Menu() throws Exception
    {
        while(true)
        {
            System.out.println("1. Create New Account");
            System.out.println("2. View All Users");
            System.out.println("3. View All Prospect");
            System.out.println("4. Change Password");
            System.out.println("5. Search Prospect");
            System.out.println("6. Activate/Deactivate Account");
            System.out.println("7. Signout");
            int choice=sc.nextInt();
            switch(choice)
            {
                case 1:                 
                try
                {
                    createUserAccount();
                }
                catch(Exception e)
                {

                }
                break;
                case 2:
                try
                {
                    viewAllUsers();
                }
                catch(Exception e)
                {
                }
                break;
                case 3:
                try
                {
                    viewProspect();
                }
                catch(Exception e)
                {

                }
                break;      
                case 4:
                try
                {
                    changePwd();
                }
                catch(Exception e)
                {

                }
                break;      
                case 5:
                try
                {
                    search();
                }
                catch(Exception e)
                {

                }
                break;      
                case 6:
                try
                {
                    //AdminModule();
                }
                catch(Exception e)
                {

                }
                break;
                case 7:
                try
                {
                    System.exit(0);
                    break;
                }
                catch(Exception e)
                {

                }
                break;
                default:
                System.out.println("Invalid choice:");
                break;
            }

        }

    }

    public static void ClearScreen()
    {
        try{
            ProcessBuilder b= new ProcessBuilder("cmd","/c","cls");
            b.inheritIO().start().waitFor();
        }
        catch(Exception e)
        {
            System.out.println("Error Occured....");
        }
    }
}





//**************************Manager Module******************************
class Monitor
{
		String name,id;
		Monitor(String eid,String n)
		{
			id=eid;
			name=n;
		}
		static Scanner sc = new Scanner(System.in);	
		static Connection con = null ;
		static   	
		{
			try{
				Class.forName("com.mysql.jdbc.Driver");	
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");	
				if(con!=null)
					System.out.println("Connection Established...");
				else
					System.out.println("Connection Failed");
				
			}
			catch(Exception e)
			{
				System.out.println("Cannot Load Driver");
			}
		}
	public static void insert() throws Exception
   	{
		//Menu.ClearScreen();
        	Scanner sc=new Scanner(System.in);
      		PreparedStatement pst=con.prepareStatement("insert into prospect values(?,?,?,?,?,?,?,?)");
        	System.out.println("Enter Prospect ID : " );
        	pst.setInt(1,sc.nextInt());
		sc.nextLine();
        	System.out.println("Enter Prospect Name : " );
        	pst.setString(2,sc.nextLine());
       	 	System.out.println("Enter Prospect Phone Number : " );
        	pst.setString(3,sc.nextLine());
       		System.out.println("Enter Prospect Address : " );
       		pst.setString(4,sc.nextLine());
        	System.out.println("Enter Interested Model : " );
        	pst.setString(5,sc.nextLine());
        	System.out.println("Enter Interested Color: " );
        	pst.setString(6,sc.nextLine());
        	System.out.println("Enter the Date of Visit : " );
        	pst.setString(7,sc.nextLine());
        	System.out.println("Enter the Category Of HOTNESS : " );
        	pst.setString(8,sc.nextLine());
		int res = pst.executeUpdate();
        	if(res>0)
            		System.out.println("Record Inserted....");
        	else
            		System.out.println("Error in Inserting the Record");
   	}
	public static void select() throws Exception
    {
	//Menu.ClearScreen();
        PreparedStatement pst = con.prepareStatement("select * from prospect");
        ResultSet res = pst.executeQuery();
        if(res.next()==false)
            System.out.println("No Records Available");
        else
        {	ResultSet t_res = pst.executeQuery();
		ResultSetMetaData rsmd = t_res.getMetaData();
		int n=1;
		while(n<9)
		{
			System.out.print(rsmd.getColumnName(n)+"\t\t");
			n++;
		}
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            while(t_res.next())
            {
                if(t_res.getString(4).length()<10)
			 System.out.print(t_res.getInt(1)+"\t\t"+t_res.getString(2)+"\t\t"+t_res.getString(3)+"\t\t"+t_res.getString(4)+"\t\t\t"+t_res.getString(5)+"\t\t"+t_res.getString(6)+"\t"+t_res.getString(7)+"\t"+t_res.getString(8)+"\n");
		else if(t_res.getString(4).length()>14)
			 System.out.print(t_res.getInt(1)+"\t\t"+t_res.getString(2)+"\t\t"+t_res.getString(3)+"\t\t"+t_res.getString(4)+"\t"+t_res.getString(5)+"\t\t"+t_res.getString(6)+"\t"+t_res.getString(7)+"\t"+t_res.getString(8)+"\n");
		
		else
			System.out.print(t_res.getInt(1)+"\t\t"+t_res.getString(2)+"\t\t"+t_res.getString(3)+"\t\t"+t_res.getString(4)+"\t\t"+t_res.getString(5)+"\t\t"+t_res.getString(6)+"\t"+t_res.getString(7)+"\t"+t_res.getString(8)+"\n");
            }
        }
    }
	 public static void update() throws Exception
    {
	//Menu.ClearScreen();
        Scanner sc=new Scanner(System.in);
        System.out.println("1. Phone \n2. Model \n3. Color \n4. Hotness\nEnter choice... ");
        int ch=sc.nextInt();
        switch(ch)
        {
            case 1:
            PreparedStatement pst1 = con.prepareStatement("update prospect set prosPhone = ? where prosID = ?");
            System.out.println("Enter ID of The Prospect: ");
            pst1.setInt(2,sc.nextInt());
            System.out.println("Enter new Phone Number : ");
	    sc.nextLine();
            pst1.setString(1,sc.nextLine()) ;
            int res1 = pst1.executeUpdate();
            if(res1>0)
                System.out.println("Successfully Updated....");
            else
                System.out.println("Updation Failed");
            break;
            case 2:
            PreparedStatement pst2 = con.prepareStatement("update prospect set interestedModel = ? where prosID = ?");
            System.out.println("Enter ID of The Prospect: ");
            pst2.setInt(2,sc.nextInt());
            System.out.println("Enter new Model : ");
	    sc.nextLine();
            pst2.setString(1,sc.nextLine()) ;
            int res2 = pst2.executeUpdate();
            if(res2>0)
                System.out.println("Successfully Updated....");
            else
                System.out.println("Updation Failed");
            break;
            case 3:
            PreparedStatement pst3 = con.prepareStatement("update prospect set interestedColor = ? where prosID = ?");
            System.out.println("Enter ID of The Prospect: ");
            pst3.setInt(2,sc.nextInt());
            System.out.println("Enter new Color : ");
	    sc.nextLine();
            pst3.setString(1,sc.nextLine()) ;
            int res3 = pst3.executeUpdate();
            if(res3>0)
                System.out.println("Successfully Updated....");
            else
                System.out.println("Updation Failed");
            break;
            case 4:
            PreparedStatement pst4 = con.prepareStatement("update prospect set hotness = ? where prosID = ?");
            System.out.println("Enter ID of The Prospect: ");
            pst4.setInt(2,sc.nextInt());
            System.out.println("Enter the Hotness Category : ");
	    sc.nextLine();
            pst4.setString(1,sc.nextLine()) ;
            int res4 = pst4.executeUpdate();
            if(res4>0)
                System.out.println("Successfully Updated....");
            else
                System.out.println("Updation Failed");
            break;
            default:
            System.out.println("Invalid choice...!!!");
            break;
        }
    }

	 public static void search() throws Exception
    {
	//Menu.ClearScreen();
        Scanner sc=new Scanner(System.in);
        System.out.println("Press 1 To Search By Hotness :");
        System.out.println("Press 2 To Search By Prospect ID :");
        System.out.println("press 3 Exit");
        int ch=sc.nextInt();
        sc.nextLine();
        switch(ch)
        {
            case 1 :
            PreparedStatement pst = con.prepareStatement("select * from prospect where hotness=?");
            System.out.println("Enter the Degree of Hotness(Hot/Warm/Cold)");
            pst.setString(1,sc.nextLine());
            ResultSet res=pst.executeQuery();
            while(res.next())
            {
                String s = String.format("%14d \t %14s \t %14s \t %14s \t %14s \t",res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8));
                System.out.println(s);
            }
            break;
            case 2 :
            PreparedStatement pst1 = con.prepareStatement("select * from prospect where prosID=?");
            System.out.println("Enter the Degree of Prospect ID :");
            pst1.setInt(1,sc.nextInt());
            ResultSet res_t=pst1.executeQuery();
            while(res_t.next())
            {
                String s = String.format("%14d \t %14s \t %14s \t %14s \t %14s \t",res_t.getInt(1),res_t.getString(2),res_t.getString(3),res_t.getString(4),res_t.getString(5),res_t.getString(6),res_t.getString(7),res_t.getString(8));
                System.out.println(s);
            }
            break;
            case 3 :

            break;
		default : System.out.println("Invalid Choice ...");
        }
    }
	public static void password() throws Exception
	{
		//Menu.ClearScreen();
		Scanner sc=new Scanner(System.in);
		PreparedStatement pst1 = con.prepareStatement("update employee set userPass = ? where userName = ?");
            	System.out.println("Enter Username: ");
            	pst1.setString(2,sc.nextLine());
            	System.out.println("Enter new Password : ");
		pst1.setString(1,sc.nextLine());
            	int res1 = pst1.executeUpdate();
            	if(res1>0)
                	System.out.println("Successfully Updated....");
            	else
                	System.out.println("Updation Failed");	
	}
	public static void Menu() throws Exception
	{
		while(true)
		{
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			//Displaying The Menu...
			System.out.println("1. ADD NEW PROSPECT ");
			System.out.println("2. VIEW ALL PROSPECT ");
			System.out.println("3. UPDATE PROSPECT ");
			System.out.println("4. SEARCH ");
			System.out.println("5. CHANGE OWN PASSWORD ");
			System.out.println("6. SIGNOUT ");
			System.out.print("Enter Your Choice -->");
			//Taking the Choice for User & Store the Value...
			int choice=sc.nextInt();
			//Switch Case...
			switch(choice)
			{
				case 1 :try{
						insert();
						}
					catch(Exception e){}
						break;
				case 2 :try{
						select();
						}
						catch(Exception e){}
						break;
				case 3 :try{
						update();
						}
					catch(Exception e){}
						break;
				case 4 :try{
						search();
						}
					catch(Exception e){}
						break;
				case 5 :try{
						password();
						}
					catch(Exception e){}
						break;
				case 6 :
						System.exit(0);
               					 break;
				default : 	
						System.out.println("Invalid Choice Entered ...");
						break;
			}//switch...
			  String c=br.readLine();
		}//while
	}
public static void ClearScreen()
	{
	try{
                ProcessBuilder b= new ProcessBuilder("cmd","/c","cls");
                b.inheritIO().start().waitFor();
            }
            catch(Exception e)
            {
                System.out.println("Error Occured....");
            }
	}

}

 






//*********************************Login Module*********************************** 
class Login
{

    public static void main(String args[]) throws Exception
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter UserName :");
        String uname=sc.nextLine();
        System.out.println("Enter Password :");
        String pwd=new String(System.console().readPassword());
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        PreparedStatement pst=con.prepareStatement("select * from employee where userName=? and userPass=?");
        pst.setString(1,uname);
        pst.setString(2,pwd);
        ResultSet res=pst.executeQuery();
        if(res.next())
        {
            String Name=res.getString(1);
            String Password=res.getString(2);
            String Type=res.getString(3);
            if((Type.equals("Admin") || Type.equals("admin")))
            {
                Admin obj=new Admin(Name,Password);	
		obj.ClearScreen();
                obj.Menu();
            }
	    /*else
		{
			System.out.println("User is Not an Admin ");
		}*/
            else if(Type.equals("Manager") || Type.equals("manager"))
            {
		
                Monitor ob=new Monitor(Name,Password);
		ob.ClearScreen();
                ob.Menu();
            }
	    else
		{
			System.out.println("User has No Role in Database...");
		}
		
        }
        else
            System.out.println("Invalid Username & Password...");
    }
}
 