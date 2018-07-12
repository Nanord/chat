package server.subscription.eventListeners;

import server.subscription.EventType;

public interface EventListener  {
    void update(EventType eventType, Object obj);
}
