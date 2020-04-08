<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 07.04.2020
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>BattleShip</title>
    <title>BattleShip</title>
    <link rel="icon" href="res/favicon.ico">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <style>
        body, h1 {
            font-family: "Raleway", sans-serif;
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
            font-size: 30px;
        }

        .text-size-20 {
            color: white;
            padding-top: 5%;
            padding-right: 2%;
            font-size: 20px;
            text-align: center;
            margin: auto;
            font-weight: bold;
        }

        .container {
            display: grid;
            width: 100%;
            height: 100%;
            grid-template-areas: "my-header my-header my-header my-header my-header" "left a-BNKAN-1 a-BNKAN-2 a-BNKAN-3 right" "left player-field my-message-field enemy-field right" "my-footer my-footer my-footer my-footer my-footer";
            grid-template-columns: 20% 20% 20% 20% 20%;
            grid-template-rows: 20% 10% 60% 10%;
        }

        .container > div {
            /*border: 1px dashed #888;*/
        }

        .my-header {
            grid-area: my-header;
        }

        .left {
            grid-area: left;
        }

        .a-BNKAN-1 {
            grid-area: a-BNKAN-1;
        }

        .a-BNKAN-2 {
            grid-area: a-BNKAN-2;
        }

        .a-BNKAN-3 {
            grid-area: a-BNKAN-3;
        }

        .right {
            grid-area: right;
        }

        .player-field {
            grid-area: player-field;
            padding-left: 23%;
        }

        .my-message-field {
            grid-area: my-message-field;
        }

        .enemy-field {
            grid-area: enemy-field;
        }

        .my-footer {
            grid-area: my-footer;
        }
        .my-button {
            font-weight: bold;
            color: rgb(181, 181, 181);
            margin-top: 20px;
            margin-left: 3%;
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

        textarea {
            margin-left: 16%;
            resize: none;
            margin-top: 5%;
            padding-left: 1px;
        }

        td {
            text-align: center;
            width: 22px;
            height: 22px;

        }
        td.white{
            background: white;
        }

        td.SHIP {
            background-color: #6dfc9f;
        }
        td.SHIP::after {
            content: '\25C9';
        }

        td.MISS {
            background-color: aqua;
        }

        td.MISS::after {
            content: '\2B25';
        }
        td.HIT{
            background-color: red;
        }
        td.HIT::after {
            content: '\2BBD';
        }

        input[type="radio"][disabled]
        {
            -webkit-appearance:none;
            right: 5px;
        }

    </style>
</head>
<body>

<div class="container background-image">
    <div class="my-header">
        <div class="w3-text-white w3-animate-opacity">
            <h1 class="w3-jumbo w3-center w3-animate-top" style="margin:auto">UNTITLED BATTLESHIP
                GAME</h1>
            <hr class="w3-border-grey" style="margin:auto;width:30%;border-width: 1px">
            <p class="text-size-30 w3-center">SINK 'EM</p>
        </div>
    </div>
    <div class="left"></div>
    <div class="a-BNKAN-1"style="margin-left: 15%">
        <p class="text-size-20" >YOUR FIELD</p>
    </div>
    <div class="a-BNKAN-2">
        <p class="text-size-20">MESSAGES</p>
    </div>
    <div class="a-BNKAN-3"style="margin-right: 20%">
        <p class="text-size-20">ENEMY FIELD</p>
    </div>
    <div class="right"></div>

    <div class="player-field">
        <form action="/game" method="post">
            <table>
                <tr>
                    <td></td>
                    <c:forEach items="A,B,C,D,E,F,G,H,I,J" var="col">
                        <td>${col}</td>
                    </c:forEach>
                </tr>
                <c:forEach begin="1" end="10" var="row">
                    <tr>
                        <td>${row}</td>
                        <c:forEach items="A,B,C,D,E,F,G,H,I,J" var="col">
                            <c:set var="addr" value="${col}${row}"/>
                            <c:set var="ship" value="${sessionScope.player.ownField.getState(addr)}"/>
                            <td class="${ship} white ">
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </div>

    <div class="my-message-field">
        <textarea id="w3mission" rows="15" cols="30" readonly disabled>
<c:forEach var="h" items="${sessionScope.player.history}">${h}
</c:forEach>
        </textarea>
    </div>

    <div class="enemy-field">
        <form action="/game" method="post">
            <table>
                <tr>
                    <td></td>
                    <c:forEach items="A,B,C,D,E,F,G,H,I,J" var="col">
                        <td>${col}</td>
                    </c:forEach>
                </tr>
                <c:forEach begin="1" end="10" var="row">
                    <tr>
                        <td >${row}</td>
                        <c:forEach items="A,B,C,D,E,F,G,H,I,J" var="col">
                            <c:set var="addr" value="${col}${row}"/>
                            <c:set var="ship" value="${sessionScope.player.enemyField.getState(addr)}"/>
                            <td class="${ship} white">
                                <input type="radio"
                                       name="cell"
                                       value="${addr}"
                                       <c:if test="${sessionScope.player.enemyField.getState(addr) == 'HIT'}">disabled</c:if>
                                       <c:if test="${sessionScope.player.enemyField.getState(addr) == 'MISS'}">disabled</c:if>
                                >
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
            <button class="my-button" type="submit">Fire</button>
        </form>
    </div>

    <div class="my-footer w3-text-white">
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
