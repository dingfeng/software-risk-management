<div id="searchUser">
    <table>
        <tr>
            <th>编号</th>
            <th>账号</th>
            <th>邮箱</th>
            <th>角色</th>
        </tr>
        <% _.each(data, function (item) { %>
        <tr>
        <td>
             <td><%= item.id %></td>
            <td><%= item.account %></td>
            <td><%= item.email %></td>
            <td><%= item.role %></td>
        </tr>
        <% }); %>
    </table>
</div>