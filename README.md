# Zendesk Intern Coding Challenge

## Installation
- Open terminal, and type `git clone https://github.com/ZanyuanYang/Zendesk-Coding-Challenge.git` to download the code
- Use `IntelliJ IDEA` to open the project just clone
- Click `Run` button to run the project ![img.png](img.png)
- After seeing below message in console, this means the project is running successfully ![img_1.png](img_1.png)
- Open the Browser(`Chrome/Safari/Firefox/etc`)
- Type `http://localhost:8080/tickets` to view all tickets
- Click the subject link or type `http://localhost:8080/singleTickets?ticketId=1` to view the single tickets(If the `tickets(tickerId)` doesn't exist, it will show the error message).

## For Tester
If Tester want to use their own account to test the project, Tester can navigate to [GlobalConst.java](./src/main/java/com/example/zendesk/GlobalConst.java) to change `URL` and `AUTH`.
- `URL` format: `https://{subdomain}.zendesk.com/api/v2/tickets.json`
- `AUTH` format: `{email_address}/token:{api_token}`

## Technique
- SpringBoot
- FreeMarker