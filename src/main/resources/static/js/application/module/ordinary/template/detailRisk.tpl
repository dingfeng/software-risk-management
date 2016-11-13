<div id="detailRisk">
    <form>
        <div class="title">
            <label for="title">标　　题：</label>
            <input id="title" disabled="true" name="title" type="text" value="<%= data.title %>">
        </div>
        <div class="description">
            <label for="description">风险描述：</label>
            <input id="description" name="description" type="text" value="<%= data.description %>"/>
        </div>
        <div>
            <label for="projectName">所属项目：</label>
            <input id="projectName" name="projectName" disabled="true" type="text" value="<%= data.project %>">
        </div>
        <div>
            <label for="status">状　　态：</label>
            <select id="status" name ="status" >
            <% if(data.status == "处理完毕") { %>
                <option value ="1" > 正在处理</option>
                <option value ="2" selected >处理完毕</option>
            <% } else { %>
                <option value ="1" selected > 正在处理</option>
                <option value ="2"  >处理完毕</option>
            <% } %>
            </select>

            <label for="influence">影　　响：</label>
            <input id="influence" name="influence" disabled="true" type="text" value="<%= data.influence %>">
        </div>
        <div>
            <label for="possibility">可 能 性：</label>
            <input id="possibility" name="possibility" disabled="true" type="text" value="<%= data.possibility %>">
            <label for="trigger">触 发 器：</label>
            <input id="trigger" name="trigger" type="text" value="<%= data.trigger %>" disabled="true">
        </div>
        <div>
            <label for="handler">处 理 者：</label>
            <input id="handler" name="handler" disabled="true" type="text" value="<%= data.handler %>">
            <label for="author">创 建 者：</label>
            <input id="author" disabled="true" name="author" type="text" value="<%= data.createdBy %>">
        </div>
        <div>
            <label for="updatedAt">编辑时间：</label>
            <input id="updatedAt" name="updatedAt" disabled="true" type="text" value="<%= data.updatedAt %>">
            <label for="createdAt">创建时间：</label>
            <input id="createdAt" disabled="true" name="createdAt" type="text" value="<%= data.createdAt %>">
        </div>
    </form>
    <div>
        <button class="ok">保存</button>
        <button class="cancel">取消</button>
    </div>
</div>