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
- アクティビティとフラグメントはビューモデルにのみ依存します。他の複数のクラスに依存するクラスはリポジトリのみです。この例のリポジトリは、永続データモデルとリモートのバックエンド データソースに依 l
- モデルクラスに落とし込んで整えられたデータの取得/保存を、Repository に委任する
- Model からのデータの取得を Repository に全て委任することで、Repository がデータの取得先を変更したとしても、ViewModel を変更する必要がなくなります
- Room ライブラリでは、DAO（Data Access Object）を通して、DB にデータを挿入・取得する
- `POJO` class act as the method return type.

### Room

- `Entity`: Annotated class that describes a database table when working with Room.
- `SQLite database`: On device storage. The Room persistence library creates and maintains this database for you.
- `DAO`: Data access object. A mapping of SQL queries to functions. When you use a DAO, you call the methods, and Room takes care of the rest.
- `Room database`: Simplifies database work and serves as an access point to the underlying SQLite database (`hides SQLiteOpenHelper`). The Room database uses the DAO to issue queries to the SQLite database.
- `Repository`: Used to manage multiple data sources.

### WorkManager

- `アプリが終了した場合`や`デバイスが再起動した場合`でも実行される、`延期可能な（つまり、直ちに実行する必要がない処理）非同期タスクのスケジュールを簡単に設定する`ための API
- `FirebaseJobDispatcher`、`GcmNetworkManager`、`ジョブ スケジューラ`など、以前のすべての Android バックグラウンド スケジューリング API の代わりに使用することが推奨されている
  - `API 23+` であれば、内部は `Job Scheduler` が使用される
- `処理の制約`: 処理の制約を使用して、処理の実行に最適な条件を宣言的に定義（たとえば、デバイスが Wi-Fi につながっているとき、アイドル状態のとき、十分な保存容量があるときにだけ実行など）
- `柔軟なスケジューリング` ウィンドウを使用して、1 回限り、または繰り返し実行するように処理のスケジュールを設定できる。`処理にタグや名前を付けて`、`一意で置き換え可能な処理のスケジュール設定`、`処理グループのモニタリングやキャンセルをまとめて行う`ことができる。スケジュール設定された処理は、内部で管理されている SQLite データベースに保存される。WorkManager により、デバイスの再起動後もこの処理が確実に保持され、スケジュールの再設定が行われる。
- `柔軟な再試行ポリシー`: 処理は失敗することもあるため、WorkManager には、設定可能な`指数バックオフ ポリシー`など、`柔軟な再試行ポリシー`が用意されている。
- `処理の連結`: 複雑な作業の場合は、シームレスで自然なインターフェースを使用して個々の処理タスクを連結することにより、どの部分を順次実行し、どの部分を並列実行するかを制御できる。処理を連結すると、WorkManager により、`1 つの処理タスクの出力データが自動的に次の処理タスクに渡される`(メソッドチェーンのような感じ)
-

```java
WorkManager.getInstance(...)
    .beginWith(Arrays.asList(workA, workB))
    .then(workC)
    .enqueue();
```

- `フレックス期間`のある定期的な処理を定義する: `repeatInterval` と一緒に `flexInterval` を渡す
  - `フレックス期間`は `repeatInterval - flexInterval` から始まり、`間隔の最後`まで続く
  - 繰り返し間隔は `PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS` 以上で、フレックス間隔は `PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS` 以上である必要がある
  - `定期的な処理に対する制約の影響`: たとえば、ユーザーのデバイスが充電中の場合にのみ処理が実行されるように、WorkRequest に制約を追加できるが。この場合、定義された繰り返し間隔が経過しても、この条件が満たされるまで PeriodicWorkRequest は実行されない。そのため、`実行間隔内に条件が満たされない場合`、特定の処理の実行が`遅延`したり、`スキップ`されたりする可能性がある。[制約一覧](https://developer.android.com/reference/androidx/work/Constraints#requiresBatteryNotLow)
  - `処理の実行中に制約が満たされなくなる`と、WorkManager がワーカーを`停止`. その後、`すべての制約が満たされる`と、処理が`再試行`

```java
WorRequest saveRequest = new PeriodicWorkRequest
                          .Builder(SaveImageToFolderWorker.class,
                            1, TimeUnit.HOURS,
                            15, TimeUnit.MINUTES
                            )
                          .build();
```

- `バックオフポリシー`: `Result.retry()` を使って再試行する際に使用。最小バックオフ遅延は 10 秒、ポリシーが `LINEAR`に設定されているので、１試行ごとに 10 秒増加される。つまり 10 秒後に試行、その 20 秒後に試行,,, `EXPONENTIAL` も使える.
-
