<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>
<%@ taglib uri="http://www.zkoss.org/dsp/zk/core" prefix="z" %>
<%@ taglib uri="http://www.zkoss.org/dsp/web/theme" prefix="t" %>

body{overflow-x:hidden;margin-top:-9px;padding:0!important}

#tooltip{position:absolute;display:none;border:0;padding:3px 8px;border-radius:3px;font-size:10px;background-color:#222;color:#fff;z-index:25}

a:hover,a:active,a:visited,a:focus{text-decoration:none;cursor:pointer;color:#333}

.header{height:80px;position:relative}
.header h1{padding-left:70px;left:15px;overflow:hidden;position:relative;top:20px;width:191px;background:url(${c:encodeURL('../img/top_zk_logo.png')}) no-repeat transparent;opacity:.7}
.header h1 a{color:#adadad;text-shadow:0 1px #fff;font-size:40px;display:block;font-weight:normal;line-height:54px}

.search{position:absolute;z-index:20;top:6px;left:230px}

.user-nav{position:absolute;right:30px;top:5px;z-index:20;margin:0}

.sidebar{display:block;float:left;position:relative;width:220px;z-index:16}

.content{background:#eee;margin-left:220px;margin-right:0;padding-bottom:25px;position:relative;min-height:500px;width:auto;-webkit-background-clip:padding-box;border-top-left-radius:8px;z-index:20}
.content-header{${t:gradient('ver','#ffffff 0%; #eeeeee 100%') };border-top-left-radius:8px;min-height:80px;padding-top:5px;width:100%;margin-top:-38px;z-index:20}
.content-header h1{color:#555;font-size:28px;font-weight:normal;text-shadow:0 1px 0 #fff;margin-left:20px;margin-top:20px}
.content-header .btn-group{float:right;right:20px;position:absolute;margin-top:-50px}

.breadcrumb{background-color:#e5e5e5;box-shadow:0 0 1px #fff;border-top:1px solid #d6d6d6;border-bottom:1px solid #d6d6d6;padding-left:10px;padding:0}
.breadcrumb a{padding:8px 20px 8px 10px;display:inline-block;font-size:11px;color:#666}
.breadcrumb a i{opacity:.6;margin-right:6px;vertical-align:text-bottom}
.breadcrumb a:hover i{opacity:1}
.breadcrumb a:after{content:"\f054";display:inline-block;font-family:FontAwesome;font-weight:normal;font-style:normal;text-decoration:inherit;-webkit-font-smoothing:antialiased;margin:0 -20px 0 13px}
.breadcrumb a:last-child:after{display:none}

.style-switcher{position:absolute;width:250px;height:30px;background-color:#000;z-index:30;right:0;top:129px;border-radius:5px 0 0 5px;margin-right:-220px}
.style-switcher i{display:inline-block;margin:-4px 10px 0 10px;cursor:pointer}
.style-switcher span{font-weight:bold;color:#fff;display:inline-block;margin:-15px 20px 0 0;vertical-align:middle}
.style-switcher a{display:inline-block;width:20px;height:20px;margin-top:4px;border-style:solid;border-width:1px;border-color:transparent}