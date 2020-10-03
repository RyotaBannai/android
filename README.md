# android
#### References and Memos
- [Alergdialog](https://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-android)
- CheckBox クラスは `CompoundButton`クラスのサブクラスである.(CheckBox の親は TextView)`CompoundBotton`クラスは Button クラスのサブクラスで２つ以上の情報を保持するためのクラス.
- [Toast](https://developer.android.com/guide/topics/ui/notifiers/toasts)
- [add aar package](https://stackoverflow.com/questions/16682847/how-to-manually-include-external-aar-package-using-new-gradle-android-build-syst)
- [Google Map api v3 on Android](https://developers.google.com/maps/documentation/android-sdk/start#None-java)
- [状態の保存に関して](https://developer.android.com/topic/libraries/architecture/saving-states)
- AdapterView -> ListView class. AdapterView class は アダプターを使って別に用意されたデータを小要素として持つ View. 例えばリストは、リストに表示される個々のデータが小要素となり、個々のデータはアダプターを使ってリストに割り当てられる.
- inflate 方法 [ref](https://akira-watson.com/android/inflate.html)
```java
getLayoutInflator().inflate(R.layout.inflate_layout, root);
// Or
Context context = getApplicationContext();
LayoutInflater inflater = LayoutInflater.from(context);
// Or LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
inflaterinflate(R.layout.inflate_layout, root)
```
- intent で「上方向へのナビゲーションを追加する」つまりヘッダーに戻るボタンを自動的に設置する [ref](https://developer.android.com/training/basics/firstapp/starting-activity#Up)
```xml
<activity android:name=".DisplayMessageActivity"
          android:parentActivityName=".MainActivity">
    <meta-data android:name="android.support.PARENT_ACTIVITY"
               android:value=".MainActivity" />
</activity>
```
- [Fragment passing arguments](https://qiita.com/Reyurnible/items/dffd70144da213e1208b)
- [dp に関して](https://qiita.com/nein37/items/0a92556a80c6c14503b2)
- [Constraint Layout](https://qiita.com/nakker1218/items/0faa8c1ab504cc4cedea)
