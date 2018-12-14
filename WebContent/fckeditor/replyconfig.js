
//挑选自己的工具栏
FCKConfig.ToolbarSets["classnet"] = [
	[	
		'Bold','Italic','Underline',
		'JustifyLeft','JustifyCenter','JustifyRight',
		'Table','SpecialChar',
		'FontFormat',
		'Undo','Redo','SelectAll','RemoveFormat',
	]
] ;

//修改字体，这个功能没考虑在工具栏，只是列出
FCKConfig.FontNames	='宋体';
FCKConfig.FontSizes ='9/最小;12/较小;16/中等;20/较大;24/最大';

//修改 回车 与 shift+回车的功能
FCKConfig.EnterMode = 'br' ;			// p | div | br
FCKConfig.ShiftEnterMode = 'p' ;	// p | div | br

//表情图片编辑--引用的表情包
FCKConfig.SmileyPath	= FCKConfig.BasePath + 'images/smiley/fav/' ;
//罗列出要显示的表情图片名称
FCKConfig.SmileyImages	= ['00.gif','01.gif','02.gif','03.gif','04.gif','05.gif','06.gif','07.gif','08.gif','09.gif','10.gif','11.gif','12.gif',
							'13.gif','14.gif','15.gif','16.gif','17.gif','18.gif','19.gif','20.gif','21.gif','24.gif','25.gif','26.gif','27.gif',
							'28.gif','29.gif','30.gif','31.gif','32.gif','33.gif','34.gif','35.gif','89.gif'] ;
FCKConfig.SmileyColumns = 8 ;
FCKConfig.SmileyWindowWidth		= 480 ;
FCKConfig.SmileyWindowHeight	= 320 ;

// 5. 设置允许上传的图片类型的扩展名列表
FCKConfig.ImageUploadAllowedExtensions	= ".(jpg|gif|jpeg|png|bmp)$" ;

// 其它需要修改的配置 ... 

FCKConfig.LinkDlgHideTarget		= true; // false ;
FCKConfig.LinkDlgHideAdvanced	= true; // false ;

FCKConfig.ImageDlgHideLink		= true; // false ;
FCKConfig.ImageDlgHideAdvanced	= true; // false 

FCKConfig.LinkUpload = false;
//上传图片的请求的路径
FCKConfig.ImageUploadURL = 'http://localhost:9080/classnet/upload.do';
