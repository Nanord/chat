package server.subscription;

import server.subscription.eventListeners.EventListener;

import java.util.*;

public class EventManager {
    private static EventManager instance;

    public static EventManager getInstance() {
        if(instance == null) {
            synchronized (EventManager.class) {
                if(instance == null)
                    instance = new EventManager();
            }
        }
        return instance;
    }


    private static Map<EventType, List<EventListener>> listeners = Collections.synchronizedMap(new HashMap<>());

    private EventManager() {
        putListeners();
    }

    public static synchronized void putListeners() {
        for (EventType anEventType : EventType.values()) {
            listeners.put(anEventType, new ArrayList<>());
        }
    }


    public synchronized void subscribe(EventType eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public synchronized void unsubscribe(EventType eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    public synchronized void notify(EventType eventType, Object... obj) {
        List<EventListener> users = listeners.get(eventType);
        for (EventListener listener : users) {
            listener.update(obj);
        }
    }

}
