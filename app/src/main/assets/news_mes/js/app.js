var _uas = navigator.userAgent.toLowerCase();
/*
 * wifi模式下图片滑动加载
 * Date: 2016/08/23
 */
window.onload = function() {
	$('.content_images img[data-src]').each(function(i, item) {
		var $src = $(this).attr('data-src');
		$(this).load(function() {
			$(this).unbind("load");
			$(this).removeAttr('data-src');
			var _bodyHeight = window.document.body.offsetHeight;
			if (_uas.match(/android/i)) {
				window.wst.setLayoutHeight(_bodyHeight);
			} else if (_uas.match(/iphone|ipad/i)) {
				window.location.hash = "height" + _bodyHeight;
			}
		});
		$(this).attr('src', $src);
	});

	$.each($('.ke-video video'), function(index, val) {
		var _this = this;
		this.src = $(this).attr("dataurl");
		this.style.height = this.offsetWidth / 1.777 + 'px';
		if (_uas.match(/iphone|ipad/i)) {
			$(this).parent(".ke-video").find(".playControl").hide();
		}
		setTimeout(function() {
			_this.load();
			var _bodyHeight = window.document.body.offsetHeight;
			if (_uas.match(/android/i)) {
				window.wst.setLayoutHeight(_bodyHeight);
			} else if (_uas.match(/iphone|ipad/i)) {
				window.location.hash = "height" + _bodyHeight;
			}
		},300)
	});

	$.each($('.ke-audio audio'), function(index, val) {
		var _this = this;
		this.src = $(this).attr("dataurl");
		setTimeout(function() {
			_this.load();
		},300)
	});

	$('.ke-video video').on('click', function() {
		if(this.paused) {
			$(this).parent(".ke-video").find("video").get(0).play();
		}
	})

	$('.ke-video video').on('canplay', function() {
		// 获取到视频的时间
		var fen = parseInt(this.duration / 60);
		var time = fen + ':' + (parseInt(this.duration - fen * 60) > 9 ? parseInt(this.duration - fen * 60) : '0' + parseInt(this.duration - fen * 60));
		$(this).parent(".ke-video").find(".times").html(time);
		$(this).parent(".ke-video").find(".times").show();
	})

	$('.ke-video .playControl').on('click', function() {
		$(this).parent(".ke-video").find("video").get(0).play();
	})
	$('.ke-video video').on('play', function() {
		// 监听视频播放
		$(this).parent(".ke-video").find(".playControl").hide();
		$(this).parent(".ke-video").find(".playTit").hide();
		$(this).attr('controls', 'controls');
		pauseAllAudio(); // 暂停所有音频
	})
	$('.ke-video video').on('pause', function() {
		// 监听视频暂停
		// if (_uas.match(/iphone|ipad/i)) {
		// 	$(this).parent(".ke-video").find(".playControl").hide();
		// } else {
		// 	$(this).parent(".ke-video").find(".playControl").show();
		// }
		// $(this).parent(".ke-video").find(".playControl").show();
		$(this).parent(".ke-video").find(".playControl").hide();
		// $(this).removeAttr('controls');
	})

	$('.ke-audio .playBtn').on('click', function() {
		// 视频播放按钮
		if ($(this).parents().find(".ke-audio").find("audio").get(0).paused) {
		    // 暂停中
		    $(this).parents().find(".ke-audio").find("audio").get(0).play();	
			pauseAllVideo(); // 暂停所有视频	
		} else {
		    // 播放中
		    $(this).parents().find(".ke-audio").find("audio").get(0).pause();
		}
	})

	$('.ke-audio .handle').get(0).addEventListener("touchstart", function(e){
	   	this.parentLineWidth = $(this).parent(".progress").find(".line").width();
		this.touchstartX = e.touches[0].clientX;
		$(this).parents().find(".ke-audio").find("audio").get(0).pause();
		$(this).parents().find(".ke-audio").find(".playBtn").removeClass('pause');
	});

	$('.ke-audio .handle').get(0).addEventListener("touchmove", function(e){
		var moveX = e.touches[0].clientX - this.touchstartX;
		var playProgress = (this.offsetLeft + moveX) / this.parentLineWidth * 100;
		if(playProgress <= 0) {
			playProgress = 0;
		} else if(playProgress >= 100) {
			playProgress = 100;
		}
		$(this).parents().find(".ke-audio").find(".hasPlay").width(playProgress + '%');
		$(this).parents().find(".ke-audio").find(".handle").css({
			left: playProgress + '%'
		});
		this.touchstartX = e.touches[0].clientX;
		this.playProgress = playProgress;
	});

	$('.ke-audio .handle').get(0).addEventListener("touchend", function(e) {
	   	$(this).parents().find(".ke-audio").find("audio").get(0).currentTime = $(this).parents().find(".ke-audio").find("audio").get(0).duration * this.playProgress / 100;
	   	$(this).parents().find(".ke-audio").find("audio").get(0).play();
	});

	$('.ke-audio audio').on('timeupdate', function() {
		var playProgress = this.currentTime / this.duration * 100 + '%';
		var currentTime = parseInt(this.currentTime / 60) + ':' + (parseInt(this.currentTime % 60) > 9 ? parseInt(this.currentTime % 60) : '0' + parseInt(this.currentTime % 60));
		$(this).parent(".ke-audio").find(".playTime").html(currentTime);
		$(this).parent(".ke-audio").find(".hasPlay").width(playProgress);
		$(this).parent(".ke-audio").find(".handle").css({
			left: playProgress
		});
	})
	$('.ke-audio audio').on('play', function() {
		$(this).parents().find(".ke-audio").find(".playBtn").addClass('pause');
	})
	$('.ke-audio audio').on('pause', function() {
		$(this).parents().find(".ke-audio").find(".playBtn").removeClass('pause');
	})
};

$(function() {
	$("img[data-noimage]").attr("class", " ");
	$("img[data-noimage]").on('click', function() {
		var src = "http://sapp.huanhuba.com/images/default3.gif";
		$(this).load(function() {
			var realImg = $(this).attr("data-noimage");
			$(this).unbind("load");
			$(this).load(function() {
				var _height = window.document.body.offsetHeight;
				$(this).addClass("dataBig");
				if (_uas.match(/android/i)) {
					window.wst.setLayoutHeight(_height);
				} else if (_uas.match(/iphone|ipad/i)) {
					window.location.hash = "height" + _height;
				}
				$(this).unbind("load");
			});
			$(this).attr('src', realImg);
		});
		$(this).attr('src', src);
	});
	$(".content_images img").on("click", function() {
		if ($(this).hasClass("dataBig")) {
			var list = $(".content_images img");
			var _this = $(this).get(0);
			var length = list.length;
			for (var i = 0; i < length; i++) {
				if (list[i] === _this) {
					if (_uas.match(/android/i)) {
						var num = i;
					} else if (_uas.match(/iphone|ipad/i)) {
						var num = i + 1;
					}

				}
			}
			if (_uas.match(/android/i)) {
				window.wst.setCurrentNum(num);
			} else if (_uas.match(/iphone|ipad/i)) {
				window.location.hash = "currentNum" + num;
			}
		}
	});

	$(".peopleImg").on("click", function() {
		var id = $(this).attr("data-uid");
		if (_uas.match(/android/i)) {
			window.wst.setUserId(id);
		} else if (_uas.match(/iphone|ipad/i)) {
			window.location.hash = "userId" + id;
		}
	});
});

function jumpTo(link_type, link_id2, link_id) {
	if (_uas.match(/android/i)) {
		console.log("jumpToUrl(" + link_type + ", " + link_id2 + ", " + link_id + ")");
		window.wst.jumpToUrl(link_type, link_id2, link_id);
	} else if (_uas.match(/iphone|ipad/i)) {
		var length = arguments.length;
		var _hash = "";
		if (length == 3) {
			_hash = 'gonextpage={"link_type":' + link_type + ',"link_id2":' + link_id2 + ',"link_id":' + link_id + '}';
		} else if (length == 2) {
			if (link_type == 20) {
				_hash = 'gonextpage={"link_type":' + link_type + ',"post_id":' + link_id2 + '}';
			} else {
				_hash = 'gonextpage={"link_type":' + link_type + ',"link_id":' + link_id2 + '}';
			}

		}
		window.location.hash = _hash;
	}
}

var wk = 0;

function CallIsWk(wkWebview) {
	wk = parseInt(wkWebview);
}

function jumpToOther(jsonData) {
	var data = JSON.stringify(jsonData);
	if (_uas.match(/android/i)) {
		window.wst.jumpToOther(data);
	} else if (_uas.match(/iphone|ipad|ipod/i)) {
		if (wk) {
			window.webkit.messageHandlers.kuaixunModel.postMessage({
				method: 'gonextpage',
				param: data
			});
		} else {
			native('gonextpage', data);
		}
	}
}

function pauseAllVideo() {
	$.each($('.ke-video video'), function(index, val) {
		this.pause();
	});
}

function pauseAllAudio() {
	$.each($('.ke-audio audio'), function(index, val) {
		this.pause();
	});
}

//javascript:jumpToOther({'link_type':'30','link_id':'2086424','link_id2':'75207','title':'3','expert_id':'1})