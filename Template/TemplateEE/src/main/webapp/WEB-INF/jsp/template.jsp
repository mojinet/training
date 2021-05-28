<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <%-- META --%>
        <jsp:include page="/page/include/meta.html"/>
        <title>${ pageTitle }</title>
    </head>
    <body>
        <main class="l_main">
            <%-- HEADER --%>
            <jsp:include page="/page/include/header.html"/>

            <%-- MAIN CONTENT --%>
            <div class="l_main_content container">
                <%-- Include main --%>
                <jsp:include page="${ pageContent }"/>

            </div>

            <%-- FOOTER --%>
            <jsp:include page="/page/include/footer.html"/>
        </main>
    </body>
</html>