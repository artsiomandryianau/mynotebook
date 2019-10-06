<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<div>
    <@l.logout />
</div>
<div>
    <form method="post" action="main" enctype="multipart/form-data">
        <img src="https://st.depositphotos.com/1637056/2958/i/450/depositphotos_29589345-stock-photo-open-blank-notebook-on-grunge.jpg"
             alt="Note" height="110" width="110"/>

        <textarea rows="5" cols="50"
                  style="font-family:Times New Roman;color:#003399;white-space:pre-wrap" type="text" name="text"
                  placeholder="Enter the note here"></textarea>
        <input type="text" name="tag" placeholder="Tag">
        <input type="file" name="file"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Add</button>
    </form>
</div>

<form method="get" action="/main">
    <input type="text" name="filter" value="${filter?ifExists}">
    <button type="submit">Find by tag</button>
</form>
<form method="post" action="removeNote">
    <input type="text" name="removeNote">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit">Remove note</button>
</form>
<div><h1>List of notes</h1></div>


<#list notes as note>
    <div>
        <h3>
            <b>${note.id}</b>
            <span>${note.text}</span>
            <div>
                <#if note.filename??>
                <img src="/img/${note.filename}">
            </#if>
            </div>
            <h5>
                <p><i>${note.tag}</i>
                <i>${note.date}</i></p>
            </h5>
        </h3>
    </div>
<#else>
No notes
</#list>

</@c.page>