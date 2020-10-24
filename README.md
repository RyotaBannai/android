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

- `Marshalling` or `Marshaling` is the process of transforming the memory representation of an object to a data format suitable for storage or transmission, and it is typically `used when data must be moved between different parts of a computer program or from one program to another`. Marshalling is `similar to serialization` and is used to communicate to remote objects with an object, in this case a serialized object. It `simplifies complex communication, using composite objects in order to communicate instead of primitives`. The inverse of marshalling is called `unmarshalling` (or `demarshalling`, similar to `deserialization`).

#### UI の保持

- アプリがバックグラウンドにあり、システムはアプリのプロセスをメモリに保持しようと最善を尽くすが、ユーザーが他のアプリを操作している間にアプリのプロセスがシステムによって破棄されることがある。この場合、アクティビティインスタンスはそのインスタンスに保存されているすべての状態と一緒に破棄される。

  - Android の基本的な特徴のうち、一般的ではないものとして、`アプリケーション プロセスの存続期間がアプリケーションによって直接制御されない`ということがあります。存続期間は、システムによって、実行中であることが認識されているアプリケーション、ユーザーにとっての重要度、システム全体での空きメモリ量の組み合わせから決定されます。

- プロセスのライフサイクルに関連する不具合としてよくあるのは、`BroadcastReceiver` が `BroadcastReceiver.onReceive()` メソッド内でインテントを受け取る際にスレッドを開始し、関数から戻ってしまう場合です。関数から戻ると、BroadcastReceiver が無効になったため、ホストプロセスが不要になった（プロセス中で他のアプリケーション コンポーネントが有効でない場合）とシステムにみなされます
- [reference](https://developer.android.com/guide/components/activities/process-lifecycle.html)

#### onSaveInstanceState

- いつ呼ばれるか：`onPause`の直後
- いつ復元できるか：`onSavedInstanceState`で保存した値は、`onCreate`もしくは`onRestoreInstanceState`(null チェック不要)で復元が可能

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState != null) {
        score = savedInstanceState.getInt(STATE_SCORE);
        level = savedInstanceState.getInt(STATE_LEVEL);
    }
    ...
}

@Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        score = savedInstanceState.getInt(STATE_SCORE);
        level = savedInstanceState.getInt(STATE_LEVEL);
    }
```

- Bundle はどこに保存される: Bundle は前述のタイミングで onSavedInstanceState が呼ばれた後、`アプリのプロセス`から `SystemServer のプロセス`の `ActivityRecord`(`各 Activity のキャッシュのようなもの)`に保存される。SystemServer プロセスは前面に出ているアプリや、通話アプリのような永続化プロセスよりも優先度が高くなっているため、`相当な異常がない限りは kill されない`
- 使い方: `onSaveInstanceState()` に保存したい内容を記述する

```java
@Override
private void onSaveInstanceState(@Nonnull Bundle outState){
  outState.putInt(STATE_INDEX, 1);
  super.onStateInstanceState(outState);
}
```

#### Misc

- [Alergdialog](https://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-android)
- CheckBox クラスは `CompoundButton`クラスのサブクラスである.(CheckBox の親は TextView)`CompoundBotton`クラスは Button クラスのサブクラスで２つ以上の情報を保持するためのクラス.
- [Toast](https://developer.android.com/guide/topics/ui/notifiers/toasts)
- [add aar package](https://stackoverflow.com/questions/16682847/how-to-manually-include-external-aar-package-using-new-gradle-android-build-syst)
- [Google Map api v3 on Android](https://developers.google.com/maps/documentation/android-sdk/start#None-java)
- [状態の保存に関して](https://developer.android.com/topic/libraries/architecture/saving-states)
- `AdapterView` -> `ListView` class. `AdapterView` class は アダプターを使って別に用意されたデータを小要素として持つ `View`. 例えばリストは、リストに表示される個々のデータが小要素となり、個々のデータはアダプターを使ってリストに割り当てられる.
- 縦に長い View を表示するのに `ScrollView`（ViewGroup）を使うことがあるが、`ListView`はその ScrollView の子クラスである.
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

### ViewModel

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

### LiveData

- LiveData はアクティブ(ライフサイクルの状態が STARTED または RESUMED)な Observer （UI コントローラ）のみ通知する
- Observer は、`LifecycleOwner` インターフェースを実装するオブジェクトとペアで登録できる。このようにペアリングすることで、対応する Lifecycle オブジェクトの状態が `DESTROYED` に変わったときに Observer を削除できる。
- LiveData には、格納されているデータを更新するための公開メソッドはない。LiveData オブジェクトに格納されている値を編集する必要がある場合は、`MutableLiveData` クラスで公開されている `setValue(T)` メソッドと `postValue(T)` メソッドを使用する必要がある。

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

#### 処理の状態

- 1 回限りの処理 Worker の状態で `SUCCEEDED`, `FAILED`, `CANCELLED`のいずれかの`終了状態`にある時には`WorkInfo.State.isFinished()`は `true`を返す。
- 定期的な処理の状態では, `CANCELLED` のみが終了状態（RETRY, SUCCESS, FAILURE いずれの場合も次の状態が ENQUEUED になる。）
- ブロック状態: 最終状態の 1 つである `BLOCKED` は、一連の処理、つまり`処理チェーン`にまとめられた処理に適用される

#### 処理の管理

- `一意処理`: 特定の名前が付けられた処理インスタンスが同時に 1 つだけ存在することを保証する強力な概念

  - `WorkManager.enqueueUniqueWork()`: 1 回限りの処理の場合
  - `WorkManager.enqueueUniquePeriodicWork()`: 定期的な処理の場合
    - `uniqueWorkName` - WorkRequest を一意に識別するために使用される String。
    - `existingWorkPolicy` - 一意の名前が付けられた未完了の処理チェーンがまだ存在する場合に WorkManager が行う必要がある処理を指定する enum（REPLACE, KEEP など）。
    - `work` - スケジュール設定を行う WorkRequest。

- `WorkQuery`: キューに登録されたジョブに関する複雑なクエリを行うことができる。
  - `getWorkInfoByIdLiveData` も使えるため、`WorkQuery`で指定した条件の時に特定の処理を走らせることができる。

```java
WorkQuery workQuery = WorkQuery.Builder
       .fromTags(Arrays.asList("syncTag"))
       .addStates(Arrays.asList(WorkInfo.State.FAILED, WorkInfo.State.CANCELLED))
       .addUniqueWorkNames(Arrays.asList("preProcess", "sync")
     )
    .build();
```

- `作業チェーン`：最初の `OneTimeWorkRequest` が作業リクエスト チェーンでキューに登録されると、後続のすべての作業リクエストは、その最初の作業リクエストの`作業が完了するまでブロックされる`
  - `再試行ポリシーが定義されていないか使い果たされている場合`、または `OneTimeWorkRequest が Result.failure() を返すようななんらかの状態に達した場合`は、`その作業リクエストとすべての依存する作業リクエストが FAILED.` としてマークされる
  - 同様に CANCELLED の場合は、他のタスクも CANCELLED としてマークされることになる。
  - [reference](https://developer.android.com/topic/libraries/architecture/workmanager/how-to/chain-work)

### ListenableFuture

- allows you to `register callbacks to be executed once the computation is complete`, or `if the computation is already complete, immediately`.
- [reference to Guava ListenableFuture](https://github.com/google/guava/wiki/ListenableFutureExplained)

### Service

- A service is a component which `runs in the background without direct interaction with the user`. As the service has no user interface, it is `not` bound to the lifecycle of an activity.
- By default, a service runs in the same process as the main thread of the application. A commonly used pattern for a service implementation is `to create and run a new Thread in the service to perform the processing in the background` and then to terminate the service once it has finished the processing.
- `Custom services` are started from other `Android components`, i.e., `activities`, `broadcast receivers` and `other services`.

#### When to use Service

- If the user buys an MP3 through your app, and you need to download that MP3 file, an `AsyncTask` is not a good solution.
  > That could easily take over a second. While the download is going on, the user could switch away from the app (e.g., press HOME). At that point, your process is eligible to be terminated... perhaps before your download is complete. Using an `IntentService` to manage the download is a signal to the OS that you are really doing work here, adding value to the user, and so `the process will be left alone` for a little while.
- Note that if the background work might take `15+ seconds`, `WakefulBroadcastReceiver` or my `WakefulIntentService` is probably a good idea, so `the device does not fall asleep`while you are trying to wrap up this bit of work.
- [reference to StackOverFlow](https://stackoverflow.com/questions/21148724/when-to-use-and-when-not-to-use-a-service-in-android)

- A `service` is only started once, no matter how often you call the `startService()` method
  > What if you call this method twice in your code? Do you have to worry about synchronizing the onStartCommand() method call? No, this method is called by the Android system in the main user interface thread, therefore it cannot be called simultaneously from two different threads.
- Android はバックグラウンド アプリによるバッグラウンド サービスの作成を許可しない。そのため、Android 8.0 では、`フォアグラウンドで新しいサービスを作成する` `Context.startForegroundService()` メソッドが新たに導入されている。

  > Service がバックグラウンドで動作するので、非同期と勘違いしそうですがそうではなく、Activity から表示 UI を無くしたようなコンポーネントです。ですから別スレッドでの処理が必要なものは Activity と同じように扱います。(つまり、Service もデフォルトではメインスレッドを使っている)

  > システムによってサービスが作成されると、アプリは、サービスの startForeground() メソッドを 5 秒以内に呼び出して、その新しいサービスの通知をユーザーに表示します。
  > アプリが startForeground() を制限時間内に呼び出さない場合、サービスが停止し、アプリが ANR になります。

  > `Foreground Service` とは、通常のサービスと違い、通知（Notification）を表示し、バックグラウンドで実行している事をユーザに認識させた状態で実行するものです。
  > 処理自体は Main スレッドとは別スレッドで実行されているが、ユーザが通知を通じ認識できるため、Foreground Service となります。

- [reference](https://akira-watson.com/android/service.html#4)

- `IntentService`

  - ワークキューを使った順次処理を行える。ワークキューを使って要求されたタスクを一つづつ実行し、
    全てが終わると自ら終了してくれる。(Service では 作業が終わっても `stopSelf()` や `stopService()` を呼び出して自身でサービスを停止しないと行けない場合がある)
  - 簡単に別スレッドを使うことができるのが IntentService

- Communication b/w Activity and Service with `sendBroadcast(intent)` in Service and `onReceive(Context context, Intent intent)` in Activity

  - [ref](https://www.vogella.com/tutorials/AndroidServices/article.html)

- リモート プロセスと通信するサービスが必要な場合は、`Messenger` を使用してサービスのインターフェースを提供できる。この方法では、AIDL を使用しなくても`プロセス間通信`（`IPC`）を実行できる。
  - ほとんどのアプリにおいて、サービスはマルチスレッドを実行する必要がないため、Messenger を使用することで、`サービスが一度に 1 つの呼び出し`を処理できる。
  - サービスのマルチスレッド化が重要である場合は、`AIDL` を使用してインターフェースを定義する。

#### WindowManager

- [reference](https://akira-watson.com/android/windowmanager.html)

#### ANR

- ANR とはメインスレッド上で重たい処理を行った際に表示される警告で、ユーザーには「〜は応答していません」というダイアログとして表示されます。ここでいう重たい処理というのは具体的には以下の通りです。
  - メインスレッド上で 5 秒以上かかる処理を実行する
  - BroadcastReceiver が 10 秒以内で終了しない
- [reference](http://yuyakaido.hatenablog.com/entry/2014/12/30/161157)

### SharedPreference (共有環境設定)

- インスタンスの取得方法

```java
Context#getPreferences(int) // getPreferences(Context.MODE_PRIVATE); // Activity 専用のSP. モードを引数として渡す
Context#getSharedPreferences(String, int) // getSharedPreferences("SaveData", Context.MODE_PRIVATE); // 名前で識別される複数の共有環境設定ファイルが必要な場合. 引数で保存データの名前とモードを渡す
PreferenceManager#getDefaultSharedPreferences(Context) // PreferenceManager.getDefaultSharedPreferences(getApplicationContext()); // getDefaultSharedPreferences で取得したときのモードはデフォルトで MODE_PRIVATE
```

- ファイルパス: `/data/data/[パッケージ名]/shared_prefs/`
- ファイル名:

  - getPreferences(int)で作成した場合: `[Activityのクラス名].xml`
  - getSharedPreferences("SaveData", int)で作成した場合: `SaveData.xml`
  - getDefaultSharedPreferences(Context)で作成した場合: `[パッケージ名]_preferences.xml`

- 保存したデータのモードについて

| モード名             | 効果                         | 備考                 |
| -------------------- | ---------------------------- | -------------------- |
| MODE_PRIVATE         | 自アプリのみ読み書き可能     |                      |
| MODE_WORLD_READABLE  | 他アプリから読み取り可能     | API Level17 で非推奨 |
| MODE_WORLD_WRITEABLE | 他アプリから書き込み可能     | API Level17 で非推奨 |
| MODE_MULTI_PROCESS   | 複数のプロセスで読み書き可能 | API Level23 で非推奨 |

- `apply()` は、すぐにメモリ内の `SharedPreferences` オブジェクトを変更するが、更新内容は`非同期`でディスクに書き込まれる。他方、`commit()` を使用すると、データを`同期的`にディスクに書き込むことができる。ただし、`commit()` は同期的であり、UI レンダリングを一時停止する可能性があるため、メインスレッドからは呼び出さないようにする。

### AsyncTask

- AsyncTask を使うと Thread や Runnable を意識することなく、 メインスレッドとは別のスレッドで処理を行うことができる。

- `onPreExecute()`: `doInBackground`メソッドの実行前にメインスレッドで実行される
  - 非同期処理前に何か処理を行いたい時などに使うことができる。

-`doInBackground()`: メインスレッドとは別のスレッドで実行される
　- 非同期で処理したい内容を記述。 このメソッドだけは必ず実装する必要がある。
　- doInBackground メソッドから画面を操作しようとすると例外が発生する

- `onProgressUpdate()`: メインスレッドで実行される

  - 非同期処理の進行状況をプログレスバーで表示したい時などに使うことができる。

- `onPostExecute()`: `doInBackground`メソッドの実行後にメインスレッドで実行される
  - `doInBackground` メソッドの戻り値をこのメソッドの引数として受け取り、その結果を画面に反映させることができる

#### Background Task

- 一般に、数ミリ秒以上かかるタスクはすべて、`バックグラウンド スレッド`に委任する必要がある。長時間実行される一般的なタスクは、`ビットマップのデコー`ド、`ストレージへのアクセス`、`機械学習（ML）モデルの操作`、ネ`ットワークへのリクエスト`など。
- 即時タスク: ユーザーがアプリをバックグラウンドに移行したり、デバイスが再起動したりしても、直ちに実行して処理を続行する必要があるタスクの場合、WorkManager とその長時間実行タスクのサポートを使用
- 遅延タスク: ユーザー操作に直接つながっておらず、今後いつでも実行できるタスクはすべて延期できる。延期タスクの場合におすすめのソリューションは `WorkManager`。WorkManager を使用すると、`アプリが終了した場合`や`デバイスが再起動した場合`でも、実行する予定の延期可能な非同期タスクのスケジュールを簡単に設定できる
- 定時タスク: 正確な時間どおりに実行する必要があるタスクには、`AlarmManager` を使用
- Executor のいる java.util.concurrent パッケージは Java 1.5 から追加されたパッケージであり、Thread や Runnable、synchronize / wait / notify だけでどうにかするにはあまりにも辛かったマルチスレッド処理を簡便化するためのオブジェクトが集められている。

#### Okhttp

- Interceptor rewriting request and response: https://square.github.io/okhttp/interceptors/#rewriting-requests

#### Local Broadcast

- プロセス状態への影響:
  - プロセスがレシーバーを実行する（コードが `onReceive()` メソッドで実行中である）場合は、`フォアグラウンド プロセス`とみなされ、メモリに対して過度の負荷が発生している場合を除き、システムがプロセスの実行を継続
  - `onReceive()` からコードが返されると、`ブロードキャスト レシーバーがアクティブではなくなり`、レシーバーのホストプロセスの重要度が、内部で実行されているアプリの他のコンポーネントと同程度になります。このホストプロセスがマニフェストで宣言されたレシーバーのみをホストしている場合（`ユーザーが一度もアプリを操作していないか直近の操作がない場合が考えられます`）、`onReceive()` からコードが返されると、システムは対象のプロセスを優先度の低いプロセスとみなし、重要度の高い他のプロセスでリソースを利用できるようにするため、このプロセスを強制終了する可能性がある。
  - `ブロードキャスト レシーバー`から`実行時間の長いバックグラウンド スレッドを開始しないようにする必要がある`。こうするには、`goAsync()` を呼び出す（`バックグラウンド スレッド`で`ブロードキャストを処理`するために、さらに若干の時間が必要な場合）か、`JobScheduler` を使用してレシーバーの `JobService` のスケジュール設定を行い、`プロセスの実行作業が引き続きアクティブであることをシステムが認識できるようにする`
  - [reference](https://developer.android.com/guide/components/broadcasts?hl=ja#effects-process-state)

### デバイスの起動状態を管理する

- 電池の消耗を防ぐため、一定時間アイドル状態になった Android デバイスはすぐにスリープ状態となる。（CUP の）スリープ状態になると http ダウンロードなどアプリの実行が停止してしまうので、画面をオンにする（CUP がアクティブ状態）か、wake lock を使って、画面はオフだけどバックグラウンドで CUP が動いて処理を実行する、というようなことが必要になる。

- 画面をオンのままにする：

1. アクティビティで `FLAG_KEEP_SCREEN_ON` を使用する
   - `wake lock` とは異なり、`特別な権限が不要`で、アプリ間を移動するユーザーをプラットフォームが適切に管理する。`使用されていないリリースの解放をアプリで行う必要はない`。

```java
public class MainActivity extends Activity {
      @Override
      protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // here
      }
    }
```

2. アプリのレイアウト XML ファイルで `android:keepScreenOn` 属性を使用する方法

```java
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:keepScreenOn="true"> // here // activity で FLAG_KEEP_SCREEN_ON する場合と同じ
        ...
    </RelativeLayout>
```

- `デバイスがスリープ状態になる前に処理を完了させるために CPU を動作させ続ける必要がある場合`は、`wake lock` と呼ばれる `PowerManager` のシステム サービス機能を使用。`wake lock` を使用すると、ホストデバイスの電力状態をアプリで管理できる。
  - ホストデバイスの電池寿命に大きな影響を及ぼすことがあるため、wake lock は厳密に必要な場合のみ使用.
  - `wake lock` を使用するのに適したケースの 1 つとして`バックグラウンド サービス`がある。バックグラウンド サービスでは、`wake lock` を取得して CPU を動作させ続け、`画面がオフになっている間`に処理を行う必要がある。
  - Best practice は `WakefulBroadcastReceiver` (この方法でアプリ用の `PARTIAL_WAKE_LOCK` の作成と管理を行う) を介して wake lock を管理すること。
    - `Broadcast` に対して時間がかかる処理を投げるようにして、その中から `startWakefulService`を読んで`partial wake lock` 状態を作る.（下のスニペットを参考）
  - wake lock を開放するには `wakelock.release()` を呼ぶ。

```java
// 純粋な wake lock
PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
  WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
          "MyApp::MyWakelockTag");
  wakeLock.acquire();
```

```java
// partial wake lock
 public class MyWakefulReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Start the service, keeping the device awake while the service is
        // launching. This is the Intent to deliver to the service.
        Intent service = new Intent(context, MyIntentService.class);
        startWakefulService(context, service); // ここで IntentService に処理を投げてしまう
    }
}
...
public class MyIntentService extends IntentService {
    public static final int NOTIFICATION_ID = 1;
    private NotificationManager notificationManager;
    NotificationCompat.Builder builder;
    public MyIntentService() {
        super("MyIntentService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        // Do the work that requires your app to keep the CPU running.
        // ...
        // Release the wake lock provided by the WakefulBroadcastReceiver.
        MyWakefulReceiver.completeWakefulIntent(intent); // サービスの終了時に MyWakefulReceiver.completeWakefulIntent() が呼び出され、wake lock を解放
    }
    }
```

- `AlarmManager(反復アラームのスケジュール設定)`

  - AlarmManager クラスをベースにしたアラームを使用すると、`アプリの実行期間外に`時間ベースで処理を実行できる。たとえば、1 日に 1 回サービスを開始して天気予報をダウンロードするなど、アラームを使用することによって`実行時間が長い処理を開始`できる
  - ネットワーク リクエストを反復アラームは定時に設定しない（`setRepeating()` ではなく `setInexactRepeating()` を使う）（複数のアプリが定時に実行され、サーバに対する負荷・レイテンシの原因になるため）→ リクエストに`ランダム性（ジッター）`を追加。また `setInexactRepeating()` を使うことで Android によって複数のアプリの反復アラーガが同期されて、同時にトリガーされるようになる → デバイスのスリープを解除する回数が減るため、電池の消耗を抑えることができる。
  - `ローカル処理`(サーバーにアクセスしない処理、またはサーバーのデータを必要としない処理)は定時でも問題なし。
  - アラームタイプ：

    - `実経過時間`: リファレンスとして「システムが起動してからの経過時間」を使用 → 実経過時間はタイムゾーンやロケールの影響を受けないため、時間の経過に基づくアラームの設定に適しています（30 秒ごとにトリガーされるアラームなど）
      - `ELAPSED_REALTIME` - デバイスが起動してからの経過時間に基づいてペンディング インテントを開始しますが、デバイスのスリープは`解除しません`。経過時間には、デバイスがスリープしていた時間が含まれます。
      - `ELAPSED_REALTIME_WAKEUP` - デバイスが起動してから指定された時間が経過した後にデバイスの`スリープを解除`し、ペンディング インテントを開始します。
    - `リアルタイム クロック（RTC）`: UTC（ウォール クロック）時間を使用 → リアルタイム クロックは、現在のロケールに依存するアラームに適している
      - `RTC` - スリープは解除しない
      - `RTC_WAKEUP` - 解除する

  - デフォルトでは、`デバイスがシャットダウンするとすべてのアラームがキャンセルされる`。アラームがキャンセルされないようにするには、`ユーザーがデバイスを再起動したときに自動的に反復アラームを再開するようにアプリを設計する`。こうすることで、ユーザーが手動でアラームを再開しなくても `AlarmManager によってタスクの実行が継続される`。同レポジトリの `BootReceiver` を参考

  - アラームは`アプリの外部で動作する`ため、`アプリが実行されていないとき`や、`デバイス自体がスリープ状態になっているとき`でも、アラームを使用してイベントやアクションをトリガーできる
  - `アプリの実行期間中に`必ず実行される時間ベースの処理については、`Handler` クラスを `Timer` および `Thread` と一緒に使用することを検討
  - アプリを実行期間外に処理をトリガーする場合の一般的なシナリオとして、`データをサーバーと同期`がある。このようなケースでは、反復アラームを使用したくなるかもしれませんが、アプリのデータをホストしているサーバーを所有している場合は、`Google Cloud Messaging（GCM）`を`同期アダプター`と組み合わせて使用する方が AlarmManager より効果的なソリューションとなる。
  - Android デバイスが `Doze モード`の場合は、alarm は無視される。強制的に alarm を送りたい場合は `setAndAllowWhileIdle()` と `setExactAndAllowWhileIdle()` のどちらのメソッドを使用.
    (各アプリに対して 9 分に 2 回以上アラームを発生させることができない)
  - メッセージ受信にネットワークへの継続的な接続を必要とするアプリでは、できるだけ `Firebase Cloud Messaging（FCM）`を使用
  - `アプリスタンバイ`：ユーザーがアプリをアクティブに使用していない場合に、システムはアプリがアイドル状態であると判断します。システムがこのように判断するのは、`ユーザーが一定時間アプリをタップしておらず`、次のどの条件にも該当しないときです。
    - ユーザーがアプリを明示的に起動している
    - アプリのプロセスが現在`フォアグラウンド`にある
    - アプリが`ロック画面`や`通知トレイ`に表示される通知を生成する
    - アプリが有効な`デバイス管理アプリ`（`Device Policy Controller` など）である場合。通常、アプリはバックグラウンドで実行されますが、`デバイス管理アプリ`は、サーバーからいつでもポリシーを受信できるように常に使用可能な状態である必要があるため、アプリスタンバイにはなりません
  - デバイスを電源に接続すると、アプリは`スタンバイ状態から抜けて`ネットワークに自由にアクセスできるようになり、保留中のジョブと同期をすべて実行できます。デバイスが長時間アイドル状態の場合、システムによってアイドル中のアプリは `1 日 1 回程度`ネットワーク アクセスを許可されます。
  - `ホワイトリスト`: Doze 最適化とアプリ スタンバイ最適化の対象から`一部除外されるアプリを設定できる`
    - このホワイトリストに含まれるアプリは、Doze モード中やアプリ スタンバイ中でも`ネットワークの使用が可能`で、`部分的な wake lock を保持`できる。ただし、ホワイトリストに含まれるアプリにも、他のアプリと同様に`他の制限は適用される`（AlarmManger のアラームは発生しないなど。アプリがホワイトリストに現在含まれているかどうかを確認するには `isIgnoringBatteryOptimizations()`を呼び出す）
  - `Doze モード・アプリスタンバイに関する詳細`(https://developer.android.com/training/monitoring-device-state/doze-standby?hl=ja#testing_doze)
