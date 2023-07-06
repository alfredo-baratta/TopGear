<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.html" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inserimento accessorio</title>
<style>
	.informazioni-prodotto {
		min-height: fit-content;
		width: 570px;
		margin: auto;
		margin-top: 100px;
	}
	
	@media screen and (min-width: 1251px) {
		.informazioni-prodotto-form{
			padding-left: 10%;
			padding-right: 10%;
		}
	}
	
	.informazioni-prodotto input[type="text"],
	.informazioni-prodotto textarea {
	  width: 100%;
	}
	
	input[type="text"],
	input[type="number"],
	textarea{
		border: 1px solid grey;
		outline: none;
		margin-bottom: 15px;
		display: block;
		max-width: 450px;
		width: 450px;
        padding: 7px;
        border: 2px solid #dbdada;
        border-radius: 5px;
        outline: none;
        font-size: 12px;
		resize: none;
	}
	
	textarea{
		max-height: 100px;
	}
	
      
      @media screen and (max-width: 1251px) {
        .container {
          flex-direction: column;
          justify-content: flex-end;
        }
        .informazioni-prodotto {
          width: 100%;
          height: 100%;
          width: 600px;
          align-self: center;
        }
      }

      @media screen and (max-width: 630px) {
        .informazioni-prodotto {
          width: 98%;
        }
	    input[type="text"],
		input[type="number"],
		textarea{
			border: 1px solid grey;
			outline: none;
			margin-bottom: 15px;
			display: block;
			max-width: 100%;
			width: 100%;
	        padding: 7px;
	        border: 2px solid #dbdada;
	        border-radius: 5px;
	        outline: none;
	        font-size: 12px;
			resize: none;
		}
      }
      
      .submit-button{
      		margin-top: 15px;
      		display: block;
      }
      
      .rbdiv{
      	margin-bottom: 10px;
      	display: block;
      }
	
</style>
</head>
<body>
	<div class="informazioni-prodotto">
      <form class="informazioni-prodotto-form" action="upModAccessorio" method="post">
        <label for="nome">Nome:</label>
        <input type="text" name="nome" required>
        
  		<label for="descrizione">Descrizione:</label>
  		<textarea id="descrizione" name="descrizione" rows="8" cols="50" required></textarea>
        
        <label for="prezzo">Prezzo:</label>
        <input type="number" id = "prezzo" step="0.01" name="prezzo" required>
        
        <label for="prezzo">Iva:</label>
        <input type="number" id = "iva" step="0.01" name="iva" required>
        
        <label for="qt_disp">Quantità disponibile:</label>
        <input type="number" id="qt_disp" name="disponibilita" required>
        
        <div class="rbdiv">
	  		<input type="radio" id="rb1" class="visibile" name="visibile" value="false" checked>
	  		<label for="rb1">Non visibile</label>
  		</div>
		
		<div class="rbdiv">
  			<input type="radio" id="rb2" class="visibile" name="visibile" value="true">
  			<label for="rb2">Visibile</label>
		</div>
		
		<input type="file" class="up-img" id="imageFile" multiple="multiple" name="imageFile" accept="image/*" >
		
		<button type="submit" class="submit-button">Inserisci prodotto</button>
      </form>
    </div>
</body>
<%@ include file="footer.html" %>


<script>
	document.querySelector("#imageFile").addEventListener("change", (e) => {
	if (window.File && window.FileReader && window.FileList && window.Blob){
		const files = e.target.files;
		console.log(files);
	}else {
		alert("Your browser does not support the File API")
	}
	})
</script>
</html>