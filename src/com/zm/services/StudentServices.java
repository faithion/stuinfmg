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
	 * 添加学生
	 * @return
	 * @throws Exception
	 */
	public boolean addStudent()throws Exception{
		//1.定义JDBC变量
		Connection conn = null;
		PreparedStatement pstm = null;
		try{
			//2.创建连接
			conn=DBUtils.getConnection();
			//3.创建sql语句
			StringBuilder sql = new StringBuilder()
					.append("insert into stuinf(student02,student03,student04,student05,student06,student07,")
					.append("			student08,student09)")
					.append("			values(?,?,?,?,?,?,?,?);");
					
			//4.编译sql语句
			pstm=conn.prepareStatement(sql.toString());
			//5.参数赋值
			pstm.setObject(1, this.dto.get("student02"));
			pstm.setObject(2, this.dto.get("student03"));
			pstm.setObject(3, this.dto.get("student04"));
			pstm.setObject(4, this.dto.get("student05"));
			pstm.setObject(5, this.dto.get("student06"));
			pstm.setObject(6, Tools.joinArray(this.dto.get("student07")));
			pstm.setObject(7, this.dto.get("student08"));
			pstm.setObject(8, this.dto.get("student09"));
			//6.执行sql语句
			return pstm.executeUpdate()>0;
		}finally {
			DBUtils.close(pstm);
			DBUtils.close(conn);
		}
	}
	/**
	 * 编号查询
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
    * 不定条件查询
    * @return
    * @throws Exception
    */
   public List<Map<String,String>> query()throws Exception
   {
	   //1.定义JDBC接口变量
	   Connection conn=null;
	   PreparedStatement pstm=null;
	   ResultSet rs=null;
	   try
	   {
		   //从DTO还原页面查询条件
		   Object student02=this.dto.get("qstudent02");//学号
		   Object student03=this.dto.get("qstudent03");//姓名
		   Object bstudent01=this.dto.get("bstudent01");//入库时间[begin]
		   Object estudent01=this.dto.get("estudent01");//入库时间[end]
		   Object student08=this.dto.get("qstudent08");//班级
		   Object student04=this.dto.get("qstudent04");//性别
		   
		   //创建连接
		   conn=DBUtils.getConnection();
		   //定义SQL语句
		   StringBuilder sql=new StringBuilder()
				   .append("select student01,student02,student03,a.fvalue student04,student05,student06,student07,b.fvalue student08,student09")
				   .append("	from stuinf,syscode a,syscode b")
				   .append("		where a.fname='student04' and student04=a.fcode")
				   .append("					and b.fname='student08'	and student08=b.fcode");
		   //表示每个?对应的参数
		   List<Object> paramList=new ArrayList<>();
		   //2.逐一判断各个条件是否录入了数据
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
		   
		   //编译SQL语句
		   pstm=conn.prepareStatement(sql.toString());
		   
		   //完成动态参数的赋值
		   int index=1;//表示?的下标
		   for(Object param:paramList){
			   pstm.setObject(index++, param);
		   }
		   
		   //执行查询
		   rs=pstm.executeQuery();
		   //获取结果集描述对象
		   ResultSetMetaData rsmd=rs.getMetaData();
		   //计算结果集中的列数
		   int count=rsmd.getColumnCount();
		   //计算安全的初始容量
		   int initSize=((int)(count/0.75))+1;
		   
		   //定义容器,装载全部数据
		   List<Map<String,String>> rows=new ArrayList<>();
		   //定义容器变量,装载当前行数据
		   Map<String,String> ins=null;
		   
		   //循环解析rs结果集
		   while(rs.next())
		   {
			   //实例化装载当前行的HashMap
			   ins=new HashMap<>(initSize);
			   //遍历rs中的所有列
			   for(int i=1;i<=count;i++)
			   {
				   //完成列级映射
				   ins.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
			   }
			   //将当前行数据放入List
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
    * 修改数据
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
    * 删除数据
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
    * 批量删除
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
		   //从DTO中还原数组
		   String idlist[]=(String[])this.dto.get("idlist");
		   
		   //循环数组，读取每行数据对应的主键值
		   for(String id:idlist){
			   System.out.println(id);
			   //完成参数赋值
			   pstm.setObject(1, id);
			   //将准备好的sql语句，放入缓冲区
			   //batch相当于批量处理，是事务
			   pstm.addBatch();
		   }
		   //定义事务的默认返回值，表示执行失败
		   boolean tag=false;
		   //开启事务
		   conn.setAutoCommit(false);
		   try {
			   //在事务范围内，执行缓冲区中所有的SQL语句
			   pstm.executeBatch();
			   //提交事务
			   conn.commit();
			   //修改事务返回值
			   tag=true;
		   } catch (Exception e) {
			 //回滚事务
			   conn.rollback();
			   e.printStackTrace();
		   } finally {
			   //结束事务
			   conn.setAutoCommit(true);
		   }
		   return tag;
	   } finally {
			DBUtils.close(pstm);
			DBUtils.close(conn);
	   }
   }
}
