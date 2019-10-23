<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<form method="get" action="/main">
    <input type="text" name="filter" value="${filter?ifExists}">
    <button type="submit" class="btn btn-primary">Find by tag</button>
</form>
<form method="post" action="removeNote">
    <input type="text" name="removeNote">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit" class="btn btn-primary">Remove note</button>
</form>
<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Add new Note
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <textarea rows="5" cols="50"
                      style="font-family:Times New Roman;color:#003399;white-space:pre-wrap" class="form-control" type="text" name="text"
                      placeholder="Enter the note here"></textarea>
            <div class="form-group">
                <input type="text" class="form-control" name="tag" placeholder="Tag">
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="filename" id="file">
                    <label class="custom-file-label" for="file">Choose file</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Add</button>
            </div>
        </form>
    </div>
</div>






<div class="card-columns">
    <#list notes as note>
    <div class="card my-3">
        <#if note.filename??>
        <img src="/img/${note.filename}" class="card-img-top">
    </#if>
    <div class="m-2">
        <span>${note.text}</span>
        <i>${note.tag}</i>
    </div>
    <div class="card-footer text-muted">
        ${note.id}
        ${note.authorName}
        ${note.date}
    </div>
</div>
<#else>
No message
</#list>
</div>


</@c.page>