

window.onload = function() 
	{
		//一个全局变量,用来保存删除商品是的商品id
		 var gg_id = null;
		
		
		//一个标签多个class，需要来试一下用哪个class来选择
		//jquery的each方法，不好用
		var mbtns = document.getElementsByClassName('minus');
		var pbtns = document.getElementsByClassName('plus');
		var gnums = document.getElementsByClassName('input-sm');
		var gids = document.getElementsByClassName('g_ids');
		var gtprices = document.getElementsByClassName('g_tprices');
		var gsprices = document.getElementsByClassName('g_sprices');
		
		var checkboxs = document.getElementsByClassName('check-one');
		var checkboxstats = document.getElementsByClassName('checkboxstat');
		

		for(var i = 0; i <$("input[name='checkbox']").length;i++)
		{
			var result = (checkboxstats[i].value);	
			
			//比较相等一直不行,最后添加了trim解决
			if($.trim(result) == 'checked')
			{
				checkboxs[i].checked = true;
			}
		
		}
		
		if($("input[name='checkbox']").length == $("input[name='checkbox']:checked").length)
		{
			$(".check-all").prop("checked","true");
		}
		
		//刷新总额
		_cal_tot_amont();
		
		//全选---分开写就不行，有问题
		$(".check-all.check").click(function()
		{
			var isChecked = $(this).prop("checked");
		 $("input[name='checkbox']").prop("checked", isChecked);
			_cal_tot_amont();	
			
			for(var i = 0; i <$("input[name='checkbox']").length;i++)
			{
				var g_id = gids[i].value;
				var isChecked ;
				if(checkboxs[i].checked)
				{
					isChecked = true;
				}
				else
				{
					isChecked = false;
				}

				_stat_change(isChecked,g_id);
			}

		});
		
		//选中
		$("input[name='checkbox']").click(function(){
			_cal_tot_amont();		
			
			var index = $(this).attr("index");
			var g_id = gids[index].value;
			//选中:true  取消选中:false
			var isChecked = $(this).prop("checked");
			
			_stat_change(isChecked,g_id);
		});

		
		for (var i = 0; i < mbtns.length; i++) {

			(function(i) {
				
				mbtns[i].onclick = function() {
					
					var g_id = gids[i].value;

					var num = gnums[i].value;
					
					var g_price = gsprices[i].innerHTML;
					
					
					if (num == 1) {
						_del(g_id);
					}
					else
					{
						$.ajax({
							//提交数据的类型 POST GET
							type : "get",
							//提交的网址
							url : "mChatList.action",
							async : false,
							//提交的数据
							data : "g_id=" + g_id,
							//返回数据的格式
							datatype : "text",//"xml", "html", "script", "json", "jsonp", "text".
							contentType : "application/json;charset=utf-8",

							//成功返回之后调用的函数             
							success : function(data) {
									var g_num = (Number(num) - 1);
									gnums[i].value =  g_num;
									var t_price = parseFloat(g_price*g_num).toFixed(1);
									gtprices[i].innerHTML = t_price;
									_cal_tot_amont();

							},

							//调用出错执行的函数
							error : function() {
								//请求出错处理
							}
						});
					}
				}

				pbtns[i].onclick = function() {

					var g_id = gids[i].value;

					var g_price = gsprices[i].innerHTML;
					var num = gnums[i].value;

					$.ajax({
						//提交数据的类型 POST GET
						type : "get",
						//提交的网址
						url : "pChatList.action",
						//提交的数据
						data : "g_id=" + g_id,
						//返回数据的格式
						datatype : "text",//"xml", "html", "script", "json", "jsonp", "text".
						contentType : "application/json;charset=utf-8",

						//成功返回之后调用的函数             
						success : function(data) {
							var g_num = (Number(num) + 1);
							gnums[i].value = g_num;
							var t_price = parseFloat(g_price*g_num).toFixed(1);
							gtprices[i].innerHTML = t_price;
							_cal_tot_amont();
						},

						//调用出错执行的函数
						error : function() {
							//请求出错处理
						}
					});

				}
			})(i)

		}
	};
	



	function _dell()
	{
		g_id = gg_id;
		$.ajax({
			type : "get",
			url : "deleteChatGood.action",
			async : false, //嵌套时最好加上这个，不然容易出问题，我的就是这里出问题了，外层的应该无所谓，内层一定要加
			data : "g_id=" + g_id,
			datatype : "text",//"xml", "html", "script", "json", "jsonp", "text".
			contentType : "application/json;charset=utf-8",
			success : function(msg) {
				//刷新整个页面
				location.reload();
			}
		});
	}

	function _del(g_id)
	{
		gg_id = g_id;
		$('#myModal').modal('show');
	}

	
	
	//计算选中商品的总金额
	function _cal_tot_amont()
	{
		var s = 0.0;
		var checkboxs = document.getElementsByClassName('check-one');
		var gtprices = document.getElementsByClassName('g_tprices');
		var tot_amont = document.getElementById('tot_amont');
		var gids = document.getElementsByClassName('g_ids');

		for(var i = 0; i < checkboxs.length; i++)
		{
			if(checkboxs[i].checked)
			{
				var price = (gtprices[i].innerHTML);
				
				s = parseFloat(parseFloat(s)+parseFloat(price)).toFixed(1);
				
			}
		}

		tot_amont.innerHTML = s;
		
		//判断全选按钮的状态
		if($("input[name='checkbox']").length == $("input[name='checkbox']:checked").length)
		{
			$(".check-all").prop("checked","true");
		}
		else
		{
			$(".check-all").prop("checked",false);
		}
	}
	
	//点击checkbox时的处理
	function _stat_change(isChecked,g_id)
	{
		
		//判断全选按钮的状态
		if($("input[name='checkbox']").length == $("input[name='checkbox']:checked").length)
		{
			$(".check-all").prop("checked","true");
		}
		else
		{
			$(".check-all").prop("checked",false);
		}
		
		var stat;
		if(isChecked == true)
			stat = "checked";
		else
			stat = "";
		
		
		var data = "g_id="+Number(g_id)+"&stat="+stat;
		$.ajax({
			type : "get",
			url : "chatgoodCheckedStat.action",
			async : false, //嵌套时最好加上这个，不然容易出问题，我的就是这里出问题了，外层的应该无所谓，内层一定要加
			data :data ,
			datatype : "text",//"xml", "html", "script", "json", "jsonp", "text".
			contentType : "application/json;charset=utf-8",
			success : function(msg) {

			}
		});
	}
	
	function _onblur(g_id,index)
	{
		var g_num = document.getElementsByClassName('good_num');
		var data = "g_id="+Number(g_id)+"&g_num="+g_num[index].value;
		if(g_num[index].value == 0)
		{
			_del(g_id);
		}
		else
		{
			$.ajax({
				type : "get",
				url : "modifyChatList.action",
				async : false, //嵌套时最好加上这个，不然容易出问题，我的就是这里出问题了，外层的应该无所谓，内层一定要加
				data :data ,
				datatype : "text",//"xml", "html", "script", "json", "jsonp", "text".
				contentType : "application/json;charset=utf-8",
				success : function(msg) {
					//刷新整个页面
					location.reload();
				}
			});
		}
		
		
	}