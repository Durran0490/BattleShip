<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 07.04.2020
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>BattleShip</title>
    <link rel="icon" href="res/favicon.ico">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
</head>
<style>
    body, h1 {
        font-family: "Raleway", sans-serif
    }

    body, html {
        height: 100%
    }

    .background-image {
        background-image: url("../res/background.png");
        background-color: slategrey;
        min-height: 100%;
        background-position: center;
        background-size: cover;
    }

    .text-size-60 {
        margin: 8px;
        font-size: 60px;
        color: #ff4f2b;
        font-weight: bold;
    }

    .text-size-40 {
        margin: 8px;
        font-size: 40px;
    }

    .container {
        display: grid;
        width: 100%;
        height: 100%;
        grid-template-areas: "my-title my-title my-title my-title" "my-winner my-winner my-winner my-winner" "left-space my-continue my-left right-space" "my-footer my-footer my-footer my-footer";
        grid-template-columns: 30% 20% 20% 30%;
        grid-template-rows: 193px 253px 260px 211px;
    }

    .container > div {
        /*border: 1px dashed #888;*/
    }

    .my-title {
        grid-area: my-title;
    }

    .my-winner {
        padding-top: 50px;
        grid-area: my-winner;
    }
    .my-button {
        font-weight: bold;
        color: rgb(181, 181, 181);
        margin-top: 20px;
        margin-left: 20%;
        height: 50px;
        border-style: outset;
        border-color: rgb(181, 255, 249);
        background-color: white;
        width: 260px;
    }

    .my-button:active {
        background-color: #d2ffba;
        transform: translateY(1px);
        border-style: inset;
        border-color: rgb(181, 255, 249);
    }

    .my-button:hover {
        background-color: #d2ffba;
        color: rgb(110, 110, 110);
        box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0 rgba(0, 0, 0, 0.19);
    }
    .my-button-exit {
        font-weight: bold;
        color: rgb(181, 181, 181);
        margin-top: 20px;
        margin-left: 20%;
        height: 50px;
        border-style: outset;
        border-color: rgb(181, 255, 249);
        background-color: white;
        width: 260px;
    }

    .my-button-exit:active {
        background-color: #ff7d7d;
        transform: translateY(1px);
        border-style: inset;
        border-color: rgb(181, 255, 249);
    }

    .my-button-exit:hover {
        background-color: #ff7d7d;
        color: rgb(110, 110, 110);
        box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0 rgba(0, 0, 0, 0.19);
    }
</style>
<body>
<div class="container background-image w3-text-white">
    <div class="my-title">
        <div class="w3-center">
            <h1 class="w3-jumbo w3-animate-top ">UNTITLED BATTLESHIP GAME</h1>
            <hr class="w3-border-grey" style="margin:auto;width:40%">
            <p class="text-size-40 w3-center">DEFEAT</p>
        </div>
    </div>
    <div class="my-winner">
        <div class="w3-center">
            <p class="text-size-60 w3-center w3-animate-opacity">YOU'VE LOST THIS MATCH "${sessionScope.player.name}" !</p>
            <p></p>
            <p class="text-size-40 w3-center w3-animate-opacity">PLAYER "${sessionScope.game.getWinner().getName()}" HAS WON</p>
        </div>
    </div>
    <div class="left-space"></div>
    <div class="my-continue" >
        <form action="/start" method="post">
            <button type="submit" class="my-button" name="continue" value="continue" >START NEW GAME</button>
        </form>
    </div>
    <div class="my-left" >
        <form action="/start" method="get">
            <button type="submit" class="my-button-exit"  name="refresh" value="refresh">EXIT GAME</button>
        </form>
    </div>
    <div class="right-space"></div>
    <div class="my-footer">
        <div class="w3-display-bottomleft w3-padding-large">
            Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a>
        </div>
        <div class="w3-display-bottomright w3-padding-large w3-large">
            <a href="https://github.com/Durran0490/BattleShip" target="_blank">GitHub
                <img src="res/GitHub-Mark-Light-32px.png">
            </a>
        </div>
    </div>
</div>
</body>
</html>
