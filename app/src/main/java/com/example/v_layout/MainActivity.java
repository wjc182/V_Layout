package com.example.v_layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.v_layout.adapter.Adapter_StickyLayout;
import com.example.v_layout.adapter.ColumnLayoutHelperAdapter;
import com.example.v_layout.adapter.FloaltAdafel;
import com.example.v_layout.adapter.GrildAdpter;
import com.example.v_layout.adapter.OnePlusNLayoutHelperAdapter;
import com.example.v_layout.adapter.OnePlusNLayoutHelperAdapter2;
import com.example.v_layout.adapter.ScrollFixLayoutHelperAdafel;
import com.example.v_layout.api.ApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
 private  RecyclerView recyclerView;
 private Button bu_SouSu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //rec列表
        recyclerView=findViewById(R.id.rec_main);
        //搜索按钮
        bu_SouSu=findViewById(R.id.bu_SouSu);

       //初始化有一个布局管理器
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        //布局管理
        recyclerView.setLayoutManager(virtualLayoutManager);
        //如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        // 创建栏布局对象
        ColumnLayoutHelper columnLayoutHelper = new ColumnLayoutHelper();
        // 公共属性
        columnLayoutHelper.setItemCount(2);// 设置布局里Item个数
        columnLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        columnLayoutHelper.setMargin(20, 10, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        columnLayoutHelper.setBgColor(Color.WHITE);// 设置背景颜色
        columnLayoutHelper.setAspectRatio(2.5f);// 设置设置布局内每行布局的宽与高的比
        // columnLayoutHelper特有属性
        columnLayoutHelper.setWeights(new float[]{100});// 设置该行每个Item占该行总宽度的比例
       //适配器
        ColumnLayoutHelperAdapter helperAdapter = new ColumnLayoutHelperAdapter(columnLayoutHelper,this);


        //网格布局
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
        // 公共属性
        gridLayoutHelper.setItemCount(5);// 设置布局里Item个数
        gridLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        gridLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        gridLayoutHelper.setBgColor(Color.WHITE);// 设置背景颜色
        gridLayoutHelper.setAspectRatio(1.5f);// 设置设置布局内每行布局的宽与高的比
        // gridLayoutHelper特有属性（下面会详细说明）
        gridLayoutHelper.setWeights(new float[]{20, 20, 20, 20, 20});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        gridLayoutHelper.setVGap(20);// 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(20);// 控制子元素之间的水平间距
        gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
        gridLayoutHelper.setSpanCount(5);// 设置每行多少个网格
        GrildAdpter grildAdpter = new GrildAdpter(gridLayoutHelper,this);

        //0托多
        OnePlusNLayoutHelper onePlusNLayoutHelper = new OnePlusNLayoutHelper(5);
        // 在构造函数里传入显示的Item数
        // 最多是1拖4,即5个
        // 公共属性
        onePlusNLayoutHelper.setItemCount(2);// 设置布局里Item个数
        onePlusNLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        onePlusNLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        onePlusNLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        onePlusNLayoutHelper.setAspectRatio(2);// 设置设置布局内每行布局的宽与高的
        OnePlusNLayoutHelperAdapter onePlusNLayoutHelperAdapter = new OnePlusNLayoutHelperAdapter(onePlusNLayoutHelper, this);


        /**
         设置浮动布局
         */
        FloatLayoutHelper floatLayoutHelper = new FloatLayoutHelper();
        // 创建FloatLayoutHelper对象

        // 公共属性
        floatLayoutHelper.setItemCount(1);// 设置布局里Item个数
        // 从设置Item数目的源码可以看出，一个FixLayoutHelper只能设置一个
        floatLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        floatLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        floatLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        floatLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
        // floatLayoutHelper特有属性
        floatLayoutHelper.setDefaultLocation(300,300);// 设置布局里Item的初始位置
        FloaltAdafel floaltAdafel = new FloaltAdafel( this,floatLayoutHelper);

        /**
         设置吸边布局
         */
        StickyLayoutHelper stickyLayoutHelper = new StickyLayoutHelper();

        // 公共属性
        stickyLayoutHelper.setItemCount(1);// 设置布局里Item个数
        stickyLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        stickyLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        stickyLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        stickyLayoutHelper.setAspectRatio(3);// 设置设置布局内每行布局的宽与高的比
        // 特有属性
        stickyLayoutHelper.setStickyStart(true);
        // true = 组件吸在顶部
        // false = 组件吸在
        stickyLayoutHelper.setOffset(200);// 设置吸边位置的偏移量
        Adapter_StickyLayout adapter_stickyLayout = new Adapter_StickyLayout(this,stickyLayoutHelper);


        //固定布局
        ScrollFixLayoutHelper scrollFixLayoutHelper = new ScrollFixLayoutHelper(ScrollFixLayoutHelper.TOP_RIGHT,0,0);
        // 参数说明:
        // 参数1:设置吸边时的基准位置(alignType) - 有四个取值:TOP_LEFT(默认), TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
        // 参数2:基准位置的偏移量x
        // 参数3:基准位置的偏移量y
        // 公共属性
        scrollFixLayoutHelper.setItemCount(1);// 设置布局里Item个数
        // 从设置Item数目的源码可以看出，一个FixLayoutHelper只能设置一个
//        @Override
//        public void setItemCount(int itemCount) {
//            if (itemCount > 0) {
//                super.setItemCount(1);
//            } else {
//                super.setItemCount(0);
//            }
//        }
        scrollFixLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        scrollFixLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        scrollFixLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        scrollFixLayoutHelper.setAspectRatio(3);// 设置设置布局内每行布局的宽与高的比
        // fixLayoutHelper特有属性
        scrollFixLayoutHelper.setAlignType(FixLayoutHelper.TOP_RIGHT);// 设置吸边时的基准位置(alignType)
        scrollFixLayoutHelper.setX(30);// 设置基准位置的横向偏移量X
        scrollFixLayoutHelper.setY(50);// 设置基准位置的纵向偏移量Y
        scrollFixLayoutHelper.setShowType(ScrollFixLayoutHelper.SHOW_ON_ENTER);// 设置Item的显示模式
        ScrollFixLayoutHelperAdafel adafel1 = new ScrollFixLayoutHelperAdafel(this, scrollFixLayoutHelper);

        //banner
        // 创建栏布局对象
        ColumnLayoutHelper columnLayoutHelper1 = new ColumnLayoutHelper();
        // 公共属性
        columnLayoutHelper.setItemCount(2);// 设置布局里Item个数
        columnLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        columnLayoutHelper.setMargin(20, 10, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        columnLayoutHelper.setBgColor(Color.WHITE);// 设置背景颜色
        columnLayoutHelper.setAspectRatio(2.5f);// 设置设置布局内每行布局的宽与高的比
        // columnLayoutHelper特有属性
        columnLayoutHelper.setWeights(new float[]{100});// 设置该行每个Item占该行总宽度的比例
        //适配器
        ArrayList<Bean.DataDTO> list = new ArrayList<>();
        OnePlusNLayoutHelperAdapter2 adapter2 = new OnePlusNLayoutHelperAdapter2(columnLayoutHelper1, this, list);

        new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.baseUrl)
                .build()
                .create(ApiService.class)
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Bean bean) {
                        List<Bean.DataDTO> data = bean.getData();
                        list.addAll(data);
                        adapter2.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        // 共有的适配器
        DelegateAdapter adapter = new DelegateAdapter(virtualLayoutManager, true);
        //添加适配器
        adapter.addAdapter(adapter2);
        adapter.addAdapter(helperAdapter);
        adapter.addAdapter(adapter_stickyLayout);
        adapter.addAdapter(grildAdpter);
        adapter.addAdapter(onePlusNLayoutHelperAdapter);
        adapter.addAdapter(floaltAdafel);
        adapter.addAdapter(adafel1);
        recyclerView.setAdapter(adapter);
        
    }
}