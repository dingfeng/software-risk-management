<div id="detailProject">
    <form>
        <div class="name">
            <label for="name">项 目 名：</label>
            <input id="name" name="name" type="text" value="<%= data.name %>">
        </div>
        <div class="description">
            <label for="description">描　　述：</label>
            <textarea id="description" name="description" type="text"><%= data.description %></textarea>
        </div>
        <div class="joinedNames">
            <label for="joinedNames">参 与 者：</label>
            <input id="joinedNames" name="joinedNames" readonly="true" type="text" value="<%= data.joinedNames %>">
            <label for="createdBy">创 建 者：</label>
            <input id="createdBy" name="createdBy" readonly="true" type="text" value="<%= data.createdBy %>">
        </div>
        <div class="createdAt">
            <label for="createdAt">创建时间：</label>
            <input id="createdAt" name="createdAt" readonly="true" type="text" value="<%= data.createdAt %>">
            <label for="updatedAt">编辑时间：</label>
            <input id="updatedAt" name="updatedAt" readonly="true" type="text" value="<%= data.updatedAt %>">
        </div>
    </form>
    <div>
        <button class="saveBtn">保存</button>
        <button class="addPersonBtn">邀请</button>
        <button class="addRiskBtn">新建风险</button>
    </div>
    <table>
        <tr>
            <th>编号</th>
            <th>风险描述</th>
            <th>创建人</th>
            <th>创建时间</th>
            <th>编辑时间</th>
            <th>查看详情</th>
        </tr>
        <% _.each(data.riskList.models, function (item) { %>
        <tr>
            <td><%= item.attributes.id %></td>
            <td><%= item.attributes.description %></td>
            <td><%= item.attributes.createdBy %></td>
            <td><%= item.attributes.createdAt %></td>
            <td><%= item.attributes.updatedAt %></td>
            <td><a href="#ordinary/detailProject/<%= data.id %>/detailRisk/<%= item.attributes.id %>">详情</a></td>
        </tr>
        <% }); %>
    </table>
</div>