<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 05.04.2020
  Time: 15:14
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
    <meta http-equiv="refresh" content="5">
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

    .text-size-30 {
        margin: 8px;
        font-size: 25px;
    }
    .text-size-40 {
        margin: 8px;
        font-size: 40px;
    }
</style>
<body>
<div class="background-image w3-display-container w3-text-white">
    <div class="w3-display-middle">
        <h1 class="w3-jumbo ">UNTITLED BATTLESHIP GAME</h1>
        <hr class="w3-border-grey" style="margin:auto;width:60%">
<%--        <p class="text-size-30 w3-center">WELCOME</p>--%>
<%--        <p class="text-size-40 w3-center">"${sessionScope.player.name}"</p>--%>
        <p class="text-size-30 w3-center">Please wait for second player to set their own ships</p>
    </div>
    <div class="w3-display-bottomleft w3-padding-large">
        Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a>
    </div>
    <div class="w3-display-bottomright w3-padding-large w3-large">
        <a href="https://github.com/Durran0490/BattleShip" target="_blank">GitHub
            <img src="res/GitHub-Mark-Light-32px.png">
        </a>
    </div>
</div>
</body>
</html>
