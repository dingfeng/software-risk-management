<div id="searchProject">
    <table>
        <tr>
            <th>编号</th>
            <th>项目名</th>
            <th>描述</th>
            <th>创建人</th>
            <th>创建时间</th>
        </tr>
        <% _.each(data, function (item) { %>
        <tr>
            <td><%= item.id %></td>
            <td><%= item.name %></td>
            <td><%= item.description %></td>
            <td><%= item.createdBy %></td>
            <td><%= item.createdAt %></td>
        </tr>
        <% }); %>
    </table>
</div>