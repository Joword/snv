import $ from 'jquery';

let Class_utils = (function () {
	function create() {
		let obj = arguments[0];
		return obj || undefined
	}

	return {
		create: create
	}
})();
let Validator = Class_utils.create({
	/** 非空验证 */
	isNotNull: function (rule, value, callback, source, options) {
		if (value !== null && value !== undefined && value !== 'undefined' && value !== "") return true;
		return false;
	},
	/**去空格后非空验证*/
	isNotNullTrim: function (source) {
		if (source !== null && source !== undefined && source !== 'undefined' && $.trim(source) !== "") {
			return true;
		}
		return false;
	},
	/**
	 * 字符串范围
	 * @param rule
	 * @param value
	 * @param callback
	 * @param source
	 * @param options
	 * @returns {boolean}
	 */
	range: function (rule, value, callback, source, options) {
		let lenght = this.$Utils.trimObj(source).length;
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
		if (this.$Utils.trimObj(source).length <= num) {
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
		if (this.$Utils.trimObj(source).length >= num) {
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
		if (this.$Utils.trimObj(source).replace(/[^\x00-\xff]/g, "**").length <= num) {
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
		if (this.$Utils.trimObj(source).replace(/[^\x00-\xff]/g, "**").length >= num) {
			return true;
		}
		return false;
	},
	/** 身份证验证 */
	identityVer: function (identity) {
		let pattern = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
		if (identity != null && identity !== "" && pattern.test(identity)) {
			return true;
		}
		return false;
	},
	/** 手机号验证 */
	mobileVer: function (mobile) {
		let pattern = /^((1[3|4|5|7|8|9][0-9]{1})+\d{8})$/;
		if (mobile != null && mobile != "" && pattern.test(mobile)) {
			return true;
		}
		return false;
	},
	/** 邮箱验证 */
	emailVer: function (email) {
		let pattern = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		if (email != null && email != "" && pattern.test(email)) {
			return true;
		}
		return false;
	},
	/** 电话验证(座机) */
	phoneVer: function (phone) {
		let pattern = /^0\d{2,3}-?\d{7,8}$/;
		if (phone != null && phone != "" && pattern.test(mobile)) {
			return true;
		}
		return false;
	},
	/** 验证是否为正整数 */
	numberVer: function (input) {
		let pattern = /^[1-9][0-9]*$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	/** 验证非零正整数 */
	NZ_numberVer: function (input) {
		let pattern = /^\+?[1-9][0-9]*$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	/** 验证非零负整数 */
	_NZ_numberVer: function (input) {
		let pattern = /^\-[1-9][0-9]*$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	/** 验证非负整数（正整数 + 0） */
	_noIntNumberVer: function (input) {
		let pattern = /^\d+$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	/** 验证非正整数（负整数 + 0） */
	noIntNumberVer: function (input) {
		let pattern = /^((-\d+)|(0+))$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	/** 验证整数 */
	intNumberVer: function (input) {
		let pattern = /^-?\d+$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	/** 验证正浮点小数 */
	doubleNumberVer: function (input) {
		let pattern = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	/** 验证非正浮点小数（负浮点小数 + 0） */
	_noDoubleNumVer: function (input) {
		let pattern = /^((-\d+(\.\d+)?)|(0+(\.0+)?))$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	/** 验证负浮点小数 */
	_doubleNumberVer: function (input) {
		let pattern = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	/** 验证浮点小数 */
	doubleVer: function (input) {
		let pattern = /^(-?\d+)(\.\d+)?$/;
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
		let pattern = /^chr\d+:\d+\.\.\d+$/;
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
		let pattern = /^(EX|IN)\d+\/(CDS\d+|\d+-UTR)?$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	isLetter: function (input) {
		let pattern = /^[a-zA-Z]+$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	email: function (input) {
		let pattern = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	},
	bgiEmail: function (input) {
		let pattern = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@(bgi.com|genomics.cn)$/;
		if (input) {
			return pattern.test(input);
		} else {
			return false;
		}
	}
});

export default {
	/**
	 * 去除字符串前后空格(undefinded返回空字符)
	 * @param target
	 * @param excludeArr
	 * @param includeArr
	 */
	trimObj: function (target, excludeArr, includeArr) {
		let ret = "";
		if (Array.isArray(target)) {
			ret = target;
			$.each(target, function (i, item) {
				this(item, excludeArr, includeArr);
			});
		} else if (target && typeof target === 'object') {
			ret = target;
			for (let key in target) {
				if (target[key] && typeof target[key] === 'object') {
					this(target[key], excludeArr, includeArr);
				} else if (target[key] && typeof target[key] === 'boolean') {
					continue;
				} else if (target[key]) {
					if (excludeArr || includeArr) {
						var clearflag = false;
						if (excludeArr) {
							var findRet = _.find(excludeArr, function (item) {
								return item === key;
							});
							if (!findRet) {
								target[key] = $.trim(target[key]);
								clearflag = true;
							}
						}

						if (!clearflag && includeArr) {
							let findRet = _.find(includeArr, function (item) {
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
		} else if (target || target === "0" || target === 0) {
			ret = $.trim(target);
		}
		return ret;
	},
	/**去空格后非空验证*/
	isNotNullTrim: function (source) {
		return source != null && true && source !== 'undefined' && source.trim() !== "";
	},
	/**
	 * 验证字符串最大长度【注：一个汉字的长度为2】
	 * @param source 字符串
	 * @param num 指定的长度
	 */
	maxCharacter: function (source, num) {
		// eslint-disable-next-line no-control-regex
		return this.trimObj(source).replace(/[^\x00-\xff]/g, "**").length <= num;

	},
	getQueryString: function (name, defVal) {
		let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		let r = window.location.search.substr(1).match(reg);
		if (r != null) return decodeURIComponent(r[2]);
		return defVal ? defVal : null;
	},
	Validator
}
