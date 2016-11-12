
<div id="adminAddUser">
    <form class="addUser">
        <div>
            <p class="errMsg"><%= data.errorMsg %>　</p>
        </div>
        <div>
            <label for="username">账 户：</label>
            <input id="username" name="username" type="text" value="">
        </div>
        <div>
            <label for="password">密 码：</label>
            <input id="password" name="password" type="text" value="">
        </div>
         <div>
              <label for="email">邮 件：</label>
              <input id="email" name="email" type="text" value="">
         </div>
        <div>
            <label for="role">角 色：</label>
            <select name="role" id="role">
                  <option value="0" selected>管理员</option>
                  <option value="1">主管</option>
                  <option value="2">普通用户</option>
            </select>
        </div>
        <div>
            <input class="submit" type="button" value="创建">
        </div>
    </form>
</div>