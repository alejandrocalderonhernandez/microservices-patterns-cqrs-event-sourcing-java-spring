package com.viking_bank.cqrs.core.domain;

import com.viking_bank.cqrs.core.events.BaseEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public abstract class AggregateRoot {

    @Getter
    protected String id;

    @Getter
    @Setter
    private Integer version;

    private final Set<BaseEvent> changes;

    protected AggregateRoot() {
        this.version = -1;
        this.changes = new HashSet<>();
    }

    public Set<BaseEvent> getUncommittedChanges() {
        return this.changes;
    }

    public void markChangesAsCommit() {
        this.changes.clear();
    }

    protected void applyChanges(BaseEvent event) {
        try {
            var method = getClass().getDeclaredMethod(method_apply_name, event.getClass());
            method.setAccessible(true);
            method.invoke(this, event);
        }  catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            log.error(e.getMessage());
        } finally {
           this.changes.add(event);
        }
    }

    public void raiseEvent(BaseEvent event) {
        this.applyChanges(event);
    }

    public void replayEvent(Set<BaseEvent> events) {
        events.forEach(this::applyChanges);
    }

    private static final String method_apply_name = "apply";
}
