<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <style>
      body {
        min-height: 100vh;
        height: 100%;
        font-family: Arial, Helvetica, sans-serif;
      }

      .riepilogo {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        border: 3px solid #f5f5f5;
        border-radius: 5px;
        width: 100%;
        padding: 14px;
        max-width: 300px;
        max-height: 220px;
      }

      .carrello-left {
        width: 60%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
      }

      .dettagli-riepilogo {
        display: flex;
        flex-direction: column;
        width: 100%;
      }

      .dettagli-box {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        width: 100%;
        margin-bottom: 0;
      }

      .title {
        color: grey;
        font-weight: 600;
        font-size: 17px;
      }

      .description {
        color: black;
        font-size: 15px;
        font-weight: bold;
      }

      .container {
        width: 100%;
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        justify-content: space-around;
        align-items: center;
      }

      .riepilogo button {
        background-color: black;
        color: white;
        padding: 10px;
        border: none;
        outline: none;
        width: 100%;
        height: 45px;
        font-weight: 400;
        margin-top: 15px;
      }

      .riepilogo hr {
        margin-top: 10px;
        margin-bottom: 10px;
        width: 100%;
        border-top: 1px solid #f5f5f5;
      }

      button:hover {
        cursor: pointer;
      }

      table th {
        color: grey;
        font-weight: 600;
        padding-bottom: 10px;
        border-bottom: 2px solid #f5f5f5;
        font-size: 14px;
      }

      table td {
        text-align: center;
        padding-top: 15px;
        padding-bottom: 15px;
      }

      .table-title {
        width: 100%;
        display: flex;
        justify-content: space-between;
        align-items: center;
        border: none;
        outline: none;
        margin-bottom: 15px;
      }

      .item:not(:last-of-type) {
        border-bottom: 2px solid #f5f5f5;
      }

      .wrapper {
        display: flex;
        align-items: center;
        justify-content: center;
      }
      .wrapper span {
        width: 100%;
        text-align: center;
        font-size: 16px;
        font-weight: 600;
        cursor: pointer;
        user-select: none;
      }
      .wrapper span.num {
        font-size: 16px;
        border-right: 2px solid rgba(0, 0, 0, 0.2);
        border-left: 2px solid rgba(0, 0, 0, 0.2);
        pointer-events: none;
      }

      @media screen and (max-width: 1000px) {
        .container {
          flex-direction: column;
        }
        .carrello-left {
          width: 100%;
        }
        .riepilogo {
          margin-top: 25px;
        }
      }

      @media screen and (max-width: 600px) {
        .table thead {
          display: none;
        }

        .table,
        .table tbody,
        .table tr,
        .table td {
          display: flex;
          flex-direction: column;
          justify-content: center;
          width: 100%;
        }
        .table tr {
          margin-bottom: 15px;
        }
        .table td {
          position: relative;
        }
      }
      @media screen and (max-width: 370px) {
        .riepilogo {
          padding-left: 0;
          padding-right: 0;
          padding-top: 14px;
          padding-bottom: 14px;
        }

        .riepilogo hr {
          display: none;
        }

        .riepilogo button {
          width: 90%;
        }

        .dettagli-box {
          justify-content: space-around;
        }
      }
    </style>

    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
  </head>
  <body>
    <div class="container">
      <div class="carrello-left">
        <div class="table-title">
          <h3>Carrello</h3>
          <button
            style="
              width: 30px;
              height: 30px;
              background-color: transparent;
              border: 1px solid #f5f5f5;
              border-radius: 5px;
            "
          >
            <i class="fa fa-trash" aria-hidden="true"></i>
          </button>
        </div>
        <table style="width: 100%; border-collapse: collapse" class="table">
          <thead>
            <tr>
              <th>PRODOTTO</th>
              <th>QUANTITA'</th>
              <th>PREZZO</th>
            </tr>
          </thead>
          <tbody>
            <tr class="item">
              <td style="display: flex; gap: 5px; align-items: center">
                <img
                  style="height: 100px"
                  src="dogwood-7978952_1920.jpg"
                  alt="image"
                  srcset=""
                />
                <p style="font-weight: 500; margin-top: 0">Nome</p>
              </td>
              <td>
                <div class="wrapper">
                  <span class="minus">-</span>
                  <span class="num">1</span>
                  <span class="plus">+</span>
                </div>
              </td>
              <td>
                <p style="font-weight: 600">250 euro</p>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <div class="riepilogo">
        <div class="dettagli-riepilogo">
          <div class="dettagli-box">
            <p class="title">Prodotti presenti:</p>
            <p class="description">1</p>
          </div>
          <div class="dettagli-box">
            <p class="title">Totale con IVA:</p>
            <p class="description">550 euro</p>
          </div>
        </div>
        <hr />
        <button>Checkout</button>
      </div>
    </div>
    <script>
      const plus = document.querySelector(".plus");
      const minus = document.querySelector(".minus");
      const num = document.querySelector(".num");
      let quantity = 1;

      plus.addEventListener("click", () => {
        quantity++;
        num.innerText = quantity;
      });

      minus.addEventListener("click", () => {
        if (quantity > 1) {
          quantity--;
          num.innerText = quantity;
        }
      });
    </script>
  </body>
</html>
