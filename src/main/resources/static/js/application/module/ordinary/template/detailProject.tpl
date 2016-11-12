<div id="detailProject">
    <div class="name">
        <label for="name">项目名：</label>
        <input id="name" name="name" type="text" value="<%= data.name %>">
    </div>
    <div class="description">
        <label for="description">描　述：</label>
        <textarea id="description" name="description" type="text" value="<%= data.description %>"/>
    </div>
    <div class="joinedNames">
        <label for="joinedNames">参与者：</label>
        <input id="joinedNames" name="joinedNames" disabled="true" type="text" value="<%= data.joinedNames %>">
    </div>
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
            <th>修改</th>
        </tr>
        <% _.each(data.riskList, function (item) { %>
        <tr>
            <td><%= item.id %></td>
            <td><%= item.description %></td>
            <td><%= item.createdBy %></td>
            <td><%= item.createdAt %></td>
            <td><%= item.updatedAt %></td>
            <td><a href="#">修改</a></td>
        </tr>
        <% }); %>
    </table>
</div>