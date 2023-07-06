<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="header.html" %>
<!DOCTYPE html>
<html lang="it">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Home-Topgear</title>
    <link
      href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap"
      rel="stylesheet"
    />
    <style>
      /* Reset */
      body,
      html {
        background: #000;
        color: #fff;
        font-family: "Intro", sans-serif;
        font-size: 16px;
        box-sizing: border-box;
        margin: 0;
        padding: 0;
      }
      *,
      *:before,
      *:after {
        box-sizing: border-box;
      }
      h1,
      h2,
      h3,
      h4,
      h5,
      h6,
      p,
      ol,
      ul {
        margin: 0 0 1rem 0;
        padding: 0;
      }
      ol,
      ul {
        list-style-type: none;
      }
      img {
        height: auto;
      }
      h1 {
        font-size: 4rem;
      }
      h2 {
        font-size: 3rem;
      }
      h3 {
        font-size: 2rem;
      }
      h4 {
        font-size: 1rem;
      }
      h5 {
        font-size: 0.8rem;
      }
      h6 {
        font-size: 0.6rem;
      }
      a {
        text-decoration: none;
      }
      .video-bg {
        width: 100%;
        height: 100vh;
        position: fixed;
        top: 0;
        left: 0;
        object-fit: cover;
        opacity: 0.5;
        z-index: 0;
      }

      .section {
        position: relative;
        z-index: 1;
        width: 100%;
        height: 100vh;
      }

      .title {
        text-align: center;
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        max-width: 50%;
        position: fixed;
        top: 0;
        left: 0;
        margin-left: 25%;
        transform: scale(1);
        transition: all 1s cubic-bezier(0.215, 0.61, 0.355, 1);
        opacity: 0;
      }

      .section.in-page .title {
        transform: scale(1.2);
        opacity: 1;
      }

      .panel {
        height: auto;
        width: 100%;
        background: #f7f7f7;
        position: relative;
        z-index: 2;
        color: #000;
        display: flex;
        justify-content: center;
        align-items: center;
      }
      .panel--white {
        background: #fff;
      }

      .container {
        width: 100%;
        max-width: 1000px;
        padding: 15% 10% 0 10%;
      }

      .intro {
        font-size: 20px;
        font-weight: bold;
      }
      .title-big {
        font-size: 5rem;
      }

      .fade-in {
        opacity: 0;
        transform: translateY(10vh);
        transition: all 1s cubic-bezier(0.215, 0.61, 0.355, 1);
      }
      .fade-in.in-page {
        opacity: 1;
        transform: translateY(0vh);
      }

      .img-big-left {
        height: 450px;
        width: 1569px;
        position: relative;
        left: -30vw;
        display: block;
      }
      .img-big-center {
        width: 100%;
        position: relative;
        left: 0vw;
        display: block;
      }

      .title-med {
        font-size: 3.5rem;
      }
      .subtitle {
        font-size: 1.5rem;
        color: #666;
        max-width: 500px;
        padding-bottom: 10%;
      }
      .orange-text {
        font-size: 2rem;
        color: #f56900;
        text-align: center;
        font-weight: bold;
      }
    </style>
  </head>
  <body>
     <video class="video-bg" src="assets/home.mp4" autoplay muted loop></video>
    <div class="section watch">
      <h3 class="title" style="position: fixed; bottom: 10">Auto</h3>
      <h1 class="title">Accendete i motori</h1>
    </div>
    <div class="section watch">
      <img src="assets/suv.png" alt="" class="title" />
    </div>
    <div class="section watch">
      <img src="assets/SUV2.png" alt="" class="title" />
    </div>

    <div class="panel">
      <div class="container">
        <p class="watch fade-in intro">Design</p>
        <h2 class="watch fade-in title-big">
          Ingegneria <br />
          estrema.
        </h2>
        <img src="assets/auto.png" alt="" class="img-big-left" />
      </div>
    </div>

    <div class="panel panel--white">
      <div class="container">
        <h2 class="watch fade-in title-med">
          Sembra una dura. Perchè lo è. estrema.
        </h2>
        <p class="watch fade-in subtitle">
          Volevamo creare l'auto assoluta, perciò abbiamo progettato ogni
          dettaglio con cura maniacale puntando a prestazioni ineguagliabili. Il
          carbonio bilancia perfettamente peso, robustezza e resistenza alle intemperie.
          I rinforzi della nuova carrozzeria proteggono dagli
          impatti laterali il parabrezza anteriore curvo, in cristallo di zaffiro. E
          la Digital Crown e la maniglia laterale ora sono più grandi, comodi da
          usare anche con i guanti.
        </p>

        <p class="watch fade-in orange-text">
          lorem ipsum lorem ipsum lorem ipsum lorem ipsum 
        </p>

        <img src="assets/auto2.png" alt="" class="img-big-center" />
      </div>
    </div>



    <script>
      // elements
      var elements_to_watch = document.querySelectorAll(".watch");

      // callback
      var callback = function (items) {
        items.forEach((item) => {
          if (item.isIntersecting) {
            item.target.classList.add("in-page");
          } else {
            item.target.classList.remove("in-page");
          }
        });
      };

      // observer
      var observer = new IntersectionObserver(callback, { threshold: 0.6 });

      // apply
      elements_to_watch.forEach((element) => {
        observer.observe(element);
      });
    </script>
  </body>
  <%@ include file="footer.html" %>
</html>
