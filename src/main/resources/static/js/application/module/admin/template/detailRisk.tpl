<div id="detailRisk">
    <div class="description">
        <label for="description">风险描述：</label>
        <textarea id="description" name="description" type="text" value="<%= data.description %>"/>
    </div>
    <div class="projectName">
        <label for="projectName">所属项目：</label>
        <input id="projectName" name="projectName" disabled="true" type="text" value="<%= data.projectName %>">
    </div>
    <div>
        <button class="ok">保存</button>
        <button class="cancel">取消</button>
    </div>
</div>