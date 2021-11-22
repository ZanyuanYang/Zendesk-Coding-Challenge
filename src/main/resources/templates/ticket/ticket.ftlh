<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Mobile Ticket Viewer</title>
    <style>
        th, td {
            border: 1px solid black;
            padding: 10px;
        }
    </style>
</head>
<body>
    <#assign total_tickets = 0 >
    <#list tickets as ticket>
        <#assign total_tickets = total_tickets + 1 >
    </#list>
    <h1>Mobile Ticket Viewer</h1>
    <h3>Total Tickets: ${total_tickets}</h3>
    <table>
        <tr>
            <th>Subject</th>
            <th>Created Time</th>
            <th>Update Time</th>
        </tr>
        <#list tickets as ticket>
            <tr>
                <td><a href="/singleTickets?ticketId=${ticket.id}">${ticket.subject}</a></td>
                <td>${ticket.created_at}</td>
                <td>${ticket.updated_at}</td>
            </tr>
        </#list>
    </table>
</body>
</html>