<div id="adminAddUser">
    <form class="addUser">
        <div>
            <p class="errMsg"><%= data.errorMsg %>　</p>
        </div>
        <div>
            <label for="username">项目名：</label>
            <input id="username" name="projectName" type="text" value=""
                   onKeyUp="value=value.replace(/[^0-9a-zA-Z@.]/g,'')">
        </div>
        <div>
            <label for="password">项目描述：</label>
            <textarea id="password" name="description" type="description" value=""
                   onKeyUp="value=value.replace(/[^0-9a-zA-Z]/g,'')"></textarea>
        </div>
        <!--
        <div>
            <label for="role">角　色：</label>
            <select name="role" id="role">
                <% if(data.role=="admin") { %>
                    <option value="admin" selected>管理员</option>
                    <option value="manager">主管</option>
                    <option value="ordinary">普通用户</option>
                <% } else if(data.role=="manager") { %>
                    <option value="admin">管理员</option>
                    <option value="manager" selected>主管</option>
                    <option value="ordinary">普通用户</option>
                <% } else if(data.role=="ordinary") { %>
                    <option value="admin">管理员</option>
                    <option value="manager">主管</option>
                    <option value="ordinary" selected>普通用户</option>
                <% } else { %>
                    <option value="admin">管理员</option>
                    <option value="manager">主管</option>
                    <option value="ordinary">普通用户</option>
                <% } %>
            </select>
        </div>
        -->
        <div>
            <input class="submit" type="button" value="创建">
        </div>
    </form>
</div>