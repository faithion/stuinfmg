<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e"%>
<html>
<head>
<title>ѧ����Ϣ����ϵͳ</title>
</head>
<body>
${msg}
<br>
<br>
	<form action="/stuinfmg/add.html" method="post">
		<table border="1" width="50%" align="center">
			<caption>
				${empty param.student01? '���':'�޸�' }ѧ��
				<hr width="160">
			</caption>
			<tr>
				<td>ѧ��</td>
				<td>
					<e:text name="student02" required="true" defval="${ins.student02 }"/>
				</td>
			</tr>
			<tr>
				<td>����</td>
				<td>
					<e:text name="student03" required="true" defval="${ins.student03 }"/>
				</td>
			</tr>
			<tr>
				<td>�Ա�</td>
				<td>
					<e:radio name="student04" value="��:1,Ů:2" defval="${ins.student04 }"/>
				</td>
			</tr>
			<tr>
				<td>�༶</td>
				<td>
					<e:text name="student05" required="true" defval="${ins.student05 }"/>
				</td>
			</tr>		
			<tr>
				<td>��ѧ����</td>
				<td>
					<e:date name="student06" defval="${ins.student06 }"/>
				</td>
			</tr>	
			<tr>
				<td>����</td>
				<td>
					<e:groupbox name="student07" value="����:1,����:2,�˶�:3,����:4" defval="${ins.student07 }"/>
				</td>
			</tr>	
			<tr>
				<td>״̬</td>
				<td>
					<e:select name="student08" value="�ڶ�:1,��ѧ:2,��ҵ:3" defval="${ins.student08 }"/>
				</td>
			</tr>	
			<tr>
				<td>��ע</td>
				<td>
					<e:textarea name="student09" rows="5" cols="50" defval="${ins.student09 }"/>
				</td>
			</tr>	
			<tr>
				<td colspan="2" align="center">
					<input type="submit" name="next" value="${empty param.student01? '���':'�޸�' }"
					formaction="/stuinfmg/${empty param.student01? 'add':'modify' }.html">
					<input type="submit" name="next" value="����	" formaction="/stuinfmg/Query.jsp" formnovalidate="formnovalidate">
				</td>
			</tr>
		</table>
		<input type="hidden" name="student01" value="${param.student01 }">
	</form>
</body>
</html>