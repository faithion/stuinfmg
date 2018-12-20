package com.zm.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.tools.Tool;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;

import com.sun.media.jfxmedia.events.NewFrameEvent;
import com.zm.system.db.DBUtils;
import com.zm.system.tools.Tools;

public final class StudentServices {
	private Map<String, Object> dto=null;
	
	public StudentServices(Map<String, Object> dto){
		this.dto=dto;
	}
	/**
	 * ���ѧ��
	 * @return
	 * @throws Exception
	 */
	public boolean addStudent()throws Exception{
		//1.����JDBC����
		Connection conn = null;
		PreparedStatement pstm = null;
		try{
			//2.��������
			conn=DBUtils.getConnection();
			//3.����sql���
			StringBuilder sql = new StringBuilder()
					.append("insert into stuinf(student02,student03,student04,student05,student06,student07,")
					.append("			student08,student09)")
					.append("			values(?,?,?,?,?,?,?,?);");
					
			//4.����sql���
			pstm=conn.prepareStatement(sql.toString());
			//5.������ֵ
			pstm.setObject(1, this.dto.get("student02"));
			pstm.setObject(2, this.dto.get("student03"));
			pstm.setObject(3, this.dto.get("student04"));
			pstm.setObject(4, this.dto.get("student05"));
			pstm.setObject(5, this.dto.get("student06"));
			pstm.setObject(6, Tools.joinArray(this.dto.get("student07")));
			pstm.setObject(7, this.dto.get("student08"));
			pstm.setObject(8, this.dto.get("student09"));
			//6.ִ��sql���
			return pstm.executeUpdate()>0;
		}finally {
			DBUtils.close(pstm);
			DBUtils.close(conn);
		}
	}
	/**
	 * ��Ų�ѯ
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> findById() throws Exception{
		Connection conn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try{
			conn=DBUtils.getConnection();
			StringBuilder sql=new StringBuilder()
					.append("select student02,student03,student04,student05,student06,student07,student08,student09")
					.append(" from stuinf")
					.append(" where student01=?");
			pstm=conn.prepareStatement(sql.toString());
			pstm.setObject(1, this.dto.get("student01"));
			rs=pstm.executeQuery();
			Map<String, String> ins=null;
			if(rs.next()){
				ResultSetMetaData rsmd=rs.getMetaData();
				int count=rsmd.getColumnCount();
				int initSize=(int)(count/0.75)+1;
				ins=new HashMap<>(initSize);
				
				for(int i=1;i<=count;i++){
					ins.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
				}
			}
			return ins;
		}finally {
			DBUtils.close(rs);
			DBUtils.close(pstm);
			DBUtils.close(conn);
		}
	}
	/**
    * ����������ѯ
    * @return
    * @throws Exception
    */
   public List<Map<String,String>> query()throws Exception
   {
	   //1.����JDBC�ӿڱ���
	   Connection conn=null;
	   PreparedStatement pstm=null;
	   ResultSet rs=null;
	   try
	   {
		   //��DTO��ԭҳ���ѯ����
		   Object student02=this.dto.get("qstudent02");//ѧ��
		   Object student03=this.dto.get("qstudent03");//����
		   Object bstudent01=this.dto.get("bstudent01");//���ʱ��[begin]
		   Object estudent01=this.dto.get("estudent01");//���ʱ��[end]
		   Object student08=this.dto.get("qstudent08");//�༶
		   Object student04=this.dto.get("qstudent04");//�Ա�
		   
		   //��������
		   conn=DBUtils.getConnection();
		   //����SQL���
		   StringBuilder sql=new StringBuilder()
				   .append("select student01,student02,student03,a.fvalue student04,student05,student06,student07,b.fvalue student08,student09")
				   .append("	from stuinf,syscode a,syscode b")
				   .append("		where a.fname='student04' and student04=a.fcode")
				   .append("					and b.fname='student08'	and student08=b.fcode");
		   //��ʾÿ��?��Ӧ�Ĳ���
		   List<Object> paramList=new ArrayList<>();
		   //2.��һ�жϸ��������Ƿ�¼��������
		   if(student02!=null && !student02.equals("")){
			   sql.append(" and student02=?");
			   paramList.add(student02);
		   }
		   if(student03!=null && !student03.equals("")){
			   sql.append(" and student03 like ?");
			   paramList.add("%"+student02+"%");
		   }
		   if(student04!=null && !student04.equals("")){
			   sql.append(" and student04= ?");
			   paramList.add(student04);
		   }
		   if(student08!=null && !student08.equals("")){
			   sql.append(" and student08= ?");
			   paramList.add(student08);
		   }
		   if(bstudent01!=null && !bstudent01.equals("")){
			   sql.append(" and student01>=?");
			   paramList.add(bstudent01);
		   }
		   if(estudent01!=null && !estudent01.equals("")){
			   sql.append(" and student01<=?");
			   paramList.add(estudent01);
		   }
		   
		   //����SQL���
		   pstm=conn.prepareStatement(sql.toString());
		   
		   //��ɶ�̬�����ĸ�ֵ
		   int index=1;//��ʾ?���±�
		   for(Object param:paramList){
			   pstm.setObject(index++, param);
		   }
		   
		   //ִ�в�ѯ
		   rs=pstm.executeQuery();
		   //��ȡ�������������
		   ResultSetMetaData rsmd=rs.getMetaData();
		   //���������е�����
		   int count=rsmd.getColumnCount();
		   //���㰲ȫ�ĳ�ʼ����
		   int initSize=((int)(count/0.75))+1;
		   
		   //��������,װ��ȫ������
		   List<Map<String,String>> rows=new ArrayList<>();
		   //������������,װ�ص�ǰ������
		   Map<String,String> ins=null;
		   
		   //ѭ������rs�����
		   while(rs.next())
		   {
			   //ʵ����װ�ص�ǰ�е�HashMap
			   ins=new HashMap<>(initSize);
			   //����rs�е�������
			   for(int i=1;i<=count;i++)
			   {
				   //����м�ӳ��
				   ins.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
			   }
			   //����ǰ�����ݷ���List
			   rows.add(ins);
		   }
		   return rows;
	   }
	   finally
	   {
		   DBUtils.close(rs);
		   DBUtils.close(pstm);
		   DBUtils.close(conn);
	   }
   }
   /**
    * �޸�����
    * @return
    * @throws Exception
    */
   public boolean modify() throws Exception{
	   PreparedStatement pstm=null;
	   Connection conn=null;
	   try{
		   StringBuilder sql=new StringBuilder()
				   .append("update stuinf ")
				   .append("	set student02=?,student03=?,student04=?,student05=?,student06=?,student07=?,student08=?,student09=?")
				   .append("		where student01=?");
		   conn=DBUtils.getConnection();
		   pstm=conn.prepareStatement(sql.toString());
		   pstm.setObject(1, this.dto.get("student02"));
		   pstm.setObject(2, this.dto.get("student03"));
		   pstm.setObject(3, this.dto.get("student04"));
		   pstm.setObject(4, this.dto.get("student05"));
		   pstm.setObject(5, this.dto.get("student06"));
		   pstm.setObject(6, Tools.joinArray(this.dto.get("student07")));
		   pstm.setObject(7, this.dto.get("student08"));
		   pstm.setObject(8, this.dto.get("student09"));
		   pstm.setObject(9, this.dto.get("student01"));
		   return pstm.executeUpdate()>0;
	   }finally {
		   DBUtils.close(pstm);
		   DBUtils.close(conn);
	   }
   }
   /**
    * ɾ������
    * @return
    * @throws Exception
    */
   public boolean deleteById()throws Exception{
	   Connection conn=null;
	   PreparedStatement pstm=null;
	   try {
		   conn=DBUtils.getConnection();
		   String sql="delete from stuinf where student01=?";
		   pstm=conn.prepareStatement(sql);
		   pstm.setObject(1, this.dto.get("student01"));
		   System.out.println(pstm);
		   return pstm.executeUpdate()>0;
	   } finally {
			DBUtils.close(pstm);
			DBUtils.close(conn);
	   }
   }
   /**
    * ����ɾ��
    * @return
    * @throws Exception
    */
   public boolean batchDelete()throws Exception{
	   Connection conn=null;
	   PreparedStatement pstm=null;
	   try {
		   conn=DBUtils.getConnection();
		   String sql="delete from stuinf where student01=?";
		   pstm=conn.prepareStatement(sql);
		   //��DTO�л�ԭ����
		   String idlist[]=(String[])this.dto.get("idlist");
		   
		   //ѭ�����飬��ȡÿ�����ݶ�Ӧ������ֵ
		   for(String id:idlist){
			   System.out.println(id);
			   //��ɲ�����ֵ
			   pstm.setObject(1, id);
			   //��׼���õ�sql��䣬���뻺����
			   //batch�൱����������������
			   pstm.addBatch();
		   }
		   //���������Ĭ�Ϸ���ֵ����ʾִ��ʧ��
		   boolean tag=false;
		   //��������
		   conn.setAutoCommit(false);
		   try {
			   //������Χ�ڣ�ִ�л����������е�SQL���
			   pstm.executeBatch();
			   //�ύ����
			   conn.commit();
			   //�޸����񷵻�ֵ
			   tag=true;
		   } catch (Exception e) {
			 //�ع�����
			   conn.rollback();
			   e.printStackTrace();
		   } finally {
			   //��������
			   conn.setAutoCommit(true);
		   }
		   return tag;
	   } finally {
			DBUtils.close(pstm);
			DBUtils.close(conn);
	   }
   }
}
