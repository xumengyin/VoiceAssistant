package com.x5webview.wv;

public interface WvJsHandler<T,R> {
    void handler(T data, ResponseCallback<R> callback);
}
