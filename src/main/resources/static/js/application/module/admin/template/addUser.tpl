<div id="adminAddUser">
    <form class="addUser">
        <div>
            <p class="errMsg"><%= data.errorMsg %>　</p>
        </div>
        <div>
            <label for="username">账户：</label>
            <input id="username" name="username" type="text" value="">
        </div>
        <div>
            <label for="password">密码：</label>
            <input id="password" name="password" type="text" value="">
        </div>
         <div>
              <label for="email">邮件：</label>
              <input id="email" name="email" type="text" value="">
         </div>
        <div>
            <label for="role">角　色：</label>
            <select name="role" id="role">
                  <option value="admin" selected>管理员</option>
                  <option value="manager">主管</option>
                  <option value="ordinary">普通用户</option>
            </select>
        </div>
        <div>
            <input class="submit" type="button" value="创建">
        </div>
    </form>
</div>