<form class="login">
    <p class="errorMsg"><%= data.errorMsg %>　</p>
    <h2>项目风险管理系统--登录</h2>
    <div>
        <label for="username">用户名：</label>
        <input id="username" name="username" type="text" value="<%= data.username %>">
    </div>
    <div>
        <label for="password">密　码：</label>
        <input id="password" name="password" type="password" value="<%= data.password %>">
    </div>
    <div>
        <input id="login" type="button" value="登录">
    </div>
</form>