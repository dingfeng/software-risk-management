<div id="searchUser">
    <table>
        <tr>
            <th>编号</th>
            <th>账号</th>
            <th>邮箱</th>
            <th>角色</th>
            <th>修改</th>
            <th>删除</th>
        </tr>
        <% _.each(data, function (item) { %>
        <tr>
             <td><%= item.id %></td>
            <td><%= item.account %></td>
            <td><%= item.email %></td>
            <td><%= item.role %></td>
            <td><input class="modify" type="button"  value="修改"></td>
            <th><input class="deleteUser" id="<%= item.id%>" type="button" value="删除"></th>
        </tr>
        <% }); %>
    </table>
</div>