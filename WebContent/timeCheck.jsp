<%--
  Created by IntelliJ IDEA.
  User: 江江江
  Date: 2020/5/31
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" http-equiv="content-type" >
    <title>页面停留时间的统计方法</title>
</head>
<body onbeforeunload="new_page()"  onLoad="init();  window.setTimeout('show_secs()',1);"  onblur="">
<%--onunload="new_page()"  onLoad="init(); window.setTimeout('show_secs()',1);"--%>
<SCRIPT LANGUAGE="JAVASCRIPT">
    var ap_name = navigator.appName;
    var ap_vinfo = navigator.appVersion;
    var ap_ver = parseFloat(ap_vinfo.substring(0,ap_vinfo.indexOf('(')));
    var time_start = new Date();
    var clock_start = time_start.getTime();
    var dl_ok=false;
    function init ()
    {
        if(ap_name=="Netscape" && ap_ver>=3.0)
            dl_ok=true;
        document.getElementById("urll").innerText=sessionStorage.getItem('time');

        return true;
    }
    function get_time_spent ()
    {
        var time_now = new Date();
        return((time_now.getTime() - clock_start)/1000);
    }
    function show_secs ()
    {
        var i_total_secs = Math.round(get_time_spent());
        var i_secs_spent = i_total_secs % 60;
        var i_mins_spent = Math.round((i_total_secs-30)/60);
        var s_secs_spent = "" + ((i_secs_spent>9) ? i_secs_spent : "0" + i_secs_spent);
        var s_mins_spent ="" + ((i_mins_spent>9) ? i_mins_spent : "0" + i_mins_spent);
        document.fm0.time_spent.value = s_mins_spent + ":" + s_secs_spent;
        document.getElementById("ahref").innerHTML=s_mins_spent + ":" + s_secs_spent;
        document.getElementById("ahref").href="UserBrowseLogServlet?time="+s_mins_spent + ":" + s_secs_spent;
        window.setTimeout('show_secs()',1000);
    }




    window.onbeforeunload= new_page();
    function new_page()
    {
        sessionStorage.setItem("time",document.getElementById("time_spent").value);

    }



    function  close() {
        document.getElementById("urll").innerText=document.referrer;


    }

</SCRIPT>
<form name="fm0" onSubmit="0">您在本网页的停留时间:
    <INPUT type="text" name="time_spent" id="time_spent" size=7 onFocus="this.blur()">
</form>
<p id="urll"></p>>
<a  id="ahref" href="">打开页面</a>
<input type="button" onclick="new_page()" value="在新窗口打开s"/>
<input type="button" onclick="replace()" value="跳转后没有后退功能"/>
</body>
</html>
