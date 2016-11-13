<div id="createRisk">
   <form>
           <div class="title">
               <label for="title">标　　题：</label>
               <input id="title" name="title" type="text" value="">
           </div>
           <div class="description">
               <label for="description">风险描述：</label>
               <textarea id="description" name="description" type="text" value=""/>
           </div>
           <div>
               <label for="influence">影　　响：</label>
                <select id="influence" name="influence">
                               <option value = "1">高</option>
                               <option value = "2">中</option>
                               <option value = "3">低</option>
                </select>
           </div>
           <div>
               <label for="possibility">可 能 性：</label>
               <select id="possibility" name="possibility">
                   <option value = "1">高</option>
                   <option value = "2">中</option>
                   <option value = "3">低</option>
               </select>
               <label for="trigger">触 发 器：</label>
               <input id="trigger" name="trigger" type="text" value="">
           </div>
           <div>
               <label for="handler">处 理 者：(仅填写一位)</label>
               <input id="handler" name="handler" type="text" value="">
           </div>

       </form>
       <div>
           <button class="ok">新建</button>
           <button class="cancel">取消</button>
       </div>
</div>