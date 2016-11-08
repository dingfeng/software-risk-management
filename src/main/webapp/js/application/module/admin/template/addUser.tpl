<div id="adminAddUser">
    <form class="addUser">
        <div>
            <p class="errMsg"><%= errorMsg %>　</p>
        </div>
        <div>
            <label for="username">用户名：</label>
            <input id="username" name="username" type="text" value="<%= username %>"
                   onKeyUp="value=value.replace(/[^0-9a-zA-Z@.]/g,'')">
        </div>
        <div>
            <label for="password">密　码：</label>
            <input id="password" name="password" type="password" value="<%= password %>"
                   onKeyUp="value=value.replace(/[^0-9a-zA-Z]/g,'')">
        </div>
        <div>
            <label for="role">角　色：</label>
            <select name="role" id="role">
                <option value="admin">管理员</option>
                <option value="manager">主管</option>
                <option value="ordinary">普通用户</option>
            </select>
        </div>
    </form>
</div>