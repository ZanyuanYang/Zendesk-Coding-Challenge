<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Single Mobile Ticket Viewer</title>
</head>
<body>
<#--    <h1>${singleTickets.id}</h1>-->
    <h1>Single Mobile Ticket Viewer</h1>
    <div>
        <#if ticketsExistOrNot == true >
            <h2>Title: ${singleTickets.subject}</h2>
            <p>${singleTickets.description}</p>
        <#else>
            <h1>The tickets doesn't exists</h1>
        </#if>
        <a href="/tickets">Back to Previous Page</a>
    </div>
</body>
</html>