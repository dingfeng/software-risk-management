<div id="createProject">
    <form class="createProject">
        <div>
            <label for="projectName">项目名：</label>
            <input id="projectName" name="projectName" type="text" value="<%= data.projectName %>">
        </div>
        <div>
            <label for="description">项目描述：</label>
            <textarea id="description" name="description" type="text" value="<%= data.description %>"
            ></textarea>
        </div>
        <div>
            <input class="submit" type="button" value="创建">
        </div>
    </form>
</div>