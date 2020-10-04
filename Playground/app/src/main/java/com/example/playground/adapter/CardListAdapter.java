package com.example.playground.adapter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.playground.R;

public class CardListAdapter extends ArrayAdapter<PackageInfo> {
    LayoutInflater inflater;
    PackageManager packageManager;

    public CardListAdapter(Context context) {
        super(context, 0);
        inflater = LayoutInflater.from(context);
        packageManager = context.getPackageManager();
    }

    /*
    * getView は画面をスクロールし、新しい一行が表示されるたびに呼ばれる.
    *
    * 「一行の UI を生成する」という作業がなかなか処理が重い.
    * Android 側で既にその対策は考えられていて、実は View を再利用する仕組みがある。
    * 画面をスクロールして表示領域から消えた View (一行)は、破棄されず、
    * スクロールして表示されようとする次の新しい行に対してインスタンスを再利用しようとします。
    * それが convertView になります。
    *
    * 再利用できる View が渡った場合は、convertView に null でないオブジェクトが渡ります。
    *
    * */

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        /*
        * inflate を使用して一行分のレイアウト情報を読み込む
        * */

        if (convertView == null) {
            // 再利用可能な View があったらそれを使う。なかったら(= null) 新しく作成
            convertView = inflater.inflate(R.layout.adapter_list_item_card, parent, false);
        }

        PackageInfo info = getItem(position);

        TextView tv = (TextView) convertView.findViewById(R.id.title);
        tv.setText(info.applicationInfo.loadLabel(packageManager));

        tv = (TextView) convertView.findViewById(R.id.sub);
        tv.setText(info.packageName + "\n" + "versionName :" + info.versionName + "\nversionCode : " + info.versionCode);

        ImageView iv = (ImageView) convertView.findViewById(R.id.icon);
        iv.setImageDrawable(info.applicationInfo.loadIcon(packageManager));

        return convertView;

    }
}
