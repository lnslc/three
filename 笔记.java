/**
px-屏幕像素
in-物理英寸
pt-物理点
mm-毫米
dp-非密度制约的像素		 常用
sp-缩放比例无关的像素  字号



打开应用时先后执行了onCreate()->onStart()->onResume()
按BACK键时，我们这个应用程序将结束，这时候我们将先后调用onPause()->onStop()->onDestory()
按HOME的时候，Activity先后执行了onPause()->onStop()
再次启动Activity时，则先后分别执行了onRestart()->onStart()->onResume()

Activity状态 活动：栈顶 可交互 系统全力保证活跃
						暂停：可见但不可交互，在一个透明或非全屏的Activity下时，有可能被释放，释放后进入停止状态
						停止：不可见，有可能被终止，进入非活动状态
						非活动：被终止，从栈中移除
onCreate()-->onRestore()-->onStart()-->onResume()-->onSave()-->onPause()-->onStop()-->onDestory()
     |            |            |           |     <-------------    |          |          |
     |            |            |           |         活动状态       |          |          |
     |            |            |   <---------------onRestart()------------    |          |
     |            |            |                   可见生存期                  |          |
     |            |            |                                              |          |

1. OnLowMemory
      OnLowMemory是Android提供的API，在系统内存不足，所有后台程序（优先级为background的进程，不是指后台运行的进程）都被杀死时，系统会调用OnLowMemory。系统提供的回调有：Application/Activity/Fragementice/Service/ContentProvider
除了上述系统提供的API，还可以自己实现ComponentCallbacks，通过API注册，这样也能得到OnLowMemory回调。例如：
public static class MyCallback implements ComponentCallbacks { 
        @Override
        public void onConfigurationChanged(Configuration arg) { 
        }
 
        @Override
        public void onLowMemory() {
            //do release operation
        }
    }
然后，通过Context.registerComponentCallbacks ()在合适的时候注册回调就可以了。通过这种自定义的方法，可以在很多地方注册回调，而不需要局限于系统提供的组件。
2.  OnTrimMemory
       OnTrimMemory是Android 4.0之后提供的API，系统会根据不同的内存状态来回调。系统提供的回调有：Application/Activity/Fragement/Service/ContentProvider
  OnTrimMemory的参数是一个int数值，代表不同的内存状态：
TRIM_MEMORY_COMPLETE：内存不足，并且该进程在后台进程列表最后一个，马上就要被清理
TRIM_MEMORY_MODERATE：内存不足，并且该进程在后台进程列表的中部。
TRIM_MEMORY_BACKGROUND：内存不足，并且该进程是后台进程。
TRIM_MEMORY_UI_HIDDEN：内存不足，并且该进程的UI已经不可见了。      
      以上4个是4.0增加

TRIM_MEMORY_RUNNING_CRITICAL：内存不足(后台进程不足3个)，并且该进程优先级比较高，需要清理内存
TRIM_MEMORY_RUNNING_LOW：内存不足(后台进程不足5个)，并且该进程优先级比较高，需要清理内存
TRIM_MEMORY_RUNNING_MODERATE：内存不足(后台进程超过5个)，并且该进程优先级比较高，需要清理内存      
       以上3个是4.1增加
系统也提供了一个ComponentCallbacks2，通过Context.registerComponentCallbacks()注册后，就会被系统回调到。
OnLowMemory和OnTrimMemory的比较
1，OnLowMemory被回调时，已经没有后台进程；而onTrimMemory被回调时，还有后台进程。
2，OnLowMemory是在最后一个后台进程被杀时调用，一般情况是low memory killer 杀进程后触发；而OnTrimMemory的触发更频繁，每次计算进程优先级时，只要满足条件，都会触发。
3，通过一键清理后，OnLowMemory不会被触发，而OnTrimMemory会被触发一次。


Fragment：这是个好东西，可用的地方很多																													
merge:套在什么布局下就变成什么布局，为重用而生，用include 加载
ViewStub:ViewStub是一个轻量级的View，它一个看不见的，不占布局位置，占用资源非常小的控件。可以为ViewStub指定一个布局，在Inflate布局的时候，只有ViewStub会被初始化，然后当ViewStub被设置为可见的时候，或是调用了ViewStub.inflate()的时候，ViewStub所向的布局就会被Inflate和实例化，然后ViewStub的布局属性都会传给它所指向的布局。这样，就可以使用ViewStub来方便的在运行时，要还是不要显示某个布局。

      但ViewStub也不是万能的，下面总结下ViewStub能做的事儿和什么时候该用ViewStub，什么时候该用可见性的控制。

     首先来说说ViewStub的一些特点：

         1. ViewStub只能Inflate一次，之后ViewStub对象会被置为空。按句话说，某个被ViewStub指定的布局被Inflate后，就不会够再通过ViewStub来控制它了。

         2. ViewStub只能用来Inflate一个布局文件，而不是某个具体的View，当然也可以把View写在某个布局文件中。

     基于以上的特点，那么可以考虑使用ViewStub的情况有：

         1. 在程序的运行期间，某个布局在Inflate后，就不会有变化，除非重新启动。

              因为ViewStub只能Inflate一次，之后会被置空，所以无法指望后面接着使用ViewStub来控制布局。所以当需要在运行时不止一次的显示和隐藏某个布局，那么ViewStub是做不到的。这时就只能使用View的可见性来控制了。

         2. 想要控制显示与隐藏的是一个布局文件，而非某个View。

              因为设置给ViewStub的只能是某个布局文件的Id，所以无法让它来控制某个View。

     所以，如果想要控制某个View(如Button或TextView)的显示与隐藏，或者想要在运行时不断的显示与隐藏某个布局或View，只能使用View的可见性来控制。
    **动态加载，瀑布流？
    
    

布局  线性：LinerLayout android:orientation="horizontal or vertical" 水平或垂直 独有
											android:layout_width和layout_height wrap_content:表示宽度匹配内容，简单地说就是文字有多长按钮就多长。
																													fill_parent:与match_parent相同，android2.2以后就不推荐使用了。
																													match_parent:表示宽度匹配父内容，按钮外的容器有多宽就显示多宽。
										  android:layout_weight 独有属性，占比 当使用weight属性时，将width设为0dip即可，效果跟设成wrap_content是一样的。这样weight就可以理解为占比了！
																													
		相对布局：RelativeLayout 是一种相对布局，控件的位置是按照相对位置来计算的，后一个控件在什么位置依赖于前一个控件的基本位置，是布局最常用，也是最灵活的一种布局。
														第一类:属性值为true或false
														android:layout_centerHrizontal 水平居中
														android:layout_centerVertical 垂直居中
														android:layout_centerInparent 相对于父元素完全居中
														android:layout_alignParentBottom 贴紧父元素的下边缘
														android:layout_alignParentLeft 贴紧父元素的左边缘
														android:layout_alignParentRight 贴紧父元素的右边缘
														android:layout_alignParentTop 贴紧父元素的上边缘
														android:layout_alignWithParentIfMissing 如果对应的兄弟元素找不到的话就以父元素做参照物
														第二类：属性值必须为id的引用名“@id/id-name”
														android:layout_below 在某元素的下方
														android:layout_above 在某元素的的上方
														android:layout_toLeftOf 在某元素的左边
														android:layout_toRightOf 在某元素的右边
														android:layout_alignTop 本元素的上边缘和某元素的的上边缘对齐
														android:layout_alignLeft 本元素的左边缘和某元素的的左边缘对齐
														android:layout_alignBottom 本元素的下边缘和某元素的的下边缘对齐
														android:layout_alignRight 本元素的右边缘和某元素的的右边缘对齐
														第三类：属性值为具体的像素值，如30dip，40px
														android:layout_marginBottom 离某元素底边缘的距离
														android:layout_marginLeft 离某元素左边缘的距离
														android:layout_marginRight 离某元素右边缘的距离
														android:layout_marginTop 离某元素上边缘的距离
														
		单桢布局:FrameLayout 对象好比一块在屏幕上提前预定好的空白区域，可以将一些元素填充在里面，如图片。所有元素都被放置在FrameLayout区域的最左上区域，而且无法为这些元素制指定一个确切的位置，若有多个元素，那么后面的元素会重叠显示在前一个元素上。
											最简单的布局，用处不大，可以做简单的桢动画
		表格布局：TableLayout 是指将子元素的位置分配到行或列中。Android的一个TableLayout有许多TableRow组成，每一个TableRow都会定义一个Row。TableLayout容器不会显示Row，Column，及Cell的边框线，每个Row拥有0个或多个Cell，每个Cell拥有一个View对象
												android:collapseColumns:将TableLayout里面指定的列隐藏，若有多列需要隐藏，请用逗号将需要隐藏的列序号隔开。             

　　										 android:stretchColumns:设置指定的列为可伸展的列，以填满剩下的多余空白空间，若有多列需要设置为可伸展，请用逗号将需要伸展的列序号隔开。                

　　										 android:shrinkColumns:设置指定的列为可收缩的列。当可收缩的列太宽(内容过多)不会被挤出屏幕。当需要设置多列为可收缩时，将列序号用逗号隔开。列元素(Button)属性：（奇怪的是button 里面没有android:layout_column 和android:layout_span两个属性，写进去无反应，还不知道为什么）

　　										 android:layout_colum:设置该控件在TableRow中指定的列。 无效？待解

　　										 android:layout_span:设置该控件所跨越的列数。无效？待解
												参数都从0开始
												
	 网格布局：GridLayout 4.0才加入的 指定行(rowCount)列(columnCount) 自动排列
	 										android:layout_rowSpan  占n行
	 										android:layout_columnSpan 占n列
	 										android:layout_gravity属性为fill生效
	 										子控件默认按照wrap_content 不必另行设置
	 										
	 										
	 										
组件 EditText：android:inputType 输入类型有很多
							android:maxLength 最大长度
							android:hint 默认文字 
    					android:textColorHint 默认文字颜色
    					android:imeOptions 键盘回车样式
    					
    					editText.selectAll();全选
    					
    					Editable editable=editText.getText();  
              Selection.setSelection(editable, 1,editable.length()); 部分选取
              
              int start=editText.getSelectionStart();  
              int end=editText.getSelectionEnd();  
              CharSequence selectText=editText.getText().subSequence(start, end);  获取选中的文本
              Toast.makeText(HelloEditText.this, selectText, Toast.LENGTH_SHORT).show()获取选中的文本
              
		 Button: 用OnClickListener监听
		 					OnTouchListener 复杂的监听 实现onTouch(View v , MotionEvent event)event包括了触摸时的具体内容，如移动、按下等
		 				android:drawable...  按钮上加图片 不同位置
		 				android:background 按钮图片
		 RadioGroup：单选  RadioGroup里面括RadioButton
		 						用OnCheckedChangeListener监听
		 						android:button="@null" 去除RadioButton前面的圆点
		 						android:checked="true"为默认选中
		 						RadioGroup.getCheckedRadioButtonId ();--获取选中按钮的id
								RadioGroup.clearCheck ();//---清除选中状态
								RadioGroup.check (int id);//---通过参入选项id来设置该选项为选中状态如果传递-1作为指定的选择标识符来清除单选按钮组的勾选状态，相当于调用clearCheck()操作
								setOnCheckedChangeListener (RadioGroup.OnCheckedChangeListener listener); //--一个当该单选按钮组中的单选按钮勾选状态发生改变时所要调用的回调函数
								addView (View child, int index, ViewGroup.LayoutParams params);//---使用指定的布局参数添加一个子视图
								//参数 child 所要添加的子视图    index 将要添加子视图的位置  params 所要添加的子视图的布局参数
								RadioButton.getText();//获取单选框的值
								//此外，RadioButton的checked属性设置为true，代码里调用RadioButton的check(id)方法，不会触发onCheckedChanged事件
		 						自定义样式  要放在drawable文件夹下*
		 									<?xml version="1.0" encoding="utf-8"?>    
											<selector xmlns:android="http://schemas.android.com/apk/res/android">  
											<!-- 未选中->  
											    <item    
											         android:state_checked="false"    
											         android:drawable="@drawable/tabswitcher_long" /> --图片 用android:background="@drawable/radio"
											         android:color="#00ffff"	-文字颜色 用android:textColor="@drawable/radio"
											<!--选中->    
											    <item    
											        android:state_checked="true"    
											        android:drawable="@drawable/tabswitcher_short" /> 
											        android:color="#00ffff"   
											</selector>
		 CheckBox：多选 用OnCheckedChangeListener监听
		 					isChecked()方法用来判断RadioButton和CheckBox控件是否被选中
		 					与RadioButton基本相同
		 					
		 Spinner：下拉列表框 用OnItemSelectedListener监听
		 					android:spinnerMode="dropdown" 下拉菜单
		 					android:spinnerMode="dialog" 弹出框
		 					android:entries 下拉内容，用strings里的<string-array>
		 									也可以用setAdapter(SpinnerAdapter)动态设置
		 									ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        							adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);设置样式
        							android:popupBackground 背景颜色
        							android:gravity 对齐方式 多个可用"|"隔开
		 ImageButton:好看的按钮 android:src 图片
		 							也可以用自定义样式，与RadioGroup基本相同
		 ImageView：图片 android:src 图片  setImageResource()设置图片 可以setAlpha()
		 						动态创建，只能用代码实现
		 						ViewGroup就是个布局
		 						<LinearLayout  
							     android:id="@+id/viewGroup"  
							     android:layout_width="wrap_content"  
							     android:layout_height="wrap_content"  
							     android:gravity="center_horizontal"  
							     android:orientation="vertical" />  
		 						ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);  
								ImageView[] imageViews = new ImageView[images.length];  
								for (int i = 0; i < imageViews.length; i++) {  
								    ImageView imageView = new ImageView(this);  
								    imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));  
								    imageViews[i] = imageView;  
								    imageView.setImageResource(R.drawable.ic_launcher);  
								    group.addView(imageView);  
								}
		 DatePicker：日期选择控件DatePickerDialog应该会比DatePicker常用
		 TimePicker：时间选择 TimePickerDialog
		 ScrollView：滚动视图 功能强大 切记XML文件ScrollView中只能放一个其他控件，如果想加入更多，只能通过java代码形式。
		 ListView: 应该会比ScrollView常用 
		 AlertDialog： 不能直接new  用 AlertDialog.Builder alertDialog = new AlertDialog.Builder(Activity.this)创建
		 						setTitle ：为对话框设置标题
					　　　　setIcon ：为对话框设置图标
					
					　　　　setMessage：为对话框设置内容
					　　　　setView ： 给对话框设置自定义样式
					　　　　setItems ：设置对话框要显示的一个list，一般用于显示几个命令时
					　　　　setMultiChoiceItems ：用来设置对话框显示一系列的复选框
								 setSingleChoiceItems  ：用来设置对话框显示一系列的单选框
								 以上内容互斥，同时只能用一个
								 
					　　　　setNeutralButton ：普通按钮
					　　　　setPositiveButton ：给对话框添加"Yes"按钮
					　　　　setNegativeButton ：对话框添加"No"按钮
					
								 必须有
					　　　　create ： 创建对话框
					　　　　show ：显示对话框
		 ProgressDialog:直接new
										setTitle ：为对话框设置标题
					　　　　   setIcon ：为对话框设置图标
										setMessage：为对话框设置内容
										setProgress:当前进度0-100
										setCancelable:是否可以按退回按键取消
										setButton:可以加按钮
									 *setIndeterminate:是否不明确 条里用  默认为false 显示进度；true不明确，不显示进度，?在加载最开始时候用
			
		 
监听：OnCheckedChangeListener：单选按钮RadioGroup、复选框CheckBox
		OnItemSelectedListener事件 用于Spinner  
															public void onItemSelected(AdapterView<?> adapter,View view,int position,long id)
															public void onNothingSelected(AdapterView<?> arg0)
															两个方法要实现
		OnFocuChangeListener焦点事件 当控件获得焦点时触发 public void onFocusChange(View view,boolean hasFocus)
		OnLongClickListener长按2秒以上触发的事件  public boolean onLongClick(View v) return true截断事件，false则其他监听会继续捕捉
		OnTouchListener 触摸事件 适用范围很多 public boolean onTouch(View v, MotionEvent event)return true截断事件，false则其他监听会继续捕捉
		OnKeyListener 键盘监听 public boolean onKey(View v, int keyCode, KeyEvent event)
		
		


--设置为墙纸		
<uses-permission android:name="android.permission.SET_WALLPAPER"/>
//清空当前墙纸  
MainActivity.this.clearWallpaper();  
//当前view转换为ImageView对象  
ImageView iv=(ImageView)view;  
//启用图形缓冲  
iv.setDrawingCacheEnabled(true);  
//使用当前缓冲图形创建Bitmap  
Bitmap bmp=Bitmap.createBitmap(iv.getDrawingCache());  
//当前图形设置为墙纸  
MainActivity.this.setWallpaper(bmp);  
//清理图形缓冲  
iv.setDrawingCacheEnabled(false); 
															
																													


*/