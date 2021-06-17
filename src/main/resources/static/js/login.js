

$(function  () {
    var show_num = [];
    draw(show_num);

    $("#canvas").on('click',function(){
        draw(show_num);
    })
    layui.use('form', function(){
        var form = layui.form;
        // layer.msg('玩命卖萌中', function(){
        //   //关闭后的操作
        //   });
        //监听提交
        form.on('submit(np_login)', function(data){
            var val = $(".input-val").val().toLowerCase();
            var num = show_num.join("");
            if(val==''){
                alert('请输入验证码！');
            }else if(val == num){
                $(".input-val").val('');
                draw(show_num);

                $.ajax({
                    type: "post",
                    url: "/later/login",
                    data: {"username": data.field.username, "password": data.field.password},
                    success: function (data) {
                        if (data.state === 200) {
                            //非首次加载 do something
                            window.location.href = "/later/goindex"
                        }
                        layer.msg(data.msg);
                    }
                });

            }else{
                alert('验证码错误！请重新输入！');
                $(".input-val").val('');
                draw(show_num);
            }

            return false;
        });

        //手机号登录
        form.on('submit(sj_login)', function(data){


            $.ajax({
                type: "post",
                url: "/later/login/message",
                data: {"phone": data.field.phone, "verify": data.field.verify},
                success: function (data) {
                    if (data.state === 200) {
                        //非首次加载 do something
                        window.location.href = "/later/goindex"
                    }
                    layer.msg(data.msg);
                }
            });
            return false;
        });






    });
});


//百度统计可去掉
/*var _hmt = _hmt || [];
(function() {
    var hm = document.createElement("script");
    hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(hm, s);
})*/



<!--验证码-->

function draw(show_num) {
    var canvas_width=$('#canvas').width();
    var canvas_height=$('#canvas').height();
    var canvas = document.getElementById("canvas");//获取到canvas的对象，演员
    var context = canvas.getContext("2d");//获取到canvas画图的环境，演员表演的舞台
    canvas.width = canvas_width;
    canvas.height = canvas_height;
    var sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
    var aCode = sCode.split(",");
    var aLength = aCode.length;//获取到数组的长度

    for (var i = 0; i <= 3; i++) {
        var j = Math.floor(Math.random() * aLength);//获取到随机的索引值
        var deg = Math.random() * 30 * Math.PI / 180;//产生0~30之间的随机弧度
        var txt = aCode[j];//得到随机的一个内容
        show_num[i] = txt.toLowerCase();
        var x = 10 + i * 20;//文字在canvas上的x坐标
        var y = 26 + Math.random() * 8;//文字在canvas上的y坐标
        context.font = "bold 23px 微软雅黑";

        context.translate(x, y);
        context.rotate(deg);

        context.fillStyle = randomColor();
        context.fillText(txt, 0, 0);

        context.rotate(-deg);
        context.translate(-x, -y);
    }
    for (var i = 0; i <= 5; i++) { //验证码上显示线条
        context.strokeStyle = randomColor();
        context.beginPath();
        context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
        context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
        context.stroke();
    }
    for (var i = 0; i <= 30; i++) { //验证码上显示小点
        context.strokeStyle = randomColor();
        context.beginPath();
        var x = Math.random() * canvas_width;
        var y = Math.random() * canvas_height;
        context.moveTo(x, y);
        context.lineTo(x + 1, y + 1);
        context.stroke();
    }
}

function randomColor() {//得到随机的颜色值
    var r = Math.floor(Math.random() * 256);
    var g = Math.floor(Math.random() * 256);
    var b = Math.floor(Math.random() * 256);
    return "rgb(" + r + "," + g + "," + b + ")";
}


//切换登录方式
function js_alert(a) {//得到随机的颜色值
    if(a==1){
        $("#np_login").hide();
        $("#sj_login").show();
    }else if(a==2){
        $("#np_login").show();
        $("#sj_login").hide();;
    }

}

//获取验证码信息
function getyam(){
    var phone=$("#yz_phone").val();
    if(phone!=""&&phone!=null){
        //验证手机号格式
        if (!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(phone))) {
            layer.msg("手机号码有误，请重填");
        }else {
            $.ajax({
                type: "post",
                url: "/later/short/message",
                data: {"phone": phone},
                success: function (data) {
                    if (data.state != 203) {
                        //60秒后重置
                        daojishi(60);
                    }
                    layer.msg(data.msg);
                }
            });
        }

    }else {
        layer.msg("不能为空或格式不正确");
    }

}

//倒计时
function daojishi(seconds){
    if (seconds > 1){
        seconds--;
        $("#yzm").val(seconds+"秒后重新获取 ").attr("disabled", true);//禁用按钮
        // 定时1秒调用一次
        setTimeout(function(){
            daojishi(seconds);
        },1000);
    }else{
        $("#yzm").val("获取验证码").attr("disabled", false);//启用按钮
    }

}
