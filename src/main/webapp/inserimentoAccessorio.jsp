<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.html" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inserimento accessorio</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<style>
	body {
        font-family: Arial, sans-serif;
        background-color: #fa885f;
      }
    .informazioni-prodotto {
        min-height: fit-content;
        width: 600px;
        margin: auto;
        background-color: #fff;
        padding-top: 100px;
    }
    
    .submit-button, .up-label{
        background-color: #9ceaff;
        font-size: 14px;
        color: black;
        border: none;
        outline: none;
        width: 250px;
        height: 45px;
        border-radius: 5px;
        margin: auto;
    	display: block; /* Imposta il pulsante come blocco per poter applicare il margine laterale automatico */
        margin-top: 15px;
      }
      
     .submit-button{
     	margin-bottom: 15px;
     }
      
      .up-label {
		  display: flex;
		  align-items: center;
		  justify-content: center;
		}
      
      .submit-button:hover, .up-label:hover {
        cursor: pointer;
        background-color: #4e9bb7;
        color: white;
      }
      
      #fileInput{
      	display: none;
      }
    
    @media screen and (min-width: 630px) {
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
        max-width: 480px;
        width: 480px;
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
        
        .informazioni-prodotto-form{
        	margin: auto;
    		display: block;
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

<style>
    output{
    width: 100%;
    min-height: 150px;
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    gap: 15px;
    position: relative;
    border-radius: 5px;
  }
  
  	output .image {
	  width: 150px;
	  height: 150px;
	  border-radius: 5px;
	  box-shadow: 0 0 5px rgba(0, 0, 0, 0.15);
	  overflow: hidden;
	  position: relative;
	}
	
	output .image img {
	  height: 100%;
	  width: 100%;
	  object-fit: cover;
	}

  
  	output .image span {
    	position: absolute;
    	top: -4px;
    	right: 4px;
    	cursor: pointer;
    	font-size: 22px;
    	color: white;
  	}
  
	  output .image span:hover {
	    opacity: 0.8;
	  }
	  
	  output .span--hidden{
	    visibility: hidden;
	  }
</style>
</head>
<body>
    <div class="informazioni-prodotto">
      <form class="informazioni-prodotto-form" method="post" onsubmit="inviaDati()"  enctype="multipart/form-data">
        <label for="nome" class="labnome">Nome:</label>
        <input type="text" name="nome" required>
        
        <label for="descrizione">Descrizione:</label>
        <textarea id="descrizione" name="descrizione" rows="8" cols="50" required></textarea>
        
        <label for="prezzo">Prezzo:</label>
        <input type="number" id="prezzo" step="0.01" name="prezzo" required>
        
        <label for="prezzo">Iva:</label>
        <input type="number" id="iva" step="0.01" name="iva" required>
        
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
        
        <input type="file" multiple="multiple" accept="image/*" name="fileInput[]" id="fileInput">
        <label for="fileInput" class="up-label">
			<i class="material-icons">add_photo_alternate</i>
			Carica immagine
		</label>
		<button type="submit" class="submit-button">Inserisci prodotto</button>
        
        <output id="imageOutput"></output>       
      </form>
    </div>
</body>
<%@ include file="footer.html" %>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"
    		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
  			crossorigin="anonymous"></script>


<script>
	const output = document.querySelector("#imageOutput");
	const fileInput = document.querySelector("#fileInput");
	let imagesArray = [];
	
	fileInput.addEventListener("change", () => {
	    const files = fileInput.files;
	    for (let i = 0; i < files.length; i++) {
	        const reader = new FileReader();
	        reader.onload = (event) => {
	            const imageBox = createImageBox(event.target.result);
	            imagesArray.push(imageBox);
	            displayImages();
	        };
	        reader.readAsDataURL(files[i]);
	    }
	});
	
	function createImageBox(imageSrc) {
	    const imageBox = document.createElement("div");
	    imageBox.classList.add("image");
	  
	    const imageElement = document.createElement("img");
	    imageElement.src = imageSrc;
	    imageElement.alt = "image";
	    imageBox.appendChild(imageElement);
	  
	    const deleteIcon = document.createElement("span");
	    deleteIcon.innerHTML = "&times;";
	    deleteIcon.classList.add("delete-icon");
	    deleteIcon.addEventListener("click", () => {
	        deleteImage(imageBox);
	    });
	    imageBox.appendChild(deleteIcon);
	  
	    return imageBox;
	}
	
	function displayImages() {
	    output.innerHTML = "";
	    imagesArray.forEach((imageBox) => {
	        output.appendChild(imageBox);
	    });
	}
	
	function deleteImage(imageBox) {
	    const index = imagesArray.indexOf(imageBox);
	    imagesArray.splice(index, 1);
	    displayImages();
	}
	
	
	function inviaDati() {
		  // Recupera i dati del form
		 
		  var nome = document.querySelector('input[name="nome"]').value;
		  var descrizione = document.querySelector('textarea[name="descrizione"]').value;
		  var prezzo = document.querySelector('input[name="prezzo"]').value;
		  var iva = document.querySelector('input[name="iva"]').value;
		  var disponibilita = document.querySelector('input[name="disponibilita"]').value;
		  var visibile = document.querySelector('input[name="visibile"]:checked').value;

		  // Crea l'oggetto FormData
		  var formData = new FormData();
		  
		  // Aggiungi i dati del form all'oggetto FormData
		  formData.append('nome', nome);
		  formData.append('descrizione', descrizione);
		  formData.append('prezzo', prezzo);
		  formData.append('iva', iva);
		  formData.append('disponibilita', disponibilita);
		  formData.append('visibile', visibile);
		  
		  imagesArray.forEach(function (imageBox) {
			  var image = imageBox.querySelector('img');
			  var file = dataURLtoFile(image.src, 'image.png'); // Converti l'URL dell'immagine in un oggetto File
			  formData.append('fileInput[]', file);
			});
		  
		  
		  // Invia i dati tramite Ajax
		  $.ajax({
			  url: 'caricamentoAccessorio',
			  type : 'POST',
              encType : 'multipart/form-data',
              dataType: 'json',
              //contentType: 'application/x-www-form-urlencoded',
              cache : false,
              processData : false,
              contentType : false,
              
              data : formData,
              
		    success: function(response) {
		    	window.location.replace("/TopGear/catalogoadmin");
		    },
		    error: function(xhr, status, error) {
		      alert(xhr.responseText+" "+status+" "+error)
		    }
		  });
		}
	
	// Funzione per convertire l'URL dell'immagine in un oggetto File
	function dataURLtoFile(dataURL, filename) {
	  var arr = dataURL.split(',');
	  var mime = arr[0].match(/:(.*?);/)[1];
	  var bstr = atob(arr[1]);
	  var n = bstr.length;
	  var u8arr = new Uint8Array(n);
	  while (n--) {
	    u8arr[n] = bstr.charCodeAt(n);
	  }
	  return new File([u8arr], filename, { type: mime });
	}


</script>
</html>
