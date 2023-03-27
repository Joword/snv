import $ from 'jquery';
import Vue from 'vue';

var vues = new Vue();
window.$vue = {};
var globalShadeTime = 5000;
var currentShade = null;
//全局配置
$.ajaxSetup({
	dataType: "json",
	cache: false,
});

var Class_utils = (function () {
	function create() {
		var obj = arguments[0];
		return obj || undefined
	}

	return {
		create: create
	}
})();

//工具集合Tools
var Utils = Class_utils.create({
	shade: 0.3,
	alert: function (msg, callback) {
	},
	info: function (msg, callback, options) {
		var setting = {
			message: msg,
			type: 'info',
			duration: globalShadeTime,
			onClose: callback
		}
		if (options && typeof options === 'object') $.extend(true, setting, options)
		vues.$message(setting);
	},
	warn: function (msg, callback, options) {
		var setting = {
			message: msg,
			type: 'warning',
			duration: globalShadeTime,
			onClose: callback
		}
		if (options && typeof options === 'object') $.extend(true, setting, options)
		vues.$message(setting);
	},
	error: function (msg, callback, options) {
		var setting = {
			message: msg,
			type: 'error',
			duration: globalShadeTime,
			onClose: callback
		}
		if (options && typeof options === 'object') $.extend(true, setting, options)
		vues.$message(setting);
	},
	success: function (msg, callback, options) {
		var setting = {
			message: msg,
			type: 'success',
			duration: globalShadeTime,
			onClose: callback
		}
		if (options && typeof options === 'object') $.extend(true, setting, options)
		vues.$message(setting);
	},
	confirm: function (msg, success, fail, options) {
		var setting = {
			message: msg,
			confirmButtonText: '确定',
			cancelButtonText: '取消',
			type: 'warning',
			modal: false
		}
		if (options && typeof options === 'object') $.extend(true, setting, options)
		vues.$confirm(msg, setting).then(success).catch(fail);
	},
	getYOffset: function (height) {
		return (($(top.window).height() - (height ? height : 0)) / 2 - 105) + "px";
	},
	clearUploadFile: function (upload) {
		if (!upload) return;
		var uploadFiles = upload.uploadFiles;
		for (var i in uploadFiles) {
			uploadFiles[i].status = 'ready';
		}
	},
	ajaxHandler: function (options, callback) {     /*callback  回调方法*/   /*options  参数*/
		var defaults = {
			data: {},
			method: 'POST',
			async: true,
			contentType: "application/json",
			dataType: 'json',
			mask: true,
			errorHandler: null,
			trimNull: true,
		};
		var settings = $.extend({}, defaults, options);   /*如果有相同的属性 会覆盖前面的值*/
		var vUrl = settings.url;    /*获得url*/

		if (vUrl.indexOf("?") !== -1) {  //查看当前url是否携带参数，  拼接当前时间
			vUrl += "&_timestamp=" + new Date().getTime();
		} else {
			vUrl += "?_timestamp=" + new Date().getTime();
		}
		settings.url = vUrl;   //覆盖旧的url
		if (settings["trimNull"]) {
			if (typeof settings["data"] == "object") {
				settings["data"] = Utils.trimObj(settings["data"]);
			} else if (typeof settings["data"] == "string") {
				var data = JSON.parse(settings["data"]);
				settings["data"] = JSON.stringify(Utils.trimObj(data));
			}
		}
		Utils.showLoadingShade();
		var request = $.ajax(settings);
		request.always(function () {
			Utils.closeLoadingShade();
		}).done(function (data) {
			if (!(data.code === 0)) {
				if (typeof settings["errorHandler"] === 'function') {
					settings["errorHandler"](data);
				} else {
					Utils.error("操作失败：" + data.msg);
				}
			} else {
				//重新登录
				if (data["_needLogin_"]) {
					var win = window;
					while (win != win.top) {
						win = win.top;
					}
					//将后端重定向的地址取出来,使用win.location.href去实现重定向的要求
					win.location.href = data["_loginUrl_"];
					return;
				}
				if (typeof (callback) === "function") {
					callback(data);
				}
			}
		}).fail(function (error) {
			Utils.error("系统异常！");
		}).always(function () {

		});
		return request;
	},
	/**
	 * 数据形式
	 * @param name
	 * @param extra 扩展信息
	 * @param locale
	 * @returns {Array}
	 */
	getSysProps: function (name, extra, locale) {
		var arrays = [];
		var prop = this.getGridSysPropsObject(name, locale ? locale : extra);
		if (prop) {
			for (var key in prop) {
				var tmp = { key: isNaN(key) ? key : Number(key), value: prop[key] };
				arrays.push(tmp);
			}
		}
		arrays = _.sortBy(arrays, "key");

		if (Array.isArray(extra) && extra.length > 0) {
			if (extra[0] && extra[0]["after"]) {
				delete extra[0]["after"];
				arrays = arrays.concat(extra);
			} else {
				arrays = extra.concat(arrays);
			}
		} else if (extra && typeof extra == 'object') {
			if (extra["after"]) {
				arrays.push(extra);
				delete extra["after"];
			} else {
				arrays.unshift(extra);
			}
		}
		return arrays;
	},
	/**
	 * 字符串形式
	 * @param name
	 * @param locale
	 * @returns {string}
	 */
	getGridSysPropsStr: function (name, locale) {
		var result = "";
		var prop = this.getSysProps(name, locale);

		var array = _.map(prop, function (item) {
			return item["key"] + ":" + item["value"];
		});
		return array.join(";");
	},
	getConfigProp: function (name) {
		var props = top.configProps;
		if (!props) props = eval('(' + localStorage.getItem("configProps") + ')');
		return props[name];
	},
	/**
	 * 对象形式
	 * @param name
	 * @param locale
	 * @returns {*|null}
	 */
	getGridSysPropsObject: function (name, locale) {
		var props = top.sys_cn_props;
		if (!props) {
			props = eval('(' + localStorage.getItem("sys_cn_props") + ')');
		}
		if (locale) {
			if (locale == "cn") {
				props = top.sys_cn_props;
				if (!props) {

					props = eval('(' + localStorage.getItem("sys_cn_props") + ')');
				}
			} else if (locale == "en") {
				props = top.sys_en_props;
				if (!props) {
					props = eval('(' + localStorage.getItem("sys_en_props") + ')');
				}
			} else if (locale == "tra") {
				props = top.sys_tra_props;
				if (!props) {
					props = eval('(' + localStorage.getItem("sys_tra_props") + ')');
				}
			}
		}
		return (props && props[name]) || null;
	},
	/**
	 * 对象形式（反转模式）
	 * @param name
	 * @param locale
	 * @returns {{}}
	 */
	getReverseSysPropsObject: function (name, locale) {
		var ret = {}
		var obj = Utils.getGridSysPropsObject(name, locale);
		for (name in obj) {
			ret[obj[name]] = name;
		}
		return ret;
	},
	/**
	 * key -> value
	 * @param name
	 * @param value
	 * @param locale
	 * @returns {*}
	 */
	propsValueTransfer: function (name, value, defVal, locale) {
		if (Array.isArray(name)) {
			var target = _.find(name, function (num) {
				return num["key"] == value;
			});
			return target ? target["value"] : (typeof defVal !== 'undefined' ? defVal : '');
		}
		var props = this.getGridSysPropsObject(name, locale);
		if (props && value) {
			return props[value] ? props[value] : (typeof defVal !== 'undefined' ? defVal : '');
		}
		return '';
	},
	/**
	 * value -> key
	 * @param name
	 * @param value
	 * @param locale
	 */
	propsReverseValueTransfer: function (name, value, locale) {
		if (Array.isArray(name)) {
			var target = _.find(name, function (num) {
				return num["value"] == value;
			});
			return target ? target["key"] : "-";
		}
		var props = this.getReverseSysPropsObject(name, locale);
		if (props) {
			return props[value] || "";
		}
		return "";
	},
	//判断是否为空
	isBlank: function (value) {
		return !value || !/\S/.test(value)
	},
	//判断是否为空
	isNotBlank: function (value) {
		return value && /\S/.test(value);
	},

	redirectUrl: function (url, params) {
		var s = "";
		for (var i in params) {
			s = s + i + "=" + params[i] + "&";
		}
		s = s.substring(0, s.length - 1);
		var encode = window.btoa(window.encodeURIComponent(s));
		parent.location.href = url + "?" + encode;
	},
	// 获取请求参数
	// 使用示例
	// location.href = http://localhost:8080/index.html?id=123
	// Utils.getQueryString('id') --> 123;
	getEncodeQueryString: function (name, defVal) {
		var reqStr = window.decodeURIComponent(window.atob(window.location.search.substr(1)));
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = reqStr.match(reg);
		if (r != null) return decodeURIComponent(r[2]);
		return defVal ? defVal : null;
	},
	getQueryString: function (name, defVal) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) return decodeURIComponent(r[2]);
		return defVal ? defVal : null;
	},
	removeUrlParameters: function (url, name) {
		var firstSplitUrls;
		if (url.indexOf("#") > 0) {
			firstSplitUrls = url.split("#");
			url = firstSplitUrls[0];
		}
		var pathname = window.location.pathname;
		var search = window.location.search.substr(1);

		if (url) {
			var urlArr = url.split("?");
			if (urlArr.length == 2) {
				pathname = urlArr[0];
				search = urlArr[1];
			} else {
				pathname = url;
				search = "";
			}

		}

		if (this.isBlank(search)) {
			return pathname;
		}

		if (Array.isArray(name)) {
			$.each(name, function (i, item) {
				var reg = new RegExp("(^|&)" + item + "=([^&]*)(&|$)");
				var r = search.match(reg);
				if (r) {
					var target = r[1] + item + "=" + r[2];
					search = search.replace(target, "");
				}
			});
		} else {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			var r = search.match(reg);
			if (r) {
				var target = r[1] + name + "=" + r[2];
				search = search.replace(target, "");
			}

		}
		return pathname + (this.isBlank(search) ? "" : "?" + search) + (firstSplitUrls ? "#" + firstSplitUrls[1] : "");
	},
	setUrlParameter: function (url, name, value) {
		var firstSplitUrls;
		url = this.removeUrlParameters(url, name);
		if (url.indexOf("#") > 0) {
			firstSplitUrls = url.split("#");
			url = firstSplitUrls[0];
		}
		if (url.indexOf("?") < 0) {
			url = url + "?" + name + "=" + value;
		} else {
			url = url + "&" + name + "=" + value;
		}
		return url;
	},
	/**
	 * 保留#号
	 * @param url
	 * @param name
	 * @param value
	 * @returns {string}
	 */
	setUrlParameterHashtag: function (url, name, value) {
		var firstSplitUrls;
		url = this.removeUrlParameters(url, name);
		if (url.indexOf("#") > 0) {
			firstSplitUrls = url.split("#");
			url = firstSplitUrls[0];
		}
		if (url.indexOf("?") < 0) {
			url = url + "?" + name + "=" + value;
		} else {
			url = url + "&" + name + "=" + value;
		}
		return url + (firstSplitUrls ? "#" + firstSplitUrls[1] : "");
	},
	parseObj: function (str) {
		return eval('(' + str + ')');
	},

	/** [getBrowserInfo 获取浏览器信息] */
	getBrowserInfo: function () {
		var Sys = {};
		var ua = navigator.userAgent.toLowerCase();
		var re = /(edge|msie|firefox|chrome|opera|version).*?([\d.]+)/;
		var m = ua.match(re) || ['unKnow', 'unKnow', 'unKnow'];
		Sys.browser = m[1].replace(/version/, "'safari");
		Sys.ver = m[2];
		return Sys;
	},
	/**
	 *
	 * 去除字符串前后空格(undefinded返回空字符)
	 *
	 * @param 要去除前后空格的字符串
	 *
	 */
	trimObj: function (target, excludeArr, includeArr) {
		var ret = "";
		if (Array.isArray(target)) {
			ret = target;
			$.each(target, function (i, item) {
				Utils.trimObj(item, excludeArr, includeArr);
			});
		} else if (target && typeof target === 'object') {
			ret = target;
			for (key in target) {
				if (target[key] && typeof target[key] === 'object') {
					Utils.trimObj(target[key], excludeArr, includeArr);
				} else if (target[key] && typeof target[key] === 'boolean') {
					continue;
				} else if (target[key]) {
					if (excludeArr || includeArr) {
						var clearflag = false;
						if (excludeArr) {
							var findRet = _.find(excludeArr, function (item) {
								return item == key;
							});
							if (!findRet) {
								target[key] = $.trim(target[key]);
								clearflag = true;
							}
						}

						if (!clearflag && includeArr) {
							var findRet = _.find(includeArr, function (item) {
								return item == key;
							});
							if (findRet) {
								target[key] = $.trim(target[key]);
							}
						}
					} else {
						target[key] = $.trim(target[key]);
					}
				}
			}
		} else if (target || target == "0" || target == 0) {
			ret = $.trim(target);
		}


		return ret;
	},
	/**
	 * 消除字符中所有空格
	 * @param data
	 * @param includeArr
	 */
	clearWhiteSpace: function (data, includeArr) {
		if (Array.isArray(data)) {
			$.each(data, function (i, item) {
				for (name in item) {
					if (item[name] && typeof item[name] === 'object') {
						Utils.clearWhiteSpace(item[name], includeArr);
					} else {
						var i = !includeArr ? 1 : _.find(includeArr, function (key) {
							return key == name;
						});
						if (i) {
							item[name] = item[name] && item[name].replace ? item[name].replace(/\s+/g, "") : item[name];
						}
					}
				}
			});
		} else if (typeof data === 'object') {
			for (name in data) {
				if (data[name] && typeof data[name] === 'object') {
					Utils.clearWhiteSpace(data[name], includeArr);
				} else {
					var i = !includeArr ? 1 : _.find(includeArr, function (key) {
						return key == name;
					});
					if (i) {
						data[name] = data[name] && data[name].replace ? data[name].replace(/\s+/g, "") : data[name];
					}
				}
			}
		} else if (typeof data === 'string') {
			data = data.replace(/\s+/g, "");
		}
		return data;
	},
	/**
	 * 清空对象属性值
	 */
	clearObject: function (target, excludeArr, includeArr) {
		if (Array.isArray(target)) {
			$.each(target, function (i, item) {
				Utils.clearObject(item, excludeArr, includeArr);
			})
		} else {
			for (key in target) {
				if (target[key] && typeof target[key] === 'object') {
					Utils.clearObject(target[key], excludeArr, includeArr);
				} else {
					if (excludeArr || includeArr) {
						var clearflag = false;
						if (excludeArr) {
							var findRet = _.find(excludeArr, function (item) {
								return item == key;
							});
							if (!findRet) {
								target[key] = null;
								clearflag = true;
							}
						}

						if (!clearflag && includeArr) {
							var findRet = _.find(includeArr, function (item) {
								return item == key;
							});
							if (findRet) {
								target[key] = null;
							}
						}
					} else {
						target[key] = null;
					}

				}

			}
		}

	},

	/**
	 * 获取对象属性的值如Utils.getAccessor(obj,"company.address");
	 * @param obj
	 * @param expr
	 * @returns {*}
	 */
	getAccessor: function (obj, expr) {
		var ret, p, prm = [], i;
		if (typeof expr === 'function') {
			return expr(obj);
		}
		ret = obj[expr];
		if (ret === undefined) {
			try {
				if (typeof expr === 'string') {
					prm = expr.split('.');
				}
				i = prm.length;
				if (i) {
					ret = obj;
					while (ret && i--) {
						p = prm.shift();
						ret = ret[p];
					}
				}
			} catch (e) {
			}
		}
		return ret;
	},

	getCookie: function (name) {
		var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
		if (arr = document.cookie.match(reg)) {
			return unescape(arr[2]);
		} else {
			return null;
		}
	},

	setCookie: function (cname, cvalue, exdays) {
		var d = new Date();
		d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
		var expires = "expires=" + d.toUTCString();
		document.cookie = cname + "=" + cvalue + "; " + expires;
	},
	/**
	 * 获取浏览器缓存信息（会话级别）
	 * @param key
	 */
	getSessionCache: function (key) {
		var value = sessionStorage.getItem(key);
		if (this.isJSON(value)) {
			return JSON.parse(value);
		} else {
			return value
		}
	},
	/**
	 * 设置浏览器缓存信息（会话级别）
	 * @param key
	 * @param value
	 */
	setSessionCache: function (key, value) {
		if (typeof value == 'object') {
			sessionStorage.setItem(key, JSON.stringify(value));
		} else {
			sessionStorage.setItem(key, value);

		}
	},
	/**
	 * 移除指定缓存（会话级别）
	 * @param key
	 */
	removeSessionCacheByKey: function (key) {
		sessionStorage.removeItem(key);

	},
	/**
	 * 清空缓存（会话级别）
	 */
	clearSessionCache: function () {
		sessionStorage.clear();
	},

	/**
	 * 获取浏览器缓存信息（永久级别）
	 * @param key
	 */
	getLocalCahce: function (key) {
		var value = localStorage.getItem(key);
		if (this.isJSON(value)) {
			return JSON.parse(value);
		} else {
			return value
		}
	},
	/**
	 * 设置浏览器缓存信息（永久级别）
	 * @param key
	 * @param value
	 */
	setLocalCache: function (key, value) {
		if (typeof value == 'object') {
			localStorage.setItem(key, JSON.stringify(value));
		} else {
			localStorage.setItem(key, value);
		}
	},
	/**
	 * 移除指定缓存（永久级别）
	 * @param key
	 */
	removeLocalCacheByKey: function (key) {
		localStorage.removeItem(key);
	},
	/**
	 * 清空缓存（永久级别）
	 */
	clearLocalCache: function () {
		localStorage.clear();
	},
	/**
	 * 判断是否为json字符串
	 * @param str
	 * @returns {boolean}
	 */
	isJSON: function (str) {
		if (typeof str == 'string') {
			try {
				var obj = JSON.parse(str);
				if (typeof obj == 'object' && obj) {
					return true;
				} else {
					return false;
				}
			} catch (e) {
				console.log('error：' + str + '!!!' + e);
				return false;
			}
		}
	},
	/**
	 * 字符串截断
	 * @param str
	 * @param n
	 * @returns {string}
	 */
	cut: function (str, n) {
		var l = 0, i = 0;
		if (str.replace(/[\u4e00-\u9fa5]/g, '--').length <= n) return str.toString();
		while (i < str.length) {
			if (l >= n - 4) break;
			l += ((str.charCodeAt(i) >= 0x4e00 && str.charCodeAt(i) <= 0x9fa5) ? 2 : 1);
			i++;
		}
		return str.substring(0, i) + '...';
	},
	/**
	 * 转成持久化对象，即把枚举类转化成数数值（只针对 EntityFormatter.format 后的输出结果进行转换）
	 */
	toPersistentObj: function (data) {
		var reg = new RegExp("^original_([^&]*)$");
		if (Array.isArray(data)) {
			var persistentObjs = [];
			$.each(data, function (i, item) {
				var persistentObj = $.extend(true, {}, item);
				for (name in persistentObj) {
					if (name.startsWith("original_")) {
						var r = name.match(reg);
						var target = r[1];
						persistentObj[target] = persistentObj[name];
						delete persistentObj[name];
					}
				}
				persistentObjs.push(persistentObj);
			});
			return persistentObjs;
		} else {
			var persistentObj = $.extend(true, {}, data);
			for (name in persistentObj) {
				if (name.startsWith("original_")) {
					var r = name.match(reg);
					var target = r[1];
					persistentObj[target] = persistentObj[name];
					delete persistentObj[name];
				}
			}
			return persistentObj;
		}

	},

	createUUID: function () {
		return new UUID().createUUID().toLowerCase();
	},
	deepCopy: function (obj) {
		if (Array.isArray(obj)) {
			var ret = [];
			$.each(obj, function (i, item) {
				if (Array.isArray(item)) {
					ret = ret.concat(Utils.deepCopy(item));
				} else {
					ret.push($.extend(true, {}, item));
				}
			});
			return ret;
		} else {
			return $.extend(true, {}, obj);
		}
	},
	/**
	 ** 减法函数，用来得到精确的减法结果
	 ** 说明：javascript的减法结果会有误差，在两个浮点数相减的时候会比较明显。这个函数返回较为精确的减法结果。
	 ** 调用：accSub(arg1,arg2)
	 ** 返回值：arg1减去arg2的精确结果
	 **/
	accSub: function (arg1, arg2) {
		var r1, r2, m, n;
		try {
			r1 = arg1.toString().split(".")[1].length;
		} catch (e) {
			r1 = 0;
		}
		try {
			r2 = arg2.toString().split(".")[1].length;
		} catch (e) {
			r2 = 0;
		}
		m = Math.pow(10, Math.max(r1, r2));
		n = (r1 >= r2) ? r1 : r2;
		return ((arg1 * m - arg2 * m) / m).toFixed(n);
	},

	/**
	 ** 乘法函数，用来得到精确的乘法结果
	 ** 说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。
	 ** 调用：accMul(arg1,arg2)
	 ** 返回值：arg1乘以arg2的精确结果
	 **/
	accMul: function (arg1, arg2) {
		var m  = 0,
			s1 = arg1.toString(),
			s2 = arg2.toString();
		try {
			m += s1.split(".")[1].length;
		} catch (e) {
		}
		try {
			m += s2.split(".")[1].length;
		} catch (e) {
		}
		return Number(s1.replace(".", "")) *
				Number(s2.replace(".", "")) / Math.pow(10, m);
	},

	/**
	 ** 除法函数，用来得到精确的除法结果
	 ** 说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。
	 ** 调用：accDiv(arg1,arg2)
	 ** 返回值：arg1除以arg2的精确结果
	 **/
	accDiv: function (arg1, arg2) {
		var t1 = 0,
			t2 = 0,
			r1, r2;
		try {
			t1 = arg1.toString().split(".")[1].length;
		} catch (e) {
		}
		try {
			t2 = arg2.toString().split(".")[1].length;
		} catch (e) {
		}
		r1 = Number(arg1.toString().replace(".", ""));
		r2 = Number(arg2.toString().replace(".", ""));
		return (r1 / r2) * Math.pow(10, t2 - t1);
	},

	/**
	 * js浮点数四舍五入
	 * @param num 要处理的数据
	 * @param fixed 要保留的位数
	 * @returns {Number} 返回四舍五入后的数据
	 */
	roundFixed: function (num, fixed) {
		var pos            = num.toString().indexOf('.'),
			decimal_places = num.toString().length - pos - 1,
			_int           = num * Math.pow(10, decimal_places),
			divisor_1      = Math.pow(10, decimal_places - fixed),
			divisor_2      = Math.pow(10, fixed);
		return Math.round(_int / divisor_1) / divisor_2;
	},
	////四舍五入方法roundFixed返回值不精确，新增下列方法
	numToFixed: function (num, fixed) {
		return Math.round(Math.round(num * (Math.pow(10, parseInt(fixed) + 1))) / 10) / (Math.pow(10, parseInt(fixed)));
	},
	/**
	 * 检查是否为数字类型
	 */
	checkIsRate: function (nubmer) {
		var reg = /^[0-9]+.?[0-9]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/
		if (!reg.test(nubmer)) {
			return false;
		} else {
			return true;
		}
	},

	/***
	 * 日期比较 yyyy-MM-dd
	 */
	compareDate: function (checkStartDate, checkEndDate) {
		var arys1 = new Array();
		var arys2 = new Array();
		if (checkStartDate != null && checkEndDate != null) {
			arys1 = checkStartDate.split('-');
			var sdate = new Date(arys1[0], parseInt(arys1[1] - 1),
					arys1[2]);
			arys2 = checkEndDate.split('-');
			var edate = new Date(arys2[0], parseInt(arys2[1] - 1),
					arys2[2]);
			if (sdate > edate) {
				return false;
			} else {
				return true;
			}
		}
	},
	formatDate: function dateFtt(fmt, date) {
		var o = {
			"M+": date.getMonth() + 1,                 //月份
			"d+": date.getDate(),                    //日
			"h+": date.getHours(),                   //小时
			"m+": date.getMinutes(),                 //分
			"s+": date.getSeconds(),                 //秒
			"q+": Math.floor((date.getMonth() + 3) / 3), //季度
			"S": date.getMilliseconds()             //毫秒
		};
		if (/(y+)/.test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
		}
		for (var k in o)
			if (new RegExp("(" + k + ")").test(fmt)) {
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
			}
		return fmt;
	},
	/***
	 * 比较日期+事件 yyyy-MM-dd hh:mm:ss
	 */
	compareTime: function (startDate, endDate) {
		if (startDate.length > 0 && endDate.length > 0) {
			var startDateTemp = startDate.split(" ");
			var endDateTemp = endDate.split(" ");
			var arrStartDate = startDateTemp[0].split("-");
			var arrEndDate = endDateTemp[0].split("-");
			var arrStartTime = startDateTemp[1].split(":");
			var arrEndTime = endDateTemp[1].split(":");
			var allStartDate = new Date(arrStartDate[0],
					arrStartDate[1], arrStartDate[2], arrStartTime[0],
					arrStartTime[1], arrStartTime[2]);
			var allEndDate = new Date(arrEndDate[0], arrEndDate[1],
					arrEndDate[2], arrEndTime[0], arrEndTime[1],
					arrEndTime[2]);

			if (allStartDate.getTime() >= allEndDate.getTime()) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	},

	/***
	 * 比较日期
	 */
	compareDateTime: function (startDate, endDate) {
		if (startDate.indexOf(" ") != -1 && endDate.indexOf(" ") != -1) {
			//包含时间，日期
			return Utils.compareTime(startDate, endDate);
		} else {
			//不包含时间，只包含日期
			return Utils.compareDate(startDate, endDate);
		}
	},
	/**
	 *计算年龄算法
	 *@param birthday --生日（yyyy-MM-dd)
	 *@return 年龄
	 */
	getPersonAge: function (birthday) {
		if (birthday) {
			var ageYear = new Date(birthday).getFullYear();
			var currYear = new Date().getFullYear();
			return (currYear - ageYear + 1);
		}
	},
	addSubMenuNavigations: function (subMenus) {
		if (typeof top.home_vm === 'object' && top.home_vm) {
			top.home_vm.addSubMenuNavigations(subMenus);
		}
	},
	/**
	 * 重新加载
	 */
	reloadSubMenuNavigations: function (subMenus) {
		if (typeof top.home_vm === 'object' && top.home_vm) {
			top.home_vm.reloadSubMenuNavigations(subMenus);
		}
	},
	getSubMenuNavigations: function () {
		if (typeof top.home_vm === 'object' && top.home_vm) {
			return top.home_vm.getSubMenuNavigations();
		}
	},

	addDialogNavigation: function (dialogNav) {
		if (typeof top.home_vm === 'object' && top.home_vm) {
			top.home_vm.addDialogNavigation(dialogNav);
		}
	},
	closeCurrentDialogNavigation: function () {
		if (typeof top.home_vm === 'object' && top.home_vm) {
			top.home_vm.closeCurrentDialogNavigation();
		}
	},
	setRightNavigationContent: function (html) {
		if (typeof top.home_vm === 'object' && top.home_vm) {
			top.home_vm.setRightNavigationContent(html);
		}
	},
	showLoadingShade: function () {
		// if ( !$vue.$loading &&  !(typeof $vue.$loading === 'function')) return;
		// currentShade = $vue.$loading({
		//     lock: true,
		//     text: 'Loading',
		//     spinner: 'el-icon-loading',
		//     background: 'rgba(0, 0, 0, 0.7)'
		// });
		if (typeof top.home_vm === 'object' && top.home_vm && typeof top.home_vm.showLoadingShade === 'function') {
			top.home_vm.showLoadingShade();
		}
	},
	closeLoadingShade: function () {
		// if (currentShade) currentShade.close();
		if (typeof top.home_vm === 'object' && top.home_vm && typeof top.home_vm.closeLoadingShade === 'function') {
			top.home_vm.closeLoadingShade();
		}
	},
	closeFullScreenShade: function () {
		if (typeof top.home_vm === 'object' && top.home_vm) {
			top.home_vm.closeFullScreenShade();
		}
	},
	showFullScreenShade: function () {
		if (typeof top.home_vm === 'object' && top.home_vm) {
			top.home_vm.showFullScreenShade();
		}
	},
	addCurrentPageFocusEvent: function (focusCallback) {
		var curPage = decodeURI(window.location.href);
		if (typeof top.home_vm === 'object' && top.home_vm) {
			top.home_vm.$watch("menuItems", function (newVal, oldVal) {
				var findFlag = _.find(newVal, function (item) {
					return item["isShow"] == true && curPage.indexOf(item["url"]) >= 0;
				});
				if (top && top.hasOwnProperty('home_vm') && findFlag && focusCallback && typeof focusCallback === 'function') {
					focusCallback();
				}
			});
		}
	},
	/**
	 *
	 * @param confirmMsg 切换页面前的弹窗提示语
	 *
	 * @param beforeConfirmHandler 切换页面前的回调方法,返回true将在切换前弹窗
	 * @param afterConfirmYesHandler 弹窗后点确定后的回调方法
	 */
	registSwitchNavConfirm: function (confirmMsg, beforeConfirmHandler, afterConfirmYesHandler) {
		var curPage = decodeURI(window.location.href);
		if (typeof top.home_vm === 'object' && top.home_vm) {
			var info = top.home_vm.getCurShowNavInfo(curPage);
			$.extend(info, { confirmMsg: confirmMsg, beforeConfirmHandler: beforeConfirmHandler, afterConfirmYesHandler: afterConfirmYesHandler });
		}
	},
	/**
	 * 自动选择菜单
	 */
	autoSelectMenu: function (menuName) {
		if (typeof top.home_vm === 'object' && top.home_vm) {
			top.home_vm.autoSelectMenu(menuName);
		}
	},
	/**
	 * 发送消息
	 * @param type
	 * @param msg
	 */
	wsSendMessage: function (type, msg) {
		if (typeof top.home_vm === 'object' && top.home_vm) {
			var info = top.home_vm.wsSendMessage(type, msg);
		}
	},
	/**
	 * 添加事件监听
	 * @param handler
	 */
	addSocketMessageListener: function (handler) {
		if (typeof top.home_vm === 'object' && top.home_vm) {
			var info = top.home_vm.addSocketMessageListener({ type: handler["type"], handler: handler["handler"] });
		}
	},
	/**
	 * 初始化上传组件
	 * @param url
	 * @param browseButton
	 * @param container
	 * @param params
	 * @param uploaded
	 * @param error
	 * @param addListener
	 * @param progress
	 * @param filters
	 * @returns {Uploader}
	 */
	initFileUpload: function (url, browseButton, container, params, uploaded, error, addListener, progress, filters) {
		var setting = {
			runtimes: 'html5,html4,flash,silverlight',
			browse_button: browseButton, // you can pass an id...
			container: container, // ... or DOM Element itself
			url: url,
			multi_selection: false,
			multipart_params: params,
			filters: filters,
			// filters: {
			//     max_file_size: '10mb',
			//     mime_types: [
			//         {title: "excel files", extensions: "xls"}
			//     ]
			// },
			init: {
				PostInit: function () {

				},
				FilesAdded: function (up, files) {
					if (typeof (addListener) === "function") {
						addListener(up, files);
					}
				},
				UploadProgress: function (up, file) {
					if (typeof (progress) === "function") {
						progress(up, file);
					}
				},
				//当队列中的某一个文件上传完成后触发 (回调函数)
				FileUploaded: function (up, file, response) {
					if (typeof (uploaded) === "function") {
						uploaded(up, file, response);
					}
				},
				Error: function (up, err) {
					if (typeof (error) === "function") {
						error(up, err);
					}
				}
			}
		};

		if (typeof url === 'object') {
			$.extend(setting, url);
		}
		var uploader = new plupload.Uploader(setting);
		uploader.init();
		return uploader;
	},
	/**
	 * 开始上传
	 * @param uploader
	 */
	startUpload: function (uploader) {
		uploader.start();
	},
	initImageViewer: function (id, options, callback) {
		var shade = this.shade;
		var imageId = Utils.createUUID();
		var setting = {
			url: 'data-original',
			zIndex: 29891020,
			show: function () {
				Utils.showTopShade(imageId, shade);
			},
			hide: function () {
				setTimeout(function () {
					Utils.closeTopShade(imageId);
				}, 300);
			}
		};
		if (typeof options === 'object') {
			$.extend(setting, options);
		}
		var images = $("#" + id);
		$.each(images, function (i, item) {
			new Viewer(item, setting);
		});
		if (typeof callback === 'function') {
			callback(id);
		}

	},
	/**
	 * 获取当前用户id
	 */
	getCurrentUser: function () {
		return Utils.getLocalCahce("currentUser");
	},
	getNotNullObject: function (obj) {
		return obj ? obj : '';
	},
	/**
	 * 路径格式转换
	 */
	filePathTransform: function (path) {
		return encodeURIComponent(path.split('/').join(interpretation_CONSTANTS.HTML_FILE_SEPARATOR));
	}
});

//校验工具
var Validator = Class_utils.create({
	/** 非空验证 */
	isNotNull: function (rule, value, callback, source, options) {
		if (value != null && value != undefined && value != 'undefined' && value != "") return true;
		return false;
	},
	/**去空格后非空验证*/
	isNotNullTrim: function (source) {
		if (source != null && source != undefined && source != 'undefined' && $.trim(source) != "") {
			return true;
		}
		return false;
	},
	/**
	 * 字符串范围
	 * @param source
	 * @param start 最大长度
	 * @param stop 最小长度
	 * @returns {boolean}
	 */
	range: function (rule, value, callback, source, options) {
		var lenght = Utils.trimObj(source).length;
		if (rule.options.min <= lenght && lenght <= rule.options.max) {
			return true;
		} else {
			return false;
		}
	},
	/**
	 * 字符串最大长度
	 * @param source
	 * @param num
	 * @returns {boolean}
	 */
	maxLength: function (source, num) {
		if (Utils.trimObj(source).length <= num) {
			return true;
		} else {
			return false;
		}
	},
	/**
	 * 字符串最小长度
	 * @returns {boolean}
	 */
	minLength: function () {
		if (Utils.trimObj(source).length >= num) {
			return true;
		} else {
			return false;
		}
	},
	/**
	 * 验证字符串最大长度【注：一个汉字的长度为2】
	 * @param source 字符串
	 * @param num 指定的长度
	 */
	maxCharacter: function (source, num) {
		if (Utils.trimObj(source).replace(/[^\x00-\xff]/g, "**").length <= num) {
			return true;
		}
		return false;
	},
	/**
	 * 验证字符串最小长度【注：一个汉字的长度为2】
	 * @param source 字符串
	 * @param num 指定的长度
	 */
	minCharacter: function (source, num) {
		if (Utils.trimObj(source).replace(/[^\x00-\xff]/g, "**").length >= num) {
			return true;
		}
		return false;
	},
	/** 身份证验证 */
	identityVer: function (identity) {
		var pattern = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
		if (identity != null && identity != "" && pattern.test(identity)) {
			return true;
		}
		return false;
	},
	/** 手机号验证 */
	mobileVer: function (mobile) {
		var pattern = /^((1[3|4|5|7|8|9][0-9]{1})+\d{8})$/;
		if (mobile != null && mobile != "" && pattern.test(mobile)) {
			return true;
		}
		return false;
	},
	/** 邮箱验证 */
	emailVer: function (email) {
		var pattern = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		if (email != null && email != "" && pattern.test(email)) {
			return true;
		}
		return false;
	},
	/** 电话验证(座机) */
	phoneVer: function (phone) {
		var pattern = /^0\d{2,3}-?\d{7,8}$/;
		if (phone != null && phone != "" && pattern.test(mobile)) {
			return true;
		}
		return false;
	},
	/** 验证是否为正整数 */
	numberVer: function (input) {
		var pattern = /^[1-9][0-9]*$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	/** 验证非零正整数 */
	NZ_numberVer: function (input) {
		var pattern = /^\+?[1-9][0-9]*$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	/** 验证非零负整数 */
	_NZ_numberVer: function (input) {
		var pattern = /^\-[1-9][0-9]*$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	/** 验证非负整数（正整数 + 0） */
	_noIntNumberVer: function (input) {
		var pattern = /^\d+$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	/** 验证非正整数（负整数 + 0） */
	noIntNumberVer: function (input) {
		var pattern = /^((-\d+)|(0+))$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	/** 验证整数 */
	intNumberVer: function (input) {
		var pattern = /^-?\d+$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	/** 验证正浮点小数 */
	doubleNumberVer: function (input) {
		var pattern = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	/** 验证非正浮点小数（负浮点小数 + 0） */
	_noDoubleNumVer: function (input) {
		var pattern = /^((-\d+(\.\d+)?)|(0+(\.0+)?))$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	/** 验证负浮点小数 */
	_doubleNumberVer: function (input) {
		var pattern = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	/** 验证浮点小数 */
	doubleVer: function (input) {
		var pattern = /^(-?\d+)(\.\d+)?$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	/**
	 * 判断染色体格式，示例：chr2:47702290..47702290
	 */
	isChromosome: function (input) {
		var pattern = /^chr\d+:\d+\.\.\d+$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	/**
	 * 判断基因亚区格式，示例：EX10/CDS9、EX1/5-UTR、IN1/
	 */
	isGeneSubregion: function (input) {
		var pattern = /^(EX|IN)\d+\/(CDS\d+|\d+-UTR)?$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	isLetter: function (input) {
		var pattern = /^[a-zA-Z]+$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	email: function (input) {
		var pattern = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	bgiEmail: function (input) {
		var pattern = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@(bgi.com|genomics.cn)$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	}


});
// 注册校验工具
Vue.prototype.$valid = Validator;
Vue.mixin({
	data: function () {
		return {
			contentHeight: ($(window).height() - 105),
			elTableHeight: ($(window).height() - 100),
		}
	},
	methods: {
		indexMethod: function (index) {
			return this.pageInfo ? ((this.pageInfo.pageSize * (this.pageInfo.currentPage - 1)) + index + 1) : index + 1;
		},
	},
	mounted: function () {
		var _this = this;
		window.$vue = this;// 全局注入$vue
		window.onresize = function (ev) {
			$(".el-table.index-table").css("height", $(window).height() - 106);
		}
	},

})










