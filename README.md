# android
#### References and Memos
- Contextとは…
> Contextとは、簡単に言うと、アプリケーションの親情報です。ActivityはContextの子クラスとなります。
　APIの中にはちょいちょいこのContextを必要とするものが存在します。
　ActivityもContextなんだったら、thisで良いじゃん！！とか思うかもしれませんが、
　Activityはメモリの逼迫等に引きずられて、簡単に死にます(インスタンスがnull)。
　そうなると、参照がなくなったActivityを持ち続けることになり、メモリリークの原因となります。
　その点、getApplicationContext()で取得できるContext情報はActivityのライフサイクルに依存せず、
　アプリケーションとしての純粋な情報となるため、メモリリークの危険性を回避できます。
　Contextを必要とするAPIには、可能な限りgetApplicationContext()を渡すようにしましょう。
- [Alergdialog](https://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-android)
- CheckBox クラスは `CompoundButton`クラスのサブクラスである.(CheckBox の親は TextView)`CompoundBotton`クラスは Button クラスのサブクラスで２つ以上の情報を保持するためのクラス.
- [Toast](https://developer.android.com/guide/topics/ui/notifiers/toasts)
- [add aar package](https://stackoverflow.com/questions/16682847/how-to-manually-include-external-aar-package-using-new-gradle-android-build-syst)
- [Google Map api v3 on Android](https://developers.google.com/maps/documentation/android-sdk/start#None-java)
- [状態の保存に関して](https://developer.android.com/topic/libraries/architecture/saving-states)
- AdapterView -> ListView class. AdapterView class は アダプターを使って別に用意されたデータを小要素として持つ View. 例えばリストは、リストに表示される個々のデータが小要素となり、個々のデータはアダプターを使ってリストに割り当てられる.
- 縦に長い View を表示するのに `ScroolView`（ViewGroup）を使うことがあるが、`ListView`はその ScrollView の子クラスである.
- inflate 方法 [ref](https://akira-watson.com/android/inflate.html)
```java
getLayoutInflator().inflate(R.layout.inflate_layout, root);
// Or
Context context = getApplicationContext();
LayoutInflater inflater = LayoutInflater.from(context);
// Or LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
inflater.inflate(R.layout.inflate_layout, root)
```
- [ListView good article](https://qiita.com/yu_eguchi/items/65311af1c9fc0bff0cb0#%E8%A7%A3%E8%AA%AC-1)
```java
// コンテキストから取得
LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
// アクティビティから取得
LayoutInflater inflater = getLayoutInflater();
// システムサービスから取得
LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
