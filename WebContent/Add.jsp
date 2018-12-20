<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e"%>
<html>
<head>
<title>学生信息管理系统</title>
</head>
<body>
${msg}
<br>
<br>
	<form action="/stuinfmg/add.html" method="post">
		<table border="1" width="50%" align="center">
			<caption>
				${empty param.student01? '添加':'修改' }学生
				<hr width="160">
			</caption>
			<tr>
				<td>学号</td>
				<td>
					<e:text name="student02" required="true" defval="${ins.student02 }"/>
				</td>
			</tr>
			<tr>
				<td>姓名</td>
				<td>
					<e:text name="student03" required="true" defval="${ins.student03 }"/>
				</td>
			</tr>
			<tr>
				<td>性别</td>
				<td>
					<e:radio name="student04" value="男:1,女:2" defval="${ins.student04 }"/>
				</td>
			</tr>
			<tr>
				<td>班级</td>
				<td>
					<e:text name="student05" required="true" defval="${ins.student05 }"/>
				</td>
			</tr>		
			<tr>
				<td>入学日期</td>
				<td>
					<e:date name="student06" defval="${ins.student06 }"/>
				</td>
			</tr>	
			<tr>
				<td>爱好</td>
				<td>
					<e:groupbox name="student07" value="唱歌:1,跳舞:2,运动:3,看书:4" defval="${ins.student07 }"/>
				</td>
			</tr>	
			<tr>
				<td>状态</td>
				<td>
					<e:select name="student08" value="在读:1,休学:2,毕业:3" defval="${ins.student08 }"/>
				</td>
			</tr>	
			<tr>
				<td>备注</td>
				<td>
					<e:textarea name="student09" rows="5" cols="50" defval="${ins.student09 }"/>
				</td>
			</tr>	
			<tr>
				<td colspan="2" align="center">
					<input type="submit" name="next" value="${empty param.student01? '添加':'修改' }"
					formaction="/stuinfmg/${empty param.student01? 'add':'modify' }.html">
					<input type="submit" name="next" value="返回	" formaction="/stuinfmg/Query.jsp" formnovalidate="formnovalidate">
				</td>
			</tr>
		</table>
		<input type="hidden" name="student01" value="${param.student01 }">
	</form>
</body>
</html>