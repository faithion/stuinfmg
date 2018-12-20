package com.zm.system.db;

//���Ӷ���
import java.sql.Connection;
//����������
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//��Դ�ļ�������
import java.util.ResourceBundle;

/**
 * �����ڲ����г�Ա(���Ժͷ���)����static��,��ô
 * ��ʱ��������ʵ����,���й�����(���캯��,���췽��,������),һ��˽��
 */
public class DBUtils 
{
	//����jar�к������ȫ·��
	private static String driver=null;
	//�������Ӵ�
	private static String url=null;
	private static String username=null;
	private static String password=null;
	
	//ͨ����̬���������
	static
	{
		//System.out.println("run  static block....");
		//alt+shift+z,���·����,�س�
		try 
		{
			//��ȡ��Դ�ļ���������
			ResourceBundle bundle=ResourceBundle.getBundle("DBOptions");
			//����Դ�ļ���,����ȡֵ
			driver=bundle.getString("DRIVER");
			url=bundle.getString("URL");
			username=bundle.getString("USERNAME");
			password=bundle.getString("PASSWORD");
			
			
			//��������
			Class.forName(driver);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	private DBUtils() {}
	
	public static Connection getConnection()throws Exception
	{
		//ͨ������������������Ŀ�����ݿ������
		return DriverManager.getConnection(url, username, password);
	}
	public static void close(ResultSet rs)
	{
		try
		{
			if(rs!=null)   //���NPE
			{
				rs.close();	
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pstm)
	{
		try
		{
			if(pstm!=null)   //���NPE
			{
				pstm.close();	
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void close(Connection conn)
	{
		try
		{
			if(conn!=null)   //���NPE
			{
				conn.close();	
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		try
		{
//			for(int i=0;i<10;i++)
//			{
				System.out.println(DBUtils.getConnection());
//			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	
}










