<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<label for="name" class="form-label" style="font-size: 40px;">Movie
		Name </label>
	<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	<input style="font-size: 25px; width: 50%" type="name"
		class="form-control" value="${movie.getName()} name="name">

	<br>
	<br>
	<br>

	<label for="desc" class="form-label" style="font-size: 40px;">Movie
		Description</label>
	<br>


	<input type="text" class="form-control" name="desc"
		value="${movie.getDes()}"
		style="width: 60%; border-radius: 5px; height: 35px; overflow-wrap: break-word; padding: 0 0 0 0; font-size: 20px;"></input>

	<br>
	<br>
	<br>


	<label for="rating" class="form-label" style="font-size: 40px;">Movie
		Rating</label>
	<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	<input type="text" class="form-control"
		style="font-size: 25px; width: 50%" name="rating"
		value="${movie.getRating()}">


	<br>
	<br>
	<br>

	<label for="release_Year" class="form-label" style="font-size: 40px;">Year
		of Release</label>
	<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
	<input type="text" class="form-control" name="release_Year"
		style="font-size: 25px; width: 50%" value="${movie.getRelease_Year()}">

	<br>
	<br>
	<br>

	<div>
		<label for="ss1" class="form-label" style="font-size: 40px;">Movie
			ScreenShot</label> <span>&nbsp;&nbsp;&nbsp;&nbsp;</span><input type="file"
			class="form-control"  name="ss1">
			<img alt="nhi h kuch" src="background.jpg">
	</div>
	<div>
		<label for="ss2" class="form-label" style="font-size: 40px;">Movie
			ScreenShot</label> <span>&nbsp;&nbsp;&nbsp;&nbsp;</span> <input type="file"
			class="form-control" name="ss2">
	</div>

	<div>
		<label for="ss3" class="form-label" style="font-size: 40px;">Movie
			ScreenShot</label> <span>&nbsp;&nbsp;&nbsp;&nbsp;</span> <input type="file"
			class="form-control" name="ss3">
	</div>

	<div>
		<label for="ss4" class="form-label" style="font-size: 40px;">Movie
			ScreenShot</label> <span>&nbsp;&nbsp;&nbsp;&nbsp;</span> <input type="file"
			class="form-control" name="ss4">
	</div>

</body>
</html>