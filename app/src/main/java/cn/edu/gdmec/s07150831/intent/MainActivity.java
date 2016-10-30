package cn.edu.gdmec.s07150831.intent;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText et1,et2;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.url);
        et2 = (EditText) findViewById(R.id.phone);
        tv1 = (TextView) findViewById(R.id.textView1);
    }

    public void componentname(View v)
    {
        //ComponentName（组件名称）是用来打开其他应用程序中的Activity或服务的
        ComponentName componentName = new ComponentName(this, IntentDemo2.class);
        Intent intent1 = new Intent();
        intent1.setComponent(componentName);
        intent1.putExtra("value",et1.getText().toString());  //加上这句传入参数后，不会出现空指针异常
        //startActivity(intent1);     //无返回值跳转，所以不会显示效果
        startActivityForResult(intent1, 10);  //有返回值跳转，正常显示效果。
    }

    //目的是为了从启动页面MainActivity跳转到IntentDemo2中，目前所写的方法不实现跳转回来
    public void intentfilter(View v)
    {
        String action = "cn.edu.gdmec.kissme";
        Intent intent2 = new Intent();
        intent2.setAction(action);  //参数是要跳转的activity定义的action名，这个action是在AndroidManifest.xml中定义
        intent2.putExtra("value", et1.getText().toString());
        //startActivity(intent2);   //无返回值跳转，所以不会显示效果
        startActivityForResult(intent2, 10);
    }

    public void view(View v)
    {
        Intent intent3 = new Intent();
        intent3.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(et1.getText().toString());
        intent3.setData(uri);
        startActivity(intent3);
    }

    public void dial(View v)
    {
        Intent intent3 = new Intent(Intent.ACTION_DIAL);
        Uri uri = Uri.parse("tel:"+et2.getText().toString());
        intent3.setData(uri);
        startActivity(intent3);
    }

    public void startactivityforresult(View v)
    {
        Bundle bundle = new Bundle();
        bundle.putString("value",et1.getText().toString());
        Intent intent = new Intent(MainActivity.this, IntentDemo2.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 10:
                Bundle bundle = data.getExtras();
                tv1.setText(bundle.getString("result"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
