package com.example.playground.data;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.example.playground.BR;

public class User extends BaseObservable {
    /*
    * extendsを消費するのが嫌な場合は、別の手段としてフィールドをObservableFieldにする方法もある
    * フィールドの型がObservableFieldになってしまうので注意
    * */
//    public ObservableField<String> name = new ObservableField<>();
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Bindable // 監視用の BR.name が生成される。setter で使用できる
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name); // この時に、layout 側から再度 getName() が呼ばれることになる
    }
}
