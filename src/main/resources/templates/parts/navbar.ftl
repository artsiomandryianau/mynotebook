
<#include "security.ftl">
<#import "login.ftl" as l>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <img src="https://st.depositphotos.com/1637056/2958/i/450/depositphotos_29589345-stock-photo-open-blank-notebook-on-grunge.jpg"
         alt="Note" height="50" width="50"/>
    <a class="navbar-brand" href="/">  Mynotebook</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">

            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">Notes</a>
            </li>
            <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/user">User list</a>
            </li>
        </#if>
        </ul>

        <div class="navbar-text mr-3">${name}</div>
        <@l.logout />

    </div>
</nav>