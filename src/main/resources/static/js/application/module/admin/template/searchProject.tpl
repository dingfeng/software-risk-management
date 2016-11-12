<div id="searchProject">
    <table>
        <tr>
            <th>编号</th>
            <th>项目名</th>
            <th>描述</th>
            <th>创建人</th>
            <th>创建时间</th>
            <th>编辑时间</th>
            <th>删除</th>
        </tr>
        <% _.each(data, function (item) { %>
        <tr>
            <td><%= item.id %></td>
            <td><%= item.name %></td>
            <td><%= item.description %></td>
            <td><%= item.createdBy %></td>
            <td><%= item.createdAt %></td>
            <td><%= item.updatedAt %></td>
            <td><input class="deleteProject" id="<%= item.id%>" type="button" value="删除"></td>
        </tr>
        <% }); %>
    </table>
</div>