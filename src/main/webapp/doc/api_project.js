define({
  "name": "一起踢球接口文档",
  "version": "0.0.1",
  "description": "",
  "title": "一起踢球测试界面",
  "url": "http://4.16.1.158:8080/yqtq_app",
  "sampleUrl": "http://4.16.1.158:8080/yqtq_app",
  "header": {
    "title": "start",
    "content": "<h3>接口返回格式</h3>\n<h2>单条数据:</h2>\n<blockquote>\n<p>异常:</p>\n</blockquote>\n<pre><code class=\"language-javascript\">{ \n    &quot;status&quot;:1,\n    &quot;msg&quot;:&quot;错误编码&quot;,\n    &quot;data&quot;:{}\n}\n</code></pre>\n<blockquote>\n<p>正常:</p>\n</blockquote>\n<pre><code class=\"language-javascript\">{\n    &quot;status&quot;:0,\n    &quot;msg&quot;:&quot;&quot;,\n    &quot;data&quot;:{\n        &quot;id&quot;:&quot;1&quot;,\n        &quot;name&quot;:&quot;wb&quot;\n    }\n}\n</code></pre>\n<h2>多条数据:</h2>\n<blockquote>\n<p>异常:</p>\n</blockquote>\n<pre><code class=\"language-javascript\">{\n    &quot;status&quot;:1,\n    &quot;msg&quot;:&quot;错误编码&quot;,\n    &quot;data&quot;:{}\n}\n</code></pre>\n<blockquote>\n<p>正常:</p>\n</blockquote>\n<pre><code class=\"language-javascript\">{\n    &quot;status&quot;:0,\n    &quot;msg&quot;:&quot;&quot;,\n    &quot;data&quot;:{\n        &quot;page&quot;:{\n            &quot;totalNum&quot;:4,\n            &quot;totalPage&quot;:2,\n            &quot;currentPage&quot;:1\n        },\n        &quot;list&quot;:[\n            {\n                &quot;id&quot;:267,\n                &quot;city&quot;:&quot;Wuhan&quot;\n            },\n            {\n                &quot;id&quot;:266,\n                &quot;city&quot;:&quot;Nanjin&quot;\n            }\n        ]\n    }\n}\n</code></pre>\n<h3>错误码</h3>\n<table>\n<thead>\n<tr>\n<th>解释</th>\n<th>错误码</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td>服务器错误</td>\n<td>error_0001</td>\n</tr>\n<tr>\n<td>参数不正确</td>\n<td>error_0002</td>\n</tr>\n<tr>\n<td>没有数据</td>\n<td>error_0003</td>\n</tr>\n<tr>\n<td>验证码不正确</td>\n<td>error_0004</td>\n</tr>\n<tr>\n<td>验证码类型不正确</td>\n<td>error_0005</td>\n</tr>\n<tr>\n<td>手机号码已存在</td>\n<td>error_0006</td>\n</tr>\n<tr>\n<td>已经关注过</td>\n<td>error_0007</td>\n</tr>\n<tr>\n<td>没有关注过</td>\n<td>error_0008</td>\n</tr>\n<tr>\n<td>已经收藏过</td>\n<td>error_0009</td>\n</tr>\n<tr>\n<td>没有收藏过</td>\n<td>error_0010</td>\n</tr>\n<tr>\n<td>不能转发自己的日志</td>\n<td>error_0011</td>\n</tr>\n<tr>\n<td>已转发过的日志不能重复转发</td>\n<td>error_0012</td>\n</tr>\n<tr>\n<td>用户被禁用，无法登录</td>\n<td>error_0013</td>\n</tr>\n<tr>\n<td>验证码超时</td>\n<td>error_0014</td>\n</tr>\n<tr>\n<td>用户不存在</td>\n<td>error_0015</td>\n</tr>\n<tr>\n<td>优惠券不存在</td>\n<td>error_0016</td>\n</tr>\n<tr>\n<td>已经领取过优惠券</td>\n<td>error_0017</td>\n</tr>\n<tr>\n<td>手机号码不存在</td>\n<td>error_0018</td>\n</tr>\n<tr>\n<td>手机号或密码错误</td>\n<td>error_0019</td>\n</tr>\n<tr>\n<td>已经点赞过</td>\n<td>error_0020</td>\n</tr>\n<tr>\n<td>没有点赞过</td>\n<td>error_0021</td>\n</tr>\n<tr>\n<td>订单不存在</td>\n<td>error_0022</td>\n</tr>\n<tr>\n<td>收货地址不存在</td>\n<td>error_0023</td>\n</tr>\n<tr>\n<td>收货地址不属于该用户</td>\n<td>error_0024</td>\n</tr>\n<tr>\n<td>已经预约过</td>\n<td>error_0025</td>\n</tr>\n<tr>\n<td>无效的优惠券</td>\n<td>error_0026</td>\n</tr>\n</tbody>\n</table>\n"
  },
  "template": {
    "forceLanguage": "zh",
    "withCompare": true,
    "withGenerator": true
  },
  "apidoc": "0.2.0",
  "generator": {
    "name": "apidoc",
    "time": "2016-08-05T09:36:10.936Z",
    "url": "http://apidocjs.com",
    "version": "0.16.1"
  }
});
