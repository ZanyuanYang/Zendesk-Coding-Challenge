<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "./header.ftlh">
    <title>Mobile Ticket Viewer</title>
    <style>
        th, td {
            border: 1px solid black;
            padding: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <#assign total_tickets = 0 >
        <#list tickets as ticket>
            <#assign total_tickets = total_tickets + 1 >
        </#list>
        <h1 class="text-center">Mobile Ticket Viewer</h1>
        <h3>Total Tickets: ${total_tickets}</h3>
        <#include "./pagination.ftlh">

        <#--  table start-->
        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-responsive-xl">
                    <thead class="thead-dark">
                    <tr class="table-info  table-hover text-center">
                        <th>id</th>
                        <th>Subject</th>
                        <th>Created Time</th>
                        <th>Update Time</th>
                    </tr>
                    </thead>
                    <tbody id="loop">
                    <#list tickets as ticket>
                        <tr class="list-group_name" id="list-group_name">
                            <td>${ticket.id}</td>
                            <td><a href="/singleTickets?ticketId=${ticket.id}">${ticket.subject}</a></td>
                            <td>${ticket.created_at}</td>
                            <td>${ticket.updated_at}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
        <#--  table end-->
    </div>




    <script type="text/javascript" src="${path}/js/pagination.js"></script>
</body>
</html>