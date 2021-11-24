<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "./header.ftlh">
    <title>Single Mobile Ticket Viewer</title>
</head>
<body>
    <div class="container">
        <h1 class="text-center">Single Mobile Ticket Viewer</h1>
        <#if authenticate == false >
            <h2 style="color: red;">Error, Couldn't authenticate you</h2>
            <h2 style="color: red;">Please Enter correct email address or token</h2>
        <#else>
            <div>
                <#if ticketsExistOrNot == true >
                    <h3>Title: ${singleTickets.subject}</h3>
                    <p>${singleTickets.description}</p>
                <#else>
                    <h1>The tickets doesn't exists</h1>
                </#if>
                <a href="/tickets">Back to Previous Page</a>
            </div>
        </#if>
    </div>
</body>
</html>