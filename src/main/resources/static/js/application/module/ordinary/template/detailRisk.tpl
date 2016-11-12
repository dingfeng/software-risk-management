<div id="detailRisk">
    <form>
        <div class="title">
            <label for="title">项 目 名：</label>
            <input id="title" name="title" type="text" value="<%= data.title %>">
        </div>
        <div class="description">
            <label for="description">风险描述：</label>
            <textarea id="description" name="description" type="text" value="<%= data.description %>"/>
        </div>
        <div>
            <label for="projectName">所属项目：</label>
            <input id="projectName" name="projectName" disabled="true" type="text" value="<%= data.project %>">
        </div>
        <div>
            <label for="status">状　　态：</label>
            <input id="status" name="status" type="text" value="<%= data.status %>">
            <label for="influence">影　　响：</label>
            <input id="influence" name="influence" disabled="true" type="text" value="<%= data.influence %>">
        </div>
        <div>
            <label for="possibility">可 能 性：</label>
            <input id="possibility" name="possibility" disabled="true" type="text" value="<%= data.possibility %>">
            <label for="trigger">触 发 器：</label>
            <input id="trigger" name="trigger" type="text" value="<%= data.trigger %>">
        </div>
        <div>
            <label for="handler">处 理 者：</label>
            <input id="handler" name="handler" disabled="true" type="text" value="<%= data.handler %>">
            <label for="author">创 建 者：</label>
            <input id="author" name="author" type="text" value="<%= data.author %>">
        </div>
        <div>
            <label for="updatedAt">编辑时间：</label>
            <input id="updatedAt" name="updatedAt" disabled="true" type="text" value="<%= data.updatedAt %>">
            <label for="createdAt">创建时间：</label>
            <input id="createdAt" name="createdAt" type="text" value="<%= data.createdAt %>">
        </div>
    </form>
    <div>
        <button class="ok">保存</button>
        <button class="cancel">取消</button>
    </div>
</div>