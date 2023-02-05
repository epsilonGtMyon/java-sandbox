<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="/WEB-INF/el/dates.tld" prefix="dates"%>

<html>
<head>
 <title>home</title>
</head>

<body>
	<main>
		<div>
			<h2>now</h2>
			<div>
				${dates:now()}
			</div>
		</div>
		
		<div>
			<h2>format</h2>
			<div>
				<table>
					<tbody>
						<tr>
							<td>yyyy/MM/dd HH:mm:ss</td>
							<td>${dates:format(dates:now(), "yyyy/MM/dd HH:mm:ss")}</td>
						</tr>
						<tr>
							<td>yyyy/MM/dd</td>
							<td>${dates:format(dates:now(), "yyyy/MM/dd")}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</main>

</body>
</html>
