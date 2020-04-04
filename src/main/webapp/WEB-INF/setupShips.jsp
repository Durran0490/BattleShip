<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 02.04.2020
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
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
            margin-top: 50px;
            padding-top: 40px;
            font-size: 20px;
            text-align: center;
            font-weight: bold;
        }

        .warning-message {
            color: white;
            background-color: red;
            opacity: 50%;
            width: 30%;
            height: auto;
            border: 0px solid darkred;
            padding: 5px;
            margin: auto;
            font-size: 20px;
            font-weight: bold;
        }

        .container {
            display: grid;
            width: 100%;
            height: 100%;
            grid-template-areas: "my-header my-header my-header" "my-title my-title my-title" "left my-center-field right" "my-message-field my-message-field my-message-field" "my-footer my-footer my-footer";
            grid-template-columns: 37% 482px 1fr;
            grid-template-rows: 194px 74px 411px 120px;
        }

        .container > div {
            /*border: 1px dashed #888;*/
        }

        .my-header {
            grid-area: my-header;
        }

        .my-title {
            grid-area: my-title;
        }

        .left {
            grid-area: left;
        }

        .my-center-field {
            grid-area: my-center-field;
            padding-top: 10%;
            padding-left: 20%;
        }

        .right {
            grid-area: right;
        }

        .my-message-field {
            grid-area: my-message-field;
            margin-top: 10px;
        }

        .my-footer {
            grid-area: my-footer;
        }

        .my-button {
            font-weight: bold;
            color: rgb(181, 181, 181);
            margin-top: 20px;
            margin-left: 7%;
            height: 50px;
            border-style: outset;
            border-color: rgb(181, 255, 249);
            background-color: white;
            width: 260px;
        }

        .my-button:active {
            transform: translateY(1px);
            border-style: inset;
            border-color: rgb(181, 255, 249);
        }

        .my-button:hover {
            color: rgb(110, 110, 110);
            box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0 rgba(0, 0, 0, 0.19);
        }

        .regular-checkbox{
            -webkit-appearance: none;
            background-color: #fafafa;
            border: 1px solid #cacece;
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px -15px 10px -12px rgba(0, 0, 0, 0.05);
            padding: 12px;
            margin: -2px -2px;
            border-radius: 3px;
            display: inline-block;
            position: relative;
            outline: none;
        }

        .regular-checkbox:active, .regular-checkbox:checked:active {
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px 1px 3px rgba(0, 0, 0, 0.1);
        }

        .regular-checkbox-after:checked {
            background-color: #03fcb6;
            border: 1px solid #adb8c0;
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px -15px 10px -12px rgba(0, 0, 0, 0.05), inset 15px 10px -12px rgba(255, 255, 255, 0.1);
            color: #99a1a7;
        }

        /*.wrong-placed-checkbox:checked {*/
        /*    background-color: #ff6363;*/
        /*    border: 1px solid #adb8c0;*/
        /*    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px -15px 10px -12px rgba(0, 0, 0, 0.05), inset 15px 10px -12px rgba(255, 255, 255, 0.1);*/
        /*    color: #99a1a7;*/
        /*}*/

        .regular-checkbox:checked:after {
            content: '\25C9';
            font-size: 25px;
            position: absolute;
            top: -3px;
            left: 7%;
            color: #99a1a7;
        }

        td {
            text-align: center;
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
            <p class="text-size-30 w3-center">PLEASE PLACE YOUR SHIPS</p>
        </div>
    </div>
    <div class="my-title w3-center text-size-20 w3-text-white">PLACE YOUR SHIPS HERE</div>
    <div class="left"></div>
    <div class="my-center-field">
        <form action="/setup" method="post">
            <table>
                <tr>
                    <td></td>
                    <td></td>
                    <c:forEach items="A,B,C,D,E,F,G,H,I,J" var="col">
                        <td>${col}</td>
                    </c:forEach>
                </tr>
                <c:forEach begin="1" end="10" var="row">
                    <tr>
                        <td>${row}</td>
                        <td></td>
                        <c:forEach items="A,B,C,D,E,F,G,H,I,J" var="col">
                            <td>
                                <c:set var="addr" value="${col}${row}"/>
                                <input type="checkbox" class="regular-checkbox regular-checkbox-after" name="cells" value="${addr}"
                                <c:if test="${sessionScope.player.ownField.getState(addr) == 'SHIP'}">checked</c:if>>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
            <button type="submit" class="my-button">SUBMIT POSITIONS</button>
        </form>
    </div>
    <div class="right"></div>
    <div class="my-message-field w3-center w3-animate-opacity">
        <c:if test="${not empty requestScope.warningMessage}">
            <p class="warning-message">${requestScope.warningMessage}</p>
        </c:if>
        <p></p>
        <c:if test="${not empty requestScope.wrongPlacedCells}">
            <p class="warning-message">${requestScope.wrongPlacedCells}</p>
        </c:if>
    </div>
    <div class="my-footer w3-text-white">
        <div class="w3-display-bottomleft w3-padding-large">
            Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a>
        </div>
        <div class="w3-display-bottomright w3-padding-large w3-large">
            <a href="https://github.com/Durran0490/BattleShip" target="_blank">GitHub
                <img src="res/GitHub-Mark-Light-32px.png" alt="HTML tutorial">
            </a>
        </div>
    </div>
</div>

</body>
</html>
