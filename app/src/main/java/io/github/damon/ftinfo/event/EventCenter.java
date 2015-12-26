package io.github.damon.ftinfo.event;

/**
 * Author:      ZhangYan
 * Date:        15/12/26
 * Description:
 */
public class EventCenter<T> {
    T data;
    int eventType;

    public EventCenter(T data, int eventType) {
        this.data = data;
        this.eventType = eventType;
    }

    public EventCenter(T data) {
        this.data = data;
    }

    public T getData(){
        return data;
    }

    public int getEventType(){
        return eventType;
    }
}
