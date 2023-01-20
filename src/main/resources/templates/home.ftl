<!DOCTYPE html>
<html>
    <head>
        <title>Skeleton Project - Homepage</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/skeleton/skeleton.css">
    </head>
    <body>
        <h1>Skeleton</h1>
        <h2>Our CSD297 Project!</h2>

        <#if loggedIn>
            <a href="?cmd=logout">Log Out</a>
        <#else>
            <a href="?cmd=showLogin">Log In</a><br />
        </#if>
    </body>
</html>
