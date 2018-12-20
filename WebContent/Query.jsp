<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>查询</title>
	<script>
		function onEdit(vstudent01){
			document.forms['myform'].action="/stuinfmg/findById.html?student01="+vstudent01;
			document.forms["myform"].submit();
		}
	</script>
	<script>
		function onDel(vstudent01){
			document.forms['myform'].action="/stuinfmg/deleteById.html?student01="+vstudent01;
			document.forms['myform'].submit();
		}
	</script>
	<script>
		var count=0;//选中的checkbox的个数
		function onSelect(obj){
			obj?count++:count--;
			document.forms['myform'].next[2].disabled=(count==0);//checkbox不为空则可以删除
			if(document.getElementsByName("idlist").length==count){//如果全部被选了
				document.getElementsByName("sel")[0].checked=true;
			}
		}
		function onAllSelect(){
			if(document.getElementsByName("sel")[0].checked==true){
				for(var i =0;i<document.getElementsByName("idlist").length;i++){
					document.getElementsByName("idlist")[i].checked=true;
				}
			}else{
				for(var i =0;i<document.getElementsByName("idlist").length;i++){
					document.getElementsByName("idlist")[i].checked=false;
				}
			}
		}
	</script>
	<script>
		function test(){
		}
	</script>
</head>
<body>
	${msg }
	<br>
	<br>
	<form id="myform" action="/stuinfmg/query.html" method="post">
		<!-- 数据查询条件 -->
		<table border="1" width="80%" align="center">
			<caption>
				学生信息管理
				<hr width="160">
			</caption>
			<tr>
				<td colspan="4" align="center">查询条件</td>
			</tr>
			<tr>
				<td>学号</td>
				<td>
					<e:text name="qstudent02" autofocus="true"/>
				</td>
				<td>姓名</td>
				<td>
					<e:text name="qstudent03"/>
				</td>
			</tr>
			<tr>
				<td>性别</td>
				<td>
					<e:radio name="qstudent04" value="男:1,女:2,不限:''" defval=""/>
				</td>
				<td>状态</td>
				<td>
					<e:select name="qstudent08" value="在读:1,修学:2,毕业:3" header="true"/>
				</td>
			</tr>
			<tr>
				<td>入库号[begin]</td>
				<td>
					<e:number step="1" name="bstudent01"/>
				</td>
				<td>入库号[end]</td>
				<td>
					<e:number step="1" name="estudent01"/>
				</td>
			</tr>
		</table>
		<!-- 数据迭代 -->
		<table border="1" width="80%" align="center">
			<tr>
				<td>
					<input type="checkbox" name="sel" onclick="onAllSelect()">
				</td>
				<td>序号</td>
				<td>入库号</td>
				<td>学号</td>
				<td>姓名</td>
				<td>性别</td>
				<td>班级</td>
				<td>生日</td>
				<td>状态</td>
				<td>备注</td>
				<td></td>
				<c:choose>
					<c:when test="${rows!=null }">
						<c:forEach var="ins" items="${rows }" varStatus="i">
							<tr>
								<td>
									<input type="checkbox" name="idlist" value="${ins.student01 }"
										onclick="onSelect(this.checked)">
								</td>
								<td>${i.count }</td>
								<td>${ins.student01 }</td>
								<td>${ins.student02 }</td>
								<td>
									<a href="#" onclick="onEdit('${ins.student01 }')">
										${ins.student03 }
									</a>
								</td>
								<td>${ins.student04 }</td>
								<td>${ins.student05 }</td>
								<td>${ins.student06 }</td>
								<td>${ins.student08 }</td>
								<td>${ins.student09 }</td>
								<td>
									<a href="#" onclick="onDel('${ins.student01 }')">[删除]</a>
								</td>
							</tr>
						</c:forEach>
					</c:when>
				
					<c:otherwise>
						<c:forEach begin="1" step="1" end="10">
							<tr>
								<td>&nbsp;</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</c:forEach>
					</c:otherwise>
			</c:choose>
		</table>
		<!-- 功能按钮 -->
		<table border="1" width="80%" align="center">
			<tr>
				<td align="center">
					<input type="submit" name="next" value="查询">
					<input type="submit" name="next" value="添加" formaction="/stuinfmg/Add.jsp">
					<input type="submit" name="next" value="删除" formaction="/stuinfmg/batchDelete.html"
						disabled="disabled">
				</td>
			<tr>
		</table>
	</form>
</body>
</html>