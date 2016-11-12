<div id="createRisk">
    <form>
        <div class="title">
            <label for="title">标　　题：</label>
            <textarea id="title" name="title" type="text" value="<%= data.title %>"/>
        </div>
        <div class="description">
            <label for="description">风险描述：</label>
            <textarea id="description" name="description" type="text" value="<%= data.description %>"/>
        </div>
        <div class="project">
            <label for="project">所属项目：</label>
            <input id="project" name="projectName" readonly="true" type="text" value="<%= data.project %>">
        </div>
    </form>
    <div>
        <button class="ok">创建</button>
        <button class="cancel">取消</button>
    </div>
</div>