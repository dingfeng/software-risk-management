<div id="searchProject">
    <table>
        <tr>
            <th>编号</th>
            <th>标题</th>
            <th>可能性</th>
            <th>状态</th>
            <th>影响</th>
            <th>触发器</th>
            <th>文本描述</th>
            <th>创建者</th>
            <th>处理者</th>
            <th>所属项目</th>
            <th>创建时间</th>
            <th>编辑时间</th>
            <th>查看详情</th>
        </tr>
        <% _.each(data, function (item) { %>
        <tr>
            <td><%= item.id %></td>
            <td><%= item.title %></td>
            <td><%= item.possibility %></td>
            <td><%= item.status %></td>
            <td><%= item.influence %></td>
            <td><%= item.trigger %></td>
            <td><%= item.description %></td>
            <td><%= item.createdBy %></td>
            <td><%= item.handler %></td>
            <td><%= item.project %></td>
            <td><%= item.createdAt %></td>
            <td><%= item.updatedAt %></td>
            <td><a href ="#ordinary/detailSingleRisk/<%= item.id %>" >详情</a></td>
        </tr>
        <% }); %>
    </table>
</div>