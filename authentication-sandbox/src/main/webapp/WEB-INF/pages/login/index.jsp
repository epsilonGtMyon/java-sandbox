<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />

  <title>login</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/common/css/style.css"></link>
</head>
<body>
	<h1>ログイン</h1>
	
	<div>
		<span class="message is-danger">
			<c:out value="${view.message}"/>
		</span>
	</div>
	<form action="./login" method="post">
		<div>
			<label>
				ユーザーID:
				<input type="text" name="userId" />
			</label>
		</div>
		<div>
			<label>
				パスワード
				<input type="password" name="password" />
			</label>
		</div>
	
		<button type="submit">ログイン</button>
	</form>
</body>
</html>