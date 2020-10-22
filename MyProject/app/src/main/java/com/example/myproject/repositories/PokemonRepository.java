package com.example.myproject.repositories;

import android.util.Log;

import com.example.myproject.common.Result;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.Executor;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * okhttp ref:
 * https://www.vogella.com/tutorials/JavaLibrary-OkHttp/article.html
 * https://qiita.com/riversun/items/5f78d47a3d95f857d34f#okhttp3
 */

public class PokemonRepository {
    private final String TAG = getClass().getSimpleName();
    private final String baseUrl = "https://pokeapi.co/api/v2/";
    private final OkHttpClient client;
    private final Executor executor;
    private Gson gson = new Gson();

    public PokemonRepository(Executor executor) {
        this.client = new OkHttpClient();
        this.executor = executor;
    }

    public interface RepositoryCallback<T> {
        void onComplete(T result);
    }

    /**
     * 実行をバックグラウンド スレッドに移動
     */
    public void makeGetPokemonRequest(
            String endpoint,
            final RepositoryCallback<JSONArray> callback
//          final Handler resultHandler, // main スレッドへのハンドラを渡しておくと UI の変更処理で使える
    ) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "In executor.");
                Result<Response> responseRequest = get(endpoint);
                if (responseRequest instanceof Result.Success) {
                    notifyResult(responseRequest, callback);
                }
            }
        });
    }

    private void notifyResult(
            final Result<Response> result,
            final RepositoryCallback<JSONArray> callback) {
        Response data = (Response) ((Result.Success) result).data;
        try {
            // Log.d(TAG, data.body().getClass().getName()); // okhttp3.internal.http.RealResponseBody // check response type
            String jsonData = data.body().string();
            JSONObject jObject = new JSONObject(jsonData);
            JSONArray jArray = jObject.getJSONArray("results"); // 目的のデータだけ抽出
            callback.onComplete(jArray);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 同期処理
     */
    private Result<Response> get(String endpoint) {
        Request request = new Request.Builder()
                .url(baseUrl + endpoint)
                .build();
        try {
            Log.d(TAG, "Request success");
            Response response = client.newCall(request).execute();
            return new Result.Success<Response>(response);
        } catch (Exception e) {
            Log.d(TAG, "Request failed");
            return new Result.Error<Response>(e);
        }
    }

    /**
     * 非同期処理
     */
    private void asyncGet(String endpoint) {
        Request request = new Request.Builder()
                .url(baseUrl + endpoint)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "asyncGET failed");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Callback の中で UI を操作すると落ちる
                // new Handler(Looper.getMainLooper()) を使って UI スレッドで操作する
            }
        });
    }
}
