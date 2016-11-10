<div id="inaside">
    <% _.each(data, function (item) { %>
        <% if(item.active) { %>
            <div class="item active" name="<%= item.id %>">
                <p><%= item.name %></p>
            </div>
        <% } else { %>
            <div class="item" name="<%= item.id %>">
                <p><%= item.name %></p>
            </div>
        <% } %>
    <% }); %>
</div>