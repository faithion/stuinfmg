<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>��ѯ</title>
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
		var count=0;//ѡ�е�checkbox�ĸ���
		function onSelect(obj){
			obj?count++:count--;
			document.forms['myform'].next[2].disabled=(count==0);//checkbox��Ϊ�������ɾ��
			if(document.getElementsByName("idlist").length==count){//���ȫ����ѡ��
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
		<!-- ���ݲ�ѯ���� -->
		<table border="1" width="80%" align="center">
			<caption>
				ѧ����Ϣ����
				<hr width="160">
			</caption>
			<tr>
				<td colspan="4" align="center">��ѯ����</td>
			</tr>
			<tr>
				<td>ѧ��</td>
				<td>
					<e:text name="qstudent02" autofocus="true"/>
				</td>
				<td>����</td>
				<td>
					<e:text name="qstudent03"/>
				</td>
			</tr>
			<tr>
				<td>�Ա�</td>
				<td>
					<e:radio name="qstudent04" value="��:1,Ů:2,����:''" defval=""/>
				</td>
				<td>״̬</td>
				<td>
					<e:select name="qstudent08" value="�ڶ�:1,��ѧ:2,��ҵ:3" header="true"/>
				</td>
			</tr>
			<tr>
				<td>����[begin]</td>
				<td>
					<e:number step="1" name="bstudent01"/>
				</td>
				<td>����[end]</td>
				<td>
					<e:number step="1" name="estudent01"/>
				</td>
			</tr>
		</table>
		<!-- ���ݵ��� -->
		<table border="1" width="80%" align="center">
			<tr>
				<td>
					<input type="checkbox" name="sel" onclick="onAllSelect()">
				</td>
				<td>���</td>
				<td>����</td>
				<td>ѧ��</td>
				<td>����</td>
				<td>�Ա�</td>
				<td>�༶</td>
				<td>����</td>
				<td>״̬</td>
				<td>��ע</td>
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
									<a href="#" onclick="onDel('${ins.student01 }')">[ɾ��]</a>
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
		<!-- ���ܰ�ť -->
		<table border="1" width="80%" align="center">
			<tr>
				<td align="center">
					<input type="submit" name="next" value="��ѯ">
					<input type="submit" name="next" value="���" formaction="/stuinfmg/Add.jsp">
					<input type="submit" name="next" value="ɾ��" formaction="/stuinfmg/batchDelete.html"
						disabled="disabled">
				</td>
			<tr>
		</table>
	</form>
</body>
</html>