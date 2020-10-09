# android

#### References and Memos

- Context とは…
  > Context とは、簡単に言うと、アプリケーションの親情報です。Activity は Context の子クラスとなります。
  > 　 API の中にはちょいちょいこの Context を必要とするものが存在します。
  > 　 Activity も Context なんだったら、this で良いじゃん！！とか思うかもしれませんが、
  > 　 Activity はメモリの逼迫等に引きずられて、簡単に死にます(インスタンスが null)。
  > 　そうなると、参照がなくなった Activity を持ち続けることになり、メモリリークの原因となります。
  > 　その点、getApplicationContext()で取得できる Context 情報は Activity のライフサイクルに依存せず、
  > 　アプリケーションとしての純粋な情報となるため、メモリリークの危険性を回避できます。
  > 　 Context を必要とする API には、可能な限り getApplicationContext()を渡すようにしましょう。
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

- [RecyclerView and ViewHolder](https://qiita.com/naoi/items/f8a19d6278147e98bbc2)
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
- カスタムレイアウト: 既存 View を継承して作成。
- カスタムビューの種類:
  - `フルカスタムコンポーネント`: `View` を継承していちから新たなコンポーネントを作成するパターン
  - `複合コンポーネント`: 既存のコンポーネントを複数組み合わせて新たなコンポーネントを作成するパターン
- While using attributes like `android:textAppearance` it directly uses the predefined specific sp values for the textSize.

```java
android:textAppearance="?android:textAppearanceSmall" // => 14sp
android:textAppearance="?android:textAppearanceMedium" // => 18sp
android:textAppearance="?android:textAppearanceLarge" // => 22sp
```

- `ViewModel`: the purpose of the ViewModel class is to store data somewhere else, outside of the `Activity`
  - `UI controller`: both `Fragments` and `Activities`, which use ViewModels.
  - `transient UI data`, which is data needed for the UI. Examples include data the user enters, data generated during runtime, or data loaded from a database.
  - ViewModels should not hold a reference to `Activities`, `Fragments`, or `Contexts`. Furthermore, ViewModels should not contain `elements that contain references to UI controllers, such as Views`, since this will create an indirect reference to a Context. The reason you shouldn’t store these objects is that ViewModels outlive your specific UI controller instances — if you rotate an Activity three times, you have just created three different Activity instances, but you only have one ViewModel.
  - If you need an Application context, you should extend `AndroidViewModel` which is simply a ViewModel that includes an Application reference.
  - 再作成されたアクティビティが受け取る ViewModel インスタンスは、`最初のアクティビティで作成されたものと同じ`。オーナーのアクティビティが終了すると、フレームワークは、リソースをクリーンアップできるように `ViewModel` オブジェクトの `onCleared()` メソッドを呼び出す。
  - ViewModel オブジェクトには `LifecycleObservers`（`LiveData` オブジェクトなど）を含めることができる。
- MVVM: View Controllers -> ViewModel(Live Data) -> Repository -> (Model(Room) -> SQLite) | Remote Data Source(Retrofit) -> Web Services [ref](https://developer.android.com/jetpack/docs/guide#overview)
 - アクティビティとフラグメントはビューモデルにのみ依存します。他の複数のクラスに依存するクラスはリポジトリのみです。この例のリポジトリは、永続データモデルとリモートのバックエンド データソースに依l
 - モデルクラスに落とし込んで整えられたデータの取得/保存を、Repository に委任する
 - Model からのデータの取得を Repository に全て委任することで、Repository がデータの取得先を変更したとしても、ViewModel を変更する必要がなくなります
 - Room ライブラリでは、DAO（Data Access Object）を通して、DB にデータを挿入・取得する
