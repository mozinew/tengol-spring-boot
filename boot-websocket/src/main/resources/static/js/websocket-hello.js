var socket;
console.log("开始测试 WebSocket");
if(typeof(WebSocket) == "undefined") {
    console.log("您的浏览器不支持WebSocket");
}else{
    console.log("您的浏览器支持WebSocket");
    //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
    //等同于socket = new WebSocket("ws://localhost:8083/checkcentersys/websocket/20");
    var cid = document.getElementById("id_clientId").value;
    socket = new WebSocket("ws://127.0.0.1:9090/hello/socket/" + cid);
    //打开事件
    socket.onopen = function() {
        console.log("Socket 已打开");
        //socket.send("这是来自客户端的消息" + location.href + new Date());
    };
    //获得消息事件
    socket.onmessage = function(msg) {
        console.log(msg.data);
        //发现消息进入    开始处理前端触发逻辑
    };
    //关闭事件
    socket.onclose = function() {
        console.log("Socket已关闭");
    };
    //发生了错误事件
    socket.onerror = function() {
        alert("Socket发生了错误");
        //此时可以尝试刷新页面
    }
    //离开页面时，关闭socket
    //jquery1.8中已经被废弃，3.0中已经移除
    // $(window).unload(function(){
    //     socket.close();
    //});
}